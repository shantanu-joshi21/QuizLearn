package com.shantanu.quizapp;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class AppUtility {
    Context context;
    String colour_Voilet = "#8E44AD";
    String colour_Black = "#000000";
    String colour_White = "#FFFFFF";
    String colour_Red = "#FF0000";
    String colour_Lime = "#00FF00";
    String colour_Blue = "#0000FF";
    String colour_Yellow = "#FFFF00";
    String colour_Cyan = "#00FFFF";
    String colour_Magenta = "#FF00FF";
    String colour_Silver = "#C0C0C0";
    String colour_Gray = "#808080";
    String colour_Maroon = "#800000";
    String colour_Olive = "#808000";
    String colour_Green = "#008000";
    String colour_Purple = "#800080";
    String colour_Teal = "#008080";
    String colour_Navy = "#000080";

    TextView addObject(LinearLayout ll,String typeOfObject,String text){
        return addObject(ll,typeOfObject,text,colour_White,colour_Black,false);
    }
    TextView addObject(LinearLayout ll,String typeOfObject, String text, String backGroundColurName, String textColourName,boolean wantBold){
        return addObject(ll,typeOfObject,text,backGroundColurName,textColourName,wantBold,false);
    }
    TextView addObject(LinearLayout ll,String typeOfObject, String text, String backGroundColurName, String textColourName,boolean wantBold,boolean wantCentre){
        TextView o;
        if(typeOfObject=="b") {
            o = new Button(ll.getContext());
        }else if(typeOfObject=="e"){
            o = new EditText(ll.getContext());
        }else{
            o = new TextView(ll.getContext());
        }
        if(wantBold) {
            o.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        LinearLayout.LayoutParams rel_obj = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
        //LinearLayout.LayoutParams rel_obj = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
        /*if(wantCentre){
            o.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);;
        }*/
        rel_obj.setMargins(20, 10, 0, 10);
        o.setLayoutParams(rel_obj);
        o.setText(text);
        o.setBackgroundColor(Color.parseColor(backGroundColurName));
        o.setTextColor(Color.parseColor(textColourName));
        //o.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        ll.addView(o);
        return  o;
    }
    void showToast(String toastText){
        Toast.makeText(context,toastText,Toast.LENGTH_SHORT).show();
    }

    String[][] fileRead(String fileName){
        File file=new File(fileName);
        int lineCount=0;
        try {
            //BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String st;
            while((st=br.readLine())!=null){
                //System.out.println(st);
                lineCount++;
            }
        }catch (Exception e){
            System.out.println("not possible");
        }
        //Toast.makeText(context.getApplicationContext(), lineCount+"",Toast.LENGTH_LONG).show();
        String[][]allData=new String[lineCount][];
        int lineNumber=0;
        try {
            //BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String st;
            while((st=br.readLine())!=null){
                Log.d("checking",st);
                allData[lineNumber]=st.split("#");
                lineNumber++;
            }
        }catch (Exception e){
            System.out.println("not possible");
        }
        return  allData;
    }

    void x_print_2d_array(String[][]s){
        for(String[] oned:s){
            String a="";
            for(String element:oned){
                //System.out.print(element+" ");
                //Log.d("printing",element);
                a += element+"-";
            }
            System.out.println(a);
            //Log.d("pppp",a);
        }
    }

    void print_2d_array(Object[][]s){
        for(Object[] oned:s){
            for(Object element:oned){
                System.out.print(element+" ");
                //Log.d("printing",element.toString());
            }
        }
    }

    public Integer[] giveRandomNumbers(Integer []a,int questionsRequired){
        Integer []ans=new Integer[questionsRequired];
        int total=a.length;
        if(questionsRequired>total){
            return ans;
        }
        for(int i=0;i<questionsRequired;i++){
            while(true){
                boolean isNew=true;
                ans[i]=a[(int)(Math.random()*total)];
                for(int j=i-1;j>=0;j--){
                    if(ans[i]==ans[j]){
                        isNew=false;
                        break;
                    }
                }
                if(isNew){
                    break;
                }
            }
        }
        return  ans;
    }

    String replace$$(String inputString){
        String ans="";
        ans=inputString.replace("$$","\n");
        return  ans;
    }

    void addBlankLines(LinearLayout ll,int x){
        for(int i=0;i<x;i++){
            addObject(ll,"t","",colour_White,colour_White,false);
        }
    }
}