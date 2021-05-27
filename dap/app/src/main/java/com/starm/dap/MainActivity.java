package com.starm.dap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private TextView recoverPsd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn=(Button) findViewById(R.id.login_button);
        recoverPsd=(TextView) findViewById(R.id.link_recover);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent todashboard=new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(todashboard);
                finish();
            }
        });
        recoverPsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent torecover=new Intent(MainActivity.this,RecoverPasswordActivity.class);
                startActivity(torecover);
                finish();
            }
        });
    }
}