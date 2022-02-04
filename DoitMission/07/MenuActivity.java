package dev_allgot.understand.doitmission_07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button manageCustomerButton = findViewById(R.id.manageCustomerButton);
        Button manageSalesButton = findViewById(R.id.manageSalesButton);
        Button manageGoodsButton = findViewById(R.id.manageGoodsButton);

        manageCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resIntent = new Intent();
                resIntent.putExtra("menu", 100);

                setResult(RESULT_OK, resIntent);
            }
        });

        manageSalesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resIntent = new Intent();
                resIntent.putExtra("menu", 200);

                setResult(RESULT_OK, resIntent);
            }
        });

        manageGoodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resIntent = new Intent();
                resIntent.putExtra("menu", 300);

                setResult(RESULT_OK, resIntent);
            }
        });
    }
}
