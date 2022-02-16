package dev_allgot.understand.doitmission_16;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    LinearLayout pannelLayout;
    Button openPannelButton;

    Animation open_pannel;
    Animation close_pannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        open_pannel = AnimationUtils.loadAnimation(this, R.anim.sliding_open);
        close_pannel = AnimationUtils.loadAnimation(this, R.anim.sliding_close);

        close_pannel.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                pannelLayout.setVisibility(View.INVISIBLE);
                openPannelButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        pannelLayout = findViewById(R.id.pannelLayout);
        openPannelButton = findViewById(R.id.openPannelButton);

        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new ViewClient());

        Button goButton = findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText addressEdit = findViewById(R.id.addressEdit);
                String url = addressEdit.getText().toString();
                webView.loadUrl(url);
                addressEdit.setText("");
                pannelLayout.startAnimation(close_pannel);
                InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(addressEdit.getWindowToken(), 0);
            }
        });

        Button openPannelButton = findViewById(R.id.openPannelButton);
        openPannelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPannelButton.setVisibility(View.INVISIBLE);
                pannelLayout.setVisibility(View.VISIBLE);
                pannelLayout.startAnimation(open_pannel);
            }
        });
    }

    public class ViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }
    }
}
