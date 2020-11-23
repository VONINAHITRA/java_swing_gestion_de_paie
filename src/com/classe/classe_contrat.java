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
public class classe_contrat {
       private String matricule;
       private String nom_pre_contrat;
       private String service_contrat;
       private String dt_now_contrat;
       private String dt_embauche_contra;
       private String dt_enter_contrat;
       private String dt_sortie_contrat;
       private String clasifi_contrat;
       private String indice_contrat;
       private double sal_base_contrat;
       private String type_contrat;
public classe_contrat(){
}
public classe_contrat(String matricule,String nom_pre_contrat,String service_contrat,String dt_now_contrat,String dt_embauche, String dt_enter_contrat,String dt_sortie_contrat, String clasifi_contrat,String indice_contrat,double sal_base_contrat,String type_contrat){
this.matricule = matricule;
this.nom_pre_contrat = nom_pre_contrat;
this.service_contrat = service_contrat;
this.dt_now_contrat = dt_now_contrat;
this.dt_embauche_contra = dt_embauche;
this.dt_enter_contrat = dt_enter_contrat;
this.dt_sortie_contrat = dt_sortie_contrat;
this.clasifi_contrat = clasifi_contrat;
this.indice_contrat = indice_contrat;
this.sal_base_contrat = sal_base_contrat;
this.type_contrat = type_contrat;
}
    /**
     * @return the matricule
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * @param matricule the matricule to set
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * @return the nom_pre_contrat
     */
    public String getNom_pre_contrat() {
        return nom_pre_contrat;
    }

    /**
     * @param nom_pre_contrat the nom_pre_contrat to set
     */
    public void setNom_pre_contrat(String nom_pre_contrat) {
        this.nom_pre_contrat = nom_pre_contrat;
    }

    /**
     * @return the service_contrat
     */
    public String getService_contrat() {
        return service_contrat;
    }

    /**
     * @param service_contrat the service_contrat to set
     */
    public void setService_contrat(String service_contrat) {
        this.service_contrat = service_contrat;
    }

    /**
     * @return the dt_now_contrat
     */
    public String getDt_now_contrat() {
        return dt_now_contrat;
    }

    /**
     * @param dt_now_contrat the dt_now_contrat to set
     */
    public void setDt_now_contrat(String dt_now_contrat) {
        this.dt_now_contrat = dt_now_contrat;
    }

    /**
     * @return the dt_embauche_contra
     */
    public String getDt_embauche_contra() {
        return dt_embauche_contra;
    }

    /**
     * @param dt_embauche_contra the dt_embauche_contra to set
     */
    public void setDt_embauche_contra(String dt_embauche_contra) {
        this.dt_embauche_contra = dt_embauche_contra;
    }

    /**
     * @return the dt_enter_contrat
     */
    public String getDt_enter_contrat() {
        return dt_enter_contrat;
    }

    /**
     * @param dt_enter_contrat the dt_enter_contrat to set
     */
    public void setDt_enter_contrat(String dt_enter_contrat) {
        this.dt_enter_contrat = dt_enter_contrat;
    }

    /**
     * @return the dt_sortie_contrat
     */
    public String getDt_sortie_contrat() {
        return dt_sortie_contrat;
    }

    /**
     * @param dt_sortie_contrat the dt_sortie_contrat to set
     */
    public void setDt_sortie_contrat(String dt_sortie_contrat) {
        this.dt_sortie_contrat = dt_sortie_contrat;
    }

    /**
     * @return the clasifi_contrat
     */
    public String getClasifi_contrat() {
        return clasifi_contrat;
    }

    /**
     * @param clasifi_contrat the clasifi_contrat to set
     */
    public void setClasifi_contrat(String clasifi_contrat) {
        this.clasifi_contrat = clasifi_contrat;
    }

    /**
     * @return the indice_contrat
     */
    public String getIndice_contrat() {
        return indice_contrat;
    }

    /**
     * @param indice_contrat the indice_contrat to set
     */
    public void setIndice_contrat(String indice_contrat) {
        this.indice_contrat = indice_contrat;
    }

    /**
     * @return the sal_base_contrat
     */
    public double getSal_base_contrat() {
        return sal_base_contrat;
    }

    /**
     * @param sal_base_contrat the sal_base_contrat to set
     */
    public void setSal_base_contrat(double sal_base_contrat) {
        this.sal_base_contrat = sal_base_contrat;
    }

    /**
     * @return the type_contrat
     */
    public String getType_contrat() {
        return type_contrat;
    }

    /**
     * @param type_contrat the type_contrat to set
     */
    public void setType_contrat(String type_contrat) {
        this.type_contrat = type_contrat;
    }

}
