package com.example.a08_02_rezerwacjawizyty;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        String service = intent.getStringExtra(EXTRA_SERVICE);
        String date = intent.getStringExtra(EXTRA_DATE);
        String name = intent.getStringExtra(EXTRA_NAME);
        String phone = intent.getStringExtra(EXTRA_PHONE);

        String summary =
                "Usługa: " + service +
                        "\nData: " + date +
                        "\nImię i nazwisko: " + name +
                        "\nTelefon: " + phone;

        TextView tvSummary = findViewById(R.id.tvSummary);
        tvSummary.setText(summary);


    }
    public static final String EXTRA_SERVICE =
            "com.example.rezerwacja.SERVICE";

    public static final String EXTRA_DATE =
            "com.example.rezerwacja.DATE";

    public static final String EXTRA_NAME =
            "com.example.rezerwacja.NAME";

    public static final String EXTRA_PHONE =
            "com.example.rezerwacja.PHONE";

    public static final String EXTRA_NUMBER =
            "com.example.rezerwacja.NUMBER";
}