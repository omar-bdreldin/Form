package com.o_bdreldin.form.viewholder

import android.view.View
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
    private val multiAutoCompleteTextView: MultiAutoCompleteTextView
    private val chipGroup: ChipGroup
    private val chipGenerator: ChipGenerator

    init {
        multiAutoCompleteTextView = inputField as MultiAutoCompleteTextView
        chipGroup = view.findViewById(R.id.chip_group)
        chipGenerator = ChipGenerator()
    }

    override fun inputFieldId(): Int = R.id.auto_complete_text_view
    override fun inputLayoutId(): Int = R.id.text_input_layout

    override fun _bind(field: Field<MutableList<Any>>) {
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
            multiAutoCompleteTextView.adapter.getItem(position).apply {
                chipGroup.addView(chipGenerator.generateChipsForAny(field, mutableListOf(this)).first())
            }
        }
        multiAutoCompleteTextView.validator = object : AutoCompleteTextView.Validator {
            override fun isValid(charSequence: CharSequence): Boolean {
                if (field.autocompleteReferenceList() != null) {
                    for (o in field.autocompleteReferenceList()!!) {
                        if (o.toString().contentEquals(charSequence))
                            return true
                    }
                }
                return false
            }

            override fun fixText(charSequence: CharSequence): CharSequence {
                return if (field.value != null) field.value.toString() else ""
            }
        }
    }

    private inner class ChipGenerator {
        fun generateChipsForAny(field: Field<MutableList<Any>>, vararg objects: Any): List<Chip> {
            return objects.map { any: Any ->
                val chip = Chip(context)
                chip.isCloseIconVisible = true
                chip.setOnCloseIconClickListener { _ ->
                    field.value.remove(any)
                    chipGroup.removeView(chip)
                }
                chip.text = any.toString()
                chip
            }
        }
    }
}
