package com.example.todoapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Cette classe permet d'instancier la fenêtre de création d'activités.
 * Une activité créée est envoyée dans la classe gérant les activités. (taskListActivity)
 */
public class taskCreateActivity extends AppCompatActivity {

    private Task task;
    private Spinner spinner;
    private String contexte;

    @Override
    /**
     *  Cette  méthode précise le comportement des éléments de la view.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        Button boutonCreationTask = findViewById(R.id.creationTaskButton);
        EditText intitule = findViewById(R.id.intituleId);
        EditText description = findViewById(R.id.descriptionId);
        EditText duree = findViewById(R.id.dureeId);
        EditText date = findViewById(R.id.dateId);
        EditText url = findViewById(R.id.urlId);
        spinner = (Spinner) findViewById(R.id.spinner);

        //creation d'un spinner dans une combobox
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(taskCreateActivity.this, R.array.context_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //permet de changer la valeur du spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                contexte = String.valueOf(spinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        boutonCreationTask.setOnClickListener(new View.OnClickListener() { //on associe la méthode au bouton défini dans le xml
            @Override
            /**
             * Cette méthode précise le comportement du bouton de création d'activité.
             * On récupère le contenu des zones texte du xml pour créer une nouvelle tache que l'on envoie ensuite à la view taskListActivity.java
             */
            public void onClick(View view) {
                Intent activityList = new Intent(taskCreateActivity.this, taskListActivity.class);
                task = new Task(intitule.getText().toString(), description.getText().toString(), duree.getText().toString(), date.getText().toString(), contexte, url.getText().toString());
                activityList.putExtra("createTask", (Serializable) task);
                finish();
                startActivity(activityList);
            }
        });
    }
}
