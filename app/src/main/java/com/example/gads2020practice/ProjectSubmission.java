package com.example.gads2020practice;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectSubmission extends AppCompatActivity {

    Button btnSubmitProject;
    EditText first_name, last_name, email_address, github_link;
    String Stringfname, Stringlname, Stringemail, Stringgitlink;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private ImageView btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        Toolbar toolbar = findViewById(R.id.projecttoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnSubmitProject = findViewById(R.id.btnSubmitProject);

        //builder = new AlertDialog.Builder(this);

        first_name = findViewById(R.id.input_first_name);
        last_name = findViewById(R.id.input_last_name);
        email_address = findViewById(R.id.input_email_address);
        github_link = findViewById(R.id.input_github_link);

        //instance of retrofit
        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        btnSubmitProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Stringfname = first_name.getText().toString();
                Stringlname = last_name.getText().toString();
                Stringemail = email_address.getText().toString();
                Stringgitlink = github_link.getText().toString();

                //createPost();
                //confirmDialog();
                inputValidation();
            }

        });


    }


    private void inputValidation() {
        String cannotBeBlank = "Input cannot be blank";
        String mustBeValidEmail = "Enter a valid email";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

        Stringfname = first_name.getText().toString();
        Stringlname = last_name.getText().toString();
        Stringemail = email_address.getText().toString();
        Stringgitlink = github_link.getText().toString();
        if (Stringfname.isEmpty()) {
            first_name.setError(cannotBeBlank);
            return;
        }
        else if (Stringlname.isEmpty()) {
            last_name.setError(cannotBeBlank);
        }
       else if (Stringemail.isEmpty()) {
            email_address.setError(cannotBeBlank);
        }
       else if (Stringgitlink.isEmpty()) {
            github_link.setError(cannotBeBlank);
        }
        else if (!(Stringemail.matches((emailPattern)) || Stringemail.matches(emailPattern2))) {
            email_address.setError(mustBeValidEmail);
        }else{
            confirmDialog();
        }





    }

    private void confirmDialog() {

       //AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog confirmDialog = new AlertDialog.Builder(this).create();
        final LayoutInflater inflater = getLayoutInflater();
        final View dialoglayout = inflater.inflate(R.layout.confirm_dialog, null);
        confirmDialog.setView(dialoglayout);
        confirmDialog.setCancelable(true);
        confirmDialog.show();

        confirmDialog.getWindow().setLayout(1000, 700);


        btnCancel=confirmDialog.findViewById(R.id.btncancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog.dismiss();
            }
        });



        Button btnConfirm=dialoglayout.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPost();
                confirmDialog.dismiss();
            }
        });
    }


    private void createPost() {

        Call<Void> call = jsonPlaceHolderApi.createPost(
                Stringfname,
                Stringlname,
                Stringemail,
                Stringgitlink);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.d("ProjectSubmission",String.valueOf(response.code()));
                    failDialog();//if fail then display fail dialog
                    return;
                }
                Log.d("PostResult", String.valueOf(response.code()));
                successDialog(); //if succesful then display succesful dialog
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                failDialog();
                Log.d("ProjectSubmission",String.valueOf(t.getMessage()));
            }
        });
    }


    private void successDialog() {
        final AlertDialog successDialog = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.success_dialog, null);
        successDialog.setView(dialoglayout);
        successDialog.show();
        successDialog.getWindow().setLayout(1000, 700);
    }
    private void failDialog() {
        final AlertDialog failDialog = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.fail_dialog, null);
        failDialog.setView(dialoglayout);
        failDialog.show();
        failDialog.getWindow().setLayout(1000, 700);
    }



}
