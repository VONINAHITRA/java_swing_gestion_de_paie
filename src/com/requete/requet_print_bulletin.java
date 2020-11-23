/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import com.classe.classe_print_bulletin;
import com.classe.classe_reduction;

/**
 *
 * @author VONIANAHITRA
 */
public class requet_print_bulletin {
 public  Connecter_bd con;
private String query;
public  classe_reduction cls; 
public requet_print_bulletin(){
    con = new Connecter_bd();
}
//Ajouter
public void ajouter(classe_print_bulletin b) throws Exception{
    query = "insert into print_bulletin  values(null,'"+b.getNom()+"','"+b.getNum()+"','"+b.getClassifi()+"','"+b.getDt_paye()+"','"+b.getSal_base()+"','"+b.getIndice()+"','"+b.getVoca()+"','"+b.getSup8()+"','"+b.getSup8_plus()+"','"+b.getIdem()+"','"+b.getAvances()+"','"+b.getNouri()+"','"+b.getCess_sal()+"','"+b.getCnaps()+"','"+b.getOsief()+"','"+b.getIrsa()+"','"+b.getAutres()+"','"+b.getTotal_brute()+"','"+b.getTotal_reduc()+"','"+b.getMontant_payer()+"','"+b.getLettre()+"','"+b.getPdu()+"','"+b.getPau()+"','"+b.getHeure40()+"','"+b.getAnciente()+"')";
    con.executeUpdate(query);   
}
//Modifier
public void modifer(String ref,String dt, classe_print_bulletin b) throws Exception{
    query = "update print_bulletin  set nom='"+b.getNom()+"', matricule='"+b.getNum()+"',clasification='"+b.getClassifi()+"',payer_du='"+b.getDt_paye()+"',salaire_base='"+b.getSal_base()+"',indice='"+b.getIndice()+"',vocation='"+b.getVoca()+"',heure30='"+b.getSup8()+"',heure50='"+b.getSup8_plus()+"',idemnite='"+b.getIndice()+"',avance='"+b.getAvances()+"',nouriture='"+b.getNouri()+"',cession='"+b.getCess_sal()+"',cnaps='"+b.getCnaps()+"',osief='"+b.getOsief()+"',irsa='"+b.getIrsa()+"',autre_reduc='"+b.getAutres()+"',totale_brute='"+b.getTotal_brute()+"', totale_reduc='"+b.getTotal_reduc()+"',montantt_payer='"+b.getMontant_payer()+"',enlettres='"+b.getLettre()+"',pdu='"+b.getPdu()+"',pau='"+b.getPau()+"' where matricule='"+ref+"' and payer_du='"+dt+"'";
    con.executeUpdate(query);   
}

//Supprimer
public void supprimer(String bul,String dt) throws Exception{
query = "delete from print_bulletin where matricule ='"+bul+"' and payer_du='"+dt+"'";
con.executeUpdate(query);
} 

}
