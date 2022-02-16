package dev_allgot.understand.doitmission_15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toInputButton = findViewById(R.id.toInputButton);

        toInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inputActIntent = new Intent(getApplicationContext(), InputActivity.class);
                inputActIntent.putExtra("actAnim", true);
                startActivity(inputActIntent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent.getBooleanExtra("actAnim", false)) {
            Animation transition = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.transition);
            findViewById(R.id.container).startAnimation(transition);
        }
    }
}
