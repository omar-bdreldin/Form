package com.o_bdreldin.form.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import com.google.android.material.chip.Chip

import com.google.android.material.chip.ChipGroup
import com.o_bdreldin.form.R
import com.o_bdreldin.form.field.Field
import java.util.*

/**
 * Created by Omar Bdreldin on 9/21/2019
 */
class MultipleAutoCompleteTextFieldViewHolder(view: View) : BasicViewHolder<MutableList<Any>>(view) {
    companion object {
        fun create(parent: ViewGroup) : MultipleAutoCompleteTextFieldViewHolder =
            MultipleAutoCompleteTextFieldViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_holder_multiple_autocomplete,
                    parent,
                    false
                )
            )
    }
    private val multiAutoCompleteTextView: MultiAutoCompleteTextView = inputField as MultiAutoCompleteTextView
    private val chipGroup: ChipGroup = view.findViewById(R.id.chip_group)
    private val chipGenerator: ChipGenerator

    init {
        chipGenerator = ChipGenerator()
    }

    override fun inputFieldId(): Int = R.id.auto_complete_text_view
    override fun inputLayoutId(): Int = R.id.text_input_layout

    override fun _bind(field: Field<MutableList<Any>>) {
        multiAutoCompleteTextView.setText("")
        when (field.status) {
            Field.Status.NONE, Field.Status.SET -> inputLayout.error = null
            Field.Status.VALIDATED -> {
                when (field.valid) {
                    Field.Valid.IS_VALID -> inputLayout.error = null
                    Field.Valid.IS_INVALID, Field.Valid.IS_NULL -> inputLayout.error = context.getString(field.errorRequiredStringRes())
                }
            }
        }
        multiAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        chipGroup.removeAllViews()
        chipGenerator.generateChipsForAny(field, field.value).forEach {
            chipGroup.addView(it)
        }
    }

    override fun _setListeners(field: Field<MutableList<Any>>) {
        multiAutoCompleteTextView.setAdapter<ArrayAdapter<*>>(
            ArrayAdapter<Any>(
                context,
                android.R.layout.simple_dropdown_item_1line,
                field.autocompleteReferenceList()!!
            )
        )
        multiAutoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            if (!field.value.contains(multiAutoCompleteTextView.adapter.getItem(position))) {
                multiAutoCompleteTextView.adapter.getItem(position).apply {
                    chipGroup.addView(chipGenerator.generateChipsForAny(field, mutableListOf(this)).first())
                    field.value.add(this)
                }
            }
            multiAutoCompleteTextView.setText("")
        }
    }

    private inner class ChipGenerator {
        fun generateChipsForAny(field: Field<MutableList<Any>>, objects: MutableList<Any>): List<Chip> {
            return objects.map { any: Any ->
                val chip = Chip(context)
                chip.isCloseIconVisible = true
                chip.setOnCloseIconClickListener {
                    field.value.remove(any)
                    chipGroup.removeView(chip)
                }
                chip.text = any.toString()
                chip
            }
        }
    }
}
