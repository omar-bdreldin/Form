package com.o_bdreldin.form.field;

import android.text.InputType;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import java.util.List;

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

    default int endIconMode() {
        return 0;
    }

    default int endIcon() {
        return 0;
    }

    void setEndIcon(int endIcon);

    @NonNull
    Status getStatus();

    void setStatus(@NonNull Status status);

    @NonNull
    Valid getValid();

    void setValid(@NonNull Valid valid);

    enum Status {
        NONE, SET, VALIDATED
    }

    enum Valid {
        IS_VALID, IS_NULL, IS_INVALID
    }
}
