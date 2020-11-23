/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forme;

import com.classe.DeuxChiffresApresVirgule;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.requete.Connecter_bd;
import com.requete.Connexion;
import com.requete.connections;
import com.requete.requet_impression;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.lang.reflect.Array;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JRException;
import static org.hibernate.criterion.Expression.sql;
import org.hsqldb.Types;

/**
 *
 * @author VONIANAHITRA
 */
public class declaration extends javax.swing.JFrame {
    PreparedStatement ps;
    PreparedStatement pse;
    Statement st;
    Statement ste;
    Connection conc;
    Connecter_bd con_db = new Connecter_bd();
    connections maCon = new connections();
    String query;
    ResultSet rs;

////////////////////////////////personnel///////////////////////////////////////
    public DefaultTableModel tab_osief1er;
    private String num_perso;
    private String num_perso2;
    private String sexe_perso;
    private String dt_perso;
    private String situ_perso;
    private String nb_enf_perso;
    private String adrs_perso ;
    private String tel_perso; 
    public int mois;
     /* Creates new form declaration
     */
    //pour Cnaps
    public DefaultTableModel tab_cnaps;
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
//pour le salaire globale par classification.
//pour en une année
public  DefaultTableModel tab_globale_classifi;
private String num_clasifi;
private String nom_clasifi;
private String somme_clasifi;
//pour en un mois
    public declaration() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
     
       // tab_globale.addColumn("Classification du personnel");


/*public void recuperer_val_anne() throws Exception{ 
        tab_globale.setRowCount(0);//initialisation du table
        int annee = txt_annee_chercher.getYear();
        query = "select tab_contrat.num_perso, tab_contrat.clasifi_contrat,tab_paie.nom_pre_bulletin as nom, sum(tab_paie.totale_av_bulletin) as salaire_annee from tab_paie inner join tab_contrat on tab_contrat.num_perso = tab_paie.num_perso where tab_paie.annee_bulletin='"+annee+"'";
        //query = "select num_perso,sum(totale_av_bulletin) as salaire_annee from tab_paie where annee_bulletin='"+annee+"' group by num_perso ";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
while(rs.next()){
      num = rs.getString("num_perso");
      String nom = rs.getString("nom");
      String clasifi = rs.getString("clasifi_contrat");
     //somme_base = rs.getString("sal_base_contrat");
      somme_brute = rs.getString("salaire_annee");
      Object[] anneeobj = {num,nom,clasifi, somme_brute};
      tab_globale.addRow(anneeobj);
    }

    } */
//pour le rechere en un mois
//pour le mois
//pour salaire globale stanfdard

    //Délcaration osief 3mois
private void compteperso_vola_osief() throws SQLException {
Date actuelle = new Date();
DateFormat dateFormat = new SimpleDateFormat("yyyy");
String date_now = dateFormat.format(actuelle);
//int mois = txt_mois_chercher.getMonth();
//int mws = mois+1;
//String mw = Integer.toString(mws);
String an ="2018";
        query = "select sum(tab_paie.totale_av_bulletin) as salaire from tab_paie";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        if(rs.next()) {
            String somme = rs.getString("salaire");
            //txt_nbr_perso.setText(somme);
            double vola = Double.parseDouble(somme);
            double cotis = ((vola*8)/100);
            String cot = Double.toString(cotis);
            //String drala =DeuxChiffresApresVirgule.ChiffresApresVirgule(vola);
            //txt_somme_globale.setText(drala);
            txt_somme_osief.setText(somme);
            txt_cotisation.setText(cot);
        }else{
            txt_somme_osief.setText("0.0");
        } 
        
    }
 public void recuperer_val_osief() throws Exception {
        tab_osief1er.setRowCount(0);//initialisation du table
        String d1 = "01/01/2018";
        String d2= "01/03/2018";
        int date1 = date1er2.getMonth()+1;
        int date2 = jMonthChooser4.getMonth()+1;
        /*
         Object[] perso = {moiw,num_perso,nom_perso,sal_base,brute,annee};
         tab_osief1er.addRow(perso);
        */
        query ="select tab_paie.num_perso as num, tab_paie.nom_pre_bulletin as nom,"
                + "tab_paie.sal_base as sal_base,tab_paie.simple_av as av,tab_paie.totale_av_bulletin as total_av,"
                + "tab_paie.annee_bulletin as annee,tab_paie.mois_bulletin as mois,"
                + "(select sum(tab_paie.totale_av_bulletin) from tab_paie  where annee_bulletin='"+annee_osief2.getYear()+"' and tab_paie.mois_bulletin between '"+date1+"' and '"+date2+"'"
                + " order by tab_paie.mois_bulletin) as total,(select (sum(tab_paie.totale_av_bulletin)*2/100) from tab_paie "
                + " where annee_bulletin='"+annee_osief2.getYear()+"' and tab_paie.mois_bulletin between '"+date1+"' and '"+date2+"' order by tab_paie.mois_bulletin) as pourcent "
                + "from tab_paie where annee_bulletin='"+annee_osief2.getYear()+"' and tab_paie.mois_bulletin between '"+date1+"' and '"+date2+"' order by tab_paie.mois_bulletin";
       // query = " select * from tab_paie ";
      // query = "select * from tab_paie where mois_bulletin between '"+date1+"' and '"+date2+"' order by mois_bulletin ";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            num_perso = rs.getString("num");
            String  nom_perso = rs.getString("nom");
            String moiw = rs.getString("mois");
            int moiso = Integer.parseInt(moiw);
            String annee= rs.getString("annee");
            String sal_base = rs.getString("sal_base");  
            String  brute = rs.getString("total_av");
            String pourcent = rs.getString("pourcent");
            String total = rs.getString("total");
            txt_somme_osief.setText(total);
            txt_cotisation.setText(pourcent);
            double sal = Double.parseDouble(sal_base);
            double avs = Double.parseDouble(brute);
             //double  av= (avs-sal);
            String somme = rs.getString("av");
        switch (moiso) {
                case 12:
                    String deco = "Décembre";
                    Object[] dec = {deco,annee,num_perso,nom_perso,sal_base,somme,brute};
                    tab_osief1er.addRow(dec);
                    break;
                case 11:
                    String nov = "Novembre";
                    Object[] novo = {nov,annee,num_perso,nom_perso,sal_base,somme,brute};
                    tab_osief1er.addRow(novo);
                    break;
                case 10:
                    String oct = "Octobre";
                    Object[] octo = {oct,annee,num_perso,nom_perso,sal_base,somme,brute};
                tab_osief1er.addRow(octo);
                    break;
                case 9:
                   String septo = "Octobre";
                    Object[]sept = {septo,annee,num_perso,nom_perso,sal_base,somme,brute};
                tab_osief1er.addRow(sept);
                    break;
                case 8:
                    String outo = "Août";
                  Object[]   aou = {outo,annee,num_perso,nom_perso,sal_base,somme,brute};
            tab_osief1er.addRow(aou);
                    break;
                case 7:
                    String juilo = "Juillet";
                    Object[]  juil= {juilo,annee,num_perso,nom_perso,sal_base,somme,brute};
            tab_osief1er.addRow(juil);
                    break;
                case 6:
                    String juino = "Juin";
                   Object[]  jui = {juino,annee,num_perso,nom_perso,sal_base,somme,brute};
                   tab_osief1er.addRow(jui);
                    break;
                case 5:
                    String maio = "Mai";
                   Object[] mai = {maio,annee,num_perso,nom_perso,sal_base,somme,brute};
            tab_osief1er.addRow(mai);
                    break;
                case 4:
                    String avro = "Avril";
                   Object[] avr = {avro,annee,num_perso,nom_perso,sal_base,somme,brute};
            tab_osief1er.addRow(avr);
                    break;
                case 3:
                    String marsbo= "Mars";
                    Object[] mar = {marsbo,annee,num_perso,nom_perso,sal_base,somme,brute};
                 tab_osief1er.addRow(mar);
                    break;
                case 2:
                    String fevo = "Févrièr";
                    Object[] fev = {fevo,annee,num_perso,nom_perso,sal_base,somme,brute};
                 tab_osief1er.addRow(fev);
                    break;
                case 1:
                    String javo = "Janvier";
                    Object[] jav = {javo,annee,num_perso,nom_perso,sal_base,somme,brute};
                    tab_osief1er.addRow(jav);
                    break;
            }
           
        }
    }
 
public void config_tab_osief() throws Exception {
        tab_osief1er= new DefaultTableModel();
        tab_osief1er.addColumn("Mois");
        tab_osief1er.addColumn("Année");
        tab_osief1er.addColumn("Matricule");
        tab_osief1er.addColumn("Nom et prénom");
        tab_osief1er.addColumn("Salaire de base");   
        tab_osief1er.addColumn("Avantages");
        tab_osief1er.addColumn("Total salaire");
        JTableHeader tb = table_osief.getTableHeader();
        tb.setBackground(new Color(0,102,153));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
       table_osief.setBackground(Color.WHITE);
       table_osief.setModel(tab_osief1er);
       table_osief.getColumnModel().getColumn(0).setPreferredWidth(100);
       table_osief.getColumnModel().getColumn(0).setMaxWidth(100);
       table_osief.getColumnModel().getColumn(0).setMinWidth(100);

       table_osief.getColumnModel().getColumn(1).setPreferredWidth(100);
       table_osief.getColumnModel().getColumn(1).setMaxWidth(100);
       table_osief.getColumnModel().getColumn(1).setMinWidth(100);

       table_osief.getColumnModel().getColumn(2).setPreferredWidth(150);
       table_osief.getColumnModel().getColumn(2).setMaxWidth(150);
       table_osief.getColumnModel().getColumn(2).setMinWidth(150);

       table_osief.getColumnModel().getColumn(3).setPreferredWidth(350);
       table_osief.getColumnModel().getColumn(3).setMaxWidth(350);
       table_osief.getColumnModel().getColumn(3).setMinWidth(350);

       table_osief.getColumnModel().getColumn(4).setPreferredWidth(150);
       table_osief.getColumnModel().getColumn(4).setMaxWidth(150);
       table_osief.getColumnModel().getColumn(4).setMinWidth(150);

       table_osief.getColumnModel().getColumn(5).setPreferredWidth(190);
       table_osief.getColumnModel().getColumn(5).setMaxWidth(150);
       table_osief.getColumnModel().getColumn(5).setMinWidth(150);
recuperer_val_osief();
       //recuperer_val_p();
     //  osief_trois_mois();
    }
//table standard osief
public void tabla_standard_osief() throws Exception {
        tab_osief1er= new DefaultTableModel();
        tab_osief1er.addColumn("Mois");
        tab_osief1er.addColumn("Année");
        tab_osief1er.addColumn("Matricule");
        tab_osief1er.addColumn("Nom et prénom");
        tab_osief1er.addColumn("Salaire de base");   
        tab_osief1er.addColumn("Avantages");
        tab_osief1er.addColumn("Total salaire");
        JTableHeader tb = table_osief.getTableHeader();
        tb.setBackground(new Color(0,102,153));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
       table_osief.setBackground(Color.WHITE);
       table_osief.setModel(tab_osief1er);
       table_osief.getColumnModel().getColumn(0).setPreferredWidth(100);
       table_osief.getColumnModel().getColumn(0).setMaxWidth(100);
       table_osief.getColumnModel().getColumn(0).setMinWidth(100);

       table_osief.getColumnModel().getColumn(1).setPreferredWidth(100);
       table_osief.getColumnModel().getColumn(1).setMaxWidth(100);
       table_osief.getColumnModel().getColumn(1).setMinWidth(100);

       table_osief.getColumnModel().getColumn(2).setPreferredWidth(150);
       table_osief.getColumnModel().getColumn(2).setMaxWidth(150);
       table_osief.getColumnModel().getColumn(2).setMinWidth(150);

       table_osief.getColumnModel().getColumn(3).setPreferredWidth(350);
       table_osief.getColumnModel().getColumn(3).setMaxWidth(350);
       table_osief.getColumnModel().getColumn(3).setMinWidth(350);

       table_osief.getColumnModel().getColumn(4).setPreferredWidth(150);
       table_osief.getColumnModel().getColumn(4).setMaxWidth(150);
       table_osief.getColumnModel().getColumn(4).setMinWidth(150);

       table_osief.getColumnModel().getColumn(5).setPreferredWidth(150);
       table_osief.getColumnModel().getColumn(5).setMaxWidth(150);
       table_osief.getColumnModel().getColumn(5).setMinWidth(150);
    }

public void recuperer_val_cnaps() throws Exception {
        tab_cnaps.setRowCount(0);//initialisation du table
        String d1 = "01/01/2018";
        String d2= "01/03/2018";
        int datecnaps1 = mois_cnaps1.getMonth()+1;
        int datecnaps2 = mois_cnaps2.getMonth()+1;
        
        /*
         Object[] perso = {moiw,num_perso,nom_perso,sal_base,brute,annee};
            tab_osief1er.addRow(perso);
        */
       // query = " select * from tab_paie ";
       /* query = "select tab_paie.num_perso as num,tab_paie.nom_pre_bulletin as nom,"
                + "tab_paie.mois_bulletin as mois,tab_paie.annee_bulletin as annee,"
                + "tab_paie.sal_base as sal_base from tab_paie where tab_paie.annee_bulletin "
                + "between '"+date1+"' and '"+date2+"'";*/
       //query = "select * from tab_paie where mois_bulletin between '"+date1+"' and '"+date2+"' order by mois_bulletin ";
       query ="select tab_paie.num_perso as num, tab_paie.nom_pre_bulletin as nom,"
                + "tab_paie.sal_base as sal_base,tab_paie.simple_av as av,tab_paie.totale_av_bulletin as total_av,"
                + "tab_paie.annee_bulletin as annee,tab_paie.mois_bulletin as mois,"
                + "(select sum(tab_paie.totale_av_bulletin) from tab_paie  where annee_bulletin='"+annee_cnaps.getYear()+"' and tab_paie.mois_bulletin between '"+datecnaps1+"' and '"+datecnaps2+"'"
                + " order by tab_paie.mois_bulletin) as total,(select (sum(tab_paie.totale_av_bulletin)*1/100) from tab_paie "
                + " where annee_bulletin='"+annee_cnaps.getYear()+"' and tab_paie.mois_bulletin between '"+datecnaps1+"' and '"+datecnaps2+"' order by tab_paie.mois_bulletin) as pourcent "
                + "from tab_paie where annee_bulletin='"+annee_cnaps.getYear()+"' and tab_paie.mois_bulletin between '"+datecnaps1+"' and '"+datecnaps2+"' order by tab_paie.mois_bulletin";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            num_perso = rs.getString("num");
            String  nom_perso = rs.getString("nom");
            String moiw = rs.getString("mois");
            int moiso = Integer.parseInt(moiw);
            String annee= rs.getString("annee");
            String sal_base = rs.getString("sal_base");  
            String pourcent =rs.getString("pourcent");
            String total =rs.getString("total_av");
            String av =rs.getString("av");
            String hafa =rs.getString("total");
            cotisatio_cnaps.setText(pourcent);
            totale_cnaps.setText(hafa);
        switch (moiso) {
                case 12:
                    String deco = "Décembre";
                    Object[] dec = {deco,annee,num_perso,nom_perso,sal_base,av,total};
                    tab_cnaps.addRow(dec);
                    break;
                case 11:
                    String nov = "Novembre";
                    Object[] novo = {nov,annee,num_perso,nom_perso,sal_base,av,total};
                    tab_cnaps.addRow(novo);
                    break;
                case 10:
                    String oct = "Octobre";
                    Object[] octo = {oct,annee,num_perso,nom_perso,sal_base,av,total};
                tab_cnaps.addRow(octo);
                    break;
                case 9:
                   String septo = "Octobre";
                    Object[]sept = {septo,annee,num_perso,nom_perso,sal_base,av,total};
                tab_cnaps.addRow(sept);
                    break;
                case 8:
                    String outo = "Août";
                  Object[]   aou = {outo,annee,num_perso,nom_perso,sal_base,av,total};
            tab_cnaps.addRow(aou);
                    break;
                case 7:
                    String juilo = "Juillet";
                    Object[]  juil= {juilo,annee,num_perso,nom_perso,sal_base,av,total};
           tab_cnaps.addRow(juil);
                    break;
                case 6:
                    String juino = "Juin";
                   Object[]  jui = {juino,annee,num_perso,nom_perso,sal_base,av,total};
                   tab_cnaps.addRow(jui);
                    break;
                case 5:
                    String maio = "Mai";
                   Object[] mai = {maio,annee,num_perso,nom_perso,sal_base,av,total};
            tab_cnaps.addRow(mai);
                    break;
                case 4:
                    String avro = "Avril";
                   Object[] avr = {avro,annee,num_perso,nom_perso,sal_base,av,total};
            tab_cnaps.addRow(avr);
                    break;
                case 3:
                    String marsbo= "Mars";
                    Object[] mar = {marsbo,annee,num_perso,nom_perso,sal_base,av,total};
                tab_cnaps.addRow(mar);
                    break;
                case 2:
                    String fevo = "Févrièr";
                    Object[] fev = {fevo,annee,num_perso,nom_perso,sal_base,av,total};
                 tab_cnaps.addRow(fev);
                    break;
                case 1:
                    String javo = "Janvier";
                    Object[] jav = {javo,annee,num_perso,nom_perso,sal_base,av,total};
                    tab_cnaps.addRow(jav);
                    break;
            }
           
        }
    }
public void config_tab_cnaps() throws Exception{
       tab_cnaps= new DefaultTableModel();
        tab_cnaps.addColumn("Mois");
        tab_cnaps.addColumn("Année");
        tab_cnaps.addColumn("Matricule");
        tab_cnaps.addColumn("Nom et prénom");
        tab_cnaps.addColumn("Salaire de base");   
        tab_cnaps.addColumn("Avantages");
        tab_cnaps.addColumn("Total salaire personnel");
        JTableHeader tb = table_cnaps.getTableHeader();
        tb.setBackground(new Color(0,102,153));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
       table_cnaps.setBackground(Color.WHITE);
       table_cnaps.setModel(tab_cnaps);
       table_cnaps.getColumnModel().getColumn(0).setPreferredWidth(100);
       table_cnaps.getColumnModel().getColumn(0).setMaxWidth(100);
       table_cnaps.getColumnModel().getColumn(0).setMinWidth(100);

       table_cnaps.getColumnModel().getColumn(1).setPreferredWidth(100);
       table_cnaps.getColumnModel().getColumn(1).setMaxWidth(100);
       table_cnaps.getColumnModel().getColumn(1).setMinWidth(100);

       table_cnaps.getColumnModel().getColumn(2).setPreferredWidth(100);
       table_cnaps.getColumnModel().getColumn(2).setMaxWidth(100);
       table_cnaps.getColumnModel().getColumn(2).setMinWidth(100);

       table_cnaps.getColumnModel().getColumn(3).setPreferredWidth(370);
       table_cnaps.getColumnModel().getColumn(3).setMaxWidth(370);
       table_cnaps.getColumnModel().getColumn(3).setMinWidth(370);

       table_cnaps.getColumnModel().getColumn(4).setPreferredWidth(150);
       table_cnaps.getColumnModel().getColumn(4).setMaxWidth(150);
       table_cnaps.getColumnModel().getColumn(4).setMinWidth(150);

       table_cnaps.getColumnModel().getColumn(5).setPreferredWidth(180);
       table_cnaps.getColumnModel().getColumn(5).setMaxWidth(180);
       table_cnaps.getColumnModel().getColumn(5).setMinWidth(180);
       
      /* table_cnaps.getColumnModel().getColumn(5).setPreferredWidth(150);
       table_cnaps.getColumnModel().getColumn(5).setMaxWidth(150);
       table_cnaps.getColumnModel().getColumn(5).setMinWidth(150);*/
       recuperer_val_cnaps();
}

public void config_tab_standard_cnaps(){
        tab_cnaps= new DefaultTableModel();
        tab_cnaps.addColumn("Mois");
        tab_cnaps.addColumn("Année");
        tab_cnaps.addColumn("Matricule");
        tab_cnaps.addColumn("Nom et prénom");
        tab_cnaps.addColumn("Salaire de base");   
        tab_cnaps.addColumn("Avantages");
        tab_cnaps.addColumn("Total salaire personnel");
        JTableHeader tb = table_cnaps.getTableHeader();
        tb.setBackground(new Color(0,102,153));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
       table_cnaps.setBackground(Color.WHITE);
       table_cnaps.setModel(tab_cnaps);
       table_cnaps.getColumnModel().getColumn(0).setPreferredWidth(100);
       table_cnaps.getColumnModel().getColumn(0).setMaxWidth(100);
       table_cnaps.getColumnModel().getColumn(0).setMinWidth(100);

       table_cnaps.getColumnModel().getColumn(1).setPreferredWidth(100);
       table_cnaps.getColumnModel().getColumn(1).setMaxWidth(100);
       table_cnaps.getColumnModel().getColumn(1).setMinWidth(100);

       table_cnaps.getColumnModel().getColumn(2).setPreferredWidth(100);
       table_cnaps.getColumnModel().getColumn(2).setMaxWidth(100);
       table_cnaps.getColumnModel().getColumn(2).setMinWidth(100);

       table_cnaps.getColumnModel().getColumn(3).setPreferredWidth(370);
       table_cnaps.getColumnModel().getColumn(3).setMaxWidth(370);
       table_cnaps.getColumnModel().getColumn(3).setMinWidth(370);

       table_cnaps.getColumnModel().getColumn(4).setPreferredWidth(150);
       table_cnaps.getColumnModel().getColumn(4).setMaxWidth(150);
       table_cnaps.getColumnModel().getColumn(4).setMinWidth(150);

       table_cnaps.getColumnModel().getColumn(5).setPreferredWidth(180);
       table_cnaps.getColumnModel().getColumn(5).setMaxWidth(180);
       table_cnaps.getColumnModel().getColumn(5).setMinWidth(180);
       
}
//Configuration de la table IRSA
/*

select tba_irsa.impot_du as mocnaps_irsa, tba_irsa.num_perso as num,tba_irsa.nom_pre_irsa as nom,tba_irsa.sal_base,(select sum(tba_irsa.sal_base) from tba_irsa where tba_irsa.annee_irsa=$P{ANNEE_IRSA} and tba_irsa.mois_irsa=$P{MOIS_IRSA}) as somme_sal_base,tba_irsa.elements_salaire  as eleme,tba_irsa.salaire_brutes as brutes,(select sum(tba_irsa.salaire_brutes) from tba_irsa  where tba_irsa.annee_irsa=$P{ANNEE_IRSA} and tba_irsa.mois_irsa=$P{MOIS_IRSA}) as somme_brutes ,tba_irsa.cnaps as irsa ,tba_irsa.osief as osief ,(select sum(tba_irsa.osief) from tba_irsa  where tba_irsa.annee_irsa=$P{ANNEE_IRSA} and tba_irsa.mois_irsa=$P{MOIS_IRSA}) as somme_oseif,tba_irsa.cnaps as cnaps,(select sum(tba_irsa.cnaps) from tba_irsa  where tba_irsa.annee_irsa=$P{ANNEE_IRSA} and tba_irsa.mois_irsa=$P{MOIS_IRSA}) as somme_cnaps, tba_irsa.abatement_for as abate,(select sum(tba_irsa.base_imposable) from tba_irsa  where tba_irsa.annee_irsa=$P{ANNEE_IRSA} and tba_irsa.mois_irsa=$P{MOIS_IRSA}) as somme_bas_impose,tba_irsa.impot_coresspondant as impo_cores,tba_irsa.base_imposable as sa_impose ,(select sum(tba_irsa.impot_coresspondant)  from tba_irsa  where tba_irsa.annee_irsa=$P{ANNEE_IRSA} and tba_irsa.mois_irsa=$P{MOIS_IRSA} ) as somme_imot_cores,(select sum(tba_irsa.elements_salaire) from tba_irsa  where tba_irsa.annee_irsa=$P{ANNEE_IRSA} and tba_irsa.mois_irsa=$P{MOIS_IRSA} ) as somme_element,tba_irsa.enfant as enfant, tba_irsa.charge_familliale as famille,(select sum(tba_irsa.charge_familliale) from tba_irsa where tba_irsa.annee_irsa=$P{ANNEE_IRSA} and tba_irsa.mois_irsa=$P{MOIS_IRSA} ) as somme_famille,(select sum(tba_irsa.enfant) from tba_irsa  where tba_irsa.annee_irsa=$P{ANNEE_IRSA} and tba_irsa.mois_irsa=$P{MOIS_IRSA}) as somme_enfant,tba_irsa.mois_irsa as mois,tba_irsa.annee_irsa as annee,tba_irsa.autres_reduc as autres,(select sum(tba_irsa.autres_reduc)  from tba_irsa  where tba_irsa.annee_irsa=$P{ANNEE_IRSA} and tba_irsa.mois_irsa=$P{MOIS_IRSA}) as somme_autres
,(select sum(tba_irsa.impot_du) from tba_irsa  where tba_irsa.annee_irsa=$P{ANNEE_IRSA} and tba_irsa.mois_irsa=$P{MOIS_IRSA} ) as somme_irsa from tba_irsa where tba_irsa.annee_irsa=$P{ANNEE_IRSA} and tba_irsa.mois_irsa=$P{MOIS_IRSA}
*/
public void recuperer_val_irsa() throws Exception {
        tab_osief1er.setRowCount(0);//initialisation du table
        String d1 = "01/01/2018";
        String d2= "01/03/2018";
        int date1 = date1er2.getMonth()+1;
        int date2 = jMonthChooser4.getMonth()+1;
        int an= annee_irsa.getYear();
        int mois_irsa =mois_cnaps3.getMonth()+1;
        /*
         Object[] perso = {moiw,num_perso,nom_perso,sal_base,brute,annee};
         tab_osief1er.addRow(perso);
        */
        query ="select tba_irsa.impot_du as mocnaps_irsa, tba_irsa.num_perso as num,tba_irsa.nom_pre_irsa as nom,tba_irsa.sal_base  as sal_base,"
                + "(select sum(tba_irsa.sal_base) from tba_irsa where tba_irsa.annee_irsa='"+an+"' and tba_irsa.mois_irsa='"+mois_irsa+"') "
                + "as somme_sal_base,tba_irsa.elements_salaire  as eleme,tba_irsa.salaire_brutes as brutes,(select sum(tba_irsa.salaire_brutes) "
                + "from tba_irsa  where tba_irsa.annee_irsa='"+an+"' and tba_irsa.mois_irsa='"+mois_irsa+"') as somme_brutes ,tba_irsa.cnaps as irsa ,"
                + "tba_irsa.osief as osief ,(select sum(tba_irsa.osief) from tba_irsa  where tba_irsa.annee_irsa='"+an+"' "
                + "and tba_irsa.mois_irsa='"+mois_irsa+"') as somme_oseif,tba_irsa.cnaps as cnaps,(select sum(tba_irsa.cnaps) "
                + "from tba_irsa  where tba_irsa.annee_irsa='"+an+"' and tba_irsa.mois_irsa='"+mois_irsa+"') as somme_cnaps, "
                + "tba_irsa.abatement_for as abate,(select sum(tba_irsa.base_imposable) from tba_irsa  where tba_irsa.annee_irsa='"+an+"'"
                + "and tba_irsa.mois_irsa='"+mois_irsa+"') as somme_bas_impose,tba_irsa.impot_coresspondant as impo_cores,"
                + "tba_irsa.base_imposable as sa_impose ,(select sum(tba_irsa.impot_coresspondant)  "
                + "from tba_irsa  where tba_irsa.annee_irsa='"+an+"' and tba_irsa.mois_irsa='"+mois_irsa+"' ) "
                + "as somme_imot_cores,(select sum(tba_irsa.elements_salaire) from tba_irsa  where tba_irsa.annee_irsa='"+an+"' and"
                + " tba_irsa.mois_irsa='"+mois_irsa+"') as somme_element,tba_irsa.enfant as enfant, tba_irsa.charge_familliale as famille,"
                + "(select sum(tba_irsa.charge_familliale) from tba_irsa where tba_irsa.annee_irsa='"+an+"' and tba_irsa.mois_irsa='"+mois_irsa+"' )"
                + " as somme_famille,(select sum(tba_irsa.enfant) from tba_irsa  where tba_irsa.annee_irsa='"+an+"' and "
                + "tba_irsa.mois_irsa='"+mois_irsa+"') as somme_enfant,tba_irsa.mois_irsa as mois,tba_irsa.annee_irsa as annee,"
                + "tba_irsa.autres_reduc as autres,(select sum(tba_irsa.autres_reduc)  from tba_irsa  where tba_irsa.annee_irsa='"+an+"'"
                + " and tba_irsa.mois_irsa='"+mois_irsa+"') as somme_autres\n" +
                 ",(select sum(tba_irsa.impot_du) from tba_irsa  where tba_irsa.annee_irsa='"+an+"' and tba_irsa.mois_irsa='"+mois_irsa+"' ) "
                + "as somme_irsa from tba_irsa where tba_irsa.annee_irsa='"+an+"' and tba_irsa.mois_irsa='"+mois_irsa+"'";
//        query ="select tab_paie.num_perso as num, tab_paie.nom_pre_bulletin as nom,"
//                + "tab_paie.sal_base as sal_base,tab_paie.simple_av as av,tab_paie.totale_av_bulletin as total_av,"
//                + "tab_paie.annee_bulletin as annee,tab_paie.mois_bulletin as mois,"
//                + "(select sum(tab_paie.totale_av_bulletin) from tab_paie  where annee_bulletin='"+annee_osief2.getYear()+"' and tab_paie.mois_bulletin between '"+date1+"' and '"+date2+"'"
//                + " order by tab_paie.mois_bulletin) as total,(select (sum(tab_paie.totale_av_bulletin)*8/100) from tab_paie "
//                + " where annee_bulletin='"+annee_osief2.getYear()+"' and tab_paie.mois_bulletin between '"+date1+"' and '"+date2+"' order by tab_paie.mois_bulletin) as pourcent "
//                + "from tab_paie where annee_bulletin='"+annee_osief2.getYear()+"' and tab_paie.mois_bulletin between '"+date1+"' and '"+date2+"' order by tab_paie.mois_bulletin";
//       // query = " select * from tab_paie ";
      // query = "select * from tab_paie where mois_bulletin between '"+date1+"' and '"+date2+"' order by mois_bulletin ";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String nom = rs.getString("nom");
            String  elem = rs.getString("eleme");
            String moiw = rs.getString("mois");
            int moiso = Integer.parseInt(moiw);
            String sal_bru= rs.getString("brutes");
            String cnaps = rs.getString("cnaps");  
            String sal_base = rs.getString("sal_base");
            String base_impose= rs.getString("sa_impose");
            String autres_reduc = rs.getString("autres");
            String impos_cores = rs.getString("impo_cores");
            String enfant = rs.getString("enfant");
            String charge_fami = rs.getString("famille");
            String impot_irsa = rs.getString("mocnaps_irsa");
            String abat =rs.getString("abate");
            String osief = rs.getString("osief");
            String total_sal_b =rs.getString("somme_sal_base");
            String total_cotisa = rs.getString("somme_irsa");
//            txt_somme_osief.setText(total);
//            txt_cotisation.setText(pourcent);
//            double sal = Double.parseDouble(sal_base);
//            double avs = Double.parseDouble(brute);
             //double  av= (avs-sal);
//             total_sal_base.(total_sal_base);
//             total_cotisa.setText(total_cotisa);
totzl_cotisa.setText(total_cotisa);
                total_sal_base.setText(total_sal_b);
        Object[] irsa = {nom,sal_base,elem,sal_bru,cnaps,osief,abat,autres_reduc,base_impose,impos_cores,
        enfant,charge_fami,impot_irsa};
        tabirsa.addRow(irsa);
//        switch (moiso) {
//            
//        }
//                case 12:
//                    String deco = "Décembre";
//                    Object[] dec = {nom,annee,num_perso,nom_perso,sal_base,somme,brute};
//                    tabirsa.addRow(dec);
//                    break;
//                case 11:
//                    String nov = "Novembre";
//                    Object[] novo = {nov,annee,num_perso,nom_perso,sal_base,somme,brute};
//                    tabirsa.addRow(novo);
//                    break;
//                case 10:
//                    String oct = "Octobre";
//                    Object[] octo = {oct,annee,num_perso,nom_perso,sal_base,somme,brute};
//                tabirsa.addRow(octo);
//                    break;
//                case 9:
//                   String septo = "Octobre";
//                    Object[]sept = {septo,annee,num_perso,nom_perso,sal_base,somme,brute};
//                tabirsa.addRow(sept);
//                    break;
//                case 8:
//                    String outo = "Août";
//                  Object[]   aou = {outo,annee,num_perso,nom_perso,sal_base,somme,brute};
//            tabirsa.addRow(aou);
//                    break;
//                case 7:
//                    String juilo = "Juillet";
//                    Object[]  juil= {juilo,annee,num_perso,nom_perso,sal_base,somme,brute};
//            tabirsa.addRow(juil);
//                    break;
//                case 6:
//                    String juino = "Juin";
//                   Object[]  jui = {juino,annee,num_perso,nom_perso,sal_base,somme,brute};
//            tabirsa.addRow(jui);
//                    break;
//                case 5:
//                    String maio = "Mai";
//                   Object[] mai = {maio,annee,num_perso,nom_perso,sal_base,somme,brute};
//            tabirsa.addRow(mai);
//                    break;
//                case 4:
//                    String avro = "Avril";
//                   Object[] avr = {avro,annee,num_perso,nom_perso,sal_base,somme,brute};
//           tabirsa.addRow(avr);
//                    break;
//                case 3:
//                    String marsbo= "Mars";
//                    Object[] mar = {marsbo,annee,num_perso,nom_perso,sal_base,somme,brute};
//                 tabirsa.addRow(mar);
//                    break;
//                case 2:
//                    String fevo = "Févrièr";
//                    Object[] fev = {fevo,annee,num_perso,nom_perso,sal_base,somme,brute};
//                 tabirsa.addRow(fev);
//                    break;
//                case 1:
//                    String javo = "Janvier";
//                    Object[] jav = {javo,annee,num_perso,nom_perso,sal_base,somme,brute};
//                    tabirsa.addRow(jav);
//                    break;
//            }
           
        }
    }
public void config_tab_irsa() throws Exception{
        tabirsa= new DefaultTableModel();
        tabirsa.addColumn("Nom et pénom");
        tabirsa.addColumn("Sal de base");
        tabirsa.addColumn("Element du sal");
        tabirsa.addColumn("Salaire brutes");
        tabirsa.addColumn("CNaPS");
        tabirsa.addColumn("OSIEF");   
        tabirsa.addColumn("Abat for");
        tabirsa.addColumn("Autres déduc");
        tabirsa.addColumn("Base imposable");
        tabirsa.addColumn("Impôt corrpdt");
        tabirsa.addColumn("Enfant à charge");
        tabirsa.addColumn("Charge famil");
        tabirsa.addColumn("Impôt d'IRSA");
        JTableHeader tb = table_irsa.getTableHeader();
        tb.setBackground(new Color(0,102,153));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
       table_irsa.setBackground(Color.WHITE);
       table_irsa.setModel(tabirsa);
       table_irsa.getColumnModel().getColumn(0).setPreferredWidth(200);
       table_irsa.getColumnModel().getColumn(0).setMaxWidth(200);
       table_irsa.getColumnModel().getColumn(0).setMinWidth(200);

       table_irsa.getColumnModel().getColumn(1).setPreferredWidth(100);
       table_irsa.getColumnModel().getColumn(1).setMaxWidth(100);
       table_irsa.getColumnModel().getColumn(1).setMinWidth(100);

       table_irsa.getColumnModel().getColumn(2).setPreferredWidth(100);
       table_irsa.getColumnModel().getColumn(2).setMaxWidth(100);
       table_irsa.getColumnModel().getColumn(2).setMinWidth(100);

       table_irsa.getColumnModel().getColumn(3).setPreferredWidth(100);
       table_irsa.getColumnModel().getColumn(3).setMaxWidth(100);
       table_irsa.getColumnModel().getColumn(3).setMinWidth(100);

       table_irsa.getColumnModel().getColumn(4).setPreferredWidth(100);
       table_irsa.getColumnModel().getColumn(4).setMaxWidth(100);
       table_irsa.getColumnModel().getColumn(4).setMinWidth(100);

       table_irsa.getColumnModel().getColumn(5).setPreferredWidth(100);
       table_irsa.getColumnModel().getColumn(5).setMaxWidth(100);
       table_irsa.getColumnModel().getColumn(5).setMinWidth(100);
       
       table_irsa.getColumnModel().getColumn(6).setPreferredWidth(100);
       table_irsa.getColumnModel().getColumn(6).setMaxWidth(100);
       table_irsa.getColumnModel().getColumn(6).setMinWidth(100);
recuperer_val_irsa();
}
public void config_tab_standard_irsa() throws Exception{
        tabirsa= new DefaultTableModel();
        tabirsa.addColumn("Nom et pénom");
        tabirsa.addColumn("Sal de base");
        tabirsa.addColumn("Element du sal");
        tabirsa.addColumn("CNaPS");
        tabirsa.addColumn("OSIEF");   
        tabirsa.addColumn("Abat for");
        tabirsa.addColumn("Autres déduc");
        tabirsa.addColumn("Base imposable");
        tabirsa.addColumn("Impôt corrpdt");
        tabirsa.addColumn("Enfant à charge");
        tabirsa.addColumn("Charge famil");
        tabirsa.addColumn("Impôt d'IRSA");
        JTableHeader tb = table_irsa.getTableHeader();
        tb.setBackground(new Color(0,102,153));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
       table_irsa.setBackground(Color.WHITE);
       table_irsa.setModel(tabirsa);
       table_irsa.getColumnModel().getColumn(0).setPreferredWidth(200);
       table_irsa.getColumnModel().getColumn(0).setMaxWidth(200);
       table_irsa.getColumnModel().getColumn(0).setMinWidth(200);

       table_irsa.getColumnModel().getColumn(1).setPreferredWidth(100);
       table_irsa.getColumnModel().getColumn(1).setMaxWidth(100);
       table_irsa.getColumnModel().getColumn(1).setMinWidth(100);

       table_irsa.getColumnModel().getColumn(2).setPreferredWidth(100);
       table_irsa.getColumnModel().getColumn(2).setMaxWidth(100);
       table_irsa.getColumnModel().getColumn(2).setMinWidth(100);

       table_irsa.getColumnModel().getColumn(3).setPreferredWidth(100);
       table_irsa.getColumnModel().getColumn(3).setMaxWidth(100);
       table_irsa.getColumnModel().getColumn(3).setMinWidth(100);

       table_irsa.getColumnModel().getColumn(4).setPreferredWidth(100);
       table_irsa.getColumnModel().getColumn(4).setMaxWidth(100);
       table_irsa.getColumnModel().getColumn(4).setMinWidth(100);

       table_irsa.getColumnModel().getColumn(5).setPreferredWidth(100);
       table_irsa.getColumnModel().getColumn(5).setMaxWidth(100);
       table_irsa.getColumnModel().getColumn(5).setMinWidth(100);
       
       table_irsa.getColumnModel().getColumn(6).setPreferredWidth(100);
       table_irsa.getColumnModel().getColumn(6).setMaxWidth(100);
       table_irsa.getColumnModel().getColumn(6).setMinWidth(100);

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
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_cnaps = new javax.swing.JTable();
        cotisatio_cnaps = new javax.swing.JTextField();
        totale_cnaps = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        annee_cnaps = new com.toedter.calendar.JYearChooser();
        jLabel13 = new javax.swing.JLabel();
        mois_cnaps1 = new com.toedter.calendar.JMonthChooser();
        jLabel14 = new javax.swing.JLabel();
        mois_cnaps2 = new com.toedter.calendar.JMonthChooser();
        jButton2 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_irsa = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        annee_irsa = new com.toedter.calendar.JYearChooser();
        jLabel16 = new javax.swing.JLabel();
        mois_cnaps3 = new com.toedter.calendar.JMonthChooser();
        jLabel1 = new javax.swing.JLabel();
        total_sal_base = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        totzl_cotisa = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_osief = new javax.swing.JTable();
        txt_somme_osief = new javax.swing.JTextField();
        txt_cotisation = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        date1er2 = new com.toedter.calendar.JMonthChooser();
        jLabel23 = new javax.swing.JLabel();
        annee_osief2 = new com.toedter.calendar.JYearChooser();
        jMonthChooser4 = new com.toedter.calendar.JMonthChooser();
        jButton5 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_accueil = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane3.setBackground(new java.awt.Color(0, 102, 153));
        jTabbedPane3.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane3.setToolTipText("");
        jTabbedPane3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

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

        cotisatio_cnaps.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cotisatio_cnaps.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cotisatio_cnaps.setEnabled(false);

        totale_cnaps.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totale_cnaps.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        totale_cnaps.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("TOTAL DE SALAIRE BRUTE:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Cotisation 1% :");

        jLabel9.setText("AR");

        jLabel10.setText("AR");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Années :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Mois Début :");

        mois_cnaps1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Mois Fin :");

        mois_cnaps2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton2.setBackground(new java.awt.Color(0, 102, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Search_25px_1.png"))); // NOI18N
        jButton2.setText("Afficher");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(0, 102, 153));
        jButton12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton12.setText("Imprimer");
        jButton12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totale_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cotisatio_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGap(2, 2, 2))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mois_cnaps2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mois_cnaps1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(annee_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel15)))
                        .addGap(0, 31, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cotisatio_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totale_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(annee_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addGap(11, 11, 11)
                        .addComponent(mois_cnaps1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addGap(7, 7, 7)
                        .addComponent(mois_cnaps2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("DECLARATION C.Na.PS", jPanel5);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

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
        jScrollPane5.setViewportView(table_irsa);

        jButton3.setBackground(new java.awt.Color(0, 102, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Search_25px_1.png"))); // NOI18N
        jButton3.setText("Afficher");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(0, 102, 153));
        jButton14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton14.setText("Imprimer");
        jButton14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Années :");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Mois");

        mois_cnaps3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("TOTAL DE SALAIRE DE BASE:");

        total_sal_base.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        total_sal_base.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        total_sal_base.setEnabled(false);

        jLabel6.setText("AR");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Cotisation :");

        totzl_cotisa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totzl_cotisa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        totzl_cotisa.setEnabled(false);

        jLabel12.setText("AR");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(mois_cnaps3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(annee_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel18)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(518, 518, 518)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(total_sal_base, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totzl_cotisa, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(total_sal_base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(totzl_cotisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(4, 4, 4)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(annee_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mois_cnaps3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("DECLARATION IRSA", jPanel10);

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
        jScrollPane3.setViewportView(table_osief);

        txt_somme_osief.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_somme_osief.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_somme_osief.setEnabled(false);

        txt_cotisation.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_cotisation.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_cotisation.setEnabled(false);
        txt_cotisation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cotisationActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Cotisation 2% :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("TOTAL DE SALAIRE BRUTES:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("AR");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("AR");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel21.setText("Année :");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Mois début :");

        date1er2.setBackground(new java.awt.Color(255, 255, 255));
        date1er2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Mois Fin :");

        annee_osief2.setBackground(new java.awt.Color(255, 255, 255));

        jMonthChooser4.setBackground(new java.awt.Color(255, 255, 255));
        jMonthChooser4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton5.setBackground(new java.awt.Color(0, 102, 153));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setForeground(java.awt.Color.white);
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Search_25px_1.png"))); // NOI18N
        jButton5.setText("Afficher");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(0, 102, 153));
        jButton16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton16.setText("Imprimer");
        jButton16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_somme_osief, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_cotisation, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(210, 210, 210))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jMonthChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(annee_osief2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(date1er2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_somme_osief, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cotisation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(annee_osief2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22)
                        .addGap(13, 13, 13)
                        .addComponent(date1er2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMonthChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane3.addTab("DECLARATION O.S.I.F", jPanel12);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));

        menu_accueil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_Home_20px.png"))); // NOI18N
        menu_accueil.setText("Menu principale");
        menu_accueil.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        menu_accueil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_accueilMouseClicked(evt);
            }
        });

        jMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Return_25px.png"))); // NOI18N
        jMenuItem1.setText("Accueil principale");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menu_accueil.add(jMenuItem1);

        jMenuItem3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon_48/icons8_Folder_Invoices_25px_1.png"))); // NOI18N
        jMenuItem3.setText("Activités");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menu_accueil.add(jMenuItem3);

        jMenuItem4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon_48/icons8_Payment_History_25px.png"))); // NOI18N
        jMenuItem4.setText("Salaire globale");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menu_accueil.add(jMenuItem4);

        jMenuItem2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Logout_Rounded_Left_25px.png"))); // NOI18N
        jMenuItem2.setText("Se déconnecter");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menu_accueil.add(jMenuItem2);

        jMenuBar1.add(menu_accueil);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            // TODO add your handling code here:
            //config_tab_perso_standard();
            tabla_standard_osief();
            config_tab_standard_cnaps();
            config_tab_standard_irsa();
        } catch (Exception ex) {
            Logger.getLogger(declaration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened
/*public void config_tab_perso_standard() throws Exception {
        tab_osief = new DefaultTableModel();
        tab_osief.addColumn("Im");
        tab_osief.addColumn("Nom et prénoms");
        tab_osief.addColumn("1er Mois");
        tab_osief.addColumn("Avatages");   
        tab_osief.addColumn("Temps");
        tab_osief.addColumn("2er Mois");
        tab_osief.addColumn("Avatages");
        tab_osief.addColumn("Temps");
        tab_osief.addColumn("3er Mois");
        tab_osief.addColumn("Avatages");
        tab_osief.addColumn("Temps");
        tab_osief.addColumn("Total Salaire");
        tab_osief.addColumn("Cotisation");
        tab_osief.addColumn("Net à Payer");
        JTableHeader tb = table_osief.getTableHeader();
        tb.setBackground(new Color(0, 51, 102));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
       table_osief.setBackground(Color.WHITE);
       table_osief.setModel(tab_osief);
       table_osief.getColumnModel().getColumn(0).setPreferredWidth(50);
       table_osief.getColumnModel().getColumn(0).setMaxWidth(50);
       table_osief.getColumnModel().getColumn(0).setMinWidth(50);

       table_osief.getColumnModel().getColumn(1).setPreferredWidth(250);
       table_osief.getColumnModel().getColumn(1).setMaxWidth(250);
       table_osief.getColumnModel().getColumn(1).setMinWidth(250);

       table_osief.getColumnModel().getColumn(2).setPreferredWidth(80);
       table_osief.getColumnModel().getColumn(2).setMaxWidth(80);
       table_osief.getColumnModel().getColumn(2).setMinWidth(80);

       table_osief.getColumnModel().getColumn(3).setPreferredWidth(80);
       table_osief.getColumnModel().getColumn(3).setMaxWidth(80);
       table_osief.getColumnModel().getColumn(3).setMinWidth(80);

       table_osief.getColumnModel().getColumn(4).setPreferredWidth(50);
       table_osief.getColumnModel().getColumn(4).setMaxWidth(50);
       table_osief.getColumnModel().getColumn(4).setMinWidth(50);

       table_osief.getColumnModel().getColumn(5).setPreferredWidth(80);
       table_osief.getColumnModel().getColumn(5).setMaxWidth(80);
       table_osief.getColumnModel().getColumn(5).setMinWidth(80);

       table_osief.getColumnModel().getColumn(6).setPreferredWidth(80);
       table_osief.getColumnModel().getColumn(6).setMaxWidth(80);
       table_osief.getColumnModel().getColumn(6).setMinWidth(80);
       
        table_osief.getColumnModel().getColumn(7).setPreferredWidth(50);
       table_osief.getColumnModel().getColumn(7).setMaxWidth(50);
       table_osief.getColumnModel().getColumn(7).setMinWidth(50);
       
        table_osief.getColumnModel().getColumn(8).setPreferredWidth(80);
       table_osief.getColumnModel().getColumn(8).setMaxWidth(80);
       table_osief.getColumnModel().getColumn(8).setMinWidth(80);
       
        table_osief.getColumnModel().getColumn(9).setPreferredWidth(80);
       table_osief.getColumnModel().getColumn(9).setMaxWidth(80);
       table_osief.getColumnModel().getColumn(9).setMinWidth(80);
       
       table_osief.getColumnModel().getColumn(10).setPreferredWidth(50);
       table_osief.getColumnModel().getColumn(10).setMaxWidth(50);
       table_osief.getColumnModel().getColumn(10).setMinWidth(50);
       
       table_osief.getColumnModel().getColumn(11).setPreferredWidth(90);
       table_osief.getColumnModel().getColumn(11).setMaxWidth(90);
       table_osief.getColumnModel().getColumn(11).setMinWidth(90);
       
       table_osief.getColumnModel().getColumn(12).setPreferredWidth(80);
       table_osief.getColumnModel().getColumn(12).setMaxWidth(80);
       table_osief.getColumnModel().getColumn(12).setMinWidth(80);
}*/
    //pour entre deux dates du salaire globale



    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       
    }//GEN-LAST:event_formWindowClosed
//teste de délcaration osief  
 public void exportPDF(String nomFichier,String Titre,int nbColonne)
    {
        try{
            Document document=new Document(PageSize.A4,0.0f,0.0f,40.0f,2.0f);
            PdfWriter.getInstance(document,new FileOutputStream(nomFichier));
            document.open();
            //contenu
            
                //creation d'une font
            com.itextpdf.text.Font fontGray = FontFactory.getFont("Arial", 11, BaseColor.GRAY);
            com.itextpdf.text.Font fontBlack = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            com.itextpdf.text.Font fontBlue = FontFactory.getFont("Verdana", 16, BaseColor.BLUE);
            com.itextpdf.text.Font blanc = FontFactory.getFont("Arial", 10, BaseColor.WHITE);
            
            document.add(new Paragraph("\n"));//saut de ligne
            Paragraph titre=new Paragraph(Titre.toUpperCase(),fontBlue);
            titre.setAlignment(1);
            document.add(titre);
            document.add(new Paragraph("\n"));//saut de ligne
            
            //table
            PdfPTable tablePdf=new PdfPTable(nbColonne); 
            tablePdf.setWidthPercentage(90);
            
            //ajout du table header
            //tableau Header
            String col[]={"Nom et prénon","Avantage","Salaire de base","Temps"};
            for(int i=0; i< nbColonne; i++)
            {
                PdfPCell colonne =new PdfPCell(new Phrase(col[i].toUpperCase(), blanc)); 
                colonne.setHorizontalAlignment(1);
                colonne.setBackgroundColor(BaseColor.GRAY);
                tablePdf.addCell(colonne);
            }
            
            Connexion connect=new Connexion();
            ResultSet rs=null;
            ResultSet rs2=null;
            Connection con=connect.obtenirconnexion();
            
            String data[];
            
            String valeur[][];
            
            try{
                Statement stat=con.createStatement();

                String sql="";
                String sql_perso = "select distinct num_perso_contrat as perso from tab_contrat order by num_perso_contrat ASC";
                rs2=stat.executeQuery(sql_perso);
                while(rs2.next()){
                sql="select nom_perso,avantage,salaire_base,temps from tab_osief where num_perso='"+rs2.getString("perso")+"'"; 
                
                }
                
                rs=stat.executeQuery(sql);
                while(rs.next())
                {
                    for(int j=1;j<=nbColonne;j++)
                    {
                        PdfPCell pdfCell=new PdfPCell();
                        
                        if(rs.getString(j) != "")
                           pdfCell.setPhrase(new Phrase(rs.getString(j), fontBlack));
                        else
                           pdfCell.setPhrase(new Phrase("", fontBlack));
                        
                        pdfCell.setHorizontalAlignment(1); //centre
                        tablePdf.addCell(pdfCell);
                    }
                }
            }
            catch(SQLException e){
            }
            document.add(tablePdf);
            document.close();
            //JOptionPane.showMessageDialog(null, "Exportation avec succes !");
            
            //ouverture du fichier PDF crée
            try{
                Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Gestion_paie"+nomFichier);
             Desktop.getDesktop().open(new File("C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Gestion_paie\\Osief.pdf"));
            }catch(RuntimeException e)
            {
               // JOptionPane.showMessageDialog(null, "Impossible d'ouvrir le fichier! "+e);
            }
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null,"erreur: "+e);
        }
    }
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        accueil_principale acp = new accueil_principale();
        acp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       perso_entrep_contrat_avance_forme sal_g = new perso_entrep_contrat_avance_forme();
        sal_g.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        authentificaiton auth = new authentificaiton();
        auth.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menu_accueilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_accueilMouseClicked

    }//GEN-LAST:event_menu_accueilMouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/payement","root","");
            final String  JRXML = "C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Gestion_paie\\src\\impression\\irsa.jrxml";
            // String sql = "select * from print_bulletin where matricule like '%"+num+"%'";
            Map param = new HashMap();
            param.put("ANNEE_IRSA",annee_cnaps.getYear());
            param.put("MOIS_IRSA",mois_cnaps1.getMonth()+1);
//            param.put("MOIS_CNAPS2",mois_cnaps2.getMonth()+1);
//param.put("DATE_AVANCE",txt_dt_imprimer_avance.getText());
requet_impression print = new requet_impression();
print.displayReport(JRXML, param, con);
// config_tab_annee();     
        } catch (SQLException ex) {
            Logger.getLogger(declaration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(declaration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(declaration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            config_tab_irsa();
        } catch (Exception ex) {
            Logger.getLogger(declaration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/payement","root","");
            final String  JRXML = "C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Gestion_paie\\src\\impression\\cnaps.jrxml";
            // String sql = "select * from print_bulletin where matricule like '%"+num+"%'";
            Map param = new HashMap();
            param.put("ANNEE_CNAPS",annee_cnaps.getYear());
            param.put("MOIS_CNAPS1",mois_cnaps1.getMonth()+1);
            param.put("MOIS_CNAPS2",mois_cnaps2.getMonth()+1);
            //param.put("DATE_AVANCE",txt_dt_imprimer_avance.getText());
            requet_impression print = new requet_impression();
            print.displayReport(JRXML, param, con);
            // config_tab_annee();
        } catch (Exception ex) {
            Logger.getLogger(declaration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            config_tab_cnaps();
        } catch (Exception ex) {
            Logger.getLogger(declaration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_cotisationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cotisationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cotisationActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
       // exportPDF("Osief.pdf","Déclaration OSIEF",4);
       try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/payement","root","");
            final String  JRXML = "C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Gestion_paie\\src\\impression\\osief_f.jrxml";
            // String sql = "select * from print_bulletin where matricule like '%"+num+"%'";
            Map param = new HashMap();
            param.put("ANNEE_OSIEF",annee_osief2.getYear());
            param.put("MOIS_OSIEF1",date1er2.getMonth()+1);
            param.put("MOIS_OSIEF2",jMonthChooser4.getMonth()+1);
            //param.put("DATE_AVANCE",txt_dt_imprimer_avance.getText());
            requet_impression print = new requet_impression();
            print.displayReport(JRXML, param, con);
            // config_tab_annee();
        } catch (Exception ex) {
            Logger.getLogger(declaration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // String sql ="select ";
            config_tab_osief();
            //compteperso_vola_osief();
        } catch (Exception ex) {
            Logger.getLogger(declaration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        globale_salaire globs = new globale_salaire();
        globs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(declaration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(declaration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(declaration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(declaration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new declaration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JYearChooser annee_cnaps;
    private com.toedter.calendar.JYearChooser annee_irsa;
    private com.toedter.calendar.JYearChooser annee_osief2;
    private javax.swing.JTextField cotisatio_cnaps;
    private com.toedter.calendar.JMonthChooser date1er2;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JMenuItem jMenuItem4;
    private com.toedter.calendar.JMonthChooser jMonthChooser4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JMenu menu_accueil;
    private com.toedter.calendar.JMonthChooser mois_cnaps1;
    private com.toedter.calendar.JMonthChooser mois_cnaps2;
    private com.toedter.calendar.JMonthChooser mois_cnaps3;
    private javax.swing.JTable table_cnaps;
    private javax.swing.JTable table_irsa;
    private javax.swing.JTable table_osief;
    private javax.swing.JTextField total_sal_base;
    private javax.swing.JTextField totale_cnaps;
    private javax.swing.JTextField totzl_cotisa;
    private javax.swing.JTextField txt_cotisation;
    private javax.swing.JTextField txt_somme_osief;
    // End of variables declaration//GEN-END:variables
}
