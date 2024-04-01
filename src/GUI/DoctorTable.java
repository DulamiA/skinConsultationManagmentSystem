package GUI;

import Classes.Doctor;
import Classes.WestminsterSkinConsultationManager;


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DoctorTable extends AbstractTableModel {

    private final String[] columnNames = {"First Name", "Surname","License Number","Specialization"};
    private final ArrayList<Doctor> myList = new ArrayList<>();

    public DoctorTable(){
        for (int i =0; i<WestminsterSkinConsultationManager.doctors.size();i++){
            if(WestminsterSkinConsultationManager.doctors.get(i) !=null){
                myList.add(WestminsterSkinConsultationManager.doctors.get(i));
            }
        }
    }
    @Override
    public int getRowCount() {
        return myList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if (columnIndex == 0) {
            temp = myList.get(rowIndex).getName();
        }
        else if (columnIndex == 1) {
            temp = myList.get(rowIndex).getSurname();
        }
        else if (columnIndex == 2) {
            temp = myList.get(rowIndex).getMedicalLicenseNo();
        }
        else if (columnIndex == 3) {
            temp = myList.get(rowIndex).getSpecialization();
        }
        return temp;
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public ArrayList<Doctor> getMyList() {
        return myList;
    }
}
