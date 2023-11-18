package com.example.sabaynews.adapter;

import android.content.Context;
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
import com.example.sabaynews.models.CategoriesItem;

import java.util.List;

public class ArticleAdapter  extends RecyclerView.Adapter<ArticleAdapter.ArticleVewHolder> {
    private List<ArticlesItem> articlesItems;
    private Context context;

    public ArticleAdapter(List<ArticlesItem> articlesItems, Context context) {
        this.articlesItems = articlesItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ArticleVewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleVewHolder(LayoutInflater.from(context).inflate(R.layout.article_card_item_layout,null,false));
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
    }

    @Override
    public int getItemCount() {
        return articlesItems.size();
    }

    public static class ArticleVewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView title;
        public ArticleVewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtArticleTitle);
            imageView= itemView.findViewById(R.id.imgViewArticle);
        }
    }
}
