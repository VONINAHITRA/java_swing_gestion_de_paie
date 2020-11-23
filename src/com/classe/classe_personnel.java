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
public class classe_personnel {
    private String num_perso;
    private String nom_pre_perso;
    private String sexe;
    private String dt_emb_perso;
    private String situ_perso;
    private int nbr_enf_perso;
    private String adrs_perso;
    private String tel_perso;
public classe_personnel(){
}
public classe_personnel(String num_perso,String nom_pre_perso, String sexe, String dt_emb_perso, String situ_perso,int nbr_enf_perso, String adrs_perso, String tel_perso ){
this.num_perso = num_perso;
this.nom_pre_perso = nom_pre_perso;
this.sexe = sexe;
this.dt_emb_perso = dt_emb_perso;
this.situ_perso = situ_perso;
this.nbr_enf_perso = nbr_enf_perso;
this.adrs_perso = adrs_perso;
this.tel_perso =tel_perso;
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
     * @return the nom_pre_perso
     */
    public String getNom_pre_perso() {
        return nom_pre_perso;
    }

    /**
     * @param nom_pre_perso the nom_pre_perso to set
     */
    public void setNom_pre_perso(String nom_pre_perso) {
        this.nom_pre_perso = nom_pre_perso;
    }

    /**
     * @return the sexe
     */
    public String getSexe() {
        return sexe;
    }

    /**
     * @param sexe the sexe to set
     */
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    /**
     * @return the dt_emb_perso
     */
    public String getDt_emb_perso() {
        return dt_emb_perso;
    }

    /**
     * @param dt_emb_perso the dt_emb_perso to set
     */
    public void setDt_emb_perso(String dt_emb_perso) {
        this.dt_emb_perso = dt_emb_perso;
    }

    /**
     * @return the situ_perso
     */
    public String getSitu_perso() {
        return situ_perso;
    }

    /**
     * @param situ_perso the situ_perso to set
     */
    public void setSitu_perso(String situ_perso) {
        this.situ_perso = situ_perso;
    }

    /**
     * @return the nbr_enf_perso
     */
    public int getNbr_enf_perso() {
        return nbr_enf_perso;
    }

    /**
     * @param nbr_enf_perso the nbr_enf_perso to set
     */
    public void setNbr_enf_perso(int nbr_enf_perso) {
        this.nbr_enf_perso = nbr_enf_perso;
    }

    /**
     * @return the adrs_perso
     */
    public String getAdrs_perso() {
        return adrs_perso;
    }

    /**
     * @param adrs_perso the adrs_perso to set
     */
    public void setAdrs_perso(String adrs_perso) {
        this.adrs_perso = adrs_perso;
    }

    /**
     * @return the tel_perso
     */
    public String getTel_perso() {
        return tel_perso;
    }

    /**
     * @param tel_perso the tel_perso to set
     */
    public void setTel_perso(String tel_perso) {
        this.tel_perso = tel_perso;
    }

    
}
