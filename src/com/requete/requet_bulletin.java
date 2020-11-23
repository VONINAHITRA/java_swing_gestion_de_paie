/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import com.classe.classe_bulletin;
import com.classe.classe_contrat;
import com.classe.classe_suplemetaire;

/**
 *
 * @author VONINAHITRA
 */
public class requet_bulletin {
  public  Connecter_bd con;
private String query;
public  classe_suplemetaire cls; 
public requet_bulletin(){
con = new Connecter_bd();
}
//Requete Pour ajout 
public void ajouter(classe_bulletin b) throws Exception{
query = "insert into tab_paie  values(null,'"+b.getNum_perso()+"','"+b.getNom_pre_bulletin()+"','"+b.getDt_now_bulletin()+"', '"+b.getMois_bulletin()+"', '"+b.getAnnee_bulletin()+"', '"+b.getPdu_bulletin()+"', '"+b.getPau_bulletin()+"','"+b.getTotale_reduc_bulletin()+"','"+b.getTotale_av_bulletin()+"','"+b.getTotale_net_bulltin()+"','"+b.getSal_base()+"','"+b.getSimple_av()+"')";
con.executeUpdate(query);
}
//Requet pour modification
public void modifier(String ref,int mois, classe_bulletin b) throws Exception{
query = "update tab_paie set num_perso='"+b.getNum_perso()+"',nom_pre_bulletin = '"+b.getNom_pre_bulletin()+"', "
        + "dt_now_bulletin='"+b.getDt_now_bulletin()+"', mois_bulletin ='"+b.getMois_bulletin()+"', "
        + "annee_bulletin='"+b.getAnnee_bulletin()+"', periode_du_bulletin='"+b.getPdu_bulletin()+"', "
        + "periode_au_bulletin='"+b.getPau_bulletin()+"', total_reduc_bulletin='"+b.getTotale_reduc_bulletin()+"', "
        + "totale_av_bulletin='"+b.getTotale_av_bulletin()+"', totale_net_bulletin='"+b.getTotale_net_bulltin()+"',"
        + "sal_base='"+b.getSal_base()+"', simple_av = '"+b.getSimple_av()+"' where num_perso='"+ref+"' and mois_bulletin='"+mois+"'" ;
con.executeUpdate(query);
}
//Requete pour suppression
public void supprimer(String bul,String dt) throws Exception{
query = "delete from tab_paie where num_perso ='"+bul+"' and dt_now_bulletin='"+dt+"'";
con.executeUpdate(query);
}  
}
