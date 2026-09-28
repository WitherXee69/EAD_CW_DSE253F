/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.witherxee.petclinic.ui;

import com.witherxee.petclinic.controller.PetController;
import com.witherxee.petclinic.exception.MissingOwnerException;
import com.witherxee.petclinic.controller.CustomerController;
import com.witherxee.petclinic.model.Customer;
import com.witherxee.petclinic.model.Pet;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author witherxee
 */
public class PetPanel extends javax.swing.JPanel {

    private PetController controller;
    private CustomerController cusController;
    private int selectedPetId = -1;

    private final Color BACKGROUND = new Color(235, 241, 247);
    private final Color NAVY = new Color(32, 58, 86);
    private final Color ACCENT = new Color(55, 107, 154);
    private final Color MUTED = new Color(90, 108, 125);
    private final Color BORDER = new Color(218, 226, 235);

    /**
     * Creates new form PetPanel
     */
    public PetPanel() {
        initComponents();

        controller = new PetController();

        applyStyle();
        loadPets();
        loadOwners();
    }

    private void applyStyle() {

        setBackground(BACKGROUND);

        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitle.setForeground(NAVY);

        lblSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSubtitle.setForeground(MUTED);

        lblFormTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblFormTitle.setForeground(NAVY);

        lblTableTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTableTitle.setForeground(NAVY);

        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(BORDER));

        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createLineBorder(BORDER));

        styleLabel(lblName);
        styleLabel(lblSpecies);
        styleLabel(lblDob);
        styleLabel(lblOwnerId);
        styleLabel(lblGender);
        styleLabel(lblBreed);

        styleField(txtName);
        styleField(txtSpecies);
        styleField(txtDob);
        styleField(txtBreed);

        cmbGender.setFont(new Font("SansSerif", Font.PLAIN, 13));
        cmbGender.setBackground(Color.WHITE);
        cmbGender.setForeground(NAVY);

        btnAdd.setBackground(ACCENT);
        btnAdd.setForeground(Color.WHITE);

        btnUpdate.setBackground(NAVY);
        btnUpdate.setForeground(Color.WHITE);

        btnDelete.setBackground(new Color(170, 75, 75));
        btnDelete.setForeground(Color.WHITE);

        btnClear.setBackground(new Color(235, 241, 247));
        btnClear.setForeground(NAVY);

        btnAdd.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnUpdate.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnDelete.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnClear.setFont(new Font("SansSerif", Font.BOLD, 13));

        tblPets.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tblPets.setForeground(NAVY);
        tblPets.setRowHeight(30);
        tblPets.setShowGrid(false);
        tblPets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPets.setSelectionBackground(new Color(220, 233, 244));
        tblPets.setSelectionForeground(NAVY);

        tblPets.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        tblPets.getTableHeader().setForeground(NAVY);
        tblPets.getTableHeader().setBackground(new Color(245, 249, 253));
    }

    private void styleLabel(javax.swing.JLabel label) {
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(NAVY);
    }

    private void styleField(javax.swing.JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setForeground(NAVY);
        field.setBackground(Color.WHITE);
    }

    private void loadPets() {

        List<Pet> pets = controller.getAllPets();

        DefaultTableModel model =
                (DefaultTableModel) tblPets.getModel();

        model.setRowCount(0);

        for (Pet pet : pets) {

            model.addRow(new Object[]{
                pet.getPetId(),
                pet.getName(),
                pet.getSpecies(),
                pet.getDateOfBirth(),
                pet.getOwnerId(),
                pet.getGender(),
                pet.getBreed()
            });
        }
    }
    
    private int getSelectedId(javax.swing.JComboBox<String> comboBox) {
        String selected = (String) comboBox.getSelectedItem();
        if (selected == null) {
            return -1;
        }
        return Integer.parseInt(selected.substring(0, selected.indexOf(" | ")));
    }
    
    private void loadOwners(){
        cmbOwners.removeAllItems();

        List<Customer> customers =
            cusController.getAllCustomers();

        for (Customer cus : customers) {
            String item = cus.getId() + " | " + cus.getName();

            cmbOwners.addItem(item);
        }
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

    private boolean validateFields() {

        if (txtName.getText().trim().isEmpty()
                || txtSpecies.getText().trim().isEmpty()
                || txtDob.getText().trim().isEmpty()
                || txtBreed.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        try {

            LocalDate.parse(txtDob.getText().trim());

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Date of birth must use yyyy-MM-dd format.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        try {

            int ownerId = getSelectedId(cmbOwners);

            if (ownerId <= 0) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Owner ID must be a valid number.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        return true;
    }

    private Pet getPetFromFields() {

        int ownerId = getSelectedId(cmbOwners);

        LocalDate dob =
                LocalDate.parse(txtDob.getText().trim());

        char gender =
                cmbGender.getSelectedItem().toString().charAt(0);

        return new Pet(
                selectedPetId,
                txtName.getText().trim(),
                txtSpecies.getText().trim(),
                dob,
                ownerId,
                gender,
                txtBreed.getText().trim()
        );
    }

    private void clearFields() {

        selectedPetId = -1;

        txtName.setText("");
        txtSpecies.setText("");
        txtDob.setText("");
        
        if (cmbOwners.getItemCount() > 0) {
            cmbOwners.setSelectedIndex(0);
        }
        txtBreed.setText("");

        cmbGender.setSelectedIndex(0);

        tblPets.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        lblFormTitle = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblSpecies = new javax.swing.JLabel();
        lblDob = new javax.swing.JLabel();
        lblOwnerId = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblBreed = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtSpecies = new javax.swing.JTextField();
        txtDob = new javax.swing.JTextField();
        txtBreed = new javax.swing.JTextField();
        cmbGender = new javax.swing.JComboBox();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        cmbOwners = new javax.swing.JComboBox<>();
        tablePanel = new javax.swing.JPanel();
        lblTableTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPets = new javax.swing.JTable();

        setBackground(new java.awt.Color(235, 241, 247));

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(32, 58, 86));
        lblTitle.setText("Pet Management");

        lblSubtitle.setText("Register and manage pets in the clinic");

        formPanel.setBackground(new java.awt.Color(255, 255, 255));

        lblFormTitle.setText("Pet Details");

        lblName.setText("Name");

        lblSpecies.setText("Species");

        lblDob.setText("Date of Birth");

        lblOwnerId.setText("Owner ID");

        lblGender.setText("Gender");

        lblBreed.setText("Breed");

        cmbGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFormTitle)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 170, 170)
                            .addComponent(lblDob)
                            .addComponent(txtDob, javax.swing.GroupLayout.DEFAULT_SIZE, 170, 170))
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSpecies)
                                    .addComponent(txtSpecies, javax.swing.GroupLayout.DEFAULT_SIZE, 170, 170)
                                    .addComponent(lblOwnerId)))
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cmbOwners, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGender)
                            .addComponent(txtBreed, javax.swing.GroupLayout.DEFAULT_SIZE, 170, 170)
                            .addComponent(cmbGender, 0, 170, 170)
                            .addComponent(lblBreed)))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(btnAdd, 90, 90, 90)
                        .addGap(10, 10, 10)
                        .addComponent(btnUpdate, 90, 90, 90)
                        .addGap(10, 10, 10)
                        .addComponent(btnDelete, 90, 90, 90)
                        .addGap(10, 10, 10)
                        .addComponent(btnClear, 90, 90, 90)))
                .addContainerGap(290, Short.MAX_VALUE))
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblFormTitle)
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName)
                            .addComponent(lblSpecies)
                            .addComponent(lblGender))
                        .addGap(6, 6, 6)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSpecies, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDob)
                            .addComponent(lblOwnerId)
                            .addComponent(lblBreed))
                        .addGap(6, 6, 6)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDob, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBreed, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(cmbOwners, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        tablePanel.setBackground(new java.awt.Color(255, 255, 255));

        lblTableTitle.setText("Registered Pets");

        tblPets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Species", "Date of Birth", "Owner ID", "Gender", "Breed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPets.setRowHeight(30);
        tblPets.setShowGrid(false);
        tblPets.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPetsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPets);

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTableTitle)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTableTitle)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle)
                    .addComponent(lblSubtitle)
                    .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitle)
                .addGap(4, 4, 4)
                .addComponent(lblSubtitle)
                .addGap(20, 20, 20)
                .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {

        if (!validateFields()) {
            return;
        }

        try {

            Pet pet = getPetFromFields();

            controller.addPet(pet);

            loadPets();
            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Pet added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (MissingOwnerException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Owner Error",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add pet.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {

        if (selectedPetId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a pet from the table first.",
                    "Selection Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!validateFields()) {
            return;
        }

        try {

            Pet pet = getPetFromFields();

            controller.updatePet(pet);

            loadPets();
            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Pet updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update pet.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {

        if (selectedPetId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a pet from the table first.",
                    "Selection Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this pet?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {

            controller.deletePet(selectedPetId);

            loadPets();
            clearFields();

            JOptionPane.showMessageDialog(
                    this,
                    "Pet deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        clearFields();
    }

    private void tblPetsMouseClicked(java.awt.event.MouseEvent evt) {

        int row = tblPets.getSelectedRow();
        
        int ownerId = -1;
        
        int petId = Integer.parseInt(tblPets.getValueAt(row, 0).toString());
        
        Pet pet = controller.getPetById(petId);

        if (row == -1) {
            return;
        }

        selectedPetId =
                (int) tblPets.getValueAt(row, 0);

        txtName.setText(
                tblPets.getValueAt(row, 1).toString()
        );

        txtSpecies.setText(
                tblPets.getValueAt(row, 2).toString()
        );

        txtDob.setText(
                tblPets.getValueAt(row, 3).toString()
        );
        
        ownerId = pet.getOwnerId();

        selectComboItem(cmbOwners, ownerId);

        cmbGender.setSelectedItem(
                tblPets.getValueAt(row, 5).toString()
        );

        txtBreed.setText(
                tblPets.getValueAt(row, 6).toString()
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbGender;
    private javax.swing.JComboBox<String> cmbOwners;
    private javax.swing.JPanel formPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBreed;
    private javax.swing.JLabel lblDob;
    private javax.swing.JLabel lblFormTitle;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblOwnerId;
    private javax.swing.JLabel lblSpecies;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTableTitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTable tblPets;
    private javax.swing.JTextField txtBreed;
    private javax.swing.JTextField txtDob;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSpecies;
    // End of variables declaration//GEN-END:variables
}
