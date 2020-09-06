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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Frag2 extends Fragment {
    private RecyclerView frag2_list;
    private Frag2Adapter mFrag2Adapter;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private ProgressBar progressBar;

    private List<SkillIQ> IQLists;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.frag2_layout, container, false);


        frag2_list = v.findViewById(R.id.frag2_list);
        frag2_list.setHasFixedSize(true);
        frag2_list.setLayoutManager(new LinearLayoutManager(v.getContext()));
       // frag1_list.setAdapter(mFrag1Adapter);

        progressBar=v.findViewById(R.id.progressBar);

        //instance of retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

            Call<List<SkillIQ>> call = jsonPlaceHolderApi.getSkillList();

             call.enqueue(new Callback<List<SkillIQ>>() {
                    @Override
                    public void onResponse(Call<List<SkillIQ>> call, Response<List<SkillIQ>> response) {
                        IQLists=response.body();
                        mFrag2Adapter=new Frag2Adapter(IQLists, Frag2.this);
                        frag2_list.setAdapter(mFrag2Adapter);
                        progressBar.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onFailure(Call<List<SkillIQ>> call, Throwable t) {

                    }
                });




        return v;
    }


}
