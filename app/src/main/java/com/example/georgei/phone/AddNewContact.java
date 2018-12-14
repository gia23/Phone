package com.example.georgei.phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNewContact extends Activity {

    Button backbtn, saveConBtn;
    EditText firstname, midname, lastname, phone, email, address;
    Spinner spnr;
    String eName, ePhone, eEmail, eAddress;
    int id_To_Update = 0;
    DBHelper phoneDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_newcontact);

        backbtn = (Button)findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNewContact.this, MainActivity.class);
                startActivity(intent);
                onBackPressed();
                finish();
            }
        });

        firstname = (EditText)findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phone);
        spnr = (Spinner)findViewById(R.id.spnr);
        email = (EditText)findViewById(R.id.eMail);
        address = (EditText)findViewById(R.id.address);
        saveConBtn = (Button)findViewById(R.id.savebtn);
        saveConBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddNewContact.this, ViewContact.class);
                eName = firstname.getText().toString();
                ePhone = phone.getText().toString();
                eEmail = email.getText().toString();
                eAddress = address.getText().toString();

                intent.putExtra("firstname", eName);
                intent.putExtra("phone", ePhone);
                intent.putExtra("email", eEmail);
                intent.putExtra("address", eAddress);
                startActivity(intent);
                finish();
            }
        });

        String[] obj = {"Mobile", "Home", "Work"};
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, obj);
        spnr.setAdapter(adapter);


    }

    public void run(View view){
        Bundle extras = getIntent().getExtras();
        if (extras !=null){
            int Value = extras.getInt("id");
            if (Value>=0){
                if (phoneDB.updateContact(id_To_Update, firstname.getText().toString(), phone.getText().toString(),
                        email.getText().toString(), address.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Not updated", Toast.LENGTH_SHORT).show();
                }
            }else {
                if (phoneDB.insertContact(firstname.getText().toString(), phone.getText().toString(),
                        email.getText().toString(), address.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Not done", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
