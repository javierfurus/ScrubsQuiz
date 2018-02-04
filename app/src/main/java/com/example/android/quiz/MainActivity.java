package com.example.android.quiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    RadioButton radioButton5;
    RadioButton radioButton6;
    RadioButton radioButton7;
    RadioButton radioButton8;
    RadioButton radioButton9;
    RadioButton radioButton10;
    RadioButton radioButton11;
    RadioGroup rgQ1;
    RadioGroup rgQ2;
    RadioGroup rgQ3;
    RadioGroup rgQ4;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    EditText editTextforAnswer;
    EditText nameField;
    String correctAnswer1EditText;
    String correctAnswer2EditText;
    String correctAnswer3EditText;
    String correctAnswer4EditText;
    TextView scoreSummary;
    Button submitButton;
    Boolean scoreisSubmitted;
    Button resetButton;
    Button shareButton;
    int finalScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correctAnswer1EditText="abc";
        correctAnswer2EditText="american broadcasting company";
        correctAnswer3EditText="american broadcasting company studios";
        correctAnswer4EditText="abc studios";
        finalScore=0;
        scoreSummary = (TextView) findViewById(R.id.summary);
        radioButton1 = (RadioButton) findViewById(R.id.radiobutton1);
        radioButton2 = (RadioButton) findViewById(R.id.radiobutton2);
        radioButton3 = (RadioButton) findViewById(R.id.radiobutton3);
        radioButton4 = (RadioButton) findViewById(R.id.radiobutton4);
        radioButton5 = (RadioButton) findViewById(R.id.radiobutton5);
        radioButton6 = (RadioButton) findViewById(R.id.radiobutton6);
        radioButton7 = (RadioButton) findViewById(R.id.radiobutton7);
        radioButton8 = (RadioButton) findViewById(R.id.radiobutton8);
        radioButton9 = (RadioButton) findViewById(R.id.radiobutton9);
        radioButton10 = (RadioButton) findViewById(R.id.radiobutton10);
        radioButton11 = (RadioButton) findViewById(R.id.radiobutton11);
        rgQ1 = (RadioGroup) findViewById(R.id.rQ1);
        rgQ2 = (RadioGroup) findViewById(R.id.rQ2);
        rgQ3 = (RadioGroup) findViewById(R.id.rQ3);
        rgQ4 = (RadioGroup) findViewById(R.id.rQ4);
        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkbox4);
        nameField = (EditText) findViewById(R.id.namefield);
        editTextforAnswer = (EditText) findViewById(R.id.itisABC);
        submitButton = (Button) findViewById(R.id.submitButton);
        resetButton = (Button) findViewById(R.id.resetButton);
        shareButton = (Button) findViewById(R.id.shareButton);
        scoreisSubmitted = false;

    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void calculateScore (View view)
    {
        String nameGiven=nameField.getText().toString();
        //calculates if radio buttons have been pressed properly.
        if (radioButton1.isChecked()){
            finalScore += 1;
        }
        if (radioButton5.isChecked()) {
            finalScore += 1;
        }
        if (radioButton9.isChecked()) {
            finalScore += 2;
        }
        if (radioButton10.isChecked()){
            finalScore +=1;
        }

//       To calculate the questions in checkboxes.
            if (checkBox1.isChecked()){
            finalScore+=2;
        }
            if (checkBox2.isChecked()){
            finalScore+=2;
        }
            if (checkBox3.isChecked()){
            finalScore+=2;
        }
        if (checkBox4.isChecked()){
                finalScore+=2;
        }
//checks if EditText has been filled out properly
        String editTextforAnswerConvertedInput = editTextforAnswer.getText().toString();
        editTextforAnswerConvertedInput= editTextforAnswerConvertedInput.toLowerCase();

        if (editTextforAnswerConvertedInput.equals(correctAnswer1EditText)
                || editTextforAnswerConvertedInput.equals(correctAnswer2EditText)
                || editTextforAnswerConvertedInput.equals(correctAnswer3EditText)
                || editTextforAnswerConvertedInput.equals(correctAnswer4EditText) )
        {
            finalScore+=4;
        }

        displayScore(finalScore, nameGiven);
        scoreisSubmitted=true;
        submitButton.setVisibility(View.INVISIBLE);
        shareButton.setVisibility(View.VISIBLE);
        resetButton.setVisibility(View.VISIBLE);

    }


    public void displayScore (int score, String givenName) {
        String summaryText = getString(R.string.dear) + givenName + "!";
        summaryText += getString(R.string.score_of_player)+ score + getString(R.string.outof);
        summaryText += getString(R.string.diagnosis);
        if (finalScore >= 9) {
            summaryText += getString(R.string.diagnosis_healthy);
            summaryText += getString(R.string.healthy_report);
        }
        if (finalScore < 9)
        {
            summaryText += getString(R.string.diagnosis_sick);
            summaryText += getString(R.string.sick_report);
        }
        summaryText += getString(R.string.congrats);
        scoreSummary.setText(summaryText);
    }

    public void resetScore (View view) {
        rgQ1.clearCheck();
        rgQ2.clearCheck();
        rgQ3.clearCheck();
        rgQ4.clearCheck();
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        editTextforAnswer.setText("");
        scoreSummary.setText(getString(R.string.your_score_will_appear_here_after_you_have_hit_the_quot_submit_quot_button));
        finalScore=0;
        scoreisSubmitted=false;
        submitButton.setVisibility(View.VISIBLE);
        shareButton.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.INVISIBLE);

    }
    public void submitScore (View view) {
        String nameGiven=nameField.getText().toString();
        Intent mailOrder = new Intent(Intent.ACTION_SEND);
        mailOrder.setData(Uri.parse("mailto:")); // only email apps should handle this
        mailOrder.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.submit_subject) + nameGiven);
        mailOrder.putExtra(Intent.EXTRA_TEXT,scoreSummary.getText().toString());
        if (mailOrder.resolveActivity(getPackageManager()) == null) { //Called when the user has no Activity to launch "mailto:"
            mailOrder.setType("message/rfc822");
            startActivity(mailOrder);
        }
        else{
            startActivity(mailOrder);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score", finalScore);
        outState.putBoolean("isSubmitted", scoreisSubmitted);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String nameGiven=nameField.getText().toString();
        scoreisSubmitted=savedInstanceState.getBoolean("isSubmitted");
        finalScore = savedInstanceState.getInt("score");
        if (scoreisSubmitted){
            displayScore(finalScore, nameGiven);
            submitButton.setVisibility(View.INVISIBLE);
            shareButton.setVisibility(View.VISIBLE);
            resetButton.setVisibility(View.VISIBLE);
        }

    }
}
