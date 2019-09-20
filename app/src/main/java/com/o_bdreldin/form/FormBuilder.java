package com.o_bdreldin.form;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.o_bdreldin.form.field.Field;

import java.util.List;

/**
 * Created by Omar Bdreldin on 8/24/2019
 */
public interface FormBuilder {
    Form build();
    FormBuilder setLayoutManager(@NonNull RecyclerView.LayoutManager layoutManager);
    FormBuilder setFieldList(List<? extends Field<?>> fields);
    FormBuilder showSubmitButton(@NonNull Boolean show);
    FormBuilder submitButtonText(@StringRes int text);
    FormBuilder setOnFormSubmitListener(OnFormSubmitListener onFormSubmitListener);
    Boolean validate();
}
