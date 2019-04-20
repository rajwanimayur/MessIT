package com.example.mayur.messit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder> {
    private List<Menu_Item> menu;

    public MenuItemAdapter(List<Menu_Item> menu){
        this.menu = menu;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parentViewGroup, int i) {
        View itemView = LayoutInflater.from(parentViewGroup.getContext())
                .inflate(R.layout.menu_item_row, parentViewGroup, false);
        return new MenuItemViewHolder(itemView);
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
            menuCategory = (TextView)v.findViewById(R.id.menuCategory);
            menuItem =  (TextView)v.findViewById(R.id.menuItem);
        }
    }
}
