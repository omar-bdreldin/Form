package com.o_bdreldin.form.field;

import android.text.InputType;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;
import com.o_bdreldin.form.ViewType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Omar Bdreldin on 9/17/2019
 */
public class MultipleAutoCompleteTextField<VAL> extends Field_Impl_I<List<VAL>> {

    private final List<VAL> list;

    public MultipleAutoCompleteTextField(int hintStringRes, int errorRequiredStringRes
            , @NonNull List<VAL> list) {
        this(hintStringRes, errorRequiredStringRes, 0, list);
    }

    public MultipleAutoCompleteTextField(int hintStringRes, int errorRequiredStringRes
            , int errorInvalidStringRes, @NonNull List<VAL> list) {
        super(hintStringRes, errorRequiredStringRes, errorInvalidStringRes);
        this.list = list;
        setValue(new ArrayList<>());
    }

    @Override
    public int viewType() {
        return ViewType.VIEW_TYPE_MULTIPLE_AUTO_COMPLETE;
    }

    @Override
    public List<?> autocompleteReferenceList() {
        return list;
    }

    @Override
    public int inputType() {
        return InputType.TYPE_CLASS_TEXT;
    }

    @Override
    public int endIconMode() {
        return TextInputLayout.END_ICON_DROPDOWN_MENU;
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
