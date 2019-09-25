package com.o_bdreldin.form.viewholder;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.o_bdreldin.form.R;
import com.o_bdreldin.form.field.Field;

/**
 * Created by Omar Bdreldin on 9/2/2019
 */
public class TextFieldViewHolder extends BasicViewHolder<String> implements TextWatcher {

    protected boolean setTextCalled = false;
    protected Consumer<String> stringConsumer;

    public TextFieldViewHolder(@NonNull View view) {
        super(view);
    }

    @Override
    protected int inputFieldId() {
        return R.id.text_input_edit_text;
    }

    @Override
    protected int inputLayoutId() {
        return R.id.text_input_layout;
    }

    @Override
    public void _bind(Field<String> field) {
        switch (field.getStatus()) {
            case NONE:
                setTextAndWithoutTriggeringOnTextChanged("");
                inputLayout.setError(null);
                break;
            case SET:
                if (field.getValue() != null)
                    setTextAndWithoutTriggeringOnTextChanged(field.getValue());
                inputLayout.setError(null);
                break;
            case VALIDATED:
                switch (field.getValid()) {
                    case IS_VALID:
                        inputLayout.setError(null);
                        setTextAndWithoutTriggeringOnTextChanged(field.getValue());
                        break;
                    case IS_NULL:
                        inputLayout.setError(context.getString(field.errorRequiredStringRes()));
                        setTextAndWithoutTriggeringOnTextChanged("");
                        break;
                    case IS_INVALID:
                        inputLayout.setError(context.getString(field.errorInvalidStringRes()));
                        setTextAndWithoutTriggeringOnTextChanged(field.getValue());
                        break;
                }
                break;
        }
    }

    @Override
    protected void _setListeners(Field<String> field) {
        stringConsumer = field::setValue;
        inputField.setOnFocusChangeListener((view, focused) -> {
            if (focused) inputField.addTextChangedListener(this);
            else inputField.addTextChangedListener(this);
        });
    }

    protected void setTextAndWithoutTriggeringOnTextChanged(@NonNull String text) {
        setTextCalled = true;
        inputField.setText(text);
        setTextCalled = false;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!setTextCalled)
            if (stringConsumer != null)
                stringConsumer.accept(editable.toString());
    }
}
