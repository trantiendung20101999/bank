package com.example.bank.api;

import com.example.bank.model.Account;
import com.example.bank.model.BankSavingBookResponse;
import com.example.bank.model.SaveBankSavingBook;
import com.example.bank.model.UserLoginResponse;
import com.example.bank.model.UserResResponse;
import com.example.bank.model.UserResSend;
import com.example.bank.model.UserUpdate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);



    @POST("api/auth/signin")
    Call<UserLoginResponse>signIn(@Body Account account);
    @POST("api/auth/signup")
    Call<UserResResponse>signUp(@Body UserResSend urs);

    @PUT("api/users/{id}")
    Call<UserLoginResponse>update(@Path("id") long id,
                 @Body UserUpdate ud,
                       @Header("Authorization") String auth);

    @GET("api/auth/sigin_fb")
    Call<UserLoginResponse>signinlogin(@Query("faceId") String faceId);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/bank/{id}")
    Call<BankSavingBookResponse>getBankSavingBook(@Path("id") long id,@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/bank/withdrawal/{id}")
    Call<Integer>DelBankSavingBook(@Path("id") long id,@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/bank/save")
    Call<BankSavingBookResponse>SaveBankSavingBook(@Body SaveBankSavingBook saveBankSavingBook, @Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/bank/moneycurrent/{id}")
    Call<Double>getMoneyCurrent(@Path("id") long id,@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/bank/moneyontime/{id}")
    Call<Double>getMoneyOnTime(@Path("id") long id,@Header("Authorization") String auth);



}
