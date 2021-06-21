package in.example.aisle.Network;


import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    @POST("phone_number_login")
    @FormUrlEncoded
    Single<JsonObject> login(
            @Field("number") String number
    );

    @POST("verify_otp")
         @FormUrlEncoded
    Single<JsonObject> OtpLogin(
            @Field("number") String num,
            @Field("otp") String otp
    );

    @GET("test_profile_list")

    Single<JsonObject> getProfileList(

    );





}