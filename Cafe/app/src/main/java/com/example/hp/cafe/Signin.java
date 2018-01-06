package com.example.hp.cafe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hp.cafe.Common.Common;
import com.example.hp.cafe.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Signin extends AppCompatActivity {

    EditText edtphone,edtpassword;
    Button btnsignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

    edtpassword=(MaterialEditText)findViewById(R.id.edtPass);
        edtphone=(MaterialEditText)findViewById(R.id.edtPhone);
        btnsignin=(Button)findViewById(R.id.buttonsignin);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
       final DatabaseReference table_user=database.getReference("user");

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Common.isConnectedtoInternet(getBaseContext())) {
                final ProgressDialog mdialog = new ProgressDialog(Signin.this);
                mdialog.setMessage("Please Wait");
                mdialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if user not exist
                        if (dataSnapshot.child(edtphone.getText().toString()).exists()) {

                            //get user info
                            mdialog.dismiss();

                            User user = dataSnapshot.child(edtphone.getText().toString()).getValue(User.class);
                            user.setPhone(edtphone.getText().toString());//set phone

                            if (user.getPassword().equals(edtpassword.getText().toString()))
                            {
                                Intent homeintent = new Intent(Signin.this, Home.class);
                                Common.CurrentUser = user;

                                startActivity(homeintent);
                                finish();
                            }
                            else {
                                Toast.makeText(Signin.this, "Wrong Password !", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mdialog.dismiss();
                            Toast.makeText(Signin.this, "User not Exist !", Toast.LENGTH_SHORT).show();
                        }
                        if(edtphone==null && edtpassword==null)
                        {
                            Toast.makeText(Signin.this, "Wrong Password !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

             else
            {

                Toast.makeText(Signin.this, "Please check your Connection !!", Toast.LENGTH_SHORT).show();
            }




        }


    });
    }
}

