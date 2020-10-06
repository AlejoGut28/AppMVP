package com.upeu.crudappmvp.activity.main;



import androidx.annotation.NonNull;

import com.upeu.crudappmvp.api.ApiClient;
import com.upeu.crudappmvp.api.ApiInterface;
import com.upeu.crudappmvp.model.Alumno;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    void getData() {

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Alumno>> call = apiInterface.getAlumnos();
        System.out.println(apiInterface.getAlumnos());
        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(@NonNull  Call<List<Alumno>> call, @NonNull  Response<List<Alumno>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }
            @Override
            public void onFailure(@NonNull  Call<List<Alumno>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
