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
public class classe_print_bulletin {
   private String num; 
   private String nom;
   private String classifi;
   private int indice;
   private double sal_base;
   private double voca;
   private double idem;
   private double sup8;
   private double sup8_plus;
   private double total_brute;
   private double cnaps;
   private double osief;
   private double irsa;
   private double cess_sal;
   private double nouri;
   private double autres;
   private double avances;
   private double total_reduc;
   private String lettre;
   private double montant_payer;
   private String dt_paye;
   private String pdu;
   private String pau;
   private double anciente;
   private double heure40;
public classe_print_bulletin(){
}
public classe_print_bulletin(String nom,String num,String classifi ,String dt_paye
,double sal_base ,int indice ,double voca ,double sup8,double sup8_plus ,double idem ,
double avances , double nouri,double cess_sal ,double cnaps ,double osief ,double irsa,double autres ,
double total_brute,double total_reduc , double montant_payer, String lettre,String pdu, String pau,double heure40,double anciente){
 this.num = num;
 this.nom=nom ;
 this.classifi=classifi ;
 this.indice=indice ;
 this.sal_base=sal_base ;
 this.voca=voca ;
 this.idem=idem;
 this.sup8=sup8 ;
 this.sup8_plus=sup8_plus ;
 this.total_brute=total_brute ;
 this.cnaps=cnaps;
 this.osief=osief ;
 this.irsa=irsa;
 this.cess_sal=cess_sal ;
 this.nouri=nouri ;
 this.autres=autres ;
 this.avances=avances ;
 this.total_reduc= total_reduc;
 this.lettre=lettre;
 this.montant_payer=montant_payer ;
 this.dt_paye=dt_paye;
 this.pdu = pdu;
 this.pau = pau;
 this.heure40 = heure40;
 this.anciente = anciente;
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
     * @return the classifi
     */
    public String getClassifi() {
        return classifi;
    }

    /**
     * @param classifi the classifi to set
     */
    public void setClassifi(String classifi) {
        this.classifi = classifi;
    }

    /**
     * @return the indice
     */
    public int getIndice() {
        return indice;
    }

    /**
     * @param indice the indice to set
     */
    public void setIndice(int indice) {
        this.indice = indice;
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
     * @return the voca
     */
    public double getVoca() {
        return voca;
    }

    /**
     * @param voca the voca to set
     */
    public void setVoca(double voca) {
        this.voca = voca;
    }

    /**
     * @return the idem
     */
    public double getIdem() {
        return idem;
    }

    /**
     * @param idem the idem to set
     */
    public void setIdem(double idem) {
        this.idem = idem;
    }

    /**
     * @return the sup8
     */
    public double getSup8() {
        return sup8;
    }

    /**
     * @param sup8 the sup8 to set
     */
    public void setSup8(double sup8) {
        this.sup8 = sup8;
    }

    /**
     * @return the sup8_plus
     */
    public double getSup8_plus() {
        return sup8_plus;
    }

    /**
     * @param sup8_plus the sup8_plus to set
     */
    public void setSup8_plus(double sup8_plus) {
        this.sup8_plus = sup8_plus;
    }

    /**
     * @return the total_brute
     */
    public double getTotal_brute() {
        return total_brute;
    }

    /**
     * @param total_brute the total_brute to set
     */
    public void setTotal_brute(double total_brute) {
        this.total_brute = total_brute;
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
     * @return the irsa
     */
    public double getIrsa() {
        return irsa;
    }

    /**
     * @param irsa the irsa to set
     */
    public void setIrsa(double irsa) {
        this.irsa = irsa;
    }

    /**
     * @return the cess_sal
     */
    public double getCess_sal() {
        return cess_sal;
    }

    /**
     * @param cess_sal the cess_sal to set
     */
    public void setCess_sal(double cess_sal) {
        this.cess_sal = cess_sal;
    }

    /**
     * @return the nouri
     */
    public double getNouri() {
        return nouri;
    }

    /**
     * @param nouri the nouri to set
     */
    public void setNouri(double nouri) {
        this.nouri = nouri;
    }

    /**
     * @return the autres
     */
    public double getAutres() {
        return autres;
    }

    /**
     * @param autres the autres to set
     */
    public void setAutres(double autres) {
        this.autres = autres;
    }

    /**
     * @return the avances
     */
    public double getAvances() {
        return avances;
    }

    /**
     * @param avances the avances to set
     */
    public void setAvances(double avances) {
        this.avances = avances;
    }

    /**
     * @return the total_reduc
     */
    public double getTotal_reduc() {
        return total_reduc;
    }

    /**
     * @param total_reduc the total_reduc to set
     */
    public void setTotal_reduc(double total_reduc) {
        this.total_reduc = total_reduc;
    }

    /**
     * @return the lettre
     */
    public String getLettre() {
        return lettre;
    }

    /**
     * @param lettre the lettre to set
     */
    public void setLettre(String lettre) {
        this.lettre = lettre;
    }

    /**
     * @return the montant_payer
     */
    public double getMontant_payer() {
        return montant_payer;
    }

    /**
     * @param montant_payer the montant_payer to set
     */
    public void setMontant_payer(double montant_payer) {
        this.montant_payer = montant_payer;
    }

    /**
     * @return the dt_paye
     */
    public String getDt_paye() {
        return dt_paye;
    }

    /**
     * @param dt_paye the dt_paye to set
     */
    public void setDt_paye(String dt_paye) {
        this.dt_paye = dt_paye;
    }

    /**
     * @return the pdu
     */
    public String getPdu() {
        return pdu;
    }

    /**
     * @param pdu the pdu to set
     */
    public void setPdu(String pdu) {
        this.pdu = pdu;
    }

    /**
     * @return the pau
     */
    public String getPau() {
        return pau;
    }

    /**
     * @param pau the pau to set
     */
    public void setPau(String pau) {
        this.pau = pau;
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
     * @return the heure40
     */
    public double getHeure40() {
        return heure40;
    }

    /**
     * @param heure40 the heure40 to set
     */
    public void setHeure40(double heure40) {
        this.heure40 = heure40;
    }

   

   
}
