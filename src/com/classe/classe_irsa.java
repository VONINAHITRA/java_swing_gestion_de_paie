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
public class classe_irsa {
    private String num_perso;
    private String nom_perso;
    private double elment;
    private double sal_base;
    private double cnaps;
    private double osief;
    private double abt_for;
    private double bas_impose;
    private double impo_cores;
    private double enfant;
    private double charge_fami;
    private double impot_du;
    private double sal_brutes;
    private  String mois_irsa;
    private  String annee_irsa;
    private double autres_reduc;
    private String date;
 public classe_irsa(){
}
  public classe_irsa(String num,String nom,double elem, double sal_base,double sal_brutes,double cnaps, double osief
  ,double abt_for,double bas_impose, double impo_cores, double enfant,double charge, double impot_du, String mois_irsa, String annee_irsa,double autres_reduc, String date){
this.num_perso =num;
this.nom_perso=nom;
this.elment= elem;
this.sal_base =sal_base;
this.sal_brutes =sal_brutes;
this.cnaps =cnaps;
this.osief = osief;
this.abt_for =abt_for;
this.bas_impose = bas_impose;
this.impo_cores =impo_cores;
this.enfant = enfant;
this.charge_fami = charge;
this.impot_du = impot_du;
this.mois_irsa =mois_irsa;
this.annee_irsa  =annee_irsa;
this.autres_reduc =autres_reduc;
this.date= date;
  
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
     * @return the nom_perso
     */
    public String getNom_perso() {
        return nom_perso;
    }

    /**
     * @param nom_perso the nom_perso to set
     */
    public void setNom_perso(String nom_perso) {
        this.nom_perso = nom_perso;
    }

    /**
     * @return the elment
     */
    public double getElment() {
        return elment;
    }

    /**
     * @param elment the elment to set
     */
    public void setElment(double elment) {
        this.elment = elment;
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
     * @return the cnaps
     */
    public double getCnaps() {
        return cnaps;
    }

    /**
     * @param cnaps the cnaps to set
     */
    public void setCnaps(double cnaps) {
        this.cnaps = cnaps;
    }

    /**
     * @return the osief
     */
    public double getOsief() {
        return osief;
    }

    /**
     * @param osief the osief to set
     */
    public void setOsief(double osief) {
        this.osief = osief;
    }

    /**
     * @return the abt_for
     */
    public double getAbt_for() {
        return abt_for;
    }

    /**
     * @param abt_for the abt_for to set
     */
    public void setAbt_for(double abt_for) {
        this.abt_for = abt_for;
    }

    /**
     * @return the bas_impose
     */
    public double getBas_impose() {
        return bas_impose;
    }

    /**
     * @param bas_impose the bas_impose to set
     */
    public void setBas_impose(double bas_impose) {
        this.bas_impose = bas_impose;
    }

    /**
     * @return the impo_cores
     */
    public double getImpo_cores() {
        return impo_cores;
    }

    /**
     * @param impo_cores the impo_cores to set
     */
    public void setImpo_cores(double impo_cores) {
        this.impo_cores = impo_cores;
    }

    /**
     * @return the enfant
     */
    public double getEnfant() {
        return enfant;
    }

    /**
     * @param enfant the enfant to set
     */
    public void setEnfant(double enfant) {
        this.enfant = enfant;
    }

    /**
     * @return the charge_fami
     */
    public double getCharge_fami() {
        return charge_fami;
    }

    /**
     * @param charge_fami the charge_fami to set
     */
    public void setCharge_fami(double charge_fami) {
        this.charge_fami = charge_fami;
    }

    /**
     * @return the impot_du
     */
    public double getImpot_du() {
        return impot_du;
    }

    /**
     * @param impot_du the impot_du to set
     */
    public void setImpot_du(double impot_du) {
        this.impot_du = impot_du;
    }

    /**
     * @return the sal_brutes
     */
    public double getSal_brutes() {
        return sal_brutes;
    }

    /**
     * @param sal_brutes the sal_brutes to set
     */
    public void setSal_brutes(double sal_brutes) {
        this.sal_brutes = sal_brutes;
    }

    /**
     * @return the mois_irsa
     */
    public String getMois_irsa() {
        return mois_irsa;
    }

    /**
     * @param mois_irsa the mois_irsa to set
     */
    public void setMois_irsa(String mois_irsa) {
        this.mois_irsa = mois_irsa;
    }

    /**
     * @return the annee_irsa
     */
    public String getAnnee_irsa() {
        return annee_irsa;
    }

    /**
     * @param annee_irsa the annee_irsa to set
     */
    public void setAnnee_irsa(String annee_irsa) {
        this.annee_irsa = annee_irsa;
    }

    /**
     * @return the autres_reduc
     */
    public double getAutres_reduc() {
        return autres_reduc;
    }

    /**
     * @param autres_reduc the autres_reduc to set
     */
    public void setAutres_reduc(double autres_reduc) {
        this.autres_reduc = autres_reduc;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

   
    
}
