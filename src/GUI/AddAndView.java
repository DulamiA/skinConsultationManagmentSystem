package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAndView extends JPanel implements ActionListener {
    private JButton viewDetail, addConsultation, close;
    private JLabel viewDetailL, addConsultationL;
    private final JFrame container;

    public AddAndView(JFrame container) {
        this.container = container;
        componentInitialize();
        addDetailsForm();

        viewDetail.addActionListener(this);
        addConsultation.addActionListener(this);
        close.addActionListener(this);
    }
    private void addDetailsForm(){
        JLabel title1 = new JLabel("Westminster Skin Consultation", SwingConstants.CENTER),
        title2 = new JLabel("Centre",SwingConstants.CENTER);
        title1.setForeground(Color.BLACK);
        title1.setFont(new Font("Serif", Font.BOLD, 25));
        setLayout(new GridBagLayout());
        title2.setForeground(Color.BLACK);
        title2.setFont(new Font("Serif", Font.BOLD, 25));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(title1,BorderLayout.NORTH);
        panel.add(title2);
        setBackground(Color.WHITE);
        gbc.insets = new Insets(10,0,20,0);
        setPreferredSize(new Dimension(350,630));

        gbc.gridwidth = 4;
        gbc.gridheight =1;

        gbc.gridy =0;
        add(panel, gbc);

        gbc.insets = new Insets(50,30,0,0);
        gbc.gridwidth =1;
        gbc.gridy =1;
        gbc.gridx =0;
        add(addConsultationL, gbc);

        gbc.insets = new Insets(50,30,0,0);
        gbc.gridy =1;
        gbc.gridx =1;
        add(addConsultation, gbc);

        gbc.insets = new Insets(30,30,0,0);
        gbc.gridy =2;
        gbc.gridx =0;
        add(viewDetailL, gbc);

        gbc.insets = new Insets(30,30,0,0);
        gbc.gridy =2;
        gbc.gridx =1;
        add(viewDetail, gbc);

        gbc.insets = new Insets(200,30,0,30);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(close, gbc);

    }
    private void componentInitialize()  {

        addConsultationL = new JLabel("Add an appointment :", SwingConstants.LEFT);
        viewDetailL = new JLabel("View your \nappointment :", SwingConstants.LEFT);
        viewDetail = new JButton("View");
        addConsultation = new JButton("Add");
        close = new JButton("Close");

        close.setBackground(Color.DARK_GRAY);
        viewDetail.setBackground(Color.lightGray);
        addConsultation.setBackground(Color.lightGray);
        close.setForeground(Color.WHITE);

        addConsultationL.setForeground(Color.BLACK);
        viewDetailL.setForeground(Color.BLACK);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==viewDetail){
            container.dispose();
            new NameAndIDEnter();
        }

        else if (e.getSource()==addConsultation) {
            container.dispose();
            new AddConsultation();
        }

        else if (e.getSource() == close) {
            System.exit(0);
        }
    }
}
