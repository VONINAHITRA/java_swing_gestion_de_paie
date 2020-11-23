/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import com.classe.classe_osief;

/**
 *
 * @author VONIANAHITRA
 */
public class requet_osief {
public  Connecter_bd con;
private String query;
public classe_osief cli; 
public requet_osief(){
con = new Connecter_bd();
}  
public void ajouter(classe_osief f) throws Exception{
query = "insert into tab_osief values(null,'"+f.getNum()+"','"+f.getNom()+"','"+f.getAvantage()+"','"+f.getSal_base()+"','"+f.getTemps()+"','"+f.getMois()+"','"+f.getAnnee()+"')";
con.executeUpdate(query);   
}
}
