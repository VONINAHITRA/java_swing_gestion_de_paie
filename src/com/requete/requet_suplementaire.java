/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;
import com.classe.classe_contrat;
import com.classe.classe_suplemetaire;

/**
 *
 * @author VONINAHITRA
 */
public class requet_suplementaire {
public  Connecter_bd con;
private String query;
public  classe_suplemetaire cls; 
public requet_suplementaire(){
con = new Connecter_bd();
}
//Requete Pour ajout 
public void ajouter(classe_suplemetaire s) throws Exception{
query = "insert into tab_av  values(null,'"+s.getNum_perso()+"', '"+s.getDt_now_av()+"', '"+s.getMois_av()+"', '"+s.getAnnee_av()+"', '"+s.getPeriode_du_av()+"', '"+s.getPeriode_au_av()+"','"+s.getIdemnite_av()+"','"+s.getAnciente()+"','"+s.getVoca_piece_av()+"','"+s.getNbrhers_sup_av()+"','"+s.getNbheure40()+"','"+s.getHrs_sup8_av()+"','"+s.getHrs_sup_plus8_av()+"','"+s.getVal_heures_40()+"','"+s.getTotale_brute_av()+"')";
con.executeUpdate(query);
}
//Requet pour modification
public void modifier(String ref, classe_suplemetaire s) throws Exception{
query = "update tab_av set num_perso='"+s.getNum_perso()+"',dt_now_av='"+s.getDt_now_av()+"',mois_av='"+s.getMois_av()+"',annee_av='"+s.getAnnee_av()+"',periode_du_av='"+s.getPeriode_du_av()+"',periode_au_av='"+s.getPeriode_au_av()+"',idemnite_av='"+s.getIdemnite_av()+"',anciente='"+s.getAnciente()+"',voca_piece_av='"+s.getVoca_piece_av()+"',nbrhers_sup_av='"+s.getNbrhers_sup_av()+"',nbrheure40='"+s.getNbheure40()+"',hrs_sup8_av='"+s.getHrs_sup8_av()+"',hrs_sup_plus8_av='"+s.getHrs_sup_plus8_av()+"',val_heures_40='"+s.getVal_heures_40()+"',totale_brute_av='"+s.getTotale_brute_av()+"' where num_perso='"+ref+"'";
con.executeUpdate(query);
}
//Requete pour suppression
public void supprimer(String supl, String dt) throws Exception{
query = "delete from tab_av where num_perso ='"+supl+"' and dt_now_av='"+dt+"'";
con.executeUpdate(query);
}
}