package com.o_bdreldin.form;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Omar Bdreldin on 8/25/2019
 */
public final class FormFactory {
    public static FormBuilder with(RecyclerView recyclerView) {
        if (recyclerView == null)
            throw new IllegalArgumentException("RecyclerView can't be null.");
        else return new FormBuilder_Impl(recyclerView);
    }
}
