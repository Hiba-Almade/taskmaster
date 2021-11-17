package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class taskDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);


        TaskDataBase taskDataBase=TaskDataBase.getInstance(this);
        TaskDao taskDao=taskDataBase.taskDao();
        Intent fromHome=getIntent();
        String id =fromHome.getIntExtra("id",0)+"";
        Task task=taskDao.findTaskById(id);
        String title = task.title;
        String body=task.body;

        TextView titleLabel = findViewById(R.id.titleLabel);
        TextView bodyLabel=findViewById(R.id.descLabel);
        titleLabel.setText(title);
        bodyLabel.setText(body);
    }
}