package my.foodagregator.createaccount.Util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private   static final String BASE_URL="https://mighty-lowlands-86210.herokuapp.com/";


    private static Retrofit getRetrofit(){
        OkHttpClient.Builder okHttpClient=new OkHttpClient.Builder();
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original=chain.request();
                Request request=original.newBuilder()
                        .addHeader("Accept","application/json").method(original.method(),original.body()).build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient.build()).build();
            return  retrofit;
    }

    public static API getApi(){
        API api=getRetrofit().create(API.class);
        return api;
    }


}
