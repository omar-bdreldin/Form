package com.o_bdreldin.form.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.o_bdreldin.form.R;
import com.o_bdreldin.form.ViewType;
import com.o_bdreldin.form.viewholder.BasicViewHolder;
import com.o_bdreldin.form.viewholder.TextFieldViewHolder;

/**
 * Created by Omar Bdreldin on 9/29/2019
 */
public class ViewHolderBuilder {

    private final FieldStyle fieldStyle;

    public ViewHolderBuilder(FieldStyle fieldStyle) {
        this.fieldStyle = fieldStyle;
    }

    @Nullable
    public BasicViewHolder<?> createViewHolder(Context context, LayoutInflater layoutInflater,
                                               ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case ViewType.VIEW_TYPE_TEXT:
                return createViewHolderForViewTypeText(context, layoutInflater, viewGroup);
            case ViewType.VIEW_TYPE_AUTO_COMPLETE:
            case ViewType.VIEW_TYPE_MULTIPLE_AUTO_COMPLETE:
            default:
                return null;
        }
    }

    private BasicViewHolder<?> createViewHolderForViewTypeText(Context context
            , LayoutInflater layoutInflater, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.view_holder_text, parent, false);
        TextFieldViewHolder viewHolder = new TextFieldViewHolder(view);
        applyFieldStyleForViewTypeText(viewHolder, context);
        return viewHolder;
    }

    private void applyFieldStyleForViewTypeText(TextFieldViewHolder viewHolder, Context context) {
        if (fieldStyle != null) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                    viewHolder.inputLayout.getLayoutParams();
            int margin = (int) context.getResources().getDimension(fieldStyle.verticalSpacing);
            params.setMargins(0, margin, 0, margin);
            viewHolder.inputLayout.setLayoutParams(params);
            if (fieldStyle.style != null) {
                // TODO: no way of setting style programmatically!
            }
        }
    }
}
