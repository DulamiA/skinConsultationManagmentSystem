package Classes;

import java.io.FileWriter;

public interface SkinConsultationManager {
    String addNewDoctor(Doctor doctor);
    String deleteDoctor(String licenceNo);
    String displayDoctors();
    String saveInFile(FileWriter fileWriter);
    void saveConsultation(Consultation consultation);
}
