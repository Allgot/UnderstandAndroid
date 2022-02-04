package dev_allgot.understand.doitmission_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MngGoodsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mng_goods);

        Button toMenuButton = findViewById(R.id.toMenuButton);
        Button toLoginButton = findViewById(R.id.toLoginButton);

        toMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resIntent = new Intent();
                resIntent.putExtra("goTo", 1); // go to MenuActivity
                resIntent.putExtra("from", 2); // from MngGoodsActivity

                setResult(RESULT_OK, resIntent);
                finish();
            }
        });

        toLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resIntent = new Intent();
                resIntent.putExtra("goTo", 0); // go to MainActivity
                resIntent.putExtra("from", 2); // from MngGoodsActivity

                setResult(RESULT_OK, resIntent);
                finish();
            }
        });
    }
}
