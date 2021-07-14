package pointofsale;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class supplier extends JFrame {

    JTextField searchbar, product,custname, custmobile, custaddress, custemail, custbill, searchbar2, text1, t2, t3, t4, t7, t8,a1,a2,a3,a4,a5;
    JTextArea t5, t6,a6;
    JTable t1;
    JButton savebtn, update, delete, searchbtn;


    supplier() {

        // adding image of the upper panel
        ImageIcon close = new ImageIcon(ClassLoader.getSystemResource("images/first.png"));
        Image close2 = close.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon close3 = new ImageIcon(close2);
        JLabel l1 = new JLabel("");
        l1.setIcon(close3);
        l1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l1.setBounds(1550, 10, 20, 20);
        add(l1);

        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                dispose();
            }
        });

        JTabbedPane tp = new JTabbedPane();
        tp.setFont(new Font("arial", Font.BOLD, 22));
        tp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tp.setBackground(new Color(0, 0, 0, 0));
        tp.setBounds(10, 10, 1570, 860);
        add(tp);

        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(0, 0, 0, 0));
        panel1.setLayout(null);

        ImageIcon background = new ImageIcon(ClassLoader.getSystemResource("images/back6.jpg"));
        Image background2 = background.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        ImageIcon background3 = new ImageIcon(background2);
        JLabel b1 = new JLabel("");
        b1.setIcon(background3);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setBounds(0, 0, 1920, 1080);
        panel1.add(b1);

        JLabel search = new JLabel("Search:-");
        search.setBounds(10, 10, 200, 30);
        search.setForeground(Color.white);
        search.setFont(new Font("arial", Font.BOLD, 25));
        b1.add(search);

        searchbar = new JTextField();
        searchbar.setBounds(180, 10, 400, 30);
        searchbar.setHorizontalAlignment(JTextField.CENTER);
        searchbar.setBorder(BorderFactory.createEmptyBorder());
        searchbar.setFont(new Font("arial", Font.BOLD, 25));
        b1.add(searchbar);

        delete = new JButton("Show All");
        delete.setBackground(Color.orange);
        delete.setFocusable(false);
        delete.setForeground(Color.black);
        delete.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        delete.setFont(new Font("arial", Font.BOLD, 22));
        delete.setBounds(1100, 10, 150, 40);
        b1.add(delete);
        delete.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me1) {
                delete.setBackground(new Color(255, 153, 51));
                delete.setForeground(Color.white);
            }

            public void mouseExited(MouseEvent me1) {
                delete.setBackground(Color.orange);
                delete.setForeground(Color.black);
            }

            public void mouseClicked(MouseEvent me) {

                try {

                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    String query = "select * from supplier";
                    pstmt = DBConnection.prepareStatement(query);

                    rs = pstmt.executeQuery();
                    t1.setModel(DbUtils.resultSetToTableModel(rs));

                    pstmt.close();
                    DBConnection.close();

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        JButton save = new JButton("Save to Excel");
        save.setFocusable(false);
        save.setBounds(1300, 10, 200, 40);
        save.setBackground(Color.orange);
        save.setForeground(Color.black);
        save.setFont(new Font("arial", Font.BOLD, 22));
        save.setLayout(null);
        save.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.add(save);
        save.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me1) {
                save.setBackground(new Color(255, 153, 51));
                save.setForeground(Color.white);
            }

            public void mouseExited(MouseEvent me1) {
                save.setBackground(Color.orange);
                save.setForeground(Color.black);
            }

            public void mouseClicked(MouseEvent me) {
                
                BufferedOutputStream excelbou=null;
                        HSSFWorkbook exceljtableexport=null;
                        FileOutputStream excelfops=null;
                
                // choose location for swing jfilechooser
                
                    JFileChooser jfc=new JFileChooser("C:\\Users\\dell\\Documents\\malhotraengineers");
                   
                    // change dailogue box title
                    
                    jfc.setDialogTitle("Save As");
                    
                      // only filters files with these extesions "xls etc"
                    
                    FileNameExtensionFilter fnef=new FileNameExtensionFilter("EXCEL FILES","xls","xlsx","xlsm");
                    jfc.setFileFilter(fnef);
                    int jfc2=jfc.showSaveDialog(null);
                    
                    
                    //check if same button is pressed or not
                    
                    if(jfc2 == JFileChooser.APPROVE_OPTION)
                    {
                        
                        
                        
                        
                        try {
                            exceljtableexport=new HSSFWorkbook();
                            HSSFSheet excelsheet=exceljtableexport.createSheet("Excel Sheet");
                            for(int i=0;i<t1.getRowCount();i++)
                            {
                                HSSFRow excelrow=excelsheet.createRow(i);
                                for(int j=0;j<t1.getColumnCount();j++)
                                {
                                    HSSFCell excelcell=excelrow.createCell(j);
                                    
                                    excelcell.setCellValue(t1.getValueAt(i, j).toString());
                                    
                                }
                            }   excelfops = new FileOutputStream(jfc.getSelectedFile() + ".xls");
                           excelbou=new BufferedOutputStream(excelfops);
                            exceljtableexport.write(excelbou);
                            JOptionPane.showMessageDialog(null, "Supplier Excel File Created Sucessfully");
                            
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                        ex.printStackTrace();
                    } finally {
                            try {
                                  if(excelbou != null){   
                                    excelbou.close();
                                }
                                
                                if(excelfops != null){   
                                    excelfops.close();
                                }
                               
                                  if(exceljtableexport != null){   
                                    
                                }
                                
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                            
                            
                       
                    }
                  
                    
                    
                    
                  
            }
            
        });

        ImageIcon searchicon = new ImageIcon(ClassLoader.getSystemResource("images/search.png"));
        Image searchicon2 = searchicon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon searchicon3 = new ImageIcon(searchicon2);
        JLabel searchicon1 = new JLabel("");
        searchicon1.setIcon(searchicon3);
        searchicon1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchicon1.setBounds(590, 10, 30, 30);
        b1.add(searchicon1);
        b1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                try {

                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    String query = "select * from supplier where name=?";
                    pstmt = DBConnection.prepareStatement(query);
                    pstmt.setString(1, searchbar.getText());
                    rs = pstmt.executeQuery();
                    t1.setModel(DbUtils.resultSetToTableModel(rs));

                    pstmt.close();
                    DBConnection.close();

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        JLabel customername = new JLabel("Supplier Name:-");
        customername.setBounds(10, 120, 300, 30);
        customername.setForeground(Color.white);
        customername.setFont(new Font("arial", Font.BOLD, 25));
        b1.add(customername);

        custname = new JTextField();
        custname.setBounds(10, 160, 250, 35);
        custname.setHorizontalAlignment(JTextField.CENTER);
        custname.setBorder(BorderFactory.createEmptyBorder());
        custname.setFont(new Font("arial", Font.BOLD, 21));
        b1.add(custname);

        JLabel customeraddress = new JLabel("Product:-");
        customeraddress.setBounds(300, 120, 300, 30);
        customeraddress.setForeground(Color.white);
        customeraddress.setFont(new Font("arial", Font.BOLD, 25));
        b1.add(customeraddress);

        custaddress = new JTextField();
        custaddress.setBounds(300, 160, 300, 35);
        custaddress.setFont(new Font("arial", Font.BOLD, 21));
        custaddress.setHorizontalAlignment(JTextField.CENTER);
        custaddress.setBorder(BorderFactory.createEmptyBorder());
        b1.add(custaddress);

        JLabel customeremail = new JLabel("Supplier City:-");
        customeremail.setBounds(630, 120, 300, 30);
        customeremail.setForeground(Color.white);
        customeremail.setFont(new Font("arial", Font.BOLD, 25));
        b1.add(customeremail);

        custemail = new JTextField();
        custemail.setBounds(630, 160, 280, 35);
        custemail.setHorizontalAlignment(JTextField.CENTER);
        custemail.setBorder(BorderFactory.createEmptyBorder());
        custemail.setFont(new Font("arial", Font.BOLD, 21));
        b1.add(custemail);

        JLabel customermobileno = new JLabel("Contact Person:-");
        customermobileno.setBounds(930, 120, 400, 30);
        customermobileno.setForeground(Color.white);
        customermobileno.setFont(new Font("arial", Font.BOLD, 25));
        b1.add(customermobileno);

        custmobile = new JTextField();
        custmobile.setBounds(930, 160, 280, 35);
        custmobile.setHorizontalAlignment(JTextField.CENTER);
        custmobile.setBorder(BorderFactory.createEmptyBorder());
        custmobile.setFont(new Font("arial", Font.BOLD, 21));
        b1.add(custmobile);

        JLabel customerbillnumber = new JLabel("Account Number:-");
        customerbillnumber.setBounds(1250, 120, 300, 30);
        customerbillnumber.setForeground(Color.white);
        customerbillnumber.setFont(new Font("arial", Font.BOLD, 25));
        b1.add(customerbillnumber);

        custbill = new JTextField();
        custbill.setBounds(1250, 160, 300, 35);
        custbill.setBorder(BorderFactory.createEmptyBorder());
        custbill.setHorizontalAlignment(JTextField.CENTER);
        custbill.setFont(new Font("arial", Font.BOLD, 21));
        b1.add(custbill);

        JPanel ptable = new JPanel();
        ptable.setBounds(10, 250, 1545, 560);
        ptable.setLayout(new GridLayout(1, 1));
        ptable.setBackground(Color.white);
        b1.add(ptable);

        t1 = new JTable();
        ptable.add(t1);

        JScrollPane jsp = new JScrollPane(t1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ptable.add(jsp);

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            PreparedStatement pst = null;
            ResultSet rs = null;
            String str = "select * from supplier";
            pst = DBConnection.prepareStatement(str);
            rs = pst.executeQuery();
            t1.setModel(DbUtils.resultSetToTableModel(rs));
            pst.close();
            DBConnection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        t1.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent me) {
                int a = t1.getSelectedRow();

                String name = t1.getValueAt(a, 0).toString();
                String city = t1.getValueAt(a, 4).toString();
                String product = t1.getValueAt(a, 1).toString();
                String contactperson = t1.getValueAt(a, 9).toString();
                String acc = t1.getValueAt(a, 8).toString();

                custname.setText(name);
                custemail.setText(city);
                custaddress.setText(product);
                custmobile.setText(contactperson);
                custbill.setText(acc);

            }

        });

        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(new Color(0, 0, 0, 0));

        ImageIcon back = new ImageIcon(ClassLoader.getSystemResource("images/back6.jpg"));
        Image back2 = back.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        ImageIcon back3 = new ImageIcon(back2);
        JLabel backgroundimage = new JLabel("");
        backgroundimage.setIcon(back3);
        backgroundimage.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backgroundimage.setBounds(0, 0, 1920, 1080);
        panel2.add(backgroundimage);

        JLabel search2 = new JLabel("Search:-");
        search2.setBounds(10, 10, 200, 30);
        search2.setForeground(Color.white);
        search2.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(search2);

        searchbar2 = new JTextField();
        searchbar2.setBounds(180, 10, 400, 30);
        searchbar2.setHorizontalAlignment(JTextField.CENTER);
        searchbar2.setBorder(BorderFactory.createEmptyBorder());
        searchbar2.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(searchbar2);

        ImageIcon si = new ImageIcon(ClassLoader.getSystemResource("images/search.png"));
        Image si2 = si.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon si3 = new ImageIcon(si2);
        JLabel sea1 = new JLabel("");
        sea1.setIcon(si3);
        sea1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sea1.setBounds(590, 10, 30, 30);
        backgroundimage.add(sea1);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("E");
        SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
        String dat = sdf.format(d);
        String dat2 = sdf2.format(d);
        String dat3 = sdf3.format(d);

        JLabel D = new JLabel("Date Of Purchasing Goods");
        D.setBounds(1250, 20, 300, 40);
        D.setForeground(Color.white);
        D.setText(dat);
        D.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(D);

        JLabel Day = new JLabel("Date Of Purchasing Goods");
        Day.setBounds(1390, 20, 300, 40);
        Day.setForeground(Color.white);
        Day.setText(dat2);
        Day.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(Day);

        JLabel D3 = new JLabel("Date Of Purchasing Goods");
        D3.setBounds(1450, 20, 300, 40);
        D3.setForeground(Color.white);
        D3.setText(dat3);
        D3.setVisible(false);
        D3.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(D3);

        JLabel name = new JLabel("Name:-");
        name.setBounds(10, 90, 200, 30);
        name.setForeground(Color.white);
        name.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(name);

        text1 = new JTextField();
        text1.setBounds(240, 90, 500, 30);
        text1.setFont(new Font("arial", Font.BOLD, 23));
        text1.setHorizontalAlignment(JTextField.CENTER);
        text1.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(text1);

        JLabel product1=new JLabel("Product:-");
        product1.setBounds(10, 150, 200, 30);
        product1.setForeground(Color.white);
        product1.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(product1);
         
        
        
        product=new JTextField();
        product.setBounds(240, 150, 500, 30);
        product.setFont(new Font("arial", Font.BOLD, 23));
        product.setHorizontalAlignment(JTextField.CENTER);
        product.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(product);
        
       /*  try {
            //call out dbConnector method from Entity class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            String query = "select distinct brandname from product order by brandname";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //shows topic data in combobox
                product.addItem(rs.getString("brandname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
        
        JLabel officeno = new JLabel("Phone Number:-");
        officeno.setBounds(10, 210, 200, 30);
        officeno.setForeground(Color.white);
        officeno.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(officeno);

        t2 = new JTextField();
        t2.setBounds(240, 210, 500, 30);
        t2.setFont(new Font("arial", Font.BOLD, 23));
        t2.setHorizontalAlignment(JTextField.CENTER);
        t2.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(t2);

        JLabel customeremailid = new JLabel("Supplier Email:-");
        customeremailid.setBounds(10, 280, 300, 30);
        customeremailid.setForeground(Color.white);
        customeremailid.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(customeremailid);

        t3 = new JTextField();
        t3.setBounds(240, 280, 500, 30);
        t3.setFont(new Font("arial", Font.BOLD, 23));
        t3.setHorizontalAlignment(JTextField.CENTER);
        t3.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(t3);

        JLabel city = new JLabel("City:-");
        city.setBounds(10, 350, 200, 30);
        city.setForeground(Color.white);
        city.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(city);

        t4 = new JTextField();
        t4.setBounds(240, 350, 500, 30);
        t4.setFont(new Font("arial", Font.BOLD, 23));
        t4.setHorizontalAlignment(JTextField.CENTER);
        t4.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(t4);

        JLabel billingaddress = new JLabel("Billing Address:-");
        billingaddress.setBounds(10, 420, 200, 30);
        billingaddress.setForeground(Color.white);
        billingaddress.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(billingaddress);

        t5 = new JTextArea();
        t5.setBounds(240, 420, 500, 90);
        t5.setFont(new Font("arial", Font.BOLD, 23));

        t5.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(t5);

        JLabel shippingadress = new JLabel("Shipping Address:-");
        shippingadress.setBounds(10, 520, 300, 30);
        shippingadress.setForeground(Color.white);
        shippingadress.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(shippingadress);

        t6 = new JTextArea();
        t6.setBounds(240, 520, 500, 90);
        t6.setFont(new Font("arial", Font.BOLD, 23));

        t6.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(t6);

        JLabel bank = new JLabel("Bank:-");
        bank.setBounds(10, 620, 200, 30);
        bank.setForeground(Color.white);
        bank.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(bank);

        t7 = new JTextField();
        t7.setBounds(240, 620, 500, 30);
        t7.setFont(new Font("arial", Font.BOLD, 23));
        t7.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(t7);

        JLabel accno = new JLabel("Account No:-");
        accno.setBounds(10, 680, 200, 30);
        accno.setForeground(Color.white);
        t7.setHorizontalAlignment(JTextField.CENTER);
        accno.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(accno);

        t8 = new JTextField();
        t8.setBounds(240, 680, 500, 30);
        t8.setHorizontalAlignment(JTextField.CENTER);
        t8.setFont(new Font("arial", Font.BOLD, 23));
        t8.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(t8);

        savebtn = new JButton("Save");
        savebtn.setBounds(50, 750, 150, 40);
        savebtn.setBackground(Color.orange);
        savebtn.setFocusable(false);
        savebtn.setForeground(Color.black);
        savebtn.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        savebtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        savebtn.setFont(new Font("arial", Font.BOLD, 22));
        backgroundimage.add(savebtn);
        savebtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me1) {
                savebtn.setBackground(new Color(255, 153, 51));
                savebtn.setForeground(Color.white);
            }

            public void mouseExited(MouseEvent me1) {
                savebtn.setBackground(Color.orange);
                savebtn.setForeground(Color.black);
            }

            public void mouseClicked(MouseEvent me) {

                String name = text1.getText();
                String product2 = product.getText();
                String phonenumber2 = t2.getText();
                String supplieremail = t3.getText();
                String city2 = t4.getText();
                String billingaddress = t5.getText();
                String shippingaddress = t6.getText();
                String bank2 = t7.getText();
                String accountnumber = t8.getText();
                String contactperson = a1.getText();
                String contactemail = a2.getText();
                String phonenumber3=a3.getText();
                String phonenumber4=a4.getText();
                String city3=a5.getText();
                String note=a6.getText();
                
                
                try {

                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                    PreparedStatement pstmt = null;
                    String query = "INSERT  into supplier values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pstmt = DBConnection.prepareStatement(query);

                    pstmt.setString(1, name);
                    pstmt.setString(2, product2);
                    pstmt.setString(3, phonenumber2);
                    pstmt.setString(4, supplieremail);
                    pstmt.setString(5, city2);
                    pstmt.setString(6, billingaddress);
                    pstmt.setString(7, shippingaddress);
                    pstmt.setString(8, bank2);
                    pstmt.setString(9, accountnumber);
                    pstmt.setString(10, contactperson);
                    pstmt.setString(11, contactemail);
                    pstmt.setString(12, phonenumber3);
                    pstmt.setString(13, phonenumber4);
                    pstmt.setString(14, city3);
                    pstmt.setString(15, note);

                    int a = pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Supplier Added Sucessfully");
                    pstmt.close();
                    DBConnection.close();

                } catch (Exception ex) {
                    System.out.println(ex);
                }
                table_load();
            }
        });

        update = new JButton("Update");
        update.setBackground(Color.orange);
        update.setForeground(Color.black);
        update.setFocusable(false);
        update.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        update.setCursor(new Cursor(Cursor.HAND_CURSOR));
        update.setFont(new Font("arial", Font.BOLD, 22));
        update.setBounds(250, 750, 150, 40);
        backgroundimage.add(update);
        update.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me1) {
                update.setBackground(new Color(255, 153, 51));
                update.setForeground(Color.white);
            }

            public void mouseExited(MouseEvent me1) {
                update.setBackground(Color.orange);
                update.setForeground(Color.black);
            }

            public void mouseClicked(MouseEvent me) {

                try {

                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                    PreparedStatement pstmt = null;
                    String query = "update  supplier set name=?,phonenumber=?,shippingaddress=?,email=?,contactpersonname=?,contactpersonemail=?,phone1=?,phone2=? where name=?";
                    pstmt = DBConnection.prepareStatement(query);

                    pstmt.setString(1, text1.getText());
                    pstmt.setString(2, t2.getText());
                    pstmt.setString(3, t6.getText());
                    pstmt.setString(4, t3.getText());
                    pstmt.setString(5, a1.getText());
                    pstmt.setString(6, a2.getText());
                    pstmt.setString(7, a3.getText());
                    pstmt.setString(8, a4.getText());
                   
                    
                    pstmt.setString(9, searchbar2.getText());

                    int a = pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Customer Details updated Sucessfully");
                    pstmt.close();
                    DBConnection.close();

                } catch (Exception ex) {
                    System.out.println(ex);
                }
                table_load();
            }
        });

        searchbtn = new JButton("Search");
        searchbtn.setBackground(Color.orange);
        searchbtn.setForeground(Color.black);
        searchbtn.setFocusable(false);
        searchbtn.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        searchbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchbtn.setFont(new Font("arial", Font.BOLD, 22));
        searchbtn.setBounds(450, 750, 150, 40);
        backgroundimage.add(searchbtn);
        searchbtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me1) {
                searchbtn.setBackground(new Color(255, 153, 51));
                searchbtn.setForeground(Color.white);
            }

            public void mouseExited(MouseEvent me1) {
                searchbtn.setBackground(Color.orange);
                searchbtn.setForeground(Color.black);
            }

            public void mouseClicked(MouseEvent me) {

                try {

                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    String query = "select * from supplier where name=?";
                    pstmt = DBConnection.prepareStatement(query);
                    pstmt.setString(1, searchbar2.getText());
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        text1.setText(rs.getString("name"));

                        String str2 = rs.getString("product");
                        product.setText(str2);

                        String str3 = rs.getString("phonenumber");
                        t2.setText(str3);

                        String str5 = rs.getString("email");
                        t3.setText(str5);
                        
                        String str4 = rs.getString("city");
                        t4.setText(str4);

                        String str6 = rs.getString("billingaddress");
                        t5.setText(str6);

                        String str7 = rs.getString("shippingaddress");
                        t6.setText(str7);

                        String str8 = rs.getString("bank");
                        t7.setText(str8);

                        String str9 = rs.getString("accountnumber");
                        t8.setText(str9);

                         String str10 = rs.getString("contactpersonname");
                        a1.setText(str10);
                        
                        String str11 = rs.getString("contactpersonemail");
                        a2.setText(str11);

                        String str12 = rs.getString("phone1");
                        a3.setText(str12);

                        String str13 = rs.getString("phone2");
                        a4.setText(str13);

                        String str14 = rs.getString("city2");
                        a5.setText(str14);

                        String str15 = rs.getString("note");
                        a6.setText(str15);
                        
                    }

                    pstmt.close();
                    DBConnection.close();

                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        });

         JLabel c = new JLabel("Contact Person Details:-");
        c.setBounds(810, 110, 400, 30);
        c.setForeground(Color.white);
        c.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(c);
        
        
        JPanel border=new JPanel();
        border.setBounds(800,150,750,450);
        border.setLayout(null);
        border.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        border.setBackground(new Color(0,0,0,0));
        backgroundimage.add(border);
        
        
        
        JLabel contactperson = new JLabel("Name:-");
        contactperson.setBounds(10, 30, 200, 30);
        contactperson.setForeground(Color.white);
        contactperson.setFont(new Font("arial", Font.BOLD, 25));
        border.add(contactperson);

        a1 = new JTextField();
        a1.setBounds(240, 30, 500, 30);
        a1.setFont(new Font("arial", Font.BOLD, 23));
        a1.setHorizontalAlignment(JTextField.CENTER);
        a1.setBorder(BorderFactory.createEmptyBorder());
        border.add(a1);

        JLabel email=new JLabel("Email ID:-");
        email.setBounds(10, 90, 200, 30);
        email.setForeground(Color.white);
        email.setFont(new Font("arial", Font.BOLD, 25));
        border.add(email);
        
        a2 = new JTextField();
        a2.setBounds(240, 90, 500, 30);
        a2.setFont(new Font("arial", Font.BOLD, 23));
        a2.setHorizontalAlignment(JTextField.CENTER);
        a2.setBorder(BorderFactory.createEmptyBorder());
        border.add(a2);
        
        JLabel mobileno1 = new JLabel("Phone Number 01:-");
        mobileno1.setBounds(10, 150, 240, 30);
        mobileno1.setForeground(Color.white);
        mobileno1.setFont(new Font("arial", Font.BOLD, 25));
        border.add(mobileno1);

        a3 = new JTextField();
        a3.setBounds(240, 150, 500, 30);
        a3.setFont(new Font("arial", Font.BOLD, 23));
        a3.setHorizontalAlignment(JTextField.CENTER);
        a3.setBorder(BorderFactory.createEmptyBorder());
        border.add(a3);
        
         JLabel mobileno2 = new JLabel("Phone Number 02:-");
        mobileno2.setBounds(10, 210, 240, 30);
        mobileno2.setForeground(Color.white);
        mobileno2.setFont(new Font("arial", Font.BOLD, 25));
        border.add(mobileno2);

        a4 = new JTextField();
        a4.setBounds(240, 210, 500, 30);
        a4.setFont(new Font("arial", Font.BOLD, 23));
        a4.setHorizontalAlignment(JTextField.CENTER);
        a4.setBorder(BorderFactory.createEmptyBorder());
        border.add(a4);

        

        JLabel accno2 = new JLabel("City:-");
        accno2.setBounds(10, 270, 200, 30);
        accno2.setForeground(Color.white);
        accno2.setFont(new Font("arial", Font.BOLD, 25));
        border.add(accno2);

        
        a5 = new JTextField();
        a5.setBounds(240, 270, 500, 30);
        a5.setFont(new Font("arial", Font.BOLD, 23));
        a5.setHorizontalAlignment(JTextField.CENTER);
        a5.setBorder(BorderFactory.createEmptyBorder());
        border.add(a5);
        
        

        JLabel balance = new JLabel("Private Note:-");
        balance.setBounds(10, 330, 200, 30);
        balance.setForeground(Color.white);
        balance.setFont(new Font("arial", Font.BOLD, 25));
        border.add(balance);

        a6 = new JTextArea();
        a6.setBounds(240, 330, 500, 60);
        a6.setFont(new Font("arial", Font.BOLD, 23));
        a6.setBorder(BorderFactory.createEmptyBorder());
        border.add(a6);
       
        
        
        
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.white);

         ImageIcon backg = new ImageIcon(ClassLoader.getSystemResource("images/back6.jpg"));
        Image backg2 = backg.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        ImageIcon backg3 = new ImageIcon(backg2);
        JLabel b = new JLabel("");
        b.setIcon(back3);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setBounds(0, 0, 1920, 1080);
        panel3.add(b);
        
        JLabel title=new JLabel("malhotra-engineers");
        title.setBounds(650,20,700,80);
        title.setFont(new Font("arial",Font.BOLD,60));
        title.setForeground(Color.white);
        b.add(title);
        
         JLabel title2=new JLabel("All Supplier reports here");
        title2.setBounds(750,90,400,40);
        title2.setFont(new Font("arial",Font.BOLD,30));
        title2.setForeground(Color.white);
        b.add(title2);
        
        JButton show=new JButton("Create all Suppliers reports");
        show.setBackground(Color.orange);
        show.setForeground(Color.black);
        show.setFocusable(false);
        show.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        show.setCursor(new Cursor(Cursor.HAND_CURSOR));
        show.setFont(new Font("arial", Font.BOLD, 22));
        show.setBounds(700, 450, 500, 40);
        b.add(show);
        show.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me1) {
                show.setBackground(new Color(255, 153, 51));
                show.setForeground(Color.white);
            }

            public void mouseExited(MouseEvent me1) {
                show.setBackground(Color.orange);
                show.setForeground(Color.black);
            }
            public void mouseClicked(MouseEvent me){
                MessageFormat header=new MessageFormat("malhotra-engineers customers list");
                MessageFormat footer=new MessageFormat("malhotra-engineers pvt.lmtd");
                
                try{
                    t1.print(JTable.PrintMode.FIT_WIDTH,header,footer);
                    
                }catch(PrinterException ex){
                    System.out.println(ex);
                }
            }
        });
        
        
        tp.add("All Suppliers-Info", panel1);
        tp.add("Add-Supplier", panel2);
        tp.add("Supplier-Reports", panel3);

        setLayout(null);
        setBounds(320, 190, 1590, 880);
        setUndecorated(true);

    }

    public void table_load() {

        try {

            DefaultTableModel dt = (DefaultTableModel) t1.getModel();
            dt.setRowCount(0);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String query = "select * from supplier";
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
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                v.add(rs.getString(9));
                v.add(rs.getString(10));
                v.add(rs.getString(11));
                v.add(rs.getString(12));
                v.add(rs.getString(13));
                v.add(rs.getString(14));
                v.add(rs.getString(15));
                
                dt.addRow(v);

            }

            pstmt.close();
            DBConnection.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String args[]) {
        new supplier().setVisible(true);
    }

}
