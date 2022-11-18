package com.example.myapplication;

import  androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText, log, password;
    private TextView textView;
    private RadioButton rb1, rb2;
    private RadioGroup radioGroup;
    private Button next, logIn;
    private DatabaseReference database;
    private String MESSAGE_KEY = "Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        Intent i = new Intent(this, MainActivity2.class);
        next.setOnClickListener(view -> {
            String s = editText.getText().toString();
            i.putExtra("q", s);

            // Firebase
            FirebaseApp.initializeApp(this);
            database = FirebaseDatabase.getInstance().getReference(MESSAGE_KEY);
            String id = database.getKey();
            String name = editText.getText().toString();
            Message mess = new Message(id, name);
            database.push().setValue(mess);
        });
        logIn.setOnClickListener(view -> {
            String l = log.getText().toString();
            i.putExtra("w", l);
            startActivity(i);
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton1:
                        textView.setText("1");
                        break;
                    case R.id.radioButton2:
                        textView.setText("2");
                        break;
                }
            }
        });
    }

    private void init() {
        next = findViewById(R.id.button);
        radioGroup = findViewById(R.id.radiogroup);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView2);
        log = findViewById(R.id.log);
        password = findViewById(R.id.password);
        logIn = findViewById(R.id.buttonLogIn);

    }

    @Override
    public void onClick(View view) {
        // Next activity
        Intent i = new Intent(this, MainActivity2.class);
        String s = editText.getText().toString();
        i.putExtra("q", s);
        String l = log.getText().toString();
        i.putExtra("w", l);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
    }


}
