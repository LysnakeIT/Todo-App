package com.example.todoapp;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;

/**
 * Cette classe permet d'instancier la fenêtre de modification ou de suppression d'activités.
 * Une activité modifiée est mise à jour dans la classe gérant les activités. (taskListActivity)
 * Il en va de même pour la suppression d'activités.
 */
public class taskDescActivity extends AppCompatActivity {

    private EditText intitule;
    private EditText description;
    private EditText duree;
    private EditText date;
    private EditText url;
    private int id;
    private Spinner spinner;
    private String contexte;

    @Override
    /**
     * Cette méthode précise le comportement des éléments de la view.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_des);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        //On récupère le s adresses des zones de texte du xml afin de les remplir
        intitule = (EditText) findViewById(R.id.intituleModif);
        description = (EditText) findViewById(R.id.descriptionModif);
        duree = (EditText) findViewById(R.id.dureeModif);
        date = (EditText)findViewById(R.id.dateModif);
        url = (EditText)findViewById(R.id.urlModif);
        spinner = (Spinner) findViewById(R.id.spinnerModif);
        Button suppressionTask = (Button) findViewById(R.id.taskDelete);
        Button validModif = (Button) findViewById(R.id.valifModif);


        Bundle bundle = getIntent().getExtras();

        //on reçoit les informations de la tache dans un bundle intent et on remplit les zones texte
        intitule.setText(bundle.getString("modif_intitule"));
        description.setText(bundle.getString("modif_description"));
        duree.setText(bundle.getString("modif_duree"));
        date.setText(bundle.getString("modif_date"));
        url.setText(bundle.getString("modif_url"));
        id = bundle.getInt("idList");

        //creation d'un spinner dans une combobox
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(taskDescActivity.this, R.array.context_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //permet de recuperer la valeur du contexte de l'activité que l'on est en train d'editer
        ArrayAdapter secondAdapter = (ArrayAdapter) spinner.getAdapter();
        int spinnerPosition = secondAdapter.getPosition(bundle.getString("modif_context"));
        spinner.setSelection(spinnerPosition);

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

        //bouton de suppression de la tache
        suppressionTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        //bouton de validation des modifications de la tache
        validModif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Cette méthode permet d'envoyer le nouveau contenu de la tache éditée à taskListActivity en y retournant.
     */
    public void finish(){
        Intent activityModifTask = new Intent(taskDescActivity.this, taskListActivity.class);
        activityModifTask.putExtra("new_intitule", intitule.getText().toString());
        activityModifTask.putExtra("new_description", description.getText().toString());
        activityModifTask.putExtra("new_duree", duree.getText().toString());
        activityModifTask.putExtra("new_date", date.getText().toString());
        activityModifTask.putExtra("new_context", contexte);
        activityModifTask.putExtra("new_url", url.getText().toString());
        activityModifTask.putExtra("edit", id);
        super.finish();
        startActivity(activityModifTask);
    }

    /**
     * Cette méthode permet d'envoyer le message de suppression de l'activité sélectionnée à taskListActivity avant d'y retourner.
     */
    public void delete(){
        Intent activityDeleteTask = new Intent(taskDescActivity.this, taskListActivity.class);
        activityDeleteTask.putExtra("delete", id);
        super.finish();
        startActivity(activityDeleteTask);
    }
}
