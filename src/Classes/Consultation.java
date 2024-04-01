package Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Consultation {
    private final Patient patient;
    private final Doctor doctor;
    private final Date date;
    private final int hours;
    private final double cost;
    private String notes;


    private static final ArrayList<Consultation> consultations = new ArrayList<>();

    public Consultation(Patient patient, Doctor doctor, Date date, int hours, String notes) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.hours = hours;
        this.cost = (25*hours);
        setNotes(notes);
        consultations.add(this);
    }
    public Consultation(String firstName,String lastName, String DOB,String mobileNumber,String notes,Date date, int hours, Doctor doctor) {
        this.cost = (15*hours);
        this.date = date;
        this.doctor = doctor;
        this.patient = new Patient(firstName,lastName,DOB,mobileNumber);
        this.hours = hours;
        setNotes(notes);
        consultations.add(this);
    }

    public Date getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        if (notes.equals("")) this.notes = null;
        else this.notes = notes;
    }

    public double getCost() {
        return cost;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }
    public static ArrayList<Consultation> getConsultations(){return consultations;}

    //Method to get the end time of the consultation
    public static Date getEndTime(Date date, int hours) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd:HH:mm");
        Date myDate = parser.parse(parser.format(date));
        Calendar cal =Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.HOUR_OF_DAY,hours);
        return cal.getTime();
    }

    @Override
    public String toString() {
        return "Classes.Consultation{" +
                "patient=" + patient +
                ", doctor=" + doctor +
                ", date='" + date + '\'' +
                ", time='" + hours + '\'' +
                ", cost='" + cost + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
