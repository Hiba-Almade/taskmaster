package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

public class addTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            Amplify.addPlugin(new AWSApiPlugin());
        } catch (AmplifyException e) {
            e.printStackTrace();
        }

        EditText title=findViewById(R.id.titleinput);
        EditText desc=findViewById(R.id.descinput);
        Button btn= findViewById(R.id.taskbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"submitted!",Toast.LENGTH_LONG).show();

                // Room DB //
//                TaskDataBase taskDataBase=TaskDataBase.getInstance(this);
//                TaskDao taskDao =taskDataBase.taskDao();
//                Task task= new Task(title.getText().toString(),desc.getText().toString(),"NEW");
//                taskDao.insert(task);

                // AWS DB //
                Task todo = Task.builder()
                        .title(title.getText().toString())
                        .body(desc.getText().toString())
                        .state("New")
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(todo),
                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );

                Intent toHome= new Intent(addTask.this,MainActivity.class);
                startActivity(toHome);
            }
        });
    }
}