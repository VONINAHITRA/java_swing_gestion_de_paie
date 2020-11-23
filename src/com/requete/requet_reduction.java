/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import com.classe.classe_contrat;
import com.classe.classe_reduction;

/**
 *
 * @author VONINAHITRA
 */
public class requet_reduction {
public  Connecter_bd con;
private String query;
public  classe_reduction cls; 
public requet_reduction(){
con = new Connecter_bd();
}
//Requete Pour ajout 
public void ajouter(classe_reduction r) throws Exception{
query = "insert into tab_reduc values(null,'"+r.getNum_perso()+"', '"+r.getDt_now_reduc()+"',"
        + " '"+r.getMois_reduc()+"', '"+r.getAnnee_reduc()+"', '"+r.getPdu_reduc()+"', "
        + "'"+r.getPau_reduc()+"', '"+r.getCnaps_reduc()+"', '"+r.getOsief_reduc()+"',"
        + "'"+r.getIrsa_reduc()+"','"+r.getCess_sal_reduc()+"','"+r.getNouri_log_reduc()+"',"
        + "'"+r.getAutres_reduc()+"','"+r.getAvance_reduc()+"','"+r.getTotale_brute_reduc()+"')";
con.executeUpdate(query);
}
//Requet pour modification
public void modifier(String ref, classe_reduction r) throws Exception{
query = "update tab_reduc set num_perso='"+r.getNum_perso()+"',dt_now_reduc='"+r.getDt_now_reduc()+"', "
        + "mois_reduc='"+r.getMois_reduc()+"', annee_reduc='"+r.getAnnee_reduc()+"',"
        + " periode_du_reduc='"+r.getPdu_reduc()+"', periode_au_reduc='"+r.getPau_reduc()+"', "
        + "cnaps_reduc='"+r.getCnaps_reduc()+"', osief_reduc='"+r.getOsief_reduc()+"', "
        + "irsa_reduc='"+r.getIrsa_reduc()+"' , cess_sal_reduc='"+r.getCess_sal_reduc()+"',"
        + " nouri_log_reduc='"+r.getNouri_log_reduc()+"', autres_reduc='"+r.getAutres_reduc()+"',avance_reduc='"+r.getAvance_reduc()+"',"
        + "totale_brute_reduc='"+r.getTotale_brute_reduc()+"' where num_perso='"+ref+"'" ;
con.executeUpdate(query);
}
//Requete pour suppression
public void supprimer(String r, String dt) throws Exception{
query = "delete from tab_reduc where num_perso ='"+r+"' and dt_now_reduc='"+dt+"'";
con.executeUpdate(query);
}
}
