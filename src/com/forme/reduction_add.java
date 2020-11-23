/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forme;

import com.classe.DeuxChiffresApresVirgule;
import com.classe.classe_irsa;
import com.classe.classe_osief;
import com.classe.classe_reduction;
import com.requete.Connecter_bd;
import com.requete.connections;
import com.requete.requet_irsa;
import com.requete.requet_osief;
import com.requete.requet_reduction;
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
import javax.swing.JTextField;

/**
 *
 * @author VONINAHITRA
 */
public class reduction_add extends javax.swing.JFrame {
private String query;
private Connecter_bd con_db;
connections maCon = new connections();
ResultSet rs;
PreparedStatement ps;
Statement st;
Connection conc;
    /**
     * Creates new form reduction_add
     */
    public reduction_add() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        irsa_pourcent.setVisible(false);
        irsa_min.setVisible(false);
        irsa_val.setVisible(false);
        osief_pourcent.setVisible(false);
        cnaps_pourcent.setVisible(false);
        suple.setVisible(false);
        allocation.setVisible(false);
        txt_enfant.setVisible(false);
        cnaps_pourcent.setVisible(false);
        irsa_val.setVisible(false);
        irsa_min.setVisible(false);
        txt_impot_coressp.setVisible(false);
        txt_element_sal.setVisible(false);
        autres_deduc.setVisible(false);
        txt_base_imposable.setVisible(false);
    }
  private void variable() throws Exception{
     con_db = new Connecter_bd();
     query = "select * from valeur"; 
      ResultSet rs = con_db.executeQuery(query);
    while(rs.next()){
           String irsa_pourcen = rs.getString("irsa_pourcent");
           String irsa_va =rs.getString("irsa_val");
           String irsa_mi =rs.getString("irsa_min");
           String osief_pourcen = rs.getString("osief_pourcent");
           String cnaps_pourcen = rs.getString("cnaps_pourcent");
           String allocatio =rs.getString("allocation");
           String supl = rs.getString("supl");
   suple.setText(supl);
   osief_pourcent.setText(osief_pourcen);
   irsa_pourcent.setText(irsa_pourcen);
   irsa_min.setText(irsa_mi);
   irsa_val.setText(irsa_va);
   cnaps_pourcent.setText(cnaps_pourcen);
   allocation.setText(allocatio);
   
   } 
  }  
private void select_num_perso() throws Exception, SQLException{
  // txt_num_perso_sup.removeAllItems();
    con_db = new Connecter_bd();
    query = "select * from tab_perso";
    ResultSet rs = con_db.executeQuery(query);
    while(rs.next()){
       String perso = rs.getString("num_perso").toString();
       txt_num_perso_reduc.addItem(perso);
   } 
}

private void select_nom_perso() throws Exception, SQLException{
    con_db = new Connecter_bd();
     //txt_num_perso_avcance.removeAllItems();
    String num_p = txt_num_perso_reduc.getSelectedItem().toString();
    query = "select * from tab_perso where num_perso='"+num_p+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
       String perso = rs.getString("nom_pre_perso");
       String num = rs.getString("num_perso");
       txt_nom_perso_reduc.setText(perso);
       txt_num_perso_reduc2.setText(num);
   } 
}

private void select_irsa() throws Exception, SQLException{
    
    con_db = new Connecter_bd();
    double min_sal_base = 250000;
    double irsa_default = 2000;
    double irsa;
    int vinght = 20;
    double sal_reste;
     //txt_num_perso_avcance.removeAllItems();
    String num_p = txt_num_perso_reduc.getSelectedItem().toString();
    query = "select * from tab_contrat where num_perso='"+num_p+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
       String sal_base = rs.getString("sal_base_contrat");
       txt_sal_base_reduc.setText(sal_base);
       double sal_base_reduc = Double.parseDouble(sal_base);
    if(sal_base_reduc<=min_sal_base){
    irsa = irsa_default;  
    String iras_final = DeuxChiffresApresVirgule.ChiffresApresVirgule(irsa);
    txt_iras_reduce.setText(iras_final);
      }else{ 
    /*String cna = txt_cnaps_reduc.getText();
     String osi = txt_osief_reduc.getText();
     double cnaps = Double.parseDouble(cna);
     double osief = Double.parseDouble(osi);
     double sal_rs_all = (cnaps+osief);
     String ok = Double.toString(sal_rs_all);
     txt_iras_reduce.setText(ok);*/   
     double sal_reste2 = (sal_base_reduc-min_sal_base); 
    // double cnapsosief;
     String cna = txt_cnaps_reduc.getText();
     String osi = txt_osief_reduc.getText();
     double cnaps = Double.parseDouble(cna);
     double osief = Double.parseDouble(osi);
     double sal_cnaosi = (cnaps+osief);
     double sal_rs_all = (sal_reste2-sal_cnaosi);
     double taux_irsa_20 = (sal_rs_all*20);
     double taux_irsa_final = (taux_irsa_20/100);
     String alloc = txt_famille_reduc.getText();
     double alloc_fami = Double.parseDouble(alloc);
     double finale_irsa = (taux_irsa_final-alloc_fami);
     String afficer_irsa = DeuxChiffresApresVirgule.ChiffresApresVirgule(finale_irsa);
     txt_iras_reduce.setText(afficer_irsa);
    }/*else{
      sal_reste = (sal_base_reduc-min_sal_base); 
    // double cnapsosief;
     String cna = txt_cnaps_reduc.getText();
     String osi = txt_osief_reduc.getText();
     double cnaps = Double.parseDouble(cna);
     double osief = Double.parseDouble(osi);
     double sal_cnaosi = (cnaps+osief);
     double sal_rs_all = (sal_reste-sal_cnaosi);
     double taux_irsa_20 = (sal_rs_all*20);
     double taux_irsa_final = (taux_irsa_20/100);
     double finale_irsa = (taux_irsa_final);
     String afficer_irsa = Double.toString(finale_irsa);
     txt_iras_reduce.setText(afficer_irsa);     
     }*/
    }else{
   JOptionPane.showMessageDialog(null,"Cet personnel doit être effectué à un contrat","Message",JOptionPane.WARNING_MESSAGE);     
    }
}
private void allocation() throws Exception{
        String num_p = txt_num_perso_reduc.getSelectedItem().toString();
        //String ms = txt_mois_avance.getSelectedItem().toString();
        con_db = new Connecter_bd();
        query = "select * from tab_perso where num_perso='"+num_p+"'";
        ResultSet rs = con_db.executeQuery(query);
        double vola_zaza = 4000;
      if(rs.next()){
        String nbr = rs.getString("nbr_enf_perso");
        String nbenf = rs.getString("nbr_enf_perso");
        double nbr_enf = Double.parseDouble(nbr);
        double somme =( nbr_enf*vola_zaza);
        String vola = DeuxChiffresApresVirgule.ChiffresApresVirgule(somme);
        txt_famille_reduc.setText(vola);
        txt_enfant.setText(nbenf);
      }else{
          
      }
}
private void select_sal_brute() throws Exception, SQLException{
    con_db = new Connecter_bd();
    int un = 1;
    int deux= 2;
    double unpourcent,unpourcent_finale;
    double deuxpourcent,deuxpourcent_finale;
    //impot sur le revenu salaire annuel.
     //txt_num_perso_avcance.removeAllItems();
    String num_p = txt_num_perso_reduc.getSelectedItem().toString();
    query = "select * from tab_av where num_perso='"+num_p+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
       String brute_av = rs.getString("totale_brute_av").toString();
       txt_brute_av_reduc.setText(brute_av);
      double brute_ok = Double.parseDouble(brute_av);
      unpourcent = (brute_ok*un);
      unpourcent_finale = (unpourcent/100);
     // String afficher_un = Double.toString(unpourcent_finale);
      String afficher_un = DeuxChiffresApresVirgule.ChiffresApresVirgule(unpourcent_finale);
      deuxpourcent = (brute_ok*deux);
      deuxpourcent_finale = (deuxpourcent/100);
      //String affoicher_deux = Double.toString(deuxpourcent_finale);
      String affoicher_deux = DeuxChiffresApresVirgule.ChiffresApresVirgule(deuxpourcent_finale);
      txt_cnaps_reduc.setText(afficher_un);
      txt_osief_reduc.setText(affoicher_deux);
      //String totale8 = DeuxChiffresApresVirgule.ChiffresApresVirgule(finale2);
   }else{
        String num_p2 = txt_num_perso_reduc.getSelectedItem().toString();
        query = "select * from tab_contrat where num_perso='"+num_p2+"'";
        ResultSet rs2 = con_db.executeQuery(query);
        if(rs2.next()){
      String sal_base = rs2.getString("sal_base_contrat");
      txt_brute_av_reduc.setText(sal_base);
      double brute_ok = Double.parseDouble(sal_base);
      unpourcent = (brute_ok*un);
      unpourcent_finale = (unpourcent/100);
      String afficher_un = DeuxChiffresApresVirgule.ChiffresApresVirgule(unpourcent_finale);
      deuxpourcent = (brute_ok*deux);
      deuxpourcent_finale = (deuxpourcent/100);
      String affoicher_deux = DeuxChiffresApresVirgule.ChiffresApresVirgule(deuxpourcent_finale);
      txt_cnaps_reduc.setText(afficher_un);
      txt_osief_reduc.setText(affoicher_deux);
        }else{  
        }
    }
}
private void calcule(){
        double sal_brute = Double.parseDouble(txt_brute_av_reduc.getText());
        double cnaps = Double.parseDouble(txt_cnaps_reduc.getText());
        double osief =Double.parseDouble(txt_osief_reduc.getText());
        double base_imposable = sal_brute-(cnaps+osief);
        double sal_base =Double.parseDouble(txt_sal_base_reduc.getText());
        double element = (sal_brute-sal_base);
//        double elemen_brutes =sal_brute-sal_base;
        double impot_cores =((base_imposable*20)/100);
        //convertisation
        String base_impose = Double.toString(base_imposable);
//        String elements =Double.toString(element);
//        String impot_core = Double.toString(impot_cores);
//        String eleme_sal =Double.toString(elemen_brutes);
        //affichage txt_element_sal txt_impot_coressp
        String elements = DeuxChiffresApresVirgule.ChiffresApresVirgule(element);
        String impot_core = DeuxChiffresApresVirgule.ChiffresApresVirgule(impot_cores);
         txt_base_imposable.setText(base_impose);
         txt_element_sal.setText(elements);
         txt_impot_coressp.setText(impot_core);
//         txt
}
private void declaration() throws Exception{
    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        double sal_brute = Double.parseDouble(txt_brute_av_reduc.getText());
        double cnaps = Double.parseDouble(txt_cnaps_reduc.getText());
        double irsa = Double.parseDouble(txt_iras_reduce.getText());
        double osief =Double.parseDouble(txt_osief_reduc.getText());
        double base_imposable =Double.parseDouble(txt_base_imposable.getText());
        double sal_base =Double.parseDouble(txt_sal_base_reduc.getText());
        double element =Double.parseDouble(txt_element_sal.getText());
        String nom= txt_nom_perso_reduc.getText();
        String num = txt_num_perso_reduc2.getText();
        double alloc =Double.parseDouble(txt_famille_reduc.getText());
        double autres_reduc=Double.parseDouble(autres_deduc.getText());
        int ms = txt_mois_reduc.getMonth()+1;
        String mois = Integer.toString(ms);
        int an= txt_annee_reduc.getYear();
        //txt_base_imposable autres_deduc
        String mc ="MC";
        String annee = Integer.toString(an);
        int enfant =Integer.parseInt(txt_enfant.getText());
        double abate=0;
        double impot_cores =Double.parseDouble(txt_impot_coressp.getText());
        String date = dt.format(txt_pdu_reduc.getDate());
        classe_irsa cli = new classe_irsa(num,nom,sal_base,element,sal_brute,cnaps,osief,abate,base_imposable,impot_cores,enfant,alloc,irsa,mois,annee,autres_reduc,date);
        requet_irsa rqti = new requet_irsa();
        rqti.ajouter(cli);
        classe_osief clf = new classe_osief(num, nom, element, sal_base, mc,ms,an);
        requet_osief rqtf = new requet_osief();
        rqtf.ajouter(clf);  
}

public void select_avance() throws Exception{
   // String ms = txt_mois_reduc.getSelectedItem().toString();
    int ms = txt_mois_reduc.getMonth();
    int mois = ms+01;
    int annee = txt_annee_reduc.getYear();
    String an = Integer.toString(annee);
    String num_p = txt_num_perso_reduc.getSelectedItem().toString();
    con_db = new Connecter_bd();
    query = "select * from tab_avance where num_perso='"+num_p+"' and mois_avance='"+mois+"' and annee_avance='"+an+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
    String avance = rs.getString("total_avance");
    txt_avances_reduc.setText(avance);
    }else{
    txt_avances_reduc.setText("0");  
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_annuler_entrep = new javax.swing.JButton();
        btn_enre_reduc = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_annee_reduc = new com.toedter.calendar.JYearChooser();
        jLabel11 = new javax.swing.JLabel();
        txt_pdu_reduc = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        txt_pau_reduc = new com.toedter.calendar.JDateChooser();
        btn_ok_dt_reduc = new javax.swing.JButton();
        txt_num_perso_reduc = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txt_mois_reduc = new com.toedter.calendar.JMonthChooser();
        jPanel3 = new javax.swing.JPanel();
        txt_nom_perso_reduc = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_num_perso_reduc2 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txt_sal_base_reduc = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_famille_reduc = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_cession_sal_reduc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_nouri_loge_reduc = new javax.swing.JTextField();
        txt_autres_reduc = new javax.swing.JTextField();
        txt_vaca_sup = new javax.swing.JLabel();
        btn_ok_reduc_final = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txt_base_imposable = new javax.swing.JTextField();
        txt_element_sal = new javax.swing.JTextField();
        autres_deduc = new javax.swing.JTextField();
        irsa_val = new javax.swing.JTextField();
        irsa_pourcent = new javax.swing.JTextField();
        irsa_min = new javax.swing.JTextField();
        osief_pourcent = new javax.swing.JTextField();
        cnaps_pourcent = new javax.swing.JTextField();
        suple = new javax.swing.JTextField();
        allocation = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txt_totale_reduc = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txt_iras_reduce = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_osief_reduc = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_cnaps_reduc = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_avances_reduc = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_brute_av_reduc = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txt_enfant = new javax.swing.JTextField();
        txt_impot_coressp = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DEDUCTIONS REGLEMENTAIRES");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Tax_50px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(19, 19, 19))
        );

        btn_annuler_entrep.setBackground(new java.awt.Color(0, 102, 153));
        btn_annuler_entrep.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_annuler_entrep.setForeground(new java.awt.Color(255, 255, 255));
        btn_annuler_entrep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Close_Window_25px.png"))); // NOI18N
        btn_annuler_entrep.setText("Annuler/Fermer");
        btn_annuler_entrep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_annuler_entrepActionPerformed(evt);
            }
        });

        btn_enre_reduc.setBackground(new java.awt.Color(0, 102, 153));
        btn_enre_reduc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_enre_reduc.setForeground(new java.awt.Color(255, 255, 255));
        btn_enre_reduc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Checkmark_25px.png"))); // NOI18N
        btn_enre_reduc.setText("Enregister");
        btn_enre_reduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enre_reducActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Date de la déduction réglementaire", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Mois du :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Année :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Période du");

        txt_pdu_reduc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("au ");

        txt_pau_reduc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btn_ok_dt_reduc.setBackground(new java.awt.Color(0, 102, 153));
        btn_ok_dt_reduc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_ok_dt_reduc.setForeground(new java.awt.Color(255, 255, 255));
        btn_ok_dt_reduc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Approval_25px.png"))); // NOI18N
        btn_ok_dt_reduc.setText("Approuver");
        btn_ok_dt_reduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ok_dt_reducActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("N° Matricule:");

        txt_mois_reduc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(txt_annee_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(txt_mois_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel12)
                        .addGap(54, 54, 54)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_ok_dt_reduc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_pau_reduc, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(27, 27, 27)
                        .addComponent(txt_num_perso_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txt_pdu_reduc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txt_num_perso_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addComponent(txt_pdu_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pau_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel12))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txt_mois_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txt_annee_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ok_dt_reduc))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Date de la déduction réglementaire", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        txt_nom_perso_reduc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nom_perso_reduc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nom_perso_reduc.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Nom te prénom :");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("N° matricule :");

        txt_num_perso_reduc2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_num_perso_reduc2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_num_perso_reduc2.setEnabled(false);

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText("Salaire de base :");

        txt_sal_base_reduc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_sal_base_reduc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_sal_base_reduc.setEnabled(false);

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("Ar");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Allocation familliale :");

        txt_famille_reduc.setEditable(false);
        txt_famille_reduc.setBackground(new java.awt.Color(255, 255, 255));
        txt_famille_reduc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_famille_reduc.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("Ar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel29))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nom_perso_reduc)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_sal_base_reduc, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_num_perso_reduc2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30)
                                .addGap(0, 106, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(64, 64, 64)
                                .addComponent(txt_famille_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel31)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_nom_perso_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_num_perso_reduc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_sal_base_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel29))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_famille_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31))
                    .addComponent(jLabel6))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Date de la déduction réglementaire", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Cession de salaires :");

        txt_cession_sal_reduc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nouriture et logement :");

        txt_nouri_loge_reduc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nouri_loge_reduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nouri_loge_reducActionPerformed(evt);
            }
        });

        txt_autres_reduc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_vaca_sup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_vaca_sup.setText("Autres réduction:");

        btn_ok_reduc_final.setBackground(new java.awt.Color(0, 102, 153));
        btn_ok_reduc_final.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_ok_reduc_final.setForeground(new java.awt.Color(255, 255, 255));
        btn_ok_reduc_final.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Ok_25px.png"))); // NOI18N
        btn_ok_reduc_final.setText("Valider");
        btn_ok_reduc_final.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ok_reduc_finalActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Ar");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Ar");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Ar");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_cession_sal_reduc)
                            .addComponent(txt_nouri_loge_reduc)
                            .addComponent(txt_autres_reduc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txt_element_sal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(autres_deduc, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_base_imposable, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(allocation, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(suple, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(cnaps_pourcent, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(irsa_min, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(irsa_val, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_vaca_sup)
                                    .addComponent(irsa_pourcent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addComponent(btn_ok_reduc_final, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(150, 150, 150))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(osief_pourcent, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_cession_sal_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_nouri_loge_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_autres_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(txt_vaca_sup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(osief_pourcent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(irsa_pourcent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_ok_reduc_final)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(suple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(irsa_val, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_base_imposable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(autres_deduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(irsa_min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cnaps_pourcent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_element_sal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(allocation, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Date de la déduction réglementaire", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Totale de réduction et taxes :");

        txt_totale_reduc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_totale_reduc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_totale_reduc.setEnabled(false);

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("Ar");

        txt_iras_reduce.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_iras_reduce.setEnabled(false);
        txt_iras_reduce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_iras_reduceActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Ar");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("IRSA:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("OSIEF :");

        txt_osief_reduc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_osief_reduc.setEnabled(false);
        txt_osief_reduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_osief_reducActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Ar");

        txt_cnaps_reduc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_cnaps_reduc.setEnabled(false);
        txt_cnaps_reduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cnaps_reducActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Ar");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("CNaPS :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Avances :");

        txt_avances_reduc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_avances_reduc.setEnabled(false);
        txt_avances_reduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_avances_reducActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Ar");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Salaire brutes:");

        txt_brute_av_reduc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_brute_av_reduc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_brute_av_reduc.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Ar");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(154, 154, 154))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(143, 143, 143)))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addGap(81, 81, 81)))
                    .addComponent(jLabel9)
                    .addComponent(jLabel15)
                    .addComponent(jLabel27))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txt_cnaps_reduc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txt_osief_reduc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txt_iras_reduce)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txt_totale_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28)))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(txt_avances_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel17))
                        .addComponent(txt_brute_av_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel16)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_brute_av_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_avances_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cnaps_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_osief_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_iras_reduce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(txt_totale_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_Reset_20px.png"))); // NOI18N
        jButton1.setText("Effacer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(txt_impot_coressp, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(txt_enfant, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_enre_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_annuler_entrep, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_impot_coressp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_enfant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_enre_reduc)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_annuler_entrep))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_annuler_entrepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_annuler_entrepActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_annuler_entrepActionPerformed

    private void btn_enre_reducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enre_reducActionPerformed
        //tsy tokony io le variable fa kamo be zah ra,avy da nagako @io avao.
        //fe mety avao se.
        String nb =  txt_cession_sal_reduc.getText();
        String idm = txt_nouri_loge_reduc.getText();
        String voca = txt_autres_reduc.getText();
       if(nb.equals("")==true || idm.equals("")==true || voca.equals("")==true){
           JOptionPane.showMessageDialog(null,"Veillez remplir les champs  vides au mois mettez zéro (0) comme les autres","Message",JOptionPane.WARNING_MESSAGE);
       }else{
           double totale;
    String avance = txt_avances_reduc.getText();
    String nouri_loge = txt_nouri_loge_reduc.getText();
    String cess_sal = txt_cession_sal_reduc.getText();
    String cnaps = txt_cnaps_reduc.getText();
    String osief = txt_osief_reduc.getText();
    String irsa = txt_iras_reduce.getText();
    String num_perso = txt_num_perso_reduc2.getText();
    String autres = txt_autres_reduc.getText();
    double autres_c =Double.parseDouble(autres);
    //convertisation en double
    double avance_c = Double.parseDouble(avance);
    double nouri_loge_c = Double.parseDouble(nouri_loge);
    double cesse_sal_c = Double.parseDouble(cess_sal);
    double cnaps_c = Double.parseDouble(cnaps);
    double osief_c = Double.parseDouble(osief);
    double irsa_c = Double.parseDouble(irsa);
    totale = (avance_c+nouri_loge_c+cesse_sal_c+cnaps_c+osief_c+irsa_c+autres_c);
    String totale_ok = DeuxChiffresApresVirgule.ChiffresApresVirgule(totale);
    txt_totale_reduc.setText(totale_ok); 
    Date actuelle = new Date();
DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
String periode_du = dt.format(txt_pdu_reduc.getDate());
String periode_au = dt.format(txt_pau_reduc.getDate());
String date_now = dateFormat.format(actuelle);
//String mois_av = txt_mois_sup.getSelectedItem().toString();
int annee = txt_annee_reduc.getYear();
int mw = txt_mois_reduc.getMonth();
int mws = mw+01;
String mois_red = Integer.toString(mws);
String annee_red = Integer.toString(annee);  
int infos = JOptionPane.showConfirmDialog(this,"Voullez-vous vraiment effectué cette opération de la réduction ?","Message",JOptionPane.YES_NO_OPTION);
   if(infos==JOptionPane.YES_OPTION){ 
               try {
                   String num_p = txt_num_perso_reduc.getSelectedItem().toString();
                   con_db = new Connecter_bd();
                   query = "select * from tab_av where num_perso='"+num_p+"' and mois_av='"+mws+"' and annee_av='"+annee+"'";
                   ResultSet rs = con_db.executeQuery(query);
                   if(rs.next()){
                       String totale_brute_av = rs.getString("totale_brute_av");
                       double brute = Double.parseDouble(totale_brute_av);
                       String totale_reduc = txt_totale_reduc.getText();
                       double reduc = Double.parseDouble(totale_reduc);
                       double avance_add = Double.parseDouble(txt_avances_reduc.getText());
                       String totales = DeuxChiffresApresVirgule.ChiffresApresVirgule(reduc);
                       double tot = Double.parseDouble(totales);
                       if(brute>reduc){
                           classe_reduction clr = new classe_reduction(num_perso,date_now,mois_red,annee_red,periode_du,periode_au,cnaps_c,osief_c,irsa_c,cesse_sal_c,nouri_loge_c,autres_c,avance_add,tot);
                           requet_reduction rqtreduc = new requet_reduction();
                           try {
                               rqtreduc.ajouter(clr);
                               declaration();
                           } catch (Exception ex) {
                               Logger.getLogger(reduction_add.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           JOptionPane.showMessageDialog(null,"Les informations ont été  bien enregistrés","Message",JOptionPane.PLAIN_MESSAGE);
                       }else{
                           JOptionPane.showMessageDialog(null,"La total de brute de salaire doit supérieur ou equale au totale  taxes et réduction","Message",JOptionPane.WARNING_MESSAGE);
                       }
                   }else{
                       String sal_base = txt_sal_base_reduc.getText();
                       double salaire_base = Double.parseDouble(sal_base);
                       String totale_reduc = txt_totale_reduc.getText();
                       double reduc = Double.parseDouble(totale_reduc);
                       double avance_add = Double.parseDouble(txt_avances_reduc.getText());
                       if(salaire_base>reduc){
                       classe_reduction clr = new classe_reduction(num_perso,date_now,mois_red,annee_red,periode_du,periode_au,cnaps_c,osief_c,irsa_c,cesse_sal_c,nouri_loge_c,autres_c,avance_add,totale);
                       requet_reduction rqtreduc = new requet_reduction();
                       rqtreduc.ajouter(clr);
                       declaration();
                       JOptionPane.showMessageDialog(null,"L'enregistrement a été bien effectué ","Message",JOptionPane.PLAIN_MESSAGE);
                       }else{
                          JOptionPane.showMessageDialog(null,"La total de brute de salaire doit supérieur ou equale au totale  taxes et réduction","Message",JOptionPane.WARNING_MESSAGE);
                            }  
                       }
                       /* int ms = txt_mois_reduc.getMonth();
                       int mois = ms+1;
                       int annee = txt_annee_reduc.getYear();
                       String an = Integer.toString(annee);
                       String num_p = txt_num_perso_reduc.getSelectedItem().toString();
                       con_db = new Connecter_bd();
                       query = "select * from tab_av where num_perso='"+num_p+"' and mois_av='"+mois+"' and annee_av='"+an+"'";
                       ResultSet rs = con_db.executeQuery(query);
                       if(rs.next()){
                       String totale_brute_av = rs.getString("totale_brute_av");
                       txt_avances_reduc.setText(avance);
                       String totale_reduc = txt_totale_reduc.getText();
                       */        
          }    catch (SQLException ex) { 
                   Logger.getLogger(reduction_add.class.getName()).log(Level.SEVERE, null, ex);
               } catch (Exception ex) {
                   Logger.getLogger(reduction_add.class.getName()).log(Level.SEVERE, null, ex);
               } 
         }   
       }

    }//GEN-LAST:event_btn_enre_reducActionPerformed
private void totale_brute(){
    double totale;
    String avance = txt_avances_reduc.getText();
    String nouri_loge = txt_nouri_loge_reduc.getText();
    String cess_sal = txt_cession_sal_reduc.getText();
    String cnaps = txt_cnaps_reduc.getText();
    String osief = txt_osief_reduc.getText();
    String irsa = txt_iras_reduce.getText();
   // String num_perso = txt_num_perso_reduc2.getText();
    String autres = txt_autres_reduc.getText();
    double autres_c =Double.parseDouble(autres);
    //convertisation en double
    double avance_c = Double.parseDouble(avance);
    double nouri_loge_c = Double.parseDouble(nouri_loge);
    double cesse_sal_c = Double.parseDouble(cess_sal);
    double cnaps_c = Double.parseDouble(cnaps);
    double osief_c = Double.parseDouble(osief);
    double irsa_c = Double.parseDouble(irsa);
    totale = (avance_c+nouri_loge_c+cesse_sal_c+cnaps_c+osief_c+irsa_c+autres_c);
    String totale_ok = DeuxChiffresApresVirgule.ChiffresApresVirgule(totale);
    txt_totale_reduc.setText(totale_ok); 
}

    private void btn_ok_dt_reducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ok_dt_reducActionPerformed
 if(((JTextField)txt_pdu_reduc.getDateEditor().getUiComponent()).getText().equals("")==false || 
      ((JTextField)txt_pau_reduc.getDateEditor().getUiComponent()).getText().equals("")==false){
        //pour l'année
        DateFormat dateFormat_an = new SimpleDateFormat("yyyy");
Date pdu = txt_pdu_reduc.getDate();
String pdu2 = dateFormat_an.format(pdu);
Date pau = txt_pau_reduc.getDate();
String pau2 = dateFormat_an.format(pau);
    int pan1_1 = Integer.parseInt(pdu2);
    int pan2_2 = Integer.parseInt(pau2);
  //pour le mois
DateFormat dateFormat_ms = new SimpleDateFormat("MM");
Date pdu_ms = txt_pdu_reduc.getDate();
String pdu1_ms = dateFormat_ms.format(pdu_ms);
Date pau_ms = txt_pau_reduc.getDate();
String pau2_ms = dateFormat_ms.format(pau_ms);
    int pdu_ms1 = Integer.parseInt(pdu1_ms);
    int pau_ms1 = Integer.parseInt(pau2_ms);   
if((pan1_1==pan2_2) && (pdu_ms1 == pau_ms1)){
Date date_now = new Date();
DateFormat dateFormat_now_an = new SimpleDateFormat("yyyy");
String dt_an_now = dateFormat_now_an.format(date_now);
DateFormat dateFormat_now_mw = new SimpleDateFormat("MM");
String dt_mw_now = dateFormat_now_mw.format(date_now);
    int an_now_1 = Integer.parseInt(dt_an_now);
    int mw_now_1 = Integer.parseInt(dt_mw_now);
int an = txt_annee_reduc.getYear();
int mw = txt_mois_reduc.getMonth();
int mws= mw+01;
   if((mw_now_1==pdu_ms1) && (an_now_1==pan1_1) && (an==an_now_1) && (mws==mw_now_1)){
        try {
        int annee = txt_annee_reduc.getYear();
        //String an = Integer.toString(annee);
        String num_p = txt_num_perso_reduc.getSelectedItem().toString();
        //String ms = txt_mois_avance.getSelectedItem().toString();
        con_db = new Connecter_bd();
        query = "select * from tab_reduc where num_perso='"+num_p+"' and mois_reduc='"+mws+"' and annee_reduc='"+annee+"'";
        ResultSet rs = con_db.executeQuery(query);
        if(rs.next()){
            JOptionPane.showMessageDialog(null,"Désolé vous ne pouvez pas entrer deux (02) fois l'heure suplémentaire dans un mois Sauf la mofification \n Veillez entendre le mois prochain SVP.","Message",JOptionPane.WARNING_MESSAGE);   
        }else{
           ///select_sal_perso();
            //btn_ok_dt_sup_final.enable(true);
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        String dt1 = dt.format(txt_pdu_reduc.getDate());
        String dt2 = dt.format(txt_pau_reduc.getDate());
                Date date1 = dt.parse(dt1);
                Date date2 = dt.parse(dt2);
        if(date1.before(date2)){
        allocation();
        select_nom_perso();
        select_avance();
        select_sal_brute();
        select_irsa();
        totale_brute(); 
//      declaration();
        calcule();
        
        }else{
             JOptionPane.showMessageDialog(null,"La date de débute du période doit être inférieur à la date de fin du période, SVP","Message",JOptionPane.WARNING_MESSAGE);         
        }
       
        }
    } catch (Exception ex) {
        Logger.getLogger(avance_add.class.getName()).log(Level.SEVERE, null, ex);
    }
   }else{
        JOptionPane.showMessageDialog(null,"Le mois et l'année doivent être actuelle","Message",JOptionPane.WARNING_MESSAGE);
   } 
    
}else{
     JOptionPane.showMessageDialog(null,"Le mois et l'année de ce deux dates doivent être éguals","Message",JOptionPane.WARNING_MESSAGE); 
}
 }else{
      JOptionPane.showMessageDialog(null,"Veillez remplir les deux champs dates","Message",JOptionPane.WARNING_MESSAGE);
 } 
        /*try {      
        select_nom_perso();
        select_avance();
        select_sal_brute();
       select_irsa();
    } catch (Exception ex) {
        Logger.getLogger(reduction_add.class.getName()).log(Level.SEVERE, null, ex);
    }*/
    }//GEN-LAST:event_btn_ok_dt_reducActionPerformed

    private void txt_iras_reduceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_iras_reduceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_iras_reduceActionPerformed

    private void txt_osief_reducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_osief_reducActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_osief_reducActionPerformed

    private void txt_avances_reducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_avances_reducActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_avances_reducActionPerformed

    private void txt_cnaps_reducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cnaps_reducActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cnaps_reducActionPerformed

    private void txt_nouri_loge_reducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nouri_loge_reducActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nouri_loge_reducActionPerformed
public void autres_reduc(){
    double s_sal =Double.parseDouble(txt_cession_sal_reduc.getText());
    double log = Double.parseDouble(txt_nouri_loge_reduc.getText());
    double autres =Double.parseDouble(txt_autres_reduc.getText());
    double sommes_autres =(s_sal+log+autres);
    String somme_affichher =Double.toString(sommes_autres);
    autres_deduc.setText(somme_affichher);
//    autres_deduc.
}        
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    try {
         select_num_perso();
         variable();
        txt_cession_sal_reduc.setText("0");
        txt_nouri_loge_reduc.setText("0");
        txt_autres_reduc.setText("0");
        txt_sal_base_reduc.setText("0");
        txt_brute_av_reduc.setText("0");
        txt_avances_reduc.setText("0");
        txt_cnaps_reduc.setText("0");
        txt_osief_reduc.setText("0");
        txt_iras_reduce.setText("0");
        txt_totale_reduc.setText("0");
        txt_base_imposable.setText("0");
        txt_impot_coressp.setText("0");
//        txt_chrage_famille.setText("0.0");
//        txt_impots.setText("0.0");
        txt_element_sal.setText("0");
        txt_enfant.setText("0");
         autres_deduc.setText("0");
//        txt_sal_brute.setText("0.0");
//suple.setText("0");
//   osief_pourcent.setText("0");
//   irsa_pourcent.setText("0");
//   irsa_min.setText("0");
//   irsa_val.setText("0");
//   cnaps_pourcent.setText("0");
//   allocation.setText("0");
        
    } catch (Exception ex) {
        Logger.getLogger(reduction_add.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_formWindowOpened

    private void btn_ok_reduc_finalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ok_reduc_finalActionPerformed

        if(txt_num_perso_reduc2.equals("")==true){
            JOptionPane.showMessageDialog(null,"Veillez approuvez le ","Message",JOptionPane.WARNING_MESSAGE);
        }else{
double totale;
            String avance = txt_avances_reduc.getText();
            String nouri_loge = txt_nouri_loge_reduc.getText();
            String cess_sal = txt_cession_sal_reduc.getText();
            String cnaps = txt_cnaps_reduc.getText();
            String osief = txt_osief_reduc.getText();
            String irsa = txt_iras_reduce.getText();
            //convertisation en double
            double avance_c = Double.parseDouble(avance);
            double nouri_loge_c = Double.parseDouble(nouri_loge);
            double cesse_sal_c = Double.parseDouble(cess_sal);
            double cnaps_c = Double.parseDouble(cnaps);
            double osief_c = Double.parseDouble(osief);
            double irsa_c = Double.parseDouble(irsa);
            totale = (avance_c+nouri_loge_c+cesse_sal_c+cnaps_c+osief_c+irsa_c);
            String totale_ok = DeuxChiffresApresVirgule.ChiffresApresVirgule(totale);
            txt_totale_reduc.setText(totale_ok);
            autres_reduc();
    }//GEN-LAST:event_btn_ok_reduc_finalActionPerformed
}

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

//double totale;
//            String avance = txt_avances_reduc.getText();
//            String nouri_loge = txt_nouri_loge_reduc.getText();
//            String cess_sal = txt_cession_sal_reduc.getText();
//            String cnaps = txt_cnaps_reduc.getText();
//            String osief = txt_osief_reduc.getText();
//            String irsa = txt_iras_reduce.getText();
//            //convertisation en double
//            double avance_c = Double.parseDouble(avance);
//            double nouri_loge_c = Double.parseDouble(nouri_loge);
//            double cesse_sal_c = Double.parseDouble(cess_sal);
//            double cnaps_c = Double.parseDouble(cnaps);
//            double osief_c = Double.parseDouble(osief);
//            double irsa_c = Double.parseDouble(irsa);
//            totale = (avance_c+nouri_loge_c+cesse_sal_c+cnaps_c+osief_c+irsa_c);
//            String totale_ok = DeuxChiffresApresVirgule.ChiffresApresVirgule(totale);
//            txt_totale_reduc.setText(totale_ok);
//            autres_reduc();
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
            java.util.logging.Logger.getLogger(reduction_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reduction_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reduction_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reduction_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reduction_add().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField allocation;
    private javax.swing.JTextField autres_deduc;
    private javax.swing.JButton btn_annuler_entrep;
    private javax.swing.JButton btn_enre_reduc;
    private javax.swing.JButton btn_ok_dt_reduc;
    private javax.swing.JButton btn_ok_reduc_final;
    private javax.swing.JTextField cnaps_pourcent;
    private javax.swing.JTextField irsa_min;
    private javax.swing.JTextField irsa_pourcent;
    private javax.swing.JTextField irsa_val;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField osief_pourcent;
    private javax.swing.JTextField suple;
    private com.toedter.calendar.JYearChooser txt_annee_reduc;
    private javax.swing.JTextField txt_autres_reduc;
    private javax.swing.JTextField txt_avances_reduc;
    private javax.swing.JTextField txt_base_imposable;
    private javax.swing.JTextField txt_brute_av_reduc;
    private javax.swing.JTextField txt_cession_sal_reduc;
    private javax.swing.JTextField txt_cnaps_reduc;
    private javax.swing.JTextField txt_element_sal;
    private javax.swing.JTextField txt_enfant;
    private javax.swing.JTextField txt_famille_reduc;
    private javax.swing.JTextField txt_impot_coressp;
    private javax.swing.JTextField txt_iras_reduce;
    private com.toedter.calendar.JMonthChooser txt_mois_reduc;
    private javax.swing.JTextField txt_nom_perso_reduc;
    private javax.swing.JTextField txt_nouri_loge_reduc;
    private javax.swing.JComboBox<String> txt_num_perso_reduc;
    private javax.swing.JTextField txt_num_perso_reduc2;
    private javax.swing.JTextField txt_osief_reduc;
    private com.toedter.calendar.JDateChooser txt_pau_reduc;
    private com.toedter.calendar.JDateChooser txt_pdu_reduc;
    private javax.swing.JTextField txt_sal_base_reduc;
    private javax.swing.JTextField txt_totale_reduc;
    private javax.swing.JLabel txt_vaca_sup;
    // End of variables declaration//GEN-END:variables
}
