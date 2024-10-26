package com.example.hw5_m2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.BitSet;

public class MainActivity extends AppCompatActivity {

    TextView resultTV;
    int firstNumber, secondNumber;
    boolean isOperation;
    String operator;
    Button btnResult;
    Button btnEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTV = findViewById(R.id.textView);
        btnResult = findViewById(R.id.btn_result);
        btnEqual = findViewById(R.id.buttonEquals);

        btnResult.setVisibility(View.INVISIBLE);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = calculateOperation(firstNumber, secondNumber, operator);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("result", result);
                startActivity(intent);
            }
        });
    }

    public void numberClick(View view) {
        if (view instanceof MaterialButton) {
            String text = ((MaterialButton) view).getText().toString();
            if (isOperation) {
                resultTV.setText("");
            }
            resultTV.append(text);
        }
        isOperation = false;
    }

    public void operationClick(View view) {
        btnResult.setVisibility(View.INVISIBLE);
        if (view.getId() == R.id.buttonClear) {
            resultTV.setText("");
        } else if (view.getId() == R.id.buttonAdd) {
            firstNumber = Integer.valueOf(resultTV.getText().toString());
            operator = "+";
        } else if (view.getId() == R.id.buttonSubtract) {
            firstNumber = Integer.valueOf(resultTV.getText().toString());
            operator = "-";
        } else if (view.getId() == R.id.buttonMultiply) {
            firstNumber = Integer.valueOf(resultTV.getText().toString());
            operator = "*";
        } else if (view.getId() == R.id.buttonDivide) {
            firstNumber = Integer.valueOf(resultTV.getText().toString());
            operator = "/";
        } else if (view.getId() == R.id.buttonEquals) {
            btnResult.setVisibility(View.VISIBLE);
            secondNumber = Integer.valueOf(resultTV.getText().toString());
            resultTV.setText(calculateOperation(firstNumber, secondNumber, operator));
        }
        isOperation = true;
    }

    private String calculateOperation(int firstNumber, int secondNumber, String operator) {
        switch (operator) {
            case "+":
                return String.valueOf(firstNumber + secondNumber);
            case "-":
                return String.valueOf(firstNumber - secondNumber);
            case "*":
                return String.valueOf(firstNumber * secondNumber);
            case "/":
                if (secondNumber != 0) {
                    return String.valueOf(firstNumber / secondNumber);
                } else {
                    return "0";
                }
            default:
                return "";
        }
    }
}