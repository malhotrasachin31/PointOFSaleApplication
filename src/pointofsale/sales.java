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

public class sales extends JFrame {

    JTextField searchbar, custname, custmobile, custaddress, custemail, custbill, searchbar2, text1, t2, t3, t4, t7, t8;
    JTextArea t5, t6;
    JTable t1;
    JButton savebtn, update, delete, searchbtn;
    JLabel salesfigure;

    sales() {

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
                    String query = "select * from sales";
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
                            JOptionPane.showMessageDialog(null, "Excel File Created Sucessfully");
                            
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
                    String query = "select * from sales where customername=?";
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

        JLabel customeraddress = new JLabel("Brand Name:-");
        customeraddress.setBounds(300, 120, 300, 30);
        customeraddress.setForeground(Color.white);
        customeraddress.setFont(new Font("arial", Font.BOLD, 25));
        b1.add(customeraddress);

        custaddress = new JTextField();
        custaddress.setBounds(300, 160, 300, 35);
        custaddress.setHorizontalAlignment(JTextField.CENTER);
        custaddress.setFont(new Font("arial", Font.BOLD, 21));
        custaddress.setBorder(BorderFactory.createEmptyBorder());
        b1.add(custaddress);

        JLabel customeremail = new JLabel("Company Name:-");
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

        JLabel customermobileno = new JLabel("Quantity Sold:-");
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

        JLabel customerbillnumber = new JLabel("Total Sales Figure:-");
        customerbillnumber.setBounds(1250, 120, 500, 30);
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
        //ptable.setBackground(Color.white);
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
            //String str = "select customername,typeofpayment,day,productname,product,companyname,quantity,grandtotal,date from sales";
            String str="select * from sales";
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

                String productname = t1.getValueAt(a, 4).toString();
                String companyname = t1.getValueAt(a, 3).toString();
                String brandname = t1.getValueAt(a, 2).toString();
                String totalquantity = t1.getValueAt(a, 12).toString();
                String grandtotal = t1.getValueAt(a, 17).toString();

                custname.setText(productname);
                custemail.setText(companyname);
                custaddress.setText(brandname);
                custmobile.setText(totalquantity);
                custbill.setText(grandtotal);

            }
       
        });

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.white);

         ImageIcon backg = new ImageIcon(ClassLoader.getSystemResource("images/back6.jpg"));
        Image backg2 = backg.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        ImageIcon backg3 = new ImageIcon(backg2);
        JLabel b = new JLabel("");
        b.setIcon(backg3);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setBounds(0, 0, 1920, 1080);
        panel3.add(b);
        
        JLabel title=new JLabel("malhotra-engineers");
        title.setBounds(650,20,700,80);
        title.setFont(new Font("arial",Font.BOLD,60));
        title.setForeground(Color.white);
        b.add(title);
        
         JLabel title2=new JLabel("All Sales Reports here");
        title2.setBounds(750,90,400,40);
        title2.setFont(new Font("arial",Font.BOLD,30));
        title2.setForeground(Color.white);
        b.add(title2);
        
         salesfigure=new JLabel();
         salesfigure.setBounds(550,200,1200,160);
        salesfigure.setFont(new Font("arial",Font.BOLD,160));
        salesfigure.setForeground(Color.ORANGE);
        b.add(salesfigure);
        
        try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                    PreparedStatement pst = null;
                    ResultSet rs = null;
                    String str = "select SUM(grandtotal) as totalquantity from sales ";
                    pst = DBConnection.prepareStatement(str);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        double totalv = rs.getDouble("totalquantity");
                        String total2 = Double.toString(totalv);
                        salesfigure.setText(total2);
                    }
                    pst.close();
                    DBConnection.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

      
        
        JButton show=new JButton("Create Sales reports");
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
                MessageFormat header=new MessageFormat("malhotra-engineers Sales Reports");
                MessageFormat footer=new MessageFormat("malhotra-engineers pvt.lmtd");
                
                try{
                    t1.print(JTable.PrintMode.FIT_WIDTH,header,footer);
                    
                }catch(PrinterException ex){
                    System.out.println(ex);
                }
            }
        });
        
        
        tp.add("All Sales-Information", panel1);
        tp.add("Sales-Reports", panel3);

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
            String query = "select * from sales";
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

                dt.addRow(v);

            }

            pstmt.close();
            DBConnection.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String args[]) {
        new sales().setVisible(true);
    }

}
