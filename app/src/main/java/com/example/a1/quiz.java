package com.example.a1;

import android.os.Bundle;
import android.content.Context;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
public class quiz extends AppCompatActivity {
    public static final String LEVEL = "LEVEL";

    public static  final  String LAST_ANS = "LAST_ANS";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private TextView the_question_text;
    private TextView level;
    private EditText the_answer_value;
    private int level_value = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        the_answer_value = findViewById(R.id.answer_value);
        the_question_text  = findViewById(R.id.q);

        setupSharedPrefs();
        level = findViewById(R.id.level);
        checkPrefs();
        question[] questions = new question[10];
        questions[0] = new question("2+1 =","3");
        questions[1] = new question("2+2 =","4");
        questions[2] = new question("9-1 =","8");
        questions[3] = new question("3-2 =","1");
        questions[4] = new question("7+0 =","7");
        questions[5] = new question("3 x 2 =","6");
        questions[6] = new question("9 x 1 =","9");
        questions[7] = new question("6 / 2 =","3");
        questions[8] = new question("5 / 5  =","1");
        questions[9] = new question("(5 x 2) - 1 =","9");







        the_question_text.setText(questions[(level_value%10)].getThe_question()+" ?");
        checkInstance(savedInstanceState);

        Button answer_btn = findViewById(R.id.answer_btn);
        answer_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String an = the_answer_value.getText().toString().trim();
                the_answer_value.setText("");

                if(!isInteger(an)){
                    level.setText("Level : "+(level_value+1)+"\n Wrong Answer");
                    return;

                }
                if(Integer.parseInt(an) == Integer.parseInt(questions[(level_value%10)].getThe_answer())){
                    level_value+=1;
                    level.setText("Level : "+(level_value+1));
                    the_question_text.setText(questions[(level_value%10)].getThe_question()+" ?");
                    editor.putInt(LEVEL, level_value);
                    editor.commit();
                    Context context = getApplicationContext();
                    CharSequence text = "Good job";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                }else {
                    level.setText("Level : "+(level_value+1)+"\n Wrong Answer");

                }
            }
        });
    }
    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @Override
    public void onDestroy() {
        String an = the_answer_value.getText().toString().trim();
        if(!an.equals("")){
            Context context = getApplicationContext();
            CharSequence text = "Your answer will be saved";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            editor.putString(LAST_ANS, an);
            editor.commit();
        }else{
            editor.putString(LAST_ANS, "");
            editor.commit();
        }


        super.onDestroy();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        String msg = level.getText().toString();
        outState.putString("MSG", msg);


    }
    private void checkPrefs() {
            level_value = prefs.getInt(LEVEL, 0);
            level.setText("Level: "+(level_value+1));
            String last_ans = prefs.getString(LAST_ANS, "");

            the_answer_value.setText(last_ans);
    }
    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
    private void checkInstance(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            String msg = savedInstanceState.getString("MSG");
            level.setText(msg);
        }
    }
}