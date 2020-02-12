package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    AutoCompleteTextView data;
    Button vendor, submit;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    List<Map<String,String>> items=null;
    ArrayAdapter<String> adapter;

    void initFirebaseItems()
    {
        database.getReference("ritik/items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                items.clear();
                adapter.clear();
                for(DataSnapshot item : dataSnapshot.getChildren())
                {
                    Map<String,String> itm = (Map<String,String>)item.getValue();
                    items.add(itm);
                    System.out.println(itm);
                    adapter.add(itm.get("name"));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById(R.id.data);
        amount= findViewById(R.id.amount);
        vendor = findViewById(R.id.vendor);
        submit=findViewById(R.id.submit);
        items = new LinkedList<>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, new LinkedList<String>());
        data.setThreshold(1);
        data.setAdapter(adapter);
        initFirebaseItems();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedSubmit();
            }
        });
        vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clcikedVendor();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void clcikedVendor() {
        Intent vndr = new Intent(getApplicationContext(),Vendors.class);
        startActivity(vndr);
    }

    private void clickedSubmit() {
    }
}
