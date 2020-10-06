package com.upeu.crudappmvp.activity.main;

import com.upeu.crudappmvp.model.Alumno;

import java.util.List;

public interface MainView {

    void showLoading();
    void hideLoading();
    void onGetResult(List<Alumno> alumnos);
    void onErrorLoading(String message);
}
