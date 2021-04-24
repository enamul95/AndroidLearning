package era.com.app.retrofit;

import era.com.app.model.RegistrationResponseModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("app-api/save-user")
    Call<RegistrationResponseModel> doUserRegistration(
            @Field("fullname") String fullname,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
    );



}
