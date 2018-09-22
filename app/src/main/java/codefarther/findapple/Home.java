package codefarther.findapple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
    TextView tvwelocme;
    String userName;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();

        tvwelocme=(TextView)findViewById(R.id.tvwelcome);
        //tvwelocme.setText("Welcome" + getIntent().getStringExtra("userName"));
        tvwelocme.setText("welocme "+user.getEmail());

    }
}
