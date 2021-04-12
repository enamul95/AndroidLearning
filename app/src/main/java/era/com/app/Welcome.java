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

public class Welcome extends AppCompatActivity {

    TextView tv_full_name;
    TextView tv_mobile;
    TextView tv_email;

    DBHelpler dbHelpler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        tv_full_name = findViewById(R.id.tv_full_name);
        tv_mobile = findViewById(R.id.tv_mobile);
        tv_email = findViewById(R.id.tv_email);


        dbHelpler = new DBHelpler(this);

        Intent i = getIntent();
        String mobileNumer = i.getStringExtra("mobileNumer");

        RegistrationModel outModel = dbHelpler.getData(mobileNumer);

        tv_full_name.setText(outModel.getFullName());
        tv_mobile.setText(outModel.getPhone());
        tv_email.setText(outModel.getEamil());

       // Toast.makeText(this, outModel.getFullName(), Toast.LENGTH_SHORT).show();


        // Log.e("mobileNumer-->",mobileNumer);

        //  lb_user_name.setText(username);


    }
}