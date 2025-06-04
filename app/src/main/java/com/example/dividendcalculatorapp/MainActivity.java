package com.example.dividendcalculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView Title, TitleResult, Result1, Result2;
    EditText InvestedFundAmount, AnnualDividendRate, NumberOfMonthsInvested;
    Button BtnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        InvestedFundAmount = findViewById(R.id.InvestedFundAmount);
        AnnualDividendRate = findViewById(R.id.AnnualDividendRate);
        NumberOfMonthsInvested = findViewById(R.id.NumberOfMonthsInvested);
        BtnCalculate = findViewById(R.id.BtnCalculate);
        Result1 = findViewById(R.id.Result1);
        Result2 = findViewById(R.id.Result2);

        BtnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double Invested = Double.parseDouble(InvestedFundAmount.getText().toString());
                    double Rate = Double.parseDouble(AnnualDividendRate.getText().toString());
                    int Month = Integer.parseInt(NumberOfMonthsInvested.getText().toString());

                    if (Month > 12) {
                        Result1.setText("Maximum 12 months only.");
                        return;
                    }

                    double MonthlyDividend = (Rate / 12 / 100) * Invested;
                    double totalDividend = MonthlyDividend * Month;

                    Result1.setText(String.format("Montly Dividend: RM %.2f", MonthlyDividend));
                    Result2.setText(String.format("Total Dividend: RM %.2f", totalDividend));

                } catch (NumberFormatException e) {
                    Result1.setText("Please fill in all fields correctly.");
                    Result2.setText("Please fill in all fields correctly.");
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int choice = item.getItemId();

        if (choice == R.id.mnuHome) {
            Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show();
            return true;

        } else if (choice == R.id.mnuAbout) {
            Toast.makeText(this, "About - Dividend Calculator, created by amira sumayyah", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}