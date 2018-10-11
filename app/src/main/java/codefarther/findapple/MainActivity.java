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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPass;
    private Button btnSignIn;
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPass=(EditText)findViewById(R.id.etPass);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        mAuth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            startActivity(new Intent(this,Home.class));
        }

    }

    public void signIn(View view) {
        verifyUser();
    }

    private void verifyUser() {
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

        progressDialog.setMessage("Signing in");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(MainActivity.this, "Welcome",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            finish();
                            updateUI(user);
                        }
                        else{
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void signUp(View view) {
        Intent intent=new Intent(this,PickUserType.class);
        startActivity(intent);
    }

    private void updateUI(FirebaseUser user) {
        String username=user.getEmail();
        Intent intent=new Intent(this,Home.class);
        intent.putExtra("userName",username);
        startActivity(intent);
    }
}
