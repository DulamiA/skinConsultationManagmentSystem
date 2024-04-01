package GUI;

import Classes.Consultation;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ConsultationDetails extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    private final List<Consultation> consultationList;
    private JButton ok;

    public ConsultationDetails(List<Consultation> consultationList) throws ParseException {
        JPanel p = new JPanel();
        this.consultationList = consultationList;

        JPanel consultationTablePanel = consultationTable();


        componentInitialization();
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.insets = new Insets(0,20,30,1200);
        gbc.gridwidth =1;
        gbc.gridy = 14;
        gbc.gridx = 1;
        p.add(ok, gbc);

        ok.addActionListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        gbc.insets = new Insets(0,0,30,0);
        gbc.gridy =0;
        gbc.gridx =1;
        p.add(consultationTablePanel,gbc);

        p.setForeground(Color.BLACK);
        p.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        p.setBackground(Color.WHITE);
        add(p);
        setUndecorated(true);
        pack();
        setLocation(0,100);
        setVisible(true);
    }

    private JPanel consultationTable() {

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(40,20,40,40);


        ConsultationTable consultationTable = new ConsultationTable(this.consultationList);
        JTable table = new JTable(consultationTable);
        JScrollPane scrollPane = new JScrollPane(table);

        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);

        table.setFillsViewportHeight(true);
        scrollPane.setBackground(Color.WHITE);
        mainPanel.setBackground(Color.WHITE);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setMinWidth(60);
        columnModel.getColumn(2).setMinWidth(60);
        columnModel.getColumn(4).setMinWidth(80);
        columnModel.getColumn(5).setMinWidth(90);
        columnModel.getColumn(7).setMinWidth(90);
        columnModel.getColumn(9).setMinWidth(200);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        columnModel.getColumn(0).setCellRenderer( centerRenderer );
        columnModel.getColumn(1).setCellRenderer( centerRenderer );
        columnModel.getColumn(2).setCellRenderer( centerRenderer );
        columnModel.getColumn(3).setCellRenderer( centerRenderer );
        columnModel.getColumn(4).setCellRenderer( centerRenderer );
        columnModel.getColumn(8).setCellRenderer( centerRenderer );
        table.setGridColor(Color.darkGray);

        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width+800,200));
        table.setRowHeight((scrollPane.getPreferredSize().height)/5);

        gbc.gridwidth = 2;
        gbc.gridy =1;
        gbc.gridx =0;
        mainPanel.add(scrollPane, gbc);

        return mainPanel;
    }
    private void componentInitialization() {
        ok = new JButton("OK");
        ok.setForeground(Color.WHITE);
        ok.setBackground(Color.DARK_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==ok){
            dispose();
            try {
                new MainGUI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
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
}
