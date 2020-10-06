package com.upeu.crudappmvp.activity.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.upeu.crudappmvp.R;
import com.upeu.crudappmvp.activity.editor.EditorActivity;
import com.upeu.crudappmvp.model.Alumno;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{
    private static final int INTENT_EDIT = 200;
    private static final int INTENT_ADD = 100;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;
    List<Alumno> alumno = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_refresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.add);
        fab.setOnClickListener(view ->
            startActivityForResult(
                    new Intent(this, EditorActivity.class),
                    INTENT_ADD)
        );

        presenter = new MainPresenter(this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position ) -> {
                int id = alumno.get(position).getId();
                String name = alumno.get(position).getName();
                String email = alumno.get(position).getEmail();
                String university = alumno.get(position).getUniversity();

                Intent intent = new Intent(this, EditorActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("email", email);
                intent.putExtra("university", university);
                startActivityForResult(intent, INTENT_EDIT);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTENT_ADD && resultCode == RESULT_OK) {
            presenter.getData(); // recargar la información
        } else if (requestCode == INTENT_EDIT && resultCode == RESULT_OK) {
            presenter.getData(); // recargar la información
        }
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Alumno> alumnos) {
        adapter = new MainAdapter(this, alumno, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        alumno = alumnos;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}