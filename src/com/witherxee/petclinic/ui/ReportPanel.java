package com.witherxee.petclinic.ui;

import com.witherxee.petclinic.util.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ReportPanel extends JPanel {
    private static final Color BACKGROUND = new Color(235,241,247);
    private static final Color NAVY = new Color(32,58,86);
    private static final Color ACCENT = new Color(55,107,154);

    public ReportPanel() {
        initComponents();
        buildInterface();
    }

    // NetBeans form compatibility; controls are created programmatically.
    private void initComponents() {
        setLayout(new BorderLayout());
    }

    private void buildInterface() {
        setBackground(BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(24,24,24,24));

        JPanel card=new JPanel(new BorderLayout(12,12));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(24,24,24,24));

        JLabel title=new JLabel("Clinic Reports");
        title.setFont(new Font("SansSerif",Font.BOLD,24));
        title.setForeground(NAVY);

        JLabel description=new JLabel("<html>Generate a clinic summary containing customer, pet, appointment,<br>"
                +"veterinarian, treatment and payment information.</html>");
        description.setFont(new Font("SansSerif",Font.PLAIN,14));

        JButton generate=new JButton("Generate Clinic Summary");
        generate.setBackground(ACCENT);
        generate.setForeground(Color.WHITE);
        generate.setFocusPainted(false);
        generate.addActionListener(e->generateReport());

        JPanel center=new JPanel(new FlowLayout(FlowLayout.LEFT,0,18));
        center.setOpaque(false);
        center.add(description);

        card.add(title,BorderLayout.NORTH);
        card.add(center,BorderLayout.CENTER);
        card.add(generate,BorderLayout.SOUTH);
        add(card,BorderLayout.NORTH);
    }

    private void generateReport() {
        String resource="/com/witherxee/petclinic/reports/clinic_summary.jasper";
        String jrxml="/com/witherxee/petclinic/reports/clinic_summary.jrxml";

        try (Connection connection=DBConnection.getConnection()) {
            JasperReport report;
            try (InputStream compiled=getClass().getResourceAsStream(resource)) {
                if(compiled!=null) {
                    report=(JasperReport)JRLoader.loadObject(compiled);
                } else {
                    try(InputStream source=getClass().getResourceAsStream(jrxml)) {
                        if(source==null) {
                            throw new IllegalStateException("Report template not found: "+jrxml);
                        }
                        report=JasperCompileManager.compileReport(source);
                    }
                }
            }

            Map<String,Object> params=new HashMap<>();
            params.put("REPORT_TITLE","Pet Clinic - Treatment and Payment Summary");

            JasperPrint print=JasperFillManager.fillReport(report,params,connection);
            if(print.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(this,"No records found to display.");
                return;
            }
            JasperViewer viewer=new JasperViewer(print,false);
            viewer.setTitle("Pet Clinic Report");
            viewer.setVisible(true);

        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Could not generate report:\n"+ex.getMessage()
                    +"\n\nCheck that JasperReports and its dependencies are in Libraries.",
                    "Report Error",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
