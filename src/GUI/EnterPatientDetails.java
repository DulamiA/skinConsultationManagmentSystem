package GUI;

import Classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EnterPatientDetails extends JPanel implements ActionListener {
    private JTextField name, surname, mobileNumber, address;
    private TextArea note;
    private JSpinner datePicker, DOB;
    private JButton submit, reset, back;
    private JLabel nameL, surnameL, mobileNumberL, dateL, numberOfHoursL, doctor, noteL, DOBL,requiredL;
    private final JFrame container;
    private JComboBox<String> doctorDropDown, hoursDropDown;
    private Date date1,date2;

    public EnterPatientDetails(JFrame container){
        this.container = container;
        componentInitialize();
        addDetailsForm();

        back.addActionListener(this);
        reset.addActionListener(this);
        submit.addActionListener(this);
    }
    private void addDetailsForm(){

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        setBackground(Color.white);

        gbc.gridheight =1;

        gbc.insets = new Insets(10,30,10,30);
        gbc.gridwidth =1;
        gbc.gridx =0;
        gbc.gridy =1;
        add(doctor, gbc);

        gbc.gridx =1;
        add(doctorDropDown, gbc);

        gbc.gridx =0;
        gbc.gridy =2;
        add(nameL, gbc);

        gbc.gridx =1;
        add(name, gbc);

        gbc.gridx =0;
        gbc.gridy =3;
        add(surnameL, gbc);

        gbc.gridx =1;
        add(surname, gbc);

        gbc.gridx =1;
        add(address, gbc);

        gbc.gridx =0;
        gbc.gridy =5;
        add(mobileNumberL, gbc);

        gbc.gridx =1;
        add(mobileNumber, gbc);

        gbc.gridx =0;
        gbc.gridy =6;
        add(DOBL, gbc);

        gbc.gridx =1;
        add(DOB, gbc);

        gbc.gridx =0;
        gbc.gridy =7;
        add(dateL, gbc);

        gbc.gridx =1;
        add(datePicker, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(numberOfHoursL, gbc);

        gbc.gridx = 1;
        add(hoursDropDown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        add(noteL, gbc);

        gbc.gridx = 1;
        add(note, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        add(requiredL, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        add(submit, gbc);

        gbc.gridx = 1;
        add(reset, gbc);

        gbc.insets = new Insets(10,30,30,30);
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 13;
        add(back, gbc);
    }
    private Consultation makeNewAppointment() throws ParseException {
        Consultation c = null;

        String doctorFullName = (String) doctorDropDown.getSelectedItem();
        String patientFirstName = name.getText();
        String patientSurName = surname.getText();

        String mobileNumberString = mobileNumber.getText();
        Date DOB_Date = (Date) DOB.getValue();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dob_Date =formatter.format(DOB_Date);
        Date date = (Date) datePicker.getValue();
        int hours = Integer.parseInt(String.valueOf(hoursDropDown.getSelectedIndex()));

        String notes = note.getText();

        assert doctorFullName != null;
        try {
            if (!doctorFullName.equals("None") ) {
                Doctor doctorSelectByCustomer = Doctor.findDoctorByFullName(doctorFullName);
                Doctor selectedDoctor = Doctor.randomlySelectAnotherDoctor(doctorSelectByCustomer, date, this);

                Date date1 = new Date();
                date1 = Consultation.getEndTime(date1, 1);

                if (selectedDoctor != null && hours != 0 && date.compareTo(date1) >= 0 && !patientSurName.equals("") && !patientFirstName.equals("")) {
                    Patient patient = Patient.isCustomer(patientFirstName, patientSurName);
                    if (patient != null) {
                        c = new Consultation(patient, selectedDoctor, date, hours, notes);
                    } else {
                        c=new Consultation(patientFirstName,patientSurName,dob_Date,mobileNumberString, notes,date,hours,selectedDoctor);
                    }
                }

                else if (patientSurName.equals("") || patientFirstName.equals("")) {
                    JOptionPane.showConfirmDialog(this, "Required fields are empty", "Warning", JOptionPane.OK_CANCEL_OPTION);
                }
                else if (mobileNumberString.trim().length()!=10) {
                    JOptionPane.showConfirmDialog(this, "Please enter correct mobile number", "Warning", JOptionPane.OK_CANCEL_OPTION);
                }
                else if (date.before(date1)) {
                    JOptionPane.showConfirmDialog(this, "Reservation should be made at least an hour from now.", "Warning", JOptionPane.OK_CANCEL_OPTION);
                }
                else if (hours == 0) {
                    JOptionPane.showConfirmDialog(this, "Please select hours you booking", "Warning", JOptionPane.OK_CANCEL_OPTION);
                }
                else {
                    JOptionPane.showConfirmDialog(this, "All doctors in that specialization category are reserved for that time range. Please select another time", "Warning", JOptionPane.OK_CANCEL_OPTION);
                }
            }  else
                JOptionPane.showConfirmDialog(this, "Please select a doctor", "Warning", JOptionPane.OK_CANCEL_OPTION);
        }catch (NumberFormatException e){
            JOptionPane.showConfirmDialog(this, "Please enter correct mobile number", "Warning", JOptionPane.OK_CANCEL_OPTION);
        }

        return c;
    }

    private void resetAllInputField(){
        date2 = new Date();
        date1 = new Date();
        doctorDropDown.setSelectedItem("None");
        name.setText("");
        surname.setText("");
        mobileNumber.setText("");
        DOB.setValue(new Date());
        datePicker.setValue(new Date());
        hoursDropDown.setSelectedIndex(0);
        note.setText("");
    }
    private void componentInitialize() {

        date1 = new Date();
        SpinnerDateModel sm1 = new SpinnerDateModel(date1, null, null, Calendar.DAY_OF_YEAR);
        datePicker = new JSpinner(sm1);
        JSpinner.DateEditor de1 = new JSpinner.DateEditor(datePicker, "  yyyy : MM : dd || hh:mm a  ");
        datePicker.setEditor(de1);

        date2 = new Date();
        SpinnerDateModel sm2 = new SpinnerDateModel(date2, null, null, Calendar.DAY_OF_YEAR);
        DOB = new JSpinner(sm2);
        JSpinner.DateEditor de2 = new JSpinner.DateEditor(DOB, "  yyyy : MM : dd  ");
        DOB.setEditor(de2);

        String[] doctorList = new String[WestminsterSkinConsultationManager.doctors.size()+1];

        Doctor[] d = WestminsterSkinConsultationManager.doctors.toArray(new Doctor[0]);
        doctorList[0] = "None";
        for (int i=0;i< d.length;i++) {
            if (d[i] !=null) doctorList[i+1] = d[i].getName()+" "+d[i].getSurname();
        }
        doctorDropDown = new JComboBox<>(doctorList);

        String[] hour = new String[]{"0","1","2","3","4","5","6","7","8","9","10",};
        hoursDropDown = new JComboBox<>(hour);

        name = new JTextField(10);
        surname = new JTextField(10);
        mobileNumber = new JTextField(10);
        address = new JTextField(10);
        note = new TextArea(3,2);
        nameL = new JLabel(         "First Name     :", SwingConstants.LEFT);
        surnameL = new JLabel(      "Surname        :", SwingConstants.LEFT);
        mobileNumberL = new JLabel( "Mobile Number  :", SwingConstants.LEFT);
        dateL = new JLabel(         "Booking date and time:", SwingConstants.LEFT);
        DOBL = new JLabel(         "Date Of Birth  :", SwingConstants.LEFT);
        submit = new JButton("Submit");
        reset = new JButton("Reset");
        back = new JButton("Back");
        doctor = new JLabel(        "Doctor         :");
        noteL = new JLabel(         "Add a note*    :");
        numberOfHoursL = new JLabel("Reservation period(Hours) :");
        requiredL = new JLabel("*not required");

        submit.setBackground(Color.lightGray);
        reset.setBackground(Color.lightGray);
        back.setBackground(Color.darkGray);
        submit.setForeground(Color.black);
        reset.setForeground(Color.black);
        back.setForeground(Color.WHITE);

        nameL.setForeground(Color.black);
        surnameL.setForeground(Color.black);
        mobileNumberL.setForeground(Color.black);
        dateL.setForeground(Color.black);
        DOBL.setForeground(Color.black);
        numberOfHoursL.setForeground(Color.black);
        doctor.setForeground(Color.black);
        noteL.setForeground(Color.black);
        requiredL.setForeground(Color.black);
        UIManager.put("OptionPane.background", Color.white);
        UIManager.put("Panel.background", Color.white);
        UIManager.put("Panel.setForeground", Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WestminsterSkinConsultationManager manager= new WestminsterSkinConsultationManager();
        if (e.getSource()==submit) {
            try {
                Consultation c = makeNewAppointment();
                manager.saveConsultation(c);
                if (c!=null) {
                    JOptionPane.showMessageDialog(this, "Consultation was booked. Remember the ID to view consultation. ID: "+c.getPatient().getId(), "Warning", JOptionPane.WARNING_MESSAGE);
                    container.dispose();
                    try {
                        new MainGUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "No reservation was made", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

        }else if (e.getSource()==back){
            container.dispose();
            try {
                new MainGUI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==reset) {
            resetAllInputField();
        }
    }
}
