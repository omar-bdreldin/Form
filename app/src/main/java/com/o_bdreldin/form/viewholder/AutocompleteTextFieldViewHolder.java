package com.o_bdreldin.form.viewholder;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;

import com.o_bdreldin.form.R;
import com.o_bdreldin.form.field.Field;

/**
 * Created by Omar Bdreldin on 9/17/2019
 */
public class AutocompleteTextFieldViewHolder extends BasicViewHolder<Object> {

    protected AutoCompleteTextView autoCompleteTextView;

    public AutocompleteTextFieldViewHolder(@NonNull View view) {
        super(view);
        autoCompleteTextView = (AutoCompleteTextView) inputField;
    }

    @Override
    protected int inputFieldId() {
        return R.id.auto_complete_text_view;
    }

    @Override
    protected int inputLayoutId() {
        return R.id.text_input_layout;
    }

    @Override
    protected void _bind(Field<Object> field) {
        autoCompleteTextView.setAdapter(null);
        // TODO
        switch (field.getStatus()) {
            case NONE:
                autoCompleteTextView.setText("");
                break;
            case SET:
                if (field.getValue() != null)
                    autoCompleteTextView.setText(field.getValue().toString());
                break;
            case VALIDATED:
                if (field.getValue() != null)
                    autoCompleteTextView.setText(field.getValue().toString());
                switch (field.getValid()) {
                    case IS_VALID:
                        inputLayout.setError(null);
                        break;
                    case IS_NULL:
                        inputLayout.setError(context.getString(field.errorRequiredStringRes()));
                        break;
                    case IS_INVALID:
                        inputLayout.setError(context.getString(field.errorInvalidStringRes()));
                        break;
                }
        }
    }

    @Override
    protected void _setListeners(Field<Object> field) {
        autoCompleteTextView.setAdapter(
                new ArrayAdapter<>(
                        context,
                        android.R.layout.simple_dropdown_item_1line,
                        field.autocompleteReferenceList()
                )
        );
        autoCompleteTextView.setOnItemClickListener((adapterView, view, position, id) -> {
            field.setValue(field.autocompleteReferenceList().get(position));
        });
    }
}