package airlinemanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame  implements ActionListener{
    JButton submit, reset, close;
    JTextField tfusername;
    JPasswordField tfpassword;
    
    public Login() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/painting-plane.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0,2,550,250);
        add(image);
        setLayout(null);
        
        JLabel lblusername = new JLabel("Username : ");
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblusername.setBounds(20, 20, 100, 20);
        image.add(lblusername);
        
        tfusername = new JTextField();
        tfusername.setBounds(130, 20, 200, 25);
        image.add(tfusername);
        
        JLabel lblpassword = new JLabel("Password : ");
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblpassword.setBounds(20, 60, 100, 25);
        image.add(lblpassword);
        
        tfpassword = new JPasswordField();
        tfpassword.setBounds(130, 60, 200, 20);
        image.add(tfpassword);
        
        reset = new JButton("Reset");
        reset.setBounds(40,120,120,20);
        reset.addActionListener(this);
        image.add(reset);
        
        submit = new JButton("Submit");
        submit.setBounds(190, 120, 120, 20);
        submit.addActionListener(this);
        image.add(submit);
        
        close = new JButton("Close");
        close.setBounds(120, 160, 120, 20);
        close.addActionListener(this);
        image.add(close);
        
        
        setSize(450, 250);
        setLocation (600, 250);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == submit) {
            String username = tfusername.getText();
            String password = tfpassword.getText();
            
            try {
                Conn c = new Conn();
                
                String query = "select * from login where username = '"+username+"' and password = '"+password+"'";
                
                ResultSet rs = c.s.executeQuery(query);
                
                if (rs.next()) {
                    new Home();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else if (ae.getSource() == close) {
            setVisible(false);
            
        } else if (ae.getSource() == reset) {
            tfusername.setText("");
            tfpassword.setText("");
        }
    }
    
    public static void main(String[] args) {
        new Login();
    }
}
