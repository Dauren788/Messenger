package com.example.chatproject.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.chatproject.MainActivity;

public class CustomTextView extends AppCompatTextView {
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitSize(context);
    }

    //установка нужного размера
    private void InitSize(Context context) {
        //получаем коэффициент увеличения шрифта
        float koef = MainActivity.getSizeCoef();

        float cur_size = getTextSize(); //текущий размер шрифта в пикселях
        //устанавливаем новый размер шрифта в пикселях
        setTextSize(TypedValue.COMPLEX_UNIT_PX, cur_size * koef);
    }
}
