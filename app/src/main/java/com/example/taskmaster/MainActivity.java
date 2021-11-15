package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Button task1button=findViewById(R.id.labButton);
        task1button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task1=task1button.getText().toString();
                Intent taskDetailsIntent = new Intent(MainActivity.this, taskDetails.class);
                taskDetailsIntent.putExtra("title",task1);
                startActivity(taskDetailsIntent);
            }
        });
        Button task2button=findViewById(R.id.cookingButton);
        task2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task2=task2button.getText().toString();
                Intent taskDetailsIntent = new Intent(MainActivity.this, taskDetails.class);
                taskDetailsIntent.putExtra("title",task2);
                startActivity(taskDetailsIntent);
            }
        });

        Button task3button=findViewById(R.id.cleanButton);
        task3button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task3=task3button.getText().toString();
                Intent taskDetailsIntent = new Intent(MainActivity.this, taskDetails.class);
                taskDetailsIntent.putExtra("title",task3);
                startActivity(taskDetailsIntent);
            }
        });
    }
}