package com.example.myapplication;

import static java.lang.Integer.*;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextWatcher;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.time.Duration;

public class MainActivity extends AppCompatActivity {

    EditText text1;
    EditText text2;
    EditText text3;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        text2 = findViewById(R.id.surname);
        text2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && text2.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"input surname",Toast.LENGTH_LONG).show();
                    text2.setError("input surname");
                }
            }
        });
        text1 = findViewById(R.id.name);
        text1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && text1.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"input name",Toast.LENGTH_LONG).show();
                    text1.setError("input name");
                }
            }
        });
        text3 = findViewById(R.id.grades);
        text3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    if (!hasFocus && text3.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "input numbers", Toast.LENGTH_LONG).show();
                        text3.setError("input numbers");
                    }
                    else if (!hasFocus && (Integer.parseInt(text3.getText().toString()) > 15 || Integer.parseInt(text3.getText().toString()) < 5)) {
                            Toast.makeText(MainActivity.this,"enter numbers between 5 and 15",Toast.LENGTH_LONG).show();
                            text3.setError("enter numbers between 5 and 15");
                    }
                }catch (NumberFormatException e){
                    text3.setError("input numbers only");
                }
            }
        });
        button1 = (Button)findViewById(R.id.button);
        button1.setVisibility(View.GONE);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFieldsForEmptyValues();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        text1.addTextChangedListener(textWatcher);
        text2.addTextChangedListener(textWatcher);
        text3.addTextChangedListener(textWatcher);
    }
    private void checkFieldsForEmptyValues() {

        if (!text1.getText().toString().isEmpty() && !text2.getText().toString().trim().isEmpty() && !text3.getText().toString().isEmpty()) {
            if((Integer.parseInt(text3.getText().toString()) <= 15 && Integer.parseInt(text3.getText().toString()) >= 5)) {
                button1.setVisibility(View.VISIBLE);
            }
        } else {
            button1.setVisibility(View.GONE);
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
        Toast.makeText(MainActivity.this,"Clicked", Toast.LENGTH_LONG).show();
    }


}