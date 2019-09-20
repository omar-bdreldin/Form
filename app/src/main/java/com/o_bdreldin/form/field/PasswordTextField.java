package com.o_bdreldin.form.field;

import android.text.InputType;

import com.o_bdreldin.form.R;

/**
 * Created by Omar Bdreldin on 9/17/2019
 */
public class PasswordTextField extends PlainTextField {

    private boolean showPasswordToggle = true;

    public PasswordTextField() {
        super(R.string.hint_password, R.string.error_password_required);
    }

    public PasswordTextField(boolean showPasswordToggle) {
        super(R.string.hint_password, R.string.error_password_required);
        this.showPasswordToggle = showPasswordToggle;
    }

    public PasswordTextField(int hintStringRes, int errorRequiredStringRes) {
        super(hintStringRes, errorRequiredStringRes);
    }

    public PasswordTextField(int hintStringRes, int errorRequiredStringRes
            , int errorInvalidStringRes) {
        super(hintStringRes, errorRequiredStringRes, errorInvalidStringRes);
    }

    public PasswordTextField(int hintStringRes, int errorRequiredStringRes
            , boolean showPasswordToggle) {
        super(hintStringRes, errorRequiredStringRes);
        this.showPasswordToggle = showPasswordToggle;
    }

    public PasswordTextField(int hintStringRes, int errorRequiredStringRes
            , int errorInvalidStringRes, boolean showPasswordToggle) {
        super(hintStringRes, errorRequiredStringRes, errorInvalidStringRes);
        this.showPasswordToggle = showPasswordToggle;
    }

    @Override
    public int inputType() {
        return InputType.TYPE_TEXT_VARIATION_PASSWORD;
    }

    @Override
    public int endIconMode() {
        return showPasswordToggle ? 0 : super.endIconMode(); // TODO
    }
}
