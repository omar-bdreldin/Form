package com.o_bdreldin.form.field;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

/**
 * Created by Omar Bdreldin on 8/24/2019
 */
public abstract class Field_Impl_I<T> implements Field<T> {
    private T value;
    @StringRes
    private final int hintStringRes, errorRequiredStringRes, errorInvalidStringRes;
    @DrawableRes
    private int endIcon;
    private Status status = Status.NONE;
    private Valid valid;

    public Field_Impl_I(int hintStringRes
            , int errorRequiredStringRes
            , int errorInvalidStringRes) {
        this.hintStringRes = hintStringRes;
        this.errorRequiredStringRes = errorRequiredStringRes;
        this.errorInvalidStringRes = errorInvalidStringRes;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
        setStatus(Status.SET);
    }

    @Override
    public int endIcon() {
        return endIcon;
    }

    public void setEndIcon(@DrawableRes int endIcon) {
        this.endIcon = endIcon;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(@NonNull Status status) {
        this.status = status;
    }

    @Override
    public Valid getValid() {
        return valid;
    }

    @Override
    public void setValid(@NonNull Valid valid) {
        this.valid = valid;
    }

    @Override
    public int hintStringRes() {
        return hintStringRes;
    }

    @Override
    public int errorRequiredStringRes() {
        return errorRequiredStringRes;
    }

    @Override
    public int errorInvalidStringRes() {
        return errorInvalidStringRes;
    }

    @NonNull
    @Override
    public Boolean validate() {
        status = Status.VALIDATED;
        boolean valid = value != null;
        if (valid) setValid(Valid.IS_VALID);
        else setValid(Valid.IS_NULL);
        return valid;
    }
}
