/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import com.classe.classe_avance;
import com.classe.classe_contrat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author VONINAHITRA
 */
public class requet_avance {
public  Connecter_bd con;
private String query;
public  classe_contrat cla; 
public requet_avance(){
con = new Connecter_bd();
}
//Requete Pour ajout 
public void ajouter(classe_avance a) throws Exception{
query = "insert into tab_avance  values(null,'"+a.getNum_perso()+"','"+a.getNom_pre_avance()+"','"+a.getDt_now_avance()+"'"
        + ",'"+a.getMois_avance()+"','"+a.getAnnee_avance()+"','"+a.getPeriode_du_avance()+"','"+a.getPeriode_au_avance()+"',"
        + "'"+a.getTotale_avance()+"','"+a.getReste_salaire()+"','"+a.getEnlettre()+"')";
con.executeUpdate(query);
}
//Requet pour modification
public void modifier(String ref ,int mois, classe_avance a,int annee) throws Exception{
query = "update tab_avance set num_perso='"+a.getNum_perso()+"', total_avance='"+a.getTotale_avance()+"',"
        + " dt_now_avance='"+a.getDt_now_avance()+"', mois_avance='"+a.getMois_avance()+"',annee_avance='"+a.getAnnee_avance()+"'"
        + ",periode_du_avance='"+a.getPeriode_du_avance()+"', periode_au_avance='"+a.getPeriode_au_avance()+"',"
        + "reste_salaire='"+a.getReste_salaire()+"',nom_pre_avance='"+a.getNom_pre_avance()+"',enlettre='"+a.getEnlettre()+"' "
        + "where num_perso='"+ref+"' and mois_avance='"+mois+"' and annee_avance='"+annee+"'";
con.executeUpdate(query);
}
//Requete pour suppression
public void supprimer(String avc, String dt) throws Exception{
Date date_now = new Date();
DateFormat dateFormat_now_an = new SimpleDateFormat("yyyy");
String dt_an_now = dateFormat_now_an.format(date_now);
query = "delete from tab_avance where num_perso ='"+avc+"' and dt_now_avance='"+dt+"'";
con.executeUpdate(query);
}
}
