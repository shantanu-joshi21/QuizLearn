package com.shantanu.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.HashSet;

public class StudyMaterial extends AppCompatActivity {
    AppUtility appUtility;
    LinearLayout linearLayout;
    String [][]studyNotes;
    HashSet<String>hashSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_material);

        hashSet=new HashSet<>();
        appUtility=new AppUtility();
        appUtility.context=getApplicationContext();


        linearLayout=findViewById(R.id.linearlayout);
        studyNotes=appUtility.fileRead("subject_notes.txt");

        for(int i=1;i<studyNotes.length;i++){
            hashSet.add(studyNotes[i][0]);
        }
        populateSubjects();
    }

    void populateSubjects(){
        this.setTitle("SUBJECTS");
        linearLayout.removeAllViews();
        for(String element:hashSet){
            TextView t=appUtility.addObject(linearLayout,"b",element, appUtility.colour_Teal, appUtility.colour_White, true);
            t.setWidth(960);
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    populateTitles(t.getText().toString());
                }
            });
        }
    }

    void populateTitles(String subject){
        this.setTitle(subject);
        linearLayout.removeAllViews();
        for(int i=1;i<studyNotes.length;i++){
            if(studyNotes[i][0].equals(subject)){
                TextView t=appUtility.addObject(linearLayout,"b",studyNotes[i][1], appUtility.colour_Teal, appUtility.colour_Yellow, false);
                t.setWidth(960);
                t.setTag(i);
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        populateDetails((Integer) t.getTag());
                    }
                });
            }
        }
        leaveBlankLines(4);

        TextView back=appUtility.addObject(linearLayout,"b","Back", appUtility.colour_Purple, appUtility.colour_White, true);
        back.setWidth(400);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateSubjects();
            }
        });
    }

    void populateDetails(int tagg){
        this.setTitle(studyNotes[tagg][1]);
        linearLayout.removeAllViews();
        String textToBeFIlled=appUtility.replace$$(studyNotes[tagg][2]);
        appUtility.addObject(linearLayout,"t",textToBeFIlled);

        leaveBlankLines(2);

        TextView linkToNotes=appUtility.addObject(linearLayout,"b","Click to notes", appUtility.colour_Green, appUtility.colour_White, true);
        linkToNotes.setWidth(600);
        linkToNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse(studyNotes[tagg][3]));
                startActivity(implicit);
            }
        });

        leaveBlankLines(4);

        TextView back=appUtility.addObject(linearLayout,"b","Back", appUtility.colour_Purple, appUtility.colour_White, true);
        back.setWidth(400);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateTitles(studyNotes[tagg][0]);
            }
        });
    }

    void leaveBlankLines(int x){
        for(int i=0;i<x;i++) {
            TextView blankSpaces = appUtility.addObject(linearLayout, "t", " ");
        }
    }
}