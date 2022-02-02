package dev_allgot.understand.doitmission_03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView upperImage;
    ImageView lowerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upperImage = findViewById(R.id.upperImage);
        lowerImage = findViewById(R.id.lowerImage);

        BitmapDrawable bitmap = (BitmapDrawable) getResources().getDrawable(R.drawable.cat_image);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        upperImage.setImageDrawable(bitmap);
        upperImage.getLayoutParams().width = bitmapWidth;
        upperImage.getLayoutParams().height = bitmapHeight;

        lowerImage.setImageDrawable(bitmap);
        lowerImage.getLayoutParams().width = bitmapWidth;
        lowerImage.getLayoutParams().height = bitmapHeight;

        lowerImage.setVisibility(View.INVISIBLE);

        Button upperButton = findViewById(R.id.upperButton);
        upperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(upperImage.getVisibility() == View.INVISIBLE) {
                    upperImage.setVisibility(View.VISIBLE);
                    lowerImage.setVisibility(View.INVISIBLE);
                }
            }
        });

        Button lowerButton = findViewById(R.id.lowerButton);
        lowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lowerImage.getVisibility() == View.INVISIBLE) {
                    lowerImage.setVisibility(View.VISIBLE);
                    upperImage.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
