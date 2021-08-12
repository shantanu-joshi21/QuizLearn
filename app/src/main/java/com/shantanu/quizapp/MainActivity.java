package com.shantanu.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView taketest,studymaterial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("QuizLearn App");
        taketest=findViewById(R.id.taketest);
        taketest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),chooseSubject.class);
                startActivity(intent);
            }
        });
        studymaterial=findViewById(R.id.studymaterial);
        studymaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),StudyMaterial.class);
                startActivity(intent);
            }
        });
    }
}