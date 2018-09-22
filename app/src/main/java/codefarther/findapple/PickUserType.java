package codefarther.findapple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PickUserType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_user_type);
    }

    public void faReg(View view) {
        Intent intent=new Intent(this,SignUpFA.class);
        startActivity(intent);
    }

    public void patReg(View view) {
        Intent intent=new Intent(this,SignUpPat.class);
        startActivity(intent);
    }
}
