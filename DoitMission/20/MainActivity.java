package dev_allgot.understand.doitmission_20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArticleAdapter adapter = new ArticleAdapter();
    SingletonRequestQueue requestQueue;
    RecyclerView articlesRecycler;
    String url = "https://www.yonhapnewstv.co.kr/category/news/headline/feed/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articlesRecycler = findViewById(R.id.articlesRecycler);
        requestQueue = SingletonRequestQueue.getInstance(getApplicationContext());

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        articlesRecycler.setLayoutManager(layoutManager);
        articlesRecycler.setAdapter(adapter);

        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                ArticleParser parser = new ArticleParser();
                                try {
                                    ArrayList<Article> articles = parser.parse(new ByteArrayInputStream(response.getBytes()));
                                    adapter.setArticles(articles);
                                    adapter.notifyItemRangeChanged(0, articles.size());
                                } catch(Exception e) { e.printStackTrace(); }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                                Log.d("ErrorResponse", error.toString());
                            }
                        });

                requestQueue.addRequest(request);
            }
        });
    }
}
