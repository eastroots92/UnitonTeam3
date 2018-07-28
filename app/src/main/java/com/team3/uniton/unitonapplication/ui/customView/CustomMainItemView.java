package com.team3.uniton.unitonapplication.ui.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team3.uniton.unitonapplication.model.MainResignationItem;
import com.team3.uniton.unitonapplication.R;
import com.team3.uniton.unitonapplication.model.Resignation;

import java.util.List;

public class CustomMainItemView extends LinearLayout {
    TextView date;
    TextView contents;

    Resignation mData;

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


    public void setData(Resignation data) {

        mData = data;

        date.setText(data.getDate());
        StringBuilder builder = new StringBuilder();
        builder.append(data.getFirst_reason());
        builder.append("\n");
        builder.append(data.getSecond_reason());
        builder.append("\n");
        builder.append(data.getThird_reason());

        contents.setText(builder.toString());
    }
}
