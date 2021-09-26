package com.sk.firebase_add_view_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText name,email,mobile,address;

    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        address = findViewById(R.id.address);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
    }

    public void AddData(View view) {
        AddDataToFirebase(name.getText().toString(),
                email.getText().toString(),mobile.getText().toString(),address.getText().toString());
    }

    private void AddDataToFirebase(String name,String email,String mobile, String address)
    {
        UserInfo userInfo = new UserInfo(name,email,mobile,address);
        databaseReference.push().setValue(userInfo);

        Toast.makeText(MainActivity.this,"data added successfully!",Toast.LENGTH_LONG).show();
    }

    public void ViewData(View view) {
        startActivity(new Intent(MainActivity.this,ViewActivity.class));
    }
}