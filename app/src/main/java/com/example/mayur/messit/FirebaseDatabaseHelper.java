package com.example.mayur.messit;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<Menu_Item> menuItems = new ArrayList<>();

    public interface DataStatus{
        void dataIsLoaded(List<Menu_Item> menuItems);
        void dataIsInserted();
        void dataIsUpdate();
        void dataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void readMenu(final DataStatus dataStatus, String daySelected, String mealSelected){
        databaseReference.child("Days").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                menuItems.clear();
                for (DataSnapshot menu_item : dataSnapshot.child(daySelected).child(mealSelected).getChildren()) {
                    Menu_Item item = new Menu_Item(
                            menu_item.getKey(),
                            menu_item.getValue().toString());
                    menuItems.add(item);
                }

                dataStatus.dataIsLoaded(menuItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateUserInfo(FirebaseUser firebaseUser, User aUser){
        databaseReference.child("Users").child(firebaseUser.getUid()).setValue(aUser);
    }
}
