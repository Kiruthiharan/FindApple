package codefarther.findapple;

import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.widget.FrameLayout;

public class Test extends baseactivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        ConstraintLayout constraintLayout=(ConstraintLayout)findViewById(R.id.constraint_content);
        getLayoutInflater().inflate(R.layout.activity_test, constraintLayout);



    }
}
