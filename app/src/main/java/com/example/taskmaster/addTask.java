package com.example.taskmaster;

import androidx.annotation.NonNull;
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
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class addTask extends AppCompatActivity {
    Uri dataUrl;
    String fileName = null;
    String fileKey=null;
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

        //intent
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        }

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
                        .fileKey(fileName)
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

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data) {
       Log.i("file", "Successfully uploaded: " + "00000000000");
//        assert data != null;

        File file = new File(data.getData().getPath());
        dataUrl = data.getData();
         fileName = file.getName();
        super.onActivityResult(requestCode, resultCode, data);
        try {
            InputStream exampleInputStream = getContentResolver().openInputStream(dataUrl);

            Amplify.Storage.uploadInputStream(
                    fileName,
                    exampleInputStream,
                    result -> Log.i("file", "Successfully uploaded: " + result.getKey()),
                    storageFailure -> Log.e("file", "Upload failed", storageFailure)
            );
            Toast.makeText(getApplication(),"File added!",Toast.LENGTH_LONG).show();

        }  catch (FileNotFoundException error) {
            Log.e("file", "Could not find file to open for input stream.", error);
        }

    }

    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        File file = new File(imageUri.getPath());
        fileName=file.getName();
        if (imageUri != null) {

            try {
                InputStream exampleInputStream = getContentResolver().openInputStream(imageUri);
                Amplify.Storage.uploadInputStream(
                        fileName,
                        exampleInputStream,
                        result -> Log.i("TaskMaster", "Successfully uploaded: " + result.getKey()),
                        storageFailure -> Log.e("TaskMaster", "Upload failed", storageFailure)
                );
            } catch (FileNotFoundException error) {
                Log.e("TaskMaster", "Could not find file to open for input stream.", error);
            }
        }
        Toast.makeText(getApplicationContext(),imageUri.getPath(),Toast.LENGTH_SHORT).show();
    }

}