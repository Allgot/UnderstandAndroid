package dev_allgot.understand.doitmission_20;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonRequestQueue {
    private static SingletonRequestQueue instance;
    private RequestQueue requestQueue;
    private static Context cxt;

    private SingletonRequestQueue(Context context) {
        cxt = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized SingletonRequestQueue getInstance(Context context) {
        if(instance == null) instance = new SingletonRequestQueue(context);
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null) requestQueue = Volley.newRequestQueue(cxt.getApplicationContext());
        return requestQueue;
    }

    public <T> void addRequest(Request<T> request) {
        getRequestQueue().add(request);
    }
}
