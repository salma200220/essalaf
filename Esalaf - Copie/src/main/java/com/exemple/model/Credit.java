package com.exemple.model;

public class Credit {
    private int id_credit;
    private String nom;
    private String credi;

    public Credit() {
    }

    public Credit(int id_credit, String nom, String credi) {
        this.id_credit = id_credit;
        this.nom = nom;
        this.credi = credi;
    }

    public int getId_credit() {
        return id_credit;
    }

    public void setId_credit(int id_credit) {
        this.id_credit = id_credit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCredi() {
        return credi;
    }

    public void setCredi(String credit) {
        this.credi = credi;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id_credit=" + id_credit +
                ", nom='" + nom + '\'' +
                ", credi=" + credi +
                '}';
    }
}
