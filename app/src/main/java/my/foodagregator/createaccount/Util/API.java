package my.foodagregator.createaccount.Util;

import android.view.View;

import my.foodagregator.createaccount.POJO.User;
import my.foodagregator.createaccount.POJO.Verify;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    @POST("api/register")
    Call<Void> ceateUser(@Body User user);

    @POST("api/verify")
    Call<Verify> verify(@Body Verify verify);

}
