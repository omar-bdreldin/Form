package com.o_bdreldin.form.field;

import android.text.InputType;
import android.util.Patterns;

import androidx.annotation.NonNull;

import com.o_bdreldin.form.R;

/**
 * Created by Omar Bdreldin on 9/2/2019
 */
public class EmailTextField extends PlainTextField {

    public EmailTextField() {
        super(R.string.hint_email, R.string.error_email_required, R.string.error_email_invalid);
    }

    public EmailTextField(int hintStringRes
            , int errorRequiredStringRes
            , int errorInvalidStringRes) {
        super(hintStringRes, errorRequiredStringRes, errorInvalidStringRes);
    }

    @NonNull
    @Override
    public Boolean validate() {
        boolean valid = super.validate();
        if (valid) {
            if (!Patterns.EMAIL_ADDRESS.matcher(getValue()).matches()) {
                valid = false;
                setValid(Valid.IS_INVALID);
            }
        }
        return valid;
    }

    @Override
    public int inputType() {
        return InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
    }
}
