package era.com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class Welcome extends AppCompatActivity {

    TextView tv_full_name;
    TextView tv_mobile;
    TextView tv_email;

    DBHelpler dbHelpler;
    String mobileNumer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        tv_full_name = findViewById(R.id.tv_full_name);
        tv_mobile = findViewById(R.id.tv_mobile);
        tv_email = findViewById(R.id.tv_email);


        dbHelpler = new DBHelpler(this);

        Intent i = getIntent();
         mobileNumer = i.getStringExtra("mobileNumer");

         /*
        RegistrationModel outModel = dbHelpler.getData(mobileNumer);

        tv_full_name.setText(outModel.getFullName());
        tv_mobile.setText(outModel.getPhone());
        tv_email.setText(outModel.getEamil());

        */

       // Toast.makeText(this, outModel.getFullName(), Toast.LENGTH_SHORT).show();


        // Log.e("mobileNumer-->",mobileNumer);

        //  lb_user_name.setText(username);
        getUser();

    }


    private void getUser(){
        ApiService apiService = new ApiService();

        RegistrationModel model = new RegistrationModel();
        model.setPhone(mobileNumer);
        Call<RegistrationResponseModel> call = apiService.getUserByPhone(model);
        call.enqueue(new Callback<RegistrationResponseModel>() {
            @Override
            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                if(response.body() !=null){
                    RegistrationResponseModel outResponseModel= response.body();
                 String fullName=   CheckNetwork.getRetrofit_NullCheck(outResponseModel.getFullname());
                    tv_full_name.setText(fullName);
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {

            }
        });
    }


}