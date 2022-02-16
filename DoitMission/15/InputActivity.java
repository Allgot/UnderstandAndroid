package dev_allgot.understand.doitmission_15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActIntent = new Intent(getApplicationContext(), MainActivity.class);
                mainActIntent.putExtra("actAnim", true);
                mainActIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(mainActIntent);
            }
        });

        if(getIntent().getBooleanExtra("actAnim", false)) {
            Animation transition = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.transition);
            findViewById(R.id.container).startAnimation(transition);
        }
    }
}
