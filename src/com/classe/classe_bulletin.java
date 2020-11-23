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
public class classe_bulletin {
 private  String num_perso;
 private String nom_pre_bulletin;
 private String dt_now_bulletin;
 private int mois_bulletin;
 private int annee_bulletin;
 private String pdu_bulletin;
 private String pau_bulletin;
 private double totale_reduc_bulletin;
 private double totale_av_bulletin;
 private double totale_net_bulltin;
 private double sal_base;
 private double simple_av;
    
public classe_bulletin(){   
}

public classe_bulletin(String num_perso,String nom_pre_bulletin, String dt_now_bulletin,int mois_bulletin,int annee_bulletin,String pdu_bulletin, String pau_bulletin,double totale_reduc_bulletin,double totale_av_bulletin,double totale_net_bulletin, double sal_base, double simple_av){
this.num_perso = num_perso;
this.nom_pre_bulletin = nom_pre_bulletin;
this.dt_now_bulletin = dt_now_bulletin;
this.mois_bulletin = mois_bulletin;
this.annee_bulletin = annee_bulletin;
this.pdu_bulletin = pdu_bulletin;
this.pau_bulletin = pau_bulletin;
this.totale_reduc_bulletin = totale_reduc_bulletin;
this.totale_av_bulletin = totale_av_bulletin;
this.totale_net_bulltin = totale_net_bulletin;
this.sal_base = sal_base;
this.simple_av = simple_av;
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
     * @return the nom_pre_bulletin
     */
    public String getNom_pre_bulletin() {
        return nom_pre_bulletin;
    }

    /**
     * @param nom_pre_bulletin the nom_pre_bulletin to set
     */
    public void setNom_pre_bulletin(String nom_pre_bulletin) {
        this.nom_pre_bulletin = nom_pre_bulletin;
    }

    /**
     * @return the dt_now_bulletin
     */
    public String getDt_now_bulletin() {
        return dt_now_bulletin;
    }

    /**
     * @param dt_now_bulletin the dt_now_bulletin to set
     */
    public void setDt_now_bulletin(String dt_now_bulletin) {
        this.dt_now_bulletin = dt_now_bulletin;
    }

    /**
     * @return the mois_bulletin
     */
    public int getMois_bulletin() {
        return mois_bulletin;
    }

    /**
     * @param mois_bulletin the mois_bulletin to set
     */
    public void setMois_bulletin(int mois_bulletin) {
        this.mois_bulletin = mois_bulletin;
    }

    /**
     * @return the annee_bulletin
     */
    public int getAnnee_bulletin() {
        return annee_bulletin;
    }

    /**
     * @param annee_bulletin the annee_bulletin to set
     */
    public void setAnnee_bulletin(int annee_bulletin) {
        this.annee_bulletin = annee_bulletin;
    }

    /**
     * @return the pdu_bulletin
     */
    public String getPdu_bulletin() {
        return pdu_bulletin;
    }

    /**
     * @param pdu_bulletin the pdu_bulletin to set
     */
    public void setPdu_bulletin(String pdu_bulletin) {
        this.pdu_bulletin = pdu_bulletin;
    }

    /**
     * @return the pau_bulletin
     */
    public String getPau_bulletin() {
        return pau_bulletin;
    }

    /**
     * @param pau_bulletin the pau_bulletin to set
     */
    public void setPau_bulletin(String pau_bulletin) {
        this.pau_bulletin = pau_bulletin;
    }

    /**
     * @return the totale_reduc_bulletin
     */
    public double getTotale_reduc_bulletin() {
        return totale_reduc_bulletin;
    }

    /**
     * @param totale_reduc_bulletin the totale_reduc_bulletin to set
     */
    public void setTotale_reduc_bulletin(double totale_reduc_bulletin) {
        this.totale_reduc_bulletin = totale_reduc_bulletin;
    }

    /**
     * @return the totale_av_bulletin
     */
    public double getTotale_av_bulletin() {
        return totale_av_bulletin;
    }

    /**
     * @param totale_av_bulletin the totale_av_bulletin to set
     */
    public void setTotale_av_bulletin(double totale_av_bulletin) {
        this.totale_av_bulletin = totale_av_bulletin;
    }

    /**
     * @return the totale_net_bulltin
     */
    public double getTotale_net_bulltin() {
        return totale_net_bulltin;
    }

    /**
     * @param totale_net_bulltin the totale_net_bulltin to set
     */
    public void setTotale_net_bulltin(double totale_net_bulltin) {
        this.totale_net_bulltin = totale_net_bulltin;
    }

    /**
     * @return the sal_base
     */
    public double getSal_base() {
        return sal_base;
    }

    /**
     * @param sal_base the sal_base to set
     */
    public void setSal_base(double sal_base) {
        this.sal_base = sal_base;
    }

    /**
     * @return the simple_av
     */
    public double getSimple_av() {
        return simple_av;
    }

    /**
     * @param simple_av the simple_av to set
     */
    public void setSimple_av(double simple_av) {
        this.simple_av = simple_av;
    }

   
    
}
