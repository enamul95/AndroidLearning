package era.com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import era.com.app.dao.DBHelpler;
import era.com.app.model.RegistrationModel;

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

                RegistrationModel model = new RegistrationModel();
                model.setFullName(etName.getText().toString());
                model.setEamil(etEmail.getText().toString());
                model.setPhone(etPhone.getText().toString());
                model.setPassword(etPassword.getText().toString());

                long l = dbHelpler.doInserReg(model);
                Toast.makeText(getApplicationContext(),"Insert Data "+l,Toast.LENGTH_LONG).show();

            }
        });


    }
}