package dev_allgot.understand.doitmission_07;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int menu=-100;

                    if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                        menu = result.getData().getIntExtra("menu", -1);
                    }

                    switch(menu) {
                        case 100:
                            Toast.makeText(getApplicationContext(), "고객 관리", Toast.LENGTH_SHORT).show();
                            break;

                        case 200:
                            Toast.makeText(getApplicationContext(), "매출 관리", Toast.LENGTH_SHORT).show();
                            break;

                        case 300:
                            Toast.makeText(getApplicationContext(), "상품 관리", Toast.LENGTH_SHORT).show();
                            break;

                        case -1:
                            Toast.makeText(getApplicationContext(), "결과 값이 잘못되었습니다.", Toast.LENGTH_SHORT).show();

                        default:
                            Toast.makeText(getApplicationContext(), "결과 값을 받아오지 못했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                launcher.launch(intent);
            }
        });
    }
}
