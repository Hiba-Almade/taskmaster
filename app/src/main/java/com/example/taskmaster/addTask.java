package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class addTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText title=findViewById(R.id.titleinput);
        EditText desc=findViewById(R.id.descinput);
        RadioButton RadioButtonFirstTeam = findViewById(R.id.radioButton1);
        RadioButton RadioButtonSecondTeam = findViewById(R.id.radioButton2);
        RadioButton RadioButtonThirdTeam = findViewById(R.id.radioButton3);
        Button btn= findViewById(R.id.taskbtn);
        Button addFile=findViewById(R.id.addfilebutton);

        List<Team> allTeam = new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team team : response.getData()) {
                        Log.i("MyAmplifyApp", team.getName());
                        allTeam.add(team);
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        addFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFileFromDevice();
            }


        public void getFileFromDevice(){
            Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.setType("*/*");
            chooseFile = Intent.createChooser(chooseFile, "Select a File");
            startActivityForResult(chooseFile, 1234);
        }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"submitted!",Toast.LENGTH_LONG).show();

                //         Room DB      //
                //                TaskDataBase taskDataBase=TaskDataBase.getInstance(this);
                //                TaskDao taskDao =taskDataBase.taskDao();
                //                Task task= new Task(title.getText().toString(),desc.getText().toString(),"NEW");
                //                taskDao.insert(task);

                //       AWS DB        //
                String teamName = "";
                if (RadioButtonFirstTeam.isChecked()) {
                    teamName = RadioButtonFirstTeam.getText().toString();

                } else if (RadioButtonSecondTeam.isChecked()) {
                    teamName = RadioButtonSecondTeam.getText().toString();


                } else if (RadioButtonThirdTeam.isChecked()) {
                    teamName = RadioButtonThirdTeam.getText().toString();

                }
                Team selectedTeam = null;
                for (Team teams : allTeam) {
                    if (teams.getName().equals(teamName)) {
                      selectedTeam=teams;
                    }

                }

                Task todo = Task.builder()
                        .teamId(selectedTeam.getId())
                        .title(title.getText().toString())
                        .body(desc.getText().toString())
                        .state("New")
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(todo),
                        response -> Log.i("newTask", "Added Todo with id: " + response.getData()),
                        error -> Log.e("newTask", "Create failed", error)
                );


                Intent toHome= new Intent(addTask.this,MainActivity.class);
                startActivity(toHome);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234)
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();

                String filePath = data.getData().getPath();
                String file_extn = filePath.substring(filePath.lastIndexOf(".") + 1);

                Toast.makeText(getApplication(),"File uploaded",Toast.LENGTH_LONG).show();
            }
    }


}