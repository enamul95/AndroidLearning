package era.com.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText etUsrName;
    EditText etPassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etUsrName = findViewById(R.id.etUsrName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // got other activity or valiation
                //  Intent intent = new Intent(MainActivity.this,Welcome.class);
                // startActivity(intent);
                //  Log.e("Login clicke error ","successfullu pressed");
                //Log.w("Login button waringin","successfullu pressed");

                if(etUsrName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter user name.",Toast.LENGTH_LONG).show();
                }else if(etPassword.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please Enter password",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "User name :"+etUsrName.getText().toString()+" Password :"+etPassword.getText().toString(), Toast.LENGTH_SHORT).show();

                    Log.e("user name :",etUsrName.getText().toString());
                    Log.e("Password :",etPassword.getText().toString());

                     //got other activity or valiation
                      Intent intent = new Intent(MainActivity.this,Welcome.class);
                      intent.putExtra("user_name",etUsrName.getText().toString());
                      startActivity(intent);
                }

            }
        });

    }
}