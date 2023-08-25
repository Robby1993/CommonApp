package com.robinson.checkinternetconnection.network

import com.robinson.checkinternetconnection.model.ApiResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("endpoint")
    fun fetchData(): Observable<ApiResponse>
}
