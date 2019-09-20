package com.o_bdreldin.form;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.o_bdreldin.form.adapter._Adapter;
import com.o_bdreldin.form.field.Field;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Omar Bdreldin on 8/24/2019
 */
public class FormBuilder_Impl implements FormBuilder {
    @NonNull
    private final RecyclerView recyclerView;
    @NonNull
    private List<? extends Field<?>> fields;
    @StringRes
    private int submitButtonText = R.string.submit;
    @NonNull
    private Boolean showSubmitButton = true;
    @NonNull
    private OnFormSubmitListener onFormSubmitListener;
    private RecyclerView.Adapter<?> adapter;
    private RecyclerView.LayoutManager layoutManager;

    FormBuilder_Impl(@NonNull RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        layoutManager = new LinearLayoutManager(recyclerView.getContext());
    }

    @Override
    public Form build() {
        // TODO: validate nulls
        recyclerView.setAdapter(adapter = new _Adapter(fields, showSubmitButton));
        return new Form_Impl(this);
    }

    @Override
    public FormBuilder setLayoutManager(@NonNull RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    @Override
    public FormBuilder setFieldList(List<? extends Field<?>> fields) {
        if (fields == null)
            throw new IllegalArgumentException("Fields list can't be null.");
        else this.fields = fields;
        return this;
    }

    @Override
    public FormBuilder showSubmitButton(@NotNull Boolean show) {
        showSubmitButton = show;
        return this;
    }

    @Override
    public FormBuilder submitButtonText(int text) {
        this.submitButtonText = text;
        return this;
    }

    @Override
    public FormBuilder setOnFormSubmitListener(OnFormSubmitListener onFormSubmitListener) {
        if (onFormSubmitListener == null)
            throw new IllegalArgumentException("OnFormSubmitListener can't be null");
        else this.onFormSubmitListener = onFormSubmitListener;
        return this;
    }

    @Override
    public Boolean validate() {
        boolean valid = true;
        for (Field<?> field : fields)
            if (!field.validate())
                valid = false;
        adapter.notifyDataSetChanged();
        return valid;
    }
}
