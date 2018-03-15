package com.hjy.test0308;

import android.util.Log;

import java.util.List;

/**
 * Created by hjy on 2018/3/14.
 */

public class Tool {

    public static double accuracy(List<Question> questions){

        double a,b,m=0;
        for (int i = 0; i < questions.size(); i++) {
            a = questions.get(i).getAnswer();
            b = questions.get(i).getUserAnswer();
            Log.i("hahaha", "accuracy: "+ a + b);
            if (Math.abs(a - b) < 0.000001) {
                m ++;
            }
        }
        return (m / questions.size()) * 100;
    }

    public static double decimalRandom(int numberRange,int decimalNumber){
        double a,c;
        a = (int)(Math.random() * numberRange);
        c = (int)(Math.random() * Math.pow(10,decimalNumber));
        c = c / Math.pow(10,decimalNumber);
        a = a + c;
        return a;
    }

}
