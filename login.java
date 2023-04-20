import javax.swing.*;
import java.awt.*;

import java.io.*;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

class SignUp extends JFrame
{
    JTextField t1;
    JPasswordField t2;
    JButton b1;
    SignUp()
   { 
    setTitle("SignUp Page");
    setLayout(null);
    JLabel l1=new JLabel("Name");
    Label l2=new Label("Password");
    Font boldFont = new Font(l1.getFont().getName(), Font.BOLD, 16);
    getContentPane().setBackground(new Color(173, 216, 230));
    l1.setFont(boldFont);
    l2.setFont(boldFont);
    t1 = new JTextField(60);
    t2 = new  JPasswordField (20);
    b1 = new JButton("Submit");
    
   

    l1.setBounds(50,20,80,30);
    l2.setBounds(50,60,80,30);
    t1.setBounds (200,20,120,30);
    t2.setBounds (200,60,120,30);
    b1.setBounds (100, 100,80,30);
   
    add(t1);
    add(t2);
    add (b1);
    add(l1);
    add(l2);


    b1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae)
        {
            String name = t1.getText().trim();
            String password = new String(t2.getPassword()).trim();
            if (name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }

            try
            {
            FileWriter fw=new FileWriter("login.txt",true);
            fw.write(t1.getText()+"\t"+t2.getText()+"\n");
            fw.close();
            JFrame f =new JFrame();
            JOptionPane.showMessageDialog(f, "Registration completed");
            dispose();   
            }
            catch(Exception e)
            {}
        }
    });
    }
}
class Login extends JFrame{

    JTextField t1;
    JPasswordField t2;
    JButton b1,b2;
    Login()
{
    setTitle("Login Page");
    setLayout(null); 
    setDefaultCloseOperation (EXIT_ON_CLOSE);
    getContentPane().setBackground(new Color(173, 216, 230));
    
    setVisible(true);
    t1 = new JTextField(60);
    t2 = new  JPasswordField (60);
    b1 = new JButton("Login");
    b2= new JButton("SignUp");
    
    JLabel l1=new JLabel("Name");
    Label l2=new Label("Password");
    JLabel l4=new JLabel("Agree the terms and conditions");
    Font boldFont = new Font(l1.getFont().getName(), Font.BOLD, l1.getFont().getSize());
    l1.setFont(boldFont);
    l2.setFont(boldFont);

    JCheckBox checkBox = new JCheckBox("Check me!");
    add(checkBox);
    pack();
    setVisible(true);


    l1.setBounds(50,30,40,30);
    l2.setBounds(50,70,60,30);
    t1.setBounds(150,30,120,30);
    t2.setBounds(150,70,120,30);
    b1.setBounds(170,150,80,30);
    b2.setBounds(170,180,80,30);
    checkBox.setBounds(20, 107, 20, 20);
    Label l3=new Label("");
    l3.setBounds(300,80,200,30);

    l4.setBounds(42,107,200,20);
    b1.setBackground(Color.GREEN);
    b2.setBackground(Color.BLUE);
    add(l3);
    add(t1);
    add(t2);
    add(l1);
    add(l2);
    add(b1);
    add(b2);
    add(l4);

    

    checkBox.addActionListener(new ActionListener()
     {
        public void actionPerformed(ActionEvent e) {
           if (checkBox.isSelected()) {
              b1.setEnabled(true);
           } else {
              b1.setEnabled(false); 
           }
        }
     });

    b1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            boolean matched = false;
            String uname = t1.getText().toString();
            String pwd = t2.getText().toString();
            try {
                FileReader fr = new FileReader("login.txt");
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.equals(uname + "\t" + pwd)) {
                        matched = true;
                        break;
                    }
                }
                fr.close();
            } 
            catch (IOException e1) {
                e1.printStackTrace();
            }
          if(matched)
          {
            dispose();
            new Airline();
          }
          else
          {
            l3.setText("Invalid Username or Password");
          }

        }

    });    
    b2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
               SignUp s=new SignUp();
               s.setVisible(true);
               s.setBounds(200,200,500,300);
        }
    });

    }
  
    
}
    class LoginDemo{
    public static void main(String[] args) {  
    Login l = new Login();
    l.setBounds (400, 200, 500, 300);
    l.setVisible(true);       
    }    
}