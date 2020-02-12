package com.example.user;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Vendors extends AppCompatActivity {

    ListView vendorList;
    ArrayAdapter<String> adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    List<Map<String,String>> vendors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors);
        vendorList = findViewById(R.id.recyclerView);
        vendors = new LinkedList<>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new LinkedList<String>());
        vendorList.setAdapter(adapter);
        initFirebaseVendors();
    }

    private void initFirebaseVendors() {
        database.getReference("ritik/vendors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vendors.clear();
                adapter.clear();
                for(DataSnapshot vendor : dataSnapshot.getChildren())
                {
                    Map<String,String> vndr = (Map<String,String>)vendor.getValue();
                    vendors.add(vndr);
                    adapter.add(vndr.get("name"));
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
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
