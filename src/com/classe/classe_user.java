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
public class classe_user {
private String user;
   private String pwd;
   private String privillege;
 public classe_user(){
 }
 public classe_user(String user, String pwd, String privillege){
  this.user = user;
  this.pwd = pwd;
  this.privillege = privillege;
 }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the privillege
     */
    public String getPrivillege() {
        return privillege;
    }

    /**
     * @param privillege the privillege to set
     */
    public void setPrivillege(String privillege) {
        this.privillege = privillege;
    }
}
