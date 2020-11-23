/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classe;

import com.requete.Connexion;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author VONIANAHITRA
 */
public class histogramme {
   
    Statement stmt;
  Connexion maConnexion = new Connexion(); 
    //public int ann = 2018;
     
    public void salaire_brut(int ann) throws SQLException{
      stmt = maConnexion.obtenirconnexion().createStatement();
       //int val;
       //val = SalaireGlobal.
       //int annee;
       String sal;
       int nb = 1;
       Double[] Salaire = new Double[12];
       String[] mois = new String [12];
       mois[0]="Janvier";
       mois[1]="Février";
       mois[2]="Mars";
       mois[3]="Avril";
       mois[4]="Mai";
       mois[5]="Juin";
       mois[6]="Juiellet";
       mois[7]="Aûot";
       mois[8]="Septembre";
       mois[9]="Octobre";
       mois[10]="Novembre";
       mois[11]="Décembre";
       while(nb < 13){
           boolean test = true;
           sal = "SELECT SUM(totale_av_bulletin) As ttl_brute FROM tab_paie WHERE annee_bulletin ='"+ann+"' AND mois_bulletin = '"+nb+"'";
            stmt = maConnexion.obtenirconnexion().createStatement();
            ResultSet Mont_sal = stmt.executeQuery(sal);
            while(Mont_sal.next()){
                Salaire[nb-1] = Mont_sal.getDouble(1);
                test = false;
            }
            if(test){
               Salaire[nb-1] = 0.0; 
            }
            nb = nb + 1;
       }
      int j = 0;
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      while(j<12){
         //dataset.setValue(nbEmploye[j], nom[j], nom[j] );
         dataset.addValue(Salaire[j], "Salaire", mois[j]);
         j = j + 1;
      }
      
            JFreeChart chart = ChartFactory.createBarChart("Histogramme de salaire brute annee "+ann, "Mois", "Salaire brute", dataset, PlotOrientation.VERTICAL, false, true, false);
            //JFreeChart chart = ChartFactory.createBarChart3D("Histogramme des nombres des employées de chaque entreprise", "Entreprise", "Nombre des empoyée", dataset, PlotOrientation.VERTICAL, true, true, true);
            //JFreeChart chart = ChartFactory.createLineChart("Courbe de nombre des émployée dans les entreprised", "Entreprise", "Nombre des empoyée", dataset);
            //JFreeChart chart = ChartFactory.createLineChart3D("Courbe de nombre des émployée dans les entreprised", "Entreprise", "Nombre des empoyée", dataset);
            //JFreeChart chart = ChartFactory.createStackedBarChart("Histogramme des nombres des employées de chaque entreprise", "Entreprise", "Nombre des empoyée", dataset, PlotOrientation.VERTICAL, false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLUE);
            ChartFrame frame = new ChartFrame("Histogramme", chart);
            frame.setVisible(true);
            frame.setSize(800, 600);
    }
    
}


