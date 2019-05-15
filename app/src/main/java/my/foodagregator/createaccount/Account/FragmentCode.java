package my.foodagregator.createaccount.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import my.foodagregator.createaccount.Util.API;
import my.foodagregator.createaccount.POJO.Verify;
import my.foodagregator.createaccount.R;
import my.foodagregator.createaccount.Util.Client;
import my.foodagregator.createaccount.WelcomeActivity;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentCode extends Fragment {
    public    static final String KEY = "phone";

    private EditText editCode;
    private Button confirm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_code,container,false);
        setupView(view);
        return view;
    }
    private void setupView(View view){
        editCode=view.findViewById(R.id.edit_code);
        confirm=view.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmCode();
            }
        });
    }
    private String getPhoneNumber(){
        String phone = getArguments().getString(KEY);
         return phone;
    }
    private void confirmCode(){
        String code=editCode.getText().toString();
        if(code.length()==6){
            Log.d("TAG"," "+code);
            Log.d("TAG"," "+getPhoneNumber());
                 queryVieify(new Verify(getPhoneNumber(),code));
        }else {
            Toast.makeText(getActivity(),"Шестизначный пароль",Toast.LENGTH_LONG).show();
        }
    }
    private void queryVieify(Verify verify){
        Call<Verify> verifyCall= Client.getApi().verify(verify);
        verifyCall.enqueue(new Callback<Verify>() {
            @Override
            public void onResponse(Call<Verify> call, retrofit2.Response<Verify> response) {
                Log.d("TAG"," "+response.code());
                if(response.code()==200){
                    Intent intent=new Intent(getActivity(), WelcomeActivity.class);
                    startActivity(intent);
                }else {Toast.makeText(getActivity(),"проверьте код",Toast.LENGTH_LONG).show();}
            }

            @Override
            public void onFailure(Call<Verify> call, Throwable t) {

            }
        });

    }
}
