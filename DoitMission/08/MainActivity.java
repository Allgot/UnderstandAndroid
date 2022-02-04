package dev_allgot.understand.doitmission_08;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText idEdit;
    EditText pwEdit;
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int from = -100;

                    if(result.getResultCode() == RESULT_OK && result.getData() != null)
                        from = result.getData().getIntExtra("from", -1);

                    switch(from) {
                        case 0:
                            Toast.makeText(getApplicationContext(), "고객 관리", Toast.LENGTH_SHORT).show();
                            break;

                        case 1:
                            Toast.makeText(getApplicationContext(), "매출  관리", Toast.LENGTH_SHORT).show();
                            break;

                        case 2:
                            Toast.makeText(getApplicationContext(), "상품 관리", Toast.LENGTH_SHORT).show();
                            break;

                        case -1:
                            Log.d("ActivityTransitionError", "from 값이 유효하지 않음.");
                            break;

                        default:
                            Log.d("ActivityTransitionError", "from 값을 받아오지 못함.");
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEdit = findViewById(R.id.idEdit);
        pwEdit = findViewById(R.id.pwEdit);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idEdit.getText().toString().equals("")) Toast.makeText(getApplicationContext(), "아이디가 입력되지 않았습니다.", Toast.LENGTH_LONG).show();
                else if(pwEdit.getText().toString().equals("")) Toast.makeText(getApplicationContext(), "비밀번호가 입력되지 않았습니다", Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    launcher.launch(intent);
                }
            }
        });
    }
}
