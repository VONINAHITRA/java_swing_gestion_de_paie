/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import com.classe.classe_contrat;
import com.classe.classe_irsa;
import com.classe.classe_personnel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VONIANAHITRA
 */
public class requet_irsa {
public  Connecter_bd con;
private String query;
public classe_irsa cli; 
public requet_irsa(){
con = new Connecter_bd();
}

public void ajouter(classe_irsa i) throws Exception{
query = "insert into tba_irsa values(null,'"+i.getNum_perso()+"','"+i.getNom_perso()+"','"+i.getElment()+"','"+i.getSal_base()+"','"+i.getSal_brutes()+"','"+i.getCnaps()+"','"+i.getOsief()+"','"+i.getAbt_for()+"','"+i.getBas_impose()+"','"+i.getImpo_cores()+"','"+i.getEnfant()+"','"+i.getCharge_fami()+"','"+i.getImpot_du()+"','"+i.getMois_irsa()+"','"+i.getAnnee_irsa()+"','"+i.getAutres_reduc()+"','"+i.getDate()+"')";
con.executeUpdate(query);
  
}
public void supprimer(String p, String mw, int an) throws Exception{
query = "delete from tba_irsa where num_perso ='"+p+"' and date='"+mw+"' and annee_irsa='"+an+"'";
con.executeUpdate(query);
}
}