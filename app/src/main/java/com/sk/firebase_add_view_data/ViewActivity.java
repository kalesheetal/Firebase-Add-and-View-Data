package com.sk.firebase_add_view_data;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<UserInfo> userInfoList;
    UserAdapter userAdapter;

    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view);

        recyclerView = findViewById(R.id.recyclerview);
        userInfoList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewActivity.this));


        database = FirebaseDatabase.getInstance().getReference("User");

        getUsers();

    }

    private void getUsers() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);

                    userInfoList.add(userInfo);
                }

                userAdapter = new UserAdapter(ViewActivity.this,userInfoList);
                recyclerView.setAdapter(userAdapter);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}