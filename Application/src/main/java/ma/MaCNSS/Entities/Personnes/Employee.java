package ma.MaCNSS.Entities.Personnes;

import ma.MaCNSS.Entities.Societe;
import ma.MaCNSS.enums.Genre;

import java.sql.Date;
import java.time.LocalDate;

public class Employee extends Patient {
    private float salaire ;
    private boolean isPresent = true ;
    private boolean isRetrait = false ;
    private LocalDate date_naissance ;
    private Societe societe ;

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Employee() {
    }

    public Employee(String immatricule, String CIN, String nom, String prenom, String ville, String telephone, String email, String password, Genre genre, float salaire, int nbr_total , int age ,LocalDate date_naissance  ) {
        super(immatricule, CIN, nom, prenom, ville, telephone, email, password, genre);
        this.salaire = salaire;
        this.date_naissance = date_naissance ;
    }

    public Employee(float salaire, int nbr_total, Societe societe) {
        this.salaire = salaire;
        this.societe = societe;
    }

    public Employee(String immatricule, String CIN, String nom, String prenom, String ville, String telephone, String email, String password, Genre genre, float salaire, int nbr_total, Societe societe , LocalDate date_naissance) {
        super(immatricule, CIN, nom, prenom, ville, telephone, email, password, genre);
        this.salaire = salaire;
        this.societe = societe;
        this.date_naissance = date_naissance ;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public boolean isRetrait() {
        return isRetrait;
    }

    public void setRetrait(boolean retrait) {
        isRetrait = retrait;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }
}
