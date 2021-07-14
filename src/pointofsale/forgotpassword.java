package pointofsale;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class forgotpassword extends JFrame implements ActionListener {

    JPanel p1;
    JTextField t1, t3, t4, t5;
    JPasswordField t2;
    JComboBox c1;

    forgotpassword() {

         // adding upper panel of login page
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(Color.BLACK);
        p1.setBounds(0, 0, 1200, 30);
        add(p1);

        JLabel title=new JLabel("Forgot-password");
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
        t1.setHorizontalAlignment(JTextField.CENTER);
        
        t1.setFont(new Font("san-serif", Font.BOLD, 22));
        t1.setBorder(BorderFactory.createEmptyBorder());
        l2.add(t1);

        JLabel l4 = new JLabel("Password (Create New Password) ");
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

        JLabel l5 = new JLabel("Your Question While Creating Account");
        l5.setBounds(50, 330, 400, 35);
        l5.setForeground(Color.WHITE);
        l5.setFont(new Font("san-serif", Font.BOLD, 22));
        l2.add(l5);

        t3 = new JTextField();
        t3.setBounds(50, 380, 400, 35);
        t3.setBackground(Color.WHITE);
        t3.setEditable(false);
        t3.setHorizontalAlignment(JTextField.CENTER);
        t3.setFont(new Font("san-serif", Font.BOLD, 22));
        t3.setBorder(BorderFactory.createEmptyBorder());
        l2.add(t3);

        JLabel l6 = new JLabel("Please Give The Answer of Above Question");
        l6.setBounds(50, 430, 500, 35);
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

        JButton b1 = new JButton("Update");
        b1.setBounds(50, 550, 150, 35);
        b1.setBackground(new Color(0,204,204));
        b1.setFont(new Font("san-serif", Font.BOLD, 15));
        b1.setFocusable(false);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setBorder(BorderFactory.createEmptyBorder());
        b1.addActionListener(this);
        l2.add(b1);
        b1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                b1.setBackground(new Color(0,153,153));
                b1.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent me) {
                b1.setBackground(new Color(0,204,204));
                 b1.setForeground(Color.black);
            }
        });
        JButton b2 = new JButton("Search For Your Username");
        b2.setBounds(220, 550, 230, 35);
        b2.setBackground(new Color(0,204,204));
        b2.setFont(new Font("san-serif", Font.BOLD, 15));
        b2.setFocusable(false);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setBorder(BorderFactory.createEmptyBorder());
        b2.addActionListener(this);
        l2.add(b2);
        b2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                 b2.setBackground(new Color(0,153,153));
                b2.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent me) {
                 b2.setBackground(new Color(0,204,204));
                 b2.setForeground(Color.black);
            }
            public void mouseClicked(MouseEvent me){
                String name = t1.getText();
            System.out.println("hello");
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                String query = "Select question from register where username=?";
                pstmt = DBConnection.prepareStatement(query);

                pstmt.setString(1, name);
                
                rs = pstmt.executeQuery();
                
                
                System.out.println("hello");
                rs.next();
                {
                    String question = rs.getString("question");
                    t3.setText(question);
                }
               
                    
                pstmt.close();
                DBConnection.close();

            } catch (Exception ex) {
                System.out.println(ex);
            }
            }
        });

        JLabel register = new JLabel("Login User Click Here");
        register.setBounds(160, 600, 200, 35);
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
        new forgotpassword().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();

        if (action.equals("Update")) {
            try{
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            PreparedStatement pst=null;
            String query=" update register set password = ? where username= ?";
            pst=DBConnection.prepareStatement(query);
            pst.setString(1, t2.getText().toString());
           pst.setString(2, t1.getText());
            
           int a=pst.executeUpdate();
           JOptionPane.showMessageDialog(null, "Sucessfully Updated!");
           pst.close(); 
           DBConnection.close();
            
            
            
            }catch(Exception ex)
            {
                System.out.println(ex);
            }
        }

    }

}
