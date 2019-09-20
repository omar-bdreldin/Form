package com.o_bdreldin.form.field;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.o_bdreldin.form.ViewType;

/**
 * Created by Omar Bdreldin on 9/2/2019
 */
public class PlainTextField extends Field_Impl_I<String> {

    public PlainTextField(int hintStringRes
            , int errorRequiredStringRes) {
        super(hintStringRes, errorRequiredStringRes, 0);
    }

    public PlainTextField(int hintStringRes
            , int errorRequiredStringRes
            , int errorInvalidStringRes) {
        super(hintStringRes, errorRequiredStringRes, errorInvalidStringRes);
    }

    @Override
    public int viewType() {
        return ViewType.VIEW_TYPE_TEXT;
    }

    @NonNull
    @Override
    public Boolean validate() {
        boolean valid = super.validate();
        if (valid) {
            if (getValue().isEmpty()) {
                valid = false;
                setValid(Valid.IS_NULL);
            }
        }
        return valid;
    }
}
