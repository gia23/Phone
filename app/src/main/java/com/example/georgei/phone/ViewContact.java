package com.example.georgei.phone;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewContact extends Activity {

    TextView tname, tPhone, tEmail, tAddress;
    String sname, sphone, semail, saddress;
    Button backbtn;
    DBHelper phoneDB;
    int id_To_Update = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_contact);

        tname = (TextView)findViewById(R.id.txtname);
        tPhone = (TextView)findViewById(R.id.txtphone);
        tEmail = (TextView)findViewById(R.id.txtemail);
        tAddress = (TextView)findViewById(R.id.txtaddress);

/*        sname = getIntent().getExtras().getString("firstname");
        tname.setText(sname);
        sphone = getIntent().getExtras().getString("phone");
        tPhone.setText(sphone);
        semail = getIntent().getExtras().getString("email");
        tEmail.setText(semail);
        saddress = getIntent().getExtras().getString("address");
        tAddress.setText(saddress);
*/
        phoneDB = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            int Value = extras.getInt("id");

            if (Value>0){
                //Means this is the view part not add contact part
                Cursor res = phoneDB.getData(Value);
                id_To_Update = Value;
                res.moveToFirst();

                sname = res.getString(res.getColumnIndex(DBHelper.CONTACTS_COLUMN_NAME));
                sphone = res.getString(res.getColumnIndex(DBHelper.CONTACTS_COLUMN_PHONE));
                semail = res.getString(res.getColumnIndex(DBHelper.CONTACTS_COLUMN_EMAIL));
                saddress = res.getString(res.getColumnIndex(DBHelper.CONTACTS_COLUMN_ADDRESS));

                if (!res.isClosed()){
                    res.close();
                }

            }
        }


        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewContact.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }
}
