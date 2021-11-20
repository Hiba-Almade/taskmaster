package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TaskDataBase taskDataBase=TaskDataBase.getInstance(this);
        TaskDao taskDao =taskDataBase.taskDao();

        EditText title=findViewById(R.id.titleinput);
        EditText desc=findViewById(R.id.descinput);
        Button btn= findViewById(R.id.taskbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"submitted!",Toast.LENGTH_LONG).show();
                Task task= new Task(title.getText().toString(),desc.getText().toString(),"NEW");
                taskDao.insert(task);

                Intent toHome= new Intent(addTask.this,MainActivity.class);
                startActivity(toHome);
            }
        });
    }
}