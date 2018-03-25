package com.brekhov1.contactshw;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText name, birthDate, phoneNr;
    private Button save, cancel, delete;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        int id = getIntent().getIntExtra("contact",0);
        final ContactHelper contactHelper = new ContactHelper(getApplicationContext());
        contact = contactHelper.getContact(id);
        name = findViewById(R.id.editName);
        phoneNr = findViewById(R.id.editPhoneNr);
        birthDate = findViewById(R.id.editBirthDate);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        delete = findViewById(R.id.delete);

        name.setText(contact.getName());
        phoneNr.setText(contact.getPhoneNr());
        birthDate.setText(contact.getBirthDate());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContactHelper contactHelper = new ContactHelper(getApplicationContext());
                contact.setName(name.getText().toString());
                contact.setPhoneNr(phoneNr.getText().toString());
                contact.setBirthDate(birthDate.getText().toString());
                contactHelper.update(contact);

                finish();
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactHelper contactHelper = new ContactHelper(getApplicationContext());
                contactHelper.delete(contact.getId());

                finish();
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}