package com.muhammad.testcicil.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.muhammad.testcicil.DetailActivity;
import com.muhammad.testcicil.R;
import com.muhammad.testcicil.model.ListModel;

import java.util.List;

public class ListFilmAdapter extends RecyclerView.Adapter<ListFilmAdapter.HolderData> {

    private List<ListModel> listModels;
    private Context context;

    public ListFilmAdapter(List<ListModel> listModels, Context context) {
        this.listModels = listModels;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_movie, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, final int position) {
        holder.textTitleFilm.setText(listModels.get(position).getTitle());
        holder.textDescFilm.setText(listModels.get(position).getType());
        holder.textYearFilm.setText(listModels.get(position).getYear());
        Glide.with(context)
                .load(listModels.get(position).getPoster())
                .into(holder.poster);
        holder.tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("i", listModels.get(position).getImdbID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listModels != null) ? listModels.size() : 0;
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView textTitleFilm,textDescFilm, textYearFilm, tvDetail;
        ImageView poster;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            textTitleFilm = itemView.findViewById(R.id.tv_title_film);
            textDescFilm = itemView.findViewById(R.id.tv_desc_film);
            textYearFilm = itemView.findViewById(R.id.tv_years_film);
            poster = itemView.findViewById(R.id.iv_poster);
            tvDetail = itemView.findViewById(R.id.tv_detail);


        }
    }
}
