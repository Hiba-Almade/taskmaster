package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView=findViewById(R.id.textView);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPreferences.getString("userName","User");
        textView.setText(userName+" Tasks");

        Button addTask=findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add=new Intent(MainActivity.this,addTask.class);
                startActivity(add);
            }
        });

        Button allTask=findViewById(R.id.allTasks);
        allTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent all=new Intent(MainActivity.this,allTasks.class);
                startActivity(all);
            }
        });

//        Button task1button=findViewById(R.id.labButton);
//        task1button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String task1=task1button.getText().toString();
//                Intent taskDetailsIntent = new Intent(MainActivity.this, taskDetails.class);
//                taskDetailsIntent.putExtra("title",task1);
//                startActivity(taskDetailsIntent);
//            }
//        });
//        Button task2button=findViewById(R.id.cookingButton);
//        task2button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String task2=task2button.getText().toString();
//                Intent taskDetailsIntent = new Intent(MainActivity.this, taskDetails.class);
//                taskDetailsIntent.putExtra("title",task2);
//                startActivity(taskDetailsIntent);
//            }
//        });
//
//        Button task3button=findViewById(R.id.cleanButton);
//        task3button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String task3=task3button.getText().toString();
//                Intent taskDetailsIntent = new Intent(MainActivity.this, taskDetails.class);
//                taskDetailsIntent.putExtra("title",task3);
//                startActivity(taskDetailsIntent);
//            }
//        });

        Button settingbutton=findViewById(R.id.settingButton);
        settingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingIntent = new Intent(MainActivity.this, setting.class);
                startActivity(settingIntent);
            }
        });

        List<Task> tasks=new ArrayList<Task>();
        tasks.add(new Task("Solve the lab ","solve lab 28","complete"));
        tasks.add(new Task("cooking","cook rice and meet","assigned"));
        tasks.add(new Task("clean","clean the home","new"));
        tasks.add(new Task("shopping","buy something","new"));


        RecyclerView recyclerView=findViewById(R.id.recycleTask);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(tasks));



    }
}