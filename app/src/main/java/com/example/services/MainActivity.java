package com.example.services;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.services.api.InverTextoApi;
import com.example.services.model.Address;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /* Retrofit turns your HTTP API into a Java (or Kotlin) interface. */
    private void consult(String zipCodNumber) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.URL).build();

        InverTextoApi inverTextoApi = retrofit.create(InverTextoApi.class);
        Call<Address> call = inverTextoApi.getAddress(
                zipCodNumber, Constants.TOKEN
        );

        call.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                if (response.isSuccessful()) {

                    Address address = response.body();
                    // Show information return in textViewInformation

                } else {
                    Toast.makeText(MainActivity.this,
                            "Error searching for information, checking postal code!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable throwable) {
                Toast.makeText(MainActivity.this,
                        "Check your internet connection!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}