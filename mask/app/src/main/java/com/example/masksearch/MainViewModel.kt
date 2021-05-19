package com.example.masksearch

import MaskService
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masksearch.model.Store
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel @ViewModelInject constructor(
    private val service: MaskService
): ViewModel() {
    val itemLivaData =  MutableLiveData<List<Store>>()
    val loadingLiveData =  MutableLiveData<Boolean>()

    init {
        fetchStoreInfo()
    }

    fun fetchStoreInfo() {
        loadingLiveData.value = true //  로딩 시작

        viewModelScope.launch {
            val storeInfo = service.fetchStoreInfo(37.1111, 128.2222)
            itemLivaData.value = storeInfo.stores

            loadingLiveData.value = false // 로딩 종료
        }
    }


}