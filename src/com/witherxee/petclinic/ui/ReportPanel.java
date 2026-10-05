package com.witherxee.petclinic.ui;

import com.witherxee.petclinic.util.DBConnection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Clinic report screen configured for JasperReports 7.0.3.
 *
 * IMPORTANT:
 * 1. Convert clinic_summary.jrxml with Jaspersoft Studio 7.x before running.
 * 2. Delete any old clinic_summary.jasper compiled with JasperReports 6.x.
 * 3. Add JasperReports 7.0.3 and the jasperreports-viewer 7.0.3 artifact,
 *    plus their transitive dependencies, to the project's runtime classpath.
 */
public class ReportPanel extends JPanel {
    private static final Color BACKGROUND = new Color(235, 241, 247);
    private static final Color NAVY = new Color(32, 58, 86);
    private static final Color ACCENT = new Color(55, 107, 154);

    private JButton generateButton;
    private JLabel statusLabel;

    public ReportPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        JPanel card = new JPanel(new BorderLayout(12, 16));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(205, 216, 226)),
                BorderFactory.createEmptyBorder(24, 24, 24, 24)));

        JLabel title = new JLabel("Clinic Reports");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(NAVY);

        JLabel description = new JLabel("<html>Generate a clinic summary containing customer, pet, appointment,<br>"
                + "veterinarian, treatment and payment information.</html>");
        description.setFont(new Font("SansSerif", Font.PLAIN, 14));

        generateButton = new JButton("Generate Clinic Summary");
        generateButton.setBackground(ACCENT);
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.addActionListener(e -> generateReport());

        statusLabel = new JLabel(" ");
        statusLabel.setForeground(NAVY);

        JPanel center = new JPanel(new GridLayout(2, 1, 0, 10));
        center.setOpaque(false);
        center.add(description);
        center.add(statusLabel);

        card.add(title, BorderLayout.NORTH);
        card.add(center, BorderLayout.CENTER);
        card.add(generateButton, BorderLayout.SOUTH);
        add(card, BorderLayout.NORTH);
    }

    private void generateReport() {
        generateButton.setEnabled(false);
        statusLabel.setText("Generating report...");

        // Use only a JasperReports 7-compatible compiled report, if one exists.
        final String compiledResource = "/com/witherxee/petclinic/reports/clinic_summary.jasper";
        final String sourceResource = "/com/witherxee/petclinic/reports/clinic_summary.jrxml";

        try (Connection connection = DBConnection.getConnection()) {
            JasperReport report;

            try (InputStream compiled = getClass().getResourceAsStream(compiledResource)) {
                if (compiled != null) {
                    // This .jasper must have been compiled with JasperReports 7.x.
                    report = (JasperReport) JRLoader.loadObject(compiled);
                } else {
                    try (InputStream source = getClass().getResourceAsStream(sourceResource)) {
                        if (source == null) {
                            throw new IllegalStateException("Report template not found: " + sourceResource);
                        }
                        // This source JRXML must first be converted to JasperReports 7 format
                        // with Jaspersoft Studio 7 or newer.
                        report = JasperCompileManager.compileReport(source);
                    }
                }
            }

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("REPORT_TITLE", "Pet Clinic - Treatment and Payment Summary");

            JasperPrint print = JasperFillManager.fillReport(report, parameters, connection);
            if (print.getPages().isEmpty()) {
                statusLabel.setText("No records found to display.");
                JOptionPane.showMessageDialog(this, "No records found to display.",
                        "Clinic Report", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // JasperViewer is provided by the jasperreports-viewer 7.0.3 artifact.
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setTitle("Pet Clinic Report");
            viewer.setVisible(true);
            statusLabel.setText("Report generated successfully.");

        } catch (Exception ex) {
            statusLabel.setText("Report generation failed.");
            JOptionPane.showMessageDialog(this,
                    "Could not generate the report:\n" + ex.getMessage()
                    + "\n\nVerify that JasperReports 7.0.3 and its runtime dependencies, "
                    + "including jasperreports-viewer 7.0.3, are on the classpath. "
                    + "Also verify that clinic_summary.jrxml was converted using Jaspersoft Studio 7.x.",
                    "Report Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            generateButton.setEnabled(true);
        }
    }
}
