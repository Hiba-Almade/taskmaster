package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.io.File;

public class taskDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);


//        TaskDataBase taskDataBase=TaskDataBase.getInstance(this);
//        TaskDao taskDao=taskDataBase.taskDao();
//        Intent fromHome=getIntent();
//        String id =fromHome.getIntExtra("id",0)+"";
//        Task task=taskDao.findTaskById(id);
//        String title = task.title;
//        String body=task.body;
//
//        TextView titleLabel = findViewById(R.id.titleLabel);
//        TextView bodyLabel=findViewById(R.id.descLabel);
//        titleLabel.setText(title);
//        bodyLabel.setText(body);

        String title =getIntent().getStringExtra("title");
        String body =getIntent().getStringExtra("body");
        String state =getIntent().getStringExtra("state");
        TextView titleLabel = findViewById(R.id.titleLabel);
        TextView bodyLabel=findViewById(R.id.descLabel);
        ImageView img=findViewById(R.id.imgg);
        TextView link = findViewById(R.id.linkk);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link.setVisibility(View.INVISIBLE);
        link.setLinkTextColor(Color.BLUE);
        img.setVisibility(View.INVISIBLE);
        titleLabel.setText(title);
        bodyLabel.setText(body);
        String file=getIntent().getStringExtra("file");
        Amplify.Storage.downloadFile(
                file,
                new File(getApplicationContext().getFilesDir() +file),
                result -> {
                    Log.i("download", "Successfully downloaded: " + result.getFile());
                    if(result.getFile().toString().contains("image")){
                        Log.i("download", "0000000000000" + result.getFile());

                        Bitmap bitmap = BitmapFactory.decodeFile(result.getFile().getPath());
                        img.setImageBitmap(bitmap);
                        img.setVisibility(View.VISIBLE);
                        link.setVisibility(View.INVISIBLE);
                    }else {
                        String linkToFile = "https://taskbucket183101-hibaalmade.s3.amazonaws.com/public/"+result.getFile().getPath();
                        link.setText(linkToFile);
                        link.setVisibility(View.VISIBLE);
                        img.setVisibility(View.INVISIBLE);
                    }
                },
                error -> Log.e("download",  "Download Failure", error)
        );

    }
}