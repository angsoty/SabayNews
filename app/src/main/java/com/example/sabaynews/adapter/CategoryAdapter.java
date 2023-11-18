package com.example.sabaynews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sabaynews.R;
import com.example.sabaynews.models.CategoriesItem;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private List<CategoriesItem> categoriesItemList;
    private Context context;


    public CategoryAdapter(List<CategoriesItem> categoriesItemList, Context context) {
        this.categoriesItemList = categoriesItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder((LayoutInflater.from(context).inflate(R.layout.category_card_item_layout,null,false)));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoriesItem item = categoriesItemList.get(position);
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
    }

    @Override
    public int getItemCount() {
        return categoriesItemList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView name,more;
        private RecyclerView recyclerView;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtCategoryName);
            more = itemView.findViewById((R.id.txtArticleTitle));
            recyclerView = itemView.findViewById(R.id.recyclerViewArticle);
        }
    }
}
