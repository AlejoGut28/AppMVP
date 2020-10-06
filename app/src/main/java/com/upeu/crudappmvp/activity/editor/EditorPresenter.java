package com.upeu.crudappmvp.activity.editor;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.upeu.crudappmvp.api.ApiClient;
import com.upeu.crudappmvp.api.ApiInterface;
import com.upeu.crudappmvp.model.Alumno;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view) {
        this.view = view;
    }

    void saveStudent(final String name, final String email, final String university) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient()
                .create(ApiInterface.class);
        Call<Alumno> call = apiInterface.saveAlumno(name, email, university);
        call.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(@NonNull Call<Alumno> call, @NonNull Response<Alumno> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null){
                    Boolean succes = response.body().getSuccess();
                    if (succes) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                            view.onRequestError(response.body().getMessage());
                    }
                }
            }
            @Override
            public void onFailure(@NonNull  Call<Alumno> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void updateNote(int id, String name, String email, String university) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Alumno> call = apiInterface.updateAlumno(id, name, email, university);
        call.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(@NonNull  Call<Alumno> call, @NonNull Response<Alumno> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null ) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<Alumno> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void deleteAlumno (int id) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Alumno> call = apiInterface.deleteAlumno(id);
        call.enqueue(new Callback<Alumno>() {
            @Override
            public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null ) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<Alumno> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
}
