package codefarther.findapple;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPat extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPass;
    private Button btnsignUp;
    private EditText etfName,etlName,etphone,etemergPhone,etDOB;


    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpPat";
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_pat);

        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();


        progressDialog=new ProgressDialog(this);
        etEmail=(EditText)findViewById(R.id.etuEmail);
        etPass=(EditText)findViewById(R.id.etuPass);
        etfName=(EditText)findViewById(R.id.etfName);
        etlName=(EditText)findViewById(R.id.etlName);
        etphone=(EditText)findViewById(R.id.etphone);
        etemergPhone=(EditText)findViewById(R.id.etemergPhone);
        etDOB=(EditText)findViewById(R.id.etDOB);

        btnsignUp=(Button)findViewById(R.id.btnsignup);

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    public void signUp(View view) {
        registerUser();
    }

    private void saveUserInfo(FirebaseUser user){
        String fName=etfName.getText().toString().trim();
        String lName=etlName.getText().toString().trim();
        int phone=Integer.parseInt(etphone.getText().toString().trim());
        int emergphone=Integer.parseInt(etemergPhone.getText().toString().trim());
        String email=etEmail.getText().toString().trim();
        String DOB=etDOB.getText().toString().trim();

        PatUserInformation patUserInformation=new PatUserInformation(email,fName,lName,phone,emergphone,DOB);
        databaseReference.child(user.getUid()).setValue(patUserInformation);
        Toast.makeText(this,"User Updates",Toast.LENGTH_SHORT).show();
        updateUI(user);
    }

    private void registerUser() {
        String email=etEmail.getText().toString().trim();
        String password=etPass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter your Email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter a Password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering User");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(SignUpPat.this, "Registered Succesfulyl",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            saveUserInfo(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpPat.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }

    private void updateUI(FirebaseUser user) {
        String username=user.getEmail();
        Intent intent=new Intent(this,Home.class);
        intent.putExtra("userName",username);
        startActivity(intent);
    }

}
