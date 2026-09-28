
package com.witherxee.petclinic.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private final SlidePanel screenPanel = new SlidePanel();

    private final JButton homeButton =
            new JButton("← Dashboard");

    private String currentName = "";

    private boolean admin = true;

    public MainFrame() {

        setTitle("PetCare Management System");
        setSize(1100, 720);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // =========================
        // TOP NAVIGATION BAR
        // =========================

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(32, 58, 86));
        topBar.setBorder(new EmptyBorder(12, 20, 12, 20));

        JLabel appTitle = new JLabel("PetCare");
        appTitle.setForeground(Color.WHITE);
        appTitle.setFont(
                new Font("SansSerif", Font.BOLD, 24));

        homeButton.setFocusPainted(false);
        homeButton.setBackground(new Color(55, 82, 110));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBorder(
                new EmptyBorder(8, 15, 8, 15));

        homeButton.addActionListener(e ->
                showScreen("HOME"));

        topBar.add(appTitle, BorderLayout.WEST);
        topBar.add(homeButton, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // =========================
        // SCREEN CONTAINER
        // =========================

        add(screenPanel, BorderLayout.CENTER);

        //DashboardPanel dashboard = new DashboardPanel(this::showScreen);
        LoginPanel login = new LoginPanel(this::showScreen);

        screenPanel.setInitialScreen(login);

        homeButton.setVisible(false);

        setVisible(true);
    }

    // =========================
    // SCREEN NAVIGATION
    // =========================

    private JPanel createScreen(String name) {

        switch (name) {

            case "HOME":
                return new DashboardPanel(this::showScreen);

            case "CUSTOMERS":
                return new CustomerPanel();

            case "PETS":
                return new PetPanel();

            case "VETERINARIANS":
                return new VeterinarianPanel();

            case "APPOINTMENTS":
                return new AppointmentPanel();

            case "TREATMENTS":
                return new TreatmentPaymentPanel();

            case "REPORTS":
                return new ReportPanel();

            default:
                return new JPanel();
        }
    }

    public void showScreen(String name) {

        if (name.equals(currentName)
                || screenPanel.isAnimating()) {
            return;
        }

        JPanel nextScreen = createScreen(name);

        boolean forward = !name.equals("HOME");

        screenPanel.slideTo(nextScreen, forward);

        currentName = name;

        homeButton.setVisible(!name.equals("HOME"));
    }

    // =========================
    // SLIDING ANIMATION PANEL
    // =========================

    private static class SlidePanel extends JPanel {

        private JPanel outgoing;
        private JPanel incoming;

        private Timer timer;

        private int progress;

        private final int duration = 300;

        private boolean forward;

        SlidePanel() {
            setLayout(null);
            setBackground(Color.WHITE);
            setDoubleBuffered(true);
        }

        void setInitialScreen(JPanel screen) {

            removeAll();

            outgoing = screen;

            add(outgoing);

            outgoing.setBounds(
                    0, 0, getWidth(), getHeight());

            revalidate();
            repaint();
        }

        boolean isAnimating() {
            return timer != null && timer.isRunning();
        }

        void slideTo(JPanel next, boolean forward) {

            if (isAnimating()) {
                return;
            }

            this.forward = forward;
            this.progress = 0;

            int width = getWidth();
            int height = getHeight();

            if (getComponentCount() > 0) {
                outgoing = (JPanel) getComponent(0);
            } else {
                outgoing = null;
            }

            incoming = next;

            if (outgoing != null) {
                outgoing.setBounds(
                        0, 0, width, height);
            }

            int startX = forward ? width : -width;

            incoming.setBounds(
                    startX, 0, width, height);

            add(incoming);

            timer = new Timer(10, e -> {

                progress += 10;

                float fraction = Math.min(
                        1f,
                        (float) progress / duration);

                // Smooth ease-out animation
                float eased = 1f
                        - (1f - fraction) * (1f - fraction);

                int offset = (int) (width * eased);

                if (forward) {

                    incoming.setLocation(
                            width - offset, 0);

                    if (outgoing != null) {
                        outgoing.setLocation(
                                -offset, 0);
                    }

                } else {

                    incoming.setLocation(
                            -width + offset, 0);

                    if (outgoing != null) {
                        outgoing.setLocation(
                                offset, 0);
                    }
                }

                if (fraction >= 1f) {

                    timer.stop();

                    removeAll();

                    incoming.setBounds(
                            0, 0,
                            getWidth(), getHeight());

                    add(incoming);

                    outgoing = incoming;
                    incoming = null;

                    revalidate();
                    repaint();
                }
            });

            timer.start();
        }

        @Override
        public void doLayout() {

            if (outgoing != null && !isAnimating()) {

                outgoing.setBounds(
                        0, 0,
                        getWidth(), getHeight());
            }
        }
    }

    // =========================
    // APPLICATION ENTRY POINT
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new MainFrame());
    }
}