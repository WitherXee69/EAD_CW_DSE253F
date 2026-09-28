package com.witherxee.petclinic.ui;

import com.witherxee.petclinic.controller.TreatmentController;
import com.witherxee.petclinic.controller.PaymentController;
import com.witherxee.petclinic.controller.AppointmentController;
import com.witherxee.petclinic.model.Treatment;
import com.witherxee.petclinic.model.Payment;
import com.witherxee.petclinic.model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Treatment and payment management screen.
 * Uses the existing controllers; database operations remain in the service/DAO layer.
 */
public class TreatmentPaymentPanel extends JPanel {
    private final TreatmentController treatmentController = new TreatmentController();
    private final PaymentController paymentController = new PaymentController();
    private final AppointmentController appointmentController = new AppointmentController();

    private JComboBox<AppointmentItem> appointmentCombo;
    private JTextField treatmentDescriptionField, medicationField, treatmentCostField;
    private JTable treatmentTable;
    private DefaultTableModel treatmentModel;
    private JTextField paymentAmountField, paymentDateField;
    private JComboBox<String> paymentMethodCombo;
    private JTable paymentTable;
    private DefaultTableModel paymentModel;
    private int selectedTreatmentId = -1;
    private int selectedPaymentId = -1;

    private static final Color BACKGROUND = new Color(235,241,247);
    private static final Color NAVY = new Color(32,58,86);
    private static final Color ACCENT = new Color(55,107,154);

    public TreatmentPaymentPanel() {
        initComponents();
        buildInterface();
        loadAppointments();
        refreshTreatments();
        refreshPayments();
    }

    // Kept for NetBeans GUI Builder compatibility. The interface is built below.
    private void initComponents() {
        setLayout(new BorderLayout());
    }

    private void buildInterface() {
        setBackground(BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));

        JLabel heading = new JLabel("Treatments & Payments");
        heading.setFont(new Font("SansSerif", Font.BOLD, 24));
        heading.setForeground(NAVY);

        appointmentCombo = new JComboBox<>();
        appointmentCombo.addActionListener(e -> {
            refreshTreatments();
            refreshPayments();
        });

        JPanel top = new JPanel(new BorderLayout(12, 8));
        top.setOpaque(false);
        top.add(heading, BorderLayout.NORTH);
        JPanel appointmentRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        appointmentRow.setOpaque(false);
        appointmentRow.add(new JLabel("Appointment:"));
        appointmentCombo.setPreferredSize(new Dimension(300, 28));
        appointmentRow.add(appointmentCombo);
        top.add(appointmentRow, BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(2,1,0,14));
        content.setOpaque(false);
        content.add(buildTreatmentSection());
        content.add(buildPaymentSection());
        add(content, BorderLayout.CENTER);
    }

    private JPanel buildTreatmentSection() {
        JPanel panel = sectionPanel("Treatment Records");
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4,6,4,6);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1;

        treatmentDescriptionField = new JTextField();
        medicationField = new JTextField();
        treatmentCostField = new JTextField();

        addField(form,g,0,"Treatment description:",treatmentDescriptionField);
        addField(form,g,1,"Medication:",medicationField);
        addField(form,g,2,"Cost:",treatmentCostField);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.setOpaque(false);
        JButton add = button("Add");
        JButton update = button("Update");
        JButton delete = button("Delete");
        JButton clear = button("Clear");
        add.addActionListener(e -> addTreatment());
        update.addActionListener(e -> updateTreatment());
        delete.addActionListener(e -> deleteTreatment());
        clear.addActionListener(e -> clearTreatmentForm());
        buttons.add(add); buttons.add(update); buttons.add(delete); buttons.add(clear);

        treatmentModel = new DefaultTableModel(
                new Object[]{"ID","Appointment","Treatment","Medication","Cost"},0) {
            public boolean isCellEditable(int r,int c){return false;}
        };
        treatmentTable = new JTable(treatmentModel);
        treatmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        treatmentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && treatmentTable.getSelectedRow() >= 0) {
                int row=treatmentTable.convertRowIndexToModel(treatmentTable.getSelectedRow());
                selectedTreatmentId=(int)treatmentModel.getValueAt(row,0);
                treatmentDescriptionField.setText(String.valueOf(treatmentModel.getValueAt(row,2)));
                medicationField.setText(String.valueOf(treatmentModel.getValueAt(row,3)));
                treatmentCostField.setText(String.valueOf(treatmentModel.getValueAt(row,4)));
                selectAppointment((int)treatmentModel.getValueAt(row,1));
            }
        });

        JPanel north = new JPanel(new BorderLayout());
        north.setOpaque(false);
        north.add(form,BorderLayout.CENTER);
        north.add(buttons,BorderLayout.SOUTH);
        panel.add(north,BorderLayout.NORTH);
        panel.add(new JScrollPane(treatmentTable),BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildPaymentSection() {
        JPanel panel = sectionPanel("Payment Records");
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4,6,4,6); g.fill=GridBagConstraints.HORIZONTAL; g.weightx=1;

        paymentAmountField = new JTextField();
        paymentDateField = new JTextField(LocalDate.now().toString());
        paymentMethodCombo = new JComboBox<>(new String[]{"Cash","Card","Bank Transfer","Online"});
        addField(form,g,0,"Amount:",paymentAmountField);
        addField(form,g,1,"Payment date (YYYY-MM-DD):",paymentDateField);
        addField(form,g,2,"Payment method:",paymentMethodCombo);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.setOpaque(false);
        JButton add=button("Add Payment"), update=button("Update"), delete=button("Delete"), clear=button("Clear");
        add.addActionListener(e->addPayment());
        update.addActionListener(e->updatePayment());
        delete.addActionListener(e->deletePayment());
        clear.addActionListener(e->clearPaymentForm());
        buttons.add(add);buttons.add(update);buttons.add(delete);buttons.add(clear);

        paymentModel = new DefaultTableModel(
                new Object[]{"ID","Appointment","Amount","Date","Method"},0) {
            public boolean isCellEditable(int r,int c){return false;}
        };
        paymentTable = new JTable(paymentModel);
        paymentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        paymentTable.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting() && paymentTable.getSelectedRow()>=0){
                int row=paymentTable.convertRowIndexToModel(paymentTable.getSelectedRow());
                selectedPaymentId=(int)paymentModel.getValueAt(row,0);
                paymentAmountField.setText(String.valueOf(paymentModel.getValueAt(row,2)));
                paymentDateField.setText(String.valueOf(paymentModel.getValueAt(row,3)));
                paymentMethodCombo.setSelectedItem(String.valueOf(paymentModel.getValueAt(row,4)));
                selectAppointment((int)paymentModel.getValueAt(row,1));
            }
        });

        JPanel north=new JPanel(new BorderLayout());
        north.setOpaque(false); north.add(form,BorderLayout.CENTER); north.add(buttons,BorderLayout.SOUTH);
        panel.add(north,BorderLayout.NORTH);
        panel.add(new JScrollPane(paymentTable),BorderLayout.CENTER);
        return panel;
    }

    private JPanel sectionPanel(String title) {
        JPanel p=new JPanel(new BorderLayout(8,8));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(205,216,226)),
                BorderFactory.createTitledBorder(
                        BorderFactory.createEmptyBorder(8,8,8,8),title)));
        return p;
    }

    private void addField(JPanel p,GridBagConstraints g,int row,String label,JComponent field) {
        g.gridy=row; g.gridx=0; g.weightx=0; p.add(new JLabel(label),g);
        g.gridx=1; g.weightx=1; field.setPreferredSize(new Dimension(180,27)); p.add(field,g);
    }

    private JButton button(String text) {
        JButton b=new JButton(text);
        b.setBackground(ACCENT); b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        return b;
    }

    private void loadAppointments() {
        appointmentCombo.removeAllItems();
        List<Appointment> appointments=appointmentController.getAllAppointments();
        for(Appointment a:appointments) appointmentCombo.addItem(new AppointmentItem(a.getAppointmentId()));
    }

    private int appointmentId() {
        AppointmentItem item=(AppointmentItem)appointmentCombo.getSelectedItem();
        if(item==null) throw new IllegalArgumentException("Please select an appointment.");
        return item.id;
    }

    private void selectAppointment(int id) {
        for(int i=0;i<appointmentCombo.getItemCount();i++)
            if(appointmentCombo.getItemAt(i).id==id){appointmentCombo.setSelectedIndex(i);break;}
    }

    private void refreshTreatments() {
        if(treatmentModel==null)return;
        treatmentModel.setRowCount(0);
        try {
            int aid=appointmentId();
            for(Treatment t:treatmentController.getTreatmentsByAppointment(aid))
                treatmentModel.addRow(new Object[]{t.getTreatmentId(),t.getAppointmentId(),
                    t.getName(),t.getDescription(),t.getCost()});
        } catch(Exception ignored) { }
    }

    private void refreshPayments() {
        if(paymentModel==null)return;
        paymentModel.setRowCount(0);
        try {
            int aid=appointmentId();
            Payment p=paymentController.getPaymentByAppointment(aid);
            if(p!=null) paymentModel.addRow(new Object[]{p.getPaymentId(),p.getAppointmentId(),
                p.getAmount(),p.getPaymentDate(),p.getPaymentMethod()});
        } catch(Exception ignored) { }
    }

    private void addTreatment() {
        try {
            Treatment t=new Treatment(0,appointmentId(),
                    treatmentDescriptionField.getText().trim(),
                    medicationField.getText().trim(),
                    Double.parseDouble(treatmentCostField.getText().trim()));
            treatmentController.addTreatment(t);
            refreshTreatments(); clearTreatmentForm();
            JOptionPane.showMessageDialog(this,"Treatment added.");
        } catch(Exception ex){showError(ex);}
    }

    private void updateTreatment() {
        try {
            if(selectedTreatmentId<0) throw new IllegalArgumentException("Select a treatment first.");
            Treatment t=new Treatment(selectedTreatmentId,appointmentId(),
                    treatmentDescriptionField.getText().trim(),
                    medicationField.getText().trim(),
                    Double.parseDouble(treatmentCostField.getText().trim()));
            treatmentController.updateTreatment(t);
            refreshTreatments(); clearTreatmentForm();
            JOptionPane.showMessageDialog(this,"Treatment updated.");
        } catch(Exception ex){showError(ex);}
    }

    private void deleteTreatment() {
        try {
            if(selectedTreatmentId<0) throw new IllegalArgumentException("Select a treatment first.");
            if(JOptionPane.showConfirmDialog(this,"Delete selected treatment?","Confirm",
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                treatmentController.deleteTreatment(selectedTreatmentId);
                refreshTreatments(); clearTreatmentForm();
            }
        } catch(Exception ex){showError(ex);}
    }

    private void addPayment() {
        try {
            Payment p=new Payment(0,appointmentId(),
                    Double.parseDouble(paymentAmountField.getText().trim()),
                    LocalDate.parse(paymentDateField.getText().trim()),
                    String.valueOf(paymentMethodCombo.getSelectedItem()));
            paymentController.addPayment(p);
            refreshPayments(); clearPaymentForm();
            JOptionPane.showMessageDialog(this,"Payment added.");
        } catch(Exception ex){showError(ex);}
    }

    private void updatePayment() {
        try {
            if(selectedPaymentId<0) throw new IllegalArgumentException("Select a payment first.");
            Payment p=new Payment(selectedPaymentId,appointmentId(),
                    Double.parseDouble(paymentAmountField.getText().trim()),
                    LocalDate.parse(paymentDateField.getText().trim()),
                    String.valueOf(paymentMethodCombo.getSelectedItem()));
            paymentController.updatePayment(p);
            refreshPayments(); clearPaymentForm();
            JOptionPane.showMessageDialog(this,"Payment updated.");
        } catch(Exception ex){showError(ex);}
    }

    private void deletePayment() {
        try {
            if(selectedPaymentId<0) throw new IllegalArgumentException("Select a payment first.");
            if(JOptionPane.showConfirmDialog(this,"Delete selected payment?","Confirm",
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                paymentController.deletePayment(selectedPaymentId);
                refreshPayments(); clearPaymentForm();
            }
        } catch(Exception ex){showError(ex);}
    }

    private void clearTreatmentForm() {
        selectedTreatmentId=-1;
        treatmentTable.clearSelection();
        treatmentDescriptionField.setText("");
        medicationField.setText("");
        treatmentCostField.setText("");
    }

    private void clearPaymentForm() {
        selectedPaymentId=-1;
        paymentTable.clearSelection();
        paymentAmountField.setText("");
        paymentDateField.setText(LocalDate.now().toString());
        paymentMethodCombo.setSelectedIndex(0);
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(this,
                ex.getMessage()==null?"Please check the entered values.":ex.getMessage(),
                "Error",JOptionPane.ERROR_MESSAGE);
    }

    private static class AppointmentItem {
        final int id;
        AppointmentItem(int id){this.id=id;}
        @Override public String toString(){return "Appointment #"+id;}
    }
}
