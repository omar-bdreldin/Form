package com.o_bdreldin.form.field;

import android.text.InputType;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;
import com.o_bdreldin.form.ViewType;

import java.util.List;

/**
 * Created by Omar Bdreldin on 9/17/2019
 */
public class AutoCompleteTextField<VAL> extends Field_Impl_I<VAL> {

    private final List<VAL> list;

    public AutoCompleteTextField(int hintStringRes, int errorRequiredStringRes
            , @NonNull List<VAL> list) {
        super(hintStringRes, errorRequiredStringRes, 0);
        this.list = list;
    }

    public AutoCompleteTextField(int hintStringRes, int errorRequiredStringRes
            , int errorInvalidStringRes, @NonNull List<VAL> list) {
        super(hintStringRes, errorRequiredStringRes, errorInvalidStringRes);
        this.list = list;
    }

    @Override
    public int viewType() {
        return ViewType.VIEW_TYPE_AUTO_COMPLETE;
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
}
