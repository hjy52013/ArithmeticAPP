package com.hjy.test0308;

import java.util.List;

/**
 * Created by hjy on 2018/3/14.
 */

public class Tool {

    public static int accuracy(List<Question> questions){

        int a,b,m=0;
        for (int i = 0; i < questions.size(); i++) {
            a = questions.get(i).getAnswer();
            b = questions.get(i).getUserAnswer();
            if (a == b) {
                m ++;
            }
        }
        return (m / questions.size()) * 100;
    }

}
