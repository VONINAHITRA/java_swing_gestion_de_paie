/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import com.classe.classe_personnel;

/**
 *
 * @author VONINAHITRA
 */
public class requet_personnel {
public  Connecter_bd con;
private String query;
public classe_personnel clp;

public requet_personnel(){
con = new Connecter_bd();
}
//Requete Pour ajout 
public void ajouter(classe_personnel p) throws Exception{
query = "insert into tab_perso  values('"+p.getNum_perso()+"', '"+p.getNom_pre_perso()+"', '"+p.getSexe()+"',"
        + " '"+p.getDt_emb_perso()+"','"+p.getSitu_perso()+"','"+p.getNbr_enf_perso()+"','"+p.getAdrs_perso()+"',"
        + "'"+p.getTel_perso()+"' )";
con.executeUpdate(query);
}
//Requete Pour ajout 
public void copieajouter(classe_personnel p) throws Exception{
query = "insert into copie_tab_perso  values('"+p.getNum_perso()+"', '"+p.getNom_pre_perso()+"', '"+p.getSexe()+"',"
        + " '"+p.getDt_emb_perso()+"','"+p.getSitu_perso()+"','"+p.getNbr_enf_perso()+"','"+p.getAdrs_perso()+"',"
        + "'"+p.getTel_perso()+"' )";
con.executeUpdate(query);
}
//Requet pour modification
public void modifier(String ref, classe_personnel p) throws Exception{
query = "update tab_perso set num_perso='"+p.getNum_perso()+"',nom_pre_perso='"+p.getNom_pre_perso()+"',"
        + " sexe_perso='"+p.getSexe()+"',dt_nais_perso='"+p.getDt_emb_perso()+"', situ_perso='"+p.getSitu_perso()+"'"
        + ",nbr_enf_perso='"+p.getNbr_enf_perso()+"',adrs_perso ='"+p.getAdrs_perso()+"', tel_perso ='"+p.getTel_perso()+"'"
        + " where num_perso='"+ref+"'" ;
con.executeUpdate(query);
}
//Requete pour suppression
public void supprimer(String p) throws Exception{
query = "delete from tab_perso where num_perso ='"+p+"'";
con.executeUpdate(query);
}
//Requete pour suppression
public void supprimerc(String p) throws Exception{
query = "delete from tab_contrat where num_perso ='"+p+"'";
con.executeUpdate(query);
}
   
}

