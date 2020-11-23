/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author VONINAHITRA
 */
public class connections {
    String urlPilote="org.gjt.mm.mysql.Driver";
    String urlDB="jdbc:mysql://localhost:3306/payement";
    Connection con;
    public connections(){
        // chargement de pilote
        try{
            Class.forName(urlPilote);
            System.out.println("Pilote charger avec succès");
        }
        catch(ClassNotFoundException ex){
            System.out.println(ex);
        }
        //connexion à la base de donnée
        try{
            con = DriverManager.getConnection(urlDB,"root","");
            System.out.println("connexion à la base de donnée reussi");
        }
        catch(SQLException ex){
           System.out.println(ex); 
        }
    }
 public Connection Obtenirconnexion(){
    return con;
    }
}
