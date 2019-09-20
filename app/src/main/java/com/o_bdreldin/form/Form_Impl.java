package com.o_bdreldin.form;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Omar Bdreldin on 8/24/2019
 */
public final class Form_Impl implements Form {

    private final FormBuilder builder;

    public Form_Impl(FormBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Boolean validate() {
        return builder.validate();
    }
}
