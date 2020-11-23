/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forme;

import com.classe.DeuxChiffresApresVirgule;
import com.requete.Connecter_bd;
import com.requete.connections;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author VONINAHITRA
 */
public class salaire_globale extends javax.swing.JFrame {
    //Pour le salaire globale
//pour en une année
public  DefaultTableModel tab_globale;
private String num;
private String somme_base;
private String somme_brute;
//pour en un mois

//pour l'irsa
public  DefaultTableModel tabirsa;
private String numirsa;
private String nomirsa;
private String sommeirsa;
//pour le cnaps
public  DefaultTableModel tabcnaps;
private String numcnaps;
private String nomcnaps;
private String sommcnaps;
//pour le osief
public  DefaultTableModel tabosief;
private String numosief;
private String nomosief;
private String sommeosief;
ResultSet rse;
PreparedStatement ps;
PreparedStatement pse;
Statement st;
Statement ste;
Connection conc;
Connecter_bd con_db = new Connecter_bd();
connections maCon = new connections();
String query;
//pour le salaire globale par classification.
//pour en une année
public  DefaultTableModel tab_globale_classifi;
private String num_clasifi;
private String nom_clasifi;
private String somme_clasifi;
//pour en un mois



    /**
     * Creates new form Salaire_globale
     */
    public salaire_globale() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
 /*Salaire globale*/
 //pour la recherche en une année
 public void config_tab_annee() throws Exception{
        tab_globale = new DefaultTableModel();
        tab_globale.addColumn("Im");
        tab_globale.addColumn("Classification du personnel");
       // tab_globale.addColumn("Somme du salaire net par mois");
        tab_globale.addColumn("Somme du salaire brute");
        JTableHeader tb = table_salaire_globale_all.getTableHeader();
        tb.setBackground(new Color(0,51,102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
        table_salaire_globale_all.setModel(tab_globale);
     
      table_salaire_globale_all.getColumnModel().getColumn(0).setPreferredWidth(150);
      table_salaire_globale_all.getColumnModel().getColumn(0).setMaxWidth(150);
      table_salaire_globale_all.getColumnModel().getColumn(0).setMinWidth(150);
      
      table_salaire_globale_all.getColumnModel().getColumn(1).setPreferredWidth(400);
      table_salaire_globale_all.getColumnModel().getColumn(1).setMaxWidth(400);
      table_salaire_globale_all.getColumnModel().getColumn(1).setMinWidth(400);
      
      table_salaire_globale_all.getColumnModel().getColumn(2).setPreferredWidth(400);
      table_salaire_globale_all.getColumnModel().getColumn(2).setMaxWidth(400);
      table_salaire_globale_all.getColumnModel().getColumn(2).setMinWidth(400);
     
      recuperer_val_anne();
 }
public void recuperer_val_anne() throws Exception{ 
        tab_globale.setRowCount(0);//initialisation du table
        int annee = txt_annee_chercher.getYear();
        query = "select tab_contrat.num_perso, tab_contrat.clasifi_contrat, sum(tab_paie.totale_av_bulletin) as salaire_annee from tab_paie inner join tab_contrat on tab_contrat.num_perso = tab_paie.num_perso where tab_paie.annee_bulletin='"+annee+"' group by num_perso";
        //query = "select num_perso,sum(totale_av_bulletin) as salaire_annee from tab_paie where annee_bulletin='"+annee+"' group by num_perso ";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
while(rs.next()){
      num = rs.getString("num_perso");
      String clasifi = rs.getString("clasifi_contrat");
     // somme_base = rs.getString("sal_base_contrat");
      somme_brute = rs.getString("salaire_annee");
      Object[] anneeobj = {num, clasifi, somme_brute};
      tab_globale.addRow(anneeobj);
    }

    } 
//pour le rechere en un mois
//pour le mois
public void config_tab_mois() throws Exception{
        tab_globale = new DefaultTableModel();
        tab_globale.addColumn("Im");
        tab_globale.addColumn("Classification du personnel");
        tab_globale.addColumn("Somme du salaire brute");
        JTableHeader tb = table_salaire_globale_all.getTableHeader();
        tb.setBackground(new Color(0,51,102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
        table_salaire_globale_all.setModel(tab_globale);
        
        table_salaire_globale_all.getColumnModel().getColumn(0).setPreferredWidth(150);
        table_salaire_globale_all.getColumnModel().getColumn(0).setMaxWidth(150);
        table_salaire_globale_all.getColumnModel().getColumn(0).setMinWidth(150);
      
        table_salaire_globale_all.getColumnModel().getColumn(1).setPreferredWidth(400);
        table_salaire_globale_all.getColumnModel().getColumn(1).setMaxWidth(400);
        table_salaire_globale_all.getColumnModel().getColumn(1).setMinWidth(400);
      
        table_salaire_globale_all.getColumnModel().getColumn(2).setPreferredWidth(400);
        table_salaire_globale_all.getColumnModel().getColumn(2).setMaxWidth(400);
        table_salaire_globale_all.getColumnModel().getColumn(2).setMinWidth(400);
      
       recuperer_val_mois();
 }


public void recuperer_val_mois() throws Exception{ 
        tab_globale.setRowCount(0);//initialisation du table
         int mw = txt_mois_chercher.getMonth();
         int mois = mw+1;
Date actuelle = new Date();
DateFormat dateFormat = new SimpleDateFormat("yyyy");
String date_now = dateFormat.format(actuelle);
        query = "select tab_contrat.num_perso, tab_contrat.clasifi_contrat, sum(tab_paie.totale_av_bulletin) as salaire_annee from tab_paie inner join tab_contrat on tab_contrat.num_perso = tab_paie.num_perso where tab_paie.mois_bulletin='"+mois+"' and tab_paie.annee_bulletin='"+date_now+"' group by num_perso ";
       // query = "select num_perso,sum(totale_av_bulletin) as salaire_annee from tab_paie where annee_bulletin='"+annee+"' group by num_perso ";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
while(rs.next()){
      num = rs.getString("num_perso");
      somme_base = rs.getString("clasifi_contrat");
      somme_brute = rs.getString("salaire_annee");
      Object[] anneeobj = {num,somme_base,somme_brute};
      tab_globale.addRow(anneeobj);
    }
    } 

//pour salaire globale stanfdard
public void config_tab_standard_globale() throws Exception{
        tab_globale = new DefaultTableModel();
        tab_globale.addColumn("Numéro");
        tab_globale.addColumn("Classification du personnel");
        tab_globale.addColumn("Somme du salaire brute");
        JTableHeader tb = table_salaire_globale_all.getTableHeader();
        tb.setBackground(new Color(0,51,102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
       table_salaire_globale_all.setModel(tab_globale);
     
      table_salaire_globale_all.getColumnModel().getColumn(0).setPreferredWidth(150);
      table_salaire_globale_all.getColumnModel().getColumn(0).setMaxWidth(150);
      table_salaire_globale_all.getColumnModel().getColumn(0).setMinWidth(150);
      
      table_salaire_globale_all.getColumnModel().getColumn(1).setPreferredWidth(400);
      table_salaire_globale_all.getColumnModel().getColumn(1).setMaxWidth(400);
      table_salaire_globale_all.getColumnModel().getColumn(1).setMinWidth(400);
      
      table_salaire_globale_all.getColumnModel().getColumn(2).setPreferredWidth(400);
      table_salaire_globale_all.getColumnModel().getColumn(2).setMaxWidth(400);
      table_salaire_globale_all.getColumnModel().getColumn(2).setMinWidth(400);
      
      
     //recuperer_val_anne();
 }
// pour l'IRSA
public void config_tab_standard_irsa() throws Exception{
        tabirsa = new DefaultTableModel();
        tabirsa.addColumn("Numéro");
        tabirsa.addColumn("Classification du personnel");
        tabirsa.addColumn("Somme du salaire brute");
        JTableHeader tb = table_irsa.getTableHeader();
        tb.setBackground(new Color(0,51,102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
        table_irsa.setModel(tabirsa);
     
       table_irsa.getColumnModel().getColumn(0).setPreferredWidth(150);
       table_irsa.getColumnModel().getColumn(0).setMaxWidth(150);
       table_irsa.getColumnModel().getColumn(0).setMinWidth(150);
      
       table_irsa.getColumnModel().getColumn(1).setPreferredWidth(400);
       table_irsa.getColumnModel().getColumn(1).setMaxWidth(400);
       table_irsa.getColumnModel().getColumn(1).setMinWidth(400);
      
       table_irsa.getColumnModel().getColumn(2).setPreferredWidth(400);
       table_irsa.getColumnModel().getColumn(2).setMaxWidth(400);
       table_irsa.getColumnModel().getColumn(2).setMinWidth(400);
      
      
     //recuperer_val_anne();
 }
//pour le cnaps
public void config_tab_standard_cnaps() throws Exception{
        tabcnaps = new DefaultTableModel();
        tabcnaps.addColumn("Numéro");
        tabcnaps.addColumn("Classification du personnel");
        tabcnaps.addColumn("Somme du salaire brute");
        JTableHeader tb = table_cnaps.getTableHeader();
        tb.setBackground(new Color(0,51,102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
       table_cnaps.setModel( tabcnaps);
     
      table_cnaps.getColumnModel().getColumn(0).setPreferredWidth(150);
      table_cnaps.getColumnModel().getColumn(0).setMaxWidth(150);
      table_cnaps.getColumnModel().getColumn(0).setMinWidth(150);
      
      table_cnaps.getColumnModel().getColumn(1).setPreferredWidth(400);
      table_cnaps.getColumnModel().getColumn(1).setMaxWidth(400);
      table_cnaps.getColumnModel().getColumn(1).setMinWidth(400);
      
      table_cnaps.getColumnModel().getColumn(2).setPreferredWidth(400);
      table_cnaps.getColumnModel().getColumn(2).setMaxWidth(400);
      table_cnaps.getColumnModel().getColumn(2).setMinWidth(400);
      
      
     //recuperer_val_anne();
 }
public void config_tab_standard_osief() throws Exception{
         tabosief = new DefaultTableModel();
         tabosief.addColumn("Numéro");
         tabosief.addColumn("Classification du personnel");
         tabosief.addColumn("Somme du salaire brute");
        JTableHeader tb = table_osief.getTableHeader();
        tb.setBackground(new Color(0,51,102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
       table_osief.setModel( tabosief );
     
      table_osief.getColumnModel().getColumn(0).setPreferredWidth(150);
      table_osief.getColumnModel().getColumn(0).setMaxWidth(150);
      table_osief.getColumnModel().getColumn(0).setMinWidth(150);
      
      table_osief.getColumnModel().getColumn(1).setPreferredWidth(400);
      table_osief.getColumnModel().getColumn(1).setMaxWidth(400);
      table_osief.getColumnModel().getColumn(1).setMinWidth(400);
      
      table_osief.getColumnModel().getColumn(2).setPreferredWidth(400);
      table_osief.getColumnModel().getColumn(2).setMaxWidth(400);
      table_osief.getColumnModel().getColumn(2).setMinWidth(400);
     //recuperer_val_anne();
 }
//pour entre deux dates du salaire globale
public void config_tab_2td_globale() throws Exception{
        tab_globale = new DefaultTableModel();
        tab_globale.addColumn("Im");
        tab_globale.addColumn("Classification du personnel");
        tab_globale.addColumn("Somme du salaire brute");
        JTableHeader tb = table_salaire_globale_all.getTableHeader();
        tb.setBackground(new Color(0,51,102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
        table_salaire_globale_all.setModel(tab_globale);
        
        table_salaire_globale_all.getColumnModel().getColumn(0).setPreferredWidth(150);
        table_salaire_globale_all.getColumnModel().getColumn(0).setMaxWidth(150);
        table_salaire_globale_all.getColumnModel().getColumn(0).setMinWidth(150);
      
        table_salaire_globale_all.getColumnModel().getColumn(1).setPreferredWidth(400);
        table_salaire_globale_all.getColumnModel().getColumn(1).setMaxWidth(400);
        table_salaire_globale_all.getColumnModel().getColumn(1).setMinWidth(400);
      
        table_salaire_globale_all.getColumnModel().getColumn(2).setPreferredWidth(400);
        table_salaire_globale_all.getColumnModel().getColumn(2).setMaxWidth(400);
        table_salaire_globale_all.getColumnModel().getColumn(2).setMinWidth(400);
      
       recuperer_val_2dt();
 }

public void recuperer_val_2dt() throws Exception{ 
        tab_globale.setRowCount(0);//initialisation du table
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        String dt_du = dt.format(txt_dt_du_chercher.getDate());
        String dt_au = dt.format(txt_dt_au_chercher.getDate());
        /*query = "select tab_contrat.num_perso, tab_contrat.clasifi_contrat, sum(tab_paie.totale_av_bulletin) as salaire_annee "
                + "from tab_paie inner join tab_perso on tab_perso.num_perso = tab_paie.num_perso "
                + "where tab_paie.periode_du_bulletin BETWEEN'"+dt_du+"' and '"+dt_au+"' group by num_perso ";*/
       // query = "select num_perso,sum(totale_av_bulletin) as salaire_annee from tab_paie where annee_bulletin='"+annee+"' group by num_perso ";
      query = "select tab_contrat.num_perso, tab_contrat.clasifi_contrat, sum(tab_paie.totale_av_bulletin) as salaire_annee "
              + "from tab_paie inner join tab_contrat on tab_contrat.num_perso = tab_paie.num_perso where tab_paie.periode_du_bulletin BETWEEN '"+dt_du+"' and '"+dt_au+"' group by num_perso";
       
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
while(rs.next()){
      num = rs.getString("num_perso");
      somme_base = rs.getString("clasifi_contrat");
      somme_brute = rs.getString("salaire_annee");
      Object[] d2tajt = {num,somme_base,somme_brute};
      tab_globale.addRow(d2tajt);
    }
    }

//Pour l'IRSA
public void config_tab_2td_irsa() throws Exception{
        tab_globale = new DefaultTableModel();
        tab_globale.addColumn("Numéro");
        tab_globale.addColumn("Classification du personnel");
        tab_globale.addColumn("Somme du salaire brute");
        JTableHeader tb = table_salaire_globale_all.getTableHeader();
        tb.setBackground(new Color(0,51,102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
        table_salaire_globale_all.setModel(tab_globale);
        
        table_salaire_globale_all.getColumnModel().getColumn(0).setPreferredWidth(150);
        table_salaire_globale_all.getColumnModel().getColumn(0).setMaxWidth(150);
        table_salaire_globale_all.getColumnModel().getColumn(0).setMinWidth(150);
      
        table_salaire_globale_all.getColumnModel().getColumn(1).setPreferredWidth(400);
        table_salaire_globale_all.getColumnModel().getColumn(1).setMaxWidth(400);
        table_salaire_globale_all.getColumnModel().getColumn(1).setMinWidth(400);
      
        table_salaire_globale_all.getColumnModel().getColumn(2).setPreferredWidth(400);
        table_salaire_globale_all.getColumnModel().getColumn(2).setMaxWidth(400);
        table_salaire_globale_all.getColumnModel().getColumn(2).setMinWidth(400);
      
    //   recuperer_val_2dt();
 }
/*Pour le salaire globale par classification du personnel*/


//pour la recherche en une année par classification
 public void   config_tab_standard_globale_classifi() throws Exception{
        tab_globale_classifi = new DefaultTableModel();
        tab_globale_classifi.addColumn("Im");
        tab_globale_classifi.addColumn("Classification du personnel");
        tab_globale_classifi.addColumn("Somme du salaire brute");
        JTableHeader tb = table_salaire_globale_all_clasifi.getTableHeader();
        tb.setBackground(new Color(0,51,102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
     table_salaire_globale_all_clasifi.setModel(tab_globale_classifi);
     
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(0).setPreferredWidth(150);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(0).setMaxWidth(150);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(0).setMinWidth(150);
      
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(1).setPreferredWidth(200);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(1).setMaxWidth(200);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(1).setMinWidth(200);
      
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(2).setPreferredWidth(600);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(2).setMaxWidth(600);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(2).setMinWidth(600);
    // recuperer_val_anne_classifi();
 }
 
 public void config_tab_2dates_classifi() throws Exception{
        tab_globale_classifi = new DefaultTableModel();
        tab_globale_classifi.addColumn("Im");
        tab_globale_classifi.addColumn("Classification du personnel");
        tab_globale_classifi.addColumn("Somme du salaire brute");
        JTableHeader tb = table_salaire_globale_all_clasifi.getTableHeader();
        tb.setBackground(new Color(0,51,102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
       table_salaire_globale_all_clasifi.setModel(tab_globale_classifi);
     
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(0).setPreferredWidth(150);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(0).setMaxWidth(150);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(0).setMinWidth(150);
      
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(1).setPreferredWidth(200);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(1).setMaxWidth(200);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(1).setMinWidth(200);
      
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(2).setPreferredWidth(600);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(2).setMaxWidth(600);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(2).setMinWidth(600);
     //recuperer_val_anne_classifi();
 }
 
 public void val_2dates_classifi() throws SQLException{
    try {
       tab_globale_classifi.setRowCount(0);//initialisation du table
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        String dt_du = dt.format(txt_dt_du__clasifi.getDate());
        String dt_au = dt.format(txt_dt_du__clasifi.getDate());
        String clasifi = txt_clasifi.getSelectedItem().toString();
        /*query = "select tab_contrat.num_perso, tab_contrat.clasifi_contrat, sum(tab_paie.totale_av_bulletin) as salaire_annee "
        + "from tab_paie inner join tab_perso on tab_perso.num_perso = tab_paie.num_perso "
        + "where tab_paie.periode_du_bulletin BETWEEN'"+dt_du+"' and '"+dt_au+"' group by num_perso ";*/
        // query = "select num_perso,sum(totale_av_bulletin) as salaire_annee from tab_paie where annee_bulletin='"+annee+"' group by num_perso ";
        query = "select tab_contrat.num_perso, tab_contrat.clasifi_contrat, sum(tab_paie.totale_av_bulletin) as salaire_annee "
                + "from tab_paie inner join tab_contrat on tab_contrat.num_perso = tab_paie.num_perso where tab_paie.annee_bulletin "
                + "BETWEEN '"+dt_du+"' and '"+dt_au+"' and  tab_contrat.clasifi_contrat='"+clasifi+"' group by num_perso";
        
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            num = rs.getString("num_perso");
            somme_base = rs.getString("clasifi_contrat");
            somme_brute = rs.getString("salaire_annee");
            Object[] d2tajt = {num,somme_base,somme_brute};
           tab_globale_classifi.addRow(d2tajt);
        } 
    } catch (SQLException ex) {
        Logger.getLogger(salaire_globale.class.getName()).log(Level.SEVERE, null, ex);
    }
 }
 public void total_calsifi(){
   
 }
   public void config_tab_annee_classifi() throws Exception{
        tab_globale_classifi= new DefaultTableModel();
        tab_globale_classifi.addColumn("Im");
        tab_globale_classifi.addColumn("Classification du personnel");
        tab_globale_classifi.addColumn("Somme du salaire brute");
        JTableHeader tb = table_salaire_globale_all_clasifi.getTableHeader();
        tb.setBackground(new Color(0,51,102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
      table_salaire_globale_all_clasifi.setModel(tab_globale_classifi);
     
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(0).setPreferredWidth(150);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(0).setMaxWidth(150);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(0).setMinWidth(150);
      
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(1).setPreferredWidth(200);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(1).setMaxWidth(200);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(1).setMinWidth(200);
      
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(2).setPreferredWidth(600);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(2).setMaxWidth(600);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(2).setMinWidth(600);
     recuperer_val_anne_classifi();
 }
public void recuperer_val_anne_classifi() throws Exception{ 
        tab_globale_classifi.setRowCount(0);//initialisation du table
        int annee = txt_annee_chercher.getYear();
        String clasifi = txt_clasifi.getSelectedItem().toString();
         query = "select tab_contrat.num_perso, tab_contrat.clasifi_contrat, sum(tab_paie.totale_av_bulletin) as salaire_annee "
                 + "from tab_paie inner join tab_contrat on tab_contrat.num_perso = tab_paie.num_perso where tab_paie.annee_bulletin='"+annee+"'"
                 + " and  tab_contrat.clasifi_contrat='"+clasifi+"' group by num_perso";
       // query = "select num_perso,sum(totale_av_bulletin) as salaire_annee from tab_paie where annee_bulletin='"+annee+"' group by num_perso ";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
while(rs.next()){
      num = rs.getString("num_perso");
      somme_base = rs.getString("clasifi_contrat");
      somme_brute = rs.getString("salaire_annee");
      Object[] anneeobj = {num, somme_base, somme_brute};
      tab_globale_classifi.addRow(anneeobj);  
    }

    } 


public void config_tab_mois_classifi(){
        //String clasifi = txt_clasifi.getSelectedItem().toString();
        tab_globale_classifi.addColumn("Im");
        tab_globale_classifi.addColumn("Classification du personnel");
        tab_globale_classifi.addColumn("Somme du salaire brute");
        JTableHeader tb = table_salaire_globale_all.getTableHeader();
        tb.setBackground(new Color(0,51,102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
      table_salaire_globale_all_clasifi.setModel(tab_globale_classifi);
        
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(0).setPreferredWidth(150);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(0).setMaxWidth(150);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(0).setMinWidth(150);
      
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(1).setPreferredWidth(200);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(1).setMaxWidth(200);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(1).setMinWidth(200);
      
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(2).setPreferredWidth(600);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(2).setMaxWidth(600);
      table_salaire_globale_all_clasifi.getColumnModel().getColumn(2).setMinWidth(600);
    

}
 private void compteperso_vola_clasifi() throws SQLException {
       String clasifi = txt_clasifi.getSelectedItem().toString();
       int annee = txt_annee_clasifi.getYear();
       query = "select tab_contrat.num_perso, tab_contrat.clasifi_contrat, sum(tab_paie.totale_av_bulletin) as salaire_annee "
                 + "from tab_paie inner join tab_contrat on tab_contrat.num_perso = tab_paie.num_perso where tab_paie.annee_bulletin='"+annee+"'"
                 + " and  tab_contrat.clasifi_contrat='"+clasifi+"' group by clasifi_contrat";
       st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            String somme = rs.getString("salaire_annee");
            //txt_nbr_perso.setText(somme);
            txt_total_clasifi.setText(somme);
        } else {

        }
    }

 private void compteperso_vola_mois() throws SQLException {
Date actuelle = new Date();
DateFormat dateFormat = new SimpleDateFormat("yyyy");
String date_now = dateFormat.format(actuelle);
int mois = txt_mois_chercher.getMonth();
int mws = mois+1;
String mw = Integer.toString(mws);
        query = "select sum(tab_paie.totale_av_bulletin) as salaire from tab_paie where annee_bulletin='"+date_now+"' and mois_bulletin='"+mws+"'";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            String somme = rs.getString("salaire");
            //txt_nbr_perso.setText(somme);
            double vola = Double.parseDouble(somme);
            String drala =DeuxChiffresApresVirgule.ChiffresApresVirgule(vola);
            txt_somme_globale.setText(drala);
        } else {

        }
    }
 private void compteperso_vola_annee() throws SQLException {
      int annee = txt_annee_chercher.getYear();
      query = "select sum(tab_paie.totale_av_bulletin) as salaire from tab_paie where annee_bulletin='"+annee+"'";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            String somme = rs.getString("salaire");
            //txt_nbr_perso.setText(somme);
            double drala = Double.parseDouble(somme);
            String bob = DeuxChiffresApresVirgule.ChiffresApresVirgule(drala);
            txt_somme_globale.setText(bob);
        } else {

        }
    }
 private void compteperso_vola_2dates() throws SQLException {
     SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        String dt_du = dt.format(txt_dt_du_chercher.getDate());
        String dt_au = dt.format(txt_dt_au_chercher.getDate());
      int annee = txt_annee_chercher.getYear();
      query = "select sum(tab_paie.totale_av_bulletin) as salaire from tab_paie where tab_paie.periode_du_bulletin BETWEEN '"+dt_du+"' and '"+dt_au+"'";

        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            String somme = rs.getString("salaire");
            //txt_nbr_perso.setText(somme);
            double drala = Double.parseDouble(somme);
            String pify = DeuxChiffresApresVirgule.ChiffresApresVirgule(drala);
            txt_somme_globale.setText(pify);
        } else {

        }
    }

public void classfi() throws SQLException, Exception{
     con_db = new Connecter_bd();
    query = "select * from tab_contrat";
    ResultSet rs = con_db.executeQuery(query);
    while(rs.next()){
       String clasifi = rs.getString("clasifi_contrat").toString();
       txt_clasifi.addItem(clasifi);
   }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_salaire_globale_all = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_mois_chercher = new com.toedter.calendar.JMonthChooser();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_dt_du_chercher = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txt_dt_au_chercher = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_annee_chercher = new com.toedter.calendar.JYearChooser();
        btn_afficher_sal_base_annee = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        txt_somme_globale = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_osief = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_mois_chercher_osief = new com.toedter.calendar.JMonthChooser();
        btn_mois_osief = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_dt_du_chercher_osief = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txt_dt_au_chercher_osief = new com.toedter.calendar.JDateChooser();
        btn_2dates_osief = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_annee_chercher_osief = new com.toedter.calendar.JYearChooser();
        btn_annee_osief = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_irsa = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txt_mois_chercher_irsa = new com.toedter.calendar.JMonthChooser();
        btn_mois_irsa = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_dt_du_irsa = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        txt_dt_au_irsa = new com.toedter.calendar.JDateChooser();
        btn_2dates_irsa = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txt_annee_irsa = new com.toedter.calendar.JYearChooser();
        btn_annee_irsa = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_cnaps = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        txt_mois_cnaps = new com.toedter.calendar.JMonthChooser();
        btn_mois_cnaps = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txt_dt_du_cnaps = new com.toedter.calendar.JDateChooser();
        jLabel41 = new javax.swing.JLabel();
        txt_dt_au_cnbaps = new com.toedter.calendar.JDateChooser();
        btn_2dates_cnaps = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        txt_annee_chercher6 = new com.toedter.calendar.JYearChooser();
        btn_annee_cnaps = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_salaire_globale_all_clasifi = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        txt_mois_clasifi = new com.toedter.calendar.JMonthChooser();
        btn_mois_clasifi = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txt_dt_du__clasifi = new com.toedter.calendar.JDateChooser();
        jLabel47 = new javax.swing.JLabel();
        txt_dt_au__clasifi = new com.toedter.calendar.JDateChooser();
        btn_2dates_clasifi = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        txt_annee_clasifi = new com.toedter.calendar.JYearChooser();
        btn_annee_clasifi = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        txt_clasifi = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_total_clasifi = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(0, 51, 102));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102), 3)));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tunga", 0, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        table_salaire_globale_all.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_salaire_globale_all.setRowHeight(25);
        jScrollPane1.setViewportView(table_salaire_globale_all);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rechercher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("En un mois ");

        jButton2.setBackground(new java.awt.Color(0, 51, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_30_25px.png"))); // NOI18N
        jButton2.setText("Afficher");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Entre deux dates");

        jLabel4.setText("Du ");

        txt_dt_du_chercher.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Au");

        txt_dt_au_chercher.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setBackground(new java.awt.Color(0, 51, 102));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_2_25px.png"))); // NOI18N
        jButton3.setText("Afficher");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("En une année");

        btn_afficher_sal_base_annee.setBackground(new java.awt.Color(0, 51, 102));
        btn_afficher_sal_base_annee.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_afficher_sal_base_annee.setForeground(new java.awt.Color(255, 255, 255));
        btn_afficher_sal_base_annee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_1_25px.png"))); // NOI18N
        btn_afficher_sal_base_annee.setText("Afficher");
        btn_afficher_sal_base_annee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_afficher_sal_base_anneeActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Impression:");

        jButton10.setBackground(new java.awt.Color(0, 51, 102));
        jButton10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton10.setText("Imprimer");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(89, 89, 89))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dt_du_chercher, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dt_au_chercher, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_mois_chercher, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                        .addGap(89, 89, 89))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(btn_afficher_sal_base_annee, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel21))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(txt_annee_chercher, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_mois_chercher, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_annee_chercher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_afficher_sal_base_annee)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_dt_du_chercher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addComponent(txt_dt_au_chercher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addGap(14, 14, 14)
                .addComponent(jButton10)
                .addGap(84, 84, 84))
        );

        txt_somme_globale.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_somme_globale.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_somme_globale.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("AR");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Total :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_somme_globale, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(12, 12, 12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_somme_globale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Salaire globale  ", jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        table_osief.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_osief.setRowHeight(25);
        jScrollPane2.setViewportView(table_osief);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rechercher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("En un mois ");

        btn_mois_osief.setBackground(new java.awt.Color(0, 51, 102));
        btn_mois_osief.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_mois_osief.setForeground(new java.awt.Color(255, 255, 255));
        btn_mois_osief.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_30_25px.png"))); // NOI18N
        btn_mois_osief.setText("Afficher");
        btn_mois_osief.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mois_osiefActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Entre deux dates");

        jLabel8.setText("Du ");

        txt_dt_du_chercher_osief.setBackground(new java.awt.Color(255, 255, 255));
        txt_dt_du_chercher_osief.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setText("Au");

        txt_dt_au_chercher_osief.setBackground(new java.awt.Color(255, 255, 255));
        txt_dt_au_chercher_osief.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btn_2dates_osief.setBackground(new java.awt.Color(0, 51, 102));
        btn_2dates_osief.setForeground(new java.awt.Color(255, 255, 255));
        btn_2dates_osief.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_2_25px.png"))); // NOI18N
        btn_2dates_osief.setText("Afficher");
        btn_2dates_osief.setMaximumSize(new java.awt.Dimension(103, 33));
        btn_2dates_osief.setMinimumSize(new java.awt.Dimension(103, 33));
        btn_2dates_osief.setPreferredSize(new java.awt.Dimension(103, 33));
        btn_2dates_osief.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_2dates_osiefActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("En une année");

        btn_annee_osief.setBackground(new java.awt.Color(0, 51, 102));
        btn_annee_osief.setForeground(new java.awt.Color(255, 255, 255));
        btn_annee_osief.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_1_25px.png"))); // NOI18N
        btn_annee_osief.setText("Afficher");
        btn_annee_osief.setMaximumSize(new java.awt.Dimension(103, 33));
        btn_annee_osief.setMinimumSize(new java.awt.Dimension(103, 33));
        btn_annee_osief.setPreferredSize(new java.awt.Dimension(103, 33));
        btn_annee_osief.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_annee_osiefActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Impression");

        jButton11.setBackground(new java.awt.Color(0, 51, 102));
        jButton11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton11.setText("Imprimer tout");
        jButton11.setMaximumSize(new java.awt.Dimension(103, 33));
        jButton11.setMinimumSize(new java.awt.Dimension(103, 33));
        jButton11.setPreferredSize(new java.awt.Dimension(103, 33));

        jButton12.setBackground(new java.awt.Color(0, 51, 102));
        jButton12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton12.setText("Imprimer");
        jButton12.setMaximumSize(new java.awt.Dimension(103, 33));
        jButton12.setMinimumSize(new java.awt.Dimension(103, 33));
        jButton12.setPreferredSize(new java.awt.Dimension(103, 33));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dt_au_chercher_osief, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dt_du_chercher_osief, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel6))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(txt_mois_chercher_osief, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(txt_annee_chercher_osief, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_mois_osief, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_annee_osief, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_2dates_osief, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_mois_chercher_osief, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btn_mois_osief)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(txt_annee_chercher_osief, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(btn_annee_osief, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_dt_du_chercher_osief, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dt_au_chercher_osief, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(btn_2dates_osief, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Déclaration de l'OSIEF", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        table_irsa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_irsa.setRowHeight(25);
        jScrollPane3.setViewportView(table_irsa);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rechercher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("En un mois ");

        btn_mois_irsa.setBackground(new java.awt.Color(0, 51, 102));
        btn_mois_irsa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_mois_irsa.setForeground(new java.awt.Color(255, 255, 255));
        btn_mois_irsa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_30_25px.png"))); // NOI18N
        btn_mois_irsa.setText("Afficher");
        btn_mois_irsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mois_irsaActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Entre deux dates");

        jLabel19.setText("Du ");

        txt_dt_du_irsa.setBackground(new java.awt.Color(255, 255, 255));
        txt_dt_du_irsa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel20.setText("Au");

        txt_dt_au_irsa.setBackground(new java.awt.Color(255, 255, 255));
        txt_dt_au_irsa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btn_2dates_irsa.setBackground(new java.awt.Color(0, 51, 102));
        btn_2dates_irsa.setForeground(new java.awt.Color(255, 255, 255));
        btn_2dates_irsa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_2_25px.png"))); // NOI18N
        btn_2dates_irsa.setText("Afficher");
        btn_2dates_irsa.setMaximumSize(new java.awt.Dimension(103, 33));
        btn_2dates_irsa.setMinimumSize(new java.awt.Dimension(103, 33));
        btn_2dates_irsa.setPreferredSize(new java.awt.Dimension(103, 33));
        btn_2dates_irsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_2dates_irsaActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("En une année");

        btn_annee_irsa.setBackground(new java.awt.Color(0, 51, 102));
        btn_annee_irsa.setForeground(new java.awt.Color(255, 255, 255));
        btn_annee_irsa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_1_25px.png"))); // NOI18N
        btn_annee_irsa.setText("Afficher");
        btn_annee_irsa.setMaximumSize(new java.awt.Dimension(103, 33));
        btn_annee_irsa.setMinimumSize(new java.awt.Dimension(103, 33));
        btn_annee_irsa.setPreferredSize(new java.awt.Dimension(103, 33));
        btn_annee_irsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_annee_irsaActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setText("Impression");

        jButton15.setBackground(new java.awt.Color(0, 51, 102));
        jButton15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton15.setText("Imprimer tout");
        jButton15.setMaximumSize(new java.awt.Dimension(103, 33));
        jButton15.setMinimumSize(new java.awt.Dimension(103, 33));
        jButton15.setPreferredSize(new java.awt.Dimension(103, 33));

        jButton16.setBackground(new java.awt.Color(0, 51, 102));
        jButton16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton16.setText("Imprimer");
        jButton16.setMaximumSize(new java.awt.Dimension(103, 33));
        jButton16.setMinimumSize(new java.awt.Dimension(103, 33));
        jButton16.setPreferredSize(new java.awt.Dimension(103, 33));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dt_au_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dt_du_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel17))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(txt_mois_chercher_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel24))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(txt_annee_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_mois_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_annee_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_2dates_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_mois_chercher_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btn_mois_irsa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(txt_annee_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btn_annee_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_dt_du_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dt_au_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(btn_2dates_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Déclaration de l'IRSA", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        table_cnaps.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_cnaps.setRowHeight(25);
        jScrollPane4.setViewportView(table_cnaps);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rechercher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setText("En un mois ");

        btn_mois_cnaps.setBackground(new java.awt.Color(0, 51, 102));
        btn_mois_cnaps.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_mois_cnaps.setForeground(new java.awt.Color(255, 255, 255));
        btn_mois_cnaps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_30_25px.png"))); // NOI18N
        btn_mois_cnaps.setText("Afficher");
        btn_mois_cnaps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mois_cnapsActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel39.setText("Entre deux dates");

        jLabel40.setText("Du ");

        txt_dt_du_cnaps.setBackground(new java.awt.Color(255, 255, 255));
        txt_dt_du_cnaps.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel41.setText("Au");

        txt_dt_au_cnbaps.setBackground(new java.awt.Color(255, 255, 255));
        txt_dt_au_cnbaps.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btn_2dates_cnaps.setBackground(new java.awt.Color(0, 51, 102));
        btn_2dates_cnaps.setForeground(new java.awt.Color(255, 255, 255));
        btn_2dates_cnaps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_2_25px.png"))); // NOI18N
        btn_2dates_cnaps.setText("Afficher");
        btn_2dates_cnaps.setMaximumSize(new java.awt.Dimension(103, 33));
        btn_2dates_cnaps.setMinimumSize(new java.awt.Dimension(103, 33));
        btn_2dates_cnaps.setPreferredSize(new java.awt.Dimension(103, 33));
        btn_2dates_cnaps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_2dates_cnapsActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel42.setText("En une année");

        btn_annee_cnaps.setBackground(new java.awt.Color(0, 51, 102));
        btn_annee_cnaps.setForeground(new java.awt.Color(255, 255, 255));
        btn_annee_cnaps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_1_25px.png"))); // NOI18N
        btn_annee_cnaps.setText("Afficher");
        btn_annee_cnaps.setMaximumSize(new java.awt.Dimension(103, 33));
        btn_annee_cnaps.setMinimumSize(new java.awt.Dimension(103, 33));
        btn_annee_cnaps.setPreferredSize(new java.awt.Dimension(103, 33));
        btn_annee_cnaps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_annee_cnapsActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel43.setText("Impression");

        jButton27.setBackground(new java.awt.Color(0, 51, 102));
        jButton27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton27.setText("Imprimer tout");
        jButton27.setMaximumSize(new java.awt.Dimension(103, 33));
        jButton27.setMinimumSize(new java.awt.Dimension(103, 33));
        jButton27.setPreferredSize(new java.awt.Dimension(103, 33));

        jButton28.setBackground(new java.awt.Color(0, 51, 102));
        jButton28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton28.setForeground(new java.awt.Color(255, 255, 255));
        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton28.setText("Imprimer");
        jButton28.setMaximumSize(new java.awt.Dimension(103, 33));
        jButton28.setMinimumSize(new java.awt.Dimension(103, 33));
        jButton28.setPreferredSize(new java.awt.Dimension(103, 33));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dt_au_cnbaps, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dt_du_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel38))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(txt_mois_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel42))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(txt_annee_chercher6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_mois_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_annee_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_2dates_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_mois_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btn_mois_cnaps)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(txt_annee_chercher6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btn_annee_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_dt_du_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dt_au_cnbaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(btn_2dates_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jTabbedPane1.addTab("Déclaration de CNaPs", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        table_salaire_globale_all_clasifi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_salaire_globale_all_clasifi.setRowHeight(25);
        jScrollPane5.setViewportView(table_salaire_globale_all_clasifi);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rechercher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel44.setText("En un mois ");

        btn_mois_clasifi.setBackground(new java.awt.Color(0, 51, 102));
        btn_mois_clasifi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_mois_clasifi.setForeground(new java.awt.Color(255, 255, 255));
        btn_mois_clasifi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_30_25px.png"))); // NOI18N
        btn_mois_clasifi.setText("Afficher");
        btn_mois_clasifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mois_clasifiActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel45.setText("Entre deux dates");

        jLabel46.setText("Du ");

        txt_dt_du__clasifi.setBackground(new java.awt.Color(255, 255, 255));
        txt_dt_du__clasifi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel47.setText("Au");

        txt_dt_au__clasifi.setBackground(new java.awt.Color(255, 255, 255));
        txt_dt_au__clasifi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btn_2dates_clasifi.setBackground(new java.awt.Color(0, 51, 102));
        btn_2dates_clasifi.setForeground(new java.awt.Color(255, 255, 255));
        btn_2dates_clasifi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_2_25px.png"))); // NOI18N
        btn_2dates_clasifi.setText("Afficher");
        btn_2dates_clasifi.setMaximumSize(new java.awt.Dimension(103, 33));
        btn_2dates_clasifi.setMinimumSize(new java.awt.Dimension(103, 33));
        btn_2dates_clasifi.setPreferredSize(new java.awt.Dimension(103, 33));
        btn_2dates_clasifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_2dates_clasifiActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel48.setText("En une année");

        btn_annee_clasifi.setBackground(new java.awt.Color(0, 51, 102));
        btn_annee_clasifi.setForeground(new java.awt.Color(255, 255, 255));
        btn_annee_clasifi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Calendar_1_25px.png"))); // NOI18N
        btn_annee_clasifi.setText("Afficher");
        btn_annee_clasifi.setMaximumSize(new java.awt.Dimension(103, 33));
        btn_annee_clasifi.setMinimumSize(new java.awt.Dimension(103, 33));
        btn_annee_clasifi.setPreferredSize(new java.awt.Dimension(103, 33));
        btn_annee_clasifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_annee_clasifiActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel49.setText("Impression");

        jButton31.setBackground(new java.awt.Color(0, 51, 102));
        jButton31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton31.setText("Imprimer tout");
        jButton31.setMaximumSize(new java.awt.Dimension(103, 33));
        jButton31.setMinimumSize(new java.awt.Dimension(103, 33));
        jButton31.setPreferredSize(new java.awt.Dimension(103, 33));

        jButton32.setBackground(new java.awt.Color(0, 51, 102));
        jButton32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton32.setForeground(new java.awt.Color(255, 255, 255));
        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton32.setText("Imprimer");
        jButton32.setMaximumSize(new java.awt.Dimension(103, 33));
        jButton32.setMinimumSize(new java.awt.Dimension(103, 33));
        jButton32.setPreferredSize(new java.awt.Dimension(103, 33));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dt_au__clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dt_du__clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel44))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(txt_mois_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel48))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(txt_annee_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_mois_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_annee_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_2dates_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_mois_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btn_mois_clasifi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(txt_annee_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btn_annee_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_dt_du__clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_dt_au__clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(btn_2dates_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        txt_clasifi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M1-A1", "M2-1B", "OS1-2A", "OS2-2B", "OS3-3A", "P1A-3B", "OP1B-4A", "OP2A-4B", "OP2A-4B", "OP2B-5A", "OP3-5B", "HC" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Classificatio personnel :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Total :");

        txt_total_clasifi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("AR");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_total_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addComponent(txt_total_clasifi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Salaire globale par classification", jPanel8);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1230, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setText("Menu principale");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Return_25px.png"))); // NOI18N
        jMenuItem1.setText("Retour à l'accueil principale");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Activity_Feed_25px.png"))); // NOI18N
        jMenuItem2.setText("Afficher les activité");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Logout_Rounded_Left_25px.png"))); // NOI18N
        jMenuItem3.setText("Se déconnecter");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    try {
        config_tab_standard_globale();
        config_tab_standard_cnaps();
        config_tab_standard_osief();
        config_tab_standard_irsa();
        config_tab_standard_globale_classifi();
        txt_somme_globale.setText("0.0");
        //compteperso_vola();
      // config_tab_annee_classifi_standard() ;
    //  classfi();
    txt_total_clasifi.setText("0.0");
    } catch (Exception ex) {
        Logger.getLogger(salaire_globale.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void btn_afficher_sal_base_anneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_afficher_sal_base_anneeActionPerformed
    try {      
        config_tab_annee();
        compteperso_vola_annee();
       // compteperso_vola();
    } catch (Exception ex) {
        Logger.getLogger(salaire_globale.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }//GEN-LAST:event_btn_afficher_sal_base_anneeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    try {        
        config_tab_mois();
        compteperso_vola_mois();
       // compteperso_vola();
    } catch (Exception ex) {
        Logger.getLogger(salaire_globale.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    try {       
       config_tab_2td_globale();
       compteperso_vola_2dates();
//       compteperso_vola();
    } catch (Exception ex) {
        Logger.getLogger(salaire_globale.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       accueil_principale acu = new accueil_principale();
       acu.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      perso_entrep_contrat_avance_forme pecaf = new perso_entrep_contrat_avance_forme();
      pecaf.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       authentificaiton auth = new authentificaiton();
       auth.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btn_mois_osiefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mois_osiefActionPerformed
       
    }//GEN-LAST:event_btn_mois_osiefActionPerformed

    private void btn_2dates_osiefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_2dates_osiefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_2dates_osiefActionPerformed

    private void btn_annee_osiefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_annee_osiefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_annee_osiefActionPerformed

    private void btn_mois_irsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mois_irsaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_mois_irsaActionPerformed

    private void btn_2dates_irsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_2dates_irsaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_2dates_irsaActionPerformed

    private void btn_annee_irsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_annee_irsaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_annee_irsaActionPerformed

    private void btn_mois_cnapsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mois_cnapsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_mois_cnapsActionPerformed

    private void btn_2dates_cnapsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_2dates_cnapsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_2dates_cnapsActionPerformed

    private void btn_annee_cnapsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_annee_cnapsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_annee_cnapsActionPerformed

    private void btn_mois_clasifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mois_clasifiActionPerformed
    try {       
       config_tab_mois_classifi();
    } catch (Exception ex) {
        Logger.getLogger(salaire_globale.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btn_mois_clasifiActionPerformed

    private void btn_2dates_clasifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_2dates_clasifiActionPerformed
      
    }//GEN-LAST:event_btn_2dates_clasifiActionPerformed

    private void btn_annee_clasifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_annee_clasifiActionPerformed
    try {       
        config_tab_annee_classifi();
        compteperso_vola_clasifi();
    } catch (Exception ex) {
        Logger.getLogger(salaire_globale.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btn_annee_clasifiActionPerformed
class pdf{
public String personnel;
public double Mois1;
public double Mois2;
public double Mois3;
public double avantage1;
public double avantage2;
public double avantage3;
public double sommes;

}
@SuppressWarnings("empty-statement")
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
    try {
        int mois1 = txt_mois_chercher.getMonth()+1;
        int max = mois1+3;
        int tmp =mois1;
        query = "select count(emp) as nb from (select DISTINCT num_perso as emp from tab_osief)f";
        ResultSet rs = con_db.executeQuery(query);
        int nb=0;
        while(rs.next()){
            nb = rs.getInt("nb");   
        }
     String[][] tab1 = new String [nb][8];
     String[] tab2 = new String [8];
     String matricule = "select distinct num_perso from tab_osief";
     ResultSet rs2 = con_db.executeQuery(matricule);
     String perso[] = new String [nb];
     int i =0 ;
     while(rs2.next()){
           perso[i] = rs2.getString("num_perso"); 
           i++;
        }
    if(i!=0){
     int j=0;
     while(j<nb){
         while(mois1<max){
           String sql ="select num_perso as num,nom_perso as nom, avatnage as av,salaire_base as sal, temps as tmp where num_perso='"+perso[j]+"' and mois_osief='"+mois1+"'";
           ResultSet rs3 = con_db.executeQuery(sql);
           pdf pf = new pdf();
           while(rs3.next()){
               if(mois1==tmp){
                pf.Mois1 = rs3.getDouble("sal"); 
                pf.avantage1 = rs3.getDouble("av");
               }else if(tmp==mois1-1){
                pf.Mois2 = rs3.getDouble("sal"); 
                pf.avantage2 = rs3.getDouble("av");
               }else if(tmp==mois1-2){
                pf.Mois3 = rs3.getDouble("sal"); 
                pf.avantage3 = rs3.getDouble("av");
               }    
           }  
           mois1++;
         }
//        mjuhyetab2 = {perso[j], pf.Mois1, pf.avantage1, pf.Mois2, pf.avantage2, pf.Mois3, pf.avantage3}; 
        mois1 = tmp;
        j++;
     }
    }
    } catch (Exception ex) {
        Logger.getLogger(salaire_globale.class.getName()).log(Level.SEVERE, null, ex);
    }
      
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(salaire_globale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(salaire_globale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(salaire_globale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(salaire_globale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new salaire_globale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_2dates_clasifi;
    private javax.swing.JButton btn_2dates_cnaps;
    private javax.swing.JButton btn_2dates_irsa;
    private javax.swing.JButton btn_2dates_osief;
    private javax.swing.JButton btn_afficher_sal_base_annee;
    private javax.swing.JButton btn_annee_clasifi;
    private javax.swing.JButton btn_annee_cnaps;
    private javax.swing.JButton btn_annee_irsa;
    private javax.swing.JButton btn_annee_osief;
    private javax.swing.JButton btn_mois_clasifi;
    private javax.swing.JButton btn_mois_cnaps;
    private javax.swing.JButton btn_mois_irsa;
    private javax.swing.JButton btn_mois_osief;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable table_cnaps;
    private javax.swing.JTable table_irsa;
    private javax.swing.JTable table_osief;
    private javax.swing.JTable table_salaire_globale_all;
    private javax.swing.JTable table_salaire_globale_all_clasifi;
    private com.toedter.calendar.JYearChooser txt_annee_chercher;
    private com.toedter.calendar.JYearChooser txt_annee_chercher6;
    private com.toedter.calendar.JYearChooser txt_annee_chercher_osief;
    private com.toedter.calendar.JYearChooser txt_annee_clasifi;
    private com.toedter.calendar.JYearChooser txt_annee_irsa;
    private javax.swing.JComboBox<String> txt_clasifi;
    private com.toedter.calendar.JDateChooser txt_dt_au__clasifi;
    private com.toedter.calendar.JDateChooser txt_dt_au_chercher;
    private com.toedter.calendar.JDateChooser txt_dt_au_chercher_osief;
    private com.toedter.calendar.JDateChooser txt_dt_au_cnbaps;
    private com.toedter.calendar.JDateChooser txt_dt_au_irsa;
    private com.toedter.calendar.JDateChooser txt_dt_du__clasifi;
    private com.toedter.calendar.JDateChooser txt_dt_du_chercher;
    private com.toedter.calendar.JDateChooser txt_dt_du_chercher_osief;
    private com.toedter.calendar.JDateChooser txt_dt_du_cnaps;
    private com.toedter.calendar.JDateChooser txt_dt_du_irsa;
    private com.toedter.calendar.JMonthChooser txt_mois_chercher;
    private com.toedter.calendar.JMonthChooser txt_mois_chercher_irsa;
    private com.toedter.calendar.JMonthChooser txt_mois_chercher_osief;
    private com.toedter.calendar.JMonthChooser txt_mois_clasifi;
    private com.toedter.calendar.JMonthChooser txt_mois_cnaps;
    private javax.swing.JTextField txt_somme_globale;
    private javax.swing.JLabel txt_total_clasifi;
    // End of variables declaration//GEN-END:variables
}
