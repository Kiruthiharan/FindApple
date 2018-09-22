package codefarther.findapple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    TextView tvwelocme;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvwelocme=(TextView)findViewById(R.id.tvwelcome);


        tvwelocme.setText("Welcome" + getIntent().getStringExtra("userName"));

    }
}
