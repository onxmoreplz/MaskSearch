package com.example.masksearch

import MaskService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masksearch.model.Store
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val itemLivaData =  MutableLiveData<List<Store>>()
    val loadingLiveData =  MutableLiveData<Boolean>()

    //나중에 초기화해도 되는 변수
    private val service: MaskService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(MaskService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        service = retrofit.create(MaskService::class.java)

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