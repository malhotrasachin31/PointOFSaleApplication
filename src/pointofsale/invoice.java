package pointofsale;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.proteanit.sql.DbUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class invoice extends JFrame{
    
    JComboBox c1;
    JButton print;
    JTable t1;
    JTextField tf1,tf2,tf3,tf4;
    
    invoice(){
        ImageIcon close = new ImageIcon(ClassLoader.getSystemResource("images/first.png"));
        Image close2 = close.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon close3 = new ImageIcon(close2);
        JLabel l1 = new JLabel("");
        l1.setIcon(close3);
        l1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l1.setBounds(1550, 10, 20, 20);
        add(l1);
        
        l1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
             dispose();   
            }
        });
        
        JTabbedPane tp=new JTabbedPane();
        tp.setFont(new Font("arial", Font.BOLD, 22));
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
        b1.setLayout(null);
        b1.setBounds(0, 0, 1920, 1080);
        panel1.add(b1);
        
        JPanel first=new JPanel();
        first.setBackground(new Color(0,0,0,0));
        first.setBounds(10,10,400,200);
        first.setLayout(null);
        first.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        b1.add(first);
        
        JLabel unpaidvoiceamount=new JLabel("Unpaid Voice Amount:-");
        unpaidvoiceamount.setBounds(10,10,300,25);
        unpaidvoiceamount.setFont(new Font("arial",Font.PLAIN,20));
        unpaidvoiceamount.setForeground(Color.white);
        first.add(unpaidvoiceamount);
        
        JLabel allpaidvoiceamount=new JLabel("All Paid Voice Amount:-");
        allpaidvoiceamount.setBounds(10,40,300,25);
        allpaidvoiceamount.setFont(new Font("arial",Font.PLAIN,20));
        allpaidvoiceamount.setForeground(Color.white);
        first.add(allpaidvoiceamount);
        
        JLabel allunpaidvoiceamount=new JLabel("All Unpaid Voice Amount:-");
        allunpaidvoiceamount.setBounds(10,70,300,25);
        allunpaidvoiceamount.setFont(new Font("arial",Font.PLAIN,20));
        allunpaidvoiceamount.setForeground(Color.white);
        first.add(allunpaidvoiceamount);
        
        JLabel allvoiceamount=new JLabel("All Invoice Amount:-");
        allvoiceamount.setBounds(10,100,300,25);
        allvoiceamount.setFont(new Font("arial",Font.PLAIN,20));
        allvoiceamount.setForeground(Color.white);
        first.add(allvoiceamount);
        
        JButton save=new JButton("Save to Excel");
        save.setBackground(Color.orange);
        save.setFocusable(false);
        save.setForeground(Color.black);
        save.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        save.setFont(new Font("arial", Font.BOLD, 18));
        save.setBounds(20, 150, 150, 40);
        first.add(save);
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

                    } catch (FileNotFoundException ex) 
                    {
                        ex.printStackTrace();
                    } catch (IOException ex) 
                    {
                        ex.printStackTrace();
                    } finally 
                    {
                        try {
                            if (excelbou != null) 
                            {
                                excelbou.close();
                            }

                            if (excelfops != null) 
                            {
                                excelfops.close();
                            }

                            if (exceljtableexport != null) {
                                //exceljtableexport.close();
                            }

                        } catch (IOException ex) {
                           System.out.println(ex);
                        }
                    
                    }
                }
                
                
                
            }
        });
        
        print = new JButton("Pay/Print Invoice");
        print.setFocusable(false);
        print.setBounds(180, 150, 200, 40);
        print.setBackground(Color.orange);
        print.setForeground(Color.black);
        print.setFont(new Font("arial", Font.BOLD, 18));
        print.setLayout(null);
        print.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        print.setCursor(new Cursor(Cursor.HAND_CURSOR));
        first.add(print);
        print.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me1) {
                print.setBackground(new Color(255, 153, 51));
                print.setForeground(Color.white);
            }

            public void mouseExited(MouseEvent me1) {
                print.setBackground(Color.orange);
                print.setForeground(Color.black);
            }
            public void mouseClicked(MouseEvent me){
                
            }
        
        });
        
        JPanel up=new JPanel();
        up.setBounds(415,10,1140,50);
        up.setLayout(null);
        up.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        up.setBackground(new Color(0,0,0,0));
        b1.add(up);
        
        JLabel search=new JLabel("Search Invoice ID:-");
        search.setBounds(300,13,300,25);
        search.setFont(new Font("arial",Font.PLAIN,20));
        search.setForeground(Color.ORANGE);
        up.add(search);
        
        tf1=new JTextField();
        tf1.setBounds(500,10,400,30);
        tf1.setHorizontalAlignment(JTextField.CENTER);
        tf1.setBackground(Color.white);
        tf1.setBorder(BorderFactory.createEmptyBorder());
        tf1.setFont(new Font("arial",Font.PLAIN,18));
        tf1.setForeground(Color.gray);
        up.add(tf1);
        
        JPanel down=new JPanel();
        down.setBounds(415,140,1140,70);
        down.setLayout(null);
        down.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        down.setBackground(new Color(0,0,0,0));
        b1.add(down);
        
        JLabel totalpaidamount=new JLabel("Total Amt.");
        totalpaidamount.setBounds(150,5,200,15);
        totalpaidamount.setFont(new Font("arial",Font.PLAIN,12));
        totalpaidamount.setForeground(Color.WHITE);
        down.add(totalpaidamount);
        
        tf2=new JTextField();
        tf2.setBounds(50,25,300,30);
        tf2.setHorizontalAlignment(JTextField.CENTER);
        tf2.setBackground(Color.white);
        tf2.setBorder(BorderFactory.createEmptyBorder());
        tf2.setFont(new Font("arial",Font.PLAIN,18));
        tf2.setForeground(Color.gray);
        down.add(tf2);
        
        JLabel totalunpaidamount=new JLabel("Grand Total Amt.");
        totalunpaidamount.setBounds(550,5,200,15);
        totalunpaidamount.setFont(new Font("arial",Font.PLAIN,12));
        totalunpaidamount.setForeground(Color.WHITE);
        down.add(totalunpaidamount);
        
        tf3=new JTextField();
        tf3.setBounds(425,25,300,30);
        tf3.setHorizontalAlignment(JTextField.CENTER);
        tf3.setBackground(Color.white);
        tf3.setBorder(BorderFactory.createEmptyBorder());
        tf3.setFont(new Font("arial",Font.PLAIN,18));
        tf3.setForeground(Color.gray);
        down.add(tf3);
        
        JLabel totalamount=new JLabel("Total Due Amt");
        totalamount.setBounds(950,5,200,15);
        totalamount.setFont(new Font("arial",Font.PLAIN,12));
        totalamount.setForeground(Color.WHITE);
        down.add(totalamount);
        
        tf4=new JTextField();
        tf4.setBounds(800,25,300,30);
        tf4.setHorizontalAlignment(JTextField.CENTER);
        tf4.setBackground(Color.white);
        tf4.setBorder(BorderFactory.createEmptyBorder());
        tf4.setFont(new Font("arial",Font.PLAIN,18));
        tf4.setForeground(Color.gray);
       down.add(tf4);
        
        JPanel tab=new JPanel();
        tab.setBounds(10,220,1545,590);
        tab.setLayout(new GridLayout(1,1));
        tab.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        tab.setBackground(new Color(0,0,0,0));
        b1.add(tab);
        
        JPanel ptable = new JPanel();
        ptable.setBounds(10, 250, 1545, 560);
        ptable.setLayout(new GridLayout(1, 1));
        ptable.setBackground(Color.white);
        b1.add(ptable);

        t1 = new JTable();
        tab.add(t1);

        JScrollPane jsp = new JScrollPane(t1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tab.add(jsp);

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            PreparedStatement pst = null;
            ResultSet rs = null;
            String str = "select * from sales";
            pst = DBConnection.prepareStatement(str);
            rs = pst.executeQuery();
            t1.setModel(DbUtils.resultSetToTableModel(rs));
            pst.close();
            DBConnection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

         DefaultListCellRenderer lr5 = new DefaultListCellRenderer();
        lr5.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

        
        c1=new JComboBox();
        c1.addItem("Select Customer Name");
        c1.setBounds(450,80,300,35);
        c1.setBackground(Color.white);
        c1.setForeground(Color.black);
        c1.setRenderer(lr5);
        c1.setFont(new Font("arial",Font.PLAIN,21));
        b1.add(c1);
        
        try {
            //call out dbConnector method from Entity class
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            String query = "select distinct name from customer order by name";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //shows topic data in combobox
                c1.addItem(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
          DefaultListCellRenderer lr6 = new DefaultListCellRenderer();
        lr6.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        
        JComboBox c2=new JComboBox();
        c2.addItem("Select Payment Type");
        c2.addItem("Cash");
        c2.addItem("Cheque");
        c2.addItem("NEFT");
        c2.setBounds(780,80,250,35);
        c2.setBackground(Color.white);
        c2.setForeground(Color.black);
        c2.setRenderer(lr6);
        c2.setFont(new Font("arial",Font.PLAIN,21));
        b1.add(c2);
        
        
        
        
        tp.add("Invoice-Details",panel1);
        
        setLayout(null);
        setBounds(320, 190, 1590, 880);
        getContentPane().setBackground(Color.white);
        setUndecorated(true);
        
    }
    
    public static void main(String [] args){
        new invoice().setVisible(true);
    }
    
        }
