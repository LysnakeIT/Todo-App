package com.example.todoapp;

import java.io.Serializable;

/**
 * Cette classe définit l'object Task utilise dans le reste de l'application
 */
public class Task implements Serializable {
    private String intitule;
    private String description;
    private String dureeH;
    private String date;
    private String contexte;
    private String url;

    /**
     * Contructeur de Task.
     * @attribute intitule: le nom de la tache
     * @attribute description: simple description de la tache
     * @attribute dureeH: duree prevue pour la tache
     * @attribute date: date à laquelle executer la tache de la tache
     * @attribute contexte: lieu ou la tache est effectuee
     * @attribute url: le nom de la tache
     */
    public Task(String intitule, String description, String dureeH, String date, String contexte, String url){
        this.intitule=intitule;
        this.description=description;
        this.dureeH=dureeH;
        this.date=date;
        this.contexte = contexte;
        this.url = url;
    }

    /**
     * getter pour l'intitule de la tache
     */
    public String getIntitule(){
        return this.intitule;
    }

    /**
     * getter pour la description description de la tache
     */
    public String getDesc(){
        return this.description;
    }

    /**
     * getter pour la duree de la tache
     */
    public String getDureeH(){
        return this.dureeH;
    }

    /**
     * getter pour la date de la tache
     */
    public String getDate(){
        return this.date;
    }

    /**
     * getter pour le contexte de la tache
     */
    public String getContexte(){
        return this.contexte;
    }

    /**
     * getter pour le lien eventuel de la tache
     */
    public String getUrl(){
        return this.url;
    }

    /**
     * setter pour l'intitule de la tache
     */
    public void setIntitule(String newIntitule) {
        this.intitule = newIntitule;
    }

    /**
     * setter pour la description description de la tache
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * setter pour la duree de la tache
     */
    public void setDuree(String duree) {
        this.dureeH = duree;
    }

    /**
     * setter pour la date de la tache
     */
    public void setDate(String newDate) {
        this.date = newDate;
    }

    /**
     * setter pour le contexte de la tache
     */
    public void setContexte(String newContexte) {
        this.contexte = newContexte;
    }

    /**
     * setter pour le lien eventuel de la tache
     */
    public void setUrl(String newUrl) {
        this.url = newUrl;
    }
}