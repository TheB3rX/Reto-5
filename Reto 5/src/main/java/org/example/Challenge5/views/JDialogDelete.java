package org.example.Challenge5.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDialogDelete extends JDialog {
    private JLabel lblText;
    private JTextField txtText;
    private JButton btnDelete;

    public JDialogDelete(ActionListener listener) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setTitle("Eliminar Piloto");
        this.setSize(400,200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        initComponents(listener);
        this.setVisible(false);
    }

    private void initComponents(ActionListener listener) {
        lblText = new JLabel("Digite Id de piloto a eliminar");
        txtText = new JTextField();
        btnDelete = new JButton("Eliminar");
        btnDelete.addActionListener(listener);
        btnDelete.setActionCommand("deleteDlg");
        posicionateComponents();
    }

    private void posicionateComponents() {
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.insets = new Insets(20,40,20,40);

        add(lblText, g);
        g.gridx++;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1;
        add(txtText, g);

        g.fill = GridBagConstraints.NONE;
        g.gridwidth = 2;
        g.gridx = 0;
        g.gridy++;
        g.ipady = 20;
        g.ipadx = 20;
        add(btnDelete, g);
    }

    public void close(){
        txtText.setText("");
        this.dispose();
    }

    public int getData() {
        return txtText.getText().length() > 0 && txtText.getText().matches("^[\\d]+$") ? Integer.parseInt(txtText.getText()) : -1;
    }
}
