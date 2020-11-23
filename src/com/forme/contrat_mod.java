/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forme;

import com.classe.classe_contrat;
import com.classe.classe_entreprise;
import com.classe.classe_personnel;
import com.requete.Connecter_bd;
import com.requete.connections;
import com.requete.requet_contrat;
import com.requete.requet_entreprise;
import com.requete.requet_personnel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author VONINAHITRA
 */
public class contrat_mod extends javax.swing.JFrame {
private String query;
private String queryv;
private Connecter_bd con_db;
connections maCon = new connections();
ResultSet rs;
PreparedStatement ps;
Statement st;
Connection conc;
String type;

    /**
     * Creates new form contrat_mod
     */
    public contrat_mod() {
        initComponents();
        this.setLocationRelativeTo(null);
       // txt_ref_contrat_mod.setVisible(true);
        this.setResizable(false);
        txt_ref_contrat_mod.setVisible(false);
        txt_enfant.setVisible(false);
    }
private void select_nom_perso() throws Exception, SQLException{
    con_db = new Connecter_bd();
     //txt_num_perso_avcance.removeAllItems();
    String num_p = txt_num_perso_mod.getText().toString();
    query = "select * from tab_contrat where num_perso='"+num_p+"'";
    ResultSet rs = con_db.executeQuery(query);
    if(rs.next()){
       String enf = rs.getString("nbr_enf_perso");
       txt_enfant.setText(enf);
   } 
}
private void verifier_add_contrat() throws Exception{   
  
    if(type.equals("CDD")==true){
       SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
         String dt1 = dt.format(txt_dt_entr_contrat_mod.getDate());
        String dt2 = dt.format(txt_dt_sor_contrat_mod.getDate());
                Date date1 = dt.parse(dt1);
                Date date2 = dt.parse(dt2);
        if(date1.before(date2)){      
        SimpleDateFormat dtcddi = new SimpleDateFormat("dd/MM/yyyy");
        Date actuelle = new Date();
        String dt_sort = dt.format(txt_dt_sor_contrat_mod.getDate());
        String date_nowr = dtcddi.format(actuelle);
        String matricule = txt_num_perso_mod.getText();
        String dt_entr = dt.format(txt_dt_entr_contrat_mod.getDate());
        String dt_embr =dt.format(txt_date_emb_perso_mod.getDate());
        String clasifi = txt_clasoifi_contrat_mod.getSelectedItem().toString();
        String nom_prenomr = txt_nom_pre_perso_mod.getText();
        String indicer = txt_indice_contrat_mod.getText();
        double sal_baser = Double.parseDouble(txt_sal_base_contrat_mod.getText());
        String service = txt_service_mod.getSelectedItem().toString();   
   if(date1.before(date2)){
        String ref = txt_ref_contrat_mod.getText();
 classe_contrat clc = new classe_contrat(matricule, nom_prenomr, service, date_nowr, dt_embr,dt_entr, dt_sort,clasifi,indicer,sal_baser,type);       
 requet_contrat rqtc = new requet_contrat();
 rqtc.modifier(ref,clc);
 int anf =2;
 classe_personnel clp = new classe_personnel(matricule,nom_prenomr, null,null, null,2,null,null);
 requet_personnel rqtp = new requet_personnel();
 rqtp.modifier(ref,clp);
     
//        rqtc.modifier(ref,clc);
       JOptionPane.showMessageDialog(null,"Les informations ont été  bien enregistrés","Message",JOptionPane.PLAIN_MESSAGE);        
     }
        }
       else{
      JOptionPane.showMessageDialog(null,"La date de d'entrée doit être inférieur à la date de sortie","Message",JOptionPane.WARNING_MESSAGE);   
          } 
     
   }else{
       String ref = txt_ref_contrat_mod.getText();
       String indeter="?";    
       SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
       // String num_per = txt_num_perso_contrat.getSelectedItem().toString();
        SimpleDateFormat dtcddi = new SimpleDateFormat("dd/MM/yyyy");
        Date actuelle = new Date();
        String date_nowr = dtcddi.format(actuelle);
        String matricule = txt_num_perso_mod.getText();
        String dt_entr = dt.format(txt_dt_entr_contrat_mod.getDate());
        String dt_embr =dt.format(txt_date_emb_perso_mod.getDate());
        String clasifi = txt_clasoifi_contrat_mod.getSelectedItem().toString();
        String nom_prenomr = txt_nom_pre_perso_mod.getText();
        String indicer = txt_indice_contrat_mod.getText();
        double sal_baser = Double.parseDouble(txt_sal_base_contrat_mod.getText());
        String service = txt_service_mod.getSelectedItem().toString();
   classe_contrat clc = new classe_contrat(matricule,nom_prenomr,service,date_nowr,dt_embr,dt_entr, indeter,clasifi,indicer,sal_baser,type);       
   requet_contrat rqtc = new requet_contrat();
   rqtc.modifier(ref,clc);  
//        classe_contrat clc = new classe_contrat(num_p,nomt,date_now,dtent,indeterminer,clasifi, nbh_mois, nb_h_jrs, indice, sal_base, lieu,type_pai,type);
//        rqtc.modifier(ref,clc);
        JOptionPane.showMessageDialog(null,"Les informations ont été  bien enregistrés","Message",JOptionPane.PLAIN_MESSAGE); 
    
    } 
 }
//    private boolean  acceptindice(){
//        boolean num = true;
//       num=  txt_indice_contrat_mod.getText().matches("^[0-9]*");
//        return num;
//    }
//     private boolean  acceptjrs(){
//        boolean num = true;
//       num=  txt_nbr_hrs_jrs_contrat_mod.getText().matches("^[0-9]*");
//        return num;
//    }
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn_add_contrat_mod = new javax.swing.JButton();
        btn_annuler_contrat_mod = new javax.swing.JButton();
        txt_ref_contrat_mod = new javax.swing.JTextField();
        btn_add_contrat_mod1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_dt_entr_contrat_mod = new com.toedter.calendar.JDateChooser();
        txt_dt_sor_contrat_mod = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_sal_base_contrat_mod = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_clasoifi_contrat_mod = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txt_indice_contrat_mod = new javax.swing.JTextField();
        txt_nom_pre_perso_mod = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_cdi_contrat_mod = new javax.swing.JRadioButton();
        txt_cdd_contrat_mod = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        txt_num_perso_mod = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_date_emb_perso_mod = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txt_service_mod = new javax.swing.JComboBox<>();
        txt_enfant = new javax.swing.JTextField();

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
        jLabel1.setText("MODIFICATION CONTRAT");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_Hand_With_Pen_40px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_add_contrat_mod.setBackground(new java.awt.Color(0, 102, 153));
        btn_add_contrat_mod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_add_contrat_mod.setForeground(new java.awt.Color(255, 255, 255));
        btn_add_contrat_mod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icons8_Checkmark_20px.png"))); // NOI18N
        btn_add_contrat_mod.setText("Enregistrer");
        btn_add_contrat_mod.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_add_contrat_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_contrat_modActionPerformed(evt);
            }
        });

        btn_annuler_contrat_mod.setBackground(new java.awt.Color(0, 102, 153));
        btn_annuler_contrat_mod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_annuler_contrat_mod.setForeground(new java.awt.Color(255, 255, 255));
        btn_annuler_contrat_mod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Close_Window_25px.png"))); // NOI18N
        btn_annuler_contrat_mod.setText("Annuler/Fermer");
        btn_annuler_contrat_mod.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_annuler_contrat_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_annuler_contrat_modActionPerformed(evt);
            }
        });

        txt_ref_contrat_mod.setEnabled(false);

        btn_add_contrat_mod1.setBackground(new java.awt.Color(0, 102, 153));
        btn_add_contrat_mod1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_add_contrat_mod1.setForeground(new java.awt.Color(255, 255, 255));
        btn_add_contrat_mod1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Reset_25px.png"))); // NOI18N
        btn_add_contrat_mod1.setText("Effacer");
        btn_add_contrat_mod1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_add_contrat_mod1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_contrat_mod1ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)), "Modification du contrat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("N° Matricule :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Date d'entré :");

        txt_dt_entr_contrat_mod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_dt_sor_contrat_mod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Date de sortie :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Nom et prénom:");

        txt_sal_base_contrat_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sal_base_contrat_modActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Classification :");

        txt_clasoifi_contrat_mod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_clasoifi_contrat_mod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M1-A1", "M2-1B", "OS1-2A", "OS2-2B", "OS3-3A", "P1A-3B", "OP1B-4A", "OP2A-4B", "OP2A-4B", "OP2B-5A", "OP3-5B", "HC" }));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Indice :");

        txt_nom_pre_perso_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nom_pre_perso_modActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Salaire de base :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Type de contrat :");

        txt_cdi_contrat_mod.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(txt_cdi_contrat_mod);
        txt_cdi_contrat_mod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_cdi_contrat_mod.setText("CDI");
        txt_cdi_contrat_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cdi_contrat_modActionPerformed(evt);
            }
        });

        txt_cdd_contrat_mod.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(txt_cdd_contrat_mod);
        txt_cdd_contrat_mod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_cdd_contrat_mod.setText("CDD");
        txt_cdd_contrat_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cdd_contrat_modActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Ar");

        txt_num_perso_mod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Date d'embauche:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Service :");

        txt_service_mod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_service_mod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Directeur générale", "Directeur technique", "Chef de personnel", "Comptable", "Caissier", "Magasin comptable", "Magasin atelier", "Responsable achat", "Coursier", "Coursier magasin", "Mécanique", "Mécanique éléctricien", "Peinture", "Agent entretient", "Femme de menage", "Gardien" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
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
                                                    .addComponent(txt_cdd_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(34, 34, 34)
                                                    .addComponent(txt_cdi_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txt_nom_pre_perso_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_num_perso_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                            .addGap(27, 27, 27)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_clasoifi_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(txt_date_emb_perso_mod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGap(91, 91, 91))))))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(42, 42, 42)
                                    .addComponent(txt_dt_sor_contrat_mod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(91, 91, 91)))
                            .addComponent(jLabel8))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(txt_dt_entr_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_sal_base_contrat_mod)
                            .addComponent(txt_indice_contrat_mod)
                            .addComponent(txt_service_mod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(7, 7, 7)
                        .addComponent(jLabel14)
                        .addGap(32, 32, 32))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_enfant, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_num_perso_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txt_service_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nom_pre_perso_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_cdd_contrat_mod)
                            .addComponent(txt_cdi_contrat_mod)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_indice_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txt_sal_base_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_clasoifi_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_date_emb_perso_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_dt_entr_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_enfant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(txt_dt_sor_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txt_ref_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_add_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_add_contrat_mod1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_annuler_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_ref_contrat_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add_contrat_mod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_add_contrat_mod1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btn_annuler_contrat_mod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_add_contrat_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_contrat_modActionPerformed
    try {    
        select_nom_perso();
    } catch (Exception ex) {
        Logger.getLogger(contrat_mod.class.getName()).log(Level.SEVERE, null, ex);
    }
        if(txt_indice_contrat_mod.getText().equals("")==true||
            txt_sal_base_contrat_mod.getText().equals("")==true ){
            JOptionPane.showMessageDialog(null,"Tous les champs sont obligatoires\n Veillez remplire, SVP","Message",JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        String dt1 =dt.format(txt_date_emb_perso_mod.getDate());
        String dt2 = dt.format(txt_dt_entr_contrat_mod.getDate());
        Date date1 = dt.parse(dt1);
        Date date2 = dt.parse(dt2);
        if(date2.before(date1)){
            JOptionPane.showMessageDialog(null,"Désolé ! La date d'embauche doit être supérieur à la date d'entrée, SVP","Message",JOptionPane.WARNING_MESSAGE); 
        }else{
              verifier_add_contrat();   
            }
             
            } catch (Exception ex) {
                Logger.getLogger(contrat_mod.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_add_contrat_modActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    try {
        select_nom_perso();
    } catch (Exception ex) {
        Logger.getLogger(contrat_mod.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_formWindowOpened

    private void btn_annuler_contrat_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_annuler_contrat_modActionPerformed
      this.dispose();
    }//GEN-LAST:event_btn_annuler_contrat_modActionPerformed

    private void btn_add_contrat_mod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_contrat_mod1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_add_contrat_mod1ActionPerformed

    private void txt_sal_base_contrat_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sal_base_contrat_modActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sal_base_contrat_modActionPerformed

    private void txt_nom_pre_perso_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nom_pre_perso_modActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nom_pre_perso_modActionPerformed

    private void txt_cdi_contrat_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cdi_contrat_modActionPerformed
        type="CDI";
        txt_dt_sor_contrat_mod.setEnabled(false);
    }//GEN-LAST:event_txt_cdi_contrat_modActionPerformed

    private void txt_cdd_contrat_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cdd_contrat_modActionPerformed
        type = "CDD";
        txt_dt_sor_contrat_mod.setEnabled(true);
    }//GEN-LAST:event_txt_cdd_contrat_modActionPerformed

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
            java.util.logging.Logger.getLogger(contrat_mod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(contrat_mod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(contrat_mod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(contrat_mod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new contrat_mod().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add_contrat_mod;
    private javax.swing.JButton btn_add_contrat_mod1;
    private javax.swing.JButton btn_annuler_contrat_mod;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
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
    public javax.swing.JRadioButton txt_cdd_contrat_mod;
    public javax.swing.JRadioButton txt_cdi_contrat_mod;
    public javax.swing.JComboBox<String> txt_clasoifi_contrat_mod;
    public com.toedter.calendar.JDateChooser txt_date_emb_perso_mod;
    public com.toedter.calendar.JDateChooser txt_dt_entr_contrat_mod;
    public com.toedter.calendar.JDateChooser txt_dt_sor_contrat_mod;
    public javax.swing.JTextField txt_enfant;
    public javax.swing.JTextField txt_indice_contrat_mod;
    public javax.swing.JTextField txt_nom_pre_perso_mod;
    public javax.swing.JTextField txt_num_perso_mod;
    public javax.swing.JTextField txt_ref_contrat_mod;
    public javax.swing.JTextField txt_sal_base_contrat_mod;
    public javax.swing.JComboBox<String> txt_service_mod;
    // End of variables declaration//GEN-END:variables
}
