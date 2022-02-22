package dev_allgot.understand.doitmission_23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    PaintBoard board;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = findViewById(R.id.paintBoard);
        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.buttButton:
                        board.setCap("BUTT");
                        break;
                    case R.id.roundButton:
                        board.setCap("ROUND");
                        break;
                    case R.id.squareButton:
                        board.setCap("SQUARE");
                        break;
                }
            }
        });
    }
}
