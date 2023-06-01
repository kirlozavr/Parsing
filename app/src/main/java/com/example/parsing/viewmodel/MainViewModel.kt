package com.example.parsing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parsing.entity.ImageEntity
import com.example.parsing.model.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private var apiService: ApiService) : ViewModel() {

    private val imagesLiveData = MutableLiveData<List<ImageEntity>>()
    private val errorMessage = MutableLiveData<String>()

    fun requestImages() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.loadImages()
            imagesLiveData.postValue(response)
        }

    }

    fun getImages(): LiveData<List<ImageEntity>> {
        return imagesLiveData
    }

    fun getError(): LiveData<String> {
        return errorMessage
    }
}