package com.upeu.crudappmvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alumno  {

    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("name") private String name;
    @Expose
    @SerializedName("email") private String email;
    @Expose
    @SerializedName("university") private String university;
    @Expose
    @SerializedName("date") private String date;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public Alumno() {
    }

    public Alumno(int id, String name, String email, String university, String date, Boolean success, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.university = university;
        this.date = date;
        this.success = success;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
