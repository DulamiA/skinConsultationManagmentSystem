package GUI;

import Classes.Consultation;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class ConsultationTable extends AbstractTableModel {

    private final String[] columnNames = {"Patient ID","First Name", "Surname","Mobile No","Date Of Birth","Booking Date n Time","Doctor name","Doctor Specialization","Cost","Notes"};
    private final List<Consultation> myList;

    public ConsultationTable(List<Consultation> consultationList){
        myList =consultationList;
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
        String pattern = "MM/dd/yyyy  HH:mm a";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Object temp = null;
        if (columnIndex == 0) {
            temp = myList.get(rowIndex).getPatient().getId();
        }
        else if (columnIndex == 1) {
            temp = myList.get(rowIndex).getPatient().getName();
        }
        else if (columnIndex == 2) {
            temp = myList.get(rowIndex).getPatient().getSurname();
        }
        else if (columnIndex == 3) {
            temp = myList.get(rowIndex).getPatient().getMobileNumber();
        }
        else if (columnIndex == 4) {
            temp = myList.get(rowIndex).getPatient().getDOB();
        }
        else if (columnIndex == 5) {
            temp = dateFormat.format(myList.get(rowIndex).getDate());
        }
        else if (columnIndex == 6) {
            temp = myList.get(rowIndex).getDoctor().getName()+" "+ myList.get(rowIndex).getDoctor().getSurname();
        }
        else if (columnIndex == 7) {
            temp = myList.get(rowIndex).getDoctor().getSpecialization();
        }
        else if (columnIndex == 8) {
            temp = myList.get(rowIndex).getCost();
        }
        else if (columnIndex == 9) {
            temp = myList.get(rowIndex).getNotes();
        }
        return temp;
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }
}
