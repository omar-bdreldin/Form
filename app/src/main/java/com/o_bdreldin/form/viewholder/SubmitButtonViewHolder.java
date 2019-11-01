package com.o_bdreldin.form.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.o_bdreldin.form.OnFormSubmitListener;
import com.o_bdreldin.form.R;

/**
 * Created by Omar Bdreldin on 11/1/2019
 */
public class SubmitButtonViewHolder extends RecyclerView.ViewHolder {

    public SubmitButtonViewHolder(View itemView, Runnable onFormSubmitListener) {
        super(itemView);
        MaterialButton button = itemView.findViewById(R.id.submit_button);
        button.setOnClickListener(view -> onFormSubmitListener.run());
    }

    public static SubmitButtonViewHolder create(ViewGroup parent
            , Runnable onFormSubmitListener) {
        return new SubmitButtonViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.view_holder_submit_button,
                        parent,
                        false
                ),
                onFormSubmitListener
        );
    }
    public SubmitButtonViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
