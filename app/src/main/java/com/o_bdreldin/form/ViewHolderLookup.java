package com.o_bdreldin;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Omar Bdreldin on 10/31/2019
 */
public interface ViewHolderLookup {
    RecyclerView.ViewHolder createViewHolderForViewType(ViewGroup parent, int viewType);
}
