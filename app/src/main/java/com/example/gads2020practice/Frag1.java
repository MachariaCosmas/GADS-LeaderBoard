package com.example.gads2020practice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Frag1 extends Fragment {
    private TextView frag1Result;
    private RecyclerView frag1_list;
    private Frag1Adapter mFrag1Adapter;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private ProgressBar progressBar;

    private List<WatchHours> hourLists;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.frag1_layout, container, false);


        frag1_list = v.findViewById(R.id.frag1_list);
        frag1_list.setHasFixedSize(true);
        frag1_list.setLayoutManager(new LinearLayoutManager(v.getContext()));
       // frag1_list.setAdapter(mFrag1Adapter);


        progressBar=v.findViewById(R.id.progressBar);/////////////

        //instance of retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

            Call<List<WatchHours>> call = jsonPlaceHolderApi.getHourList();

             call.enqueue(new Callback<List<WatchHours>>() {
                    @Override
                    public void onResponse(Call<List<WatchHours>> call, Response<List<WatchHours>> response) {
                        hourLists=response.body();
                        mFrag1Adapter=new Frag1Adapter(hourLists,Frag1.this);
                        frag1_list.setAdapter(mFrag1Adapter);

                        progressBar.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onFailure(Call<List<WatchHours>> call, Throwable t) {

                    }
                });




        return v;
    }


}
