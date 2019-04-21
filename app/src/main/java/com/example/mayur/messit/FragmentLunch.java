package com.example.mayur.messit;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;


import java.util.List;

public class FragmentLunch extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private List<Menu_Item> menuItems;
    public FragmentLunch() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.lunch_fragment,container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.lunch_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Spinner spinner = (Spinner) menu.findItem(R.id.daySpinner).getActionView();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String daySelected = parent.getItemAtPosition(position).toString();
                updateMenu(daySelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void updateMenu(String daySelected){
        new FirebaseDatabaseHelper().readMenu(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void dataIsLoaded(List<Menu_Item> menuItems) {
                v.findViewById(R.id.progressBar).setVisibility(View.GONE);
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(),menuItems);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void dataIsInserted() {

            }

            @Override
            public void dataIsUpdate() {

            }

            @Override
            public void dataIsDeleted() {

            }
        },daySelected,"Lunch");

    }
}
