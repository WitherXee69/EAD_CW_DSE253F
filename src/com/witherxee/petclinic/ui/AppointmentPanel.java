/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.witherxee.petclinic.ui;

import com.witherxee.petclinic.controller.PetController;
import com.witherxee.petclinic.controller.VeterinarianController;
import com.witherxee.petclinic.controller.AppointmentController;
import com.witherxee.petclinic.exception.AppointmentConflictException;
import com.witherxee.petclinic.model.Appointment;
import com.witherxee.petclinic.model.Pet;
import com.witherxee.petclinic.model.Veterinarian;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author witherxee
 */
public class AppointmentPanel extends javax.swing.JPanel {
    
    private final PetController petController = new PetController();
    private final AppointmentController appointmentController = new AppointmentController();
    private final VeterinarianController vetController = new VeterinarianController();

    /**
     * Creates new form AppoinmentPanel
     */
    public AppointmentPanel() {
        initComponents();
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"SCHEDULED", "COMPLETED", "CANCELLED"}));
        loadPets();
        loadVeterinarians();
        loadAppointments();
    }
    
    private void loadPets() {
        cmbPet.removeAllItems();
    
        List<Pet> pets = petController.getAllPets();

        for (Pet pet : pets) {
            String item = pet.getPetId() + " | "
                    + pet.getName() + " (" + pet.getSpecies() + ")";

            cmbPet.addItem(item);
        }
    }
    
    private void loadVeterinarians() {
        cmbVet.removeAllItems();

        List<Veterinarian> veterinarians =
            vetController.getAllVeterinarians();

        for (Veterinarian vet : veterinarians) {
            String item = vet.getId() + " | " + vet.getName();

            cmbVet.addItem(item);
        }
    }
    
    private int getSelectedId(javax.swing.JComboBox<String> comboBox) {
        String selected = (String) comboBox.getSelectedItem();
        if (selected == null) {
            return -1;
        }
        return Integer.parseInt(selected.substring(0, selected.indexOf(" | ")));
    }
    
    private void loadAppointments() {
    DefaultTableModel model =
            (DefaultTableModel) jTable1.getModel();

    model.setRowCount(0);

    List<Appointment> appointments =
            appointmentController.getAllAppointments();

    for (Appointment appointment : appointments) {

        Pet pet = petController.getPetById(
                appointment.getPetId());

        Veterinarian vet = vetController.getVeterinarianById(
                appointment.getVeterinarianId());

        String petName = pet != null
                ? pet.getName() : "Unknown";

        String vetName = vet != null
                ? vet.getName() : "Unknown";

        model.addRow(new Object[]{
            appointment.getAppointmentId(),
            petName,
            vetName,
            appointment.getDate(),
            appointment.getTime(),
            appointment.getReason(),
            appointment.getStatus()
        });
    }
}
    
    private void clearForm() {
    if (cmbPet.getItemCount() > 0) {
        cmbPet.setSelectedIndex(0);
    }

    if (cmbVet.getItemCount() > 0) {
        cmbVet.setSelectedIndex(0);
    }

    cmbStatus.setSelectedItem("SCHEDULED");

    txtDate.setText("");
    txtTime.setText("");
    txtReason.setText("");

    jTable1.clearSelection();
}
    
    private void selectComboItem(javax.swing.JComboBox<String> comboBox, int id) {

    for (int i = 0; i < comboBox.getItemCount(); i++) {
        String item = comboBox.getItemAt(i);

        if (item.startsWith(id + " | ")) {
            comboBox.setSelectedIndex(i);
            return;
        }
    }
}
    
    private void loadSelectedAppointment() {
    int row = jTable1.getSelectedRow();

    if (row == -1) {
        return;
    }

    int petId = -1;
    int vetId = -1;

    // Get pet and veterinarian IDs from the selected appointment
    int appointmentId = Integer.parseInt(
            jTable1.getValueAt(row, 0).toString());

    Appointment appointment =
            appointmentController.getAppointment(appointmentId);

    if (appointment == null) {
        return;
    }

    petId = appointment.getPetId();
    vetId = appointment.getVeterinarianId();

    selectComboItem(cmbPet, petId);
    selectComboItem(cmbVet, vetId);

    txtDate.setText(appointment.getDate().toString());
    txtTime.setText(appointment.getTime().toString());
    txtReason.setText(appointment.getReason());

    cmbStatus.setSelectedItem(appointment.getStatus());
}
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        lblPet = new javax.swing.JLabel();
        lblVet = new javax.swing.JLabel();
        cmbPet = new javax.swing.JComboBox<>();
        cmbVet = new javax.swing.JComboBox<>();
        lblDate = new javax.swing.JLabel();
        lblReason = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        txtTime = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        lblTime = new javax.swing.JLabel();
        txtReason = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblTableTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(32, 58, 86));
        lblTitle.setText("Appointments");

        lblSubtitle.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(90, 108, 125));
        lblSubtitle.setText("Manage the appointments and their details.");

        formPanel.setBackground(new java.awt.Color(255, 255, 255));

        lblPet.setText("Pet");

        lblVet.setText("Veterinarian");

        lblDate.setText("Date(YYYY-MM-DD)");

        lblReason.setText("Reason");

        txtDate.setToolTipText("YYYY-MM-DD");

        txtTime.setToolTipText("HH:MM");

        lblStatus.setText("Status");

        lblTime.setText("Time(HH:MM)");

        txtReason.setToolTipText("Enter reason for the appointment");

        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnClear.setText("Clear");
        btnClear.addActionListener(this::btnClearActionPerformed);

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPet)
                            .addComponent(lblDate)
                            .addComponent(lblStatus)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbPet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(109, 109, 109)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblReason)
                        .addGroup(formPanelLayout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(cmbVet, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTime)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                                .addGap(0, 28, Short.MAX_VALUE)
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtReason, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(lblVet, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPet)
                    .addComponent(lblVet)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbVet, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPet, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDate)
                            .addComponent(lblTime))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStatus)
                            .addComponent(lblReason))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtReason, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTableTitle.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        lblTableTitle.setForeground(new java.awt.Color(32, 58, 86));
        lblTableTitle.setText("Current Appoinments");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Pet", "Veterinarian", "Date", "Time", "Reason", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1))
                    .addComponent(lblTableTitle))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTableTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSubtitle)
                    .addComponent(lblTitle)
                    .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
        int petId = getSelectedId(cmbPet);
        int vetId = getSelectedId(cmbVet);

        if (petId <= 0 || vetId <= 0) {
            JOptionPane.showMessageDialog(
                    this, "Please select a pet and veterinarian.");
            return;
        }

        LocalDate date = LocalDate.parse(txtDate.getText().trim());
        LocalTime time = LocalTime.parse(txtTime.getText().trim());

        String reason = txtReason.getText().trim();
        String status = (String) cmbStatus.getSelectedItem();

        if (reason.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Please enter the appointment reason.");
            return;
        }

        Appointment appointment = new Appointment();

        appointment.setPetId(petId);
        appointment.setVeterinarianId(vetId);
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setReason(reason);
        appointment.setStatus(status);

        appointmentController.bookAppointment(appointment);

        JOptionPane.showMessageDialog(
                this, "Appointment added successfully!");

        loadAppointments();
        clearForm();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(
                this, "Error adding appointment: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    int row = jTable1.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(
                this, "Please select an appointment from the table.");
        return;
    }

    try {
        int appointmentId = Integer.parseInt(
                jTable1.getValueAt(row, 0).toString());

        int petId = getSelectedId(cmbPet);
        int vetId = getSelectedId(cmbVet);

        if (petId <= 0 || vetId <= 0) {
            JOptionPane.showMessageDialog(
                    this, "Please select a pet and veterinarian.");
            return;
        }

        String reason = txtReason.getText().trim();

        if (reason.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Please enter the appointment reason.");
            return;
        }

        Appointment appointment = new Appointment();

        appointment.setAppointmentId(appointmentId);
        appointment.setPetId(petId);
        appointment.setVeterinarianId(vetId);
        appointment.setDate(
                LocalDate.parse(txtDate.getText().trim()));
        appointment.setTime(
                LocalTime.parse(txtTime.getText().trim()));
        appointment.setReason(reason);
        appointment.setStatus(
                (String) cmbStatus.getSelectedItem());

        appointmentController.updateAppointment(appointment);

        JOptionPane.showMessageDialog(
                this, "Appointment updated successfully!");

        loadAppointments();
        clearForm();

    } catch (AppointmentConflictException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(
                this, "Error updating appointment: " + ex.getMessage());
    }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = jTable1.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(
                this, "Please select an appointment to delete.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this appointment?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    try {
        int appointmentId = Integer.parseInt(
                jTable1.getValueAt(row, 0).toString());

        appointmentController.deleteAppointment(appointmentId);

        JOptionPane.showMessageDialog(
                this, "Appointment deleted successfully!");

        loadAppointments();
        clearForm();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(
                this, "Error deleting appointment: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        loadSelectedAppointment();
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbPet;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JComboBox<String> cmbVet;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblPet;
    private javax.swing.JLabel lblReason;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTableTitle;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblVet;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtReason;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
