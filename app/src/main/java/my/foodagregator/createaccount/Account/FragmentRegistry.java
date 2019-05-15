package my.foodagregator.createaccount.Account;

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
import java.util.ArrayList;
import java.util.List;

import my.foodagregator.createaccount.Util.API;
import my.foodagregator.createaccount.POJO.User;
import my.foodagregator.createaccount.R;
import my.foodagregator.createaccount.Util.Client;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentRegistry extends Fragment {

    private Button register;
    private EditText firstName, lastName;
    private EditText phone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_registry,container,false);
        setupView(view);
        return view;
    }

    private void setupView(View view){
        register=view.findViewById(R.id.register);
        firstName=view.findViewById(R.id.edit_first_name);
        lastName=view.findViewById(R.id.edit_last_name);
        phone= view.findViewById(R.id.phone_input);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataUser();
            }
        });
    }


    private void queryUser(User user){
        Call<Void> call= Client.getApi().ceateUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                Log.d("TAG","response"+response.code());
                if(response.code()==200){

                }else
                    Toast.makeText(getActivity(),"произошла ошибка",Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("TAG","response"+t);
            }
        });
    }
    private void getDataUser(){
        final List<String> roles=new ArrayList<>();
        roles.add("ROLE_USER");
              String phoneStr= phone.getText().toString();
              String lastNameStr= lastName.getText().toString();
              String firstNameStr=firstName.getText().toString();
        if(firstNameStr.length()>1 && lastNameStr.length()>1 && phoneStr.length()>5) {
             queryUser(new User(phone.getText().toString(), roles, lastName.getText().toString(), firstName.getText().toString()));
            ((MainActivity)getActivity()).nextFragment(phoneStr);
        }else {
            Toast.makeText(getActivity(),"Заполните поля",Toast.LENGTH_LONG).show();
        }


    }
}
