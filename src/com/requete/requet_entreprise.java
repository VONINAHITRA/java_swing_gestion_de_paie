/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import com.classe.classe_entreprise;

/**
 *
 * @author VONINAHITRA
 */
public class requet_entreprise {
public  Connecter_bd con;
private String query;
public classe_entreprise clp; 
public requet_entreprise(){
con = new Connecter_bd();
}
//Requete Pour ajout 
public void ajouter(classe_entreprise e) throws Exception{
query = "insert into tab_entrep  values('"+e.getNum_entrep()+"', '"+e.getDesi_entrep()+"', '"+e.getAdrs_entrep()+"', '"+e.getTel_entrep()+"','"+e.getEmail_entrep()+"','"+e.getForm_juridique_entrep()+"')";
con.executeUpdate(query);
}
//Requet pour modification
public void modifier(String ref, classe_entreprise e) throws Exception{
query = "update tab_entrep set num_entrep='"+e.getNum_entrep()+"',desi_entrep='"+e.getDesi_entrep()+"', adrs_entrep='"+e.getAdrs_entrep()+"',tel_entrep='"+e.getTel_entrep()+"', email_entrep='"+e.getEmail_entrep()+"',form_juridique_entrep='"+e.getForm_juridique_entrep()+"' where num_entrep='"+ref+"'" ;
con.executeUpdate(query);
}
//Requete pour suppression
public void supprimer(String p) throws Exception{
query = "delete from tab_entrep where num_entrep ='"+p+"'";
con.executeUpdate(query);
}
}
