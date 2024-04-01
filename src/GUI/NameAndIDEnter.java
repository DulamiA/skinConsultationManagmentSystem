package GUI;

import Classes.Consultation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NameAndIDEnter extends JFrame implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
    private String firstName,ID;
    private JButton confirm, close;
    private JTextField fName,appointmentIDField;

    public NameAndIDEnter(){
        JPanel panel = makePanel();
        add(panel);

        fName.addKeyListener(this);
        appointmentIDField.addKeyListener(this);
        close.addActionListener(this);
        confirm.addActionListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        panel.setBackground(Color.WHITE);
        setUndecorated(true);
        pack();
        setLocation(500,100);
        setVisible(true);
    }

    private JPanel makePanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20,30,20,30);

        Label label = new Label("Enter Your First name and ID to proceed", Label.CENTER);
        Label nameL = new Label("First Name  :");
        Label idL = new Label("Patient ID :");
        confirm = new JButton("Confirm");
        close = new JButton("Back");
        fName = new JTextField(10);
        appointmentIDField = new JTextField(10);


        nameL.setForeground(Color.BLACK);
        idL.setForeground(Color.BLACK);
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
        confirm.setBackground(Color.LIGHT_GRAY);
        close.setBackground(Color.DARK_GRAY);
        confirm.setForeground(Color.BLACK);
        close.setForeground(Color.WHITE);

        gbc.gridwidth =4;
        gbc.gridy=0;
        gbc.gridx =0;
        panel.add(label,gbc);

        gbc.insets = new Insets(10,30,10,30);
        gbc.gridwidth =1;
        gbc.gridy=1;
        gbc.gridx =0;
        panel.add(idL,gbc);

        gbc.gridx =1;
        panel.add(appointmentIDField,gbc);

        gbc.gridy=2;
        gbc.gridx =0;
        panel.add(nameL,gbc);

        gbc.gridx =1;
        panel.add(fName,gbc);

        gbc.insets = new Insets(10,30,30,30);
        gbc.gridwidth =1;
        gbc.gridy=3;
        gbc.gridx =0;
        panel.add(confirm,gbc);

        gbc.gridx =1;
        panel.add(close,gbc);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==close) {
            this.dispose();
            try {
                new MainGUI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == confirm) {
            firstName = fName.getText().trim();
            ID = appointmentIDField.getText();
            if (!Pattern.matches("[0-9]{4}",ID)){
                JOptionPane.showConfirmDialog(this, "Incorrect ID", "Warning", JOptionPane.OK_CANCEL_OPTION);
            }else if (!Pattern.matches("[a-zA-Z]+",firstName)){
                JOptionPane.showConfirmDialog(this, "Enter a valid name", "Warning", JOptionPane.OK_CANCEL_OPTION);
            }else {
                Consultation c = findConsultation();
                List<Consultation> consultationList = findConsultationList();
                try {
                    if (c!=null) {
                        new ConsultationDetails(consultationList);
                        this.dispose();
                    }
                    else JOptionPane.showConfirmDialog(this, "There is no such reservation", "Warning", JOptionPane.OK_CANCEL_OPTION);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public Consultation findConsultation(){
        this.firstName = fName.getText();
        this.ID = appointmentIDField.getText();
        Consultation consultation1 = findConsultationByName(this.firstName);
        Consultation consultation2 = findConsultationByAppointmentID(this.ID);
        if (consultation1==consultation2) return consultation1;
        else return null;
    }

    public List<Consultation> findConsultationList(){
        this.firstName = fName.getText();
        this.ID = appointmentIDField.getText();
        return findConsultationList(this.ID, this.firstName);
    }
    public static Consultation findConsultationByName(String name){
        for (Consultation c : Consultation.getConsultations()) {
            if (name.equals(c.getPatient().getName())) return c;
        }
        return null;
    }

    public static List<Consultation> findConsultationList(String id, String name){

        List<Consultation> list = new ArrayList<>();
        for (Consultation c : Consultation.getConsultations()) {
            if (name.equals(c.getPatient().getName()) && id.equals(c.getPatient().getId())) {
                list.add(c);
            }
        }
        return list;
    }

    public static Consultation findConsultationByAppointmentID(String appointmentID) {
        for (Consultation c : Consultation.getConsultations()) {
            if (appointmentID.equals(c.getPatient().getId())) return c;
        }
        return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
