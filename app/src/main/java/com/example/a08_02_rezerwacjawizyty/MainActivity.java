package com.example.a08_02_rezerwacjawizyty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private boolean validateForm() {

        if (etService.getText().toString().trim().isEmpty()) {
            etService.setError("Podaj usługę");
            return false;
        }

        if (etDate.getText().toString().trim().isEmpty()) {
            etDate.setError("Podaj datę");
            return false;
        }

        if (etName.getText().toString().trim().isEmpty()) {
            etName.setError("Podaj imię i nazwisko");
            return false;
        }

        if (etPhone.getText().toString().trim().isEmpty()) {
            etPhone.setError("Podaj numer telefonu");
            return false;
        }

        return true;
    }
    @SuppressLint("MissingInflatedId")
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

        findViewById(R.id.btnNext).setOnClickListener(v -> {

            if (!validateForm()) {
                return;
            }

            Intent intent = new Intent(this, SummaryActivity.class);

            intent.putExtra(
                    SummaryActivity.EXTRA_SERVICE,
                    etService.getText().toString().trim()
            );

            intent.putExtra(
                    SummaryActivity.EXTRA_DATE,
                    etDate.getText().toString().trim()
            );

            intent.putExtra(
                    SummaryActivity.EXTRA_NAME,
                    etName.getText().toString().trim()
            );

            intent.putExtra(
                    SummaryActivity.EXTRA_PHONE,
                    etPhone.getText().toString().trim()
            );

            startActivity(intent);
        });

        findViewById(R.id.btnNext).setOnClickListener(v -> {
            Intent intent = new Intent(this, SummaryActivity.class);
            startActivity(intent);
        });

        Intent intent = new Intent(this, SummaryActivity.class);

        intent.putExtra(
                SummaryActivity.EXTRA_SERVICE,
                etService.getText().toString().trim()
        );

        intent.putExtra(
                SummaryActivity.EXTRA_DATE,
                etDate.getText().toString().trim()
        );

        intent.putExtra(
                SummaryActivity.EXTRA_NAME,
                etName.getText().toString().trim()
        );

        intent.putExtra(
                SummaryActivity.EXTRA_PHONE,
                etPhone.getText().toString().trim()
        );

        startActivity(intent);
    }
}