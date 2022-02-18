package dev_allgot.understand.doitmission_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {
    SingletonRequestQueue singletonRequestQueue;
    EditText addressEdit;
    TextView responseText;
    WebView responseWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singletonRequestQueue = SingletonRequestQueue.getInstance(getApplicationContext());
        addressEdit = findViewById(R.id.addressEdit);
        responseText = findViewById(R.id.responseText);
        responseWeb = findViewById(R.id.responseWeb);

        Button requestButton = findViewById(R.id.requestButton);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sUrl = addressEdit.getText().toString();
                StringRequest request = new StringRequest(Request.Method.GET, sUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                responseText.append(response);
                                responseWeb.loadDataWithBaseURL(null, response, "text/html", "utf-8", null);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                responseText.append(error.toString());
                            }
                        });

                singletonRequestQueue.addRequest(request);
            }
        });
    }
}
