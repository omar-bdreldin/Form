package com.o_bdreldin.form.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.o_bdreldin.ViewHolderLookup;
import com.o_bdreldin.form.ViewType;
import com.o_bdreldin.form.field.Field;
import com.o_bdreldin.form.viewholder.AutoCompleteTextFieldViewHolder;
import com.o_bdreldin.form.viewholder.BasicViewHolder;
import com.o_bdreldin.form.viewholder.MultipleAutoCompleteTextFieldViewHolder;
import com.o_bdreldin.form.viewholder.SubmitButtonViewHolder;
import com.o_bdreldin.form.viewholder.TextFieldViewHolder;

import java.util.List;

/**
 * Created by Omar Bdreldin on 9/2/2019
 */
public class _Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<? extends Field<?>> items;
    private boolean showSubmitButton;
    private Runnable onFormSubmitListener;
    private ViewHolderLookup viewHolderLookup;

    public _Adapter(@NonNull List<? extends Field<?>> items) {
        this.items = items;
    }

    public _Adapter(List<? extends Field<?>> items, Boolean showSubmitButton) {
        this.items = items;
        this.showSubmitButton = showSubmitButton;
    }

    public void setOnFormSubmitListener(Runnable onFormSubmitListener) {
        this.onFormSubmitListener = onFormSubmitListener;
    }

    public void setViewHolderLookup(ViewHolderLookup viewHolderLookup) {
        this.viewHolderLookup = viewHolderLookup;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ViewType.VIEW_TYPE_TEXT:
                return TextFieldViewHolder.create(parent);
            case ViewType.VIEW_TYPE_AUTO_COMPLETE:
                return AutoCompleteTextFieldViewHolder.create(parent);
            case ViewType.VIEW_TYPE_MULTIPLE_AUTO_COMPLETE:
                return MultipleAutoCompleteTextFieldViewHolder.Companion.create(parent);
            case ViewType.VIEW_TYPE_SUBMIT_BUTTON:
                return SubmitButtonViewHolder.create(parent, onFormSubmitListener);
            default:
                if (viewHolderLookup != null)
                    return viewHolderLookup.createViewHolderForViewType(parent, viewType);
                else
                    throw new IllegalStateException("Invalid viewType. If you added a custom field, provide a ViewHolderLookup implementation.");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BasicViewHolder<?>) {
            Field field = items.get(position);
            ((BasicViewHolder<?>) holder).bind(field);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position < items.size()
                ? items.get(position).viewType()
                : ViewType.VIEW_TYPE_SUBMIT_BUTTON;
    }

    @Override
    public int getItemCount() {
        return items.size() + (showSubmitButton ? 1 : 0);
    }
}
