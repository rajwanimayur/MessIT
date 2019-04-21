package com.example.mayur.messit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MenuItemViewHolder> {
    private Context context;
    private List<Menu_Item> menu;

    public RecyclerViewAdapter(Context context, List<Menu_Item> menu) {
        this.context = context;
        this.menu = menu;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.menu_item_row, parent, false);
        MenuItemViewHolder viewHolder = new MenuItemViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder menuItemViewHolder, int i) {
        menuItemViewHolder.menuCategory.setText(menu.get(i).getItemCategory());
        menuItemViewHolder.menuItem.setText(menu.get(i).getItemName());
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }



    public static class MenuItemViewHolder extends RecyclerView.ViewHolder{
        public TextView menuCategory;
        public TextView menuItem;

        public MenuItemViewHolder(View v){
            super(v);
            menuCategory = (TextView)v.findViewById(R.id.itemCategory);
            menuItem =  (TextView)v.findViewById(R.id.itemName);
        }
    }
}
