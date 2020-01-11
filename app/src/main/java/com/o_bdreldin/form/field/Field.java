package com.o_bdreldin.form.field;

import android.text.InputType;
import android.text.method.TransformationMethod;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.google.android.material.textfield.TextInputLayout;
import com.o_bdreldin.form.DoNothingTransformationMethod;

import java.util.List;

import static com.google.android.material.textfield.TextInputLayout.END_ICON_NONE;

/**
 * Created by Omar Bdreldin on 8/25/2019
 */
public interface Field<T> {

    T getValue();

    void setValue(T value);

    int viewType();

    default List<?> autocompleteReferenceList() {
        return null;
    }

    @NonNull
    Boolean validate();

    @StringRes
    int hintStringRes();

    @StringRes
    int errorRequiredStringRes();

    @StringRes
    int errorInvalidStringRes();

    default int inputType() {
        return InputType.TYPE_CLASS_TEXT;
    }

    @TextInputLayout.EndIconMode
    default int endIconMode() {
        return END_ICON_NONE;
    }

    @DrawableRes
    default int endIcon() {
        return 0;
    }

    @NonNull
    Status getStatus();

    void setStatus(@NonNull Status status);

    @NonNull
    Valid getValid();

    void setValid(@NonNull Valid valid);

    default TransformationMethod transformationMethod() {
        return new DoNothingTransformationMethod();
    }

    enum Status {
        NONE, SET, VALIDATED
    }

    enum Valid {
        IS_VALID, IS_NULL, IS_INVALID
    }
}
