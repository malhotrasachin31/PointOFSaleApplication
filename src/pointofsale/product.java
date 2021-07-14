package pointofsale;

import com.barcodelib.barcode.Linear;
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

public class product extends JFrame {

    JTextField searchbar, custname, custmobile, custaddress, custemail, custbill, searchbar2, text1, product, a1, a2, a3, a4, a5;
    JComboBox t2, t4, t5, t7, t8, t3;
    JTextArea t6, a6;
    JTable t1;
    JButton savebtn, update, delete, searchbtn;

    product() {

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
                    String query = "select * from product";
                    pstmt = DBConnection.prepareStatement(query);

                    rs = pstmt.executeQuery();
                    t1.setModel(DbUtils.resultSetToTableModel(rs));

                    pstmt.close();
                    DBConnection.close();

                } catch (Exception ex) {
                    System.out.println(ex);
                }
                table_load();
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

                BufferedOutputStream excelbou = null;
                HSSFWorkbook exceljtableexport = null;
                FileOutputStream excelfops = null;

                // choose location for swing jfilechooser
                JFileChooser jfc = new JFileChooser("C:\\Users\\dell\\Documents\\malhotraengineers");

                    // change dailogue box title
                jfc.setDialogTitle("Save As");

                      // only filters files with these extesions "xls etc"
                FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
                jfc.setFileFilter(fnef);
                int jfc2 = jfc.showSaveDialog(null);

                    //check if same button is pressed or not
                if (jfc2 == JFileChooser.APPROVE_OPTION) {

                    try {
                        exceljtableexport = new HSSFWorkbook();
                        HSSFSheet excelsheet = exceljtableexport.createSheet("Excel Sheet");
                        for (int i = 0; i < t1.getRowCount(); i++) {
                            HSSFRow excelrow = excelsheet.createRow(i);
                            for (int j = 0; j < t1.getColumnCount(); j++) {
                                HSSFCell excelcell = excelrow.createCell(j);

                                excelcell.setCellValue(t1.getValueAt(i, j).toString());

                            }
                        }
                        excelfops = new FileOutputStream(jfc.getSelectedFile() + ".xls");
                        excelbou = new BufferedOutputStream(excelfops);
                        exceljtableexport.write(excelbou);
                        JOptionPane.showMessageDialog(null, "Supplier Excel File Created Sucessfully");

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            if (excelbou != null) {
                                excelbou.close();
                            }

                            if (excelfops != null) {
                                excelfops.close();
                            }

                            if (exceljtableexport != null) {
                               
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
                    String query = "select * from product where name=?";
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

        JLabel customername = new JLabel("Product Name:-");
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

        JLabel customeraddress = new JLabel("Category:-");
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

        JLabel customeremail = new JLabel("Supplier Name:-");
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

        JLabel customermobileno = new JLabel("Company Name:-");
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

        JLabel customerbillnumber = new JLabel("Brand Name:-");
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
            String str = "select * from product";
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
                String city = t1.getValueAt(a, 2).toString();
                String product = t1.getValueAt(a, 1).toString();
                String contactperson = t1.getValueAt(a, 11).toString();
                String acc = t1.getValueAt(a, 10).toString();

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

       
        
        JLabel product1 = new JLabel("Name:-");
        product1.setBounds(10, 150, 200, 30);
        product1.setForeground(Color.white);
        product1.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(product1);
       
        
        
        product = new JTextField();
        product.setBounds(240, 150, 500, 30);
        product.setFont(new Font("arial", Font.BOLD, 23));
        product.setHorizontalAlignment(JTextField.CENTER);
        product.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(product);
        
        

        JLabel officeno = new JLabel("Category Type:-");
        officeno.setBounds(10, 210, 200, 30);
        officeno.setForeground(Color.white);
        officeno.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(officeno);

        t2 = new JComboBox();
        t2.setBounds(240, 210, 500, 40);
        t2.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        t2.setFont(new Font("arial", Font.BOLD, 23));
        t2.setBackground(Color.white);
        t2.setBorder(BorderFactory.createEmptyBorder());
        t2.addItem("Eating Items");
        t2.addItem("Washing Items");
        t2.addItem("Drinking Items");
        t2.addItem("Music Items");
        t2.addItem("Playing Items");
        t2.addItem("Clothing Items");
        t2.addItem("Toys Items");
        t2.addItem("Entertainment Items");
        t2.addItem("Cosmetics Items");
        t2.addItem("Luxury Items");
        t2.addItem("Electric items");
        backgroundimage.add(t2);

        JLabel customeremailid = new JLabel("Supplier Name:-");
        customeremailid.setBounds(10, 280, 300, 30);
        customeremailid.setForeground(Color.white);
        customeremailid.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(customeremailid);

        t3 = new JComboBox();
        t3.setBounds(240, 280, 500, 30);
        t3.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        t3.setFont(new Font("arial", Font.BOLD, 23));
        t3.setBackground(Color.white);
        t3.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(t3);
        try {
            //call out dbConnector method from Entity class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            String query = "select distinct name from supplier order by name";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //shows topic data in combobox
                t3.addItem(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel city = new JLabel("Warranty:-");
        city.setBounds(10, 350, 200, 30);
        city.setForeground(Color.white);
        city.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(city);

        t4 = new JComboBox();
        t4.setBounds(240, 350, 500, 30);
        t4.addItem("1 mon");
        t4.addItem("2 mon");
        t4.addItem("5 mon");
        t4.addItem("1 yr");
        t4.addItem("2 yr");
        t4.addItem("5 yr");
        t4.addItem("None");
        t4.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        t4.setFont(new Font("arial", Font.BOLD, 23));
        t4.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(t4);

        JLabel billingaddress = new JLabel("Quantity:-");
        billingaddress.setBounds(10, 420, 200, 30);
        billingaddress.setForeground(Color.white);
        billingaddress.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(billingaddress);

        t5 = new JComboBox();
        t5.setBounds(240, 420, 500, 30);
        t5.setBackground(Color.white);
        t5.addItem("50");
        t5.addItem("100");
        t5.addItem("150");
        t5.addItem("200");
        t5.addItem("250");
        t5.addItem("300");
        t5.addItem("350");
        t5.addItem("400");
        t5.addItem("450");
        t5.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        t5.setFont(new Font("arial", Font.BOLD, 23));
        t5.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(t5);

        JLabel shippingadress = new JLabel("Discription:-");
        shippingadress.setBounds(10, 520, 300, 30);
        shippingadress.setForeground(Color.white);
        shippingadress.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(shippingadress);

        t6 = new JTextArea();
        t6.setBounds(240, 520, 500, 90);
        t6.setFont(new Font("arial", Font.BOLD, 23));

        t6.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(t6);

        JLabel bank = new JLabel("Default-Unit:-");
        bank.setBounds(10, 620, 200, 30);
        bank.setForeground(Color.white);
        bank.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(bank);

        t7 = new JComboBox();
        t7.setBounds(240, 620, 500, 30);
        t7.setBackground(Color.white);
        t7.addItem("Litre");
        t7.addItem("Kilo-Gram");
        t7.addItem("Tonn");
        t7.addItem("Grams");
        t7.addItem("Milililitre");
        t7.addItem("Inches");
        t7.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        t7.setFont(new Font("arial", Font.BOLD, 23));
        t7.setBorder(BorderFactory.createEmptyBorder());
        backgroundimage.add(t7);

        JLabel accno = new JLabel("Symbol:-");
        accno.setBounds(10, 680, 200, 30);
        accno.setForeground(Color.white);
        accno.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(accno);

        t8 = new JComboBox();
        t8.setBounds(240, 680, 500, 30);
        t8.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        t8.setBackground(Color.white);
        t8.addItem("Recycle");
        t8.addItem("ISI Mark");
        t8.addItem("Reusable");
        t8.addItem("Throw it once Used");
        t8.addItem("Dont Throw It");
        t8.addItem("Please Stay away from children below 10 of age");
        t8.addItem("Dont Jump Over It");
        t8.addItem("Vegitarian");
        t8.addItem("Non Vegitarian");
        t8.addItem("Kindly Delivery in Peacefull manner");
        t8.setFont(new Font("arial", Font.BOLD, 20));
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

                String name = product.getText();
                 String category = t2.getSelectedItem().toString();
                 String suppliername = t3.getSelectedItem().toString();
                 String warranty = t4.getSelectedItem().toString();
                 String quantity = t5.getSelectedItem().toString();
                 String discription = t6.getText();
                 String defaultunit= t7.getSelectedItem().toString();
                 String symbol = t8.getSelectedItem().toString();
                 String mfdate = a1.getText();
                 String expdate = a2.getText();
                 String brandname = a3.getText();
                 String companyname=a4.getText();
                 String unitprice=a5.getText();
                 String privatenote=a6.getText();
                try {

                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                    PreparedStatement pstmt = null;
                    String query = "INSERT  into product values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    pstmt = DBConnection.prepareStatement(query);

                     pstmt.setString(1, name);
                     pstmt.setString(2, category);
                     pstmt.setString(3, suppliername);
                     pstmt.setString(4, warranty);
                     pstmt.setString(5, quantity);
                     pstmt.setString(6, discription);
                     pstmt.setString(7, defaultunit);
                     pstmt.setString(8, symbol);
                     pstmt.setString(9, mfdate);
                     pstmt.setString(10, expdate);
                     pstmt.setString(11, brandname);
                     pstmt.setString(12, companyname);
                     pstmt.setString(13, unitprice);
                     pstmt.setString(14, privatenote);
                    
                    int a = pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Supplier Added Sucessfully");
                    pstmt.close();
                    DBConnection.close();

                     Linear barcode=new Linear();
                barcode.setType(Linear.CODE128B);
                barcode.setI(11.0f);
                
                String Fname=product.getText();
                try {
                    barcode.renderBarcode("C:\\Users\\dell\\Documents\\malhotraengineers\\All barcodes of product\\" +Fname+ ".png");
                     JOptionPane.showMessageDialog(null, "Bar code is Successfully Generated at documents>malhotraengineers> !");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                    
                    
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

                     String name = product.getText();
                 String category = t2.getSelectedItem().toString();
                 String suppliername = t3.getSelectedItem().toString();
                 String warranty = t4.getSelectedItem().toString();
                 String quantity = t5.getSelectedItem().toString();
                 String discription = t6.getText();
                 String defaultunit= t7.getSelectedItem().toString();
                 String symbol = t8.getSelectedItem().toString();
                 String mfdate = a1.getText();
                 String expdate = a2.getText();
                 String brandname = a3.getText();
                 String companyname=a4.getText();
                 String unitprice=a5.getText();
                 String privatenote=a6.getText();
                    
                    
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                    PreparedStatement pstmt = null;
                    String query = "update  product set name=?,category=?,suppliername=?,warranty=?,quantity=?,discription=?,defaultunit=?,symbol=?,manufacturingdate=?,expirydate=?,brandname=?,companyname=?,unitprice=?,privatenote=? where name=?";
                    pstmt = DBConnection.prepareStatement(query);

                    pstmt.setString(1, name);
                     pstmt.setString(2, category);
                     pstmt.setString(3, suppliername);
                     pstmt.setString(4, warranty);
                     pstmt.setString(5, quantity);
                     pstmt.setString(6, discription);
                     pstmt.setString(7, defaultunit);
                     pstmt.setString(8, symbol);
                     pstmt.setString(9, mfdate);
                     pstmt.setString(10, expdate);
                     pstmt.setString(11, brandname);
                     pstmt.setString(12, companyname);
                     pstmt.setString(13, unitprice);
                     pstmt.setString(14, privatenote);
                     

                    pstmt.setString(15, searchbar2.getText());

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
                    String query = "select * from product where name=?";
                    pstmt = DBConnection.prepareStatement(query);
                    pstmt.setString(1, searchbar2.getText());
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                       
                        String name = rs.getString("name");
                        product.setText(name);

                        String category = rs.getString("category");
                         t2.setSelectedItem(category);

                         String suppliername = rs.getString("suppliername");
                         t3.setSelectedItem(suppliername);
                        
                         String warranty = rs.getString("warranty");
                         t4.setSelectedItem(warranty);

                         String str6 = rs.getString("quantity");
                         t5.setSelectedItem(str6);

                         String str7 = rs.getString("discription");
                         t6.setText(str7);

                         String str8 = rs.getString("defaultunit");
                         t7.setSelectedItem(str8);

                         String str9 = rs.getString("symbol");
                         t8.setSelectedItem(str9);

                         String str10 = rs.getString("manufacturingdate");
                         a1.setText(str10);
                        
                         String str11 = rs.getString("expirydate");
                         a2.setText(str11);

                         String str12 = rs.getString("brandname");
                         a3.setText(str12);

                         String str13 = rs.getString("companyname");
                         a4.setText(str13);

                         String str14 = rs.getString("unitprice");
                         a5.setText(str14);

                         String str15 = rs.getString("privatenote");
                         a6.setText(str15);
                    }

                    pstmt.close();
                    DBConnection.close();

                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        });

        JLabel c = new JLabel("More Product Details:-");
        c.setBounds(810, 110, 400, 30);
        c.setForeground(Color.white);
        c.setFont(new Font("arial", Font.BOLD, 25));
        backgroundimage.add(c);

        JPanel border = new JPanel();
        border.setBounds(800, 150, 750, 450);
        border.setLayout(null);
        border.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        border.setBackground(new Color(0, 0, 0, 0));
        backgroundimage.add(border);

        JLabel contactperson = new JLabel("M.F Date:-");
        contactperson.setBounds(10, 30, 200, 30);
        contactperson.setForeground(Color.white);
        contactperson.setFont(new Font("arial", Font.BOLD, 25));
        border.add(contactperson);

        a1 = new JTextField();
        a1.setBounds(240, 30, 300, 30);
        a1.setFont(new Font("arial", Font.BOLD, 23));
        a1.setHorizontalAlignment(JTextField.CENTER);
        a1.setBorder(BorderFactory.createEmptyBorder());
        border.add(a1);

        JLabel email = new JLabel("Exp. Date:-");
        email.setBounds(10, 90, 200, 30);
        email.setForeground(Color.white);
        email.setFont(new Font("arial", Font.BOLD, 25));
        border.add(email);

        a2 = new JTextField();
        a2.setBounds(240, 90, 300, 30);
        a2.setFont(new Font("arial", Font.BOLD, 23));
        a2.setHorizontalAlignment(JTextField.CENTER);
        a2.setBorder(BorderFactory.createEmptyBorder());
        border.add(a2);

        JLabel mobileno1 = new JLabel("Brand Name:-");
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

        JLabel mobileno2 = new JLabel("Company Name:-");
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

        JLabel accno2 = new JLabel("Unit Price:-");
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

        JLabel title = new JLabel("malhotra-engineers");
        title.setBounds(650, 20, 700, 80);
        title.setFont(new Font("arial", Font.BOLD, 60));
        title.setForeground(Color.white);
        b.add(title);

        JLabel title2 = new JLabel("All product reports here");
        title2.setBounds(750, 90, 400, 40);
        title2.setFont(new Font("arial", Font.BOLD, 30));
        title2.setForeground(Color.white);
        b.add(title2);

        JButton show = new JButton("Create all Products reports");
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

            public void mouseClicked(MouseEvent me) {
                MessageFormat header = new MessageFormat("malhotra-engineers product list");
                MessageFormat footer = new MessageFormat("malhotra-engineers pvt.lmtd");

                try {
                    t1.print(JTable.PrintMode.FIT_WIDTH, header, footer);

                } catch (PrinterException ex) {
                    System.out.println(ex);
                }
            }
        });

        JPanel panel4 = new JPanel();
        panel4.setBackground(new Color(0, 0, 0, 0));
        panel4.setLayout(null);

        ImageIcon background4 = new ImageIcon(ClassLoader.getSystemResource("images/back6.jpg"));
        Image background42 = background4.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        ImageIcon background43 = new ImageIcon(background42);
        JLabel b41 = new JLabel("");
        b41.setIcon(background3);
        b41.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b41.setBounds(0, 0, 1920, 1080);
        panel4.add(b41);

        JButton printreport=new JButton("Print Report");
        printreport.setBackground(Color.orange);
        printreport.setForeground(Color.black);
        printreport.setFocusable(false);
        printreport.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        printreport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        printreport.setFont(new Font("arial", Font.BOLD, 22));
        printreport.setBounds(40, 40, 300, 40);
        b41.add(printreport);
        printreport.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me1) {
                printreport.setBackground(new Color(255, 153, 51));
                printreport.setForeground(Color.white);
            }

            public void mouseExited(MouseEvent me1) {
                printreport.setBackground(Color.orange);
                printreport.setForeground(Color.black);
            }
            public void mouseClicked(MouseEvent me) {
                MessageFormat header = new MessageFormat("malhotra-engineers Most Selling product list");
                MessageFormat footer = new MessageFormat("malhotra-engineers pvt.lmtd");

                try {
                    t1.print(JTable.PrintMode.FIT_WIDTH, header, footer);

                } catch (PrinterException ex) {
                    System.out.println(ex);
                }
            }
        });
        
        JButton savetoexcel=new JButton("Save To Excel");
        savetoexcel.setBackground(Color.orange);
        savetoexcel.setForeground(Color.black);
        savetoexcel.setFocusable(false);
        savetoexcel.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        savetoexcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        savetoexcel.setFont(new Font("arial", Font.BOLD, 22));
        savetoexcel.setBounds(40, 100, 300, 40);
        b41.add(savetoexcel);
        savetoexcel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me1) {
                savetoexcel.setBackground(new Color(255, 153, 51));
                savetoexcel.setForeground(Color.white);
            }

            public void mouseExited(MouseEvent me1) {
                savetoexcel.setBackground(Color.orange);
                savetoexcel.setForeground(Color.black);
            }
            public void mouseClicked(MouseEvent me) {

                BufferedOutputStream excelbou = null;
                HSSFWorkbook exceljtableexport = null;
                FileOutputStream excelfops = null;

                // choose location for swing jfilechooser
                JFileChooser jfc = new JFileChooser("C:\\Users\\dell\\Documents\\malhotraengineers");

                    // change dailogue box title
                jfc.setDialogTitle("Save As");

                      // only filters files with these extesions "xls etc"
                FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
                jfc.setFileFilter(fnef);
                int jfc2 = jfc.showSaveDialog(null);

                    //check if same button is pressed or not
                if (jfc2 == JFileChooser.APPROVE_OPTION) {

                    try {
                        exceljtableexport = new HSSFWorkbook();
                        HSSFSheet excelsheet = exceljtableexport.createSheet("Excel Sheet");
                        for (int i = 0; i < t1.getRowCount(); i++) {
                            HSSFRow excelrow = excelsheet.createRow(i);
                            for (int j = 0; j < t1.getColumnCount(); j++) {
                                HSSFCell excelcell = excelrow.createCell(j);

                                excelcell.setCellValue(t1.getValueAt(i, j).toString());

                            }
                        }
                        excelfops = new FileOutputStream(jfc.getSelectedFile() + ".xls");
                        excelbou = new BufferedOutputStream(excelfops);
                        exceljtableexport.write(excelbou);
                        JOptionPane.showMessageDialog(null, "Supplier Excel File Created Sucessfully");

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            if (excelbou != null) {
                                excelbou.close();
                            }

                            if (excelfops != null) {
                                excelfops.close();
                            }

                            if (exceljtableexport != null) {
                               
                            }

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                }

            }
        });
        
       /* JButton filter=new JButton("Filter Data");
        filter.setBackground(Color.orange);
        filter.setForeground(Color.black);
        filter.setFocusable(false);
        filter.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        filter.setCursor(new Cursor(Cursor.HAND_CURSOR));
        filter.setFont(new Font("arial", Font.BOLD, 22));
        filter.setBounds(40, 160, 300, 40);
        b41.add(filter);
        filter.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me1) {
                filter.setBackground(new Color(255, 153, 51));
                filter.setForeground(Color.white);
            }

            public void mouseExited(MouseEvent me1) {
                filter.setBackground(Color.orange);
                filter.setForeground(Color.black);
            }
        });*/
        
        JPanel p=new JPanel();
        p.setBounds(440,10,1110,800);
        p.setLayout(new GridLayout(1,1));
        p.setBackground(new Color(0,0,0,0));
        b41.add(p);
        
        JTable table2=new JTable();
        table2.setBackground(Color.white);
        p.add(table2);
        
        JScrollPane sp=new JScrollPane(table2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        p.add(sp);
        
         try {

                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    String query = "select product,quantity,grandtotal from sales";
                    pstmt = DBConnection.prepareStatement(query);

                    rs = pstmt.executeQuery();
                    table2.setModel(DbUtils.resultSetToTableModel(rs));

                    pstmt.close();
                    DBConnection.close();

                } catch (Exception ex) {
                    System.out.println(ex);
                }
                table_load();
        
        
        tp.add("All Product-Info", panel1);
        tp.add("Add-Product", panel2);
        tp.add("Product-Reports", panel3);
        tp.add("Most Selling Products",panel4);
        

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
            String query = "select * from product";
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
               

                dt.addRow(v);

            }

            pstmt.close();
            DBConnection.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String args[]) {
        new product().setVisible(true);
    }

}
