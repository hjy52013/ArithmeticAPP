package com.hjy.test0308;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ed_amount)
    EditText edAmount;
    @BindView(R.id.but_confirm)
    Button butConfirm;

    int questionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final Intent myIntent = new Intent(MainActivity.this,SecondActivity.class);

        butConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edAmount.getText().toString() != null) {
                    if (Integer.parseInt(edAmount.getText().toString()) > 100) {
                        Toast.makeText(MainActivity.this,"题目个数过多",Toast.LENGTH_SHORT).show();
                    }else {
                        questionNumber = Integer.parseInt(edAmount.getText().toString());
                        myIntent.putExtra("number",questionNumber);
                        startActivity(myIntent);
                        finish();
                    }
                }else {
                    Toast.makeText(MainActivity.this,"请输入题目个数",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
