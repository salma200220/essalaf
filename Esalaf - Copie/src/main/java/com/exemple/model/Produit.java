package com.exemple.model;

public class Produit {
    private int id_produit ;

    private String nom ;

    private String prix ;

    public Produit() {
    }

    public Produit(int id_produit, String nom, String prix) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix= prix;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrix()
    {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id_produit=" + id_produit +
                ", nom='" + nom + '\'' +
                ", prix='" + prix + '\'' +
                '}';
    }
}
