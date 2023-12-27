package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
/**
 *
 * @author Rohit
 */
public class Login extends JFrame implements ActionListener{
    
    JButton login, clear, signup;
    JTextField cardTextfield; 
    JPasswordField pinTextfield;
    Login(){
        
        setTitle("AUTOMATED TELLER MACHINE");
        
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70,10,100,100);
        add(label);
        
        JLabel text = new JLabel("Welcome To ATM");
        text.setFont(new Font("Osword",Font.BOLD,40));
        text.setBounds(200,40,400,40);
        add(text);
        
        JLabel cardNo = new JLabel("Card No:");
        cardNo.setFont(new Font("Raleway",Font.BOLD,30));
        cardNo.setBounds(120,150,150,30);
        add(cardNo);
        
        cardTextfield = new JTextField();
        cardTextfield.setBounds(300, 150, 230, 30);
        cardTextfield.setFont(new Font("Arial",Font.BOLD,14));
        add(cardTextfield);
        
        JLabel pin = new JLabel("PIN");
        pin.setFont(new Font("Raleway",Font.BOLD,30));
        pin.setBounds(120,220,250,30);
        add(pin);
        
        pinTextfield = new JPasswordField();
        pinTextfield.setBounds(300, 220, 230, 30);
        pinTextfield.setFont(new Font("Arial",Font.BOLD,14));
        add(pinTextfield);
        
        login = new JButton("SIGN IN");
        login.setBounds(300, 300,100,30);
//        login.setBackground(Color.black);
//        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);
        
        clear = new JButton("CLEAR");
        clear.setBounds(430, 300,100,30);
        clear.addActionListener(this);
        add(clear);
        
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350,230,30);
        signup.addActionListener(this);
        add(signup);
        
        getContentPane().setBackground(Color.white);
        
        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == clear){
            cardTextfield.setText("");
            pinTextfield.setText("");
        }else if(ae.getSource() == login){
            Conn conn = new Conn();
            String cardnumber = cardTextfield.getText();
            String pinnumber = pinTextfield.getText();
            String query = "select * from login where cardnumber = '"+cardnumber+"'and pin = '"+pinnumber+"'";
            try{
                ResultSet rs = conn.s.executeQuery(query);
                if(rs.next()){
                 setVisible(false);
                 new Transactions(pinnumber).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, " Incorrect Card Number or Pin");
                }
            }catch(Exception e){
                System.out.println(e);
            }
                
        }else if(ae.getSource() == signup){
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
    
    
    public static void main(String args[]){
        new Login();
    }
    
}
