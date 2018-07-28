package com.team3.uniton.unitonapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class CustomMainItemView extends LinearLayout {
    TextView date;
    TextView contents;


    public CustomMainItemView(Context context) {
        super(context);
        init();
    }

    public CustomMainItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomMainItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    void init() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.main_item, this, false);
        addView(v);

        date = v.findViewById(R.id.date);
        contents = v.findViewById(R.id.contents);
    }


    void setData(MainResignationItem data) {
        date.setText(data.getDate());

        StringBuilder builder = new StringBuilder();
        List<String> datas = data.getContents();
        for(int i = 0; i < datas.size(); i++) {
            if (i != datas.size() - 1) {
                builder.append(datas.get(i));
                builder.append("\n");
            } else {
                builder.append(datas.get(i));
            }
        }
        contents.setText(builder.toString());
    }
}