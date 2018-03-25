package com.brekhov1.contactshw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    public static Activity fa;
    MainActivity() {
        fa = this;
    }

    SharedPreferences sharedPreferences;
    boolean SAVED_BOOLEAN = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fab = findViewById(R.id.fab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ContactHelper contactHelper = new ContactHelper(getApplicationContext());
        recyclerView.setAdapter(new RVAdapter(contactHelper.getAll(), this));

        sharedPreferences = getPreferences(MODE_PRIVATE);
        if (sharedPreferences.getBoolean(String.valueOf(SAVED_BOOLEAN), true)) {
            ContactHelper contactHelper1 = new ContactHelper(getApplicationContext());
            contactHelper1.insert("Danila Brekhov", "+79292159929", "17.03.2002");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(String.valueOf(SAVED_BOOLEAN),false);
            editor.commit();
            recreate();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
