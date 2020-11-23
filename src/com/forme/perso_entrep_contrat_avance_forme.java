/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.forme;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.requete.Connecter_bd;
import com.requete.connections;
import com.requete.requet_avance;
import com.requete.requet_bulletin;
import com.requete.requet_contrat;
import com.requete.requet_reduction;
import com.requete.requet_suplementaire;
import com.requete.requet_personnel;
//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.requete.Connexion;
import com.requete.requet_impression;
import com.requete.requet_irsa;
import com.requete.requet_print_bulletin;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author VONINAHITRA
 */
public class perso_entrep_contrat_avance_forme extends javax.swing.JFrame {

    public String num_pdf_av;
/////////////////////////////pour les connections et la configuration des table puis pour les variable////////////////////////////////////////////////////

    ResultSet rs;
    ResultSet rse;
    PreparedStatement ps;
    PreparedStatement pse;
    Statement st;
    Statement ste;
    Connection conc;
    Connecter_bd con_db = new Connecter_bd();
    connections maCon = new connections();
    String query;

////////////////////////////////personnel///////////////////////////////////////
    public DefaultTableModel tab_perso;
    private String p;
    private String num_perso;
    private String nom_perso;
    private String sexe_perso;
    private String dt_perso;
    private String situ_perso;
    private int nb_enf_perso;
    private String adrs_perso;
    private String tel_perso;

///////////////////////////////entreprise///////////////////////////////////////
    public DefaultTableModel tab_entrep;
    private String e;
    private String num_entrep;
    private String desing_entrep;
    private String adrs_entrep;
    private String tel_entrep;
    private String email_entrep;
    private String fomr_entrep;

/////////////////////////////Contrat////////////////////////////////////////////
    public DefaultTableModel tab_contrat;
    private String num_perso_c;
    private String dt_now_c;
    private String dt_en_c;
    private String dt_sor_c;
    private String clasifi_c;
    private int nbr_hr_jr_c;
    private int nbr_hr_moi_c;
    private String indic_c;
    private String sal_base_c;
    private String lieu_c;
    private String type_paie_c;
    private String type_cont_c;

////////////////////////////Avance//////////////////////////////////////////////
    public DefaultTableModel tab_avance;
    private String num_p_a;
    private String paynet_a;
    private String dt_now_a;
    private int mois_a;
    private String annee_a;
    private String pdtdu_a;
    private String pdtau_a;
    private String reste_a;

///////////////////////Payement du mois/////////////////////////////////////////
    public DefaultTableModel tab_b;
    private String num_p_b;
    private String dt_now_b;
    private int mois_b;
    private int annee_b;
    private String padu_b;
    private String pau_b;
    private String totale_reduc_b;
    private String totale_av_b;
    private String totale_net_b;

///////////////////////suplementaire(avantages)////////////////////////////////////////////////
    public DefaultTableModel tab_av;
    private String num_p_av;
    private String dt_now_av;
    private int mois_av;
    private String annee_av;
    private String periode_du_av;
    private String periode_au_av;
    private String idemnite_av;
    private String voca_piece_av;
    private String alloc_fami;
    private int nbhres_sup_av;
    private String hrs_sup8_av;
    private String hrs_sup_plus8_av;
    private String totale_brute_av;
///////////////////////reduction////////////////////////////////////////////////
    public DefaultTableModel tab_reduc;
    private String num_p_reduc;
    private String dt_now_reduc;
    private int mois_reduc;
    private String annee_reduc;
    private String pdu_reduc;
    private String pau_reduc;
    private String cnaps_reduc;
    private String osief_reduc;
    private String irsa_reduc;
    private String cess_sal_reduc;
    private String nouri_log_reduc;
    private String autres_reduc;
    private String totale_reduc;

//condition d'affichage
    Date date_now = new Date();
    DateFormat dateFormat_now_an = new SimpleDateFormat("yyyy");
    String dt_an_now = dateFormat_now_an.format(date_now);
    DateFormat dateFormat_now_mois = new SimpleDateFormat("MM");
    String dt_mois_now = dateFormat_now_mois.format(date_now);

    /**
     * Creates new form perso_entrep_contrat_avance_forme
     */
    public perso_entrep_contrat_avance_forme() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        txt_dt_payer_du.setVisible(false);
        txt_ref_imprimer_bulletin.setVisible(false);
        txt_dt_imprimer_avance.setVisible(false);
        txt_ref_imprimer_avance.setVisible(false);
        btn_print_avance_format.setVisible(false);
    }

////////////////////////////////////////////////////////////////////////////////Personnel fonctions et table/////////////////////////////////////////////////////
    public void recuperer_val_p() throws Exception {
        tab_perso.setRowCount(0);//initialisation du table
        query = "select * from tab_perso order by num_perso ASC";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            num_perso = rs.getString("num_perso");
            nom_perso = rs.getString("nom_pre_perso");
            sexe_perso = rs.getString("sexe_perso");
            dt_perso = rs.getString("dt_nais_perso");
            situ_perso = rs.getString("situ_perso");
            nb_enf_perso = rs.getInt("nbr_enf_perso");
            adrs_perso = rs.getString("adrs_perso");
            tel_perso = rs.getString("tel_perso");
            Object[] perso = {num_perso, nom_perso, sexe_perso, dt_perso, situ_perso, nb_enf_perso, adrs_perso, tel_perso};
            tab_perso.addRow(perso);
        }
    }

    public void config_tab_perso() throws Exception {
        tab_perso = new DefaultTableModel();
        tab_perso.addColumn("Matricule");
        tab_perso.addColumn("Nom et prénoms");
        tab_perso.addColumn("Sexe");
        tab_perso.addColumn("Date de naissance");
        tab_perso.addColumn("Etat-civil");
        tab_perso.addColumn("Enfant(s)");
        tab_perso.addColumn("Adresse");
        tab_perso.addColumn("Télephone");
        JTableHeader tb = table_personnel.getTableHeader();
        tb.setBackground(new Color(0,102,153));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
        table_personnel.setBackground(Color.WHITE);
        table_personnel.setModel(tab_perso);
        table_personnel.getColumnModel().getColumn(0).setPreferredWidth(65);
        table_personnel.getColumnModel().getColumn(0).setMaxWidth(65);
        table_personnel.getColumnModel().getColumn(0).setMinWidth(65);

        table_personnel.getColumnModel().getColumn(1).setPreferredWidth(350);
        table_personnel.getColumnModel().getColumn(1).setMaxWidth(350);
        table_personnel.getColumnModel().getColumn(1).setMinWidth(350);

        table_personnel.getColumnModel().getColumn(2).setPreferredWidth(50);
        table_personnel.getColumnModel().getColumn(2).setMaxWidth(50);
        table_personnel.getColumnModel().getColumn(2).setMinWidth(50);

        table_personnel.getColumnModel().getColumn(3).setPreferredWidth(120);
        table_personnel.getColumnModel().getColumn(3).setMaxWidth(120);
        table_personnel.getColumnModel().getColumn(3).setMinWidth(120);

        table_personnel.getColumnModel().getColumn(4).setPreferredWidth(80);
        table_personnel.getColumnModel().getColumn(4).setMaxWidth(80);
        table_personnel.getColumnModel().getColumn(4).setMinWidth(80);

        table_personnel.getColumnModel().getColumn(5).setPreferredWidth(80);
        table_personnel.getColumnModel().getColumn(5).setMaxWidth(80);
        table_personnel.getColumnModel().getColumn(5).setMinWidth(80);

        table_personnel.getColumnModel().getColumn(6).setPreferredWidth(290);
        table_personnel.getColumnModel().getColumn(6).setMaxWidth(290);
        table_personnel.getColumnModel().getColumn(6).setMinWidth(290);

        table_personnel.getColumnModel().getColumn(7).setPreferredWidth(165);
        table_personnel.getColumnModel().getColumn(7).setMaxWidth(165);
        table_personnel.getColumnModel().getColumn(7).setMinWidth(165);
        recuperer_val_p();
    }

    ////////////////////////////////////////////////////////////////////////////////Fonction entreprise et table////////////////////////////////////////////////////////////
    public void recuperer_val_e() throws Exception {
        tab_entrep.setRowCount(0);//initialisation du table
        query = "select * from tab_entrep order by num_entrep ASC";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            num_entrep = rs.getString("num_entrep");
            desing_entrep = rs.getString("desi_entrep");
            adrs_entrep = rs.getString("adrs_entrep");
            tel_entrep = rs.getString("tel_entrep");
            email_entrep = rs.getString("email_entrep");
            fomr_entrep = rs.getString("form_juridique_entrep");
            Object[] entrep = {num_entrep, desing_entrep, adrs_entrep, tel_entrep, email_entrep, fomr_entrep};

            tab_entrep.addRow(entrep);
        }
    }
////////////////////////////////////////////////////////////////////////////////Fonction contrat et table///////////////////////////////////////////////////////////
    public void config_tab_contrat() throws Exception {
        tab_contrat = new DefaultTableModel();
        tab_contrat.addColumn("Matricule");
        tab_contrat.addColumn("Nom et prénom");
        tab_contrat.addColumn("Service");
        tab_contrat.addColumn("Date");
        tab_contrat.addColumn("Date d'embauche");
        tab_contrat.addColumn("Date d'entreée");
        tab_contrat.addColumn("Date de sortie");
        tab_contrat.addColumn("Classification");
        tab_contrat.addColumn("Indice");
        tab_contrat.addColumn("Salaire de base");
        tab_contrat.addColumn("Contrat");
        JTableHeader tb = table_contrat.getTableHeader();
        tb.setBackground(new Color(0,102,153));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
        table_contrat.setModel(tab_contrat);

        table_contrat.getColumnModel().getColumn(0).setPreferredWidth(60);
        table_contrat.getColumnModel().getColumn(0).setMaxWidth(60);
        table_contrat.getColumnModel().getColumn(0).setMinWidth(60);

        table_contrat.getColumnModel().getColumn(1).setPreferredWidth(270);
        table_contrat.getColumnModel().getColumn(1).setMaxWidth(270);
        table_contrat.getColumnModel().getColumn(1).setMinWidth(270);

        table_contrat.getColumnModel().getColumn(2).setPreferredWidth(150);
        table_contrat.getColumnModel().getColumn(2).setMaxWidth(150);
        table_contrat.getColumnModel().getColumn(2).setMinWidth(150);

        table_contrat.getColumnModel().getColumn(3).setPreferredWidth(120);
        table_contrat.getColumnModel().getColumn(3).setMaxWidth(120);
        table_contrat.getColumnModel().getColumn(3).setMinWidth(120);

        table_contrat.getColumnModel().getColumn(4).setPreferredWidth(100);
        table_contrat.getColumnModel().getColumn(4).setMaxWidth(100);
        table_contrat.getColumnModel().getColumn(4).setMinWidth(100);

        table_contrat.getColumnModel().getColumn(5).setPreferredWidth(85);
        table_contrat.getColumnModel().getColumn(5).setMaxWidth(85);
        table_contrat.getColumnModel().getColumn(5).setMinWidth(85);

        table_contrat.getColumnModel().getColumn(6).setPreferredWidth(85);
        table_contrat.getColumnModel().getColumn(6).setMaxWidth(85);
        table_contrat.getColumnModel().getColumn(6).setMinWidth(85);

        table_contrat.getColumnModel().getColumn(7).setPreferredWidth(90);
        table_contrat.getColumnModel().getColumn(7).setMaxWidth(90);
        table_contrat.getColumnModel().getColumn(7).setMinWidth(90);

        table_contrat.getColumnModel().getColumn(8).setPreferredWidth(65);
        table_contrat.getColumnModel().getColumn(8).setMaxWidth(65);
        table_contrat.getColumnModel().getColumn(8).setMinWidth(65);

        table_contrat.getColumnModel().getColumn(9).setPreferredWidth(95);
        table_contrat.getColumnModel().getColumn(9).setMaxWidth(95);
        table_contrat.getColumnModel().getColumn(9).setMinWidth(95);

        table_contrat.getColumnModel().getColumn(10).setPreferredWidth(60);
        table_contrat.getColumnModel().getColumn(10).setMaxWidth(60);
        table_contrat.getColumnModel().getColumn(10).setMinWidth(60);

//        table_contrat.getColumnModel().getColumn(11).setPreferredWidth(100);
//        table_contrat.getColumnModel().getColumn(11).setMaxWidth(100);
//        table_contrat.getColumnModel().getColumn(11).setMinWidth(100);

        recuperer_val_c();
    }

    public void recuperer_val_c() throws Exception {
        tab_contrat.setRowCount(0);//initialisation du table
        query = "select * from tab_contrat order by num_perso ASC";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            num_perso_c = rs.getString("num_perso");
            String nom_pre = rs.getString("nom_pre_contrat");
            String service = rs.getString("service");
            String dt_now_c = rs.getString("dt_now_contrat");
            String date_embauche = rs.getString("date_embauche");
            String dt_en_c = rs.getString("dt_entre_contrat");
            String dt_sor_c = rs.getString("dt_sortie_contrat");
            String clasifi_c = rs.getString("clasifi_contrat");
            indic_c = rs.getString("indice_contrat");
            sal_base_c = rs.getString("sal_base_contrat");
            type_cont_c = rs.getString("type_contrat");
            Object[] contrat = {num_perso_c,nom_pre,service,dt_now_c,date_embauche ,dt_en_c,dt_sor_c,clasifi_c,indic_c,sal_base_c,type_cont_c};
            tab_contrat.addRow(contrat);

        }
    }
///////////////////////////////////////////////////////////////////////////////////Fonction avance et table//////////////////////////////////////////////////////////

    public void config_tab_avance() throws Exception {
        tab_avance = new DefaultTableModel();
        tab_avance.addColumn("Matricule");
        tab_avance.addColumn("Date");
        tab_avance.addColumn("Mois");
        tab_avance.addColumn("Année");
        tab_avance.addColumn("Début Période ");
        tab_avance.addColumn("Fin Période");
        tab_avance.addColumn("Net à payer");
        tab_avance.addColumn("Reste du salaire");
        JTableHeader tb = table_avance.getTableHeader();
        tb.setBackground(new Color(0,102,153));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
        table_avance.setModel(tab_avance);

        table_avance.getColumnModel().getColumn(0).setPreferredWidth(100);
        table_avance.getColumnModel().getColumn(0).setMaxWidth(100);
        table_avance.getColumnModel().getColumn(0).setMinWidth(100);

        table_avance.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_avance.getColumnModel().getColumn(1).setMaxWidth(150);
        table_avance.getColumnModel().getColumn(1).setMinWidth(150);

        table_avance.getColumnModel().getColumn(2).setPreferredWidth(100);
        table_avance.getColumnModel().getColumn(2).setMaxWidth(100);
        table_avance.getColumnModel().getColumn(2).setMinWidth(100);

        table_avance.getColumnModel().getColumn(3).setPreferredWidth(120);
        table_avance.getColumnModel().getColumn(3).setMaxWidth(120);
        table_avance.getColumnModel().getColumn(3).setMinWidth(120);

        table_avance.getColumnModel().getColumn(4).setPreferredWidth(120);
        table_avance.getColumnModel().getColumn(4).setMaxWidth(120);
        table_avance.getColumnModel().getColumn(4).setMinWidth(120);

        table_avance.getColumnModel().getColumn(5).setPreferredWidth(100);
        table_avance.getColumnModel().getColumn(5).setMaxWidth(100);
        table_avance.getColumnModel().getColumn(5).setMinWidth(100);

        table_avance.getColumnModel().getColumn(6).setPreferredWidth(240);
        table_avance.getColumnModel().getColumn(6).setMaxWidth(240);
        table_avance.getColumnModel().getColumn(6).setMinWidth(240);

        table_avance.getColumnModel().getColumn(7).setPreferredWidth(250);
        table_avance.getColumnModel().getColumn(7).setMaxWidth(250);
        table_avance.getColumnModel().getColumn(7).setMinWidth(250);

        recuperer_val_a();
    }

    public void recuperer_val_a() throws SQLException {
        tab_avance.setRowCount(0);//initialisation du table
        query = "select * from tab_avance where annee_avance='" + dt_an_now + "' order by mois_avance";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            num_p_a = rs.getString("num_perso");
            dt_now_a = rs.getString("dt_now_avance");
            mois_a = rs.getInt("mois_avance");
            annee_a = rs.getString("annee_avance");
            pdtdu_a = rs.getString("periode_du_avance");
            pdtau_a = rs.getString("periode_au_avance");
            paynet_a = rs.getString("total_avance");
            reste_a = rs.getString("reste_salaire");
            switch (mois_a) {
                case 12:
                    String deca = "Décembre";
                    Object[] avance_dec = {num_p_a, dt_now_a, deca, annee_a, pdtdu_a, pdtau_a, paynet_a, reste_a};
                    tab_avance.addRow(avance_dec);
                    break;
                case 11:
                    String nova = "Novembre";
                    Object[] avance_nov = {num_p_a, dt_now_a, nova, annee_a, pdtdu_a, pdtau_a, paynet_a, reste_a};
                    tab_avance.addRow(avance_nov);
                    break;
                case 10:
                    String octa = "Octobre";
                    Object[] avance_oct = {num_p_a, dt_now_a, octa, annee_a, pdtdu_a, pdtau_a, paynet_a, reste_a};
                    tab_avance.addRow(avance_oct);
                    break;
                case 9:
                    String septa = "Septembre";
                    Object[] avance_sept = {num_p_a, dt_now_a, septa, annee_a, pdtdu_a, pdtau_a, paynet_a, reste_a};
                    tab_avance.addRow(avance_sept);
                    break;
                case 8:
                    String outa = "Août";
                    Object[] avance_out = {num_p_a, dt_now_a, outa, annee_a, pdtdu_a, pdtau_a, paynet_a, reste_a};
                    tab_avance.addRow(avance_out);
                    break;
                case 7:
                    String julta = "Juillet";
                    Object[] avance_jult = {num_p_a, dt_now_a, julta, annee_a, pdtdu_a, pdtau_a, paynet_a, reste_a};
                    tab_avance.addRow(avance_jult);
                    break;
                case 6:
                    String juina = "Juin";
                    Object[] avance_Juin = {num_p_a, dt_now_a, juina, annee_a, pdtdu_a, pdtau_a, paynet_a, reste_a};
                    tab_avance.addRow(avance_Juin);
                    break;
                case 5:
                    String maia = "Mai";
                    Object[] avance_mai = {num_p_a, dt_now_a, maia, annee_a, pdtdu_a, pdtau_a, paynet_a, reste_a};
                    tab_avance.addRow(avance_mai);
                    break;
                case 4:
                    String avra = "Avril";
                    Object[] avance_avr = {num_p_a, dt_now_a, avra, annee_a, pdtdu_a, pdtau_a, paynet_a, reste_a};
                    tab_avance.addRow(avance_avr);
                    break;
                case 3:
                    String marsa = "Mars";
                    Object[] avance_mars = {num_p_a, dt_now_a, marsa, annee_a, pdtdu_a, pdtau_a, paynet_a, reste_a};
                    tab_avance.addRow(avance_mars);
                    break;
                case 2:
                    String feva = "Février";
                    Object[] avance_fev = {num_p_a, dt_now_a, feva, annee_a, pdtdu_a, pdtau_a, paynet_a, reste_a};
                    tab_avance.addRow(avance_fev);
                    break;
                case 1:
                    String java = "Janvier";
                    Object[] avance_jav = {num_p_a, dt_now_a, java, annee_a, pdtdu_a, pdtau_a, paynet_a, reste_a};
                    tab_avance.addRow(avance_jav);
                    break;

            }

        }
    }
////////////////////////////////////////////////////////////////////////////////Fonction bulletin et table//////////////////////////////////////////////////

    public void config_tab_bulletin() throws Exception {
        tab_b = new DefaultTableModel();
        tab_b.addColumn("Matricule");
        tab_b.addColumn("Date");
        tab_b.addColumn("Mois");
        tab_b.addColumn("Année");
        tab_b.addColumn("Début période");
        tab_b.addColumn("Fin période");
        tab_b.addColumn("Total de déduction et taxe");
        tab_b.addColumn("Total de suplémentaires");
        tab_b.addColumn("Salaire net");
        JTableHeader tb = table_bulletin.getTableHeader();
        tb.setBackground(new Color(0,102,153));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tohome", Font.PLAIN, 12));
        table_bulletin.setModel(tab_b);
        table_bulletin.getColumnModel().getColumn(0).setPreferredWidth(65);
        table_bulletin.getColumnModel().getColumn(0).setMaxWidth(55);
        table_bulletin.getColumnModel().getColumn(0).setMinWidth(65);

        table_bulletin.getColumnModel().getColumn(1).setPreferredWidth(130);
        table_bulletin.getColumnModel().getColumn(1).setMaxWidth(130);
        table_bulletin.getColumnModel().getColumn(1).setMinWidth(130);

        table_bulletin.getColumnModel().getColumn(2).setPreferredWidth(72);
        table_bulletin.getColumnModel().getColumn(2).setMaxWidth(72);
        table_bulletin.getColumnModel().getColumn(2).setMinWidth(72);

        table_bulletin.getColumnModel().getColumn(3).setPreferredWidth(40);
        table_bulletin.getColumnModel().getColumn(3).setMaxWidth(40);
        table_bulletin.getColumnModel().getColumn(3).setMinWidth(40);

        table_bulletin.getColumnModel().getColumn(4).setPreferredWidth(84);
        table_bulletin.getColumnModel().getColumn(4).setMaxWidth(84);
        table_bulletin.getColumnModel().getColumn(4).setMinWidth(84);

        table_bulletin.getColumnModel().getColumn(5).setPreferredWidth(85);
        table_bulletin.getColumnModel().getColumn(5).setMaxWidth(85);
        table_bulletin.getColumnModel().getColumn(5).setMinWidth(85);

        table_bulletin.getColumnModel().getColumn(6).setPreferredWidth(250);
        table_bulletin.getColumnModel().getColumn(6).setMaxWidth(250);
        table_bulletin.getColumnModel().getColumn(6).setMinWidth(250);

        table_bulletin.getColumnModel().getColumn(7).setPreferredWidth(250);
        table_bulletin.getColumnModel().getColumn(7).setMaxWidth(250);
        table_bulletin.getColumnModel().getColumn(7).setMinWidth(250);

        table_bulletin.getColumnModel().getColumn(8).setPreferredWidth(250);
        table_bulletin.getColumnModel().getColumn(8).setMaxWidth(250);
        table_bulletin.getColumnModel().getColumn(8).setMinWidth(250);

        recuperer_val_b();
    }

    public void recuperer_val_b() throws SQLException {
        tab_b.setRowCount(0);//initialisation du table
        Date date_now = new Date();
        DateFormat dateFormat_now_mw = new SimpleDateFormat("yyyy");
        String dt_mw_now = dateFormat_now_mw.format(date_now);
        int mw_an_1 = Integer.parseInt(dt_mw_now);
        query = "select * from tab_paie where annee_bulletin='" + dt_an_now + "' order by mois_bulletin ";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            num_p_b = rs.getString("num_perso");
            dt_now_b = rs.getString("dt_now_bulletin");
            mois_b = rs.getInt("mois_bulletin");
            annee_b = rs.getInt("annee_bulletin");
            padu_b = rs.getString("periode_du_bulletin");
            pau_b = rs.getString("periode_au_bulletin");
            totale_reduc_b = rs.getString("total_reduc_bulletin");
            totale_av_b = rs.getString("totale_av_bulletin");
            totale_net_b = rs.getString("totale_net_bulletin");
            switch (mois_b) {
                case 12:
                    String decb = "Décembre";
                    Object[] bulletin_dec = {num_p_b, dt_now_b, decb, annee_b, padu_b, pau_b, totale_reduc_b, totale_av_b, totale_net_b};
                    tab_b.addRow(bulletin_dec);
                    break;
                case 11:
                    String novb = "Novembre";
                    Object[] bulletin_nov = {num_p_b, dt_now_b, novb, annee_b, padu_b, pau_b, totale_reduc_b, totale_av_b, totale_net_b};
                    tab_b.addRow(bulletin_nov);
                    break;
                case 10:
                    String octb = "Octobre";
                    Object[] bulletin_oct = {num_p_b, dt_now_b, octb, annee_b, padu_b, pau_b, totale_reduc_b, totale_av_b, totale_net_b};
                    tab_b.addRow(bulletin_oct);
                    break;
                case 9:
                    String septb = "Septembre";
                    Object[] bulletin_sept = {num_p_b, dt_now_b, septb, annee_b, padu_b, pau_b, totale_reduc_b, totale_av_b, totale_net_b};
                    tab_b.addRow(bulletin_sept);
                    break;
                case 8:
                    String outb = "Août";
                    Object[] bulletin_aout = {num_p_b, dt_now_b, outb, annee_b, padu_b, pau_b, totale_reduc_b, totale_av_b, totale_net_b};
                    tab_b.addRow(bulletin_aout);
                    break;
                case 7:
                    String juilb = "Juillet";
                    Object[] bulletin_juil = {num_p_b, dt_now_b, juilb, annee_b, padu_b, pau_b, totale_reduc_b, totale_av_b, totale_net_b};
                    tab_b.addRow(bulletin_juil);
                    break;
                case 6:
                    String juinb = "Juin";
                    Object[] bulletin_juin = {num_p_b, dt_now_b, juinb, annee_b, padu_b, pau_b, totale_reduc_b, totale_av_b, totale_net_b};
                    tab_b.addRow(bulletin_juin);
                    break;
                case 5:
                    String maib = "Mai";
                    Object[] bulletin_mai = {num_p_b, dt_now_b, maib, annee_b, padu_b, pau_b, totale_reduc_b, totale_av_b, totale_net_b};
                    tab_b.addRow(bulletin_mai);
                    break;
                case 4:
                    String avrb = "Avril";
                    Object[] bulletin_avr = {num_p_b, dt_now_b, avrb, annee_b, padu_b, pau_b, totale_reduc_b, totale_av_b, totale_net_b};
                    tab_b.addRow(bulletin_avr);
                    break;
                case 3:
                    String marsb = "Mars";
                    Object[] bulletin_mars = {num_p_b, dt_now_b, marsb, annee_b, padu_b, pau_b, totale_reduc_b, totale_av_b, totale_net_b};
                    tab_b.addRow(bulletin_mars);
                    break;
                case 2:
                    String fevb = "Février";
                    Object[] bulletin_fev = {num_p_b, dt_now_b, fevb, annee_b, padu_b, pau_b, totale_reduc_b, totale_av_b, totale_net_b};
                    tab_b.addRow(bulletin_fev);
                    break;
                case 1:
                    String javb = "Janvier";
                    Object[] bulletin_jav = {num_p_b, dt_now_b, javb, annee_b, padu_b, pau_b, totale_reduc_b, totale_av_b, totale_net_b};
                    tab_b.addRow(bulletin_jav);
                    break;
            }
        }
    }

///////////////////////////////////////////////////////////////////////////////Fonction avatage heures sup et table/////////////////////////////////////////////////////
    public void config_tab_avantage() throws Exception {
        tab_av = new DefaultTableModel();
        tab_av.addColumn("Matricule");
        tab_av.addColumn("Date");
        tab_av.addColumn("Mois");
        tab_av.addColumn("Année");
        tab_av.addColumn("Début Période");
        tab_av.addColumn("Fin Période");
        tab_av.addColumn("Indeminité");
        tab_av.addColumn("Vocation");
        tab_av.addColumn("Ancienneté");
        tab_av.addColumn("Heures 30% et 50%");
        tab_av.addColumn("Heures 40%");
        tab_av.addColumn("Maj à 30%");
        tab_av.addColumn("Maj à 50%");
        tab_av.addColumn("Maj à 40%");
        tab_av.addColumn("Total brutes");
        JTableHeader tb = table_suplementaire.getTableHeader();
        tb.setBackground(new Color(0,102,153));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
        table_suplementaire.setModel(tab_av);
  
        table_suplementaire.getColumnModel().getColumn(0).setPreferredWidth(60);
        table_suplementaire.getColumnModel().getColumn(0).setMaxWidth(60);
        table_suplementaire.getColumnModel().getColumn(0).setMinWidth(60);

        table_suplementaire.getColumnModel().getColumn(1).setPreferredWidth(105);
        table_suplementaire.getColumnModel().getColumn(1).setMaxWidth(105);
        table_suplementaire.getColumnModel().getColumn(1).setMinWidth(105);

        table_suplementaire.getColumnModel().getColumn(2).setPreferredWidth(72);
        table_suplementaire.getColumnModel().getColumn(2).setMaxWidth(72);
        table_suplementaire.getColumnModel().getColumn(2).setMinWidth(72);

        table_suplementaire.getColumnModel().getColumn(3).setPreferredWidth(40);
        table_suplementaire.getColumnModel().getColumn(3).setMaxWidth(40);
        table_suplementaire.getColumnModel().getColumn(3).setMinWidth(40);

        table_suplementaire.getColumnModel().getColumn(4).setPreferredWidth(84);
        table_suplementaire.getColumnModel().getColumn(4).setMaxWidth(84);
        table_suplementaire.getColumnModel().getColumn(4).setMinWidth(84);

        table_suplementaire.getColumnModel().getColumn(5).setPreferredWidth(70);
        table_suplementaire.getColumnModel().getColumn(5).setMaxWidth(70);
        table_suplementaire.getColumnModel().getColumn(5).setMinWidth(70);

        table_suplementaire.getColumnModel().getColumn(6).setPreferredWidth(80);
        table_suplementaire.getColumnModel().getColumn(6).setMaxWidth(80);
        table_suplementaire.getColumnModel().getColumn(6).setMinWidth(80);

        table_suplementaire.getColumnModel().getColumn(7).setPreferredWidth(80);
        table_suplementaire.getColumnModel().getColumn(7).setMaxWidth(80);
        table_suplementaire.getColumnModel().getColumn(7).setMinWidth(80);
        
        table_suplementaire.getColumnModel().getColumn(8).setPreferredWidth(75);
        table_suplementaire.getColumnModel().getColumn(8).setMaxWidth(75);
        table_suplementaire.getColumnModel().getColumn(8).setMinWidth(75);

        table_suplementaire.getColumnModel().getColumn(9).setPreferredWidth(120);
        table_suplementaire.getColumnModel().getColumn(9).setMaxWidth(120);
        table_suplementaire.getColumnModel().getColumn(9).setMinWidth(120);

        table_suplementaire.getColumnModel().getColumn(10).setPreferredWidth(75);
        table_suplementaire.getColumnModel().getColumn(10).setMaxWidth(75);
        table_suplementaire.getColumnModel().getColumn(10).setMinWidth(75);

        table_suplementaire.getColumnModel().getColumn(11).setPreferredWidth(75);
        table_suplementaire.getColumnModel().getColumn(11).setMaxWidth(75);
        table_suplementaire.getColumnModel().getColumn(11).setMinWidth(75);

        table_suplementaire.getColumnModel().getColumn(12).setPreferredWidth(75);
        table_suplementaire.getColumnModel().getColumn(12).setMaxWidth(75);
        table_suplementaire.getColumnModel().getColumn(12).setMinWidth(75);
        
        table_suplementaire.getColumnModel().getColumn(13).setPreferredWidth(75);
        table_suplementaire.getColumnModel().getColumn(13).setMinWidth(75);
        
        table_suplementaire.getColumnModel().getColumn(14).setPreferredWidth(100);
        table_suplementaire.getColumnModel().getColumn(14).setMaxWidth(100);
        table_suplementaire.getColumnModel().getColumn(14).setMinWidth(100);
        
         

        recuperer_val_av();
    }

    public void recuperer_val_av() throws SQLException {
        tab_av.setRowCount(0);//initialisation du table
        Date date_now = new Date();
        DateFormat dateFormat_now_mw = new SimpleDateFormat("yyyy");
        String dt_mw_now = dateFormat_now_mw.format(date_now);
        int mw_an_1 = Integer.parseInt(dt_mw_now);
        query = "select * from tab_av where annee_av='" + dt_an_now + "' order by mois_av";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            num_p_av = rs.getString("num_perso");
            dt_now_av = rs.getString("dt_now_av");
            mois_av = rs.getInt("mois_av");
            annee_av = rs.getString("annee_av");
            periode_du_av = rs.getString("periode_du_av");
            periode_au_av = rs.getString("periode_au_av");
            idemnite_av = rs.getString("idemnite_av");
            String anciente =rs.getString("anciente");
            String heure40 = rs.getString("nbrheure40");
            String val_heure40 =rs.getString("val_heures_40");
            voca_piece_av = rs.getString("voca_piece_av");
            nbhres_sup_av = rs.getInt("nbrhers_sup_av");
            hrs_sup8_av = rs.getString("hrs_sup8_av");
            hrs_sup_plus8_av = rs.getString("hrs_sup_plus8_av");
            totale_brute_av = rs.getString("totale_brute_av");
            switch (mois_av) {
                case 12:
                    String decs = "Décembre";
                    Object[] avantage_dec = {num_p_av, dt_now_av,decs, annee_av, periode_du_av, periode_au_av, idemnite_av,voca_piece_av,anciente, nbhres_sup_av,heure40, hrs_sup8_av, hrs_sup_plus8_av,val_heure40, totale_brute_av};
                    tab_av.addRow(avantage_dec);
                    break;
                case 11:
                    String novs = "Novembre";
                    Object[] avantage_nov = {num_p_av, dt_now_av,novs, annee_av, periode_du_av, periode_au_av, idemnite_av,voca_piece_av,anciente, nbhres_sup_av,heure40, hrs_sup8_av, hrs_sup_plus8_av,val_heure40, totale_brute_av};
                    tab_av.addRow(avantage_nov);
                    break;
                case 10:
                    String octs = "Octobre";
                    Object[] avantage_oct = {num_p_av, dt_now_av,octs, annee_av, periode_du_av, periode_au_av, idemnite_av,voca_piece_av,anciente, nbhres_sup_av,heure40, hrs_sup8_av, hrs_sup_plus8_av,val_heure40, totale_brute_av};
                    tab_av.addRow(avantage_oct);
                    break;
                case 9:
                    String septs = "Septembre";
                    Object[] avantage_sept = {num_p_av, dt_now_av, septs, annee_av, periode_du_av, periode_au_av, idemnite_av,voca_piece_av,anciente, nbhres_sup_av,heure40, hrs_sup8_av, hrs_sup_plus8_av,val_heure40, totale_brute_av};
                    tab_av.addRow(avantage_sept);
                    break;
                case 8:
                    String outs = "Août";
                    Object[] avantage_out = {num_p_av, dt_now_av,outs, annee_av, periode_du_av, periode_au_av, idemnite_av,voca_piece_av,anciente, nbhres_sup_av,heure40, hrs_sup8_av, hrs_sup_plus8_av,val_heure40, totale_brute_av};
                    tab_av.addRow(avantage_out);
                    break;
                case 7:
                    String jults = "Juillet";
                    Object[] avantage_jult = {num_p_av, dt_now_av,jults, annee_av, periode_du_av, periode_au_av, idemnite_av,voca_piece_av,anciente, nbhres_sup_av,heure40, hrs_sup8_av, hrs_sup_plus8_av,val_heure40, totale_brute_av};
                    tab_av.addRow(avantage_jult);
                    break;
                case 6:
                    String juins = "Juin";
                    Object[] avantage_juin = {num_p_av, dt_now_av,juins, annee_av, periode_du_av, periode_au_av, idemnite_av,voca_piece_av,anciente, nbhres_sup_av,heure40, hrs_sup8_av, hrs_sup_plus8_av,val_heure40, totale_brute_av};
                    tab_av.addRow(avantage_juin);
                    break;
                case 5:
                    String mais = "Mai";
                    Object[] avantage_mai = {num_p_av, dt_now_av,mais, annee_av, periode_du_av, periode_au_av, idemnite_av,voca_piece_av,anciente, nbhres_sup_av,heure40, hrs_sup8_av, hrs_sup_plus8_av,val_heure40, totale_brute_av};
                    tab_av.addRow(avantage_mai);
                    break;
                case 4:
                    String avrs = "Avril";
                    Object[] avantage_avr = {num_p_av, dt_now_av, avrs, annee_av, periode_du_av, periode_au_av, idemnite_av,voca_piece_av,anciente, nbhres_sup_av,heure40, hrs_sup8_av, hrs_sup_plus8_av,val_heure40, totale_brute_av};
                    tab_av.addRow(avantage_avr);
                    break;
                case 3:
                    String marss = "Mars";
                    Object[] avantage_mars = {num_p_av, dt_now_av,marss, annee_av, periode_du_av, periode_au_av, idemnite_av,voca_piece_av,anciente, nbhres_sup_av,heure40, hrs_sup8_av, hrs_sup_plus8_av,val_heure40, totale_brute_av};
                    tab_av.addRow(avantage_mars);
                    break;
                case 2:
                    String fevs = "Février";
                    Object[] avantage_fev = {num_p_av, dt_now_av,fevs, annee_av, periode_du_av, periode_au_av, idemnite_av,voca_piece_av,anciente, nbhres_sup_av,heure40, hrs_sup8_av, hrs_sup_plus8_av,val_heure40, totale_brute_av};
                    tab_av.addRow(avantage_fev);
                    break;
                case 1:
                    String javs = "Janvier";
                    Object[] avantage_jav = {num_p_av, dt_now_av, javs, annee_av, periode_du_av, periode_au_av, idemnite_av,voca_piece_av,anciente, nbhres_sup_av,heure40, hrs_sup8_av, hrs_sup_plus8_av,val_heure40, totale_brute_av};
                    tab_av.addRow(avantage_jav);
                    break;
            }
        }
    }
/////////////////////////////////////////Fonction réduction et configuration du table////////////////////////////////////////////////////////////////////

    public void config_tab_reduction() throws Exception {
        tab_reduc = new DefaultTableModel();
        tab_reduc.addColumn("Matricule");
        tab_reduc.addColumn("Date");
        tab_reduc.addColumn("Mois");
        tab_reduc.addColumn("Année");
        tab_reduc.addColumn("Début Période ");
        tab_reduc.addColumn("Fin Période");
        tab_reduc.addColumn("Cnaps");
        tab_reduc.addColumn("Osief");
        tab_reduc.addColumn("Irsa");
        tab_reduc.addColumn("Cession de salaire");
        tab_reduc.addColumn("Nouriture et logement");
        tab_reduc.addColumn("Autres reduction");
        tab_reduc.addColumn("Total déductions");
        JTableHeader tb = table_reduction.getTableHeader();
        tb.setBackground(new Color(0,102,153));
        tb.setForeground(Color.white);
        tb.setFont(new Font("tahome", Font.PLAIN, 12));
        table_reduction.setModel(tab_reduc);
        table_reduction.getColumnModel().getColumn(0).setPreferredWidth(65);
        table_reduction.getColumnModel().getColumn(0).setMaxWidth(65);
        table_reduction.getColumnModel().getColumn(0).setMinWidth(65);

        table_reduction.getColumnModel().getColumn(1).setPreferredWidth(90);
        table_reduction.getColumnModel().getColumn(1).setMaxWidth(90);
        table_reduction.getColumnModel().getColumn(1).setMinWidth(90);

        table_reduction.getColumnModel().getColumn(2).setPreferredWidth(72);
        table_reduction.getColumnModel().getColumn(2).setMaxWidth(72);
        table_reduction.getColumnModel().getColumn(2).setMinWidth(72);

        table_reduction.getColumnModel().getColumn(3).setPreferredWidth(40);
        table_reduction.getColumnModel().getColumn(3).setMaxWidth(40);
        table_reduction.getColumnModel().getColumn(3).setMinWidth(40);

        table_reduction.getColumnModel().getColumn(4).setPreferredWidth(84);
        table_reduction.getColumnModel().getColumn(4).setMaxWidth(84);
        table_reduction.getColumnModel().getColumn(4).setMinWidth(84);

        table_reduction.getColumnModel().getColumn(5).setPreferredWidth(70);
        table_reduction.getColumnModel().getColumn(5).setMaxWidth(70);
        table_reduction.getColumnModel().getColumn(5).setMinWidth(70);

        table_reduction.getColumnModel().getColumn(6).setPreferredWidth(90);
        table_reduction.getColumnModel().getColumn(6).setMaxWidth(90);
        table_reduction.getColumnModel().getColumn(6).setMinWidth(90);

        table_reduction.getColumnModel().getColumn(7).setPreferredWidth(90);
        table_reduction.getColumnModel().getColumn(7).setMaxWidth(90);
        table_reduction.getColumnModel().getColumn(7).setMinWidth(90);

        table_reduction.getColumnModel().getColumn(8).setPreferredWidth(100);
        table_reduction.getColumnModel().getColumn(8).setMaxWidth(100);
        table_reduction.getColumnModel().getColumn(8).setMinWidth(100);

        table_reduction.getColumnModel().getColumn(9).setPreferredWidth(110);
        table_reduction.getColumnModel().getColumn(9).setMaxWidth(110);
        table_reduction.getColumnModel().getColumn(9).setMinWidth(110);

        table_reduction.getColumnModel().getColumn(10).setPreferredWidth(110);
        table_reduction.getColumnModel().getColumn(10).setMaxWidth(110);
        table_reduction.getColumnModel().getColumn(10).setMinWidth(110);

        table_reduction.getColumnModel().getColumn(11).setPreferredWidth(110);
        table_reduction.getColumnModel().getColumn(11).setMaxWidth(110);
        table_reduction.getColumnModel().getColumn(11).setMinWidth(110);

        table_reduction.getColumnModel().getColumn(12).setPreferredWidth(150);
        table_reduction.getColumnModel().getColumn(12).setMaxWidth(150);
        table_reduction.getColumnModel().getColumn(12).setMinWidth(150);
        recuperer_val_reduc();
    }

    public void recuperer_val_reduc() throws SQLException {
        tab_reduc.setRowCount(0);//initialisation du table
        Date date_now = new Date();
        DateFormat dateFormat_now_mw = new SimpleDateFormat("yyyy");
        String dt_mw_now = dateFormat_now_mw.format(date_now);
        int mw_an_1 = Integer.parseInt(dt_mw_now);
        query = "select * from tab_reduc where annee_reduc='" + dt_an_now + "' order by mois_reduc ";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            num_p_reduc = rs.getString("num_perso");
            dt_now_reduc = rs.getString("dt_now_reduc");
            mois_reduc = rs.getInt("mois_reduc");
            annee_reduc = rs.getString("annee_reduc");
            pdu_reduc = rs.getString("periode_du_reduc");
            pau_reduc = rs.getString("periode_au_reduc");
            cnaps_reduc = rs.getString("cnaps_reduc");
            osief_reduc = rs.getString("osief_reduc");
            irsa_reduc = rs.getString("irsa_reduc");
            cess_sal_reduc = rs.getString("cess_sal_reduc");
            nouri_log_reduc = rs.getString("nouri_log_reduc");
            autres_reduc = rs.getString("autres_reduc");
            totale_reduc = rs.getString("totale_brute_reduc");
            switch (mois_reduc) {
                case 12:
                    String decr = "Décembre";
                    Object[] reduc_dec = {num_p_reduc, dt_now_reduc, decr, annee_reduc, pdu_reduc, pau_reduc, cnaps_reduc, osief_reduc, irsa_reduc, cess_sal_reduc, nouri_log_reduc, autres_reduc, totale_reduc};
                    tab_reduc.addRow(reduc_dec);
                    break;
                case 11:
                    String novr = "Novembre";
                    Object[] reduc_nov = {num_p_reduc, dt_now_reduc, novr, annee_reduc, pdu_reduc, pau_reduc, cnaps_reduc, osief_reduc, irsa_reduc, cess_sal_reduc, nouri_log_reduc, autres_reduc, totale_reduc};
                    tab_reduc.addRow(reduc_nov);
                    break;
                case 10:
                    String octr = "Octobre";
                    Object[] reduc_oct = {num_p_reduc, dt_now_reduc, octr, annee_reduc, pdu_reduc, pau_reduc, cnaps_reduc, osief_reduc, irsa_reduc, cess_sal_reduc, nouri_log_reduc, autres_reduc, totale_reduc};
                    tab_reduc.addRow(reduc_oct);
                    break;
                case 9:
                    String septr = "Septembre";
                    Object[] reduc_sept = {num_p_reduc, dt_now_reduc, septr, annee_reduc, pdu_reduc, pau_reduc, cnaps_reduc, osief_reduc, irsa_reduc, cess_sal_reduc, nouri_log_reduc, autres_reduc, totale_reduc};
                    tab_reduc.addRow(reduc_sept);
                    break;
                case 8:
                    String outr = "Août";
                    Object[] reduc_out = {num_p_reduc, dt_now_reduc, outr, annee_reduc, pdu_reduc, pau_reduc, cnaps_reduc, osief_reduc, irsa_reduc, cess_sal_reduc, nouri_log_reduc, autres_reduc, totale_reduc};
                    tab_reduc.addRow(reduc_out);
                    break;
                case 7:
                    String jultr = "Juillet";
                    Object[] reduc_jult = {num_p_reduc, dt_now_reduc, jultr, annee_reduc, pdu_reduc, pau_reduc, cnaps_reduc, osief_reduc, irsa_reduc, cess_sal_reduc, nouri_log_reduc, autres_reduc, totale_reduc};
                    tab_reduc.addRow(reduc_jult);
                    break;
                case 6:
                    String juinr = "Juin";
                    Object[] reduc_juin = {num_p_reduc, dt_now_reduc, juinr, annee_reduc, pdu_reduc, pau_reduc, cnaps_reduc, osief_reduc, irsa_reduc, cess_sal_reduc, nouri_log_reduc, autres_reduc, totale_reduc};
                    tab_reduc.addRow(reduc_juin);
                    break;
                case 5:
                    String mair = "Mai";
                    Object[] reduc_mai = {num_p_av, dt_now_av, mair, annee_av, periode_du_av, periode_au_av, idemnite_av, voca_piece_av, nbhres_sup_av, hrs_sup8_av, hrs_sup_plus8_av, totale_brute_av};
                    tab_reduc.addRow(reduc_mai);
                    break;
                case 4:
                    String avr = "Avril";
                    Object[] reduc_avr = {num_p_reduc, dt_now_reduc, avr, annee_reduc, pdu_reduc, pau_reduc, cnaps_reduc, osief_reduc, irsa_reduc, cess_sal_reduc, nouri_log_reduc, autres_reduc, totale_reduc};
                    tab_reduc.addRow(reduc_avr);
                    break;
                case 3:
                    String marsr = "Mars";
                    Object[] reduc_mars = {num_p_reduc, dt_now_reduc, marsr, annee_reduc, pdu_reduc, pau_reduc, cnaps_reduc, osief_reduc, irsa_reduc, cess_sal_reduc, nouri_log_reduc, autres_reduc, totale_reduc};
                    tab_reduc.addRow(reduc_mars);
                    break;
                case 2:
                    String fevr = "Février";
                    Object[] reduc_fev = {num_p_reduc, dt_now_reduc, fevr, annee_reduc, pdu_reduc, pau_reduc, cnaps_reduc, osief_reduc, irsa_reduc, cess_sal_reduc, nouri_log_reduc, autres_reduc, totale_reduc};
                    tab_reduc.addRow(reduc_fev);
                    break;
                case 1:
                    String javr = "Janvier";
                    Object[] reduc_jav = {num_p_reduc, dt_now_reduc, javr, annee_reduc, pdu_reduc, pau_reduc, cnaps_reduc, osief_reduc, irsa_reduc, cess_sal_reduc, nouri_log_reduc, autres_reduc, totale_reduc};
                    tab_reduc.addRow(reduc_jav);
                    break;
            }
        }
    }

//maka le primary key @table raha hanao (modification et suppression)*
    public String GetPrimaryKey(JTable tab) {
        String val;
        int row = tab.getSelectedRow();
        //int col = table_user_list.getSelectedColumn();
        if (row == -1) //JOptionPane.showMessageDialog(null,"Veillez selectionner un employe  ","Message",JOptionPane.WARNING_MESSAGE);    
        {
            return null;
        }
        val = tab.getValueAt(row, 0).toString();
        return val;
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
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_contrat = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txt_somme_vola = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn_actualiser_c = new javax.swing.JButton();
        btn_add_avance = new javax.swing.JButton();
        btn_mod_contrat = new javax.swing.JButton();
        btn_sup_contrat = new javax.swing.JButton();
        forme_perso = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_personnel = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_nbr_perso = new javax.swing.JLabel();
        btn_actualiser_p = new javax.swing.JButton();
        btn_new_perso = new javax.swing.JButton();
        btn_mod_p = new javax.swing.JButton();
        btn_sup_p = new javax.swing.JButton();
        btn_pdf_p = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_avance = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txt_ref_imprimer_avance = new javax.swing.JTextField();
        txt_dt_imprimer_avance = new javax.swing.JTextField();
        btn_actualiser_entrep1 = new javax.swing.JButton();
        btn_avance_add = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btn_sup_av = new javax.swing.JButton();
        btn_print_avance = new javax.swing.JButton();
        btn_print_avance_format = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        table_suplementaire = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        btn_actualiser_c1 = new javax.swing.JButton();
        btn_add_suple = new javax.swing.JButton();
        btn_mod_contrat1 = new javax.swing.JButton();
        btn_sup_suple = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        table_reduction = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btn_actualiser_entrep2 = new javax.swing.JButton();
        btn_reduc_add = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        btn_reduc = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_bulletin = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txt_ref_imprimer_bulletin = new javax.swing.JTextField();
        txt_dt_payer_du = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        btn_add_paie = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_accueil = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jTabbedPane1.setBackground(new java.awt.Color(0, 102, 153));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane1KeyPressed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        table_contrat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        table_contrat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_contrat.setRowHeight(25);
        jScrollPane3.setViewportView(table_contrat);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Liste des contrats");

        txt_somme_vola.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_somme_vola.setText("jLabel9");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Somme de salaire globase du personnel ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Ar");

        btn_actualiser_c.setBackground(new java.awt.Color(0, 102, 153));
        btn_actualiser_c.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_actualiser_c.setForeground(new java.awt.Color(255, 255, 255));
        btn_actualiser_c.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Synchronize_25px_1.png"))); // NOI18N
        btn_actualiser_c.setText("Actualiser");
        btn_actualiser_c.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_actualiser_c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualiser_cActionPerformed(evt);
            }
        });

        btn_add_avance.setBackground(new java.awt.Color(0, 102, 153));
        btn_add_avance.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_add_avance.setForeground(new java.awt.Color(255, 255, 255));
        btn_add_avance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Plus_25px.png"))); // NOI18N
        btn_add_avance.setText("Nouveau");
        btn_add_avance.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_add_avance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_avanceActionPerformed(evt);
            }
        });

        btn_mod_contrat.setBackground(new java.awt.Color(0, 102, 153));
        btn_mod_contrat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_mod_contrat.setForeground(new java.awt.Color(255, 255, 255));
        btn_mod_contrat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Edit_25px.png"))); // NOI18N
        btn_mod_contrat.setText("Modifier");
        btn_mod_contrat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_mod_contrat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mod_contratActionPerformed(evt);
            }
        });

        btn_sup_contrat.setBackground(new java.awt.Color(0, 102, 153));
        btn_sup_contrat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_sup_contrat.setForeground(new java.awt.Color(255, 255, 255));
        btn_sup_contrat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Delete_25px.png"))); // NOI18N
        btn_sup_contrat.setText("Supprimer");
        btn_sup_contrat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_sup_contrat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sup_contratActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txt_somme_vola, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_mod_contrat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_sup_contrat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(btn_add_avance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_actualiser_c, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_somme_vola)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_actualiser_c, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_add_avance, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_mod_contrat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_sup_contrat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("CONTRAT", jPanel4);

        forme_perso.setBackground(new java.awt.Color(255, 255, 255));

        table_personnel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        table_personnel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table_personnel.setIntercellSpacing(new java.awt.Dimension(2, 0));
        table_personnel.setRowHeight(25);
        table_personnel.setSurrendersFocusOnKeystroke(true);
        table_personnel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_personnelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_personnel);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Liste des personnels");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Nombre de personnel actif:");

        txt_nbr_perso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nbr_perso.setText("jLabel9");

        btn_actualiser_p.setBackground(new java.awt.Color(0, 102, 153));
        btn_actualiser_p.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_actualiser_p.setForeground(new java.awt.Color(255, 255, 255));
        btn_actualiser_p.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Synchronize_25px_1.png"))); // NOI18N
        btn_actualiser_p.setText("Actualiser");
        btn_actualiser_p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_actualiser_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualiser_pActionPerformed(evt);
            }
        });

        btn_new_perso.setBackground(new java.awt.Color(0, 102, 153));
        btn_new_perso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_new_perso.setForeground(new java.awt.Color(255, 255, 255));
        btn_new_perso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Plus_25px.png"))); // NOI18N
        btn_new_perso.setText("Nouveau");
        btn_new_perso.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_new_perso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_new_persoActionPerformed(evt);
            }
        });

        btn_mod_p.setBackground(new java.awt.Color(0, 102, 153));
        btn_mod_p.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_mod_p.setForeground(new java.awt.Color(255, 255, 255));
        btn_mod_p.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Edit_25px.png"))); // NOI18N
        btn_mod_p.setText("Modifier");
        btn_mod_p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_mod_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mod_pActionPerformed(evt);
            }
        });

        btn_sup_p.setBackground(new java.awt.Color(0, 102, 153));
        btn_sup_p.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_sup_p.setForeground(new java.awt.Color(255, 255, 255));
        btn_sup_p.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Delete_25px.png"))); // NOI18N
        btn_sup_p.setText("Supprimer");
        btn_sup_p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_sup_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sup_pActionPerformed(evt);
            }
        });

        btn_pdf_p.setBackground(new java.awt.Color(0, 102, 153));
        btn_pdf_p.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_pdf_p.setForeground(new java.awt.Color(255, 255, 255));
        btn_pdf_p.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        btn_pdf_p.setText("Imprimer tous");
        btn_pdf_p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_pdf_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pdf_pActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout forme_persoLayout = new javax.swing.GroupLayout(forme_perso);
        forme_perso.setLayout(forme_persoLayout);
        forme_persoLayout.setHorizontalGroup(
            forme_persoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(forme_persoLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nbr_perso)
                .addGap(205, 205, 205))
            .addGroup(forme_persoLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(forme_persoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_actualiser_p, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_new_perso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_mod_p, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_sup_p, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(btn_pdf_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        forme_persoLayout.setVerticalGroup(
            forme_persoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, forme_persoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(forme_persoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(txt_nbr_perso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(forme_persoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(forme_persoLayout.createSequentialGroup()
                        .addComponent(btn_actualiser_p, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_new_perso, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_mod_p, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_sup_p, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_pdf_p, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("PERSONNEL", forme_perso);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        table_avance.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        table_avance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_avance.setRowHeight(25);
        table_avance.setRowMargin(0);
        table_avance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_avanceMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table_avance);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Avanaces sur salaire");

        txt_ref_imprimer_avance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ref_imprimer_avanceActionPerformed(evt);
            }
        });

        btn_actualiser_entrep1.setBackground(new java.awt.Color(0, 102, 153));
        btn_actualiser_entrep1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_actualiser_entrep1.setForeground(new java.awt.Color(255, 255, 255));
        btn_actualiser_entrep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Synchronize_25px_1.png"))); // NOI18N
        btn_actualiser_entrep1.setText("Actualiser");
        btn_actualiser_entrep1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_actualiser_entrep1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualiser_entrep1ActionPerformed(evt);
            }
        });

        btn_avance_add.setBackground(new java.awt.Color(0, 102, 153));
        btn_avance_add.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_avance_add.setForeground(new java.awt.Color(255, 255, 255));
        btn_avance_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Plus_25px.png"))); // NOI18N
        btn_avance_add.setText("Nouveau");
        btn_avance_add.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_avance_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_avance_addActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 102, 153));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Edit_25px.png"))); // NOI18N
        jButton8.setText("Modifier");
        jButton8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        btn_sup_av.setBackground(new java.awt.Color(0, 102, 153));
        btn_sup_av.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_sup_av.setForeground(new java.awt.Color(255, 255, 255));
        btn_sup_av.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Delete_25px.png"))); // NOI18N
        btn_sup_av.setText("Supprimer");
        btn_sup_av.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_sup_av.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sup_avActionPerformed(evt);
            }
        });

        btn_print_avance.setBackground(new java.awt.Color(0, 102, 153));
        btn_print_avance.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_print_avance.setForeground(new java.awt.Color(255, 255, 255));
        btn_print_avance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        btn_print_avance.setText("Imprimer");
        btn_print_avance.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_print_avance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print_avanceActionPerformed(evt);
            }
        });

        btn_print_avance_format.setBackground(new java.awt.Color(0, 102, 153));
        btn_print_avance_format.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_print_avance_format.setForeground(new java.awt.Color(255, 255, 255));
        btn_print_avance_format.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        btn_print_avance_format.setText("Format");
        btn_print_avance_format.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_print_avance_format.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print_avance_formatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_dt_imprimer_avance, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_ref_imprimer_avance, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_actualiser_entrep1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_avance_add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_sup_av, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_print_avance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_print_avance_format, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_ref_imprimer_avance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dt_imprimer_avance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_actualiser_entrep1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_avance_add, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_sup_av, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_print_avance, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_print_avance_format, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("AVANCE SUR SALAIRE", jPanel5);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        table_suplementaire.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        table_suplementaire.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_suplementaire.setRowHeight(25);
        table_suplementaire.setRowMargin(0);
        jScrollPane6.setViewportView(table_suplementaire);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Avantages");

        btn_actualiser_c1.setBackground(new java.awt.Color(0, 102, 153));
        btn_actualiser_c1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_actualiser_c1.setForeground(new java.awt.Color(255, 255, 255));
        btn_actualiser_c1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Synchronize_25px_1.png"))); // NOI18N
        btn_actualiser_c1.setText("Actualiser");
        btn_actualiser_c1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_actualiser_c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualiser_c1ActionPerformed(evt);
            }
        });

        btn_add_suple.setBackground(new java.awt.Color(0, 102, 153));
        btn_add_suple.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_add_suple.setForeground(new java.awt.Color(255, 255, 255));
        btn_add_suple.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Plus_25px.png"))); // NOI18N
        btn_add_suple.setText("Nouveau");
        btn_add_suple.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_add_suple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_supleActionPerformed(evt);
            }
        });

        btn_mod_contrat1.setBackground(new java.awt.Color(0, 102, 153));
        btn_mod_contrat1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_mod_contrat1.setForeground(new java.awt.Color(255, 255, 255));
        btn_mod_contrat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Edit_25px.png"))); // NOI18N
        btn_mod_contrat1.setText("Modifier");
        btn_mod_contrat1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_mod_contrat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mod_contrat1ActionPerformed(evt);
            }
        });

        btn_sup_suple.setBackground(new java.awt.Color(0, 102, 153));
        btn_sup_suple.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_sup_suple.setForeground(new java.awt.Color(255, 255, 255));
        btn_sup_suple.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Delete_25px.png"))); // NOI18N
        btn_sup_suple.setText("Supprimer");
        btn_sup_suple.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_sup_suple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sup_supleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 1221, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_add_suple, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_mod_contrat1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_sup_suple, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(btn_actualiser_c1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 155, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_actualiser_c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_add_suple, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_mod_contrat1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_sup_suple, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(431, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addContainerGap(42, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("CALCUL AVANTAGE", jPanel7);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        table_reduction.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        table_reduction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_reduction.setRowHeight(25);
        jScrollPane7.setViewportView(table_reduction);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Déductions réglementaires");

        btn_actualiser_entrep2.setBackground(new java.awt.Color(0, 102, 153));
        btn_actualiser_entrep2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_actualiser_entrep2.setForeground(new java.awt.Color(255, 255, 255));
        btn_actualiser_entrep2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Synchronize_25px_1.png"))); // NOI18N
        btn_actualiser_entrep2.setText("Actualiser");
        btn_actualiser_entrep2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_actualiser_entrep2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualiser_entrep2ActionPerformed(evt);
            }
        });

        btn_reduc_add.setBackground(new java.awt.Color(0, 102, 153));
        btn_reduc_add.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_reduc_add.setForeground(new java.awt.Color(255, 255, 255));
        btn_reduc_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Plus_25px.png"))); // NOI18N
        btn_reduc_add.setText("Nouveau");
        btn_reduc_add.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_reduc_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reduc_addActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(0, 102, 153));
        jButton10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Edit_25px.png"))); // NOI18N
        jButton10.setText("Modifier");
        jButton10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        btn_reduc.setBackground(new java.awt.Color(0, 102, 153));
        btn_reduc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_reduc.setForeground(new java.awt.Color(255, 255, 255));
        btn_reduc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Delete_25px.png"))); // NOI18N
        btn_reduc.setText("Supprimer");
        btn_reduc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_reduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reducActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_reduc_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_actualiser_entrep2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_reduc, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btn_actualiser_entrep2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_reduc_add, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_reduc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("CALCULE DE DEDUCTION", jPanel12);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        table_bulletin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        table_bulletin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_bulletin.setRowHeight(25);
        table_bulletin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_bulletinMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(table_bulletin);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Salaire mensuel / Bulletin de la paie");

        jButton27.setBackground(new java.awt.Color(0, 102, 153));
        jButton27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Synchronize_25px_1.png"))); // NOI18N
        jButton27.setText("Actualiser");
        jButton27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        btn_add_paie.setBackground(new java.awt.Color(0, 102, 153));
        btn_add_paie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_add_paie.setForeground(new java.awt.Color(255, 255, 255));
        btn_add_paie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Plus_25px.png"))); // NOI18N
        btn_add_paie.setText("Nouveau");
        btn_add_paie.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_add_paie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_paieActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(0, 102, 153));
        jButton9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Edit_25px.png"))); // NOI18N
        jButton9.setText("Modifier");
        jButton9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(0, 102, 153));
        jButton24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Delete_25px.png"))); // NOI18N
        jButton24.setText("Supprimer");
        jButton24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton26.setBackground(new java.awt.Color(0, 102, 153));
        jButton26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Print_25px.png"))); // NOI18N
        jButton26.setText("Imprimer");
        jButton26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(btn_add_paie, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(jButton26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_dt_payer_du, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(txt_ref_imprimer_bulletin, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_ref_imprimer_bulletin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_dt_payer_du, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_add_paie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton26)))
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("PAIEMENT DU SALAIRE MENSUEL", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1327, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 662, Short.MAX_VALUE)
        );

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
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8_Sales_Performance_25px.png"))); // NOI18N
        jMenuItem3.setText("Déclarations");
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

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon_48/icons8_Maintenance_25px.png"))); // NOI18N
        jMenu2.setText("Configuration");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon_48/icons8_Edit_File_25px.png"))); // NOI18N
        jMenuItem6.setText("Change les variables");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_new_persoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_new_persoActionPerformed
        personnel_add pnew = new personnel_add();
        pnew.setVisible(Boolean.TRUE);
    }//GEN-LAST:event_btn_new_persoActionPerformed

    private void btn_add_avanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_avanceActionPerformed
        contrat_add ca = new contrat_add();
        ca.setVisible(Boolean.TRUE);
    }//GEN-LAST:event_btn_add_avanceActionPerformed

    private void btn_add_paieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_paieActionPerformed
        bulletin_add pa = new bulletin_add();
        pa.setVisible(Boolean.TRUE);
    }//GEN-LAST:event_btn_add_paieActionPerformed

    private void btn_avance_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_avance_addActionPerformed
        avance_add av = new avance_add();
        av.setVisible(Boolean.TRUE);
    }//GEN-LAST:event_btn_avance_addActionPerformed
    private void compteperso() throws SQLException {
        query = "select count(*) as nbp from tab_perso";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            String p = rs.getString("nbp");
            txt_nbr_perso.setText(p);
        } else {

        }
    }

    private void compteperso_vola() throws SQLException {
        query = "select sum(tab_contrat.sal_base_contrat) as salaire from tab_contrat";
        st = maCon.Obtenirconnexion().createStatement();
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            String somme = rs.getString("salaire");
            //txt_nbr_perso.setText(somme);
            txt_somme_vola.setText(somme);
        } else {

        }
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            actualistion_table();
            // teste.setText(dt_mois_now);
        } catch (Exception ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btn_actualiser_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualiser_pActionPerformed
        try {
            actualistion_table();
            compteperso();
        } catch (Exception ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_actualiser_pActionPerformed

    private void btn_sup_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sup_pActionPerformed
        String supp = GetPrimaryKey(table_personnel);
        if (supp != null) {
            requet_personnel rqtp = new requet_personnel();
            int infos = JOptionPane.showConfirmDialog(this, "Voullez-vous vraiment  supprimer ce personnel ?", "Message", JOptionPane.YES_NO_OPTION);
            if (infos == JOptionPane.YES_OPTION) {
                try {
                    rqtp.supprimer(supp);
                    actualistion_table();
                    JOptionPane.showMessageDialog(null, "Les informations ont été bien supprimées", "Message", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    config_tab_perso();
                } catch (Exception ex) {
                    Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veillez selectionner un employe  ", "Message", JOptionPane.WARNING_MESSAGE);
            try {
                config_tab_perso();
            } catch (Exception ex) {
                Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btn_sup_pActionPerformed

    private void btn_mod_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mod_pActionPerformed
        int index_p = table_personnel.getSelectedRow();
        TableModel mod_p = table_personnel.getModel();
        String sexe = mod_p.getValueAt(index_p, 2).toString();
        if (sexe.equals("H") == true) {
            String num_p = mod_p.getValueAt(index_p, 0).toString();
            String nom_p = mod_p.getValueAt(index_p, 1).toString();
            // String sexe = mod_p.getValueAt(index_p, 2).toString();
            SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            Date dt_p = null;
            try {
                dt_p = dtf.parse(mod_p.getValueAt(index_p, 3).toString());
            } catch (ParseException ex) {
                Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
            }
            //String situ_p = mod.getValueAt(index_p, 4).toString();
            String nbr_p = mod_p.getValueAt(index_p, 5).toString();
            String adrs_p = mod_p.getValueAt(index_p, 6).toString();
            String tel_p = mod_p.getValueAt(index_p, 7).toString();
            personnel_mod pm = new personnel_mod();
            pm.setVisible(true);
            pm.pack();
            //pm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            pm.txt_num_mod_p.setText(num_p);
            pm.txt_nom_pre_mod_p.setText(nom_p);
            pm.txt_dt_emb_mod_p.setDate(dt_p);
            pm.txt_nbr_enf_mod_p.setText(nbr_p);
            pm.txt_adrs_mod_p.setText(adrs_p);
            pm.txt_tel_mod_p.setText(tel_p);
            pm.txt_ref_mod_p.setText(num_p);
            pm.txt_homme.doClick();
//
        } else {
            String num_p = mod_p.getValueAt(index_p, 0).toString();
            String nom_p = mod_p.getValueAt(index_p, 1).toString();
            //String sexe = mod_p.getValueAt(index_p, 2).toString();
            SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            Date dt_p = null;
            try {
                dt_p = dtf.parse(mod_p.getValueAt(index_p, 3).toString());
            } catch (ParseException ex) {
                Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
            }
            //String situ_p = mod.getValueAt(index_p, 4).toString();
            String nbr_p = mod_p.getValueAt(index_p, 5).toString();
            String adrs_p = mod_p.getValueAt(index_p, 6).toString();
            String tel_p = mod_p.getValueAt(index_p, 7).toString();
            personnel_mod pm = new personnel_mod();
            pm.setVisible(true);
            pm.pack();
            pm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            pm.txt_num_mod_p.setText(num_p);
            pm.txt_nom_pre_mod_p.setText(nom_p);
            pm.txt_dt_emb_mod_p.setDate(dt_p);
            pm.txt_nbr_enf_mod_p.setText(nbr_p);
            pm.txt_adrs_mod_p.setText(adrs_p);
            pm.txt_tel_mod_p.setText(tel_p);
            pm.txt_ref_mod_p.setText(num_p);
            pm.txt_femme.doClick();
        }

    }//GEN-LAST:event_btn_mod_pActionPerformed
//impression personnel_all fonction

    private void impression_all(){
        /* Connexion con = new Connexion();
    Connection conn = con.obtenirconnexion();
    JasperReport report = null;
    String path = "src\\impression\\personnel_liste.jasper";
    report = (JasperReport) JRLoader.loadObjectFromFile(path);
    JasperPrint jprint = JasperFillManager.fillReport(path, null,conn);
    JasperViewer view = new JasperViewer(jprint,false);
    view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    view.setVisible(true);
       / Connection con = DriverManager.getConnection("jdbc:mysql://localhost/payement", "root", "");
        JasperReport JAsp = JasperCompileManager.compileReport("src\\com\\impression\\personnel_liste.jrxml");
        // C:\Users\VONIANAHITRA\Documents\NetBeansProjects\Application\src\impression // "src\\impression\\personnel_liste.jrxml"
        JasperPrint jp = JasperFillManager.fillReport(JAsp, null, con);
        JasperViewer.viewReport(jp, false);*/
    }

    private void btn_pdf_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pdf_pActionPerformed
            
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/payement","root","");
            JasperReport JAsp=JasperCompileManager.compileReport("C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Gestion_paie\\src\\impression\\personnel.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(JAsp, null, con);
            JasperViewer.viewReport(jp,false);
//       // String liste = "LISTE DES PERSONNELS";
//        String non = "Personnel.pdf";
//        int nbr = 8;
//        pdfPersonnel(non, liste, nbr);
        } catch (JRException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_pdf_pActionPerformed
    public void pdfPersonnel(String nomFichier, String Titre, int nbColonne) {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(nomFichier));
            document.open();
            //contenu
            //image sur l'entete
            /* Image img_ligne=Image.getInstance("C:\\Users\\PEACE\\Documents\\gestionBancaireJSP\\gestionBancaireJSP-war\\web\\image\\img_ligne.png");
            Paragraph img = new Paragraph(new Chunk(img_ligne, 0,0));
            document.add(img);*/
            Date actuelle = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
            String date_now = dateFormat.format(actuelle);
            //creation d'une font
            com.itextpdf.text.Font fontGray = FontFactory.getFont("Arial", 10, BaseColor.GRAY);
            com.itextpdf.text.Font fontBlack = FontFactory.getFont("Arial", 7, BaseColor.BLACK);
            com.itextpdf.text.Font fontBlue = FontFactory.getFont("Verdana", 12, BaseColor.BLUE);
            com.itextpdf.text.Font blanc = FontFactory.getFont("Arial", 7, BaseColor.BLACK);
            com.itextpdf.text.Font fontNoire = FontFactory.getFont("Arial", 8, BaseColor.BLACK);

            document.add(new Paragraph("\n"));//saut de ligne
            Paragraph titre1 = new Paragraph("                                                                                                                 AUTO-TRACTOR FIANARANTSOA SARL", fontGray);
            titre1.setAlignment(1);
            document.add(titre1);
            document.add(new Phrase(Chunk.NEWLINE));
            document.add(new Phrase(Chunk.ALIGN_LEFT));
            document.add(new Phrase("AUTO-TRACTOR \nBP:1462 ZOROZOROANA-FIANARANTSOA\n FIANARANTSOA 301 \n Tél:020 75 509 01 - 034 75 509 01",fontNoire));
           // document.add(new Paragraph(date_now));
            document.add(new Phrase(Chunk.ALIGN_RIGHT));
            // document.add(new Paragraph("\n"));//saut de ligne
            document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------"));
            //document.add(new Paragraph("\n"));//saut de ligne
            //Paragraph titre = new Paragraph(Titre.toUpperCase(), fontBlue);
           // titre.setAlignment(1);
           // document.add(titre);
            document.add(new Paragraph("                                            **********LISTE DE PERSONNELS**********"));
            document.add(new Paragraph("\n")); 
            //table
            PdfPTable tablePdf = new PdfPTable(nbColonne);
            tablePdf.setWidthPercentage(100);
            tablePdf.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
            PdfPCell cel0 = new PdfPCell(new Paragraph("Im", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            PdfPCell cel1 = new PdfPCell(new Paragraph("Nom et Prénom", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            PdfPCell cel2 = new PdfPCell(new Paragraph("Sexe", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            PdfPCell cel3 = new PdfPCell(new Paragraph("Date d'Embauche", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            PdfPCell cel4 = new PdfPCell(new Paragraph("Etat-Civil", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            PdfPCell cel5 = new PdfPCell(new Paragraph("Enfant", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            PdfPCell cel6 = new PdfPCell(new Paragraph("Adresse", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            PdfPCell cel7 = new PdfPCell(new Paragraph("Téléphone", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            //PdfPCell cel7 = new PdfPCell(new Paragraph("Im",FontFactory.getFont("Arial", 7, BaseColor.BLACK)));
            // PdfPCell cel8 = new PdfPCell(new Paragraph("Im",FontFactory.getFont("Arial", 7, BaseColor.BLACK)));
            //ajout du table header
            //tableau Header
            tablePdf.addCell(cel0).setBackgroundColor(BaseColor.ORANGE);
            tablePdf.addCell(cel1).setBackgroundColor(BaseColor.ORANGE);
            tablePdf.addCell(cel2).setBackgroundColor(BaseColor.ORANGE);
            tablePdf.addCell(cel3).setBackgroundColor(BaseColor.ORANGE);
            tablePdf.addCell(cel4).setBackgroundColor(BaseColor.ORANGE);
            tablePdf.addCell(cel5).setBackgroundColor(BaseColor.ORANGE);
            tablePdf.addCell(cel6).setBackgroundColor(BaseColor.ORANGE);
            tablePdf.addCell(cel7).setBackgroundColor(BaseColor.ORANGE);
            //tablePdf.addCell(cel7);
            //PdfPCell colonne =new PdfPCell(); 
            //  colonne.setHorizontalAlignment(1);
            //colonne.setBackgroundColor(BaseColor.GRAY);
            // colonne.setBackgroundColor(BaseColor.GRAY);
            // tablePdf.addCell(colonne);

            Connexion connect = new Connexion();
            ResultSet rs = null;
            Connection con = connect.obtenirconnexion();
            try {
                Statement stat = con.createStatement();

                String sql = "";
                sql = "select * from tab_perso order by num_perso";
                rs = stat.executeQuery(sql);
                while (rs.next()) {

                    tablePdf.addCell(new Phrase(rs.getString(1), blanc));
                    tablePdf.addCell(new Phrase(rs.getString(2), blanc));
                    tablePdf.addCell(new Phrase(rs.getString(3), blanc));
                    tablePdf.addCell(new Phrase(rs.getString(4), blanc));
                    tablePdf.addCell(new Phrase(rs.getString(5), blanc));
                    tablePdf.addCell(new Phrase(rs.getString(6), blanc));
                    tablePdf.addCell(new Phrase(rs.getString(7), blanc));
                    tablePdf.addCell(new Phrase(rs.getString(8), blanc));

                }

            } catch (SQLException e) {
            }
            document.add(tablePdf);
            document.close();
            //JOptionPane.showMessageDialog(null, "Exportation avec succes !");

            //ouverture du fichier PDF crée
            try {
                Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler" + "C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Application\\personnel.pdf");
                Desktop.getDesktop().open(new File("C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Application\\Personnel.pdf"));

            } catch (RuntimeException e) {
                // JOptionPane.showMessageDialog(null, "Impossible d'ouvrir le fichier! "+e);
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"erreur: "+e);
        }
    }
    private void jTabbedPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane1KeyPressed

    }//GEN-LAST:event_jTabbedPane1KeyPressed

    private void btn_actualiser_cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualiser_cActionPerformed
        try {
            actualistion_table();
        } catch (Exception ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_actualiser_cActionPerformed

    private void btn_sup_contratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sup_contratActionPerformed
        String supc = GetPrimaryKey(table_contrat);
        if (supc != null) {
            requet_contrat rqtc = new requet_contrat();
            int infos = JOptionPane.showConfirmDialog(this, "Voullez-vous vraiment supprimer contrat de cet personnel ?", "Message", JOptionPane.YES_NO_OPTION);
            if (infos == JOptionPane.YES_OPTION) {
                try {
                    rqtc.supprimer(supc);
                    requet_personnel rqtp = new requet_personnel();
                    rqtp.supprimer(supc);
                    actualistion_table();
                    JOptionPane.showMessageDialog(null, "Les informations ont été bien supprimées ", "Message", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veillez selectionner un contrat du personnel  ", "Message", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_sup_contratActionPerformed

    private void table_personnelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_personnelMouseClicked

    }//GEN-LAST:event_table_personnelMouseClicked

    private void btn_mod_contratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mod_contratActionPerformed
       
        int index_c = table_contrat.getSelectedRow();
        TableModel mod_c = table_contrat.getModel();
        
       
//        String enf = mod_p.getValueAt(index_p,5).toString();
        String type_cont_c = mod_c.getValueAt(index_c, 10).toString();
        if (type_cont_c.equals("CDD") == true) {
            try {
                String num_perso_c = mod_c.getValueAt(index_c, 0).toString();
                SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
                Date dt_e = null;
                Date dt_s =null;
                Date dt_emb=null;
               
                dt_emb = dtf.parse(mod_c.getValueAt(index_c,4).toString());
                try {
                    dt_e = dtf.parse(mod_c.getValueAt(index_c, 5).toString());
                } catch (ParseException ex) {
                    Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    dt_s = dtf.parse(mod_c.getValueAt(index_c, 6).toString());
                } catch (ParseException ex) {
                    Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
                }
                String nom_pre = mod_c.getValueAt(index_c, 1).toString();
                String clas_c = mod_c.getValueAt(index_c, 7).toString();
                String sal_base = mod_c.getValueAt(index_c, 9).toString();
                String indi_c = mod_c.getValueAt(index_c, 8).toString();
//            String lieu_c = mod_c.getValueAt(index_c, 9).toString();
//            String type_paie_c = mod_c.getValueAt(index_c, 10).toString();
//String type_cont_cf = mod_c.getValueAt(index_c, 11).toString();
contrat_mod cm = new contrat_mod();
cm.setVisible(true);
cm.pack();
cm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
cm.txt_sal_base_contrat_mod.setText(sal_base);
cm.txt_indice_contrat_mod.setText(indi_c);
cm.txt_num_perso_mod.setText(num_perso_c);
cm.txt_ref_contrat_mod.setText(num_perso_c);
cm.txt_nom_pre_perso_mod.setText(nom_pre);
cm.txt_date_emb_perso_mod.setDate(dt_emb);
cm.txt_dt_entr_contrat_mod.setDate(dt_e);
cm.txt_dt_sor_contrat_mod.setDate(dt_s);
cm.txt_clasoifi_contrat_mod.setToolTipText(clas_c);
cm.txt_cdd_contrat_mod.doClick();
//cm.txt_enfant.setText(enf);
            } catch (ParseException ex) {
                Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String num_perso_c = mod_c.getValueAt(index_c, 0).toString();
            SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            Date dt_e = null;
            Date dt_s = null;
            Date dt_emb=null;
//            String enf = mod_p.getValueAt(index_p,5).toString();
            try {
          dt_emb = dtf.parse(mod_c.getValueAt(index_c, 4).toString());
          dt_e = dtf.parse(mod_c.getValueAt(index_c, 5).toString());
        } catch (ParseException ex) {
        Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
            String indi_c = mod_c.getValueAt(index_c, 8).toString();
            String sal_b_c = mod_c.getValueAt(index_c, 9).toString();
            String nom_pre = mod_c.getValueAt(index_c, 1).toString();
            //String sal_base = mod_c.getValueAt(index_c, 9).toString();
           // String indi_c = mod_c.getValueAt(index_c, 8).toString();
            //String type_cont_cf = mod_c.getValueAt(index_c, 11).toString();
            contrat_mod cm = new contrat_mod();
            cm.setVisible(true);
            cm.setVisible(true);
            cm.pack();
            cm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            cm.txt_num_perso_mod.setText(num_perso_c);
            cm.txt_ref_contrat_mod.setText(num_perso_c);
            cm.txt_dt_entr_contrat_mod.setDate(dt_e);
            //cm.txt_dt_sor_contrat_mod.setDate(dt_e);
            cm.txt_dt_sor_contrat_mod.setEnabled(false);
            cm.txt_nom_pre_perso_mod.setText(nom_pre);
           // cm.txt_clasoifi_contrat_mod.setToolTipText(clas_c);
            cm.txt_indice_contrat_mod.setText(indi_c);
            cm.txt_date_emb_perso_mod.setDate(dt_emb);
            cm.txt_sal_base_contrat_mod.setText(sal_b_c);
            cm.txt_cdi_contrat_mod.doClick();
//            cm.txt_enfant.setText(enf);
            //  .?
    }//GEN-LAST:event_btn_mod_contratActionPerformed
    }
    private void btn_actualiser_entrep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualiser_entrep1ActionPerformed
        try {
            actualistion_table();
        } catch (Exception ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_actualiser_entrep1ActionPerformed

    private void btn_add_supleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_supleActionPerformed
        suplementaire_add av = new suplementaire_add();
        av.setVisible(true);
    }//GEN-LAST:event_btn_add_supleActionPerformed

    private void btn_mod_contrat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mod_contrat1ActionPerformed
        int index_sup = table_suplementaire.getSelectedRow();
        TableModel mod_sup = table_suplementaire.getModel();
        String num_sup = mod_sup.getValueAt(index_sup, 0).toString();
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        Date dt_du = null;
        Date dt_au = null;
        try {
            dt_du = dtf.parse(mod_sup.getValueAt(index_sup, 4).toString());
            dt_au = dtf.parse(mod_sup.getValueAt(index_sup, 5).toString());
        } catch (ParseException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
        String idem_sup = mod_sup.getValueAt(index_sup, 6).toString();
        String voca_sup = mod_sup.getValueAt(index_sup, 7).toString();
        String anciente_sup = mod_sup.getValueAt(index_sup, 8).toString();
        String heure30_50 = mod_sup.getValueAt(index_sup, 9).toString();
        String heure40 = mod_sup.getValueAt(index_sup, 10).toString();
        String maj30 = mod_sup.getValueAt(index_sup, 11).toString();
        String maj40 = mod_sup.getValueAt(index_sup, 12).toString();
        String maj50 = mod_sup.getValueAt(index_sup, 13).toString();
        String total_brutes = mod_sup.getValueAt(index_sup, 14).toString();
        suplementaire_mod sm = new suplementaire_mod();
        sm.setVisible(true);
        double le30 =Double.parseDouble(maj30);
        double le40 =Double.parseDouble(maj40);
        double le50 =Double.parseDouble(maj50);
        double total_maj=le30+le40+le50;
        String totalmaja =Double.toString(total_maj);
        sm.pack();
        sm.txt_num_perso_sup_mod.setText(num_sup);
        sm.txt_num_perso_sup2_mod.setText(num_sup);
        sm.txt_ref_mod_sup.setText(num_sup);
        sm.txt_pdu_sup_mod.setDate(dt_du);
        sm.txt_pau_sup_mod.setDate(dt_au);
        sm.txt_idemnite_sup_mod.setText(idem_sup);
        sm.txt_vacation_sup_mod.setText(voca_sup);
        sm.txt_heure40_mod.setText(heure40);
        sm.txt_nbrhrs_sup_mod.setText(heure30_50);
        sm.txt_anciente_mod.setText(anciente_sup);
        sm.txt_val_8_plus_sup_mod.setText(maj50);
        sm.txt_val_8_sup_mod.setText(maj30);
        sm.txt_plus40_mod.setText(maj40);
        sm.txt_val_totale_sup_mod.setText(totalmaja);
        sm.txt_totale_brute_sup_mod.setText(total_brutes);
//        sm.txt_nom_perso_sup_mod.
        //sm.
//        sm.txt_num_perso_sup_mod.setText(num_sup);
//        sm.txt_ref_mod_sup.setText(num_sup);
//        sm.txt_pdu_sup_mod.setDate(dt_du);
//        sm.txt_pau_sup_mod.setDate(dt_au);
//        sm.txt_idemnite_sup_mod.setText(idem_sup);
//        sm.txt_vacation_sup_mod.setText(voca_sup);
        //sm.txt_nbrhrs_sup_mod.setText(hbr_sup);
//        sm.txt_val_8_sup_mod.setText("0.00");
//        sm.txt_val_8_plus_sup_mod.setText("0.00");
//        sm.txt_totale_brute_sup_mod.setText("0.00");
//        sm.txt_num_perso_sup2_mod.setText(num_sup);

    }//GEN-LAST:event_btn_mod_contrat1ActionPerformed

    private void btn_sup_supleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sup_supleActionPerformed
//String supsupl = GetPrimaryKey(table_suplementaire);
        int index_sup_avc = table_suplementaire.getSelectedRow();
        TableModel mod_avc = table_suplementaire.getModel();
        String num = mod_avc.getValueAt(index_sup_avc, 0).toString();
        String dt_ajt = mod_avc.getValueAt(index_sup_avc, 1).toString();
        if (num != null) {
            requet_suplementaire rqtp = new requet_suplementaire();
            int infos = JOptionPane.showConfirmDialog(this, "Voullez-vous vraiment supprimer  ?", "Message", JOptionPane.YES_NO_OPTION);
            if (infos == JOptionPane.YES_OPTION) {
                try {
                    rqtp.supprimer(num, dt_ajt);
                    actualistion_table();
                    JOptionPane.showMessageDialog(null, "Les informations ont été  bien enregistrées", "Message", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veillez selectionner dans la liste  ", "Message", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_sup_supleActionPerformed

    private void btn_actualiser_c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualiser_c1ActionPerformed
        try {
            actualistion_table();
        } catch (Exception ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_actualiser_c1ActionPerformed

    private void btn_reduc_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reduc_addActionPerformed
        reduction_add radd = new reduction_add();
        radd.setVisible(true);
    }//GEN-LAST:event_btn_reduc_addActionPerformed

    private void btn_actualiser_entrep2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualiser_entrep2ActionPerformed
        try {
            actualistion_table();
        } catch (Exception ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_actualiser_entrep2ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        try {
            actualistion_table();
        } catch (Exception ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void btn_sup_avActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sup_avActionPerformed
        // String supavance = GetPrimaryKey(table_avance);
        int index_sup_avc = table_avance.getSelectedRow();
        TableModel mod_avc = table_avance.getModel();
        String num = mod_avc.getValueAt(index_sup_avc, 0).toString();
        String dt_ajt = mod_avc.getValueAt(index_sup_avc, 1).toString();
        if (num != null) {
            requet_avance rqtp = new requet_avance();
            int infos = JOptionPane.showConfirmDialog(this, "Voullez-vous vraiment supprimer  ?", "Message", JOptionPane.YES_NO_OPTION);
            if (infos == JOptionPane.YES_OPTION) {
                try {
                    rqtp.supprimer(num, dt_ajt);
                    actualistion_table();
                    JOptionPane.showMessageDialog(null, "Les informations ont été bien supprimées", "Message", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veillez selectionner dans la liste  ", "Message", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_sup_avActionPerformed

    private void btn_reducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reducActionPerformed
        //String supreduc = GetPrimaryKey(table_reduction);
        int index_sup_avc = table_reduction.getSelectedRow();
        TableModel mod_avc = table_reduction.getModel();
        String num = mod_avc.getValueAt(index_sup_avc, 0).toString();
        String dtajt = mod_avc.getValueAt(index_sup_avc, 1).toString();
        String mois = mod_avc.getValueAt(index_sup_avc, 4).toString();
//        int mw =Integer.parseInt(mois);
        String annee = mod_avc.getValueAt(index_sup_avc, 3).toString();
        int an =Integer.parseInt(annee);
        if (num != null) {
            requet_reduction rqtp = new requet_reduction();
            requet_irsa rqtirsa = new requet_irsa();
            int infos = JOptionPane.showConfirmDialog(this, "Voullez-vous vraiment supprimer  ?", "Message", JOptionPane.YES_NO_OPTION);
            if (infos == JOptionPane.YES_OPTION) {
                try {
                    rqtirsa.supprimer(num,mois,an);
                    rqtp.supprimer(num, dtajt);
                   
                    actualistion_table();
                    JOptionPane.showMessageDialog(null, "Les informations on été bien supprimées", "Message", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veillez selectionner dans la liste  ", "Message", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_reducActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int index_a = table_avance.getSelectedRow();
        TableModel mod_a = table_avance.getModel();
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        Date dt_e = null;
        Date dt_s = null;
        try {
            dt_e = dtf.parse(mod_a.getValueAt(index_a, 4).toString());
        } catch (ParseException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dt_s = dtf.parse(mod_a.getValueAt(index_a, 5).toString());
        } catch (ParseException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
        String num_a = mod_a.getValueAt(index_a, 0).toString();
        String clas_c = mod_a.getValueAt(index_a, 1).toString();
        String nbm_c = mod_a.getValueAt(index_a, 2).toString();
        String mois_avance = mod_a.getValueAt(index_a, 3).toString();
        //String indi_c = mod_a.getValueAt(index_a, 4).toString();
        String netpaye = mod_a.getValueAt(index_a, 6).toString();
        String reste_sal = mod_a.getValueAt(index_a, 7).toString();
        avance_mod am = new avance_mod();
        am.setVisible(true);
        am.pack();
        // am.txt_netpay_avance_mod.setText(nbj_c);
//        am.txt_mois_avance_mod.setMonth();
        am.txt_num_perso_avcance_mod.setText(num_a);
        am.txt_pdu_avance_mod.setDate(dt_e);
        am.txt_pau_avance_mod.setDate(dt_s);
        am.txt_netpay_avance_mod.setText(netpaye);
        am.txt_reste_avance_mod.setText(reste_sal);
        am.txt_num_perso_avcance_ref.setText(num_a);
        am.txt_som_reti_avance_mod.setText(netpaye);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        //String supp = GetPrimaryKey(table_bulletin);
        int index_sup_avc = table_bulletin.getSelectedRow();
        TableModel mod_avc = table_bulletin.getModel();
        String num = mod_avc.getValueAt(index_sup_avc, 0).toString();
        String dt_ajt = mod_avc.getValueAt(index_sup_avc, 1).toString();
        if (num != null) {
            int infos = JOptionPane.showConfirmDialog(this, "Voullez-vous vraiment  supprimer ce bulletin?", "Message", JOptionPane.YES_NO_OPTION);
            if (infos == JOptionPane.YES_OPTION) {
                try {
                    requet_bulletin rqtb = new requet_bulletin();
                    rqtb.supprimer(num, dt_ajt);
                    requet_print_bulletin rqtp = new requet_print_bulletin();
                    rqtp.supprimer(num, dt_ajt);
                    config_tab_bulletin();
                    JOptionPane.showMessageDialog(null, "Les informations ont été bien supprimées ", "Message", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    config_tab_perso();
                } catch (Exception ex) {
                    Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veillez selectionner l'un de ces bulletin  ", "Message", JOptionPane.WARNING_MESSAGE);
            try {
                config_tab_bulletin();
            } catch (Exception ex) {
                Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void menu_accueilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_accueilMouseClicked

    }//GEN-LAST:event_menu_accueilMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        accueil_principale acp = new accueil_principale();
        acp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            int index_reduc = table_reduction.getSelectedRow();
            TableModel mod_reduc = table_reduction.getModel();
            String num_reduc = mod_reduc.getValueAt(index_reduc, 0).toString();
            SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            Date dt_du = null;
            Date dt_au = null;
            dt_au = dtf.parse(mod_reduc.getValueAt(index_reduc, 4).toString());
            dt_du = dtf.parse(mod_reduc.getValueAt(index_reduc, 5).toString());
            String cnaps = mod_reduc.getValueAt(index_reduc, 6).toString();
            String osief = mod_reduc.getValueAt(index_reduc, 7).toString();
            String cess_sal = mod_reduc.getValueAt(index_reduc, 8).toString();
            String nouri_log = mod_reduc.getValueAt(index_reduc, 9).toString();
            String autre_reduc = mod_reduc.getValueAt(index_reduc, 10).toString();
            String totale_brute = mod_reduc.getValueAt(index_reduc, 11).toString();
            reduction_mod rm = new reduction_mod();
            rm.setVisible(true);
            rm.pack();
            //rm.
            rm.txt_pdu_reduc_mod.setDate(dt_du);
            rm.txt_pau_reduc_mod.setDate(dt_au);
            rm.txt_num_perso_reduc_mod.setText(num_reduc);
            // rm.txt_ref_mod_sup.
            rm.txt_ref_mod_reduc.setText(num_reduc);

        } catch (ParseException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            int index_bul = table_bulletin.getSelectedRow();
            TableModel mod_bul = table_bulletin.getModel();
            String num_bul = mod_bul.getValueAt(index_bul, 0).toString();
            SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            Date dt_du = null;
            Date dt_au = null;
            dt_du = dtf.parse(mod_bul.getValueAt(index_bul, 4).toString());
            dt_au = dtf.parse(mod_bul.getValueAt(index_bul, 5).toString());
            String total_reduc = mod_bul.getValueAt(index_bul, 6).toString();
            String total_sup = mod_bul.getValueAt(index_bul, 7).toString();
            String net_pay = mod_bul.getValueAt(index_bul, 8).toString();
            bulletin_mod bul = new bulletin_mod();
            bul.setVisible(true);
            bul.pack();
            bul.txt_pdu_mod_b.setDate(dt_du);
            bul.txt_pau_mod_b.setDate(dt_au);
            bul.txt_num_perso_mod_b.setText(num_bul);
            bul.txt_num_perso_mod_ref_b.setText(num_bul);

        } catch (ParseException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        authentificaiton auth = new authentificaiton();
        auth.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       declaration sal_g = new declaration();
        sal_g.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed
public void PdfAvance(String dest) throws FileNotFoundException, DocumentException, IOException, SQLException, SQLException{
       Document document = new Document(PageSize.A5);
                
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
       
            com.itextpdf.text.Font fontGray = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Connexion connect2=new Connexion();
            ResultSet rs2=null;
            Connection con2=connect2.obtenirconnexion();
            Statement stat=con2.createStatement();
            String ref = txt_ref_imprimer_avance.getText();
            String dt_ref = txt_dt_imprimer_avance.getText();
            String sql="";
            sql="select * from tab_avance where num_perso='"+ref+"' and dt_now_avance='"+dt_ref+"'";
            //and dt_now_avance='"+dt_ref+"'
            rs=stat.executeQuery(sql);
            while(rs.next()){
                PdfPTable table = new PdfPTable(1);
        PdfPCell cell = new PdfPCell();
        Paragraph p;
        //numéro
        String num = rs.getString("num_perso");
        String nom = rs.getString("nom_pre_avance");
        String dt_now = rs.getString("dt_now_avance");
        String pdu = rs.getString("periode_du_avance");
        String pau = rs.getString("periode_au_avance");
        String num_plus = "Nom et prénom du travailleur: "+nom+"";
        p = new Paragraph(12,num_plus,fontGray);
        cell.addElement(p);
        //Matricule
        String nom_plus = "Numéro :  "+num+"";
        p = new Paragraph(12,nom_plus,fontGray);
        cell.addElement(p);
        //Pour la date now
        String dt_now_plus = "Paye du :  "+dt_now+"   Période du:  "+pdu+"   au:  "+pau+"";
        p = new Paragraph(12,dt_now_plus,fontGray);
        cell.addElement(p);
        cell.setBorder(20);
        table.addCell(cell); 
        
       // table.addCell(cell);
        document.add(table);
            }
        document.add(new Paragraph("______________________________________________________________________________________________",fontGray));

        document.close();
        
        
      Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler"+"C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Application\\Avance.pdf");
      Desktop.getDesktop().open(new File("C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Application\\Avance.pdf"));
}
    private void btn_print_avanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print_avanceActionPerformed
       
        try {
           // String num = txt_ref_imprimer_bulletin.getText();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/payement","root","");
            final String  JRXML = "C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Gestion_paie\\src\\impression\\avance.jrxml";
            // String sql = "select * from print_bulletin where matricule like '%"+num+"%'";
            Map param = new HashMap();
            param.put("AVANCE_NUMERO",txt_ref_imprimer_avance.getText());
            param.put("DATE_AVANCE",txt_dt_imprimer_avance.getText());
            requet_impression print = new requet_impression();
            print.displayReport(JRXML, param, con);
            
            /* try {
            String liste = "AVANCE DU SLAIRE";
            String non = "Avance.pdf";
            int nbr = 10;
            PdfAvance(non, liste, nbr);
            String nomFichier = "Avance.pdf";
            String Titre = "AVANCE DU SALAIRE";
            int nbColone = 9;
            PdfAvance(nomFichier);
            /*
            
            try{
            String a = txt_ref_imprimer_avance.getText();
            Connection con = DriverManager.getConnection(Constante.url, Constante.user, Constante.passwd);
            JasperDesign jd=JRXmlLoader.load("src\\impression\\avance_salaire.jrxml");
            String sql="select * from tab_avance where num_perso='"+a+"'";
            JRDesignQuery newQuery= new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr=JasperCompileManager.compileReport(jd);
            JasperPrint jp=JasperFillManager.fillReport(jr,null, con);
            JasperViewer.viewReport(jp);
            }catch(Exception e){
            
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
    }//GEN-LAST:event_btn_print_avanceActionPerformed

    
    private void txt_ref_imprimer_avanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ref_imprimer_avanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ref_imprimer_avanceActionPerformed

    private void table_avanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_avanceMouseClicked
        int index_a = table_avance.getSelectedRow();
        TableModel mod_a = table_avance.getModel();
        String num_a = mod_a.getValueAt(index_a, 0).toString();
        String dt_a = mod_a.getValueAt(index_a, 1).toString();
        txt_ref_imprimer_avance.setText(num_a);
        txt_dt_imprimer_avance.setText(dt_a);
    }//GEN-LAST:event_table_avanceMouseClicked

    private void table_bulletinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_bulletinMouseClicked
       int index_b = table_bulletin.getSelectedRow();
        TableModel mod_b = table_bulletin.getModel();
        String num_b = mod_b.getValueAt(index_b, 0).toString();
        String dt_payer_du = mod_b.getValueAt(index_b, 1).toString();
        txt_ref_imprimer_bulletin.setText(num_b);
        txt_dt_payer_du.setText(dt_payer_du);
    }//GEN-LAST:event_table_bulletinMouseClicked
 public void PdfPayement(String dest) throws DocumentException, SQLException, FileNotFoundException, IOException{
       Document document = new Document(PageSize.A3);
                
         PdfWriter.getInstance(document, new FileOutputStream(dest));
         document.open();
         com.itextpdf.text.Font fontGray = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
         com.itextpdf.text.Font font = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
         com.itextpdf.text.Font fontNoire = FontFactory.getFont("Arial", 12, BaseColor.LIGHT_GRAY);
         com.itextpdf.text.Font fontbul = FontFactory.getFont("Arial", 20, BaseColor.DARK_GRAY);
         PdfPTable table2 = new PdfPTable(2);
         table2.setWidthPercentage(109);
         //PdfPCell cell2 = new PdfPCell();
         Paragraph p2;
         PdfPCell cell11 = new PdfPCell(new Paragraph("                                        AUTO-TRACTOR \n                     BP:1462 ZOROZOROANA-FIANARANTSOA\n                     FIANARANTSOA 301 \n                     Tél:020 75 509 01 - 034 75 509 01",fontNoire));
         PdfPCell cell22 = new PdfPCell(new Paragraph("                                        AUTO-TRACTOR \n                     BP:1462 ZOROZOROANA-FIANARANTSOA\n                     FIANARANTSOA 301 \n                     Tél:020 75 509 01 - 034 75 509 01",fontNoire));
         cell11.setBorder(0);
         cell22.setBorder(0);
         table2.addCell(cell11);  
         table2.addCell(cell22); 
         //farany 
         document.add(table2);
         document.add(new Paragraph("\n"));
         document.add(new Paragraph("Bulletin de Paye                                                  Bulletin de Paye",fontbul)); 
         document.add(new Paragraph("\n\n"));      
            Connexion connect2=new Connexion();
            ResultSet rs2=null;
            Connection con2=connect2.obtenirconnexion();
            Statement stat=con2.createStatement();
           // String ref = txt_ref_imprimer_avance.getText();
            String ref =txt_ref_imprimer_bulletin.getText();
            String dt_ref = txt_dt_imprimer_avance.getText();
            String sql="";
           // sql="select * from tab_avance where num_perso='"+ref+"' and dt_now_avance='"+dt_ref+"'";
            //and d
             // sql ="select * from print_bulletin where num_perso='"+ref+"'";
           // rs=stat.executeQuery(sql);
             //if(rs.next()){
        PdfPTable table = new PdfPTable(2);
        //PdfPTable table1 = new PdfPTable(2);
        
        PdfPTable tabled = new PdfPTable(3);
        PdfPTable tableg = new PdfPTable(3);
        
       // table1.addCell(tableg);
        table.addCell(tabled);
        
        PdfPCell  cellg = new PdfPCell(tableg);
        PdfPCell  celld = new PdfPCell(tabled);
        
        table.addCell(cellg);
        table.addCell(celld);
        table.setWidthPercentage(109);
        document.add(table);
        document.close(); 
        Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler"+"C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Gestion_paie\\Payement.pdf");
        Desktop.getDesktop().open(new File("C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Gestion_paie\\Payement.pdf"));       
    }
    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
   
        try {
            //String num = txt_ref_imprimer_bulletin.getText();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/payement","root","");
            final String  JRXML = "C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Gestion_paie\\src\\impression\\bulletin.jrxml";
            // String sql = "select * from print_bulletin where matricule like '%"+num+"%'";
            Map param = new HashMap();
            param.put("NUMERO",txt_ref_imprimer_bulletin.getText());
            param.put("DATE_BULLETIN",txt_dt_payer_du.getText());
            requet_impression print = new requet_impression();
            print.displayReport(JRXML, param, con);
            //this.add(viewer);
            /*  String nomFichier = "Payement.pdf";
            String Titre = "PAYEMENT DU SALAIRE";
            int nbColone = 10;
            try {
            PdfPayement(nomFichier);
            } catch (DocumentException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jButton26ActionPerformed

    private void btn_print_avance_formatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print_avance_formatActionPerformed
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/payement","root","");
            JasperReport JAsp=JasperCompileManager.compileReport("C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Gestion_paie\\src\\impression\\format_avance.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(JAsp, null, con);
            JasperViewer.viewReport(jp,false);        
        } catch (JRException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_print_avance_formatActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
    variable var = new variable();
    var.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
      globale_salaire sa_g =new  globale_salaire();
      sa_g.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed
 public void pdfSociete(String nom, String liste, int nb){
     try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(nom));
            document.open();
            //contenu
            //image sur l'entete
            /* Image img_ligne=Image.getInstance("C:\\Users\\PEACE\\Documents\\gestionBancaireJSP\\gestionBancaireJSP-war\\web\\image\\img_ligne.png");
            Paragraph img = new Paragraph(new Chunk(img_ligne, 0,0));
            document.add(img);*/
            Date actuelle = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
            String date_now = dateFormat.format(actuelle);
            //creation d'une font
            com.itextpdf.text.Font fontGray = FontFactory.getFont("Arial", 10, BaseColor.GRAY);
            com.itextpdf.text.Font fontBlack = FontFactory.getFont("Arial", 7, BaseColor.BLACK);
            com.itextpdf.text.Font fontBlue = FontFactory.getFont("Verdana", 12, BaseColor.BLUE);
            com.itextpdf.text.Font blanc = FontFactory.getFont("Arial", 7, BaseColor.BLACK);
            com.itextpdf.text.Font fontNoire = FontFactory.getFont("Arial", 8, BaseColor.BLACK);

            document.add(new Paragraph("\n"));//saut de ligne
            Paragraph titre1 = new Paragraph("                                                                                                                 AUTO-TRACTOR FIANARANTSOA SARL", fontGray);
            titre1.setAlignment(1);
            document.add(titre1);
            document.add(new Phrase(Chunk.NEWLINE));
            document.add(new Phrase(Chunk.ALIGN_LEFT));
            document.add(new Phrase("AUTO-TRACTOR \nBP:1462 ZOROZOROANA-FIANARANTSOA\n FIANARANTSOA 301 \n Tél:020 75 509 01 - 034 75 509 01",fontNoire));
           // document.add(new Paragraph(date_now));
            document.add(new Phrase(Chunk.ALIGN_RIGHT));
            // document.add(new Paragraph("\n"));//saut de ligne
            document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------"));
            //document.add(new Paragraph("\n"));//saut de ligne
            //Paragraph titre = new Paragraph(Titre.toUpperCase(), fontBlue);
           // titre.setAlignment(1);
           // document.add(titre);
            document.add(new Paragraph("                                            **********LISTE DES SOCIETES**********"));
            document.add(new Paragraph("\n")); 
            //table
            PdfPTable tablePdf = new PdfPTable(nb);
            tablePdf.setWidthPercentage(100);
            tablePdf.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
            PdfPCell cel0 = new PdfPCell(new Paragraph("Numéro Entreprise", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            PdfPCell cel1 = new PdfPCell(new Paragraph("Designation", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            PdfPCell cel2 = new PdfPCell(new Paragraph("Adresse", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            PdfPCell cel3 = new PdfPCell(new Paragraph("Téléphone", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            PdfPCell cel4 = new PdfPCell(new Paragraph("Email", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
            PdfPCell cel5 = new PdfPCell(new Paragraph("Forme Juridique", FontFactory.getFont("Arial", 8, BaseColor.BLACK)));
       
            //PdfPCell cel7 = new PdfPCell(new Paragraph("Im",FontFactory.getFont("Arial", 7, BaseColor.BLACK)));
            // PdfPCell cel8 = new PdfPCell(new Paragraph("Im",FontFactory.getFont("Arial", 7, BaseColor.BLACK)));
            //ajout du table header
            //tableau Header
            tablePdf.addCell(cel0).setBackgroundColor(BaseColor.ORANGE);
            tablePdf.addCell(cel1).setBackgroundColor(BaseColor.ORANGE);
            tablePdf.addCell(cel2).setBackgroundColor(BaseColor.ORANGE);
            tablePdf.addCell(cel3).setBackgroundColor(BaseColor.ORANGE);
            tablePdf.addCell(cel4).setBackgroundColor(BaseColor.ORANGE);
            tablePdf.addCell(cel5).setBackgroundColor(BaseColor.ORANGE);
            //tablePdf.addCell(cel7);
            //PdfPCell colonne =new PdfPCell(); 
            //  colonne.setHorizontalAlignment(1);
            //colonne.setBackgroundColor(BaseColor.GRAY);
            // colonne.setBackgroundColor(BaseColor.GRAY);
            // tablePdf.addCell(colonne);

            Connexion connect = new Connexion();
            ResultSet rs = null;
            Connection con = connect.obtenirconnexion();
            try {
                Statement stat = con.createStatement();

                String sql = "";
                sql = "select * from tab_entrep order by num_entrep";
                rs = stat.executeQuery(sql);
                while (rs.next()) {

                    tablePdf.addCell(new Phrase(rs.getString(1), blanc));
                    tablePdf.addCell(new Phrase(rs.getString(2), blanc));
                    tablePdf.addCell(new Phrase(rs.getString(3), blanc));
                    tablePdf.addCell(new Phrase(rs.getString(4), blanc));
                    tablePdf.addCell(new Phrase(rs.getString(5), blanc));
                    tablePdf.addCell(new Phrase(rs.getString(6), blanc));
                }

            } catch (SQLException e) {
            }
            document.add(tablePdf);
            document.close();
            //JOptionPane.showMessageDialog(null, "Exportation avec succes !");

            //ouverture du fichier PDF crée
            try {
                Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler" + "C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Application\\Societe.pdf");
                Desktop.getDesktop().open(new File("C:\\Users\\VONIANAHITRA\\Documents\\NetBeansProjects\\Application\\Societe.pdf"));

            } catch (RuntimeException e) {
                // JOptionPane.showMessageDialog(null, "Impossible d'ouvrir le fichier! "+e);
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"erreur: "+e);
        }
 }
   
    public void actualistion_table() throws Exception {
        config_tab_avance();
        config_tab_contrat();
//        config_tab_entrep();
        config_tab_perso();
        config_tab_avantage();
        config_tab_reduction();
        config_tab_bulletin();
        compteperso();
        compteperso_vola();
    }

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
            java.util.logging.Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(perso_entrep_contrat_avance_forme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new perso_entrep_contrat_avance_forme().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualiser_c;
    private javax.swing.JButton btn_actualiser_c1;
    private javax.swing.JButton btn_actualiser_entrep1;
    private javax.swing.JButton btn_actualiser_entrep2;
    public javax.swing.JButton btn_actualiser_p;
    private javax.swing.JButton btn_add_avance;
    private javax.swing.JButton btn_add_paie;
    private javax.swing.JButton btn_add_suple;
    private javax.swing.JButton btn_avance_add;
    private javax.swing.JButton btn_mod_contrat;
    private javax.swing.JButton btn_mod_contrat1;
    private javax.swing.JButton btn_mod_p;
    private javax.swing.JButton btn_new_perso;
    private javax.swing.JButton btn_pdf_p;
    private javax.swing.JButton btn_print_avance;
    private javax.swing.JButton btn_print_avance_format;
    private javax.swing.JButton btn_reduc;
    private javax.swing.JButton btn_reduc_add;
    private javax.swing.JButton btn_sup_av;
    private javax.swing.JButton btn_sup_contrat;
    private javax.swing.JButton btn_sup_p;
    private javax.swing.JButton btn_sup_suple;
    private javax.swing.JPanel forme_perso;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu menu_accueil;
    private javax.swing.JTable table_avance;
    private javax.swing.JTable table_bulletin;
    private javax.swing.JTable table_contrat;
    public javax.swing.JTable table_personnel;
    private javax.swing.JTable table_reduction;
    private javax.swing.JTable table_suplementaire;
    private javax.swing.JTextField txt_dt_imprimer_avance;
    private javax.swing.JTextField txt_dt_payer_du;
    private javax.swing.JLabel txt_nbr_perso;
    private javax.swing.JTextField txt_ref_imprimer_avance;
    private javax.swing.JTextField txt_ref_imprimer_bulletin;
    private javax.swing.JLabel txt_somme_vola;
    // End of variables declaration//GEN-END:variables
}
