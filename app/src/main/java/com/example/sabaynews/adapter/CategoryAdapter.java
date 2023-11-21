package com.example.sabaynews.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sabaynews.R;
import com.example.sabaynews.models.CategoriesItem;
import com.example.sabaynews.uis.ListArticleByCategoryIdActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private final List<CategoriesItem> categoriesItems;
    private final Context context;


    public CategoryAdapter(List<CategoriesItem> categoriesItems, Context context) {
        this.categoriesItems = categoriesItems;
        this.context = context;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder((LayoutInflater.from(context).inflate(R.layout.category_card_item_layout,null,false)));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoriesItem item = categoriesItems.get(position);
        if (!item.getArticles().isEmpty())
        {
            ArticleAdapter articleAdapter = new ArticleAdapter(item.getArticles(),context);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,1,RecyclerView.HORIZONTAL,false);
            holder.recyclerView.setLayoutManager(gridLayoutManager);
            holder.recyclerView.setAdapter(articleAdapter);
        }
        if (item.getName()!=null)
        {
            holder.name.setText(item.getName());
        }
        holder.more.setOnClickListener(view -> {
            Intent intent = new Intent(context, ListArticleByCategoryIdActivity.class);
            intent.putExtra("ID",item.getId());
            intent.putExtra("Name",item.getName());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return categoriesItems.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        private final TextView name;
        private final TextView more;
        private final RecyclerView recyclerView;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtCategoryName);
            more = itemView.findViewById((R.id.txtSeeMore));
            recyclerView = itemView.findViewById(R.id.recyclerViewArticle);
        }
    }
}
