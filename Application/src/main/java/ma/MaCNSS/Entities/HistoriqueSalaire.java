package ma.MaCNSS.Entities;

public class HistoriqueSalaire {
    private int id_societe ;
    private String immatricule;
    private float salaire ;
    private int  nbj_Mois ;

    public HistoriqueSalaire() {
    }

    public HistoriqueSalaire(int id_societe, String immatricule, float salaire, int nbj_Mois) {
        this.id_societe = id_societe;
        this.immatricule = immatricule;
        this.salaire = salaire;
        this.nbj_Mois = nbj_Mois;
    }

    public int getId_societe() {
        return id_societe;
    }

    public void setId_societe(int id_societe) {
        this.id_societe = id_societe;
    }

    public String getImmatricule() {
        return immatricule;
    }

    public void setImmatricule(String immatricule) {
        this.immatricule = immatricule;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public int getNbj_Mois() {
        return nbj_Mois;
    }

    public void setNbj_Mois(int nbj_Mois) {
        this.nbj_Mois = nbj_Mois;
    }
}
