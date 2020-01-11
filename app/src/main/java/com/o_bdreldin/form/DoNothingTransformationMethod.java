package com.o_bdreldin.form;

import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;

/**
 * Created by Omar Bdreldin on 1/11/2020
 */
public class DoNothingTransformationMethod implements TransformationMethod {
    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return source;
    }

    @Override
    public void onFocusChanged(View view, CharSequence sourceText, boolean focused,
                               int direction, Rect previouslyFocusedRect) {

    }
}
