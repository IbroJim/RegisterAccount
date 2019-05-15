package my.foodagregator.createaccount.Account;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import my.foodagregator.createaccount.R;


public class MainActivity extends AppCompatActivity {

    private Fragment fragmentRegistry,fragmentCode;
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFragment();
    }

    private void setupFragment(){
        fragmentRegistry=new FragmentRegistry();
        fragmentCode=new FragmentCode();
        fm=getSupportFragmentManager();
        fm.beginTransaction().add(R.id.containerFragment,fragmentRegistry).commit();
    }

    public void nextFragment(String string){
        Bundle bundle = new Bundle();
        bundle.putString(FragmentCode.KEY, string);
        fragmentCode.setArguments(bundle);
        fm.beginTransaction().replace(R.id.containerFragment,fragmentCode).commit();
    }


}
