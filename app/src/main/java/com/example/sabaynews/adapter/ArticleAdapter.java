package com.example.sabaynews.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sabaynews.R;
import com.example.sabaynews.models.ArticlesItem;
import com.example.sabaynews.uis.ArticleDetailActivity;
import com.example.sabaynews.uis.FormArticleActivity;

import java.util.List;

public class ArticleAdapter  extends RecyclerView.Adapter<ArticleAdapter.ArticleVewHolder> {
    private final List<ArticlesItem> articlesItems;
    private final Context context;
    private String showView;

    public ArticleAdapter(List<ArticlesItem> articlesItems, Context context, String showView) {
        this.articlesItems = articlesItems;
        this.context = context;
        this.showView = showView;
    }

    public ArticleAdapter(List<ArticlesItem> articlesItems, Context context) {
        this.articlesItems = articlesItems;
        this.context = context;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public ArticleVewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (showView==null)
        {
            return new ArticleVewHolder(LayoutInflater.from(context).inflate(R.layout.article_card_item_layout,null,false));
        }
        return new ArticleVewHolder(LayoutInflater.from(context).inflate(R.layout.article_list_item_layout,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleVewHolder holder, int position) {
        ArticlesItem item = articlesItems.get(position);
        if (item.getTitle()!=null)
        {
            holder.title.setText(item.getTitle());
        }
        if (item.getImageUrl()!=null){
            Glide.with(context)
                    .load(item.getImageUrl())
                    .centerCrop()
                    .placeholder(R.drawable.image)
                    .into(holder.imageView);
        }
        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ArticleDetailActivity.class);
            intent.putExtra("ID",item.getId());
            context.startActivity(intent);
        });
        holder.edit.setOnClickListener(v -> {
            Intent intent = new Intent(context, FormArticleActivity.class);
            intent.putExtra("ID",item.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articlesItems.size();
    }

    public static class ArticleVewHolder extends RecyclerView.ViewHolder{
    ImageView imageView,edit;
    TextView title;

        public ArticleVewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtArticleTitle);
            imageView= itemView.findViewById(R.id.imgViewArticle);
            edit=itemView.findViewById(R.id.ivEdit);
        }
    }
}
