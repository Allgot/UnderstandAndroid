package dev_allgot.understand.doitmission_17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean isPannelLayout1Showing = true;
    TextView piText;
    LinearLayout pannelLayout1;
    LinearLayout pannelLayout2;
    Handler handler;

    Animation slideIn;
    Animation slideOut;

    Animation.AnimationListener slideOutListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPannelLayout1Showing) {
                pannelLayout1.setVisibility(View.INVISIBLE);
                isPannelLayout1Showing = false;
            }
            else {
                pannelLayout2.setVisibility(View.VISIBLE);
                isPannelLayout1Showing = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        piText = findViewById(R.id.piText);
        pannelLayout1 = findViewById(R.id.pannelLayout1);
        pannelLayout2 = findViewById(R.id.pannelLayout2);

        slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out);
        slideOut.setAnimationListener(slideOutListener);

        handler = new Handler();

        AlterThread alterThread = new AlterThread();
        alterThread.start();
    }

    public class AlterThread extends Thread {
        @Override
        public void run() {
            Runnable altering = new Runnable() {
                @Override
                public void run() {
                    if(isPannelLayout1Showing) {
                        pannelLayout1.startAnimation(slideOut);
                        pannelLayout2.setVisibility(View.VISIBLE);
                        pannelLayout2.startAnimation(slideIn);
                        piText.setText("2/2");
                    }
                    else {
                        pannelLayout2.startAnimation(slideOut);
                        pannelLayout1.setVisibility(View.VISIBLE);
                        pannelLayout1.startAnimation(slideIn);
                        piText.setText("1/2");
                    }
                    AlterThread.this.run();
                }
            };

            handler.postDelayed(altering, 5000);
        }
    }
}
