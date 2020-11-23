/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forme;

import com.classe.DeuxChiffresApresVirgule;
import com.classe.classe_suplemetaire;
import com.requete.Connecter_bd;
import com.requete.connections;
import com.requete.requet_suplementaire;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
public class suplementaire_add extends javax.swing.JFrame {
private String query;
private Connecter_bd con_db;
connections maCon = new connections();
ResultSet rs;
PreparedStatement ps;
Statement st;
Connection conc;
 
    /**
     * Creates new form avantage_add
     */
    public suplementaire_add() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
private void select_num_perso() throws Exception, SQLException{
  // txt_num_perso_sup.removeAllItems();
    con_db = new Connecter_bd();
    query = "select * from tab_perso";
    ResultSet rs = con_db.executeQuery(query);
    while(rs.next()){
       String perso = rs.getString("num_perso").toString();
       txt_num_perso_sup.addItem(perso);
   } 
}
private void select_sal_perso() throws Exception, SQLException{
   //txt_num_perso_sup.removeAllItems();
   String num_p_sup = txt_num_perso_sup.getSelectedItem().toString();
    con_db = new Connecter_bd();
    query = "select * from tab_contrat where num_perso='"+num_p_sup+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
       String sal_b_perso = rs.getString("sal_base_contrat").toString();
       
       txt_sal_base_sup.setText(sal_b_perso);
       select_nom_perso();
   }else{
        
    } 
}
private void select_nom_perso() throws Exception, SQLException{
    con_db = new Connecter_bd();
     //txt_num_perso_avcance.removeAllItems();
    String num_p = txt_num_perso_sup.getSelectedItem().toString();
    query = "select * from tab_perso where num_perso='"+num_p+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
       String perso = rs.getString("nom_pre_perso").toString();
       String num = rs.getString("num_perso");
       txt_nom_perso_sup.setText(perso);
       txt_num_perso_sup2.setText(num);
       txt_nbrhrs_sup.enable(true);
       txt_idemnite_sup.enable(true);
       txt_vacation_sup.enable(true);
//        txt_famille_sup.enable(true);
   } 
}
private void quarente(){
    double conste = 173.33; 
    int hure40 =Integer.parseInt(txt_heure40.getText());
    String sal_base = txt_sal_base_sup.getText();
    double sal_base_d = Double.parseDouble(sal_base);
     //pourquarante
    double val_quarante =(sal_base_d/conste);
    double ok40 = (val_quarante*hure40);
    double quarente =(ok40*40);
    double final_40 = (quarente/100);
    double finale40 = (final_40+ok40);
     String totale40 = DeuxChiffresApresVirgule.ChiffresApresVirgule(finale40);
     txt_plus40.setText(totale40);
}
private void sommes(){
    double aciente =Double.parseDouble(txt_anciente.getText());
    double idemnite=Double.parseDouble(txt_idemnite_sup.getText());
    double vocation =Double.parseDouble(txt_vacation_sup.getText());
    double val8 =Double.parseDouble(txt_val_8_sup.getText());
    double val_plus8 =Double.parseDouble(txt_val_8_plus_sup.getText());
    double val_40 = Double.parseDouble(txt_plus40.getText());
    double sal_base =Double.parseDouble(txt_sal_base_sup.getText());
    double majoration =(val8+val_plus8+val_40);
     String maj= DeuxChiffresApresVirgule.ChiffresApresVirgule(majoration);
     txt_val_totale_sup.setText(maj);
    double sommes =(aciente+idemnite+vocation+val8+val_plus8+val_40+sal_base);
    String total= DeuxChiffresApresVirgule.ChiffresApresVirgule(sommes);
    txt_totale_brute_sup.setText(total);
}
public void calcule_all(){
    txt_val_8_plus_sup.setText("0");
    quarente();
    DecimalFormat df = new DecimalFormat("0.00");
    df.setRoundingMode(RoundingMode.HALF_UP);
    double conste = 173.33;
   //double conste = 174;
    int huite = 8;
    double valeurheure;
    double ok;
    double trente;
    double ok_final;
    double finale;
    double cinquante;
    String nbrheur = txt_nbrhrs_sup.getText();
    int heure_totale_d = Integer.parseInt(nbrheur);
    String sal_base = txt_sal_base_sup.getText();
    double sal_base_d = Double.parseDouble(sal_base);
if(heure_totale_d<=huite){
    valeurheure = (sal_base_d/conste);
    ok = (valeurheure*heure_totale_d);   
    trente = (ok*30);  
    ok_final = (trente/100);
    finale = (ok_final+ok);
    //arrondissement
    //DecimalFormat df = new DecimalFormat("#.##");
    //df.setRoundingMode(RoundingMode.HALF_UP);
    //String totale = Double.toString(DeuxChiffresApresVirgule.ChiffresApresVirgule(finale));
    String totale = DeuxChiffresApresVirgule.ChiffresApresVirgule(finale);
    txt_val_8_sup.setText(totale);
//    txt_val_totale_sup.setText(totale);
//    txt_totale_brute_sup
//String fami = txt_famille_sup.getText();
//double famille = Double.parseDouble(fami);
//double bru = (idemnite+vocation+finale+sal_base_d);
//String brutes = Double.toString(bru);
//String brutes = df.format(bru);
//String brutes = DeuxChiffresApresVirgule.ChiffresApresVirgule(bru);
//quarente();
}else{
     int total_intiale = (heure_totale_d - huite);
valeurheure = (sal_base_d/conste);
ok = (valeurheure*total_intiale);
cinquante = (ok*50);
ok_final = (cinquante/100);
finale = (ok_final+ok);  
//DecimalFormat df = new DecimalFormat("#.##");
    //df.setRoundingMode(RoundingMode.HALF_UP);
double ok2 = (valeurheure*huite);
double trente2 = (ok2*30);
double ok_finale2 = (trente2/100);
double finale2 = (ok_finale2+ok2);
double sommes = (finale+finale2);
String totaleautres = Double.toString(finale);
String totale8z =Double.toString(finale2);
//String somme_totale = Double.toString(sommes);
//arrondissement
//String totaleautres = df.format(finale);
//String totale8 =df.format(finale2);
//tring somme_totale = df.format(sommes);
String totale8 = DeuxChiffresApresVirgule.ChiffresApresVirgule(finale2);
txt_val_8_sup.setText(totale8);
String finalez = DeuxChiffresApresVirgule.ChiffresApresVirgule(finale);
txt_val_8_plus_sup.setText(finalez);
//String sommez= DeuxChiffresApresVirgule.ChiffresApresVirgule(sommes);
//txt_val_totale_sup.setText(sommez);
//String ide = txt_idemnite_sup.getText();
//double idemnite = Double.parseDouble(ide);
//String voc = txt_vacation_sup.getText();
//double vocation = Double.parseDouble(voc);
////String fami = txt_famille_sup.getText();
////double famille = Double.parseDouble(fami);
//String brutes = Double.toString(bru);
   }
//quarente();
}
public void ajout_verifier_av() throws Exception{
String num_p = txt_num_perso_sup2.getText();
Date actuelle = new Date();
DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
String periode_du = dt.format(txt_pdu_sup.getDate());
String periode_au = dt.format(txt_pau_sup.getDate());
String date_now = dateFormat.format(actuelle);
//String mois_av = txt_mois_sup.getSelectedItem().toString();
int annee = txt_annee_sup.getYear();
int mw = txt_mois_sup.getMonth();
int mws = mw+01;
String mois = Integer.toString(mws);
String annee_av = Integer.toString(annee);
int heur30_40 = Integer.parseInt(txt_nbrhrs_sup.getText());
int heur40 =Integer.parseInt(txt_heure40.getText());
    double aciente =Double.parseDouble(txt_anciente.getText());
    double idemnite=Double.parseDouble(txt_idemnite_sup.getText());
    double vocation =Double.parseDouble(txt_vacation_sup.getText());
    double val8 =Double.parseDouble(txt_val_8_sup.getText());
    double val_plus8 =Double.parseDouble(txt_val_8_plus_sup.getText());
    double val_40 = Double.parseDouble(txt_plus40.getText());
//    double sal_base =Double.parseDouble(txt_sal_base_sup.getText());
    double sommes = Double.parseDouble(txt_totale_brute_sup.getText());
classe_suplemetaire clas = new classe_suplemetaire(num_p,date_now,mois,annee_av,periode_du,periode_au,idemnite,vocation,aciente,heur30_40,heur40,val8,val_plus8,val_40,sommes);
requet_suplementaire rqtsup= new requet_suplementaire();
rqtsup.ajouter(clas);
JOptionPane.showMessageDialog(null,"Les informations ont été  bien enregistrées","Message",JOptionPane.PLAIN_MESSAGE);  
//tena mbola miasa be io code ambany natao commentaire io.
/*classe_suplemetaire cls = new classe_suplemetaire(num_p,date_now , mois_av, annee_av,periode_du,periode_au, idemnite_av, voca_av, nbr_av, hrsval8_av, hrsvalplus8_av, totale_brute_av);
    requet_suplementaire rqtsup = new requet_suplementaire();
    rqtsup.ajouter(cls);
    JOptionPane.showMessageDialog(null,"L'avatage a été bien enregistré ","Message",JOptionPane.PLAIN_MESSAGE);  
   /* String num_p_av = txt_num_perso_sup2.getText();
    String mois_av = txt_mois_sup.getSelectedItem().toString();
    int annee= txt_annee_sup.getYear();
    String annee_av = Integer.toString(annee);
    SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
    String dtdu = dt.format(txt_pdu_sup.getDate());
    String dtau = dt.format(txt_pau_sup.getDate());
    String netpay_av = txt_netpay_avance.getText();
    Double netp = Double.parseDouble(netpay_av)*/
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
        jButton1 = new javax.swing.JButton();
        btn_valider_sup = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_annee_sup = new com.toedter.calendar.JYearChooser();
        jLabel11 = new javax.swing.JLabel();
        txt_pdu_sup = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        txt_pau_sup = new com.toedter.calendar.JDateChooser();
        btn_ok_dt_sup = new javax.swing.JButton();
        txt_num_perso_sup = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txt_mois_sup = new com.toedter.calendar.JMonthChooser();
        jPanel3 = new javax.swing.JPanel();
        txt_nom_perso_sup = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_num_perso_sup2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_sal_base_sup = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_nbrhrs_sup = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_idemnite_sup = new javax.swing.JTextField();
        txt_vacation_sup = new javax.swing.JTextField();
        txt_vaca_sup = new javax.swing.JLabel();
        btn_ok_dt_sup_final = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt_anciente = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txt_heure40 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_totale_brute_sup = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_val_totale_sup = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_val_8_sup = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_val_8_plus_sup = new javax.swing.JTextField();
        txt_plus40 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GP");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("AVANTAGES");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Money_Transfer_50px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(402, 402, 402))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_annuler_entrep.setBackground(new java.awt.Color(0, 102, 153));
        btn_annuler_entrep.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_annuler_entrep.setForeground(new java.awt.Color(255, 255, 255));
        btn_annuler_entrep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Close_Window_25px.png"))); // NOI18N
        btn_annuler_entrep.setText("Annuler/Fermer");
        btn_annuler_entrep.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_annuler_entrep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_annuler_entrepActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Reset_25px.png"))); // NOI18N
        jButton1.setText("Effacer");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_valider_sup.setBackground(new java.awt.Color(0, 102, 153));
        btn_valider_sup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_valider_sup.setForeground(new java.awt.Color(255, 255, 255));
        btn_valider_sup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Checkmark_25px.png"))); // NOI18N
        btn_valider_sup.setText("Enregister");
        btn_valider_sup.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_valider_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_valider_supActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Période et date d'vances", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Mois du :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Année :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Période du");

        txt_pdu_sup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("au ");

        txt_pau_sup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btn_ok_dt_sup.setBackground(new java.awt.Color(0, 102, 153));
        btn_ok_dt_sup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_ok_dt_sup.setForeground(new java.awt.Color(255, 255, 255));
        btn_ok_dt_sup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Approval_25px.png"))); // NOI18N
        btn_ok_dt_sup.setText("Approuver");
        btn_ok_dt_sup.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_ok_dt_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ok_dt_supActionPerformed(evt);
            }
        });

        txt_num_perso_sup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("N° personnel :");

        txt_mois_sup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_annee_sup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_mois_sup, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel12)
                        .addGap(57, 57, 57)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_ok_dt_sup, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(txt_pau_sup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txt_num_perso_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_pdu_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txt_num_perso_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addComponent(txt_pdu_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_mois_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txt_annee_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pau_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel12)))
                        .addGap(10, 10, 10)
                        .addComponent(btn_ok_dt_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Période et date d'vances", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        txt_nom_perso_sup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nom_perso_sup.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nom_perso_sup.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Nom et prénom :");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("N° :");

        txt_num_perso_sup2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_num_perso_sup2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_num_perso_sup2.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Salaire de base :");

        txt_sal_base_sup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_sal_base_sup.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_sal_base_sup.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Ar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel19)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_sal_base_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addComponent(txt_num_perso_sup2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nom_perso_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_nom_perso_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_num_perso_sup2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_sal_base_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Période et date d'vances", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Heures suplementaires(30% et 50%):");

        txt_nbrhrs_sup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Primes et indemnités:");

        txt_idemnite_sup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_idemnite_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idemnite_supActionPerformed(evt);
            }
        });

        txt_vacation_sup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_vaca_sup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_vaca_sup.setText("Vocation ou pièces :");

        btn_ok_dt_sup_final.setBackground(new java.awt.Color(0, 102, 153));
        btn_ok_dt_sup_final.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_ok_dt_sup_final.setForeground(new java.awt.Color(255, 255, 255));
        btn_ok_dt_sup_final.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Ok_25px.png"))); // NOI18N
        btn_ok_dt_sup_final.setText("Valider");
        btn_ok_dt_sup_final.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_ok_dt_sup_final.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ok_dt_sup_finalActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Ar");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Ar");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Ancienneté :");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("Ar");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText("Hrs");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("Hrs (Pour le gardien de nuit)");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("Heures suplementaires(40%):");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel31)
                    .addComponent(txt_vaca_sup)
                    .addComponent(jLabel27))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_heure40, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(txt_nbrhrs_sup))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel30)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txt_idemnite_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_anciente, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_vacation_sup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btn_ok_dt_sup_final, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_nbrhrs_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_heure40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_idemnite_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_anciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_vacation_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(txt_vaca_sup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ok_dt_sup_final, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Période et date d'vances", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Total de brutes :");

        txt_totale_brute_sup.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_totale_brute_sup.setEnabled(false);
        txt_totale_brute_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totale_brute_supActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Ar");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("total de Majorations:");

        txt_val_totale_sup.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_val_totale_sup.setEnabled(false);
        txt_val_totale_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_val_totale_supActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Ar");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Majoration 40%:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Majoration 30% :");

        txt_val_8_sup.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_val_8_sup.setEnabled(false);
        txt_val_8_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_val_8_supActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Ar");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Ar");

        txt_val_8_plus_sup.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_val_8_plus_sup.setEnabled(false);
        txt_val_8_plus_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_val_8_plus_supActionPerformed(evt);
            }
        });

        txt_plus40.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_plus40.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_plus40.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Ar");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Majoration 50%:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txt_val_totale_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel23))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(63, 63, 63)
                                    .addComponent(txt_val_8_plus_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel26))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel20))
                                    .addGap(58, 58, 58)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(txt_val_8_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel17))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                            .addComponent(txt_plus40, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel18)))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_totale_brute_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel21)))
                        .addContainerGap(58, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_val_8_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel18)
                    .addComponent(txt_plus40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel26)
                    .addComponent(txt_val_8_plus_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txt_val_totale_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(34, 34, 34)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_totale_brute_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(517, 517, 517)
                        .addComponent(btn_valider_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_annuler_entrep, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_annuler_entrep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_valider_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_annuler_entrepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_annuler_entrepActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_annuler_entrepActionPerformed

    private void txt_idemnite_supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idemnite_supActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idemnite_supActionPerformed

    private void txt_totale_brute_supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totale_brute_supActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totale_brute_supActionPerformed

    private void btn_valider_supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_valider_supActionPerformed
        String nb =  txt_nbrhrs_sup.getText();
        String idm = txt_idemnite_sup.getText();
        String voca = txt_vacation_sup.getText();
       // String fami = txt_famille_sup.getText();
       if(nb.equals("")==true || idm.equals("")==true || voca.equals("")==true){
           JOptionPane.showMessageDialog(null,"Veillez remplir les champs sont vides sur heures suplémentaires et avantages","Message",JOptionPane.WARNING_MESSAGE);
       }else{
          if(nb.equals("")==true && idm.equals("")==true && voca.equals("")==true){
            JOptionPane.showMessageDialog(null,"Veillez saisir le valeur plus de Zéro (0)  pour effectué cetteopération\n au mois l'un des chapms sur heures supléméntaires \n Et avantages.","Message",JOptionPane.WARNING_MESSAGE);  
          }else{
            calcule_all();
   int infos = JOptionPane.showConfirmDialog(this,"Voullez-vous vraiment effectué cette opération ajout des avatages ?","Message",JOptionPane.YES_NO_OPTION);
   if(infos==JOptionPane.YES_OPTION){ 
         try {
      ajout_verifier_av();
         } catch (Exception ex) {
             Logger.getLogger(suplementaire_add.class.getName()).log(Level.SEVERE, null, ex);
         }
   }else{
       
   }       
          }    
       }
        
           
    }//GEN-LAST:event_btn_valider_supActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    try {
        select_num_perso();
        //txt_famille_sup.setText("0");
        txt_idemnite_sup.setText("0");
        txt_nbrhrs_sup.setText("0");
        txt_vacation_sup.setText("0");
        txt_val_8_sup.setText("0");
        txt_val_8_plus_sup.setText("0");
        txt_sal_base_sup.setText("0");
        txt_val_totale_sup.setText("0");
        txt_totale_brute_sup.setText("0");
        txt_idemnite_sup.setText("0");
        txt_anciente.setText("0");
        txt_heure40.setText("0");
      /*  txt_nbrhrs_sup.enable(false);
        txt_idemnite_sup.enable(false);
        txt_vacation_sup.enable(false);
        txt_famille_sup.enable(false);
        btn_ok_dt_sup_final.enable(false);*/
    } catch (Exception ex) {
        Logger.getLogger(suplementaire_add.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_formWindowOpened
/*
    //String num_p_sup = txt_num_perso_sup.getSelectedItem().toString();
        String heure_sup = txt_nbrhrs_sup.getText();
        int hrs = Integer.parseInt(heure_sup);
        double conste = 173.33;
        String sal_base_p = txt_sal_base_sup.getText();
        double salaire = Double.parseDouble(sal_base_p);
        double totale_sup_heure;
        double totale_avec_pourcent;
        int cinquante =50;
        int trente = 30;
        int var8 = 8;
        if(hrs<=var8){
            totale_sup_heure = ((salaire/conste)*hrs);
            totale_avec_pourcent = ((totale_sup_heure*30)/100);
            String v = Double.toString(totale_avec_pourcent);
            txt_totale_brute_sup.setText(v);
        }
        /* try {
            select_sal_perso();
        } catch (Exception ex) {
            Logger.getLogger(avantage_add.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    public void verifier_fonction(){
    try {
        con_db = new Connecter_bd();
        //txt_num_perso_avcance.removeAllItems();
        String num_p = txt_num_perso_sup.getSelectedItem().toString();
        query = "select * from tab_contrat where num_perso='"+num_p+"'";
        ResultSet rs = con_db.executeQuery(query);
        while(rs.next()){
            String service = rs.getString("service").toString();
           if(service.equals("Gardien de nuit")==true){
              
           }else{
              txt_heure40.setEnabled(false);
              txt_heure40.setText("0");
           }
           
//        txt_famille_sup.enable(true);
        }
    } catch (Exception ex) { 
        Logger.getLogger(suplementaire_add.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    private void btn_ok_dt_supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ok_dt_supActionPerformed
//    verifier_fonction();
        if(((JTextField)txt_pdu_sup.getDateEditor().getUiComponent()).getText().equals("")==false || 
      ((JTextField)txt_pau_sup.getDateEditor().getUiComponent()).getText().equals("")==false){
        //pour l'année
DateFormat dateFormat_an = new SimpleDateFormat("yyyy");
Date pdu = txt_pdu_sup.getDate();
String pdu2 = dateFormat_an.format(pdu);
Date pau = txt_pau_sup.getDate();
String pau2 = dateFormat_an.format(pau);
    int pan1_1 = Integer.parseInt(pdu2);
    int pan2_2 = Integer.parseInt(pau2);
//pour le mois
DateFormat dateFormat_ms = new SimpleDateFormat("MM");
Date pdu_ms = txt_pdu_sup.getDate();
String pdu1_ms = dateFormat_ms.format(pdu_ms);
Date pau_ms = txt_pau_sup.getDate();
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
int an = txt_annee_sup.getYear();
int mw = txt_mois_sup.getMonth();
int mws= mw+01;
   if((mw_now_1==pdu_ms1) && (an_now_1==pan1_1) && (an==an_now_1) && (mws==mw_now_1)){
     try {
        int annee = txt_annee_sup.getYear();
        //String an = Integer.toString(annee);
        String num_p = txt_num_perso_sup.getSelectedItem().toString();
        //String ms = txt_mois_avance.getSelectedItem().toString();
        con_db = new Connecter_bd();
        query = "select * from tab_av where num_perso='"+num_p+"' and mois_av='"+mws+"' and annee_av='"+annee+"'";
        ResultSet rs = con_db.executeQuery(query);
        if(rs.next()){
            JOptionPane.showMessageDialog(null,"Désolé vous ne pouvez pas entrer deux (02) fois l'heure suplémentaire dans un mois Sauf la mofification \n Veillez entendre le mois prochain SVP.","Message",JOptionPane.WARNING_MESSAGE);   
        }else{
            select_sal_perso();
           // allocation();
            btn_ok_dt_sup_final.enable(true);
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
       select_sal_perso();
       select_nom_perso();
    } catch (Exception ex) {
        Logger.getLogger(suplementaire_add.class.getName()).log(Level.SEVERE, null, ex);
    }*/
    }//GEN-LAST:event_btn_ok_dt_supActionPerformed
private void allocation() throws Exception{
        String num_p = txt_num_perso_sup.getSelectedItem().toString();
        //String ms = txt_mois_avance.getSelectedItem().toString();
        con_db = new Connecter_bd();
        query = "select * from tab_perso where num_perso='"+num_p+"'";
        ResultSet rs = con_db.executeQuery(query);
        double vola_zaza = 4000;
      if(rs.next()){
        String nbr = rs.getString("nbr_enf_perso");
        double nbr_enf = Double.parseDouble(nbr);
        double somme =( nbr_enf*vola_zaza);
        String vola = Double.toString(somme);
       //txt_famille_sup.setText(vola);
      }else{
          
      }
}
    private void btn_ok_dt_sup_finalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ok_dt_sup_finalActionPerformed
       /* txt_nbrhrs_sup.enable(true);
        txt_idemnite_sup.enable(true);
        txt_vacation_sup.enable(true);
        txt_famille_sup.enable(true);*/
        String nb =  txt_nbrhrs_sup.getText();
        String idm = txt_idemnite_sup.getText();
        String voca = txt_vacation_sup.getText();
//        String fami = txt_famille_sup.getText();
       if(nb.equals("")==true || idm.equals("")==true || voca.equals("")==true){
        JOptionPane.showMessageDialog(null,"Veillez remplir les champs  vides","Message",JOptionPane.WARNING_MESSAGE);
       }else{
          if(nb.equals("")==true && idm.equals("")==true && voca.equals("")==true){
        JOptionPane.showMessageDialog(null,"Veillez saisir le valeur plus de Zéro (0) pour effectué cetteopération\n au mois l'un des champs","Message",JOptionPane.WARNING_MESSAGE);  
          }else{
              calcule_all(); 
              quarente();
              sommes();
          }
            
       }
              
    }//GEN-LAST:event_btn_ok_dt_sup_finalActionPerformed
private void calcule40(){
    
}
    private void txt_val_totale_supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_val_totale_supActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_val_totale_supActionPerformed

    private void txt_val_8_supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_val_8_supActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_val_8_supActionPerformed

    private void txt_val_8_plus_supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_val_8_plus_supActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_val_8_plus_supActionPerformed

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
            java.util.logging.Logger.getLogger(suplementaire_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(suplementaire_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(suplementaire_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(suplementaire_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new suplementaire_add().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_annuler_entrep;
    private javax.swing.JButton btn_ok_dt_sup;
    private javax.swing.JButton btn_ok_dt_sup_final;
    private javax.swing.JButton btn_valider_sup;
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
    private javax.swing.JTextField txt_anciente;
    private com.toedter.calendar.JYearChooser txt_annee_sup;
    private javax.swing.JTextField txt_heure40;
    private javax.swing.JTextField txt_idemnite_sup;
    private com.toedter.calendar.JMonthChooser txt_mois_sup;
    private javax.swing.JTextField txt_nbrhrs_sup;
    private javax.swing.JTextField txt_nom_perso_sup;
    private javax.swing.JComboBox<String> txt_num_perso_sup;
    private javax.swing.JTextField txt_num_perso_sup2;
    private com.toedter.calendar.JDateChooser txt_pau_sup;
    private com.toedter.calendar.JDateChooser txt_pdu_sup;
    private javax.swing.JTextField txt_plus40;
    private javax.swing.JTextField txt_sal_base_sup;
    private javax.swing.JTextField txt_totale_brute_sup;
    private javax.swing.JLabel txt_vaca_sup;
    private javax.swing.JTextField txt_vacation_sup;
    private javax.swing.JTextField txt_val_8_plus_sup;
    private javax.swing.JTextField txt_val_8_sup;
    private javax.swing.JTextField txt_val_totale_sup;
    // End of variables declaration//GEN-END:variables
}
