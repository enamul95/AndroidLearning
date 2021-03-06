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
import android.widget.TextView;
import android.widget.Toast;

import era.com.app.dao.DBHelpler;
import era.com.app.model.RegistrationModel;
import era.com.app.model.RegistrationResponseModel;
import era.com.app.retrofit.ApiService;
import era.com.app.util.CheckNetwork;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    EditText etUsrName;
    EditText etPassword;
    Button btnLogin;
    TextView lb_register;
    TextView lb_user_info;

    TextView tv_mobile;


    private DBHelpler dbHelpler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etUsrName = findViewById(R.id.etUsrName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        lb_register = findViewById(R.id.lb_register);
        lb_user_info = findViewById(R.id.lb_user_info);

        tv_mobile = findViewById(R.id.tv_mobile);

        dbHelpler = new DBHelpler(this);




        tv_mobile.setText("মোবাইল নম্বর");



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
                    doLogin();
/*
                    RegistrationModel model = new RegistrationModel();
                    model.setPhone(etUsrName.getText().toString());
                    model.setPassword(etPassword.getText().toString());

                    long l = dbHelpler.doLogin(model);

                    if(l>0){
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this,Welcome.class);
                        intent.putExtra("mobileNumer",etUsrName.getText().toString());
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid User or Password", Toast.LENGTH_SHORT).show();
                    }

                    */

                    //Toast.makeText(MainActivity.this, "User name :"+etUsrName.getText().toString()+" Password :"+etPassword.getText().toString(), Toast.LENGTH_SHORT).show();

                   // Log.e("user name :",etUsrName.getText().toString());
                   // Log.e("Password :",etPassword.getText().toString());

                     //got other activity or valiation
                      //Intent intent = new Intent(MainActivity.this,Welcome.class);
                      //intent.putExtra("user_name",etUsrName.getText().toString());
                      //startActivity(intent);
                }

            }
        });

        lb_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });

        lb_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ViewUserInfo.class);
                startActivity(intent);
            }
        });

    }

    private void doLogin(){

        ApiService apiService = new ApiService();

        RegistrationModel model = new RegistrationModel();
        model.setPhone(etUsrName.getText().toString());
        model.setPassword(etPassword.getText().toString());

        Call<RegistrationResponseModel> call = apiService.doLogin(model);

        call.enqueue(new Callback<RegistrationResponseModel>() {
            @Override
            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {

                if(response.body() !=null){
                    RegistrationResponseModel outResponseModel= response.body();
                  String outCode= CheckNetwork.getRetrofit_NullCheck(outResponseModel.getOutCode());
                  String outMessage= CheckNetwork.getRetrofit_NullCheck(outResponseModel.getOutMessage());
                    //Toast.makeText(MainActivity.this, outMessage, Toast.LENGTH_SHORT).show();
                    if("0".equals(outCode)){
                        Intent intent = new Intent(MainActivity.this,Welcome.class);
                        intent.putExtra("mobileNumer",etUsrName.getText().toString());
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, outMessage, Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
              Log.e("login error",t.getMessage());
            }
        });
    }
}