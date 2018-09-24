package codefarther.findapple;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends baseactivity {
    TextView tvwelocme;
    String userName;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        ConstraintLayout constraintLayout=(ConstraintLayout)findViewById(R.id.constraint_content);
        getLayoutInflater().inflate(R.layout.activity_home, constraintLayout);


        tvwelocme=(TextView)findViewById(R.id.tvwelcome);

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();

        //tvwelocme.setText("Welcome" + getIntent().getStringExtra("userName"));
        //tvwelocme.setText("welocme "+user.getEmail());

    }

}
