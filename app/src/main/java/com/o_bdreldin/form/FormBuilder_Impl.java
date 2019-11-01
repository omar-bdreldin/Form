package com.o_bdreldin.form;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.o_bdreldin.ViewHolderLookup;
import com.o_bdreldin.form.adapter._Adapter;
import com.o_bdreldin.form.field.Field;

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
    private Boolean showSubmitButton = false;
    @NonNull
    private OnFormSubmitListener onFormSubmitListener;
    private RecyclerView.Adapter<?> adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ViewHolderLookup viewHolderLookup;

    FormBuilder_Impl(@NonNull RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        layoutManager = new LinearLayoutManager(recyclerView.getContext());
    }

    @Override
    public Form build() {
        Form form = new Form_Impl(this);
        _Adapter _adapter = new _Adapter(fields, showSubmitButton);
        _adapter.setOnFormSubmitListener(() -> onFormSubmitListener.onFormSubmit(form));
        _adapter.setViewHolderLookup(viewHolderLookup);
        recyclerView.setAdapter(adapter = _adapter);
        return form;
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
    public FormBuilder submitButtonText(int text) {
        this.submitButtonText = text;
        return this;
    }

    @Override
    public FormBuilder showSubmitButton(@NonNull OnFormSubmitListener onFormSubmitListener) {
        this.showSubmitButton = true;
        this.onFormSubmitListener = onFormSubmitListener;
        return this;
    }

    @Override
    public FormBuilder setViewHolderLookup(@NonNull ViewHolderLookup viewHolderLookup) {
        this.viewHolderLookup = viewHolderLookup;
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
