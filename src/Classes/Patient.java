package Classes;

import java.util.Objects;

public class Patient extends Person {
    private String id;

    public Patient(String name, String surname, String DOB, String mobileNumber){
        super(name, surname, DOB, mobileNumber);
        setId();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId() {
        int min = 1001;
        int max = 9999;
        int genPatientId = (int) (Math.random() * (max - min + 1) + min);
        id =String.valueOf(genPatientId);
    }

    //Method to find if the patient consulted from the centre previously
    public static Patient isCustomer(String firstName, String lastName){
        for (Consultation c: Consultation.getConsultations()){
            if (Objects.equals(c.getPatient().getName(), firstName) &&
                    Objects.equals(c.getPatient().getSurname(), lastName)) {
                return c.getPatient();
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return "Classes.Patient{" +
                "id='" + id + '\'' +
                '}';
    }
}
