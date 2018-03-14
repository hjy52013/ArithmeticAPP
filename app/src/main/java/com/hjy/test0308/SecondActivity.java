package com.hjy.test0308;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

    int questionNumber;
    List<Question> questionList = new ArrayList<>();
    QuestionAdapter questionAdapter;
    AlertDialog.Builder builder;

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
                builder.setTitle(textViewQuestion.getText());
                builder.setView(myDialogView);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        EditText editText = myDialogView.findViewById(R.id.ed_dialog);
                        textViewAnswer.setText(editText.getText().toString());
                        questionList.get(i).setUserAnswer(Integer.parseInt(editText.getText().toString()));
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

                int m = Tool.accuracy(questionList);
                Toast.makeText(SecondActivity.this,"正确率为：" + m + "%",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void init() {

        builder = new AlertDialog.Builder(SecondActivity.this);
        questionNumber = getIntent().getIntExtra("number",0);

        int a,b,c;
        int d = 0;
        String s = "";
        for (int i = 0; i < questionNumber; i++) {
            Question question;
            a = (int)(Math.random()*100);
            b = (int)(Math.random()*100);
            c = (int)(Math.random()*100);
            switch (c % 4) {
                case 0:
                    s = "+";
                    d = a + b;
                    break;
                case 1:
                    s = "-";
                    d = a - b;
                    break;
                case 2:
                    s = "*";
                    d = a * b;
                    break;
                case 3:
                    s = "/";
                    while (b == 0){

                        b = (int)(Math.random()*100);

                    }
                    d = a / b;
                    break;
            }
            s = a + s + b + "=";
            question = new Question(s,d);
            questionList.add(question);
        }

        questionAdapter = new QuestionAdapter(questionList,SecondActivity.this);
        lvQuestion.setAdapter(questionAdapter);

    }

}
