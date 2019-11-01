package com.o_bdreldin.form;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.o_bdreldin.ViewHolderLookup;
import com.o_bdreldin.form.field.Field;

import java.util.List;

/**
 * Created by Omar Bdreldin on 8/24/2019
 */
public interface FormBuilder {
    Form build();
    FormBuilder setLayoutManager(@NonNull RecyclerView.LayoutManager layoutManager);
    FormBuilder setFieldList(List<? extends Field<?>> fields);
    FormBuilder submitButtonText(@StringRes int text);
    FormBuilder showSubmitButton(@NonNull OnFormSubmitListener onFormSubmitListener);
    FormBuilder setViewHolderLookup(@NonNull ViewHolderLookup viewHolderLookup);
    Boolean validate();
}
