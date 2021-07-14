package pointofsale;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener {

    JPanel p1;
    JTextField t1;
    JPasswordField t2;

    login() {

        // adding upper panel of login page
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(Color.BLACK);
        p1.setBounds(0, 0, 1200, 30);
        add(p1);

        JLabel title=new JLabel("Login-Form");
        title.setForeground(Color.WHITE);
        title.setBounds(600,5,200,25);
        title.setFont(new Font("Arial",Font.BOLD,18));
        p1.add(title);
        
        // adding image of the upper panel
        ImageIcon close = new ImageIcon(ClassLoader.getSystemResource("images/first.png"));
        Image close2 = close.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon close3 = new ImageIcon(close2);
        JLabel l1 = new JLabel("");
        l1.setIcon(close3);
        l1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l1.setBounds(1170, 5, 20, 20);
        p1.add(l1);

        // adding background image of the login form
        ImageIcon background = new ImageIcon(ClassLoader.getSystemResource("images/bg.jpg"));
        Image background2 = background.getImage().getScaledInstance(1200, 700, Image.SCALE_DEFAULT);
        ImageIcon background3 = new ImageIcon(background2);
        JLabel l2 = new JLabel("");
        l2.setIcon(background3);
        l2.setBounds(0, 0, 1200, 700);
        add(l2);

        // adding mouselistener to the first.png image
        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.exit(0);
            }
        });

        // setlayout of the login form
        JLabel me = new JLabel("malhotra-engineers");
        me.setBounds(400, 75, 700, 60);
        me.setForeground(Color.CYAN);
        me.setFont(new Font("san-serif", Font.BOLD, 50));
        l2.add(me);

        JLabel l3 = new JLabel("Username");
        l3.setBounds(450, 200, 300, 35);
        l3.setForeground(Color.WHITE);
        l3.setFont(new Font("san-serif", Font.BOLD, 22));
        l2.add(l3);

        t1 = new JTextField();
        t1.setBounds(450, 250, 400, 35);
        t1.setBackground(Color.WHITE);
        t1.setHorizontalAlignment(JTextField.CENTER);
        t1.setFont(new Font("san-serif", Font.BOLD, 22));
        t1.setBorder(BorderFactory.createEmptyBorder());
        l2.add(t1);

        JLabel l4 = new JLabel("Password");
        l4.setBounds(450, 300, 300, 35);
        l4.setForeground(Color.WHITE);
        l4.setFont(new Font("san-serif", Font.BOLD, 22));
        l2.add(l4);

        t2 = new JPasswordField();
        t2.setBounds(450, 350, 400, 35);
        t2.setBackground(Color.WHITE);
        t2.setHorizontalAlignment(JTextField.CENTER);
        t2.setBorder(BorderFactory.createEmptyBorder());
        t2.setFont(new Font("san-serif", Font.BOLD, 22));
        l2.add(t2);

        JButton b1 = new JButton("Login");
        b1.setBounds(450, 430, 400, 35);
        b1.setBackground(new Color(0, 205, 204));
        b1.setForeground(Color.black);
        b1.setFont(new Font("san-serif", Font.BOLD, 23));
        b1.setFocusable(false);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setBorder(BorderFactory.createEmptyBorder());
        b1.addActionListener(this);
        b1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                b1.setBackground(new Color(0, 153, 153));
                b1.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent me) {
                b1.setBackground(new Color(0, 205, 204));
                b1.setForeground(Color.black);
            }
        });
        l2.add(b1);

        JLabel register = new JLabel("Register as a new User Click Here");
        register.setBounds(550, 525, 200, 35);
        register.setForeground(Color.ORANGE);
        register.setFont(new Font("san-serif", Font.BOLD, 12));
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l2.add(register);

        register.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                register.setForeground(Color.white);
            }

            public void mouseExited(MouseEvent me) {
                register.setForeground(Color.ORANGE);
            }

            public void mouseClicked(MouseEvent me) {
                new register().setVisible(true);
                dispose();
            }
        });

        JLabel forgot = new JLabel("Forgot Password Click Here");
        forgot.setBounds(574, 550, 200, 25);
        forgot.setForeground(Color.ORANGE);
        forgot.setFont(new Font("san-serif", Font.BOLD, 12));
        forgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l2.add(forgot);

        forgot.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                forgot.setForeground(Color.white);
            }

            public void mouseExited(MouseEvent me) {
                forgot.setForeground(Color.ORANGE);
            }

            public void mouseClicked(MouseEvent me) {
                new forgotpassword().setVisible(true);
                dispose();
            }
        });

        JLabel l5 = new JLabel("<html>malhotra-engineers is a well known company of construction and builders which also known for its best inventory management system All Rights Reserved 2020 copyright@malhotra-engineers.pvt.Lmt</html>");
        l5.setBounds(50, 550, 1200, 100);
        l5.setForeground(Color.white);
        l5.setFont(new Font("san-serif", Font.BOLD, 11));
        l2.add(l5);

        // setLayout of the Jframe
        setLayout(null);
        setBounds(400, 200, 1200, 700);
        getContentPane().setBackground(Color.gray);
        setUndecorated(true);

    }

    public static void main(String[] args) {
        new login().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        
        
        if (action.equals("Login")) {

            String user = t1.getText();
            String pass = t2.getText();
           
            try {

                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                PreparedStatement pst = null;
                ResultSet rs = null;
                String str = "select * from register where username=?";
                pst = conn.prepareStatement(str);
                pst.setString(1, user);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String password = rs.getString("password");
                    
                    if (pass.equals(password)) {
                        JOptionPane.showMessageDialog(null, "Logged in Sucessfully!!");
                         new home().setVisible(true);
                       setVisible(false);
                       
                       
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Logged in Failed!!");
                    }

                }

            } catch (Exception ex) {
                System.out.println(ex);
            }

        }
    }

}
