package in.example.aisle.Viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import in.example.aisle.Network.ApiClient;
import in.example.aisle.Network.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends AndroidViewModel {


    private final ApiService apiService;
    private CompositeDisposable mDisposable = new CompositeDisposable();

    public MutableLiveData<String> login = new MutableLiveData<>();

    public LoginViewModel(@NonNull @NotNull Application application) {

        super(application);
         apiService = new  ApiClient().getClient(getApplication()).create( ApiService.class);

    }

    public void Login(String num) {


         mDisposable.add(apiService.login(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<JsonObject>() {
                    @Override
                    public void onSuccess(@NotNull JsonObject response) {

                        login.postValue(response.get("status").toString());








                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.d("chack",e.getMessage().toString());
                        login.postValue(null);

                    }
                }));




    }




}