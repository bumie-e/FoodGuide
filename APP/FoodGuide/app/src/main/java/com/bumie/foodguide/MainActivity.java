package com.bumie.foodguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecylerViewAdapter recylerViewAdapter;
    List<Places> placesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recylerViewAdapter = new RecylerViewAdapter(getApplicationContext(),placesList);
        recyclerView.setAdapter(recylerViewAdapter);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Places>> call = apiService.getMovies();
        call.enqueue(new Callback<List<Places>>() {
            @Override
            public void onResponse(Call<List<Places>> call, Response<List<Places>> response) {
                placesList = response.body();
                Log.d("TAG","Response = "+placesList);
              //  recylerViewAdapter.setMovieList(movieList);
            }

            @Override
            public void onFailure(Call<List<Places>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}