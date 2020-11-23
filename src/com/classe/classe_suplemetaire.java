/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classe;

/**
 *
 * @author VONINAHITRA
 */
public class classe_suplemetaire {
 private String num_perso;
 private String dt_now_av;
 private String mois_av;
 private String annee_av;
 private String periode_du_av;
 private String periode_au_av;
 private double idemnite_av;
 private double voca_piece_av;
 private double anciente;
 private int nbrhers_sup_av;
 private int nbheure40;
 private double hrs_sup8_av;
 private double hrs_sup_plus8_av;
 private double val_heures_40;
 private double totale_brute_av;
 
public classe_suplemetaire(){
}
public classe_suplemetaire(String num_perso, String dt_now_av, String mois_av,String annee_av,String periode_du_av, String periode_au_av,double idemnite_av,double voca_piece_av,double  anciente, int nbhers_sup_av,int nbheure40,double hrs_sup8_av,double hrs_sup_plus8_av,double val_heures_40,double totale_brute_av){
this.num_perso = num_perso;
this.dt_now_av = dt_now_av;
this.mois_av = mois_av;
this.annee_av = annee_av;
this.periode_du_av = periode_du_av;
this.periode_au_av = periode_au_av;
this.idemnite_av = idemnite_av;
this.voca_piece_av = voca_piece_av;
this.anciente = anciente;
this.nbrhers_sup_av = nbhers_sup_av;
this.nbheure40 = nbheure40;
this.hrs_sup8_av = hrs_sup8_av;
this.hrs_sup_plus8_av = hrs_sup_plus8_av;
this.val_heures_40 = val_heures_40;
this.totale_brute_av = totale_brute_av;
}

    public classe_suplemetaire(String num_p, String date_now, String mois, String annee_av, String periode_du, String periode_au, double idemnite_av, double voca_av, int nbr_av, double hrsval8_av, double hrsvalplus8_av, double totale_brute_av) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the num_perso
     */
    public String getNum_perso() {
        return num_perso;
    }

    /**
     * @param num_perso the num_perso to set
     */
    public void setNum_perso(String num_perso) {
        this.num_perso = num_perso;
    }

    /**
     * @return the dt_now_av
     */
    public String getDt_now_av() {
        return dt_now_av;
    }

    /**
     * @param dt_now_av the dt_now_av to set
     */
    public void setDt_now_av(String dt_now_av) {
        this.dt_now_av = dt_now_av;
    }

    /**
     * @return the mois_av
     */
    public String getMois_av() {
        return mois_av;
    }

    /**
     * @param mois_av the mois_av to set
     */
    public void setMois_av(String mois_av) {
        this.mois_av = mois_av;
    }

    /**
     * @return the annee_av
     */
    public String getAnnee_av() {
        return annee_av;
    }

    /**
     * @param annee_av the annee_av to set
     */
    public void setAnnee_av(String annee_av) {
        this.annee_av = annee_av;
    }

    /**
     * @return the periode_du_av
     */
    public String getPeriode_du_av() {
        return periode_du_av;
    }

    /**
     * @param periode_du_av the periode_du_av to set
     */
    public void setPeriode_du_av(String periode_du_av) {
        this.periode_du_av = periode_du_av;
    }

    /**
     * @return the periode_au_av
     */
    public String getPeriode_au_av() {
        return periode_au_av;
    }

    /**
     * @param periode_au_av the periode_au_av to set
     */
    public void setPeriode_au_av(String periode_au_av) {
        this.periode_au_av = periode_au_av;
    }

    /**
     * @return the idemnite_av
     */
    public double getIdemnite_av() {
        return idemnite_av;
    }

    /**
     * @param idemnite_av the idemnite_av to set
     */
    public void setIdemnite_av(double idemnite_av) {
        this.idemnite_av = idemnite_av;
    }

    /**
     * @return the voca_piece_av
     */
    public double getVoca_piece_av() {
        return voca_piece_av;
    }

    /**
     * @param voca_piece_av the voca_piece_av to set
     */
    public void setVoca_piece_av(double voca_piece_av) {
        this.voca_piece_av = voca_piece_av;
    }

    /**
     * @return the anciente
     */
    public double getAnciente() {
        return anciente;
    }

    /**
     * @param anciente the anciente to set
     */
    public void setAnciente(double anciente) {
        this.anciente = anciente;
    }

    /**
     * @return the nbrhers_sup_av
     */
    public int getNbrhers_sup_av() {
        return nbrhers_sup_av;
    }

    /**
     * @param nbrhers_sup_av the nbrhers_sup_av to set
     */
    public void setNbrhers_sup_av(int nbrhers_sup_av) {
        this.nbrhers_sup_av = nbrhers_sup_av;
    }

    /**
     * @return the nbheure40
     */
    public int getNbheure40() {
        return nbheure40;
    }

    /**
     * @param nbheure40 the nbheure40 to set
     */
    public void setNbheure40(int nbheure40) {
        this.nbheure40 = nbheure40;
    }

    /**
     * @return the hrs_sup8_av
     */
    public double getHrs_sup8_av() {
        return hrs_sup8_av;
    }

    /**
     * @param hrs_sup8_av the hrs_sup8_av to set
     */
    public void setHrs_sup8_av(double hrs_sup8_av) {
        this.hrs_sup8_av = hrs_sup8_av;
    }

    /**
     * @return the hrs_sup_plus8_av
     */
    public double getHrs_sup_plus8_av() {
        return hrs_sup_plus8_av;
    }

    /**
     * @param hrs_sup_plus8_av the hrs_sup_plus8_av to set
     */
    public void setHrs_sup_plus8_av(double hrs_sup_plus8_av) {
        this.hrs_sup_plus8_av = hrs_sup_plus8_av;
    }

    /**
     * @return the val_heures_40
     */
    public double getVal_heures_40() {
        return val_heures_40;
    }

    /**
     * @param val_heures_40 the val_heures_40 to set
     */
    public void setVal_heures_40(double val_heures_40) {
        this.val_heures_40 = val_heures_40;
    }

    /**
     * @return the totale_brute_av
     */
    public double getTotale_brute_av() {
        return totale_brute_av;
    }

    /**
     * @param totale_brute_av the totale_brute_av to set
     */
    public void setTotale_brute_av(double totale_brute_av) {
        this.totale_brute_av = totale_brute_av;
    }

    

}