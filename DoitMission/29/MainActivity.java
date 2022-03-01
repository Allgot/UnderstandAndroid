package dev_allgot.understand.doitmission_29;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView friendsRecycler;
    FriendAdapter adapter;

    RequestQueueSingleton requestQueueSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueueSingleton = RequestQueueSingleton.getInstance(this);

        friendsRecycler = findViewById(R.id.friendsRecycler);

        adapter = new FriendAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        friendsRecycler.setLayoutManager(layoutManager);

        adapter.addFriend("친구 1", "010-1111-1111");
        adapter.addFriend("친구 2", "010-1111-1112");

        friendsRecycler.setAdapter(adapter);

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText contentsEdit = findViewById(R.id.contentEdit);
                String msg = contentsEdit.getText().toString();

                JSONObject requestData = new JSONObject();

                try {
                    requestData.put("priority", "high");

                    JSONObject dataObj = new JSONObject();
                    dataObj.put("contents", msg);
                    requestData.put("data", dataObj);

                    JSONArray idArray = new JSONArray();
                    idArray.put(0, "{sample_id_1}");
                    idArray.put(1, "{sample_id_2}");
                    requestData.put("registration_ids", idArray);

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "https://fcm.googleapis.com/fcm/send", requestData, null, null)
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            return new HashMap<String, String>();
                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> headers = new HashMap<String, String>();
                            headers.put("Authorization", "key={sample_server_key}");

                            return headers;
                        }

                        @Override
                        public String getBodyContentType() {
                            return "application/json";
                        }
                    };

                    request.setShouldCache(false);
                    requestQueueSingleton.getRequestQueue().add(request);
                } catch(Exception e) { e.printStackTrace(); }
            }
        });
    }
}
