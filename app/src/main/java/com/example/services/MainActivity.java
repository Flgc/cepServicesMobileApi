package com.example.services;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import retrofit2.converter.gson.GsonConverterFactory;

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

        // Create the variable for the Views (xml)
        EditText editTextNumberCep =  findViewById(R.id.editTextNumberCep);
        Button buttonSearch =  findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String zipCodNumber = editTextNumberCep.getText().toString();
                consult(zipCodNumber);
            }
        });
    }

    /* Retrofit turns your HTTP API into a Java (or Kotlin) interface. */
    private void consult(String zipCodNumber) {

        TextView textViewInformation = findViewById(R.id.textViewInformation);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create()) // convert json implemented
                .build();

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
                    textViewInformation.setText(address.format());

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