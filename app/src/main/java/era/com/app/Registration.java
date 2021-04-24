package era.com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import era.com.app.dao.DBHelpler;
import era.com.app.model.RegistrationModel;
import era.com.app.model.RegistrationResponseModel;
import era.com.app.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
    EditText etName;
    EditText etEmail;
    EditText etPhone;
    EditText etPassword;
    Button btnRegistration;

    DBHelpler dbHelpler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        btnRegistration = findViewById(R.id.btnRegistration);

        dbHelpler = new DBHelpler(this);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doInsert();

               /* RegistrationModel model = new RegistrationModel();
                model.setFullName(etName.getText().toString());
                model.setEamil(etEmail.getText().toString());
                model.setPhone(etPhone.getText().toString());
                model.setPassword(etPassword.getText().toString());

                if(dbHelpler.isExist(model)){
                    Toast.makeText(getApplicationContext(),"Mobile Number Already Exist!",Toast.LENGTH_LONG).show();
                }else{
                    long l = dbHelpler.doInserReg(model);
                    if(l>0){
                        Toast.makeText(getApplicationContext(),"Data Save successfully",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Insert Failed",Toast.LENGTH_LONG).show();
                    }

                }
                */




            }
        });


    }

    private void doInsert(){
        ApiService apiService = new ApiService();
        RegistrationModel model = new RegistrationModel();
        model.setFullName(etName.getText().toString());
        model.setEamil(etEmail.getText().toString());
        model.setPhone(etPhone.getText().toString());
        model.setPassword(etPassword.getText().toString());
        Call<RegistrationResponseModel> call = apiService.doUserRegistration(model);

        call.enqueue(new Callback<RegistrationResponseModel>() {
            @Override
            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                if(response.body() !=null){
                    RegistrationResponseModel outResponseModel= response.body();
                    if(outResponseModel !=null){
                        Toast.makeText(getApplicationContext(),outResponseModel.getOutMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponseModel> call, Throwable t)
            {
                Log.e("registraito error-->",t.getMessage());
            }
        });
    }
}