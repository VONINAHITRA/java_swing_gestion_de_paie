/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import com.classe.classe_personnel;
import com.classe.classe_user;

/**
 *
 * @author VONIANAHITRA
 */
public class requet_user {
public  Connecter_bd con;
private String query;
public classe_user clu;

public requet_user(){
con = new Connecter_bd();
}
public void ajouter(classe_user u) throws Exception{
query = "insert into utilisateur  values(null,'"+u.getUser()+"', '"+u.getPwd()+"', '"+u.getPrivillege()+"')";
con.executeUpdate(query);
}
public void supprimer(String u, String pwd, String privi) throws Exception{
query = "delete from utilisateur where  mot_de_passe_user='"+u+"' and nom_user='"+pwd+"' and privilege='"+privi+"'";
con.executeUpdate(query);
}

}
