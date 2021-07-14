
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import net.proteanit.sql.DbUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

class Form extends JFrame {
    JTable table;
    ResultSet rs;

    Form() {
        final Vector columnNames = new Vector();
        final Vector data = new Vector();
        JButton button = new JButton("Export");
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        try {
           
           Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
            PreparedStatement pst=null;
                    String query="Select * from customer";
                    ResultSet rs=null;
                    pst=DBConnection.prepareStatement(query);
                    
                     rs = pst.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(md.getColumnName(i));
            }
            while (rs.next()) {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                data.addElement(row);
            }
        } catch (Exception e) {
        }
        table = new JTable(data, columnNames);
        table.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);
        panel1.add(scrollPane);
        panel2.add(button);
        panel.add(panel1);
        panel.add(panel2);
        add(panel);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection DBConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/malhotraengineers", "root", "root");
                    PreparedStatement pst=null;
                    String query="Select * from customer";
                    ResultSet rs=null;
                    pst=DBConnection.prepareStatement(query);
                    
                    rs = pst.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                    
                    
                    HSSFWorkbook wb = new HSSFWorkbook();
                    HSSFSheet sheet = wb.createSheet("Excel Sheet");
                    HSSFRow rowhead = sheet.createRow((short) 0);
                    rowhead.createCell((short) 0).setCellValue(" Id");
                    rowhead.createCell((short) 1).setCellValue(" Name");
                    rowhead.createCell((short) 2).setCellValue(" Address");
                    rowhead.createCell((short) 3).setCellValue(" Salary");
                    int index = 1;
                    while (rs.next()) {
                        HSSFRow row = sheet.createRow((short) index);
                        row.createCell((short) 0).setCellValue(rs.getInt(1));
                        row.createCell((short) 1).setCellValue(rs.getString(2));
                        row.createCell((short) 2).setCellValue(rs.getString(3));
                        row.createCell((short) 3).setCellValue(rs.getInt(4));
                        index++;
                    }
                    FileOutputStream fileOut = new FileOutputStream("c:\\Hello.xls");
                    wb.write(fileOut);
                    fileOut.close();
                    Runtime rt = Runtime.getRuntime();
                    rt.exec("cmd.exe /C start C:\\Hello.xls");
                } catch (Exception e) {
                }
            }
        });
    }
}

class JTableToExcel {

    public static void main(String arg[]) {
        try {
            Form frame = new Form();
            frame.setSize(450, 200);
            frame.setVisible(true);
        } catch (Exception e) {
        }
    }
}
