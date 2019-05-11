package com.example.currency.interfaces;

import com.example.currency.model.Currency;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyApi {
    public final String API_KEY = "/live?access_key=d417aa627081fe837a941d6196a483e8&currencies=RUB";
    @GET(API_KEY)
    Call<Currency> getCurrencyUSDToRUB();
}
