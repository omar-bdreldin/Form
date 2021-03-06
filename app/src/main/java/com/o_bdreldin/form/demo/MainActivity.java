package com.o_bdreldin.form.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.o_bdreldin.form.Form;
import com.o_bdreldin.form.FormFactory;
import com.o_bdreldin.form.R;
import com.o_bdreldin.form.field.AutoCompleteTextField;
import com.o_bdreldin.form.field.EmailTextField;
import com.o_bdreldin.form.field.MultipleAutoCompleteTextField;
import com.o_bdreldin.form.field.PasswordTextField;
import com.o_bdreldin.form.field.PlainTextField;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(
                new GridLayoutManager(this, 2) {{
                    setSpanSizeLookup(new SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            switch (position) {
                                case 0:
                                case 1:
                                    return 1;
                                default:
                                    return 2;
                            }
                        }
                    });
                }}
        );
        Form form = FormFactory.with(recyclerView)
                .showSubmitButton(Form::validate)
                .setFieldList(Arrays.asList(
                        new PlainTextField(R.string.hint_name, R.string.error_name_required),
                        new EmailTextField(),
                        new PasswordTextField(),
                        new AutoCompleteTextField<>(
                                R.string.hint_name,
                                R.string.error_name_required,
                                Arrays.asList("Omar", "Hamada", "Agon")
                        ),
                        new MultipleAutoCompleteTextField<>(
                                R.string.hint_name,
                                R.string.error_name_required,
                                Arrays.asList("Omar", "Hamada", "Agon")
                        )
                )).build();
        findViewById(R.id.submit_button).setOnClickListener(view -> {
            Log.e("FORM", "valid? " + form.validate());
        });
    }
}
