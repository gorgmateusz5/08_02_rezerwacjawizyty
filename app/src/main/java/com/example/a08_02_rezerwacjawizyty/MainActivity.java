package com.example.a08_02_rezerwacjawizyty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

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


    private void confirm() {

        String number =
                "R/" + (1000 + new Random().nextInt(9000));

        Intent result = new Intent();

        result.putExtra(
                EXTRA_NUMBER,
                number
        );

        setResult(RESULT_OK, result);
        finish();
    }
    private final ActivityResultLauncher<Intent> summaryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if (result.getResultCode() == RESULT_OK
                                && result.getData() != null) {

                            String number = result.getData()
                                    .getStringExtra(
                                            SummaryActivity.EXTRA_NUMBER
                                    );

                            Toast.makeText(
                                    this,
                                    "Rezerwacja: " + number,
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });

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
        findViewById(R.id.btnAbout).setOnClickListener(v -> {

            Intent intent = new Intent(
                    this,
                    AboutActivity.class
            );

            startActivity(intent);
        });

        findViewById(R.id.btnConfirm)
                .setOnClickListener(v -> confirm());

        findViewById(R.id.btnEdit)
                .setOnClickListener(v -> finish());

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

            summaryLauncher.launch(intent);
        });



        findViewById(R.id.btnNext).setOnClickListener(v -> {
            Intent intent = new Intent(this, SummaryActivity.class);
            summaryLauncher.launch(intent);

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

        summaryLauncher.launch(intent);
    }
}