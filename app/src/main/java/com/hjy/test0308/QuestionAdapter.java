package com.hjy.test0308;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hjy on 2018/3/13.
 */

public class QuestionAdapter extends BaseAdapter{

    List<Question> questionList;
    Context context;
    LayoutInflater layoutInflater;
    ViewHolder viewHolder;

    class ViewHolder{

        TextView textViewQuestion;
        TextView textViewAnswer;

    }

    public QuestionAdapter(List<Question> questionList, Context context) {
        this.questionList = questionList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return questionList == null ? 0 : questionList.size();
    }

    @Override
    public Question getItem(int i) {
        return questionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = layoutInflater.inflate(R.layout.lv_item,null);
            viewHolder = new ViewHolder();
            viewHolder.textViewQuestion = view.findViewById(R.id.tv_question);
            viewHolder.textViewAnswer = view.findViewById(R.id.tv_answer);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textViewQuestion.setText(getItem(i).getQuestion());

        return view;
    }
}
