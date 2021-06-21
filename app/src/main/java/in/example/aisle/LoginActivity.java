package in.example.aisle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Observable;

import in.example.aisle.Network.ApiClient;
import in.example.aisle.Network.ApiService;
import in.example.aisle.Viewmodel.LoginViewModel;
import in.example.aisle.databinding.ActivityMainBinding;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {


   public static LoginActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        EditText ed_number=(EditText)findViewById(R.id.ed_number);
        Button login=(Button) findViewById(R.id.login);
        LottieAnimationView loader=(LottieAnimationView) findViewById(R.id.progress);

        LoginViewModel homeViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ed_number.getText().toString().length() == 10) {
                    login.setEnabled(false);
                    loader.setVisibility(View.VISIBLE);
                    homeViewModel. Login("+91"+ed_number.getText().toString());


                } else {
                    ed_number.setError("Enter correct digit number");
                }

            }
        });

        homeViewModel.login.observe(this, str -> {
            Log.d("chackrrr",str);
            if (str!=null ) {
                 if(str.equals("true")){
                Intent intent = new Intent (LoginActivity.this, OtpActivity.class);
                intent.putExtra("number","+91"+ed_number.getText().toString());

                startActivity(intent);

                 }else {
                     Toast.makeText(LoginActivity.this,"Failed",Toast.LENGTH_SHORT).show();

                 }
            }
            loader.setVisibility(View.INVISIBLE);
            login.setEnabled(true);
        });





    }



}