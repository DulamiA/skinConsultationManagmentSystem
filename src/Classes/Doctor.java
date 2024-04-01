package Classes;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Objects;

public class Doctor extends Person {
    private final String medicalLicenseNo;
    private final String  specialization;

    public Doctor(String name, String surname, String DOB, String mobileNumber, String medicalLicenseNo, String specialization){
        super(name, surname,DOB, mobileNumber);
        this.medicalLicenseNo = medicalLicenseNo;
        this.specialization = specialization;
    }

    public String getMedicalLicenseNo() {
        return medicalLicenseNo;
    }

    public String getSpecialization() {
        return specialization;
    }

    //Method to find a doctor by the full name
    public static Doctor findDoctorByFullName(String fullName){
        for (Doctor doctor: WestminsterSkinConsultationManager.doctors ) {
            String value = doctor.getName()+" "+doctor.getSurname();
            if (fullName.trim().equals(value)) return doctor;
        }
        return null;
    }

    //Method to allocate a doctor randomly if the selected doctor is not available at that time
    public static Doctor randomlySelectAnotherDoctor(Doctor doctor, Date date, Component component)  {
        if ( checkDoctorAvailable(doctor,date)==null) return doctor;
        else {
            for (Doctor d : WestminsterSkinConsultationManager.doctors) {
                if (d==null) continue;
                if (d != doctor && Objects.equals(d.getSpecialization(), doctor.getSpecialization())) {
                    Consultation c = checkDoctorAvailable(d, date);
                    if (c == null) {
                        JOptionPane.showMessageDialog(component, "The doctor is not available at this time.", "Info", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showConfirmDialog(component, "Added randomly another doctor that specializes category.", "Warning", JOptionPane.OK_CANCEL_OPTION);
                        return d;
                    }
                }
            }
        }
        return null;
    }

    //Method to check the selected doctor is available at that time
    public static Consultation checkDoctorAvailable(Doctor doctor,Date date) {
        for (Consultation c : Consultation.getConsultations()) {
            if (doctor.equals(c.getDoctor())) {
                if (date.equals(c.getDate())) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name= " + getName() + " "+getSurname()+" "+
                ",dateOdBirth= " + getDOB() +" "+
                ",mobile number= " + getMobileNumber() +" "+
                "medicalLicenseNo= " + medicalLicenseNo +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
