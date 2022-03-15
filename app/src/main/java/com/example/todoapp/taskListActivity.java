package com.example.todoapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Cette classe permet d'instancier la fenetre de liste des taches.
 * Elle sert aussi de menu principal et permet d'accéder à toutes les fonctionnalités de l'application.
 */
public class taskListActivity extends AppCompatActivity {
    private Context context;
    private Adapter adapter;
    private ListView list;
    private ArrayList<Task> arrTask = new ArrayList<>();
    private Task task;
    private final String SHARED_PREFS="task";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
        context = this;
        load();

        //creation de tache par defaut au lancement de l'application
        if(arrTask.size()==0){
            arrTask.add(new Task("tache défaut", "cette tache est créée par défaut au lancement de l'application", "10h", java.time.LocalDate.now().toString(), "Maison", "https://www.google.com"));
        }
        adapter = new Adapter(this, arrTask);
        list = (ListView) findViewById(R.id.taskList);
        list.setAdapter(adapter);

        //gestion de la reception de données pour modification, creation ou suppression de tache
        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();
        if (intent.hasExtra("createTask")) {
            task = (Task) extras.getSerializable("createTask");
            arrTask.add(task);
            adapter.notifyDataSetChanged();
            save();
        } else if (intent.hasExtra("edit")) {
            int tempID = extras.getInt("edit");
            arrTask.get(tempID).setIntitule(extras.getString("new_intitule"));
            arrTask.get(tempID).setDescription(extras.getString("new_description"));
            arrTask.get(tempID).setDuree(extras.getString("new_duree"));
            arrTask.get(tempID).setDate(extras.getString("new_date"));
            arrTask.get(tempID).setContexte(extras.getString("new_context"));
            arrTask.get(tempID).setUrl(extras.getString("new_url"));
            adapter.notifyDataSetChanged();
            save();
        } else if (intent.hasExtra("delete")) {
            arrTask.remove(extras.getInt("delete"));
            adapter.notifyDataSetChanged();
            save();
        }

        //bouton de creation de nouvelle tache
        FloatingActionButton createTaskButton = (FloatingActionButton) findViewById(R.id.createActivity);
        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createActivity = new Intent(context, taskCreateActivity.class);
                startActivity(createActivity);
            }
        });

        //si on appuie longtemps sur une tache on redirige vers son lien
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Intent urlActivity = new Intent(taskListActivity.this,taskWebActivity.class);
                urlActivity.putExtra("url", arrTask.get(position).getUrl());
                startActivity(urlActivity);
                return true;
            };
        });

        //si on appuie sur une tache, on redirige vers la page de modification de la tache
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent modifActivity = new Intent(taskListActivity.this, taskDescActivity.class);
                modifActivity.putExtra("modif_intitule", arrTask.get(i).getIntitule());
                modifActivity.putExtra("modif_description", arrTask.get(i).getDesc());
                modifActivity.putExtra("modif_duree", arrTask.get(i).getDureeH());
                modifActivity.putExtra("modif_date", arrTask.get(i).getDate());
                modifActivity.putExtra("modif_context", arrTask.get(i).getContexte());
                modifActivity.putExtra("modif_url", arrTask.get(i).getUrl());
                modifActivity.putExtra("idList", i);
                startActivity(modifActivity);
            }
        });
    }

    //permet de sauvegarder les taches lors d'une session
    public void save() {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editeur = preferences .edit();
        try {
            editeur.putString("task", TaskSerializer.serialize(arrTask));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        editeur.commit();
    }

    //permet de charger l'etat de la liste des taches d'une session lorsque l'on retourne dans la page principale
    private void load() {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        try {
            arrTask = (ArrayList<Task>) TaskSerializer.deserialize(preferences.getString("task", TaskSerializer.serialize(new ArrayList<Task>())));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}