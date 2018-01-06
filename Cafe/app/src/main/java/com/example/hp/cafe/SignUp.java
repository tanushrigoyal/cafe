package com.example.hp.cafe;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hp.cafe.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtphone,edtName,edtpassword;
    Button btnsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

 edtphone=(MaterialEditText)findViewById(R.id.edtPhone);
        edtpassword=(MaterialEditText)findViewById(R.id.edtPass);
        edtName=(MaterialEditText)findViewById(R.id.edtName);

        btnsignup=(Button)findViewById(R.id.buttonsignup);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("user");

   btnsignup.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           final ProgressDialog mdialog= new ProgressDialog(SignUp.this);
           mdialog.setMessage("Please Wait");
           mdialog.show();

           table_user.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   //check if already user phone
                   if (dataSnapshot.child(edtphone.getText().toString()).exists())


                   {
                       mdialog.dismiss();
                       Toast.makeText(SignUp.this, "Number Already Registered !", Toast.LENGTH_SHORT).show();

                   } else {
                       mdialog.dismiss();
                       User user = new User(edtName.getText().toString(),edtpassword.getText().toString());
                       table_user.child(edtphone.getText().toString()).setValue(user);
                       Toast.makeText(SignUp.this, "Sign Up Completed !", Toast.LENGTH_SHORT).show();
                   finish();
                   }
               }
               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });


           }

   });
    }
}
