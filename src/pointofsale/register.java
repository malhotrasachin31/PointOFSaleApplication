package pointofsale;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class register extends JFrame implements ActionListener {

    JPanel p1;
    JTextField t1, t3, t4, t5;
    JPasswordField t2;
    JComboBox c1;

    register() {

         // adding upper panel of login page
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(Color.BLACK);
        p1.setBounds(0, 0, 1200, 30);
        add(p1);

        JLabel title=new JLabel("Register-page");
        title.setForeground(Color.WHITE);
        title.setBounds(550,5,200,25);
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

        // heading of the register page
        JLabel me = new JLabel("malhotra-engineers");
        me.setBounds(50, 40, 700, 60);
        me.setForeground(Color.CYAN);
        me.setFont(new Font("san-serif", Font.BOLD, 50));
        l2.add(me);

        JLabel l3 = new JLabel("Username (Enter your Email-ID) ");
        l3.setBounds(50, 130, 400, 35);
        l3.setForeground(Color.WHITE);
        l3.setFont(new Font("san-serif", Font.BOLD, 22));
        l2.add(l3);

        t1 = new JTextField();
        t1.setBounds(50, 180, 400, 35);
        t1.setBackground(Color.WHITE);
        t1.setFont(new Font("san-serif", Font.BOLD, 22));
        t1.setBorder(BorderFactory.createEmptyBorder());
        t1.setHorizontalAlignment(JTextField.CENTER);
        l2.add(t1);

        JLabel l4 = new JLabel("Password (Create A Password) ");
        l4.setBounds(50, 230, 400, 35);
        l4.setForeground(Color.WHITE);
        l4.setFont(new Font("san-serif", Font.BOLD, 22));
        l2.add(l4);

        t2 = new JPasswordField();
        t2.setBounds(50, 280, 400, 35);
        t2.setBackground(Color.WHITE);
        t2.setHorizontalAlignment(JTextField.CENTER);
        t2.setBorder(BorderFactory.createEmptyBorder());
        t2.setFont(new Font("san-serif", Font.BOLD, 22));
        l2.add(t2);

        JLabel l5 = new JLabel("Adress (Mention Your Address) ");
        l5.setBounds(50, 330, 400, 35);
        l5.setForeground(Color.WHITE);
        l5.setFont(new Font("san-serif", Font.BOLD, 22));
        l2.add(l5);

        t3 = new JTextField();
        t3.setBounds(50, 380, 850, 35);
        t3.setBackground(Color.WHITE);
        t3.setHorizontalAlignment(JTextField.CENTER);
        t3.setFont(new Font("san-serif", Font.BOLD, 22));
        t3.setBorder(BorderFactory.createEmptyBorder());
        l2.add(t3);

        JLabel l6 = new JLabel("Phone Number (10 Digit no.)");
        l6.setBounds(50, 430, 400, 35);
        l6.setForeground(Color.WHITE);
        l6.setFont(new Font("san-serif", Font.BOLD, 22));
        l2.add(l6);

        t4 = new JTextField();
        t4.setBounds(50, 480, 400, 35);
        t4.setBackground(Color.WHITE);
        t4.setHorizontalAlignment(JTextField.CENTER);
        t4.setFont(new Font("san-serif", Font.BOLD, 22));
        t4.setBorder(BorderFactory.createEmptyBorder());
        l2.add(t4);

        JLabel l7 = new JLabel("Question (if you forgot password)");
        l7.setBounds(500, 430, 400, 35);
        l7.setForeground(Color.WHITE);
        l7.setFont(new Font("san-serif", Font.BOLD, 22));
        l2.add(l7);

        c1 = new JComboBox();
        c1.setBounds(525, 480, 300, 35);
        c1.setBackground(Color.WHITE);
        c1.addItem("Enter Your Nick Name ?");
        c1.addItem("Favourite Game Of yours ?");
        c1.addItem("Your School Name ?");
        c1.addItem("What is Your Age ?");
        c1.addItem("Best Personality You Want to Meet ?");
        c1.setFont(new Font("san-serif", Font.BOLD, 17));
        c1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c1.setBorder(BorderFactory.createEmptyBorder());
        l2.add(c1);

        JLabel ans = new JLabel("Please Answer The Question");
        ans.setBounds(870, 430, 400, 35);
        ans.setForeground(Color.WHITE);
        ans.setFont(new Font("san-serif", Font.BOLD, 22));
        l2.add(ans);

        t5 = new JTextField();
        t5.setBounds(870, 480, 300, 35);
        t5.setBackground(Color.WHITE);
        t5.setHorizontalAlignment(JTextField.CENTER);
        t5.setFont(new Font("san-serif", Font.BOLD, 22));
        t5.setBorder(BorderFactory.createEmptyBorder());
        l2.add(t5);

        JButton b1 = new JButton("Sign-Up");
        b1.setBounds(50, 550, 200, 35);
        b1.setBackground(new Color(0, 205, 204));
        b1.setForeground(Color.black);
        b1.setFont(new Font("san-serif", Font.BOLD, 23));
        b1.setFocusable(false);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setBorder(BorderFactory.createEmptyBorder());
        //b1.setBorder(new RoundedBorder());
        b1.addActionListener(this);
        l2.add(b1);
        b1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                b1.setBackground(new Color(0,153,153));
                 b1.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent me) {
               b1.setBackground(new Color(0,205,204));
                 b1.setForeground(Color.black);
            }
        });

        JLabel register = new JLabel("Login User Click Here");
        register.setBounds(270, 550, 200, 35);
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
                new login().setVisible(true);
            }

        });

        // adding mouselistener to the first.png image
        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.exit(0);
            }
        });

        // set layout of the register form
        setLayout(null);
        setBounds(400, 200, 1200, 700);
        getContentPane().setBackground(Color.gray);
        setUndecorated(true);

    }

    public static void main(String args[]) {
        new register().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();

        if (action.equals("Sign-Up")) {

            // connection of project to the database
            String name = t1.getText();
            String password = t2.getText().toString();
            String address = t3.getText();
            String phone = t4.getText();
            String question = c1.getSelectedItem().toString();
            String answer = t5.getText();

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                PreparedStatement pstmt = null;
                String query = "INSERT  into register values (?,?,?,?,?,?)";
                pstmt = DBConnection.prepareStatement(query);

                pstmt.setString(1, name);
                pstmt.setString(2, password);
                pstmt.setString(3, address);
                pstmt.setString(4, phone);
                pstmt.setString(5, question);
                pstmt.setString(6, answer);

                int a = pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Registered Sucessfully");
                pstmt.close();
                DBConnection.close();

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

}
