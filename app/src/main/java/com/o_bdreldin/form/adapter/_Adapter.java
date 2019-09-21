package com.o_bdreldin.form.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.o_bdreldin.form.R;
import com.o_bdreldin.form.ViewType;
import com.o_bdreldin.form.field.Field;
import com.o_bdreldin.form.viewholder.AutoCompleteTextFieldViewHolder;
import com.o_bdreldin.form.viewholder.BasicViewHolder;
import com.o_bdreldin.form.viewholder.MultipleAutoCompleteTextFieldViewHolder;
import com.o_bdreldin.form.viewholder.TextFieldViewHolder;

import java.util.List;

/**
 * Created by Omar Bdreldin on 9/2/2019
 */
public class _Adapter extends RecyclerView.Adapter<BasicViewHolder<?>> {

    private final List<? extends Field<?>> items;
    private boolean showSubmitButton;

    public _Adapter(@NonNull List<? extends Field<?>> items) {
        this.items = items;
    }

    public _Adapter(List<? extends Field<?>> items, Boolean showSubmitButton) {
        this.items = items;
        this.showSubmitButton = showSubmitButton;
    }

    @NonNull
    @Override
    public BasicViewHolder<?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ViewType.VIEW_TYPE_TEXT:
                view = layoutInflater.inflate(R.layout.view_holder_text, parent, false);
                return new TextFieldViewHolder(view);
            case ViewType.VIEW_TYPE_AUTO_COMPLETE:
                view = layoutInflater.inflate(R.layout.view_holder_autocomplete, parent, false);
                return new AutoCompleteTextFieldViewHolder(view);
            case ViewType.VIEW_TYPE_MULTIPLE_AUTO_COMPLETE:
                view = layoutInflater.inflate(R.layout.view_holder_multiple_autocomplete, parent, false);
                return new MultipleAutoCompleteTextFieldViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BasicViewHolder<?> holder, int position) {
        Field field = items.get(position);
        holder.bind(field);
    }

    @Override
    public int getItemViewType(int position) {
        return position < items.size()
                ? items.get(position).viewType()
                : 0; // TODO: viewType for submit button
    }

    @Override
    public int getItemCount() {
        return items.size() + (showSubmitButton ? 1 : 0);
    }
}
