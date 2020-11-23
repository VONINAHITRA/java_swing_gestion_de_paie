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
public class classe_entreprise {
    private String num_entrep;
    private String desi_entrep;
    private String adrs_entrep;
    private String tel_entrep;
    private String email_entrep;
    private String form_juridique_entrep;
public classe_entreprise(){
}
public classe_entreprise(String num_entrep,String desi_entrep,String adrs_entrep,String tel_entrep,String email_entrep,String form_juridique_entrep){
 this.num_entrep = num_entrep;
 this.desi_entrep = desi_entrep;
 this.adrs_entrep = adrs_entrep;
 this.tel_entrep = tel_entrep;
 this.email_entrep = email_entrep;
 this.form_juridique_entrep = form_juridique_entrep;
} 

    /**
     * @return the num_entrep
     */
    public String getNum_entrep() {
        return num_entrep;
    }

    /**
     * @param num_entrep the num_entrep to set
     */
    public void setNum_entrep(String num_entrep) {
        this.num_entrep = num_entrep;
    }

    /**
     * @return the desi_entrep
     */
    public String getDesi_entrep() {
        return desi_entrep;
    }

    /**
     * @param desi_entrep the desi_entrep to set
     */
    public void setDesi_entrep(String desi_entrep) {
        this.desi_entrep = desi_entrep;
    }

    /**
     * @return the adrs_entrep
     */
    public String getAdrs_entrep() {
        return adrs_entrep;
    }

    /**
     * @param adrs_entrep the adrs_entrep to set
     */
    public void setAdrs_entrep(String adrs_entrep) {
        this.adrs_entrep = adrs_entrep;
    }

    /**
     * @return the tel_entrep
     */
    public String getTel_entrep() {
        return tel_entrep;
    }

    /**
     * @param tel_entrep the tel_entrep to set
     */
    public void setTel_entrep(String tel_entrep) {
        this.tel_entrep = tel_entrep;
    }

    /**
     * @return the email_entrep
     */
    public String getEmail_entrep() {
        return email_entrep;
    }

    /**
     * @param email_entrep the email_entrep to set
     */
    public void setEmail_entrep(String email_entrep) {
        this.email_entrep = email_entrep;
    }

    /**
     * @return the form_juridique_entrep
     */
    public String getForm_juridique_entrep() {
        return form_juridique_entrep;
    }

    /**
     * @param form_juridique_entrep the form_juridique_entrep to set
     */
    public void setForm_juridique_entrep(String form_juridique_entrep) {
        this.form_juridique_entrep = form_juridique_entrep;
    }

   
}
