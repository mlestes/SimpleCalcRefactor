package com.coolcats.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static com.coolcats.myapplication.MainActivity.Operand.DIVISION;
import static com.coolcats.myapplication.MainActivity.Operand.MINUS;
import static com.coolcats.myapplication.MainActivity.Operand.MODULUS;
import static com.coolcats.myapplication.MainActivity.Operand.MULTIPLY;
import static com.coolcats.myapplication.MainActivity.Operand.NONE;
import static com.coolcats.myapplication.MainActivity.Operand.PLUS;

public class MainActivity extends AppCompatActivity {

    enum Operand {
        NONE,
        PLUS,
        MINUS,
        MULTIPLY,
        MODULUS,
        DIVISION,
        EQUALS
    }
    private TextView calculatorView;
    private double currentValue = 0.0;
    private double storedValue = 0.0;
    private Operand operand = Operand.NONE;
    private boolean addDot = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorView = findViewById(R.id.output_textview);
    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.ac_button:
                calculatorView.setText("0");
                currentValue = 0.0;
                storedValue = 0;
                break;
            case R.id.zero_button:
                setNumber(0);
                break;
            case R.id.nine_button:
                setNumber(9);
                break;
            case R.id.eight_button:
                setNumber(8);
                break;
            case R.id.seven_button:
                setNumber(7);
                break;
            case R.id.six_button:
                setNumber(6);
                break;
            case R.id.five_button:
                setNumber(5);
                break;
            case R.id.four_button:
                setNumber(4);
                break;
            case R.id.three_button:
                setNumber(3);
                break;
            case R.id.two_button:
                setNumber(2);
                break;
            case R.id.one_button:
                setNumber(1);
                break;

            case R.id.multi_button:
                doCalculation(Operand.MULTIPLY);
                break;
            case R.id.div_button:
                doCalculation(Operand.DIVISION);
                break;
            case R.id.modulus_button:
                doCalculation(Operand.MODULUS);
                break;
            case R.id.sub_button:
                doCalculation(Operand.MINUS);
                break;
            case R.id.plus_button:
                doCalculation(Operand.PLUS);
                break;
            case R.id.equals_button:
                getResult();
                break;
            case R.id.period_button:
                String value = calculatorView.getText().toString();
                if(value.indexOf('.') == -1) addDot = true;
                break;

            case R.id.negate_button:
                currentValue *= -1;
                calculatorView.setText(currentValue+"");
                break;

        }
    }

    private void doCalculation(Operand operand) {
        getResult();
        storedValue = currentValue;
        currentValue = 0;
        this.operand = operand;
    }

    private void getResult(){

        double result = 0;

        switch (operand){
            case PLUS:
                result = storedValue + currentValue;
                break;
            case MINUS:
                result = storedValue - currentValue;
                break;
            case DIVISION:
                result = storedValue / currentValue;
                break;
            case MODULUS:
                result = storedValue % currentValue;
                break;
            case MULTIPLY:
                result = storedValue * currentValue;
                break;
            default:
                result = currentValue;
                break;
        }

        operand = Operand.NONE;
        storedValue = 0;
        currentValue = result;
        calculatorView.setText(currentValue+"");
    }

    private void setNumber(int num) {
        if(currentValue == 0 && !addDot){
            calculatorView.setText(""+num);
            currentValue = num;
        } else {
            if(!addDot) {
                String value = calculatorView.getText().toString() + num;
                Log.d("TAG_X", value);
                currentValue = Double.parseDouble(value);
                calculatorView.setText(value);
            }
            else{
                String value = calculatorView.getText().toString() + "." + num;
                Log.d("TAG_X", value);
                currentValue = Double.parseDouble(value);
                calculatorView.setText(value);
            }
            addDot = false;
        }
    }
}