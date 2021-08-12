package com.shantanu.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class chooseSubject extends AppCompatActivity {
    AppUtility appUtility;
    LinearLayout linearLayout;
    String selectedSubjectTopic;
    Button submitTest;
    final int totalQuestionsInQuiz=10;
    TextView[][]relatedTextViews;
    String [][]allQuestions;
    Integer[]userChoice=new Integer[totalQuestionsInQuiz];
    Integer[]randomizedQuestions=new Integer[totalQuestionsInQuiz];
    HashSet<Pair<String,String>>hashSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subject);
        hashSet = new HashSet<>();
        appUtility=new AppUtility();
        appUtility.context=this;
        submitTest=findViewById(R.id.submittest);
        relatedTextViews=new TextView[totalQuestionsInQuiz][4];
        //appUtility.showToast("Subjects");
        linearLayout=findViewById(R.id.subject);
        allQuestions=appUtility.fileRead("sample_qb.txt");
        mainMap();
    }

    void mainMap(){
        linearLayout.removeAllViews();
        submitTest.setVisibility(View.INVISIBLE);
        selectSubject();
//        Integer[]allValid=buildFilteredArray(selectedSubjectTopic);
//        buildRandomizedArray(allValid);
//        displayQuestions();
//        addSubmitButton();
    }

    void selectSubject(){
        this.setTitle("SUBJETCS");
        for(int i=1;i<allQuestions.length;i++){
            hashSet.add(new Pair<>(allQuestions[i][0],allQuestions[i][2]));
        }
        for(Pair<String,String> pr:hashSet){
            TextView t=appUtility.addObject(linearLayout,"b",pr.first+" - "+pr.second,appUtility.colour_Cyan,appUtility.colour_Black,true,true);
            t.setWidth(960);
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedSubjectTopic=t.getText().toString();
                    chooseSubject.this.setTitle(selectedSubjectTopic);
                    Integer[]allValid=buildFilteredArray(selectedSubjectTopic);
                    buildRandomizedArray(allValid);
                    displayQuestions();
                }
            });
        }
    }

    Integer[] buildFilteredArray(String subject) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i=0;i<allQuestions.length;i++){
            String check=allQuestions[i][0]+" - "+allQuestions[i][2];
            if(check.equals(subject)){
                arrayList.add(i);
            }
        }

        Integer[]allValid=new Integer[arrayList.size()];
        int index=0;
        for(Integer number:arrayList){
            allValid[index]=number;
            index++;
        }

        return allValid;
    }

    void buildRandomizedArray(Integer[]allValid){
        randomizedQuestions=appUtility.giveRandomNumbers(allValid,totalQuestionsInQuiz);
        for(int i=0;i<randomizedQuestions.length;i++){
            userChoice[i]=-1;
        }
    }

    void displayQuestions() {
        submitTest.setVisibility(View.VISIBLE);
        submitTest.setText("SUBMIT TEST");
        submitTest.setBackgroundColor(Color.parseColor(appUtility.colour_Voilet));
        linearLayout.removeAllViews();
        Log.d("tracker","2.1");
        for(int i=0;i<randomizedQuestions.length;i++){
            Log.d("tracker","2.2");
            //String qno=randomizedQuestions[i].toString();
            appUtility.addObject(linearLayout,"t",(i+1)+". "+allQuestions[randomizedQuestions[i]][1], appUtility.colour_White, appUtility.colour_Black, true);
            Log.d("tracker","2.3");
            for(int j=3;j<=6;j++){
                Log.d("tracker","2.4");
                char ch= (char) (j-3+'A');
                String s=ch+". "+allQuestions[randomizedQuestions[i]][j];
                Log.d("tracker","2.5");
                TextView optionSelected=appUtility.addObject(linearLayout,"t",s, appUtility.colour_White, appUtility.colour_Blue,false );
                Log.d("tracker","2.6");
                relatedTextViews[i][j-3]=optionSelected;
                optionSelected.setTag(i);
                optionSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userChoice[(int) optionSelected.getTag()]=optionSelected.getText().charAt(0)-'A';
                        int tagOfCurrentQuestion= (int) optionSelected.getTag();
                        for(int j=0;j<4;j++){
                            relatedTextViews[tagOfCurrentQuestion][j].setBackgroundColor(Color.parseColor(appUtility.colour_White));
                        }
                        optionSelected.setBackgroundColor(Color.parseColor(appUtility.colour_Yellow));
                    }
                });
            }
            appUtility.addObject(linearLayout,"t","==================");
        }
        addSubmitButton();
    }

    void addSubmitButton(){
        submitTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score=giveScore();
                appUtility.addBlankLines(linearLayout,6);
                //TextView t=appUtility.addObject(linearLayout,"b","   Your score: "+score+"   ", appUtility.colour_Teal, appUtility.colour_White, false);
                /*t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainMap();
                    }
                });*/
                //((ViewGroup)submitTest.getParent()).removeView(submitTest);
                if(submitTest.getText().toString().equals("SUBMIT TEST")) {
                    submitTest.setText("   Your score: " + score + "   ");
                    submitTest.setBackgroundColor(Color.parseColor(appUtility.colour_Teal));
                }else{
                    mainMap();
                }
            }
        });
    }

    int giveScore(){
        int score=0;
        for(int i=0;i<randomizedQuestions.length;i++){
            int correctOption=allQuestions[randomizedQuestions[i]][7].charAt(0)-'A';
            if(correctOption==userChoice[i]){
                score++;
            }
            TextView correctAnswer=relatedTextViews[i][correctOption];
            correctAnswer.setBackgroundColor(Color.parseColor(appUtility.colour_Lime));
        }
        return score;
    }

}