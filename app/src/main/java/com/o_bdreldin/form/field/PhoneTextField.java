package com.o_bdreldin.form.field;

import android.text.InputType;
import android.util.Patterns;

import androidx.annotation.NonNull;

/**
 * Created by Omar Bdreldin on 9/17/2019
 */
public class PhoneTextField extends PlainTextField {
    public PhoneTextField(int hintStringRes, int errorRequiredStringRes) {
        super(hintStringRes, errorRequiredStringRes);
    }

    public PhoneTextField(int hintStringRes, int errorRequiredStringRes, int errorInvalidStringRes) {
        super(hintStringRes, errorRequiredStringRes, errorInvalidStringRes);
    }

    @Override
    public int inputType() {
        return InputType.TYPE_CLASS_PHONE;
    }

    @NonNull
    @Override
    public Boolean validate() {
        boolean valid = super.validate();
        if (valid) {
            if (!Patterns.PHONE.matcher(getValue()).matches()) {
                setValid(Valid.IS_INVALID);
            }
        }
        return valid;
    }
}
