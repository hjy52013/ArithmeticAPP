package com.hjy.test0308;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.lv_question)
    ListView lvQuestion;

    List<Question> questionList = new ArrayList<>();
    QuestionAdapter questionAdapter;
    AlertDialog.Builder builder;
    int decimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);


        init();


        lvQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {

                final TextView textViewAnswer = view.findViewById(R.id.tv_answer);
                final TextView textViewQuestion = view.findViewById(R.id.tv_question);
                final View myDialogView = getLayoutInflater().inflate(R.layout.mydialog,null);
                final EditText editText = myDialogView.findViewById(R.id.ed_dialog);
                if (decimal == 1) {
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                }else {
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
                }
                builder.setTitle(textViewQuestion.getText());
                builder.setView(myDialogView);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        textViewAnswer.setText(editText.getText().toString());
                        String s = editText.getText().toString();
                        Double a = new Double(s);
                        questionList.get(i).setUserAnswer(a);
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.secondmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_submit:

                double m = Tool.accuracy(questionList);
                Toast.makeText(SecondActivity.this,"正确率为：" + m + "%",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void init() {

        int questionNumber,multiplication, negativeNumber,decimalNumber,numberRange;
        builder = new AlertDialog.Builder(SecondActivity.this);
        questionNumber = getIntent().getIntExtra("questionNumber",0);
        multiplication = getIntent().getIntExtra("multiplication",0);
        negativeNumber = getIntent().getIntExtra("negativeNumber",0);
        decimal = getIntent().getIntExtra("decimal",0);
        decimalNumber = getIntent().getIntExtra("decimalNumber",0);
        numberRange = getIntent().getIntExtra("numberRange",100);

        questionList = getQuestionList(questionNumber,multiplication,negativeNumber,decimal,decimalNumber,numberRange);
        questionAdapter = new QuestionAdapter(questionList,SecondActivity.this);
        lvQuestion.setAdapter(questionAdapter);

    }

    private List<Question> getQuestionList(int questionNumber,int multiplication,int negativeNumber,int decimal,int decimalNumber,int numberRange) {

        int c,q;
        String s = "";
        List<Question> questionList = new ArrayList<>();
        if (decimal == 1) {
            double a,b,d = 0;
            for (int i = 0; i < questionNumber; i++) {
                Question question;
                a = Tool.decimalRandom(numberRange,decimalNumber);
                b = Tool.decimalRandom(numberRange,decimalNumber);
                c = (int)(Math.random() * 100);
                q = (int)(Math.random() * 100);
                if (negativeNumber == 1) {
                    switch (q % 4) {
                        case 0:
                            a = 0 - a;
                            break;
                        case 1:
                            b = 0 - b;
                            break;
                        case 3:
                            break;
                        case 4:
                            a = 0 - a;
                            b = 0 - b;
                            break;
                    }
                }
                if (multiplication == 1) {
                    switch (c % 4) {
                        case 0:
                            s = "+";
                            d = a + b;
                            break;
                        case 1:
                            s = "-";
                            if (negativeNumber == 0) {
                                while (a - b < 0){
                                    a = Tool.decimalRandom(numberRange,decimalNumber);
                                    b = Tool.decimalRandom(numberRange,decimalNumber);
                                }
                            }
                            d = a - b;
                            break;
                        case 2:
                            s = "*";
                            d = a * b;
                            break;
                        case 3:
                            s = "/";
                            while (b == 0){
                                b = Tool.decimalRandom(numberRange,decimalNumber);
                            }
                            d = a / b;
                            break;
                    }
                }else {
                    switch (c % 2) {
                        case 0:
                            s = "+";
                            d = a + b;
                            break;
                        case 1:
                            s = "-";
                            if (negativeNumber == 0) {
                                while (a - b < 0){
                                    a = Tool.decimalRandom(numberRange,decimalNumber);
                                    b = Tool.decimalRandom(numberRange,decimalNumber);
                                }
                            }
                            d = a - b;
                            break;
                    }
                }
                if (b < 0){
                    s = a + s + "(" + b + ")" + "=";
                }else {
                    s = a + s + b + "=";
                }
                question = new Question(s,d);
                questionList.add(question);
            }
        }else {
            int a,b,d = 0;
            a = (int)(Math.random() * numberRange);
            b = (int)(Math.random() * numberRange);
            for (int i = 0; i < questionNumber; i++) {
                Question question;
                a = (int)(Math.random() * numberRange);
                b = (int)(Math.random() * numberRange);
                c = (int)(Math.random() * 100);
                q = (int)(Math.random() * 100);
                if (negativeNumber == 1) {
                    switch (q % 4) {
                        case 0:
                            a = 0 - a;
                            break;
                        case 1:
                            b = 0 - b;
                            break;
                        case 3:
                            break;
                        case 4:
                            a = 0 - a;
                            b = 0 - b;
                            break;
                    }
                }
                if (multiplication == 1) {
                    switch (c % 4) {
                        case 0:
                            s = "+";
                            d = a + b;
                            break;
                        case 1:
                            s = "-";
                            if (negativeNumber == 0) {
                                while (a - b < 0){
                                    a = (int)(Math.random() * numberRange);
                                    b = (int)(Math.random() * numberRange);
                                }
                            }
                            d = a - b;
                            break;
                        case 2:
                            s = "*";
                            d = a * b;
                            break;
                        case 3:
                            s = "/";
                            while (b == 0){
                                b = (int)(Math.random() * numberRange);
                            }
                            d = a / b;
                            break;
                    }
                }else {
                    switch (c % 2) {
                        case 0:
                            s = "+";
                            d = a + b;
                            break;
                        case 1:
                            s = "-";
                            if (negativeNumber == 0) {
                                while (a - b < 0){
                                    a = (int)(Math.random() * numberRange);
                                    b = (int)(Math.random() * numberRange);
                                }
                            }
                            d = a - b;
                            break;
                    }
                }
                if (b < 0){
                    s = a + s + "(" + b + ")" + "=";
                }else {
                    s = a + s + b + "=";
                }
                question = new Question(s,d);
                questionList.add(question);
            }
        }

        return  questionList;

    }

}
