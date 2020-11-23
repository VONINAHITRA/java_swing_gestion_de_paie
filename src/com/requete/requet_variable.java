/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import com.classe.classe_irsa;
import com.classe.classe_variable;

/**
 *
 * @author VONIANAHITRA
 */
public class requet_variable {
  public  Connecter_bd con;
private String query;
public classe_variable clvar; 
public requet_variable(){
con = new Connecter_bd();
}
public void ajouter(classe_variable v) throws Exception{
   query= "insert into variable values(null,'"+v.getCnaps()+"', '"+v.getIrsa()+"', '"+v.getOsief()+"', '"+v.getAllocation()+"', '"+v.getSuple()+"')";
   con.executeUpdate(query);
}
 
}
