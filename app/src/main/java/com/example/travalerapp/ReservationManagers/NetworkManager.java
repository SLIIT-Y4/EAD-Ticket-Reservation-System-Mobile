package com.example.travalerapp.ReservationManagers;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.travalerapp.managers.ContextManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static NetworkManager singleton;

    private Retrofit retrofit;
    private final String baseUrl = "http://10.0.2.2:5000/api/Logins/";
    private ConnectivityManager connectivityManager;

    public static NetworkManager getInstance(){
        if (singleton == null)
            singleton = new NetworkManager();
        return singleton;
    }

    private NetworkManager(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <T> T createService(Class<T> serviceClass)
    {
        return retrofit.create(serviceClass);
    }

    public boolean isNetworkAvailable(){
        Context context = ContextManager.getInstance().getApplicationContext();

        if (connectivityManager == null) {
            connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
        }

        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        boolean available = info != null && info.isConnectedOrConnecting();

        if (!available){
            Toast.makeText(context, "Please connect to the internet and retry", Toast.LENGTH_LONG).show();
        }

        return available;
    }


}

