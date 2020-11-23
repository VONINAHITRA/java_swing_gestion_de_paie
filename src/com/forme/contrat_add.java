/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forme;

import com.classe.classe_contrat;
import com.requete.Connecter_bd;
import com.requete.connections;
import com.requete.requet_contrat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author VONINAHITRA
 */
public class contrat_add extends javax.swing.JFrame {
private String query;
private String queryv;
private Connecter_bd con_db;
connections maCon = new connections();
ResultSet rs;
PreparedStatement ps;
Statement st;
Connection conc;
String num_perso;
String type_contrat;

    /**
     * Creates new form contrat_add
     */
    public contrat_add() {
        initComponents();
        this.setLocationRelativeTo(null);   
        this.setResizable(false);
    }

private void verifier_add_contrat() throws Exception{
  String num_p = txt_num_perso.getText().toString();
  //SimpleDateFormat dts = new SimpleDateFormat("dd-MM-yyyy");
   //String dtso = dts.format(txt_dt_sor_contrat.getDate());
  con_db = new Connecter_bd();
  query = "select * from tab_contrat where num_perso='"+num_p+"'";
  ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
      JOptionPane.showMessageDialog(null,"Cet personnel est dejà effectué à un contrat","Message",JOptionPane.WARNING_MESSAGE);  
    }else{
        if( acceptindice()==true){
     SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
             if(type_contrat=="CDI"){
                 String indeter="?";    
       // String num_per = txt_num_perso_contrat.getSelectedItem().toString();
        SimpleDateFormat dtcddi = new SimpleDateFormat("dd/MM/yyyy");
        Date actuelle = new Date();
        String date_nowr = dtcddi.format(actuelle);
        String matricule = txt_num_perso.getText();
        String dt_entr = dt.format(txt_dt_entr_contrat.getDate());
        String dt_embr =dt.format(txt_date_emb_perso.getDate());
        String clasifi = txt_clasoifi_contrat.getSelectedItem().toString();
        String nom_prenomr = txt_nom_pre_perso.getText();
        String indicer = txt_indice_contrat.getText();
        double sal_baser = Double.parseDouble(txt_sal_base_contrat.getText());
        String service = txt_service.getSelectedItem().toString();
   classe_contrat clc = new classe_contrat(matricule,nom_prenomr,service,date_nowr,dt_embr,dt_entr, indeter,clasifi,indicer,sal_baser,type_contrat);       
   requet_contrat rqtc = new requet_contrat();
   rqtc.ajouter(clc);   
  JOptionPane.showMessageDialog(null,"Les informations ont été  bien enregistrées","Message",JOptionPane.PLAIN_MESSAGE); 
             }else{
        String dt1 = dt.format(txt_dt_entr_contrat.getDate());
        String dt2 = dt.format(txt_dt_sor_contrat.getDate());
                Date date1 = dt.parse(dt1);
                Date date2 = dt.parse(dt2);
        if(date1.before(date2)){      
        SimpleDateFormat dtcddi = new SimpleDateFormat("dd/MM/yyyy");
        Date actuelle = new Date();
        String dt_sort = dt.format(txt_dt_sor_contrat.getDate());
        String date_nowr = dtcddi.format(actuelle);
        String matricule = txt_num_perso.getText();
        String dt_entr = dt.format(txt_dt_entr_contrat.getDate());
        String dt_embr =dt.format(txt_date_emb_perso.getDate());
        String clasifi = txt_clasoifi_contrat.getSelectedItem().toString();
        String nom_prenomr = txt_nom_pre_perso.getText();
        String indicer = txt_indice_contrat.getText();
        double sal_baser = Double.parseDouble(txt_sal_base_contrat.getText());
        String service = txt_service.getSelectedItem().toString();   
 classe_contrat clc = new classe_contrat(matricule, nom_prenomr, service, date_nowr, dt_embr,dt_entr, dt_sort,clasifi,indicer,sal_baser,type_contrat);       
 requet_contrat rqtc = new requet_contrat();
 rqtc.ajouter(clc);     
  JOptionPane.showMessageDialog(null,"Les informations ont été  bien enregistrées","Message",JOptionPane.PLAIN_MESSAGE); 
     }else{
      JOptionPane.showMessageDialog(null,"La date de d'entrée doit être inférieur à la date de sortie","Message",JOptionPane.WARNING_MESSAGE);          
          }
     }
          /*
        
        else{
      JOptionPane.showMessageDialog(null,"Veillez sasir le format correct SVP","Message",JOptionPane.WARNING_MESSAGE);
    }
        */
      /*  String num_per = txt_num_perso_contrat.getSelectedItem().toString();
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        String dtentr = dt.format(txt_dt_entr_contrat.getDate());
        String dtsort = dt.format(txt_dt_sor_contrat.getDate());
        String clasifi = txt_clasoifi_contrat.getSelectedItem().toString();
        String nb_hr_moi = txt_nbr_hrs_mois_contrat.getText();
        String nb_hr_jrs = txt_nbr_hrs_jrs_contrat.getText();
        int nbh_mois = Integer.parseInt(nb_hr_moi);
        int nb_h_jrs = Integer.parseInt(nb_hr_jrs);
        String indice = txt_indice_contrat.getText();
        //int indc = Integer.parseInt(indice);
        String sal_b = txt_sal_base_contrat.getText();
        double sal_base = Double.parseDouble(sal_b);
        String lieu = txt_lieu_contrat.getSelectedItem().toString();
        String type = txt_typ_paie_contrat.getSelectedItem().toString();
        classe_contrat clc = new classe_contrat(num_per, dtentr, dtsort, clasifi, nbh_mois, nb_h_jrs, indice, sal_base, lieu,type,type_contrat);
        requet_contrat rqtc = new requet_contrat();
        rqtc.ajouter(clc);*/
      
     // JOptionPane.showMessageDialog(null,"Le contrat à été bien enregistré","Message",JOptionPane.PLAIN_MESSAGE); 
    }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_dt_entr_contrat = new com.toedter.calendar.JDateChooser();
        txt_dt_sor_contrat = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_sal_base_contrat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_clasoifi_contrat = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txt_indice_contrat = new javax.swing.JTextField();
        txt_nom_pre_perso = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_cdi_contrat = new javax.swing.JRadioButton();
        txt_cdd_contrat = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        txt_num_perso = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_date_emb_perso = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txt_service = new javax.swing.JComboBox<>();
        btn_add_contrat = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));
        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NOUVEAU CONTRAT");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_Hand_With_Pen_40px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Nouveau contrat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("N° Matricule :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Date d'entrée :");

        txt_dt_entr_contrat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_dt_sor_contrat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Date de sortie :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nom et prénom:");

        txt_sal_base_contrat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sal_base_contratActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Classification :");

        txt_clasoifi_contrat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_clasoifi_contrat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M1-A1", "M2-1B", "OS1-2A", "OS2-2B", "OS3-3A", "P1A-3B", "OP1B-4A", "OP2A-4B", "OP2A-4B", "OP2B-5A", "OP3-5B", "HC" }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Indice :");

        txt_nom_pre_perso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nom_pre_persoActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Salaire de base :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Type de contrat :");

        txt_cdi_contrat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(txt_cdi_contrat);
        txt_cdi_contrat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_cdi_contrat.setText("CDI");
        txt_cdi_contrat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cdi_contratActionPerformed(evt);
            }
        });

        txt_cdd_contrat.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(txt_cdd_contrat);
        txt_cdd_contrat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_cdd_contrat.setText("CDD");
        txt_cdd_contrat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cdd_contratActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Ar");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Date d'embauche:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Service :");

        txt_service.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_service.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Directeur générale", "Directeur technique", "Chef de personnel", "Comptable", "Caissier", "Magasin comptable", "Magasin atelier", "Responsable achat", "Coursier", "Coursier magasin", "Mécanique", "Mécanique éléctricien", "Peinture", "Agent entretient", "Femme de menage", "Gardien de nuit", "Gardier du jour" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel15)
                                .addComponent(jLabel5)
                                .addComponent(jLabel3))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(txt_cdd_contrat, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(34, 34, 34)
                                            .addComponent(txt_cdi_contrat, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txt_nom_pre_perso, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_num_perso, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_clasoifi_contrat, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(txt_date_emb_perso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(91, 91, 91))))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(42, 42, 42)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(txt_dt_sor_contrat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(91, 91, 91))
                                .addComponent(txt_dt_entr_contrat, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel8))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_sal_base_contrat)
                    .addComponent(txt_indice_contrat)
                    .addComponent(txt_service, 0, 156, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addComponent(jLabel14)
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_num_perso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txt_service, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nom_pre_perso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_cdd_contrat)
                            .addComponent(txt_cdi_contrat)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_indice_contrat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txt_sal_base_contrat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_clasoifi_contrat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_date_emb_perso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_dt_entr_contrat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_dt_sor_contrat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        btn_add_contrat.setBackground(new java.awt.Color(0, 102, 153));
        btn_add_contrat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_add_contrat.setForeground(new java.awt.Color(255, 255, 255));
        btn_add_contrat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Checkmark_25px.png"))); // NOI18N
        btn_add_contrat.setText("Enregistrer");
        btn_add_contrat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_add_contrat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_contratActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Close_Window_25px.png"))); // NOI18N
        jButton3.setText("Annuler/Fermer");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Reset_25px.png"))); // NOI18N
        jButton1.setText("Effacer");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_add_contrat, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_add_contrat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private boolean  acceptindice(){
        boolean num = true;
        num=  txt_indice_contrat.getText().matches("^[0-9]*");
        return num;
    }
     private boolean  acceptjrs(){
        boolean num = true;
        num=  txt_nom_pre_perso.getText().matches("^[0-9]*");
        return num;
    }
     
    private void btn_add_contratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_contratActionPerformed
    try {
        //JOptionPane.showMessageDialog(null,"TONGA","Message",JOptionPane.WARNING_MESSAGE);
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        String dt1 =dt.format(txt_date_emb_perso.getDate());
        String dt2 = dt.format(txt_dt_entr_contrat.getDate());
        Date date1 = dt.parse(dt1);
        Date date2 = dt.parse(dt2);
        if(txt_num_perso.getText().equals("")==true||txt_nom_pre_perso.getText().equals("")==true || txt_indice_contrat.getText().equals("")==true||
                txt_sal_base_contrat.getText().equals("")==true ){
            JOptionPane.showMessageDialog(null,"Désolé ! Tous les champs sont obligatoires sauf la date de sortie\n Veillez remplire, SVP","Message",JOptionPane.WARNING_MESSAGE);
        }else{
            int infos = JOptionPane.showConfirmDialog(this,"Voullez-vous vraiment effectuer cette opération ?","Message",JOptionPane.YES_NO_OPTION);
            if(infos==JOptionPane.YES_OPTION){
                try {
                    if(date2.before(date1)){
                       JOptionPane.showMessageDialog(null,"Désolé ! La date d'embauche doit être supérieur à la date d'entrée, SVP","Message",JOptionPane.WARNING_MESSAGE); 
                    }else{
                        verifier_add_contrat();
                    }
                    
                } catch (Exception ex) {
                    Logger.getLogger(contrat_add.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                
            }  
        }
    } catch (ParseException ex) {
        Logger.getLogger(contrat_add.class.getName()).log(Level.SEVERE, null, ex);
    }
               
    }//GEN-LAST:event_btn_add_contratActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    
    }//GEN-LAST:event_formWindowOpened

    private void txt_nom_pre_persoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nom_pre_persoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nom_pre_persoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_cdd_contratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cdd_contratActionPerformed
       type_contrat = "CDD";
        txt_dt_sor_contrat.setEnabled(true);
    }//GEN-LAST:event_txt_cdd_contratActionPerformed

    private void txt_cdi_contratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cdi_contratActionPerformed
     type_contrat ="CDI";
     txt_dt_sor_contrat.setEnabled(false);
    }//GEN-LAST:event_txt_cdi_contratActionPerformed

    private void txt_sal_base_contratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sal_base_contratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sal_base_contratActionPerformed

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
            java.util.logging.Logger.getLogger(contrat_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(contrat_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(contrat_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(contrat_add.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new contrat_add().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add_contrat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton txt_cdd_contrat;
    private javax.swing.JRadioButton txt_cdi_contrat;
    private javax.swing.JComboBox<String> txt_clasoifi_contrat;
    private com.toedter.calendar.JDateChooser txt_date_emb_perso;
    private com.toedter.calendar.JDateChooser txt_dt_entr_contrat;
    private com.toedter.calendar.JDateChooser txt_dt_sor_contrat;
    private javax.swing.JTextField txt_indice_contrat;
    private javax.swing.JTextField txt_nom_pre_perso;
    private javax.swing.JTextField txt_num_perso;
    private javax.swing.JTextField txt_sal_base_contrat;
    private javax.swing.JComboBox<String> txt_service;
    // End of variables declaration//GEN-END:variables
}
