package com.example.gads2020practice;

import com.example.gads2020practice.LearningHours.WatchHours;
import com.example.gads2020practice.SkillIQ.SkillIQ;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("api/skilliq")
    Call<List<SkillIQ>> getSkillList();

    @GET("api/hours")
    Call<List<WatchHours>> getHourList();

    @FormUrlEncoded
   // @Headers("Content-Type: application/json")
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Void> createPost(
            @Field("entry.1824927963") String email_address,
            @Field("entry.1877115667") String first_name,
            @Field("entry.2006916086") String last_name,
            @Field("entry.284483984") String github_link
    );


}
