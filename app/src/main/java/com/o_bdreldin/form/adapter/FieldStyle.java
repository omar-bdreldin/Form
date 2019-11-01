package com.o_bdreldin.form.adapter;

import androidx.annotation.DimenRes;
import androidx.annotation.StyleRes;

import com.o_bdreldin.form.R;

/**
 * Created by Omar Bdreldin on 9/29/2019
 */
public final class FieldStyle {
    @StyleRes public final Integer style;
    @DimenRes public final Integer verticalSpacing;

    public FieldStyle() {
        style = null;
        verticalSpacing = R.dimen.default_field_vertical_spacing;
    }

    public FieldStyle(Integer style, Integer verticalSpacing) {
        this.style = style;
        this.verticalSpacing = verticalSpacing;
    }
}
