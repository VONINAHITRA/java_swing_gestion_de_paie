/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import com.classe.classe_contrat;
import com.classe.classe_entreprise;

/**
 *
 * @author VONINAHITRA
 */
public class requet_contrat {
public  Connecter_bd con;
private String query;
public  classe_contrat clc; 
public requet_contrat(){
con = new Connecter_bd();
}
//Requete Pour ajout 
public void ajouter(classe_contrat c) throws Exception{
query = "insert into tab_contrat  values(null,'"+c.getMatricule()+"','"+c.getNom_pre_contrat()+"','"+c.getService_contrat()+"','"+c.getDt_now_contrat()+"', "
        + "'"+c.getDt_embauche_contra()+"','"+c.getDt_enter_contrat()+"', '"+c.getDt_sortie_contrat()+"','"+c.getClasifi_contrat()+"','"+c.getIndice_contrat()+"',"
        + "'"+c.getSal_base_contrat()+"',"
        + "'"+c.getType_contrat()+"')";
con.executeUpdate(query);
}
//Requet pour modification
public void modifier(String ref, classe_contrat c) throws Exception{
query = "update tab_contrat set num_perso='"+c.getMatricule()+"',nom_pre_contrat = '"+c.getNom_pre_contrat()+"',service= '"+c.getService_contrat()+"', dt_now_contrat='"+c.getDt_now_contrat()+"',date_embauche='"+c.getDt_embauche_contra()+"',dt_entre_contrat='"+c.getDt_enter_contrat()+"', dt_sortie_contrat='"+c.getDt_sortie_contrat()+"', clasifi_contrat='"+c.getClasifi_contrat()+"', indice_contrat='"+c.getIndice_contrat()+"',sal_base_contrat='"+c.getSal_base_contrat()+"',type_contrat='"+c.getType_contrat()+"' where num_perso='"+ref+"'" ;
con.executeUpdate(query);
}
//Requete pour suppression
public void supprimer(String c) throws Exception{
query = "delete from tab_contrat where num_perso ='"+c+"'";
con.executeUpdate(query);
}
}
