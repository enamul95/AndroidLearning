package era.com.app.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import era.com.app.model.RegistrationModel;
import era.com.app.model.RegistrationResponseModel;
import era.com.app.util.IPConfigure;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static final String BASE_URL = IPConfigure.getIP();

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();

    Api api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build().create(Api.class);

    public Call<RegistrationResponseModel> doUserRegistration(RegistrationModel model) {
        return api.doUserRegistration(
                model.getFullName(),
                model.getEamil(),
                model.getPhone(),
                model.getPassword()
        );
    }

    public Call<RegistrationResponseModel> doLogin(RegistrationModel model) {
        return api.doLogin(model);
    }

    public Call<RegistrationResponseModel> getUserByPhone(RegistrationModel model) {
        return api.getUserByPhone(model);
    }
}
