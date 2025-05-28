package com.example.citranusantaraandroid.viewmodel

import android.util.Log
import androidx.compose.ui.text.Placeholder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citranusantaraandroid.model.DisplayableListItem
import com.example.citranusantaraandroid.networking.ApiClient
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


// UI State
data class CategoryListUIState(
    val isLoading: Boolean = false,
    val items: List<DisplayableListItem> = emptyList(),
    val error: String? = null,
    val categoryTitle: String = ""
)



class CategoryItemsViewModel(
    private val categoryEndPointPath : String,
    private val categoryTitleFromArgs: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryListUIState(categoryTitle = categoryTitleFromArgs))
    val uiState: StateFlow<CategoryListUIState> = _uiState


    init {
        fetchCategoryItems()
    }

    fun fetchCategoryItems() {

        // set loading first
        _uiState.value = uiState.value.copy(isLoading = true, error=null)

        viewModelScope.launch {
            try {
                val response = ApiClient.instance.getCategoryItems(categoryEndPointPath)

                if(response.isSuccessful) {
                    val jsonElement = response.body()

                    if (jsonElement != null && jsonElement is JsonObject) {
                        val dataArray = jsonElement.asJsonObject.getAsJsonArray("data")
                        val displayableListItems = mutableListOf<DisplayableListItem>()
                        dataArray?.forEach { element ->
                            if (element.isJsonObject) {
                                val jsonObject = element.asJsonObject
                                // manually parsing with fallback for different field name
                                val item = mapJsonObjectToDisplayableListItem(jsonObject, categoryEndPointPath)
                                if (item != null) {
                                    displayableListItems.add(item)
                                }
                            }
                        }
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            items = displayableListItems
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = "Error: Invalid JSON response"
                        )
                    }

                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Error: ${response.code()} - ${response.message()}"
                    )
                }


            } catch (e: Exception) {
                Log.e("fetchCategoryVM", "Exception: ${e.localizedMessage} ",e )
                _uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = "Load data failed : ${e.localizedMessage}"
                )
            }
        }
    }


    // map JsonObject into DisplayableListItem
    private fun mapJsonObjectToDisplayableListItem(jsonObject: JsonObject, categoryPath: String): DisplayableListItem? {
        try{

            val id = jsonObject.get("id").asInt
            val cultureName = getStringFromPossibleKeys(jsonObject, listOf("sitename", "culinaryname", "dancename", "folklorename", "heritagename")) ?: "Name not available"
            val cultureOrigin = getStringFromPossibleKeys(jsonObject, listOf("siteorigin","culinaryorigin", "danceorigin", "folkloreorigin", "heritageorigin")) ?: "Origin not available"
            val cultureTranslationName = getStringFromPossibleKeys(jsonObject, listOf("sitetranslationname", "culinarytranslationname", "dancetranslationname", "folkloretranslationname", "heritagetranslationname")) ?: "Translation name not available"
            val cultureDescription = getStringFromPossibleKeys(jsonObject, listOf("sitetranslationdescription", "culinarytranslationdescription", "dancetranslationdescription", "folkloretranslationdescription", "heritagetranslationdescription")) ?: "Description not available"
            val cultureThumbnailImage = getStringFromPossibleKeys(jsonObject, listOf("sitethumbnailimage", "culinarythumbnailimage", "dancethumbnailimage", "folklorethumbnailimage", "heritagethumbnailimage")) ?: "Thumbnail not available"

            return DisplayableListItem(
                id = id,
                name = cultureName,
                origin = cultureOrigin,
                translationName = cultureTranslationName,
                description = cultureDescription,
                thumbnailImage = cultureThumbnailImage
            )
        } catch(e: Exception) {
            Log.e("CategoryVM", "Error parsing jsonObject to DisplayableListItem: ${e.localizedMessage} ", )
            return null
        }
    }

    // helper to get string from some possibility name of json key
    private fun getStringFromPossibleKeys(jsonObject: JsonObject, keys: List<String>): String? {
        for (key in keys) {
            if (jsonObject.has(key) && !jsonObject.get(key).isJsonNull) {
                return jsonObject.get(key).asString
            }
        }
        return null
    }
}