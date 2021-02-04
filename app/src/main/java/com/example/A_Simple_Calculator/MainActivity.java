package com.example.A_Simple_Calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnAdd, btnSub, btnEq, btnMul, btnDiv;
    TextView ans, query;
    String process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btnAdd = findViewById(R.id.buttonAdd);
        btnSub = findViewById(R.id.buttonSub);
        btnMul = findViewById(R.id.buttonMul);
        btnEq = findViewById(R.id.buttonEq);
        btnDiv = findViewById(R.id.buttonDiv);
        ans = findViewById(R.id.ans);
        query = findViewById(R.id.query);
    }

    public void onClick1(View view){
        process = query.getText().toString();
        query.setText(process + "1");
    }

    public void onClick2(View view){
        process = query.getText().toString();
        query.setText(process + "2");
    }

    public void onClick3(View view){
        process = query.getText().toString();
        query.setText(process + "3");
    }

    public void onClick4(View view){
        process = query.getText().toString();
        query.setText(process + "4");
    }

    public void onClick5(View view){
        process = query.getText().toString();
        query.setText(process + "5");
    }

    public void onClick6(View view){
        process = query.getText().toString();
        query.setText(process + "6");
    }

    public void onClick7(View view){
        process = query.getText().toString();
        query.setText(process + "7");
    }

    public void onClick8(View view){
        process = query.getText().toString();
        query.setText(process + "8");
    }

    public void onClick9(View view){
        process = query.getText().toString();
        query.setText(process + "9");
    }

    public void onClick0(View view){
        process = query.getText().toString();
        query.setText(process + "0");
    }


    public void onClickAdd(View view){
        process = query.getText().toString();
        query.setText(process + "+");
    }

    public void onClickSub(View view){
        process = query.getText().toString();
        query.setText(process + "-");
    }

    public void onClickMul(View view){
        process = query.getText().toString();
        query.setText(process + "*");
    }

    public void onClickDiv(View view){
        process = query.getText().toString();
        query.setText(process + "/");
    }

    public void onClickDel(View view){
        ans.setText("");
        query.setText("");
    }


    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    public void onClickEquals(View view){
        String str = (String)query.getText();
        double Result = eval(str);
        String r = Double.toString(Result);
        ans.setText(r);
    }

}