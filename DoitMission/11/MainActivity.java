package dev_allgot.understand.doitmission_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText inputEdit;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEdit = findViewById(R.id.inputEdit);
        outputText = findViewById(R.id.outputText);

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(getApplicationContext(), TextService.class);
                String input = inputEdit.getText().toString();
                serviceIntent.putExtra("input", input);
                startService(serviceIntent);
            }
        });

        if(getIntent().getStringExtra("output") != null) outputText.setText(getIntent().getStringExtra("output"));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent.getStringExtra("output") != null) outputText.setText(intent.getStringExtra("output"));
    }
}
