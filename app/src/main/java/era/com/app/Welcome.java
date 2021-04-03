package era.com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

TextView lb_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        lb_user_name = findViewById(R.id.lb_user_name);

        Intent i = getIntent();
        String username  = i.getStringExtra("user_name");

        lb_user_name.setText(username);


    }
}