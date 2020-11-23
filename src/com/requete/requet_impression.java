/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requete;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author VONIANAHITRA
 */
public class requet_impression {
    private static final long serialVersionUID = 1L;
    protected JRViewer viewer = null;
    protected javax.swing.JPanel pane;
public void displayReport(String JRXML,Map param, Connection con) throws JRException, FileNotFoundException {
        //  Load .jrxml File Report
        InputStream inputStream = new FileInputStream(new File(JRXML));
        //  Compile .jrxml File Report
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, con);
        JasperViewer.viewReport(jasperPrint,false); 
        //  View the Report
        this.pane.add(viewer);
        
        //this.setVisible(true);
        //  Export the Report File
        /*OutputStream outputStream = new FileOutputStream(new File("c:/"+ title + ".pdf"));
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);*/
    }   
}
