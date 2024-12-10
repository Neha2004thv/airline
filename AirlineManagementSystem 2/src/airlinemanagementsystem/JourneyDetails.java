package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener{
    JTable table;
    JTextField pnr;
    JButton show;
    
    public JourneyDetails() {
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/ROY.jpeg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0,0,800,450);
        add(image);
        setLayout(null);
        
        JLabel lblpnr = new JLabel("PNR Details : ");
        lblpnr.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblpnr.setBounds(50, 50, 100, 25);
        image.add(lblpnr);
        
        pnr = new JTextField();
        pnr.setBounds(160, 50, 120, 25);
        image.add(pnr);
        
        show = new JButton("Show Details");
        show.setBackground(Color.WHITE);
        show.setForeground(Color.BLUE);
        show.setFont(new Font("Tahoma", Font.BOLD, 12));
        show.setBounds(290, 50, 120, 25);
        show.addActionListener(this);
        image.add(show);
        
        table = new JTable();
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 800, 150);
        jsp.setBackground(Color.PINK);
        image.add(jsp);
        
        setSize(800, 470);
        setLocation(400, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from reservation where PNR = '"+pnr.getText()+"'");
            
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JourneyDetails();
    }
}