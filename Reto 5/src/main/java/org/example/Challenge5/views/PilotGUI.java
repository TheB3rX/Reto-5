package org.example.Challenge5.views;

import org.example.Challenge5.controller.Controller;
import org.example.Challenge5.models.Pilot;
import org.example.Challenge5.models.Team;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PilotGUI extends JFrame implements ActionListener {
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    JTable jTable;
    JComboBox<Team> addComboBox;
    JLabel addButton;
    JLabel addButtonTwo;
    JLabel addButtonThree;
    JLabel addButtonFour;
    JLabel addButtonFive;
    JTextField jtAdd;
    JTextField jtAddTwo;
    JTextField jtAddThree;
    JTextField jtAddFourth;
    private JPanel panel;
    private JDialogDelete dlgDelete;

    public PilotGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 749);
        this.setTitle("Base de datos pilotos");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.startComponents();
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new PilotGUI();
    }

    private void startComponents() {
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        this.generateTitle();
        this.generateLabels();
        this.generateButtons();
        this.generateTextField();
        this.generateComboBox();
        this.createTable();
        updateTable();
        dlgDelete = new JDialogDelete(this);
    }

    private void generateTitle() {
        JLabel appTitle = new JLabel("BD Pilotos");
        appTitle.setBounds(350, 5, 200, 50);
        panel.add(appTitle);

    }

    private void generateButtons() {
        JButton addButton = new JButton("Añadir piloto");
        addButton.setBounds(600, 200, 150, 30);
        addButton.addActionListener(this);
        addButton.setActionCommand("add");
        panel.add(addButton);

        JButton btnDelete = new JButton("Eliminar Piloto");
        btnDelete.setBounds(600, 240, 150, 30);
        btnDelete.addActionListener(this);
        btnDelete.setActionCommand("delete");
        panel.add(btnDelete);
    }

    private void generateLabels() {

        addButton = new JLabel("Añadir codigo");
        addButtonTwo = new JLabel("Añadir nombre");
        addButtonThree = new JLabel("Añadir millas");
        addButtonFour = new JLabel("Añadir combustible");
        addButtonFive = new JLabel("Escoger escudería");
        addButton.setBounds(50, 110, 150, 30);
        addButtonTwo.setBounds(325, 110, 150, 30);
        addButtonThree.setBounds(600, 110, 150, 30);
        addButtonFour.setBounds(50, 200, 150, 30);
        addButtonFive.setBounds(325, 200, 150, 30);
        panel.add(addButton);
        panel.add(addButtonTwo);
        panel.add(addButtonThree);
        panel.add(addButtonFour);
        panel.add(addButtonFive);

        //Eliminar piloto por codigo
        JLabel addDeleteID = new JLabel("Cod piloto");
        panel.add(addDeleteID);
    }

    private void generateComboBox() {
        addComboBox = new JComboBox<Team>();
        addComboBox.setBounds(325, 240, 150, 30);
        updateComboBox();
        panel.add(addComboBox);
    }

    private void updateComboBox() {
        addComboBox.removeAllItems();
        for (Team team : Controller.getAllTeam()) {
            addComboBox.addItem(team);
        }
    }

    private void generateTextField() {

        jtAdd = new JTextField();
        jtAddTwo = new JTextField();
        jtAddThree = new JTextField();
        jtAddFourth = new JTextField();
        jtAdd.setBounds(50, 150, 150, 30);
        jtAddTwo.setBounds(325, 150, 150, 30);
        jtAddThree.setBounds(600, 150, 150, 30);
        jtAddFourth.setBounds(50, 240, 150, 30);
        panel.add(jtAdd);
        panel.add(jtAddTwo);
        panel.add(jtAddThree);
        panel.add(jtAddFourth);
    }

    public void createTable() {
        JScrollPane jspane = new JScrollPane();
        jspane.setBounds(10, 300, 765, 400);
        jTable = new JTable();
        jspane.setViewportView(jTable);
        panel.add(jspane);
    }

    private void updateTable() {
        List<Pilot> suministros = Controller.getPilotos();
        Pilot[] data = suministros.toArray(new Pilot[0]);
        Object[][] matrixData = new Object[data.length][5];
        for (int i = 0; i < data.length; i++) {
            matrixData[i] = new Object[]{data[i].getId(), data[i].getName(), data[i].getFuel(), data[i].getMile(), data[i].getTeam().getName()};
        }
        DefaultTableModel tblModelPilot = new DefaultTableModel(matrixData, new Object[]{"ID", "Nombre", "Combustible", "Millas", "Escudería"});
        jTable.setModel(tblModelPilot);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add":
                addPilot();
                break;
            case "delete":
                showPilotRemove();
                break;
            case "deleteDlg":
                removePilot();
                break;

        }
    }

    private void showPilotRemove() {
        dlgDelete.setVisible(true);
    }

    private void removePilot() {
        int id = dlgDelete.getData();
        if (id == -1) return;
        dlgDelete.close();
        Controller.deletePilot(new Pilot(id));
        updateTable();
    }

    private void addPilot() {
        if (validateFields()) {
            Controller.insertPilot(new Pilot(Integer.parseInt(jtAdd.getText()), jtAddTwo.getText(), Float.parseFloat(jtAddThree.getText()), Integer.parseInt(jtAddFourth.getText()), (Team) addComboBox.getSelectedItem()));
            updateTable();
        } else JOptionPane.showMessageDialog(null, "Campos vacios o incorrectos");
    }

    private boolean validateFields() {
        return (
                jtAdd.getText().length() > 0
                        && jtAddTwo.getText().length() > 0
                        && jtAddThree.getText().length() > 0
                        && jtAddFourth.getText().length() > 0
                        && jtAdd.getText().matches("^[\\d]+$")
                        && jtAddThree.getText().matches("^[\\d.]+$")
                        && jtAddFourth.getText().matches("^[\\d]+$")
        );
    }
}
