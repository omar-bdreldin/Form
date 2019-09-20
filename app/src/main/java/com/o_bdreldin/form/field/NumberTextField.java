package com.o_bdreldin.form.field;

import android.text.InputType;

/**
 * Created by Omar Bdreldin on 9/17/2019
 */
public class NumberTextField extends PlainTextField {
    public NumberTextField(int hintStringRes, int errorRequiredStringRes) {
        super(hintStringRes, errorRequiredStringRes);
    }

    public NumberTextField(int hintStringRes, int errorRequiredStringRes, int errorInvalidStringRes) {
        super(hintStringRes, errorRequiredStringRes, errorInvalidStringRes);
    }

    @Override
    public int inputType() {
        return InputType.TYPE_CLASS_NUMBER;
    }
}
