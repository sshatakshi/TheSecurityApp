package com.saurabh.vaish.womensafety;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class ShowContacts extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts);

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("saasa", "before datasnap");

        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot u: dataSnapshot.getChildren())
                {
//                    Log.d("saasa", u.getValue(User.class));
                    addToArray(u.getValue(User.class));

                }
                Log.d("saasa", "in datasnap");
                makeAdapter();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    void addToArray(User u)
    {
        users.add(u);
        Log.d("saasa_data", users.get(0).toString());
    }
    void makeAdapter()
    {
        CustomAdapter adapter = new CustomAdapter(this, users);
        Log.d("saasa", "adapter done");
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setAdapter((adapter));
    }
}
