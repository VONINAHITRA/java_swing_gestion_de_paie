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
public class classe_reduction {
private String num_perso;
private String dt_now_reduc;
private String mois_reduc;
private String annee_reduc;
private String pdu_reduc;
private String pau_reduc;
private double cnaps_reduc;
private double osief_reduc;
private double irsa_reduc;
private double cess_sal_reduc;
private double nouri_log_reduc;
private double autres_reduc;
private double avance_reduc;
private double totale_brute_reduc;
public classe_reduction(){   
}
public classe_reduction(String num_perso, String dt_now_reduc,String mois_reduc,String annee_reduc,String pdu_reduc,String pau_reduc,double cnaps_reduc,double osief_reduc,double irsa_reduc,double cess_sal_reduc,double nouri_log_reduc,double autres_reduc,double avance_reduc, double totale_sal_reduc){
this.num_perso = num_perso;
this.dt_now_reduc = dt_now_reduc;
this.mois_reduc = mois_reduc;
this.annee_reduc = annee_reduc;
this.pdu_reduc = pdu_reduc;
this.pau_reduc = pau_reduc;
this.cnaps_reduc = cnaps_reduc;
this.osief_reduc = osief_reduc;
this.irsa_reduc = irsa_reduc;
this.cess_sal_reduc  =cess_sal_reduc;
this.nouri_log_reduc = nouri_log_reduc;
this.autres_reduc = autres_reduc;
this.avance_reduc = avance_reduc;
this.totale_brute_reduc =totale_sal_reduc;
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
     * @return the dt_now_reduc
     */
    public String getDt_now_reduc() {
        return dt_now_reduc;
    }

    /**
     * @param dt_now_reduc the dt_now_reduc to set
     */
    public void setDt_now_reduc(String dt_now_reduc) {
        this.dt_now_reduc = dt_now_reduc;
    }

    /**
     * @return the mois_reduc
     */
    public String getMois_reduc() {
        return mois_reduc;
    }

    /**
     * @param mois_reduc the mois_reduc to set
     */
    public void setMois_reduc(String mois_reduc) {
        this.mois_reduc = mois_reduc;
    }

    /**
     * @return the annee_reduc
     */
    public String getAnnee_reduc() {
        return annee_reduc;
    }

    /**
     * @param annee_reduc the annee_reduc to set
     */
    public void setAnnee_reduc(String annee_reduc) {
        this.annee_reduc = annee_reduc;
    }

    /**
     * @return the pdu_reduc
     */
    public String getPdu_reduc() {
        return pdu_reduc;
    }

    /**
     * @param pdu_reduc the pdu_reduc to set
     */
    public void setPdu_reduc(String pdu_reduc) {
        this.pdu_reduc = pdu_reduc;
    }

    /**
     * @return the pau_reduc
     */
    public String getPau_reduc() {
        return pau_reduc;
    }

    /**
     * @param pau_reduc the pau_reduc to set
     */
    public void setPau_reduc(String pau_reduc) {
        this.pau_reduc = pau_reduc;
    }

    /**
     * @return the cnaps_reduc
     */
    public double getCnaps_reduc() {
        return cnaps_reduc;
    }

    /**
     * @param cnaps_reduc the cnaps_reduc to set
     */
    public void setCnaps_reduc(double cnaps_reduc) {
        this.cnaps_reduc = cnaps_reduc;
    }

    /**
     * @return the osief_reduc
     */
    public double getOsief_reduc() {
        return osief_reduc;
    }

    /**
     * @param osief_reduc the osief_reduc to set
     */
    public void setOsief_reduc(double osief_reduc) {
        this.osief_reduc = osief_reduc;
    }

    /**
     * @return the irsa_reduc
     */
    public double getIrsa_reduc() {
        return irsa_reduc;
    }

    /**
     * @param irsa_reduc the irsa_reduc to set
     */
    public void setIrsa_reduc(double irsa_reduc) {
        this.irsa_reduc = irsa_reduc;
    }

    /**
     * @return the cess_sal_reduc
     */
    public double getCess_sal_reduc() {
        return cess_sal_reduc;
    }

    /**
     * @param cess_sal_reduc the cess_sal_reduc to set
     */
    public void setCess_sal_reduc(double cess_sal_reduc) {
        this.cess_sal_reduc = cess_sal_reduc;
    }

    /**
     * @return the nouri_log_reduc
     */
    public double getNouri_log_reduc() {
        return nouri_log_reduc;
    }

    /**
     * @param nouri_log_reduc the nouri_log_reduc to set
     */
    public void setNouri_log_reduc(double nouri_log_reduc) {
        this.nouri_log_reduc = nouri_log_reduc;
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
     * @return the avance_reduc
     */
    public double getAvance_reduc() {
        return avance_reduc;
    }

    /**
     * @param avance_reduc the avance_reduc to set
     */
    public void setAvance_reduc(double avance_reduc) {
        this.avance_reduc = avance_reduc;
    }

    /**
     * @return the totale_brute_reduc
     */
    public double getTotale_brute_reduc() {
        return totale_brute_reduc;
    }

    /**
     * @param totale_brute_reduc the totale_brute_reduc to set
     */
    public void setTotale_brute_reduc(double totale_brute_reduc) {
        this.totale_brute_reduc = totale_brute_reduc;
    }

    
}
