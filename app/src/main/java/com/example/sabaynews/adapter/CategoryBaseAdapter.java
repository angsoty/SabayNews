package com.example.sabaynews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sabaynews.R;
import com.example.sabaynews.models.CategoriesItem;

import java.util.List;

public class CategoryBaseAdapter extends BaseAdapter {
    private final List<CategoriesItem> categoriesItemList;
    private final Context context;

    public CategoryBaseAdapter(List<CategoriesItem> categoriesItemList, Context context) {
        this.categoriesItemList = categoriesItemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return  categoriesItemList.size();
    }

    @Override
    public CategoriesItem getItem(int position) {
        return categoriesItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categoriesItemList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoriesItem item= categoriesItemList.get(position);
        View currentView = LayoutInflater.from(context).inflate(R.layout.category_select_layout,null,false);
        if (currentView !=null){
            TextView title = currentView.findViewById(R.id.catTitle);
            title.setText(item.getName());
        }
        return currentView;
    }
}
