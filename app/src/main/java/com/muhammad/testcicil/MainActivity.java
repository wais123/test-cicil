package com.muhammad.testcicil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.muhammad.testcicil.adapter.ListFilmAdapter;
import com.muhammad.testcicil.model.ListModel;
import com.muhammad.testcicil.model.ResponseModel;
import com.muhammad.testcicil.service.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView, recyclerViewCari;
    private ListFilmAdapter adapter;
    private List<ListModel> list = new ArrayList<>();
    ProgressBar progressBar;
    String s = "Batman";
    String page = "1";
    TextView textView1, textView2,textView3,textView4, textViewListFilm;
    ImageView imageViewSearch, imageViewClose;
    SearchView searchView;
    LinearLayoutCompat layoutCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.rv_list_film);
        recyclerViewCari = findViewById(R.id.rv_cari);
        textView1 = findViewById(R.id.tv_satu);
        textView2 = findViewById(R.id.tv_dua);
        textView3 = findViewById(R.id.tv_tiga);
        textView4 = findViewById(R.id.tv_empat);
        layoutCompat = findViewById(R.id.ln_pagination);
        textViewListFilm = findViewById(R.id.tv_listFilm);
        imageViewSearch = findViewById(R.id.iv_search);
        imageViewClose = findViewById(R.id.iv_close);
        searchView = findViewById(R.id.sv_search);
        LinearLayoutManager listFilm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(listFilm);
        listFilm();

        LinearLayoutManager listSearch = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCari.setLayoutManager(listSearch);
        searchView.setIconified(false);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = "1";
                listFilm();
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = "2";
                listFilm();
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = "3";
                listFilm();
            }
        });

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = "4";
                listFilm();
            }
        });

        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setVisibility(View.VISIBLE);
                imageViewClose.setVisibility(View.VISIBLE);
                imageViewSearch.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                recyclerViewCari.setVisibility(View.VISIBLE);
                textViewListFilm.setVisibility(View.GONE);
                layoutCompat.setVisibility(View.GONE);
            }
        });

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setVisibility(View.GONE);
                imageViewClose.setVisibility(View.GONE);
                imageViewSearch.setVisibility(View.VISIBLE);
                textViewListFilm.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerViewCari.setVisibility(View.GONE);
                layoutCompat.setVisibility(View.VISIBLE);
            }
        });

    }

    private void listFilm() {
            ApiClient.getService().getFilm(s,page).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                progressBar.setVisibility(View.VISIBLE);
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    list = response.body().getSearch();
                    adapter = new ListFilmAdapter(list, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Code: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
