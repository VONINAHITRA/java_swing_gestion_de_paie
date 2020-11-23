/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forme;

import com.classe.ConvertirChiffreEnLettre;
import com.classe.DeuxChiffresApresVirgule;
import com.classe.classe_bulletin;
import com.classe.classe_print_bulletin;
import com.requete.Connecter_bd;
import com.requete.connections;
import com.requete.requet_bulletin;
import com.requete.requet_print_bulletin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author VONINAHITRA
 */
public class bulletin_add extends javax.swing.JFrame {
private String query;
private Connecter_bd con_db;
connections maCon = new connections();
ResultSet rs;
PreparedStatement ps;
Statement st;
Connection conc;
    /**
     * Creates new form payement_add
     */
    public bulletin_add() {
        initComponents();
        this.setLocationRelativeTo(null);
        print_autres_reduc.setVisible(false);
        print_avance.setVisible(false);
        print_cess_sal_base.setVisible(false);
        print_clasifi.setVisible(false);
        print_cnaps.setVisible(false);
        print_hrs_sup8.setVisible(false);
        print_hrs_sup8_plus.setVisible(false);
        print_idemnite.setVisible(false);
        print_indice.setVisible(false);
        print_irsa.setVisible(false);
        print_lettre.setVisible(false);
        print_nom.setVisible(false);
        print_nouri_log_reduc.setVisible(false);
        print_num.setVisible(false);
        print_osief.setVisible(false);
        print_sal_base.setVisible(false);
        print_totale_brute_av.setVisible(false);
        print_totale_brute_reduc.setVisible(false);
        print_voca_piece.setVisible(false);
        print_anciente.setVisible(false);
        print_heure40.setVisible(false);
                
    }
private void select_num_perso() throws Exception, SQLException{
    txt_num_perso_b.removeAllItems();
    con_db = new Connecter_bd();
    query = "select * from tab_perso";
    ResultSet rs = con_db.executeQuery(query);
    while(rs.next()){
       String perso = rs.getString("num_perso").toString();
       txt_num_perso_b.addItem(perso);
   } 
}
private void select_sal_base() throws Exception{
    int ms = txt_mois_b.getMonth();
    int mois = ms+01;
    int annee = txt_annee_b.getYear();
    String an = Integer.toString(annee);
    String num_p = txt_num_perso_b.getSelectedItem().toString();
    con_db = new Connecter_bd();
    query = "select * from tab_contrat where num_perso='"+num_p+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
    String salaire = rs.getString("sal_base_contrat");
    txt_sal_bas_b.setText(salaire);
    }else{
    //txt_avances_reduc.setText("0");  
    }
}

private void select_num_nom_p() throws Exception{
    con_db = new Connecter_bd();
     //txt_num_perso_avcance.removeAllItems();
    String num_p = txt_num_perso_b.getSelectedItem().toString();
    query = "select * from tab_perso where num_perso='"+num_p+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
        String num_p1  = rs.getString("num_perso");
        String nom_p1 = rs.getString("nom_pre_perso");
        txt_num_b.setText(num_p1);
        txt_nom_b.setText(nom_p1);   
    }
}
//select personnelle (vita)
private void perso() throws SQLException, Exception{
    con_db = new Connecter_bd();
    String num_p = txt_num_perso_b.getSelectedItem().toString();
    query = "select * from tab_perso where num_perso='"+num_p+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
        String num  = rs.getString("num_perso");
        String nom = rs.getString("nom_pre_perso");
        print_num.setText(num);
        print_nom.setText(nom);   
    }
}
//select contrat (vita)
private void contrat() throws SQLException, Exception{
    con_db = new Connecter_bd();
    String num_p = txt_num_perso_b.getSelectedItem().toString();
    query = "select * from tab_contrat where num_perso='"+num_p+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
        String classification = rs.getString("clasifi_contrat");
        String salaire_base = rs.getString("sal_base_contrat");
        String indice = rs.getString("indice_contrat");
        print_clasifi.setText(classification);
        print_sal_base.setText(salaire_base);
        print_indice.setText(indice);
    }
}
//select avantage (vita)
private void avantage() throws Exception{
    con_db = new Connecter_bd();
    int ms = txt_mois_b.getMonth();
    int mois = ms+01;
    int annee = txt_annee_b.getYear();
    String an = Integer.toString(annee);
    String num_p = txt_num_perso_b.getSelectedItem().toString();
    query = "select * from tab_av where num_perso='"+num_p+"'and mois_av='"+mois+"' and annee_av='"+an+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
        String voca_piece = rs.getString("voca_piece_av");
        String idemnite = rs.getString("idemnite_av");
        String hrs_sup8 = rs.getString("hrs_sup8_av");
        String hrs_sup8_plus = rs.getString("hrs_sup_plus8_av");
        String totale_brute_av = rs.getString("totale_brute_av");
        String hrs_sup40 = rs.getString("val_heures_40");
        String anciente = rs.getString("anciente");
        print_voca_piece.setText(voca_piece);
        print_idemnite.setText(idemnite);
        print_hrs_sup8.setText(hrs_sup8);
        print_hrs_sup8_plus.setText(hrs_sup8_plus);
        print_heure40.setText(hrs_sup40);
        print_anciente.setText(anciente);
        print_totale_brute_av.setText(totale_brute_av);
          
    }else{
    num_p = txt_num_perso_b.getSelectedItem().toString();
    con_db = new Connecter_bd();
    query = "select * from tab_contrat where num_perso='"+num_p+"'";
   ResultSet rs1 = con_db.executeQuery(query);
    if(rs1.next()){
    String salaire = rs1.getString("sal_base_contrat");
    txt_totale_brute_b.setText(salaire);
    }
    }
}
//select reduction(vita)
private void reduction() throws Exception{
    con_db = new Connecter_bd();
    int ms = txt_mois_b.getMonth();
    int mois = ms+01;
    int annee = txt_annee_b.getYear();
    String an = Integer.toString(annee);
    String num_p = txt_num_perso_b.getSelectedItem().toString();
    query = "select * from tab_reduc where num_perso='"+num_p+"' and mois_reduc='"+mois+"' and annee_reduc='"+an+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
        String avance = rs.getString("avance_reduc");
        String cnaps = rs.getString("cnaps_reduc");
        String osief = rs.getString("osief_reduc");
        String irsa = rs.getString("irsa_reduc");
        String cess_sal_base= rs.getString("cess_sal_reduc");
        String nouri_log_reduc = rs.getString("nouri_log_reduc");
        String autres_reduc = rs.getString("autres_reduc");
        String totale_brute_reduc = rs.getString("totale_brute_reduc");
        print_avance.setText(avance);
        print_cnaps.setText(cnaps);
        print_irsa.setText(irsa);
        print_osief.setText(osief);
        print_cess_sal_base.setText(cess_sal_base);
        print_nouri_log_reduc.setText(nouri_log_reduc);
        print_autres_reduc.setText(autres_reduc);
        print_totale_brute_reduc.setText(totale_brute_reduc);
    }
}

private void select_totale_brute_avantage() throws Exception{
    int ms = txt_mois_b.getMonth();
    int mois = ms+01;
    int annee = txt_annee_b.getYear();
    String an = Integer.toString(annee);
    String num_p = txt_num_perso_b.getSelectedItem().toString();
    con_db = new Connecter_bd();
    query = "select * from tab_av where num_perso='"+num_p+"' and mois_av='"+mois+"' and annee_av='"+an+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
    String avantage = rs.getString("totale_brute_av");
    //txt_totale_brute_b.setText(avantage);
    double brutes = Double.parseDouble(avantage);
        txt_totale_brute_b.setText(avantage); 
     }
   else{
        con_db = new Connecter_bd();
        query = "select * from tab_contrat where num_perso='"+num_p+"'";   
        ResultSet rs2 = con_db.executeQuery(query);
        if(rs2.next()){
         String brute_sal_base = rs2.getString("sal_base_contrat");
         double brutera = Double.parseDouble(brute_sal_base);
         txt_totale_brute_b.setText(brute_sal_base);   
        } }
}
private void select_totale_brute_reduc() throws SQLException, Exception{
    int ms = txt_mois_b.getMonth();
    int mois = ms+01;
    int annee = txt_annee_b.getYear();
    String an = Integer.toString(annee);
    String num_p = txt_num_perso_b.getSelectedItem().toString();
    con_db = new Connecter_bd();
    query = "select * from tab_reduc where num_perso='"+num_p+"' and mois_reduc='"+mois+"' and annee_reduc='"+an+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
    String avantage = rs.getString("totale_brute_reduc");
    txt_totale_reduc_b.setText(avantage);
    }else{
    txt_totale_reduc_b.setText("0");  
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
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_mois_b = new com.toedter.calendar.JMonthChooser();
        jLabel7 = new javax.swing.JLabel();
        txt_annee_b = new com.toedter.calendar.JYearChooser();
        jLabel9 = new javax.swing.JLabel();
        txt_pdu_b = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txt_pau_b = new com.toedter.calendar.JDateChooser();
        txt_num_perso_b = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btn_apouve_b = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txt_nom_b = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_num_b = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txt_sal_bas_b = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        print_anciente = new javax.swing.JTextField();
        print_heure40 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_totale_brute_b = new javax.swing.JTextField();
        txt_totale_reduc_b = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btn_valid_b = new javax.swing.JButton();
        print_lettre = new javax.swing.JTextField();
        print_totale_brute_reduc = new javax.swing.JTextField();
        print_autres_reduc = new javax.swing.JTextField();
        print_nouri_log_reduc = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_add_bulletin = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txt_totale_net_b = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        print_num = new javax.swing.JTextField();
        print_nom = new javax.swing.JTextField();
        print_clasifi = new javax.swing.JTextField();
        print_indice = new javax.swing.JTextField();
        print_sal_base = new javax.swing.JTextField();
        print_irsa = new javax.swing.JTextField();
        print_voca_piece = new javax.swing.JTextField();
        print_hrs_sup8 = new javax.swing.JTextField();
        print_idemnite = new javax.swing.JTextField();
        print_totale_brute_av = new javax.swing.JTextField();
        print_hrs_sup8_plus = new javax.swing.JTextField();
        print_cnaps = new javax.swing.JTextField();
        print_avance = new javax.swing.JTextField();
        print_cess_sal_base = new javax.swing.JTextField();
        print_osief = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SLAIRE MENSUEL");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Cash_in_Hand_50px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Date de payement", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Mois du :");

        txt_mois_b.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Année :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Période du");

        txt_pdu_b.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("au ");

        txt_pau_b.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("N° personnel :");

        btn_apouve_b.setBackground(new java.awt.Color(0, 102, 153));
        btn_apouve_b.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_apouve_b.setForeground(new java.awt.Color(255, 255, 255));
        btn_apouve_b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Approval_25px.png"))); // NOI18N
        btn_apouve_b.setText("Approuver");
        btn_apouve_b.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_apouve_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_apouve_bActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_annee_b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_mois_b, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(txt_num_perso_b, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(50, 50, 50)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_pau_b, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(txt_pdu_b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_apouve_b, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addGap(25, 25, 25))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel4)
                        .addComponent(txt_num_perso_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_pdu_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_mois_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(txt_pau_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7)
                        .addComponent(txt_annee_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_apouve_b, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Date de payement", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        txt_nom_b.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nom_b.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nom_b.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nom et pénom du personnel :");

        txt_num_b.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_num_b.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_num_b.setEnabled(false);

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("N° :");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("Salaire de base :");

        txt_sal_bas_b.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_sal_bas_b.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_sal_bas_b.setEnabled(false);

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("Ar");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nom_b)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_num_b, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txt_sal_bas_b, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34)))
                        .addGap(0, 125, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(print_heure40, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(print_anciente, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nom_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_num_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txt_sal_bas_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(print_anciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print_heure40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Date de payement", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Enumération totale brute :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Totale de la réduction et taxes :");

        txt_totale_brute_b.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_totale_brute_b.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_totale_brute_b.setEnabled(false);

        txt_totale_reduc_b.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_totale_reduc_b.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_totale_reduc_b.setEnabled(false);

        jLabel11.setText("Ar");

        jLabel13.setText("Ar");

        btn_valid_b.setBackground(new java.awt.Color(0, 102, 153));
        btn_valid_b.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_valid_b.setForeground(new java.awt.Color(255, 255, 255));
        btn_valid_b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Ok_25px.png"))); // NOI18N
        btn_valid_b.setText("Valider");
        btn_valid_b.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_valid_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_valid_bActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(41, 41, 41)
                                .addComponent(txt_totale_brute_b, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_totale_reduc_b, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(24, 24, 24)
                                .addComponent(print_autres_reduc, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(24, 24, 24)
                                .addComponent(print_totale_brute_reduc, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(print_nouri_log_reduc, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_valid_b, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(print_lettre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_totale_brute_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(print_totale_brute_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_totale_reduc_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(print_autres_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addComponent(print_nouri_log_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(print_lettre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_valid_b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Close_Window_25px.png"))); // NOI18N
        jButton1.setText("Annuler/Retour");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Reset_25px.png"))); // NOI18N
        jButton2.setText("Effacer");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_add_bulletin.setBackground(new java.awt.Color(0, 102, 153));
        btn_add_bulletin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_add_bulletin.setForeground(new java.awt.Color(255, 255, 255));
        btn_add_bulletin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Checkmark_25px.png"))); // NOI18N
        btn_add_bulletin.setText("Enragistrer");
        btn_add_bulletin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_add_bulletin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_bulletinActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Date de payement", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        txt_totale_net_b.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_totale_net_b.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_totale_net_b.setEnabled(false);

        jLabel12.setText("Ar");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Montant de la paye du salaire mensuel :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(txt_totale_net_b, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(print_num, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(print_nom)
                .addGap(18, 18, 18)
                .addComponent(print_clasifi)
                .addGap(26, 26, 26)
                .addComponent(print_indice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(print_sal_base)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_totale_net_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(print_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print_clasifi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print_indice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print_sal_base, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(print_avance, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(print_voca_piece))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(print_cnaps, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(print_idemnite))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(print_hrs_sup8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(print_osief, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(print_hrs_sup8_plus, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(print_irsa))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(print_totale_brute_av, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(print_cess_sal_base))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_add_bulletin, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(print_voca_piece, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(print_idemnite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(print_hrs_sup8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(print_hrs_sup8_plus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(print_totale_brute_av, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(print_cnaps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(print_avance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(print_osief, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(print_cess_sal_base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(print_irsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_add_bulletin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    try {
        select_num_perso();
  print_autres_reduc.setText("0");
  print_avance.setText("0");
  print_cess_sal_base.setText("0");
  print_clasifi.setText("0");
  print_cnaps.setText("0");
  print_hrs_sup8.setText("0");
  print_hrs_sup8_plus.setText("0");
  print_idemnite.setText("0");
  print_indice.setText("0");
  print_irsa.setText("0");
  //print_lettre.setText("0.00");
  print_nom.setText("0");
  print_nouri_log_reduc.setText("0");
  print_num.setText("0");
  print_osief.setText("0");
  print_sal_base.setText("0");
  print_totale_brute_av.setText("0");
  print_totale_brute_reduc.setText("0");
  print_voca_piece.setText("0");
  print_anciente.setText("0");
  print_heure40.setText("0");
    } catch (Exception ex) {
        Logger.getLogger(bulletin_add.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_formWindowOpened

    private void btn_apouve_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_apouve_bActionPerformed
     if(((JTextField)txt_pdu_b.getDateEditor().getUiComponent()).getText().equals("")==false || 
            ((JTextField)txt_pau_b.getDateEditor().getUiComponent()).getText().equals("")==false){
          //pour l'année
DateFormat dateFormat_an = new SimpleDateFormat("yyyy");
Date pdu = txt_pdu_b.getDate();
String pdu2 = dateFormat_an.format(pdu);
Date pau = txt_pau_b.getDate();
String pau2 = dateFormat_an.format(pau);
    int pan1_1 = Integer.parseInt(pdu2);
    int pan2_2 = Integer.parseInt(pau2);
//pour le mois
DateFormat dateFormat_ms = new SimpleDateFormat("MM");
Date pdu_ms = txt_pdu_b.getDate();
String pdu1_ms = dateFormat_ms.format(pdu_ms);
Date pau_ms = txt_pau_b.getDate();
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
int an = txt_annee_b.getYear();
int mw = txt_mois_b.getMonth();
int mws= mw+01;
if((mw_now_1==pdu_ms1) && (an_now_1==pan1_1) && (an==an_now_1) && (mws==mw_now_1)){
    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        String dt1 = dt.format(txt_pdu_b.getDate());
        String dt2 = dt.format(txt_pau_b.getDate());
         try {
              Date date1 = dt.parse(dt1);
              Date date2 = dt.parse(dt2);
        if(date1.before(date2)){
    int ms = txt_mois_b.getMonth();
    int mois = ms+01;
    int annee = txt_annee_b.getYear();
   // String anv = Integer.toString(annee);
    String num_p = txt_num_perso_b.getSelectedItem().toString();
    con_db = new Connecter_bd();
    query = "select * from tab_paie where num_perso='"+num_p+"' and mois_bulletin='"+mois+"' and annee_bulletin='"+an+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
  JOptionPane.showMessageDialog(null,"Désolé! Vous ne pouvez pas rétirer un salaire mensuel deux (02) fois ,SVP","Message",JOptionPane.WARNING_MESSAGE);   
     }else{
           select_sal_base(); 
           select_num_nom_p();
           select_totale_brute_avantage();
           select_totale_brute_reduc(); 
           perso();
           avantage();
           reduction();
           contrat();
    }
    }else{
JOptionPane.showMessageDialog(null,"Désolé! la date de débute du période doit être inférieur à la date de fin du période, SVP","Message",JOptionPane.WARNING_MESSAGE);                     
        }
         } catch (ParseException ex) {
             Logger.getLogger(bulletin_add.class.getName()).log(Level.SEVERE, null, ex);
         } catch (Exception ex) {
             Logger.getLogger(bulletin_add.class.getName()).log(Level.SEVERE, null, ex);
         }
               
}else{
    JOptionPane.showMessageDialog(null,"Désolé! le mois et l'année doivent être actuelle","Message",JOptionPane.WARNING_MESSAGE); 
 }

}else{
    JOptionPane.showMessageDialog(null,"Désolé! le mois et l'année de ce deux dates doivent être éguals","Message",JOptionPane.WARNING_MESSAGE); 
   }
     }else{
    JOptionPane.showMessageDialog(null,"Veillez remplir les deux champs dates","Message",JOptionPane.WARNING_MESSAGE);
     }
    }//GEN-LAST:event_btn_apouve_bActionPerformed

    private void btn_valid_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_valid_bActionPerformed
    try {
        con_db = new Connecter_bd();
        String num_p = txt_num_perso_b.getSelectedItem().toString();
        int an = txt_annee_b.getYear();
        int mw = txt_mois_b.getMonth();
        int mws= mw+01;
        query ="select * from tab_reduc where num_perso='"+num_p+"'and annee_reduc='"+an+"' and mois_reduc='"+mws+"'";
        ResultSet rs = con_db.executeQuery(query);
        if(rs.next()){
        String brute = txt_totale_brute_b.getText();
        String reduc = txt_totale_reduc_b.getText();
        double brute_d = Double.parseDouble(brute);
        double reduc_d = Double.parseDouble(reduc);
        double somme = (brute_d-reduc_d);
//    String sommeString = Double.toString(somme);
String iras_final = DeuxChiffresApresVirgule.ChiffresApresVirgule(somme);
txt_totale_net_b.setText(iras_final);
        }else{
       JOptionPane.showMessageDialog(null,"Désolé! Il faut calculer la déduction pour effectuer cette opération","Message",JOptionPane.WARNING_MESSAGE);     
        }
        
        
//    print_add();
    } catch (SQLException ex) {
        Logger.getLogger(bulletin_add.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(bulletin_add.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btn_valid_bActionPerformed
private void ajout_bulletin() throws Exception{
    
String num_p = txt_num_perso_b.getSelectedItem().toString();
Date actuelle = new Date();
DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
String periode_du = dt.format(txt_pdu_b.getDate());
String periode_au = dt.format(txt_pau_b.getDate());
String date_now = dateFormat.format(actuelle);
int annee = txt_annee_b.getYear();
int mw = txt_mois_b.getMonth();
int mws = mw+01;
int i=0;
/*String mois = Integer.toString(mws);

String annee_av = Integer.toString(annee);*/
String t_brute = txt_totale_brute_b.getText();
double totale_b = Double.parseDouble(t_brute);
String t_reduc = txt_totale_reduc_b.getText();
double totale_r = Double.parseDouble(t_reduc);
String t_net_paye = txt_totale_net_b.getText();
double totale_net_paye = Double.parseDouble(t_net_paye);
String lettre = print_lettre.getText();
ConvertirChiffreEnLettre c = new ConvertirChiffreEnLettre();
        String n[] = (totale_net_paye+"").split("\\.");
        long entier = Long.parseLong(n[0]);
        long decimal=Long.parseLong(n[1]);
        String lettret = (decimal > 0) ? c.convert(entier) + " virgule " + c.convert(decimal) : c.convert(entier);
        //System.out.println(num + " : " + lettre + " Ariary");
        print_lettre.setText(lettret+ " " + lettre + " Ariary");
String nom =txt_nom_b.getText();
double sal_base = Double.parseDouble(txt_sal_bas_b.getText());
double simple_av1 = totale_b - sal_base;
String simple = DeuxChiffresApresVirgule.ChiffresApresVirgule(simple_av1);
double simple_av =Double.parseDouble(simple);
    classe_bulletin clb = new classe_bulletin(num_p,nom, date_now,mws, annee,periode_du,periode_au,totale_r, totale_b, totale_net_paye,sal_base,simple_av );
    requet_bulletin rqtb = new requet_bulletin();
    rqtb.ajouter(clb);
//hoan'ny impression
Date actuellep = new Date();
DateFormat dateFormatp = new SimpleDateFormat("dd/MM/yyyy");
String date_payerp = dateFormat.format(actuellep);
 String nump = print_num.getText();
 String nomp = print_nom.getText();
 double autresp = Double.parseDouble(print_autres_reduc.getText());
 double avancep =Double.parseDouble(print_avance.getText());
 double cess_salp = Double.parseDouble(print_cess_sal_base.getText());
 String clasifip = print_clasifi.getText();
 double cnapsp = Double.parseDouble(print_cnaps.getText());
 double sup8p = Double.parseDouble(print_hrs_sup8.getText());
 double sup8_plusp= Double.parseDouble(print_hrs_sup8_plus.getText());
 double idemnitep =Double.parseDouble(print_idemnite.getText());
 int indicep = Integer.parseInt(print_indice.getText());
 double irsap = Double.parseDouble(print_irsa.getText());
 double nourip = Double.parseDouble(print_nouri_log_reduc.getText());
 double osiefp = Double.parseDouble(print_osief.getText());
 double sal_basep = Double.parseDouble(print_sal_base.getText());
 double total_brute_avp = Double.parseDouble(txt_totale_brute_b.getText());
 double total_brute_reducp =Double.parseDouble(print_totale_brute_reduc.getText());
 double vocap = Double.parseDouble(print_voca_piece.getText());
 String lettrep = print_lettre.getText();
 double totale_netp = Double.parseDouble(txt_totale_net_b.getText());
String pdup = dt.format(txt_pdu_b.getDate());
String paup = dt.format(txt_pau_b.getDate());
double heure40 =Double.parseDouble(print_heure40.getText());
double anciente = Double.parseDouble(print_anciente.getText());
 //classe_print_bulletin clpr = new classe_print_bulletin(nomp, nump, clasifip, date_payerp, sal_basep, indicep,vocapp, sup8p, sup8_plusp, idemnitep, avancep, nourip, cess_salp, cnapsp, osiefp, irsap, autresp, totale_brute_avp,total_brute_reducp,totale_netp,lettrep);
    classe_print_bulletin clpr = new classe_print_bulletin(nomp,nump,clasifip ,date_payerp,sal_basep ,indicep ,vocap , sup8p,sup8_plusp ,idemnitep,avancep ,nourip,cess_salp ,cnapsp ,osiefp ,irsap,autresp , total_brute_avp,total_brute_reducp,totale_netp,lettrep,pdup,paup,heure40,anciente);
    requet_print_bulletin rqtpb = new requet_print_bulletin();
    rqtpb.ajouter(clpr);
    JOptionPane.showMessageDialog(null,"Les informations ont été  bien enregistrées","Message",JOptionPane.PLAIN_MESSAGE); 
    //classe_bulletin_cp cl = new classe_bulletin_cp(i,num_p,date_now,mws, annee,periode_du,periode_au,totale_r, totale_b, totale_net_paye);
    //rqtb.ajoutercopie(cl);
  i++;
}
//DeuxChiffresApresVirgule.ChiffresApresVirgule(finale);
//private void print_add(){
//    Date actuelle = new Date();
//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//String date_payer = dateFormat.format(actuelle);
// String num = print_num.getText();
// String nom = print_nom.getText();
// String autres = print_autres_reduc.getText();
// double avance =Double.parseDouble(print_avance.getText());
// double cess_sal = Double.parseDouble(print_cess_sal_base.getText());
// String clasifi = print_clasifi.getText();
// double cnaps = Double.parseDouble(print_cnaps.getText());
// double sup8 = Double.parseDouble(print_hrs_sup8.getText());
// double sup8_plus= Double.parseDouble(print_hrs_sup8_plus.getText());
// double idemnite =Double.parseDouble(print_idemnite.getText());
// double indice = Double.parseDouble(print_indice.getText());
// double irsa = Double.parseDouble(print_irsa.getText());
// double nouri = Double.parseDouble(print_nouri_log_reduc.getText());
// double osief = Double.parseDouble(print_osief.getText());
// double sal_base = Double.parseDouble(print_sal_base.getText());
// double totale_brute_av = Double.parseDouble(print_totale_brute_av.getText());
// double total_brute_reduc =Double.parseDouble(print_totale_brute_reduc.getText());
// double voca = Double.parseDouble(print_voca_piece.getText());
// String lettre = print_lettre.getText();
//double totale_net = Double.parseDouble(txt_totale_net_b.getText());      
//}
    private void btn_add_bulletinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_bulletinActionPerformed
    try {  
        int infos = JOptionPane.showConfirmDialog(this,"Voullez-vous vraiment effectué cette opération?","Message",JOptionPane.YES_NO_OPTION);
            if(infos==JOptionPane.YES_OPTION){  
                print_lettre.setText("");
                ajout_bulletin(); 
             }else{
        
            }
       //  print_add();
    } catch (Exception ex) {
        Logger.getLogger(bulletin_add.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btn_add_bulletinActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(bulletin_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bulletin_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bulletin_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bulletin_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bulletin_add().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add_bulletin;
    private javax.swing.JButton btn_apouve_b;
    private javax.swing.JButton btn_valid_b;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField print_anciente;
    private javax.swing.JTextField print_autres_reduc;
    private javax.swing.JTextField print_avance;
    private javax.swing.JTextField print_cess_sal_base;
    private javax.swing.JTextField print_clasifi;
    private javax.swing.JTextField print_cnaps;
    private javax.swing.JTextField print_heure40;
    private javax.swing.JTextField print_hrs_sup8;
    private javax.swing.JTextField print_hrs_sup8_plus;
    private javax.swing.JTextField print_idemnite;
    private javax.swing.JTextField print_indice;
    private javax.swing.JTextField print_irsa;
    private javax.swing.JTextField print_lettre;
    private javax.swing.JTextField print_nom;
    private javax.swing.JTextField print_nouri_log_reduc;
    private javax.swing.JTextField print_num;
    private javax.swing.JTextField print_osief;
    private javax.swing.JTextField print_sal_base;
    private javax.swing.JTextField print_totale_brute_av;
    private javax.swing.JTextField print_totale_brute_reduc;
    private javax.swing.JTextField print_voca_piece;
    private com.toedter.calendar.JYearChooser txt_annee_b;
    private com.toedter.calendar.JMonthChooser txt_mois_b;
    private javax.swing.JTextField txt_nom_b;
    private javax.swing.JTextField txt_num_b;
    private javax.swing.JComboBox<String> txt_num_perso_b;
    private com.toedter.calendar.JDateChooser txt_pau_b;
    private com.toedter.calendar.JDateChooser txt_pdu_b;
    private javax.swing.JTextField txt_sal_bas_b;
    private javax.swing.JTextField txt_totale_brute_b;
    private javax.swing.JTextField txt_totale_net_b;
    private javax.swing.JTextField txt_totale_reduc_b;
    // End of variables declaration//GEN-END:variables
}
