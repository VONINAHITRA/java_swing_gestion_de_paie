/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VONINAHITRA
 */
public class Connecter_bd {
    public Connection cnx;
    public Statement stat;
    private static Connection connnexion = null;
    private static Statement statment = null;
    private static ResultSet resultset = null;
    private static int resultat = 0;
    
//Configuration de la connction
    public static void fermerConnexion(){
    try {
            resultset.close();
            statment.close();
            connnexion.close();
        }catch (SQLException ex) {
            System.out.println("erreur 1 :"+ex.toString());
        }
    }
    
//Connction à la base de données
 public Connecter_bd(){
     Constante cnst = new Constante();
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            cnx = DriverManager.getConnection(Constante.url,Constante.user,Constante.passwd);
            stat=cnx.createStatement();
           
        }catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
}
 
 //execution de la requette pour la selection  
 public ResultSet executeQuery (String query)throws Exception{
        ResultSet res=null;
        res=stat.executeQuery(query);
        return(res);
    }
 
 //excution de la requette pour la moidification et la modification
 public int executeUpdate(String query)throws Exception{
         int res=0;
        res=stat.executeUpdate(query);
        return(res);
 }
 
 //fermeture de la connction
 public void close() throws Exception{
        stat.close();
        cnx.close();
    }
}
