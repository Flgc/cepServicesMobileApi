package com.example.services.api;

import com.example.services.model.Address;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* Retrofit turns your HTTP API into a Java (or Kotlin) interface. */
public interface InverTextoCepApi {
    @GET("/v1/cep/{zipCodNumber}")
    Call<Address> getAddress(
            @Path("zipCodNumber") String zipCodNumber,
            @Query("token") String token
    );
}
