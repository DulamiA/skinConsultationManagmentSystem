package GUI;

import Classes.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class MainGUI extends JFrame implements MouseListener, MouseMotionListener, ActionListener {
    private JButton sort;
    private DoctorTable doctorTable;

    public MainGUI() throws IOException{
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30,30,30,10);


        JPanel doctorTablePanel = doctorDetailsTable();
        JPanel mainButtonPanel = new AddAndView(this);

        gbc.gridy =0;
        gbc.gridx =0;
        panel.add(mainButtonPanel, gbc);

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridy =0;
        gbc.gridx =1;
        panel.add(doctorTablePanel,gbc);

        sort.addActionListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        add(panel);
        panel.setBackground(Color.LIGHT_GRAY);
        setUndecorated(true);
        pack();
        setLocation(200,0);
        setVisible(true);
    }

    //Method to add the doctor details to the panel
    private JPanel doctorDetailsTable() {
        JLabel label = new JLabel("Sort doctor names alphabetically :");
        sort = new JButton("Sort");

        sort.setBackground(Color.DARK_GRAY);
        sort.setForeground(Color.WHITE);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Verdana", Font.BOLD , 14));

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30,30,30,10);



        doctorTable = new DoctorTable();
        JTable table = new JTable(doctorTable);
        JScrollPane scrollPane = new JScrollPane(table);

        table.setRowHeight(30);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);

        table.setFillsViewportHeight(true);
        scrollPane.setBackground(Color.WHITE);
        mainPanel.setBackground(Color.WHITE);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60);
        columnModel.getColumn(1).setMinWidth(60);
        columnModel.getColumn(3).setMinWidth(100);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        columnModel.getColumn(0).setCellRenderer( centerRenderer );
        columnModel.getColumn(1).setCellRenderer( centerRenderer );
        columnModel.getColumn(2).setCellRenderer( centerRenderer );
        columnModel.getColumn(3).setCellRenderer( centerRenderer );
        table.setGridColor(Color.darkGray);

        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width,scrollPane.getPreferredSize().height+45));
        table.setRowHeight((scrollPane.getPreferredSize().height)/10);

        gbc.gridy =2;
        gbc.gridx =0;
        mainPanel.add(label, gbc);

        gbc.gridx =1;
        gbc.gridy =2;
        mainPanel.add(sort, gbc);

        gbc.gridwidth = 2;
        gbc.gridy =1;
        gbc.gridx =0;
        mainPanel.add(scrollPane, gbc);

        return mainPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    int mouseX, mouseY;
    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setLocation(getX()+(e.getX()-mouseX),getY()+(e.getY()-mouseY));
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    public static class SortByName implements Comparator<Doctor> {

        @Override
        public int compare(Doctor d1, Doctor d2) {
            return d1.getName().compareTo(d2.getName());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== sort) {
            ArrayList<Doctor> d = doctorTable.getMyList();
            d.sort(new SortByName());
            doctorTable.fireTableDataChanged();
        }
    }
}
