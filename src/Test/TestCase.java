package Test;

import Classes.Doctor;
import Classes.WestminsterSkinConsultationManager;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCase {
    static WestminsterSkinConsultationManager westminsterSkinConsultationManager = new WestminsterSkinConsultationManager();
    @Test
    public void test1_addNewDoctor(){
        Doctor doctor = new Doctor("Kosala","Kaludewa","09/08/1966","0987654321","1515","Surgeon");
        String test = westminsterSkinConsultationManager.addNewDoctor(doctor);
        assertEquals("Added successfully",test);
    }
    @Test
    public void test4_deleteDoctor(){
        String licenceNumber = "1515";
        String test = westminsterSkinConsultationManager.deleteDoctor(licenceNumber);
        assertEquals("Deleted successfully",test);
    }
    @Test
    public void test3_displayDoctors(){
        String test = westminsterSkinConsultationManager.displayDoctors();
        assertEquals("Doctors displayed successfully",test);
    }
    @Test
    public void test2_saveDoctors() throws IOException {
        FileWriter fw1 = new FileWriter("TestDoctorDetails.txt",false);
        String test = westminsterSkinConsultationManager.saveInFile(fw1);
        assertEquals("Saved successfully",test);
    }
    @Test
    public void test5_saveDoctorsAfterDeleting() throws IOException {
        FileWriter fw1 = new FileWriter("TestDoctorDetails.txt",false);
        String test = westminsterSkinConsultationManager.saveInFile(fw1);
        assertEquals("Saved successfully",test);
    }
}
