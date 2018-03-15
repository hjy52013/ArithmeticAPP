package com.hjy.test0308;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    int questionNumber;
    int multiplication, negativeNumber, decimal, decimalNumber,numberRange;
    Intent myIntent;

    @BindView(R.id.ed_amount)
    EditText edAmount;
    @BindView(R.id.but_confirm)
    Button butConfirm;
    @BindView(R.id.cb_multiplication)
    CheckBox cbMultiplication;
    @BindView(R.id.cb_negativeNumber)
    CheckBox cbNegativeNumber;
    @BindView(R.id.cb_decimal)
    CheckBox cbDecimal;
    @BindView(R.id.ed_decimal)
    EditText edDecimal;
    @BindView(R.id.ed_numberRange)
    EditText edNumberRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();


        cbMultiplication.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    multiplication = 1;
                } else {
                    multiplication = 0;
                }
            }
        });


        cbNegativeNumber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    negativeNumber = 1;
                } else {
                    negativeNumber = 0;
                }
            }
        });

        cbDecimal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    decimal = 1;
                    edDecimal.setVisibility(View.VISIBLE);
                } else {
                    decimal = 0;
                    edDecimal.setVisibility(View.GONE);
                }
            }
        });

        butConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (edAmount.getText().toString() != null) {
//
//                    if (decimal == 1) {
//                        if (edDecimal.getText().toString() != null) {
//
//                        }else {
//                            Toast.makeText(MainActivity.this,"请输入精确到几位小数",Toast.LENGTH_SHORT).show();
//                        }
//                    }else {
//                        if (Integer.parseInt(edAmount.getText().toString()) > 100) {
//                            Toast.makeText(MainActivity.this, "题目个数过多", Toast.LENGTH_SHORT).show();
//                        } else {
//                            questionNumber = Integer.parseInt(edAmount.getText().toString());
//                            myIntent.putExtra("number", questionNumber);
//                            startActivity(myIntent);
//                            finish();
//                        }
//                    }
//
//                } else {
//                    Toast.makeText(MainActivity.this, "请输入题目个数", Toast.LENGTH_SHORT).show();
//                }

                boolean flag = true;
                if (edAmount.getText().toString().equals("")) {
                    flag = false;
                    Toast.makeText(MainActivity.this, "请输入题目数量", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(edAmount.getText().toString()) > 100) {
                    flag = false;
                    Toast.makeText(MainActivity.this, "题目个数过多", Toast.LENGTH_SHORT).show();
                }
                if (decimal == 1 && edDecimal.getText().toString().equals("")) {
                    flag = false;
                    Toast.makeText(MainActivity.this, "请输入要精确的小数位数", Toast.LENGTH_SHORT).show();
                }else if (decimal == 1 && Integer.parseInt(edDecimal.getText().toString()) > 8){
                    flag = false;
                    Toast.makeText(MainActivity.this, "位数太多，请重新输入", Toast.LENGTH_SHORT).show();
                }
                if (flag) {
                    questionNumber = Integer.parseInt(edAmount.getText().toString());
                    if (decimal == 1) {
                        decimalNumber = Integer.parseInt(edDecimal.getText().toString());
                    }
                    if (!edNumberRange.getText().toString().equals("")) {
                        numberRange = Integer.parseInt(edNumberRange.getText().toString());
                    }
                    myIntent.putExtra("questionNumber", questionNumber);
                    myIntent.putExtra("multiplication", multiplication);
                    myIntent.putExtra("negativeNumber", negativeNumber);
                    myIntent.putExtra("decimal", decimal);
                    myIntent.putExtra("decimalNumber", decimalNumber);
                    myIntent.putExtra("numberRange",numberRange);
                    startActivity(myIntent);
                    finish();
                }

            }
        });

    }

    private void init() {

        myIntent = new Intent(MainActivity.this, SecondActivity.class);
        multiplication = 0;
        negativeNumber = 0;
        decimal = 0;
        decimalNumber = 0;
        numberRange = 100;

    }

}
