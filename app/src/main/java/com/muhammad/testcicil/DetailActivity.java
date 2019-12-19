package com.muhammad.testcicil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.muhammad.testcicil.model.DetailModel;
import com.muhammad.testcicil.model.DetailModelRating;
import com.muhammad.testcicil.service.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    ImageView ivBack, ivPoster;
    TextView textViewTitle, textViewDescShort,textViewYears, textViewDesc, textViewRating;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivBack = findViewById(R.id.iv_back);
        textViewTitle = findViewById(R.id.tv_title_film);
        textViewDescShort = findViewById(R.id.tv_short_desc);
        textViewYears = findViewById(R.id.tv_years_film);
        textViewRating = findViewById(R.id.tv_rating);
        textViewDesc = findViewById(R.id.tv_desc);
        ivPoster = findViewById(R.id.iv_poster);
        progressBar = findViewById(R.id.progressBar);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        getDetil(intent.getStringExtra("i"));
    }

    private void getDetil(String i) {
        ApiClient.getService().getDetil(i).enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {
                progressBar.setVisibility(View.VISIBLE);
                List<DetailModelRating> modelRating = response.body().getRatings();
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    textViewTitle.setText(response.body().getTitle());
                    textViewDescShort.setText(response.body().getGenre() +" - "+response.body().getRuntime());
                    textViewYears.setText(response.body().getYear()+" - ");
                    textViewRating.setText(modelRating.get(0).getValue());
                    textViewDesc.setText(response.body().getWriter());
                    Glide.with(DetailActivity.this)
                            .load(response.body().getPoster())
                            .into(ivPoster);
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(DetailActivity.this, "Code: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DetailActivity   .this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
