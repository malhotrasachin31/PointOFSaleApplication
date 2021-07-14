/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pointofsale;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.connectcode.Code128Auto;
import net.proteanit.sql.DbUtils;
//import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

public class home extends JFrame {

    String billno = "";
    JScrollPane sp;
    Double cash = 0.0;
    Double balance = 0.0;
    Double bheight = 0.0;
    Double totalamount = 0.0;
    ArrayList<String> itemname = new ArrayList<>();
    ArrayList<String> quantity2 = new ArrayList<>();
    ArrayList<String> itemprice = new ArrayList<>();
    ArrayList<String> subtotal = new ArrayList<>();

    JButton supplier, employee, product, sales, invoice, reports, customers, purchase, bill, returns, account;
    JPanel leftpanel;
    float gt;
    JLabel taxamount1, shippingcost1, subtotal1, discountamount1, grandtotal1, due1;
    String pname;
    JTable table;
    DefaultListCellRenderer lr;
    JMenu flle, Edit;
    ImageIcon customer3;
    JLabel accounts;
    JComboBox cb, cb2, cb3, cb4, qty, tf2, tf3, tf6;
    JTextField bar, city, up, tp, tf1, tf4, tf5;

    home() {

        JScrollPane jScrollPane2 = new JScrollPane();

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(Color.ORANGE);
        p1.setBounds(0, 0, 1920, 30);
        add(p1);

        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(Color.ORANGE);
        menubar.setBorder(BorderFactory.createEmptyBorder());

        JMenu file = new JMenu("Home");
        file.setFont(new Font("san-serif", Font.BOLD, 16));

        JMenuItem newdoc = new JMenuItem("Refresh-Home");
        newdoc.setFont(new Font("san-serif", Font.BOLD, 16));
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));

        newdoc.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                new home().setVisible(true);
            }

        });

        /* JMenuItem open=new JMenuItem("");
         open.setFont(new Font("san-serif",Font.BOLD,18));
         open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
     
         JMenuItem save=new JMenuItem("Save");
         save.setFont(new Font("san-serif",Font.BOLD,18));
         save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
     
         JMenuItem print=new JMenuItem("Print");
         print.setFont(new Font("san-serif",Font.BOLD,18));
         print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
     
         JMenuItem exit=new JMenuItem("Exit");
         exit.setFont(new Font("san-serif",Font.BOLD,18));
         exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
     
         file.add(open);
         file.add(save);
         file.add(newdoc);
         file.add(print);
         file.add(exit);*/
        file.add(newdoc);

        JMenu edit = new JMenu("Reports");
        edit.setFont(new Font("san-serif", Font.BOLD, 16));

        JMenuItem report = new JMenuItem("Customer");
        report.setFont(new Font("san-serif", Font.BOLD, 16));
        report.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        report.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                try {

                   
                    
                    JFileChooser jf1=new JFileChooser();
                    FileNameExtensionFilter fnef=new FileNameExtensionFilter("WORD files",".pdf");
                    jf1.setFileFilter(fnef);
                    int value =jf1.showOpenDialog(null);
                    if(value==JFileChooser.APPROVE_OPTION){
                        XWPFDocument doc=new XWPFDocument(new FileInputStream(jf1.getSelectedFile()));
                       
                        
                    }
                    
                    
                    
                } catch (Exception ex) {
                    
                };

            }

        });

        JMenuItem report2 = new JMenuItem("Supplier");
        report2.setFont(new Font("san-serif", Font.BOLD, 16));
        report2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        report2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                   File file2=new File("C:\\Users\\dell\\Documents\\malhotraengineers\\all reports\\reportsofallcustomers.pdf");
                    if(file2.exists()){
                       if(Desktop.isDesktopSupported()){
                           try {
                               Desktop.getDesktop().open(file2);
                           } catch (IOException ex) {
                               ex.printStackTrace();
                           }
                       }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "file not exists");
                    }
            }
        });

        JMenuItem report3 = new JMenuItem("Sales");
        report3.setFont(new Font("san-serif", Font.BOLD, 16));
        report3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        report3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

            }
        });

        

        JMenuItem report5 = new JMenuItem("Product");
        report5.setFont(new Font("san-serif", Font.BOLD, 16));
        report5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        report5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

            }
        });

        JMenuItem report6 = new JMenuItem("Employee");
        report6.setFont(new Font("san-serif", Font.BOLD, 16));
        report6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        report6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

            }
        });

        /* JMenuItem cut=new JMenuItem("Cut");
         cut.setFont(new Font("san-serif",Font.BOLD,18));
         cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
     
         JMenuItem copy=new JMenuItem("Copy");
         copy.setFont(new Font("san-serif",Font.BOLD,18));
         copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
     
         JMenuItem paste=new JMenuItem("Paste");
         paste.setFont(new Font("san-serif",Font.BOLD,18));
         paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
     
         JMenuItem selectall=new JMenuItem("Select All");
         selectall.setFont(new Font("san-serif",Font.BOLD,18));
         selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
         */
        edit.add(report);
        edit.add(report2);
        edit.add(report3);
        
        edit.add(report5);
        edit.add(report6);

        
        

        JMenu about = new JMenu("About");
        about.setFont(new Font("san-serif", Font.BOLD, 16));
        about.addActionListener(new ActionListener(){
           
            @Override
            public void actionPerformed(ActionEvent ae) {
               //To change body of generated methods, choose Tools | Templates.
                new about().setVisible(true);
            }
             });
        
        menubar.add(file);
        menubar.add(edit);
       
        menubar.add(about);
        
       

        ImageIcon back = new ImageIcon(ClassLoader.getSystemResource("images/back5.jpg"));
        Image back2 = back.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        ImageIcon back3 = new ImageIcon(back2);
        JLabel background = new JLabel("");
        background.setIcon(back3);
        background.setCursor(new Cursor(Cursor.HAND_CURSOR));
        background.setBounds(0, 0, 1920, 1080);
        add(background);

        menubar.setBounds(0, 30, 1920, 30);
        background.add(menubar);

        JLabel title = new JLabel("Point Of Sale Application (malhotra-engineers)");
        title.setBounds(5, 2, 500, 25);
        title.setFont(new Font("arial", Font.BOLD, 16));
        title.setForeground(Color.black);
        p1.add(title);

        JLabel loggintime = new JLabel("(Recently Logged Time)");
        loggintime.setBounds(900, 2, 500, 25);
        loggintime.setFont(new Font("arial", Font.BOLD, 16));
        loggintime.setForeground(Color.black);
        p1.add(loggintime);

        Date d1 = new Date();
        SimpleDateFormat sdt = new SimpleDateFormat("hh:mm:ss");

        // setting of the AM/PM of day
        /*
        
        
        
        
        
        
         */
        JLabel time = new JLabel();
        time.setBounds(800, 2, 500, 25);
        time.setText(sdt.format(d1));
        time.setFont(new Font("arial", Font.BOLD, 16));
        time.setForeground(Color.black);
        p1.add(time);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/first.png"));
        Image img2 = img.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel close = new JLabel("");
        close.setIcon(img3);
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        close.setBounds(1890, 5, 20, 20);
        p1.add(close);

        JPanel upperpanel1 = new JPanel();
        upperpanel1.setBounds(350, 80, 300, 100);
        upperpanel1.setBackground(new Color(135, 206, 235));
        upperpanel1.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        upperpanel1.setLayout(null);
        background.add(upperpanel1);

        JLabel totalcust = new JLabel("Total Customers");
        totalcust.setBounds(110, 10, 200, 25);
        totalcust.setForeground(Color.black);
        upperpanel1.add(totalcust);

        // setting the value of total customers in customer data
        JLabel no1 = new JLabel();
        no1.setBounds(125, 30, 200, 50);
        no1.setFont(new Font("arial", Font.BOLD, 40));
        no1.setForeground(Color.black);
        upperpanel1.add(no1);

         // connecting to the customer database
        try {
            //call out dbConnector method from Entity class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String query = "select count(*) as total_record from customer";
            pstmt = DBConnection.prepareStatement(query);
            rs = pstmt.executeQuery();
                rs.next();
                int cont = rs.getInt(1);
                String number = String.valueOf(cont);
                no1.setText(number);

            }catch (Exception ex) {
                    System.out.println(ex);
                }

            JPanel upperpanel2 = new JPanel();
            upperpanel2.setBounds(658, 80, 300, 100);
            upperpanel2.setBackground(Color.GREEN);
            upperpanel2.setBorder(BorderFactory.createLineBorder(Color.white, 0));
            upperpanel2.setLayout(null);
            background.add(upperpanel2);

            JLabel totalsupp = new JLabel("Total Suppliers");
            totalsupp.setBounds(100, 10, 200, 25);
            totalsupp.setForeground(Color.black);
            upperpanel2.add(totalsupp);

               JLabel no2 = new JLabel();
            no2.setBounds(125, 30, 200, 50);
            no2.setFont(new Font("arial", Font.BOLD, 40));
            no2.setForeground(Color.black);
            upperpanel2.add(no2);

            
            
             // connecting to the supplier database
        try {
            //call out dbConnector method from Entity class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String query = "select count(*) as total_record from supplier";
            pstmt = DBConnection.prepareStatement(query);
            rs = pstmt.executeQuery();
                rs.next();
                int cont = rs.getInt(1);
                String number = String.valueOf(cont);
                no2.setText(number);

            }catch (Exception ex) {
                    System.out.println(ex);
                }

            
            
         
            JPanel upperpanel3 = new JPanel();
            upperpanel3.setBounds(968, 80, 300, 100);
            upperpanel3.setBackground(Color.magenta);
            upperpanel3.setBorder(BorderFactory.createLineBorder(Color.white, 0));
            upperpanel3.setLayout(null);
            background.add(upperpanel3);

            JLabel no3 = new JLabel();
            no3.setBounds(125, 30, 200, 50);
            no3.setFont(new Font("arial", Font.BOLD, 40));
            no3.setForeground(Color.black);
            upperpanel3.add(no3);
            
             // connecting to the employee database
        try {
            //call out dbConnector method from Entity class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String query = "select count(*) as total_record from employee";
            pstmt = DBConnection.prepareStatement(query);
            rs = pstmt.executeQuery();
                rs.next();
                int cont = rs.getInt(1);
                String number = String.valueOf(cont);
                no3.setText(number);

            }catch (Exception ex) {
                    System.out.println(ex);
                }

            
            

            JLabel totalemp = new JLabel("Total Employees");
            totalemp.setBounds(100, 10, 200, 25);
            totalemp.setForeground(Color.black);
            upperpanel3.add(totalemp);

            JPanel upperpanel4 = new JPanel();
            upperpanel4.setBounds(1278, 80, 300, 100);
            upperpanel4.setBackground(Color.YELLOW);
            upperpanel4.setBorder(BorderFactory.createLineBorder(Color.white, 0));
            upperpanel4.setLayout(null);
            background.add(upperpanel4);

            
            
            JLabel no4 = new JLabel();
            no4.setBounds(125, 30, 200, 50);
            no4.setFont(new Font("arial", Font.BOLD, 40));
            no4.setForeground(Color.black);
            upperpanel4.add(no4);
            
             // connecting to the products database
        try {
            //call out dbConnector method from Entity class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String query = "select count(*) as total_record from product";
            pstmt = DBConnection.prepareStatement(query);
            rs = pstmt.executeQuery();
                rs.next();
                int cont = rs.getInt(1);
                String number = String.valueOf(cont);
                no4.setText(number);

            }catch (Exception ex) {
                    System.out.println(ex);
                }

            

            JLabel totalpro = new JLabel("Total Products");
            totalpro.setBounds(100, 10, 200, 25);
            totalpro.setForeground(Color.black);
            upperpanel4.add(totalpro);

            JPanel upperpanel5 = new JPanel();
            upperpanel5.setBounds(1588, 80, 300, 100);
            upperpanel5.setBackground(new Color(255, 250, 205));
            upperpanel5.setBorder(BorderFactory.createLineBorder(Color.white, 0));
            upperpanel5.setLayout(null);
            background.add(upperpanel5);

            JLabel no5 = new JLabel();
            no5.setBounds(125, 30, 200, 50);
            no5.setFont(new Font("arial", Font.BOLD, 40));
            no5.setForeground(Color.black);
            upperpanel5.add(no5);

            // connecting to the sales database
            
              try {
            //call out dbConnector method from Entity class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String query = "select count(*) as total_record from sales";
            pstmt = DBConnection.prepareStatement(query);
            rs = pstmt.executeQuery();
                rs.next();
                int cont = rs.getInt(1);
                String number = String.valueOf(cont);
                no5.setText(number);

            }catch (Exception ex) {
                    System.out.println(ex);
                }
            
            JLabel totalsale = new JLabel("Total Sales");
            totalsale.setBounds(120, 10, 200, 25);
            totalsale.setForeground(Color.black);
            upperpanel5.add(totalsale);

            leftpanel = new JPanel();
            leftpanel.setBounds(10, 80, 300, 990);
            leftpanel.setBorder(BorderFactory.createLineBorder(Color.white, 1));
            leftpanel.setLayout(null);
            leftpanel.setBackground(new Color(0, 0, 0, 0));
            background.add(leftpanel);
            close.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    System.exit(0);
                }
            });

            accounts = new JLabel("malhotrasachin96@gmail.com");
            accounts.setBounds(10, 30, 300, 30);
            accounts.setForeground(Color.white);
            accounts.setFont(new Font("arial", Font.BOLD, 18));
            leftpanel.add(accounts);

            customers = new JButton("Customers");
            customers.setFocusable(false);
            customers.setBounds(10, 75, 280, 70);
            customers.setBackground(Color.WHITE);
            customers.setForeground(Color.black);
            customers.setFont(new Font("arial", Font.BOLD, 22));
            customers.setLayout(null);
            customers.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            customers.setCursor(new Cursor(Cursor.HAND_CURSOR));
            leftpanel.add(customers);
            customers.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me1) {
                    customers.setBackground(new Color(255, 153, 51));
                    customers.setForeground(Color.white);
                }

                public void mouseExited(MouseEvent me1) {
                    customers.setBackground(Color.WHITE);
                    customers.setForeground(Color.black);
                }

                public void mouseClicked(MouseEvent me) {
                    new customer().setVisible(true);
                }
            });

            supplier = new JButton("Suppliers");
            supplier.setFocusable(false);
            supplier.setBounds(10, 150, 280, 70);
            supplier.setLayout(null);
            supplier.setBackground(Color.white);
            supplier.setForeground(Color.black);
            supplier.setFont(new Font("arial", Font.BOLD, 22));
            supplier.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            supplier.setCursor(new Cursor(Cursor.HAND_CURSOR));
            leftpanel.add(supplier);
            supplier.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    supplier.setBackground(new Color(255, 153, 51));
                    supplier.setForeground(Color.white);
                }

                public void mouseExited(MouseEvent me) {
                    supplier.setBackground(Color.WHITE);
                    supplier.setForeground(Color.black);
                }

                public void mouseClicked(MouseEvent me) {
                    new supplier().setVisible(true);
                }
            });

            employee = new JButton("Employee");
            employee.setFocusable(false);
            employee.setBounds(10, 225, 280, 70);
            employee.setLayout(null);
            employee.setBackground(Color.WHITE);
            employee.setForeground(Color.black);
            employee.setFont(new Font("arial", Font.BOLD, 22));
            employee.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            employee.setCursor(new Cursor(Cursor.HAND_CURSOR));
            leftpanel.add(employee);
            employee.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    employee.setBackground(new Color(255, 153, 51));
                    employee.setForeground(Color.white);
                }

                public void mouseExited(MouseEvent me) {
                    employee.setBackground(Color.WHITE);
                    employee.setForeground(Color.black);
                }

                public void mouseClicked(MouseEvent me) {

                    new employee().setVisible(true);

                }
            });

            product = new JButton("Product");
            product.setFocusable(false);
            product.setBounds(10, 300, 280, 70);
            product.setLayout(null);
            product.setBackground(Color.WHITE);
            product.setForeground(Color.black);
            product.setFont(new Font("arial", Font.BOLD, 22));
            product.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            product.setCursor(new Cursor(Cursor.HAND_CURSOR));
            leftpanel.add(product);
            product.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    product.setBackground(new Color(255, 153, 51));
                    product.setForeground(Color.white);
                }

                public void mouseExited(MouseEvent me) {
                    product.setBackground(Color.WHITE);
                    product.setForeground(Color.black);
                }

                public void mouseClicked(MouseEvent me) {

                    new product().setVisible(true);
                }
            });
            
            sales = new JButton("Sales");
            sales.setFocusable(false);
            sales.setBounds(10, 375, 280, 70);
            sales.setBackground(Color.WHITE);
            sales.setForeground(Color.black);
            sales.setLayout(null);
            sales.setFont(new Font("arial", Font.BOLD, 22));
            sales.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            sales.setCursor(new Cursor(Cursor.HAND_CURSOR));
            leftpanel.add(sales);
            sales.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    sales.setBackground(new Color(255, 153, 51));
                    sales.setForeground(Color.white);
                }

                public void mouseExited(MouseEvent me) {
                    sales.setBackground(Color.WHITE);
                    sales.setForeground(Color.black);
                }

                public void mouseClicked(MouseEvent me) {
                    new sales().setVisible(true);
                }
            });

            
            
            
            JLabel clock=new JLabel();
            clock.setBounds(40,900,400,60);
            clock.setFont(new Font("Arial",Font.BOLD,50));
            clock.setForeground(Color.green);
            leftpanel.add(clock);
            
            JLabel invoice1 = new JLabel("Invoice");
            invoice1.setBounds(330, 200, 250, 50);
            invoice1.setFont(new Font("arial", Font.BOLD, 40));
            invoice1.setForeground(Color.white);
            background.add(invoice1);

            

            JPanel centerpanel = new JPanel();
            centerpanel.setBounds(320, 390, 1590, 320);
            centerpanel.setBackground(new Color(0, 0, 0, 0));
            centerpanel.setBorder(BorderFactory.createLineBorder(Color.white, 1));
            centerpanel.setLayout(null);
            background.add(centerpanel);

            ImageIcon customer = new ImageIcon(ClassLoader.getSystemResource("images/cust.png"));
            Image customer2 = customer.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon customer3 = new ImageIcon(customer2);
            JLabel customerimage = new JLabel("");
            customerimage.setIcon(customer3);
            customerimage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            customerimage.setBounds(20, 10, 50, 50);
            customers.add(customerimage);

            JLabel ci = new JLabel("");
            ci.setBounds(330, 260, 250, 50);
            ci.setIcon(customer3);
            background.add(ci);

            DefaultListCellRenderer lr4 = new DefaultListCellRenderer();
            lr4.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

            cb = new JComboBox();
            cb.setBounds(400, 260, 300, 40);
            cb.setBackground(Color.white);
            cb.setRenderer(lr4);
            cb.setForeground(Color.black);
            cb.addItem("Select Customers");
            cb.setFont(new Font("arial", Font.BOLD, 20));
            background.add(cb);
            cb.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        //call out dbConnector method from Entity class
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                        PreparedStatement pstmt = null;
                        ResultSet rs = null;
                        String query = "select * from customer where name=?";
                        pstmt = DBConnection.prepareStatement(query);
                        pstmt.setString(1, cb.getSelectedItem().toString());
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            String str = rs.getString("billiingaddress");
                            city.setText(str);
                            System.out.println(str);
                        }
                        DBConnection.close();
                        pstmt.close();

                    } catch (Exception ex) {
                        System.out.println(e);
                    }

                }

            });

            try {
                //call out dbConnector method from Entity class
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                String query = "select distinct name from customer order by name";
                PreparedStatement pst = conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    //shows topic data in combobox
                    cb.addItem(rs.getString("name"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            JLabel barcode = new JLabel("Barcode:-");
            barcode.setBounds(730, 260, 250, 50);
            barcode.setForeground(Color.white);
            barcode.setFont(new Font("arial", Font.BOLD, 25));
            background.add(barcode);

            bar = new JTextField();
            bar.setBounds(880, 260, 300, 40);
            bar.setBackground(Color.white);
            bar.setBorder(BorderFactory.createEmptyBorder());
            bar.setForeground(Color.gray);
            bar.setEditable(false);
            bar.setHorizontalAlignment(JTextField.CENTER);
            bar.setFont(new Font("CCode128_S3_Trial", Font.PLAIN, 16));
            background.add(bar);

            JLabel ct = new JLabel("Address:-");
            ct.setBounds(1220, 260, 150, 50);
            ct.setForeground(Color.white);
            ct.setFont(new Font("arial", Font.BOLD, 25));
            background.add(ct);

            city = new JTextField();
            city.setBounds(1350, 260, 540, 40);
            city.setBorder(BorderFactory.createEmptyBorder());
            city.setForeground(Color.black);
            city.setEditable(false);
            city.setBackground(Color.white);
            city.setHorizontalAlignment(JTextField.CENTER);
            city.setFont(new Font("arial", Font.BOLD, 25));
            background.add(city);

            ImageIcon produc = new ImageIcon(ClassLoader.getSystemResource("images/product.png"));
            Image produc2 = produc.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon produc3 = new ImageIcon(produc2);
            JLabel producimage = new JLabel("");
            producimage.setIcon(produc3);
            producimage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            producimage.setBounds(20, 10, 50, 50);
            product.add(producimage);

            JLabel productname = new JLabel("Product:-");
            productname.setBounds(330, 325, 50, 50);
            productname.setIcon(produc3);
            background.add(productname);

            DefaultListCellRenderer lr5 = new DefaultListCellRenderer();
            lr5.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

            cb2 = new JComboBox();
            cb2.setBounds(400, 330, 300, 40);
            cb2.setBackground(Color.white);
            cb2.setForeground(Color.black);
            cb2.setRenderer(lr5);
            cb2.addItem("Select Product");
            cb2.setFont(new Font("arial", Font.BOLD, 20));
            background.add(cb2);
            try {
                //call out dbConnector method from Entity class
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                String query = "select distinct brandname from product order by brandname";
                PreparedStatement pst = conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    //shows topic data in combobox
                    cb2.addItem(rs.getString("brandname"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            DefaultListCellRenderer lr6 = new DefaultListCellRenderer();
            lr6.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

            cb4 = new JComboBox();
            cb4.setBounds(920, 330, 250, 40);
            cb4.setBackground(Color.white);
            cb4.setForeground(Color.black);
            cb4.addItem("Select Product Name");
            cb4.setRenderer(lr6);
            cb4.setFont(new Font("arial", Font.BOLD, 20));
            cb4.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    Code128Auto code128 = new Code128Auto();
                    String barcode = code128.encode(cb4.getSelectedItem().toString());
                    bar.setText(barcode);

                    try {
                        //call out dbConnector method from Entity class
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                        String query = "select unitprice from product where name=?";
                        PreparedStatement pst = conn.prepareStatement(query);
                        pst.setString(1, cb4.getSelectedItem().toString());
                        ResultSet rs = pst.executeQuery();

                        while (rs.next()) {
                            //shows topic data in combobox
                            String str = rs.getString("unitprice");
                            up.setText(str);

                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }

            });

            background.add(cb4);
            try {
                //call out dbConnector method from Entity class
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                String query = "select distinct name from product order by name";
                PreparedStatement pst = conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    //shows topic data in combobox
                    cb4.addItem(rs.getString("name"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DefaultListCellRenderer l = new DefaultListCellRenderer();
            l.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

            cb3 = new JComboBox();
            cb3.setBounds(710, 330, 200, 40);
            cb3.setBackground(Color.white);
            cb3.setForeground(Color.black);
            cb3.setRenderer(l);
            cb3.addItem("Select Company");
            cb3.setFont(new Font("arial", Font.BOLD, 20));
            background.add(cb3);

            try {
                //call out dbConnector method from Entity class
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                String query = "select distinct companyname from product order by companyname";
                PreparedStatement pst = conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    //shows topic data in combobox
                    cb3.addItem(rs.getString("companyname"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            JLabel unitprice = new JLabel("Unit-Price:-");
            unitprice.setBounds(1200, 325, 150, 50);
            unitprice.setForeground(Color.white);
            unitprice.setFont(new Font("arial", Font.BOLD, 15));
            background.add(unitprice);

            up = new JTextField();
            up.setBounds(1300, 335, 170, 30);
            up.setBorder(BorderFactory.createEmptyBorder());
            up.setForeground(Color.black);
            up.setEditable(false);
            up.setBackground(Color.white);
            up.setHorizontalAlignment(JTextField.CENTER);
            up.setFont(new Font("arial", Font.BOLD, 25));
            background.add(up);

            JLabel totalprice = new JLabel("Quantity-");
            totalprice.setBounds(1490, 325, 80, 50);
            totalprice.setForeground(Color.white);
            totalprice.setFont(new Font("arial", Font.BOLD, 15));
            background.add(totalprice);

            DefaultListCellRenderer lr7 = new DefaultListCellRenderer();
            lr7.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

            qty = new JComboBox();
            qty.setBounds(1590, 325, 80, 40);
            qty.setRenderer(lr7);
            qty.addItem("1");
            qty.addItem("2");
            qty.addItem("3");
            qty.addItem("4");
            qty.addItem("5");
            qty.addItem("6");
            qty.addItem("7");
            qty.addItem("8");
            qty.addItem("9");
            qty.addItem("10");
            qty.addItem("11");
            qty.addItem("12");
            qty.addItem("13");
            qty.addItem("14");
            qty.addItem("15");
            qty.addItem("16");
            qty.addItem("17");
            qty.addItem("18");
            qty.addItem("19");
            qty.addItem("20");
            qty.setForeground(Color.black);
            qty.setBackground(Color.white);
            qty.setFont(new Font("arial", Font.BOLD, 20));
            background.add(qty);
            qty.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ae) {

                    int quantity2 = Integer.parseInt(qty.getSelectedItem().toString());
                    int price2 = Integer.parseInt(up.getText().toString());

                    double totalprice = quantity2 * price2;
                    String totalp = Double.toString(totalprice);
                    tp.setText(totalp);

                }

            });

            JLabel quantity = new JLabel("Total:-");
            quantity.setBounds(1700, 325, 150, 50);
            quantity.setForeground(Color.white);
            quantity.setFont(new Font("arial", Font.BOLD, 15));
            background.add(quantity);

            tp = new JTextField();
            tp.setBounds(1750, 335, 160, 30);
            tp.setBorder(BorderFactory.createEmptyBorder());
            tp.setForeground(Color.black);
            tp.setEditable(false);
            tp.setHorizontalAlignment(JTextField.CENTER);
            tp.setBackground(Color.white);
            tp.setFont(new Font("arial", Font.BOLD, 25));
            background.add(tp);

            JPanel panel4 = new JPanel();
            panel4.setLayout(new GridLayout(1, 1));
            panel4.setBounds(10, 10, 1300, 300);
            panel4.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
            panel4.setBackground(Color.WHITE);
            centerpanel.add(panel4);

            table = new JTable();
            panel4.add(table);

            JScrollPane jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            panel4.add(jsp);

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                PreparedStatement pst = null;
                ResultSet rs = null;
                String str = "select * from bill";
                pst = DBConnection.prepareStatement(str);
                rs = pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
                pst.close();
                DBConnection.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }

            // remove item starting//
            table.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent me) {
                    int a = table.getSelectedRow();

                    pname = table.getValueAt(a, 2).toString();
                    System.out.println(pname);

                }
            });

            JPanel panel7 = new JPanel();
            panel7.setBounds(1320, 10, 260, 300);
            panel7.setLayout(null);
            panel7.setBorder(BorderFactory.createLineBorder(Color.white, 0, true));
            panel7.setBackground(new Color(0, 0, 0, 0));
            centerpanel.add(panel7);

            JButton addtocart = new JButton("Add To Cart");
            addtocart.setBounds(10, 50, 240, 40);
            addtocart.setFont(new Font("arial", Font.BOLD, 22));
            addtocart.setBackground(Color.green);
            addtocart.setBorder(BorderFactory.createEmptyBorder());
            addtocart.setForeground(Color.black);
            addtocart.setFocusable(false);
            panel7.add(addtocart);
            addtocart.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    addtocart.setBackground(new Color(102, 225, 102));
                    addtocart.setForeground(Color.white);
                }

                public void mouseExited(MouseEvent me) {
                    addtocart.setBackground(Color.green);
                    addtocart.setForeground(Color.black);
                }

                public void mouseClicked(MouseEvent me) {

                    itemname.add(cb4.getSelectedItem().toString());
                    quantity2.add(qty.getSelectedItem().toString());
                    itemprice.add(up.getText());
                    subtotal.add(tp.getText());

                    String name = cb.getSelectedItem().toString();

                    String companyname = cb3.getSelectedItem().toString();
                    String product = cb2.getSelectedItem().toString();
                    String productname = cb4.getSelectedItem().toString();
                    String unitprice = up.getText();
                    String quantity = qty.getSelectedItem().toString();
                    String totalprice = tp.getText();

                    DefaultTableModel dt = (DefaultTableModel) table.getModel();

                    Vector v = new Vector();

                    v.add(cb3.getSelectedItem());
                    v.add(cb2.getSelectedItem());
                    v.add(cb4.getSelectedItem());
                    v.add(up.getText());
                    v.add(qty.getSelectedItem());
                    v.add(tp.getText());

                    dt.addRow(v);

                    try {
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                        PreparedStatement pstmt = null;
                        ResultSet rs = null;
                        String s = " INSERT  into bill values (?,?,?,?,?,?)";

                        pstmt = DBConnection.prepareStatement(s);
                        pstmt.setString(1, companyname);
                        pstmt.setString(2, product);
                        pstmt.setString(3, productname);
                        pstmt.setString(4, unitprice);
                        pstmt.setString(5, quantity);
                        pstmt.setString(6, totalprice);

                        int a = pstmt.executeUpdate();//Executing SQL
                        JOptionPane.showMessageDialog(null, "Item Added Sucessfully");
                        String s1 = "select SUM(totalprice) as pricetotal from bill ";
                        pstmt = DBConnection.prepareStatement(s1);
                        rs = pstmt.executeQuery();

                        if (rs.next()) {
                            double totalv = rs.getDouble("pricetotal");
                            String total = Double.toString(totalv);
                            subtotal1.setText(total);
                        }

                        String s2 = "select SUM(quantity) as totalquantity from bill ";
                        pstmt = DBConnection.prepareStatement(s2);
                        rs = pstmt.executeQuery();
                        if (rs.next()) {
                            double totalv2 = rs.getDouble("totalquantity");
                            String total2 = Double.toString(totalv2);
                            tf5.setText(total2);
                        }

                        int b = pstmt.executeUpdate();

                        System.out.println("hello naman45");
                        pstmt.close();
                        DBConnection.close(); // Close Connection*/
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }

            });

            JButton remove = new JButton("Remove");
            remove.setBounds(10, 100, 240, 40);
            remove.setFont(new Font("arial", Font.BOLD, 22));
            remove.setBackground(Color.green);
            remove.setBorder(BorderFactory.createEmptyBorder());
            remove.setForeground(Color.black);
            remove.setFocusable(false);
            panel7.add(remove);
            remove.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    remove.setBackground(new Color(102, 225, 102));
                    remove.setForeground(Color.white);
                }

                public void mouseExited(MouseEvent me) {
                    remove.setBackground(Color.green);
                    remove.setForeground(Color.black);
                }

                public void mouseClicked(MouseEvent me) {

                    try {
                        DefaultTableModel dt = (DefaultTableModel) table.getModel();
                        int rw = table.getSelectedRow();
                        String id = dt.getValueAt(rw, 0).toString();
                        dt.removeRow(rw);

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                    try {

                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                        PreparedStatement pstmt = null;

                        String query = "DELETE from bill where productname=?";
                        pstmt = DBConnection.prepareStatement(query);
                        pstmt.setString(1, pname);
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Item removed successfully from the cart");

                        pstmt.close();
                        DBConnection.close();

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    table_load();

                    try {
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                        PreparedStatement pst = null;
                        ResultSet rs = null;
                        String str = "select SUM(totalprice) as pricetotal from bill ";
                        pst = DBConnection.prepareStatement(str);
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            double totalv = rs.getDouble("pricetotal");
                            String total = Double.toString(totalv);
                            subtotal1.setText(total);
                        }
                        pst.close();
                        DBConnection.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                    try {
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                        PreparedStatement pst = null;
                        ResultSet rs = null;
                        String str = "select SUM(quantity) as totalquantity from bill ";
                        pst = DBConnection.prepareStatement(str);
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            double totalv = rs.getDouble("totalquantity");
                            String total2 = Double.toString(totalv);
                            tf5.setText(total2);
                        }
                        pst.close();
                        DBConnection.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }

            });

            JButton removeall = new JButton("Remove All Items");
            removeall.setBounds(10, 150, 240, 40);
            removeall.setFont(new Font("arial", Font.BOLD, 22));
            removeall.setBackground(Color.green);
            removeall.setBorder(BorderFactory.createEmptyBorder());
            removeall.setForeground(Color.black);
            removeall.setFocusable(false);
            panel7.add(removeall);
            removeall.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    removeall.setBackground(new Color(102, 225, 102));
                    removeall.setForeground(Color.white);
                }

                public void mouseExited(MouseEvent me) {
                    removeall.setBackground(Color.green);
                    removeall.setForeground(Color.black);
                }

                public void mouseClicked(MouseEvent me) {

                    try {

                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                        PreparedStatement pstmt = null;

                        String query = "DELETE from bill";
                        pstmt = DBConnection.prepareStatement(query);

                        pstmt.executeUpdate();
                        tf5.setText("00.00");
                        subtotal1.setText("00.00");
                        JOptionPane.showMessageDialog(null, "All items removed successfully from the cart");

                        pstmt.close();
                        DBConnection.close();

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    table_load();

                }
            });

            JButton addtosales = new JButton("Add to Sales");
            addtosales.setBounds(10, 200, 240, 40);
            addtosales.setFont(new Font("arial", Font.BOLD, 22));
            addtosales.setBackground(Color.green);
            addtosales.setBorder(BorderFactory.createEmptyBorder());
            addtosales.setForeground(Color.black);
            addtosales.setFocusable(false);
            panel7.add(addtosales);
            addtosales.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    addtosales.setBackground(new Color(102, 225, 102));
                    addtosales.setForeground(Color.white);
                }

                public void mouseExited(MouseEvent me) {
                    addtosales.setBackground(Color.green);
                    addtosales.setForeground(Color.black);
                }

                public void mouseClicked(MouseEvent me) {

                    // code of "insert values into permanent database"  from here //
                    String totalofall = Float.toString(gt);
                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("E");
                    SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
                    String dat = sdf.format(d);
                    String dat2 = sdf2.format(d);
                    String dat3 = sdf3.format(d);
                    System.out.println(dat);
                    System.out.println(dat2);
                    System.out.println(dat3);
                    try {
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                        String s3 = "insert into sales values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement pstmt = null;
                        pstmt = DBConnection.prepareStatement(s3);
                        pstmt.setString(1, cb.getSelectedItem().toString());
                        pstmt.setString(2, bar.getText());
                        pstmt.setString(3, cb2.getSelectedItem().toString());
                        pstmt.setString(4, cb3.getSelectedItem().toString());
                        pstmt.setString(5, cb4.getSelectedItem().toString());
                        pstmt.setString(6, up.getText());
                        pstmt.setString(7, qty.getSelectedItem().toString());
                        System.out.println("hello sachin");
                        pstmt.setString(8, tp.getText());
                        pstmt.setString(9, tf1.getText());
                        System.out.println("hello naman");
                        pstmt.setString(10, tf2.getSelectedItem().toString());
                        System.out.println("hello naman2");
                        pstmt.setString(11, tf3.getSelectedItem().toString());
                        System.out.println("hello naman3");
                        pstmt.setString(12, tf4.getText());
                        pstmt.setString(13, tf5.getText());
                        System.out.println("hello naman4");
                        pstmt.setString(14, tf6.getSelectedItem().toString());
                        pstmt.setString(15, subtotal1.getText());
                        pstmt.setString(16, taxamount1.getText());
                        pstmt.setString(17, discountamount1.getText());
                        pstmt.setString(18, totalofall);
                        pstmt.setString(19, due1.getText());
                        pstmt.setString(20, dat);
                        pstmt.setString(21, dat3);
                        pstmt.setString(22, dat2);
                        pstmt.setString(23, city.getText());

                        int integer = pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "added to sales section sucessfully!");
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }

            });

            JPanel panel5 = new JPanel();
            panel5.setBounds(320, 720, 260, 350);
            panel5.setLayout(null);
            panel5.setBorder(BorderFactory.createLineBorder(Color.white, 0, true));
            panel5.setBackground(new Color(0, 0, 0, 0));
            background.add(panel5);

            JLabel subtotal2 = new JLabel("Shipping-Cost:-");
            subtotal2.setBounds(10, 50, 200, 30);
            subtotal2.setForeground(Color.white);
            subtotal2.setFont(new Font("arial", Font.BOLD, 25));
            panel5.add(subtotal2);

            tf1 = new JTextField();
            tf1.setBounds(590, 770, 300, 30);
            tf1.setBorder(BorderFactory.createEmptyBorder());
            tf1.setForeground(Color.black);
            tf1.setEditable(false);
            tf1.setText("500.0");
            tf1.setBackground(Color.white);
            tf1.setHorizontalAlignment(JTextField.CENTER);
            tf1.setFont(new Font("arial", Font.BOLD, 25));
            background.add(tf1);

            JLabel taxamount2 = new JLabel("Tax %:-");
            taxamount2.setBounds(10, 90, 250, 30);
            taxamount2.setForeground(Color.white);
            taxamount2.setFont(new Font("arial", Font.BOLD, 25));
            panel5.add(taxamount2);

            DefaultListCellRenderer lr2 = new DefaultListCellRenderer();
            lr2.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

            tf2 = new JComboBox();
            tf2.setBounds(590, 810, 300, 30);
            tf2.setBorder(BorderFactory.createEmptyBorder());
            tf2.setForeground(Color.black);
            tf2.setBackground(Color.white);
            tf2.setRenderer(lr2);
            tf2.addItem("selct %");
            tf2.addItem("5");
            tf2.addItem("10");
            tf2.addItem("15");
            tf2.addItem("20");
            tf2.setFont(new Font("arial", Font.BOLD, 25));
            background.add(tf2);
            tf2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {

                    float price = Float.parseFloat(subtotal1.getText());
                    int tax = Integer.parseInt(tf2.getSelectedItem().toString());

                    float finaltax = (price / 100) * tax;
                    String s = Float.toString(finaltax);
                    taxamount1.setText(s);

                    try {
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                        PreparedStatement pst = null;
                        ResultSet rs = null;
                        String str = "select SUM(totalprice) as pricetotal from bill ";
                        pst = DBConnection.prepareStatement(str);
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            double totalv = rs.getDouble("pricetotal");
                            String total2 = Double.toString(totalv);
                            subtotal1.setText(total2);
                        }
                        pst.close();
                        DBConnection.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
            });

            JLabel discount = new JLabel("Discount %");
            discount.setBounds(10, 130, 250, 30);
            discount.setForeground(Color.white);
            discount.setFont(new Font("arial", Font.BOLD, 25));
            panel5.add(discount);

            lr = new DefaultListCellRenderer();
            lr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

            tf3 = new JComboBox();
            tf3.setBounds(590, 850, 300, 30);
            tf3.setBorder(BorderFactory.createEmptyBorder());
            tf3.setForeground(Color.black);
            tf3.setEditable(false);
            tf3.setRenderer(lr);
            tf3.setBackground(Color.white);
            tf3.addItem("Select %");
            tf3.addItem("5");
            tf3.addItem("10");
            tf3.addItem("15");
            tf3.addItem("20");
            tf3.addItem("25");
            tf3.addItem("30");
            tf3.addItem("35");
            tf3.addItem("40");
            tf3.setFont(new Font("arial", Font.BOLD, 25));
            background.add(tf3);
            tf3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {

                    try {
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                        Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                        PreparedStatement pst = null;
                        ResultSet rs = null;
                        String str = "select SUM(totalprice) as pricetotal from bill ";
                        pst = DBConnection.prepareStatement(str);
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            double totalv2 = rs.getDouble("pricetotal");
                            String total = Double.toString(totalv2);
                            subtotal1.setText(total);
                        }
                        pst.close();
                        DBConnection.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                    float pri = Float.parseFloat(subtotal1.getText());
                    int dis = Integer.parseInt(tf3.getSelectedItem().toString());

                    float amt = (pri / 100) * dis;
                    String amt2 = Float.toString(amt);
                    discountamount1.setText(amt2);

                    float a = Float.parseFloat(taxamount1.getText());
                    float b = Float.parseFloat(subtotal1.getText());
                    float c = Float.parseFloat(shippingcost1.getText());
                    float d = Float.parseFloat(discountamount1.getText());

                    float e = a + b + c;
                    gt = e - d;

                    String gtotal = Float.toString(gt);
                    grandtotal1.setText(gtotal);

                }
            });

            JLabel paidamount = new JLabel("Paid-Amount:-");
            paidamount.setBounds(10, 170, 250, 30);
            paidamount.setForeground(Color.white);
            paidamount.setFont(new Font("arial", Font.BOLD, 25));
            panel5.add(paidamount);

            tf4 = new JTextField();
            tf4.setBounds(590, 890, 300, 30);
            tf4.setBorder(BorderFactory.createEmptyBorder());
            tf4.setForeground(Color.black);
            tf4.setBackground(Color.white);
            tf4.setHorizontalAlignment(JTextField.CENTER);
            tf4.setFont(new Font("arial", Font.BOLD, 25));
            background.add(tf4);

            JLabel quantity2 = new JLabel("Quantity:-");
            quantity2.setBounds(10, 210, 280, 30);
            quantity2.setForeground(Color.white);
            quantity2.setFont(new Font("arial", Font.BOLD, 25));
            panel5.add(quantity2);

            tf5 = new JTextField();
            tf5.setBounds(590, 930, 300, 30);
            tf5.setBorder(BorderFactory.createEmptyBorder());
            tf5.setForeground(Color.black);
            tf5.setEditable(false);
            tf5.setBackground(Color.white);
            tf5.setHorizontalAlignment(JTextField.CENTER);
            tf5.setFont(new Font("arial", Font.BOLD, 25));
            background.add(tf5);

            JLabel type = new JLabel("Type of Payment:-");
            type.setBounds(10, 250, 300, 30);
            type.setForeground(Color.white);
            type.setFont(new Font("arial", Font.BOLD, 25));
            panel5.add(type);

            DefaultListCellRenderer lr3 = new DefaultListCellRenderer();
            lr3.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

            tf6 = new JComboBox();
            ;
            tf6.setBounds(590, 970, 300, 30);
            tf6.setBorder(BorderFactory.createEmptyBorder());
            tf6.setForeground(Color.black);
            tf6.setBackground(Color.white);
            tf6.setRenderer(lr3);
            tf6.addItem("cash");
            tf6.addItem("credit card");
            tf6.addItem("debit card");
            tf6.addItem("google pay");
            tf6.addItem("paytm");
            tf6.addItem("phone pay");
            tf6.addItem("others");
            tf6.setFont(new Font("arial", Font.BOLD, 25));
            background.add(tf6);
            tf6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {

                    float paidamountbycustomer = Float.parseFloat(tf4.getText());
                    float gt2 = Float.parseFloat(grandtotal1.getText());

                    if (paidamountbycustomer > gt2) {
                        float rupee = paidamountbycustomer - gt2;
                        due1.setText("-" + Float.toString(rupee));
                    }
                    if (paidamountbycustomer == gt2) {
                        due1.setText("00.00");
                    }
                    if (paidamountbycustomer < gt2) {
                        float rupee = gt2 - paidamountbycustomer;
                        due1.setText(Float.toString(rupee));
                    }

                }
            });

            JPanel panel6 = new JPanel();
            panel6.setBounds(900, 720, 625, 280);
            panel6.setLayout(null);
            panel6.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
            panel6.setBackground(new Color(0, 0, 0, 0));
            background.add(panel6);

            JPanel left = new JPanel();
            left.setBounds(10, 10, 300, 260);
            left.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
            left.setBackground(Color.WHITE);
            left.setLayout(null);
            panel6.add(left);

            JPanel down2 = new JPanel();
            down2.setBounds(320, 1020, 1590, 50);
            down2.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
            down2.setBackground(new Color(0, 0, 0, 0));
            down2.setLayout(null);
            background.add(down2);

            JLabel note = new JLabel("The common perception of the website footer is that it’s not as important as the header or body of the content. This relates to the somewhat antiquated notion that the most important information must lie above the fold, or before you start scrolling.However, a study by Chartbeat found that visitors spend more time.");
            note.setBounds(25, 10, 1800, 30);
            note.setForeground(Color.WHITE);
            note.setFont(new Font("arial", Font.BOLD, 10));
            down2.add(note);

            JLabel subtotal = new JLabel("Sub-Total:-");
            subtotal.setBounds(10, 10, 200, 30);
            subtotal.setForeground(Color.black);
            subtotal.setFont(new Font("arial", Font.BOLD, 25));
            left.add(subtotal);

            JLabel shippingcost = new JLabel("Shipping-Cost:-");
            shippingcost.setBounds(10, 50, 200, 30);
            shippingcost.setForeground(Color.black);
            shippingcost.setFont(new Font("arial", Font.BOLD, 25));
            left.add(shippingcost);

            JLabel taxamount = new JLabel("Tax-Amount:-");
            taxamount.setBounds(10, 90, 200, 30);
            taxamount.setForeground(Color.black);
            taxamount.setFont(new Font("arial", Font.BOLD, 25));
            left.add(taxamount);

            JLabel mycart = new JLabel("malhotra-engineers cart ");
            mycart.setBounds(1550, 720, 500, 35);
            mycart.setForeground(Color.white);
            mycart.setFont(new Font("arial", Font.BOLD, 30));
            background.add(mycart);

            JButton pay = new JButton("Pay/Print Reciept");
            pay.setBounds(1580, 755, 300, 40);
            pay.setFont(new Font("arial", Font.BOLD, 22));
            pay.setBackground(Color.green);
            pay.setBorder(BorderFactory.createEmptyBorder());
            pay.setForeground(Color.black);
            pay.setFocusable(false);
            background.add(pay);
            pay.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    pay.setBackground(new Color(102, 225, 102));
                    pay.setForeground(Color.white);
                }

                public void mouseExited(MouseEvent me) {
                    pay.setBackground(Color.green);
                    pay.setForeground(Color.black);
                }

                public void mouseClicked(MouseEvent me) {

                    bheight = Double.valueOf(itemname.size());
                    //JOptionPane.showMessageDialog(rootPane, bHeight);

                    PrinterJob pj = PrinterJob.getPrinterJob();
                    pj.setPrintable(new BillPrintable(), getPageFormat(pj));
                    try {
                        pj.print();

                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }

                }//GEN-LAST:event_jButton2ActionPerformed

                public PageFormat getPageFormat(PrinterJob pj) {

                    PageFormat pf = pj.defaultPage();
                    Paper paper = pf.getPaper();

                    double bodyHeight = bheight;
                    double headerHeight = 5.0;
                    double footerHeight = 5.0;
                    double width = cm_to_pp(8);
                    double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
                    paper.setSize(width, height);
                    paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

                    pf.setOrientation(PageFormat.PORTRAIT);
                    pf.setPaper(paper);

                    return pf;
                }

            }
            );

            JLabel discountamount = new JLabel("Discount-Amount:-");

            discountamount.setBounds(10, 130, 250, 30);
            discountamount.setForeground(Color.black);

            discountamount.setFont(
                    new Font("arial", Font.BOLD, 25));
            left.add(discountamount);

            JLabel grandtotal = new JLabel("Grand-Total-Amount:-");

            grandtotal.setBounds(10, 170, 280, 30);
            grandtotal.setForeground(Color.black);

            grandtotal.setFont(
                    new Font("arial", Font.BOLD, 25));
            left.add(grandtotal);

            JLabel due = new JLabel("Due/Balance:-");

            due.setBounds(10, 210, 280, 30);
            due.setForeground(Color.black);

            due.setFont(
                    new Font("arial", Font.BOLD, 25));
            left.add(due);

            JPanel right = new JPanel();

            right.setBounds(315, 10, 300, 260);
            right.setLayout(
                    null);
            right.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
            right.setBackground(Color.WHITE);

            panel6.add(right);

            subtotal1 = new JLabel("00.00");

            subtotal1.setBounds(10, 10, 200, 30);
            subtotal1.setForeground(Color.black);

            subtotal1.setFont(
                    new Font("arial", Font.BOLD, 25));
            right.add(subtotal1);

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                PreparedStatement pst = null;
                ResultSet rs = null;
                String str = "select SUM(totalprice) as pricetotal from bill ";
                pst = DBConnection.prepareStatement(str);
                rs = pst.executeQuery();
                if (rs.next()) {
                    double totalv = rs.getDouble("pricetotal");
                    String total = Double.toString(totalv);
                    subtotal1.setText(total);
                }
                pst.close();
                DBConnection.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                PreparedStatement pst = null;
                ResultSet rs = null;
                String str = "select SUM(quantity) as totalquantity from bill ";
                pst = DBConnection.prepareStatement(str);
                rs = pst.executeQuery();
                if (rs.next()) {
                    double totalv = rs.getDouble("totalquantity");
                    String total2 = Double.toString(totalv);
                    tf5.setText(total2);
                }
                pst.close();
                DBConnection.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }

            shippingcost1 = new JLabel("500.0");

            shippingcost1.setBounds(
                    10, 50, 200, 30);
            shippingcost1.setForeground(Color.black);

            shippingcost1.setFont(
                    new Font("arial", Font.BOLD, 25));
            right.add(shippingcost1);

            taxamount1 = new JLabel("00.00");

            taxamount1.setBounds(10, 90, 200, 30);
            taxamount1.setForeground(Color.black);

            taxamount1.setFont(
                    new Font("arial", Font.BOLD, 25));
            right.add(taxamount1);

            discountamount1 = new JLabel("00.00");

            discountamount1.setBounds(10, 130, 250, 30);
            discountamount1.setForeground(Color.black);

            discountamount1.setFont(
                    new Font("arial", Font.BOLD, 25));
            right.add(discountamount1);

            /* gt=Float.parseFloat(taxamount.getText()+subtotal.getText()+shippingcost1.getText())-Float.parseFloat(discountamount1.getText());
             String gt2=Float.toString(gt);*/
            grandtotal1 = new JLabel("00.00");

            grandtotal1.setBounds(10, 170, 280, 30);
            grandtotal1.setForeground(Color.black);

            grandtotal1.setFont(
                    new Font("arial", Font.BOLD, 25));

            right.add(grandtotal1);

            due1 = new JLabel("00.00");

            due1.setBounds(10, 210, 280, 30);
            due1.setForeground(Color.black);

            due1.setFont(
                    new Font("arial", Font.BOLD, 25));
            right.add(due1);

            JPanel down = new JPanel();

            down.setBounds(1550, 800, 350, 200);
            down.setBorder(BorderFactory.createLineBorder(Color.white, 0, true));
            down.setBackground(
                    new Color(0, 0, 0, 0));
            background.add(down);

            ImageIcon supply24 = new ImageIcon(ClassLoader.getSystemResource("images/cart3.png"));
            Image supply22 = supply24.getImage().getScaledInstance(300, 190, Image.SCALE_DEFAULT);
            ImageIcon supply23 = new ImageIcon(supply22);
            JLabel supplyimage2 = new JLabel("");

            supplyimage2.setIcon(supply23);

            supplyimage2.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));
            supplyimage2.setBounds(20, 10, 300, 190);
            down.add(supplyimage2);

            ImageIcon supply = new ImageIcon(ClassLoader.getSystemResource("images/supp.png"));
            Image supply2 = supply.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon supply3 = new ImageIcon(supply2);
            JLabel supplyimage = new JLabel("");

            supplyimage.setIcon(supply3);

            supplyimage.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));
            supplyimage.setBounds(20, 10, 50, 50);
            supplier.add(supplyimage);

            ImageIcon employe = new ImageIcon(ClassLoader.getSystemResource("images/emp.png"));
            Image employe2 = employe.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon employe3 = new ImageIcon(employe2);
            JLabel employeeimage = new JLabel("");

            employeeimage.setIcon(employe3);

            employeeimage.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));
            employeeimage.setBounds(20, 10, 50, 50);
            employee.add(employeeimage);

            ImageIcon sale = new ImageIcon(ClassLoader.getSystemResource("images/sales.png"));
            Image sale2 = sale.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon sale3 = new ImageIcon(sale2);
            JLabel saleimage = new JLabel("");

            saleimage.setIcon(sale3);

            saleimage.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));
            saleimage.setBounds(20, 10, 50, 50);
            sales.add(saleimage);

            ImageIcon invoic = new ImageIcon(ClassLoader.getSystemResource("images/in.png"));
            Image invoic2 = invoic.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon invoic3 = new ImageIcon(invoic2);
            JLabel invoicimage = new JLabel("");

            invoicimage.setIcon(invoic3);

            invoicimage.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));
            invoicimage.setBounds(20, 10, 50, 50);
            //invoice.add(invoicimage);

            ImageIcon report1 = new ImageIcon(ClassLoader.getSystemResource("images/report.png"));
            Image report12 = report1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon report13 = new ImageIcon(report12);
            JLabel reportimage = new JLabel("");

            reportimage.setIcon(report13);

            reportimage.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));
            reportimage.setBounds(20, 10, 50, 50);
            //reports.add(reportimage);

            ImageIcon Bill = new ImageIcon(ClassLoader.getSystemResource("images/product.png"));
            Image Bill2 = Bill.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon Bill3 = new ImageIcon(Bill2);
            JLabel billimage = new JLabel("");

            billimage.setIcon(Bill3);

            billimage.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));
            billimage.setBounds(20, 10, 50, 50);
            //bill.add(billimage);

            ImageIcon purchased = new ImageIcon(ClassLoader.getSystemResource("images/sales.png"));
            Image purchased2 = purchased.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon purchased3 = new ImageIcon(purchased2);
            JLabel purchasedimage = new JLabel("");

            purchasedimage.setIcon(purchased3);

            purchasedimage.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));
            purchasedimage.setBounds(20, 10, 50, 50);
            //purchase.add(purchasedimage);

            ImageIcon invoic22 = new ImageIcon(ClassLoader.getSystemResource("images/in.png"));
            Image invoic222 = invoic22.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon invoic223 = new ImageIcon(invoic222);
            JLabel invoic22image = new JLabel("");

            invoic22image.setIcon(invoic223);

            invoic22image.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));
            invoic22image.setBounds(20, 10, 50, 50);
            //account.add(invoic22image);

            ImageIcon reports = new ImageIcon(ClassLoader.getSystemResource("images/report.png"));
            Image reports2 = reports.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            ImageIcon reports3 = new ImageIcon(reports2);
            JLabel reportsimage = new JLabel("");

            reportsimage.setIcon(reports3);

            reportsimage.setCursor(
                    new Cursor(Cursor.HAND_CURSOR));
            reportsimage.setBounds(20, 10, 50, 50);
            //returns.add(reportsimage);

            setLayout(
                    null);
            setBounds(0, 0, 1920, 1080);
            setUndecorated(
                    true);
            getContentPane()
                    .setBackground(Color.WHITE);
        }

    

    public void sum() {

    }

    public void table_load() {

        try {

            DefaultTableModel dt = (DefaultTableModel) table.getModel();
            dt.setRowCount(0);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String query = "select * from bill";
            pstmt = DBConnection.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Vector v = new Vector();

                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));

                dt.addRow(v);

            }

            pstmt.close();
            DBConnection.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    public class BillPrintable implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {

            int r = itemname.size();

            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    // int headerRectHeighta=40;

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));

                    g2d.drawString("-------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("         CodeGuid.com        ", 12, y);
                    y += yShift;
                    g2d.drawString("   No 00000 Address Line One ", 12, y);
                    y += yShift;
                    g2d.drawString("   Address Line 02 SRI LANKA ", 12, y);
                    y += yShift;
                    g2d.drawString("   www.facebook.com/CodeGuid ", 12, y);
                    y += yShift;
                    g2d.drawString("        +94700000000      ", 12, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 12, y);
                    y += headerRectHeight;

                    g2d.drawString(" Item Name                  Price   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += headerRectHeight;

                    for (int s = 0; s < r; s++) {
                        g2d.drawString(" " + itemname.get(s) + "                            ", 10, y);
                        y += yShift;
                        g2d.drawString("      " + quantity2.get(s) + " * " + itemprice.get(s), 10, y);
                        g2d.drawString(subtotal.get(s), 160, y);
                        y += yShift;

                    }

                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Total Amount:               " + subtotal1.getText() + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Discount Amount      :                 " + discountamount1.getText() + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Grand Total Amount:               " + grandtotal1.getText() + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Cash      :                 " + tf4.getText() + "   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Balance   :                 " + due1.getText() + "   ", 10, y);
                    y += yShift;

                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                    g2d.drawString("       THANK YOU COME AGAIN            ", 10, y);
                    y += yShift;
                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                    g2d.drawString("       SOFTWARE BY:SACHIN-MALHOTRA            ", 10, y);
                    y += yShift;
                    g2d.drawString("   CONTACT: contacts@gmail.com       ", 10, y);
                    y += yShift;

                } catch (Exception e) {
                    e.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    public static void main(String args[]) {
        new home().setVisible(true);
    }
}
