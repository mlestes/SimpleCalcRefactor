package com.coolcats.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Math.cos;
import static java.lang.Math.log10;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;

public class MainActivity extends AppCompatActivity {

    enum Operand {
        NONE,
        PLUS,
        MINUS,
        MULTIPLY,
        MODULUS,
        DIVISION
    }
    
    private TextView calculatorView;
    private double currentValue = 0.0;
    private double storedValue = 0.0;
    private Operand operand = Operand.NONE;
    private boolean addDot = false;
    private boolean doReset = false;
    DecimalFormat decimalFormat = new DecimalFormat("#.######");

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
                doReset = true;
                break;
            case R.id.period_button:
                if(doReset){
                    calculatorView.setText("0");
                    addDot = true;
                }
                else {
                    String value = calculatorView.getText().toString();
                    if (value.indexOf('.') == -1) addDot = true;
                }
                break;

            case R.id.negate_button:
                currentValue *= -1;
                calculatorView.setText(currentValue+"");
                break;

            case R.id.sin_button:
                doSin();
                break;

            case R.id.cos_button:
                doCos();
                break;

            case R.id.tan_button:
                doTan();
                break;

            case R.id.log_button:
                doLog();
                break;

            case R.id.sqrt_button:
                doSqrt();
                break;

        }
    }

    private void doSqrt() {
        String value = calculatorView.getText().toString();
        currentValue = Double.parseDouble(value);
        currentValue = sqrt(currentValue);
        decimalFormat.setRoundingMode(RoundingMode.UP);
        if(currentValue % 1 == 0) calculatorView.setText((int) currentValue+"");
        else calculatorView.setText(decimalFormat.format(currentValue)+"");
    }

    private void doLog() {
        String value = calculatorView.getText().toString();
        currentValue = Double.parseDouble(value);
        currentValue = log10(currentValue);
        decimalFormat.setRoundingMode(RoundingMode.UP);
        if(currentValue % 1 == 0) calculatorView.setText((int) currentValue+"");
        else calculatorView.setText(decimalFormat.format(currentValue)+"");
    }

    private void doTan() {
        String value = calculatorView.getText().toString();
        currentValue = Double.parseDouble(value);
        currentValue = tan(currentValue);
        decimalFormat.setRoundingMode(RoundingMode.UP);
        if(currentValue % 1 == 0) calculatorView.setText((int) currentValue+"");
        else calculatorView.setText(decimalFormat.format(currentValue)+"");
    }

    private void doCos() {
        String value = calculatorView.getText().toString();
        currentValue = Double.parseDouble(value);
        currentValue = cos(currentValue);
        decimalFormat.setRoundingMode(RoundingMode.UP);
        if(currentValue % 1 == 0) calculatorView.setText((int) currentValue+"");
        else calculatorView.setText(decimalFormat.format(currentValue)+"");
    }

    private void doSin() {
        String value = calculatorView.getText().toString();
        currentValue = Double.parseDouble(value);
        currentValue = sin(currentValue);
        decimalFormat.setRoundingMode(RoundingMode.UP);
        if(currentValue % 1 == 0) calculatorView.setText((int) currentValue+"");
        else calculatorView.setText(decimalFormat.format(currentValue)+"");
    }

    private void doCalculation(Operand operand) {
        getResult();
        storedValue = currentValue;
        currentValue = 0;
        this.operand = operand;
        doReset = true;
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
        decimalFormat.setRoundingMode(RoundingMode.UP);
        if(currentValue % 1 == 0) calculatorView.setText((int) currentValue+"");
        else calculatorView.setText(decimalFormat.format(currentValue)+"");
    }

    private void setNumber(int num) {
        if(doReset) {
            currentValue = 0;
            doReset = false;
        }
        if(currentValue == 0 && !addDot){
            calculatorView.setText(""+num);
            currentValue = num;
        } else {
            if(!addDot) {
                String value = calculatorView.getText().toString() + num;
                Log.d("TAG_X", value);
                currentValue = Double.parseDouble(value);
                decimalFormat.setRoundingMode(RoundingMode.UP);
                if(currentValue % 1 == 0) new DecimalFormat("#").format(currentValue);
                calculatorView.setText(decimalFormat.format(currentValue)+"");
            }
            else{
                String value = calculatorView.getText().toString() + "." + num;
                Log.d("TAG_X", value);
                currentValue = Double.parseDouble(value);
                decimalFormat.setRoundingMode(RoundingMode.UP);
                if(currentValue % 1 == 0) calculatorView.setText((int) currentValue);
                else calculatorView.setText(decimalFormat.format(currentValue)+"");
            }
            addDot = false;
        }
    }
}