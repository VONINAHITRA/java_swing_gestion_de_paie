/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classe;

/**
 *
 * @author VONIANAHITRA
 */
public class classe_osief {
private String num;
private String nom;
private double avantage;
private double sal_base;
private String temps;
private int mois;
private int annee;
classe_osief(){
}
public classe_osief(String num,String nom,double avantage,double sal_base,String temps,int mois,int annee){
this.num =num;
this.nom= nom;
this.avantage = avantage;
this.sal_base = sal_base;
this.temps = temps;
this.mois= mois;
this.annee = annee;
}

    /**
     * @return the num
     */
    public String getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the avantage
     */
    public double getAvantage() {
        return avantage;
    }

    /**
     * @param avantage the avantage to set
     */
    public void setAvantage(double avantage) {
        this.avantage = avantage;
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
     * @return the temps
     */
    public String getTemps() {
        return temps;
    }

    /**
     * @param temps the temps to set
     */
    public void setTemps(String temps) {
        this.temps = temps;
    }

    /**
     * @return the mois
     */
    public int getMois() {
        return mois;
    }

    /**
     * @param mois the mois to set
     */
    public void setMois(int mois) {
        this.mois = mois;
    }

    /**
     * @return the annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * @param annee the annee to set
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

   
}
