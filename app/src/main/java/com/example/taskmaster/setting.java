package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button settingButton= findViewById(R.id.saveButton);
        EditText userNameInput=findViewById(R.id.nameInput);
        RadioButton RadioButtonFirstTeam = findViewById(R.id.radioButton1);
        RadioButton RadioButtonSecondTeam = findViewById(R.id.radioButton2);
        RadioButton RadioButtonThirdTeam = findViewById(R.id.radioButton3);
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

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


                String userName = userNameInput.getText().toString();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(setting.this);
                SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
                sharedPrefEditor.putString("userName",userName);
                sharedPrefEditor.putString("teamID",selectedTeam.getId());
                sharedPrefEditor.putString("teamName",selectedTeam.getName());

                sharedPrefEditor.apply();
                Intent mainIntent = new Intent(setting.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}