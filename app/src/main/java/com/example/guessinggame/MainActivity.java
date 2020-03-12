package com.example.guessinggame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText txtGuess;
    private Button btnGuess;
    private TextView lblOutput;
    private int theNumber;
//    private int rRed = Color.rgb(200,0,0);
//    private int bBlack = Color.rgb(0,0,0);

    public void newGame(){
        txtGuess.setText("");
        lblOutput.setText(R.string.enter_a_number_then_click_guess);
        theNumber = (int)(Math.random() * 100 + 1);
        settxtGuessHint();
    }

    public void checkGuess(){
        if (txtGuess.getText() == null || txtGuess.getText().length() == 0){
            setLblOutput("Вы не ввели число!", "Red");
            return;
        }
        String guessText = txtGuess.getText().toString();
        String message = "";
        int guess = Integer.parseInt(guessText);
        if (guess < theNumber)
            message = guess + " is too low. Try again.";
        else if (guess > theNumber)
            message = guess + " is too high. Try again.";
        else
            message = guess + " is correct. You win!";

        setLblOutput(message, "Black");
        txtGuess.requestFocus();
        txtGuess.selectAll();
    }

    public void setLblOutput(String message, String color) {
        lblOutput.setText(message);
        if(color.equals("Red")) {
            lblOutput.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.Red));
        }else{
            lblOutput.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.Black));
        }
    }

    public void settxtGuessHint() {
        txtGuess.setHint(theNumber);
    }

    protected void initListeners(){
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtGuess = (EditText) findViewById(R.id.txtGuess);
        btnGuess = (Button) findViewById(R.id.btnGuess);
        lblOutput = (TextView) findViewById(R.id.lblOutput);

        newGame();
        initListeners();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
