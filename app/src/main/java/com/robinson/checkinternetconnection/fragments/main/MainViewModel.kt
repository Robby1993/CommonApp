package com.robinson.checkinternetconnection.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robinson.checkinternetconnection.model.ApiResponse
import com.robinson.checkinternetconnection.network.ApiService
import com.robinson.checkinternetconnection.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val apiService = RetrofitClient.instance.create(ApiService::class.java)
    private val disposables = CompositeDisposable()

    private val _data = MutableLiveData<ApiResponse>()
    val data: LiveData<ApiResponse> = _data

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun fetchData() {
        val disposable = apiService.fetchData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _data.value = response
            }, { error ->
                _error.value = "Error fetching data"
            })

        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}