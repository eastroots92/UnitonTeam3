package com.team3.uniton.unitonapplication.ui.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team3.uniton.unitonapplication.R;

public class ReasonItemView extends LinearLayout {

    TextView index;
    EditText content;

    public ReasonItemView(Context context) {
        super(context);
        init();
    }

    public ReasonItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ReasonItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.write_item, this, false);
        addView(v);

        index = v.findViewById(R.id.index);
        content = v.findViewById(R.id.content);
    }


    public void setData(int i, String text, boolean editable) {
        index.setText(i + ".");
        content.setText(text);

        if (!editable) {
            content.setEnabled(false);
            index.setTextColor(getResources().getColor(R.color.greyish_brown));
            content.setTextColor(getResources().getColor(R.color.greyish_brown));
        }

    }

    public String getText() {
        return content.getText().toString();
    }
}
