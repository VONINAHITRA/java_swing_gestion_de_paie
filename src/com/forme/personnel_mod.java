/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forme;

import com.classe.classe_personnel;
import com.requete.Connecter_bd;
import com.requete.requet_personnel;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author VONINAHITRA
 */
public class personnel_mod extends javax.swing.JFrame {
String sexe;
private String query;
private Connecter_bd con_db;
    /**
     * Creates new form personnel_mod
     */
    public personnel_mod() {
        initComponents();
        this.setLocationRelativeTo(null);
         this.setResizable(false);
    }

    private void verifi_mod_p(String id) throws Exception{
    /*con_db = new Connecter_bd();
    query = "select * from tab_perso where num_perso='"+txt_num_mod_p.getText()+"'";
    ResultSet rs_perso = con_db.executeQuery(query);
    if(rs_perso.next()){
    JOptionPane.showMessageDialog(null,"Désole cet personnel est déjà existé","Message",JOptionPane.ERROR_MESSAGE);
    txt_num_mod_p.setForeground(Color.red);    
     }else{
        */
    if(acceptel()==true && acceptnbr()==true){   
    String ref_mod_p = txt_ref_mod_p.getText();
    String num = txt_num_mod_p.getText();
    String nom = txt_nom_pre_mod_p.getText();
    //sexe
    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
    String dtemb = dt.format(txt_dt_emb_mod_p.getDate());
    String nbr = txt_nbr_enf_mod_p.getText();
    String situ = txt_situ_mod_p.getSelectedItem().toString();
    int nbrs = Integer.parseInt(nbr);
    String adrs = txt_adrs_mod_p.getText();
    String tel = txt_tel_mod_p.getText(); 
    classe_personnel clp = new classe_personnel(num, nom, sexe, dtemb, situ, nbrs,adrs,tel);
    requet_personnel rqtp = new requet_personnel();
    rqtp.modifier(ref_mod_p,clp);
    JOptionPane.showMessageDialog(null,"Les ifnormations ont été  bien enregistrés","Message",JOptionPane.PLAIN_MESSAGE);
    }else{
    JOptionPane.showMessageDialog(null,"Veillez sasir le format correct SVP","Message",JOptionPane.WARNING_MESSAGE);
    }
    }

    private boolean acceptel(){
        boolean tel = true;
       tel=  txt_tel_mod_p.getText().matches("(\\+[0-9][0-9][0-9]( [0-9][0-9])+)|([0-9]+)");
        return tel;
    }
    private boolean  acceptnum(){
        boolean num = true;
       num=  txt_num_mod_p.getText().matches("[0-9]*");
        return num;
    }
    private boolean  acceptnbr(){
         boolean nbr = true;
       nbr=  txt_nbr_enf_mod_p.getText().matches("^[0-9]*");
        return nbr;
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
        txt_nom_pre_mod_p = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_num_mod_p = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_homme = new javax.swing.JRadioButton();
        txt_femme = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txt_dt_emb_mod_p = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txt_nbr_enf_mod_p = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_situ_mod_p = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_adrs_mod_p = new javax.swing.JTextField();
        txt_tel_mod_p = new javax.swing.JTextField();
        txt_ref_mod_p = new javax.swing.JTextField();
        btn_fermer_mod_p = new javax.swing.JButton();
        btn_valider_mod_p = new javax.swing.JButton();
        btn_valider_mod_p1 = new javax.swing.JButton();

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
        jLabel1.setText("MODIFICATION DU PERSONNEL");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_User_40px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(96, 96, 96))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Modification du personnel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        txt_nom_pre_mod_p.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nom_pre_mod_p.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("N° Matricule:");

        txt_num_mod_p.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_num_mod_p.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_num_mod_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_num_mod_pActionPerformed(evt);
            }
        });
        txt_num_mod_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_num_mod_pKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Nom et prénom :");

        txt_homme.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(txt_homme);
        txt_homme.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_homme.setText("Homme");
        txt_homme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hommeActionPerformed(evt);
            }
        });

        txt_femme.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(txt_femme);
        txt_femme.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txt_femme.setText("Femme");
        txt_femme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_femmeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Sexe :");

        txt_dt_emb_mod_p.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Date de naissance :");

        txt_nbr_enf_mod_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nbr_enf_mod_pKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Nombre d'enfant(s) :");

        txt_situ_mod_p.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_situ_mod_p.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Célibataire", "Marié(e)", "Divorcé(e)" }));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Etat civile :");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Adresse :");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Tel :");

        txt_tel_mod_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tel_mod_pActionPerformed(evt);
            }
        });
        txt_tel_mod_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tel_mod_pKeyReleased(evt);
            }
        });

        txt_ref_mod_p.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_num_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(txt_ref_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_nom_pre_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tel_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_adrs_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txt_homme, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(txt_femme, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_dt_emb_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_situ_mod_p, javax.swing.GroupLayout.Alignment.LEADING, 0, 159, Short.MAX_VALUE)
                                .addComponent(txt_nbr_enf_mod_p, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_num_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ref_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_nom_pre_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_femme)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_homme)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_dt_emb_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_situ_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nbr_enf_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_adrs_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_tel_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        btn_fermer_mod_p.setBackground(new java.awt.Color(0, 102, 153));
        btn_fermer_mod_p.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_fermer_mod_p.setForeground(new java.awt.Color(255, 255, 255));
        btn_fermer_mod_p.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Close_Window_25px.png"))); // NOI18N
        btn_fermer_mod_p.setText("Annuler/Fermer");
        btn_fermer_mod_p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_fermer_mod_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fermer_mod_pActionPerformed(evt);
            }
        });

        btn_valider_mod_p.setBackground(new java.awt.Color(0, 102, 153));
        btn_valider_mod_p.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_valider_mod_p.setForeground(new java.awt.Color(255, 255, 255));
        btn_valider_mod_p.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Checkmark_25px.png"))); // NOI18N
        btn_valider_mod_p.setText("Enregistrer");
        btn_valider_mod_p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_valider_mod_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_valider_mod_pActionPerformed(evt);
            }
        });

        btn_valider_mod_p1.setBackground(new java.awt.Color(0, 102, 153));
        btn_valider_mod_p1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_valider_mod_p1.setForeground(new java.awt.Color(255, 255, 255));
        btn_valider_mod_p1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Reset_25px.png"))); // NOI18N
        btn_valider_mod_p1.setText("Effacer");
        btn_valider_mod_p1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_valider_mod_p1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_valider_mod_p1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btn_valider_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_valider_mod_p1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_fermer_mod_p)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_fermer_mod_p, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btn_valider_mod_p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_valider_mod_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
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

    private void txt_num_mod_pKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_num_mod_pKeyReleased
        txt_num_mod_p.setForeground(Color.black);
    }//GEN-LAST:event_txt_num_mod_pKeyReleased

    private void txt_hommeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hommeActionPerformed
        sexe = "H";
    }//GEN-LAST:event_txt_hommeActionPerformed

    private void txt_femmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_femmeActionPerformed
        sexe = "F";
    }//GEN-LAST:event_txt_femmeActionPerformed

    private void btn_fermer_mod_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fermer_mod_pActionPerformed
        this.dispose();
        perso_entrep_contrat_avance_forme pf = new perso_entrep_contrat_avance_forme();
        pf.getState();

    }//GEN-LAST:event_btn_fermer_mod_pActionPerformed

    private void btn_valider_mod_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_valider_mod_pActionPerformed

        if(txt_num_mod_p.getText().equals("")==true || txt_nom_pre_mod_p.getText().equals("")==true || sexe==null
            ||txt_adrs_mod_p.getText().equals("")==true
            || txt_tel_mod_p.getText().equals("")==true|| txt_nbr_enf_mod_p.getText().equals("")==true ){
            JOptionPane.showMessageDialog(null,"Tous les champs sont obligatoires\n Veillez remplir, SVP","Message",JOptionPane.ERROR_MESSAGE);
        }else {
            int infos = JOptionPane.showConfirmDialog(this,"Voullez-vous vraiment modifier ce personnel ?","Message",JOptionPane.YES_NO_OPTION);
            if(infos==JOptionPane.YES_OPTION){
                try {
                 String id = txt_num_mod_p.getText();
                 verifi_mod_p(id);
                } catch (Exception ex) {
                   // Logger.getLogger(personnel_mod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }//GEN-LAST:event_btn_valider_mod_pActionPerformed

    private void txt_tel_mod_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tel_mod_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tel_mod_pActionPerformed

    private void txt_nbr_enf_mod_pKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nbr_enf_mod_pKeyReleased
       txt_nbr_enf_mod_p.setForeground(Color.black);
    }//GEN-LAST:event_txt_nbr_enf_mod_pKeyReleased

    private void txt_tel_mod_pKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tel_mod_pKeyReleased
        txt_tel_mod_p.setForeground(Color.black);
    }//GEN-LAST:event_txt_tel_mod_pKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txt_ref_mod_p.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void btn_valider_mod_p1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_valider_mod_p1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_valider_mod_p1ActionPerformed

    private void txt_num_mod_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_num_mod_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_num_mod_pActionPerformed

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
            java.util.logging.Logger.getLogger(personnel_mod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(personnel_mod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(personnel_mod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(personnel_mod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new personnel_mod().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_fermer_mod_p;
    private javax.swing.JButton btn_valider_mod_p;
    private javax.swing.JButton btn_valider_mod_p1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JTextField txt_adrs_mod_p;
    public com.toedter.calendar.JDateChooser txt_dt_emb_mod_p;
    public javax.swing.JRadioButton txt_femme;
    public javax.swing.JRadioButton txt_homme;
    public javax.swing.JTextField txt_nbr_enf_mod_p;
    public javax.swing.JTextField txt_nom_pre_mod_p;
    public javax.swing.JTextField txt_num_mod_p;
    public javax.swing.JTextField txt_ref_mod_p;
    public javax.swing.JComboBox<String> txt_situ_mod_p;
    public javax.swing.JTextField txt_tel_mod_p;
    // End of variables declaration//GEN-END:variables
}
