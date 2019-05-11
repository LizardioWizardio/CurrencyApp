package com.example.currency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.currency.interfaces.CurrencyApi;
import com.example.currency.model.Currency;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String API_URL  = "http://www.apilayer.net/api/";

    Retrofit retrofit;
    CurrencyApi currencyApi;

    Double currencyValue;

    EditText etCurrencyNumber;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvCurrencyValue = findViewById(R.id.tvCurrencyValue);
        etCurrencyNumber = findViewById(R.id.etCurrencyNumber);
        tvResult = findViewById(R.id.tvResult);

        Button btnUsdToRub = findViewById(R.id.btnUsdToRub);
        Button btnRubToUsd = findViewById(R.id.btnRubToUsd);
        btnRubToUsd.setOnClickListener(this);
        btnUsdToRub.setOnClickListener(this);
        //retrofit builder
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        currencyApi = retrofit.create(CurrencyApi.class);

        Call<Currency> currency = currencyApi.getCurrencyUSDToRUB();
        currency.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                currencyValue = response.body().getQuotes().getUSDRUB();
                tvCurrencyValue.setText(String.valueOf(currencyValue));
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.err_get_currency), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (checkValue() && currencyValue != null) {
            double result;
            switch (v.getId()) {
                case R.id.btnRubToUsd:
                    result = Double.parseDouble(etCurrencyNumber.getText().toString()) / currencyValue;
                    tvResult.setText(String.valueOf(result));
                    break;
                case R.id.btnUsdToRub:
                    result = Double.parseDouble(etCurrencyNumber.getText().toString()) * currencyValue;
                    tvResult.setText(String.valueOf(result));
                    break;
            }
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.wrong_values_text), Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkValue() {
        if (etCurrencyNumber.getText().toString().equals(""))
            return false;
        else
            return true;
    }
}
