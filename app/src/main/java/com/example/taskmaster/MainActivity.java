package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        Button settingbutton=findViewById(R.id.settingButton);
        settingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingIntent = new Intent(MainActivity.this, setting.class);
                startActivity(settingIntent);
            }
        });

        TextView textView=findViewById(R.id.textView);
        TextView textView1 = findViewById(R.id.teamLabel);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPreferences.getString("userName","User");
        String teamName = sharedPreferences.getString("teamName", "no team");
        textView.setText("Hello "+ userName);
        textView1.setText(teamName);


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

//
//        TaskDataBase taskDataBase=TaskDataBase.getInstance(this);
//        TaskDao taskDao=taskDataBase.taskDao();
//        List<Task> tasks=taskDao.getAll();
////        tasks.add(new Task("Solve the lab ","solve lab 28","complete"));
////        tasks.add(new Task("cooking","cook rice and meet","assigned"));
////        tasks.add(new Task("clean","clean the home","new"));
////        tasks.add(new Task("shopping","buy something","new"));
//        RecyclerView recyclerView=findViewById(R.id.recycleTask);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new TaskAdapter(tasks));



//        TaskDataBase taskDataBase = TaskDataBase.getInstance(this);
//        TaskDao taskDao = taskDataBase.taskDao();
//        List<Task> tasks = taskDao.getAll();
//
//        //RecyclerView
//        RecyclerView recyclerView = findViewById(R.id.recycleTask);
//        TaskAdapter taskAdapter = new TaskAdapter(tasks, this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.canScrollVertically();
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(taskAdapter);

//------------------------create team --------------------------//
//        Team one = Team.builder()
//                .name("first team")
//                .build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(one),
//                response -> Log.i("Myteam", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("Myteam", "Create failed", error)
//        );
//        Team tow = Team.builder()
//                .name("Second team")
//                .build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(tow),
//                response -> Log.i("Myteam", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("Myteam", "Create failed", error)
//        );
//        Team three = Team.builder()
//                .name("third team")
//                .build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(three),
//                response -> Log.i("Myteam", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("Myteam", "Create failed", error)
//        );



    }

    @Override
    protected  void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.recycleTask);

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {

            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        List<Task> allTask = new ArrayList<>();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String teamId = sharedPreferences.getString("teamID","null");

        Amplify.API.query(
                ModelQuery.list(Task.class,Task.TEAM_ID.contains(teamId)),
                response -> {
                    for (Task task : response.getData()) {
                        allTask.add(task);

                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("TaskMaster", error.toString(), error)
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new TaskAdapter(allTask));
    }
}