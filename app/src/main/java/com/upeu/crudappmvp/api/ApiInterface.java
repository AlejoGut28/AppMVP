package com.upeu.crudappmvp.api;

import com.upeu.crudappmvp.model.Alumno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("save.php")
    Call<Alumno> saveAlumno(
            @Field("name") String name,
            @Field("email") String email,
            @Field("university") String university
    );

    @GET("alumnos.php")
    Call<List<Alumno>> getAlumnos();

    @FormUrlEncoded
    @POST("update.php")
    Call<Alumno> updateAlumno(
            @Field("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("university") String university
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<Alumno> deleteAlumno(@Field("id") int id);
}
