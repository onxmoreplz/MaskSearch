package com.example.masksearch

import MaskService
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masksearch.model.Store
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val service: MaskService,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : ViewModel() {
    val itemLivaData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    init {

        fetchStoreInfo()
    }

    @SuppressLint("MissingPermission")
    fun fetchStoreInfo() {
        loadingLiveData.value = true //  로딩 시작

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                viewModelScope.launch {
                    val storeInfo = service.fetchStoreInfo(location.latitude, location.longitude)
                    itemLivaData.value = storeInfo.stores.filter { store ->
                        store.remain_stat != null
                    }

                    loadingLiveData.value = false // 로딩 종료
                }
            }
            .addOnFailureListener {exception ->
                loadingLiveData.value = false // 로딩 종료
            }

    }


}