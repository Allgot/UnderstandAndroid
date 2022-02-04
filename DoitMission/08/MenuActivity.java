package dev_allgot.understand.doitmission_08;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int goTo = -100;
                    int from = -100;

                    if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                        goTo = result.getData().getIntExtra("goTo", -1);
                        from = result.getData().getIntExtra("from", -1);
                    }
                    if(goTo == 0) {
                        Intent resIntent = new Intent();
                        resIntent.putExtra("from", from);
                        setResult(RESULT_OK, resIntent);
                        finish();
                    }
                    else if(goTo == 1) {
                        switch(from) {
                            case 0:
                                Toast.makeText(getApplicationContext(), "고객 관리", Toast.LENGTH_SHORT).show();
                                break;

                            case 1:
                                Toast.makeText(getApplicationContext(), "매출 관리", Toast.LENGTH_SHORT).show();
                                break;

                            case 2:
                                Toast.makeText(getApplicationContext(), "상품 관리", Toast.LENGTH_SHORT).show();
                                break;

                            case -1:
                                Log.d("ActivityTransitionError","from 값이 유효하지 않음");
                                break;

                            default:
                                Log.d("ActivityTransitionError", "from 값을 받아오지 못함.");
                        }
                    }
                    else if(goTo == -1) Log.d("ActivityTransitionError", "goTo 값이 유효하지 않음");
                    else Log.d("ActivityTransitionError", "goTo 값을 받아오지 못함.");
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button mngCustomerButton = findViewById(R.id.mngCustomerButton);
        Button mngSalesButton = findViewById(R.id.mngSalesButton);
        Button mngGoodsButton = findViewById(R.id.mngGoodsButton);

        mngCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MngCustomerActivity.class);
                launcher.launch(intent);
            }
        });

        mngSalesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MngSalesActivity.class);
                launcher.launch(intent);
            }
        });

        mngGoodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MngGoodsActivity.class);
                launcher.launch(intent);
            }
        });
    }
}
