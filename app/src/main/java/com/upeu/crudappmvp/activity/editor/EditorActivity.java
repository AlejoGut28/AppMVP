package com.upeu.crudappmvp.activity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.upeu.crudappmvp.R;
import com.upeu.crudappmvp.api.ApiClient;
import com.upeu.crudappmvp.api.ApiInterface;
import com.upeu.crudappmvp.model.Alumno;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity implements EditorView{
    EditText name_ed, email_ed, university_ed;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    EditorPresenter editorPresenter;

    int id;
    String name, email, university;
    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        name_ed = findViewById(R.id.nameEditText);
        email_ed = findViewById(R.id.emailEditText);
        university_ed = findViewById(R.id.uniEditText);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espere...");

        editorPresenter = new EditorPresenter(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        university = intent.getStringExtra("university");

        setDataFromIntentExtra();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu); // poser insertar un icono dentro de un activity
        actionMenu = menu;

        if (id != 0) {
            actionMenu.findItem(R.id.edit).setVisible(true);
            actionMenu.findItem(R.id.delete).setVisible(true);
            actionMenu.findItem(R.id.save).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(false);
        } else {
            actionMenu.findItem(R.id.edit).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String name = name_ed.getText().toString().trim();
        String email = email_ed.getText().toString().trim();
        String university = university_ed.getText().toString().trim();

        switch (item.getItemId()) {
            case R.id.save:
                //Save

                if(name.isEmpty()){
                    name_ed.setError("Por favor ingrese un nombre");
                }else if (email.isEmpty()) {
                    email_ed.setError("Por favor ingrese un correo");
                }else if (university.isEmpty()) {
                    university_ed.setError("Por favor ingrese una univesidad");
                }else {
                    editorPresenter.saveStudent(name, email, university);
                }
                return true;

            case R.id.edit:
                editMode();
                actionMenu.findItem(R.id.edit).setVisible(false);
                actionMenu.findItem(R.id.delete).setVisible(false);
                actionMenu.findItem(R.id.save).setVisible(false);
                actionMenu.findItem(R.id.update).setVisible(true);
                return true;

            case R.id.update:
                if(name.isEmpty()){
                    name_ed.setError("Por favor ingrese un nombre");
                }else if (email.isEmpty()) {
                    email_ed.setError("Por favor ingrese un correo");
                }else if (university.isEmpty()) {
                    university_ed.setError("Por favor ingrese una univesidad");
                }else {
                    editorPresenter.updateNote(id, name, email, university);
                }
                return true;

            case R.id.delete:

                AlertDialog.Builder alertDialog  = new AlertDialog.Builder(this);
                alertDialog.setTitle("Eliminar !");
                alertDialog.setMessage("Estas seguro ?");
                alertDialog.setNegativeButton("Si", (dialog, which) -> {
                    dialog.dismiss();
                    editorPresenter.deleteAlumno(id);
                });
                alertDialog.setPositiveButton("Cancelar", ((dialog, which) -> dialog.dismiss()));
                alertDialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(EditorActivity.this,
                message,
                Toast.LENGTH_SHORT).show();
        finish(); // vuelve al menu principal
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(EditorActivity.this,
                message,
                Toast.LENGTH_SHORT).show();
    }

    private void setDataFromIntentExtra() {
        String message = "no se pudo actualizar";
        if( id != 0) {
            name_ed.setText(name);
            email_ed.setText(email);
            university_ed.setText(university);

            getSupportActionBar().setTitle("Alumno actualizado");
            readMode();
        } else {
            Toast.makeText(this,
                    message,
                    Toast.LENGTH_SHORT).show();
            editMode();
        }
    }

    private void editMode() {
        name_ed.setFocusableInTouchMode(true);
        email_ed.setFocusableInTouchMode(true);
        university_ed.setFocusableInTouchMode(true);
    }

    private void readMode() {
        name_ed.setFocusableInTouchMode(false);
        email_ed.setFocusableInTouchMode(false);
        university_ed.setFocusableInTouchMode(false);
        name_ed.setFocusable(false);
        email_ed.setFocusable(false);
        university_ed.setFocusable(false);
    }
}