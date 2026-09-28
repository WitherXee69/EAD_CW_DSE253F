
package com.witherxee.petclinic.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.function.Consumer;

public class DashboardPanel extends JPanel {

    private final Consumer<String> onNavigate;

    private final Color BACKGROUND =
            new Color(235, 241, 247);

    private final Color NAVY =
            new Color(32, 58, 86);

    private final Color ACCENT =
            new Color(55, 107, 154);

    private final Color MUTED =
            new Color(90, 108, 125);

    public DashboardPanel(Consumer<String> onNavigate) {

        this.onNavigate = onNavigate;

        setLayout(new BorderLayout(0, 25));
        setBackground(BACKGROUND);

        setBorder(new EmptyBorder(30, 35, 30, 35));

        // =========================
        // HEADER
        // =========================

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel brand = new JPanel(new GridLayout(2, 1));
        brand.setOpaque(false);

        JLabel appName = new JLabel("PetCare");
        appName.setFont(
                new Font("SansSerif", Font.BOLD, 30));
        appName.setForeground(NAVY);

        JLabel subtitle = new JLabel(
                "Veterinary Management System");
        subtitle.setFont(
                new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(MUTED);

        brand.add(appName);
        brand.add(subtitle);

        JLabel roleLabel = new JLabel("ADMIN");
        roleLabel.setFont(
                new Font("SansSerif", Font.BOLD, 13));
        roleLabel.setForeground(ACCENT);

        header.add(brand, BorderLayout.WEST);
        header.add(roleLabel, BorderLayout.EAST);

        // =========================
        // WELCOME SECTION
        // =========================

        JPanel welcome = new JPanel();
        welcome.setLayout(new BoxLayout(
                welcome, BoxLayout.Y_AXIS));

        welcome.setBackground(Color.WHITE);
        welcome.setBorder(
                new EmptyBorder(25, 25, 25, 25));

        JLabel welcomeTitle = new JLabel(
                "Welcome to PetCare");
        welcomeTitle.setFont(
                new Font("SansSerif", Font.BOLD, 25));
        welcomeTitle.setForeground(NAVY);

        JLabel welcomeDescription = new JLabel(
                "Manage your clinic, patients and appointments.");
        welcomeDescription.setFont(
                new Font("SansSerif", Font.PLAIN, 15));
        welcomeDescription.setForeground(MUTED);

        welcome.add(welcomeTitle);
        welcome.add(Box.createVerticalStrut(8));
        welcome.add(welcomeDescription);

        // =========================
        // MANAGEMENT CARDS
        // =========================

        JPanel cards = new JPanel(new GridLayout(
                2, 3, 18, 18));

        cards.setOpaque(false);

        cards.add(createCard(
                "Customers",
                "Manage pet owners",
                "CUSTOMERS",
                "01"));

        cards.add(createCard(
                "Pets",
                "Manage registered pets",
                "PETS",
                "02"));

        cards.add(createCard(
                "Veterinarians",
                "Manage clinic staff",
                "VETERINARIANS",
                "03"));

        cards.add(createCard(
                "Appointments",
                "Schedule and manage visits",
                "APPOINTMENTS",
                "04"));

        cards.add(createCard(
                "Treatments",
                "Manage treatments and payments",
                "TREATMENTS",
                "05"));

        cards.add(createCard(
                "Reports",
                "View and export Jasper reports",
                "REPORTS",
                "06"));

        // =========================
        // FOOTER
        // =========================

        JLabel footer = new JLabel(
                "PetCare Management System");
        footer.setFont(
                new Font("SansSerif", Font.PLAIN, 12));
        footer.setForeground(MUTED);

        // =========================
        // LAYOUT
        // =========================

        JPanel center = new JPanel(new BorderLayout(0, 20));
        center.setOpaque(false);

        center.add(welcome, BorderLayout.NORTH);
        center.add(cards, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    // =========================
    // CARD CREATION
    // =========================

    private JButton createCard(
            String title,
            String description,
            String screen,
            String number) {

        JButton card = new JButton();

        card.setLayout(new BorderLayout(0, 12));
        card.setBackground(Color.WHITE);
        card.setFocusPainted(false);
        card.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR));

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(218, 226, 235)),
                        new EmptyBorder(20, 20, 20, 20)));

        // Card number
        JLabel numberLabel = new JLabel(number);
        numberLabel.setFont(
                new Font("SansSerif", Font.BOLD, 14));
        numberLabel.setForeground(ACCENT);

        // Card title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(
                new Font("SansSerif", Font.BOLD, 19));
        titleLabel.setForeground(NAVY);

        // Card description
        JLabel descriptionLabel = new JLabel(
                "<html>" + description + "</html>");

        descriptionLabel.setFont(
                new Font("SansSerif", Font.PLAIN, 13));
        descriptionLabel.setForeground(MUTED);

        // Open label
        JLabel openLabel = new JLabel("Open screen  →");
        openLabel.setFont(
                new Font("SansSerif", Font.BOLD, 13));
        openLabel.setForeground(ACCENT);

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(
                textPanel, BoxLayout.Y_AXIS));

        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(8));
        textPanel.add(descriptionLabel);

        card.add(numberLabel, BorderLayout.NORTH);
        card.add(textPanel, BorderLayout.CENTER);
        card.add(openLabel, BorderLayout.SOUTH);

        // Navigation callback
        card.addActionListener(e ->
                onNavigate.accept(screen));

        // Simple hover effect
        card.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(
                    java.awt.event.MouseEvent e) {

                card.setBackground(
                        new Color(245, 249, 253));
            }

            @Override
            public void mouseExited(
                    java.awt.event.MouseEvent e) {

                card.setBackground(Color.WHITE);
            }
        });

        return card;
    }
}