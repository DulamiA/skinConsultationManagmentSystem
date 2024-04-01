package Classes;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    public static List<Doctor> doctors = new ArrayList<>(10);

    //Method to add doctors to the system
    @Override
    public String addNewDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("The doctor has been added to the Centre.");
        return "Added successfully";
    }

    //Method to delete a doctor from the medical licence number
    @Override
    public String  deleteDoctor(String licenceNo) {
        boolean doc = false;
        for (Doctor d:doctors){
            if(d.getMedicalLicenseNo().equals(licenceNo)){
                System.out.println(d);
                doctors.remove(d);
                System.out.println("The information of the doctor has been deleted!");
                System.out.println("The number of doctors currently available: "+doctors.size());
                doc = true;
                break;
            }
        }
        if (!doc)System.out.println("The doctor with licence number - "+licenceNo+" cannot be found!");
        return "Deleted successfully";
    }

    //Method to compare two doctor surnames and return the name that comes first
    public static class SortBySurname implements Comparator<Doctor>{
        @Override
        public int compare(Doctor d1, Doctor d2) {
            if (d1.getSurname().equals(d2.getSurname())){
                return d1.getName().compareTo(d2.getName());
            }
            return d1.getSurname().compareTo(d2.getSurname());
        }
    }

    //Method to display doctors in the console
    @Override
    public String displayDoctors() {
        if (doctors.size()==0){
            System.out.println("The centre doesn't have any doctor to display.");
            return "The centre doesn't have any doctors";
        }
        else {
            doctors.sort(new SortBySurname());
            System.out.println("                                -Doctor Information-");
            System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-10s | %-10s       %n","First Name","Surname","DOB","Mobile No","LicenceNo","Specialization");
            System.out.println("-----------------------------------------------------------------------------------------");
            for (Doctor d : doctors){
                System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-10s | %-10s %n",d.getName(),d.getSurname(),d.getDOB(),d.getMobileNumber(),d.getMedicalLicenseNo(),d.getSpecialization());
            }
        }
        return "Doctors displayed successfully";
    }

    //Method to write doctor data to a file
    @Override
    public String saveInFile(FileWriter fileWriter) {
        PrintWriter pw = new PrintWriter(fileWriter);
        if (doctors.size()==0){
            System.out.println("The centre doesn't have any doctor to save.");
        }else{
            for (Doctor d:doctors){
                String docDetails = d.getName()+"|"+d.getSurname()+"|"+d.getDOB()+"|"+d.getMobileNumber()+"|"+d.getMedicalLicenseNo()+"|"+d.getSpecialization();
                pw.println(docDetails);
            }
            pw.close();
            System.out.println("Doctor details have been saved.");
        }
        return "Saved successfully";
    }

    //Method to read doctor information from the file
    public void readInformation(){
        try {
            FileReader fr = new FileReader("DoctorDetails.txt");
            BufferedReader br = new BufferedReader(fr);
            String data = br.readLine();
            while (data!= null){
                String[] info = data.split("\\|");
                String firstName = info[0];
                String surname = info[1];
                String dob = info[2];
                String mobileNo = info[3];
                String licenceNo = info[4];
                String specialization = info[5];

                doctors.add(new Doctor(firstName,surname,dob,mobileNo,licenceNo,specialization));
                data = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to write consultation data to a file
    @Override
    public void saveConsultation(Consultation consultation) {
        try {
            FileWriter fw = new FileWriter("ConsultationDetails.txt", false);
            PrintWriter pw = new PrintWriter(fw);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm");
            if (Consultation.getConsultations().size() == 0) {
                System.out.println("The centre doesn't have any consultation to save.");
            } else {
                for (Consultation c : Consultation.getConsultations()) {
                    Patient p = c.getPatient();
                    String encryptedNotes = encryptNotes(c.getNotes());
                    String conDetails = p.getId() + " , " + p.getName() + " , " + p.getSurname() + " , " + p.getDOB() + " , " + p.getMobileNumber() + " , " + encryptedNotes + " , " + formatter.format(c.getDate()) + " , " + c.getHours() + " , " +c.getDoctor().getName()+" , "+c.getDoctor().getSurname();
                    pw.println(conDetails);
                }
                pw.close();
            }
        } catch (IOException e) {
            System.out.println("File doesn't exist.");
        }
    }

    //Method to read consultation information from the file
    public void readConsultationInformation(){
        try {
            FileReader fr = new FileReader("ConsultationDetails.txt");
            BufferedReader br = new BufferedReader(fr);
            String data = br.readLine();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm");
            while (data!= null){
                String[] info = data.split(" , ");
                String pId = info[0];
                String firstName = info[1];
                String surname = info[2];
                String dob = info[3];
                String mobileNo = info[4];
                String notes = info[5];
                Date date = formatter.parse(info[6]);
                int hours = Integer.parseInt(info[7]);
                String docName = info[8];
                String docSurname = info[9];
                String doctorFullName = docName+" "+docSurname;

                String decryptedNotes = decryptNotes(notes);
                Consultation c;
                Doctor doctor = Doctor.findDoctorByFullName(doctorFullName);
                Patient patient = Patient.isCustomer(firstName, surname);
                if (patient != null) {
                    c = new Consultation(patient, doctor, date, hours, decryptedNotes);
                } else {
                    c=new Consultation(firstName,surname,dob,mobileNo, decryptedNotes,date,hours,doctor);
                }
                c.getPatient().setId(pId);
                data = br.readLine();

            }

        } catch (IOException e) {
            System.out.println("File doesn't exist.");
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //Method to encrypt consultation notes using a char array
    public String encryptNotes(String notes){
        StringBuilder encryptedNotes = new StringBuilder();
        if (notes == null){
            encryptedNotes = new StringBuilder("null");
        }else {
            char[] chars = notes.toCharArray();
            for (char c : chars) {
                c += 10;
                encryptedNotes.append(c);
            }
        }
        return encryptedNotes.toString();
    }

    //Method to decrypt consultation
    public String decryptNotes(String notes) {
        StringBuilder decryptedNotes = new StringBuilder();
        if (!Objects.equals(notes, "null")) {
            char[] chars = notes.toCharArray();
            for (char c : chars) {
                c -= 10;
                decryptedNotes.append(c);
            }
        }
        return decryptedNotes.toString();
    }
}
