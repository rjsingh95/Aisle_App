package in.example.aisle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import in.example.aisle.Network.ApiClient;
import in.example.aisle.Network.ApiService;
import in.example.aisle.Network.PrefUtils;
import in.example.aisle.Viewmodel.LoginViewModel;
import in.example.aisle.Viewmodel.OtpViewModel;
import in.example.aisle.databinding.ActivityMainBinding;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class OtpActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private CompositeDisposable mDisposable;
    private String number;
    private OtpViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_activity);
        number =getIntent().getStringExtra("number");
        EditText ed_otp=(EditText)findViewById(R.id.ed_otp);

        ImageView edit=(ImageView)findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OtpActivity.super.onBackPressed();
            }
        });
        TextView tv_otp=(TextView)findViewById(R.id.tv_number);
        LottieAnimationView loader=(LottieAnimationView) findViewById(R.id.progress);

        tv_otp.setText(number);
         homeViewModel = new ViewModelProvider(this).get(OtpViewModel.class);



        Button otp=(Button) findViewById(R.id.otp);


        homeViewModel.login.observe(this, str -> {
            Log.d("chackrrr",str);



            if (str!=null ) {


                    Intent intent = new Intent (OtpActivity.this, MainActivity.class);
                    intent.putExtra("number",str);
                    PrefUtils.storeApiKey(OtpActivity.this, str, "token");
                    startActivity(intent);
                if(LoginActivity.instance!=null){
                    LoginActivity.instance.finish();
                }
                    finish();

            }else {
            Toast.makeText(OtpActivity.this,"Failed",Toast.LENGTH_SHORT).show();

        }
            loader.setVisibility(View.INVISIBLE);
            otp.setEnabled(true);
        });
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp.setEnabled(false);
                loader.setVisibility(View.VISIBLE);
              homeViewModel.  LoginwithOtp(ed_otp.getText().toString(),number);
            }
        });

    }




}