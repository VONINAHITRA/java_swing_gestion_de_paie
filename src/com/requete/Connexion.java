/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author VONIANAHITRA
 */
public class Connexion {
    	Connection con;
    public Connexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
       }catch(ClassNotFoundException e){
            System.err.println(e);
       }
       try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/payement","root","");
            System.out.println("connexion reussie");
       }catch(SQLException e){
           System.err.println(e);
       }
    
    }
    public Connection obtenirconnexion()
    {
        return con;
    }
}
