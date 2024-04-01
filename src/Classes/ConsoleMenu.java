package Classes;

import GUI.MainGUI;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

import static Classes.WestminsterSkinConsultationManager.doctors;

public class
ConsoleMenu {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        manager.readInformation();
        manager.readConsultationInformation();
        //Display messages to the manager
        while (true){
            System.out.println("""
                    
                    1. Add a Doctor
                    2. Delete a Doctor
                    3. Display list of Doctors
                    4. Save data to the file
                    5. Open GUI
                    6. Exit
                    """);
            System.out.print("Enter your choice :");
            String choice = sc.next();
            switch (choice){
                //Main functions of the manager
                case "1" :
                    //Check if the doctors in the centre is already 10
                    if(doctors.size()==10){
                        System.out.println("Maximum number of doctors have been allocated.");
                    }
                    else {
                        System.out.println("Enter the first name: ");
                        Scanner sc1 = new Scanner(System.in);
                        String firstName = sc1.nextLine();
                        while (!Pattern.matches("[a-zA-Z]+", firstName)) {
                            System.out.println("Enter a valid name! Enter again:");
                            firstName = sc1.nextLine();
                        }
                        System.out.println("Enter the surname: ");
                        String surName = sc1.nextLine();
                        while (!Pattern.matches("[a-zA-Z]+", surName)) {
                            System.out.println("Enter a valid name! Enter again:");
                            surName = sc1.nextLine();
                        }
                        System.out.println("Enter date of birth (DD/MM/YYYY): ");
                        String dateOfBirth = sc1.nextLine();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        while (!Pattern.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}", dateOfBirth)) {
                            System.out.println("Enter a valid date! Enter again:");
                            dateOfBirth = sc1.nextLine();
                        }
                        Date date = null;
                        try {
                            //Check the entered DOB is in the given format
                            date = simpleDateFormat.parse(dateOfBirth);
                        } catch (ParseException e) {
                            System.out.println("Not in the format.");
                        }
                        dateOfBirth = simpleDateFormat.format(date);
                        System.out.println("Enter the mobile number: ");
                        String mobileNo = sc1.nextLine();
                        //Check entered mobile number has 10 numbers
                        while (!Pattern.matches("[0-9]{10}", mobileNo)) {
                            System.out.println("Enter a valid mobile number! Enter again:");
                            mobileNo = sc1.nextLine();
                        }
                        System.out.println("Enter the medical licence number: ");
                        String licenceNo = sc1.nextLine();
                        while (!Pattern.matches("[0-9]{4}", licenceNo)) {
                            System.out.println("Enter a valid licence number! Enter again:");
                            licenceNo = sc1.nextLine();
                        }
                        for (Doctor d : doctors) {
                            if (d.getMedicalLicenseNo().equals(licenceNo)) {
                                System.out.println("A doctor is already exists with this licence number. Enter a correct licence number!");
                                System.out.println("Enter the medical licence number: ");
                                licenceNo = sc1.nextLine();
                            }
                        }
                        System.out.println("Enter specialisation: ");
                        String specialisation = sc1.nextLine();
                        while (!Pattern.matches("[a-zA-Z ]+", specialisation)) {
                            System.out.println("Enter a valid specialisation! Enter again:");
                            specialisation = sc1.nextLine();
                        }
                        Doctor doc = new Doctor(firstName, surName, dateOfBirth, mobileNo, licenceNo, specialisation);
                        manager.addNewDoctor(doc);
                    }
                    break;
                case "2" :
                    if (doctors.size()==0){
                        System.out.println("The centre doesn't have any doctor.");
                    }
                    else {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("Enter the licence number of the doctor which will be deleted: ");
                        String licenceNo = sc2.nextLine();
                        manager.deleteDoctor(licenceNo);
                    }
                    break;
                case "3" :
                    manager.displayDoctors();
                    break;
                case "4" :
                    FileWriter fw = new FileWriter("DoctorDetails.txt",false);
                    manager.saveInFile(fw);
                    break;
                //Opens the GUI
                case "5" :
                    new MainGUI();
                    break;
                //Exits from the program
                case "6" :
                    System.exit(0);
                    break;
                default :
                    System.out.println("Invalid input. Try again!\n");
                    break;
            }
        }
    }
}