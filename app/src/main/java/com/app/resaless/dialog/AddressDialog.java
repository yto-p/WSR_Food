package com.app.resaless.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.app.resaless.R;
import com.google.android.material.textfield.TextInputEditText;


public class AddressDialog extends Dialog{

    Button done, cancel;
    TextView address;
    TextInputEditText city, street, house;

    public AddressDialog(@NonNull Context context, TextView address) {
        super(context);
        this.address = address;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_address);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initViews();
        setClickListeners();

    }

    public void initViews(){
        done = findViewById(R.id.done);
        cancel = findViewById(R.id.cancel);
        city = findViewById(R.id.city);
        street = findViewById(R.id.street);
        house = findViewById(R.id.house);
    }

    public void setClickListeners(){
        cancel.setOnClickListener(v ->{
            dismiss();
        });
        done.setOnClickListener(v ->{
            address.setText(getData(city, street, house));
            dismiss();
        });
    }

    public String getData(TextInputEditText et1, TextInputEditText et2, TextInputEditText et3){
        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();
        String s3 = et3.getText().toString();
        String data = s1 + ", " + s2 + ", " + s3;

        if (!s1.trim().equals("") && !s2.trim().equals("") && !s3.trim().equals("")){
            return data.trim();
        } else {
            return "Select shipping address";
        }
    }
}