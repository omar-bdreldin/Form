package com.o_bdreldin.form.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputLayout;
import com.o_bdreldin.form.field.Field;

/**
 * Created by Omar Bdreldin on 8/25/2019
 */
public abstract class BasicViewHolder<VAL> extends RecyclerView.ViewHolder {
    @NonNull public final EditText inputField;
    @NonNull public final TextInputLayout inputLayout;
    protected final Context context;

    public BasicViewHolder(@NonNull View view) {
        super(view);
        context = view.getContext();
        inputField = view.findViewById(inputFieldId());
        inputLayout = view.findViewById(inputLayoutId());
    }

    @IdRes protected abstract int inputFieldId();

    @IdRes protected abstract int inputLayoutId();

    public void bind(Field<VAL> field) {
        inputField.setInputType(field.inputType());
        inputLayout.setHint(context.getString(field.hintStringRes()));
        inputLayout.setEndIconMode(field.endIconMode());
        _bind(field);
        _setListeners(field);
    }

    protected abstract void _bind(Field<VAL> field);

    protected abstract void _setListeners(Field<VAL> field);
}
