package in.example.aisle.Viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import in.example.aisle.Network.ApiClient;
import in.example.aisle.Network.ApiService;
import in.example.aisle.Network.PrefUtils;

import in.example.aisle.model.ProfileModel;
import in.example.aisle.modellikes.LikesResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends AndroidViewModel {


    private final ApiService apiService;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public MutableLiveData<ArrayList<ProfileModel>> peoplelist = new MutableLiveData<>();
    public MutableLiveData<LikesResponse> likes = new MutableLiveData<>();

    public HomeViewModel(@NonNull @NotNull Application application) {

        super(application);
        String token = PrefUtils.getApiKey(application, "token").toString();
         apiService = new  ApiClient().getClient2(getApplication(),token).create( ApiService.class);

    }
    public void getProfileList() {
       mDisposable.add(apiService.getProfileList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<JsonObject>() {
                    @Override
                    public void onSuccess(@NotNull JsonObject response) {
                        Log.d("chackmm",response.toString());

                        ArrayList<ProfileModel> list = new ArrayList<>();
                        Gson gSon=new Gson();
                        JsonArray jsonArray = response.get("invites").getAsJsonObject().get("profiles").getAsJsonArray();


                        for (int i = 0; i < jsonArray.size(); i++) {
                        ProfileModel people = gSon.fromJson(jsonArray.get(i), ProfileModel.class);
                       list.add(people);
                        }
                        LikesResponse likesResponse = gSon.fromJson(response.get("likes"), LikesResponse.class);
                        likes.postValue(likesResponse);
                        peoplelist.postValue(list);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        peoplelist.postValue(null);
                        Log.d("chackmm",e.getMessage());
                    }
                }));




    }




}