package com.example.finalproject;

import com.example.finalproject.Model.Asset;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    @FormUrlEncoded
    @POST("/auth/realms/master/protocol/openid-connect/token")
    Call<Asset> authenticate(
            @Field("client_id") String clientId,
            @Field("username") String username,
            @Field("password") String password,
            @Field("grant_type") String grantType,
            @Field("email") String email
    );


}
