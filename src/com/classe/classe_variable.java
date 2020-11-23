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
public class classe_variable {
    private double cnaps;
    private double irsa;
    private double allocation;
    private double osief;
    private double suple;  
public classe_variable(){   
} 
 public classe_variable(double cnaps,double irsa,double osief,double allocation,double suple){   
this.cnaps =cnaps;
this.irsa =irsa;
this.osief =osief;
this.allocation =allocation;
this.suple =suple;
 
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
     * @return the allocation
     */
    public double getAllocation() {
        return allocation;
    }

    /**
     * @param allocation the allocation to set
     */
    public void setAllocation(double allocation) {
        this.allocation = allocation;
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
     * @return the suple
     */
    public double getSuple() {
        return suple;
    }

    /**
     * @param suple the suple to set
     */
    public void setSuple(double suple) {
        this.suple = suple;
    }

}
