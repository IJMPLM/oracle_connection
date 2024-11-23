/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oracleconnection;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.JButton;
import net.proteanit.sql.DbUtils;
import java.awt.*;
/**
 *
 * @author TGG
 */


public class MainScreen extends javax.swing.JFrame {
    /**
     * Creates new form MainScreen
     */
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null; 
    public MainScreen() {
        initComponents();
        styleBackground();
        refresh();
        
        
        
        jScrollPane1.getVerticalScrollBar().setUI(new ModernScrollBarUI()); 
        jScrollPane1.getHorizontalScrollBar().setUI(new ModernScrollBarUI());
    }
    public void refresh(){
    try{
        conn = ConnectDB.Connect();
        ps = conn.prepareStatement("SELECT * FROM HR.EMPLOYEES ORDER BY LAST_NAME");
        rs = ps.executeQuery();
        tblEmployees.setModel(DbUtils.resultSetToTableModel(rs));
        styleTable();
        
        ps = conn.prepareStatement("SELECT JOB_TITLE FROM HR.JOBS ORDER BY JOB_TITLE");
        rs = ps.executeQuery();
        while(rs.next()) {
            cmbJobID.addItem(rs.getString("JOB_TITLE"));        
        }

        ps = conn.prepareStatement("SELECT DEPARTMENT_NAME FROM HR.DEPARTMENTS ORDER BY DEPARTMENT_NAME");
        rs = ps.executeQuery();
        while(rs.next()) {
            cmbDeptID.addItem(rs.getString("DEPARTMENT_NAME")); 
        
            
        ps = conn.prepareStatement("SELECT LAST_NAME || ', '||FIRST_NAME AS MANAGER_NAME FROM HR.EMPLOYEES ORDER BY LAST_NAME");
        rs = ps.executeQuery();
        while(rs.next()) {
            cmbManagerID.addItem(rs.getString("MANAGER_NAME"));        
        }
        
               
        }
        
        
        
        
    } catch(Exception e){
        System.out.println(e);
    }
}
    
    
    private void styleBackground() {
    getContentPane().setBackground(Color.BLACK);

    jLabel1.setForeground(Color.WHITE);
    jLabel2.setForeground(Color.WHITE);
    jLabel3.setForeground(Color.WHITE);
    jLabel4.setForeground(Color.WHITE);
    jLabel5.setForeground(Color.WHITE);
    jLabel6.setForeground(Color.WHITE);
    jLabel7.setForeground(Color.WHITE);
    jLabel8.setForeground(Color.WHITE);
    jLabel9.setForeground(Color.WHITE);
    jLabel10.setForeground(Color.WHITE);
    jLabel11.setForeground(Color.WHITE);

    txtEmployeeNo.setBackground(Color.DARK_GRAY);
    txtEmployeeNo.setForeground(Color.WHITE);
    txtLastName.setBackground(Color.DARK_GRAY);
    txtLastName.setForeground(Color.WHITE);
    txtEmail.setBackground(Color.DARK_GRAY);
    txtEmail.setForeground(Color.WHITE);
    txtFirstName.setBackground(Color.DARK_GRAY);
    txtFirstName.setForeground(Color.WHITE);
    txtPhoneNumber.setBackground(Color.DARK_GRAY);
    txtPhoneNumber.setForeground(Color.WHITE);
    txtHireDate.setBackground(Color.DARK_GRAY);
    txtHireDate.setForeground(Color.WHITE);
    txtSalary.setBackground(Color.DARK_GRAY);
    txtSalary.setForeground(Color.WHITE);
    txtCommissionPct.setBackground(Color.DARK_GRAY);
    txtCommissionPct.setForeground(Color.WHITE);

    cmbJobID.setBackground(Color.DARK_GRAY);
    cmbJobID.setForeground(Color.WHITE);
    cmbManagerID.setBackground(Color.DARK_GRAY);
    cmbManagerID.setForeground(Color.WHITE);
    cmbDeptID.setBackground(Color.DARK_GRAY);
    cmbDeptID.setForeground(Color.WHITE);

    btnAdd.setBackground(Color.DARK_GRAY);
    btnAdd.setForeground(Color.WHITE);
    btnUpdate.setBackground(Color.DARK_GRAY);
    btnUpdate.setForeground(Color.WHITE);
    btnDelete.setBackground(Color.DARK_GRAY);
    btnDelete.setForeground(Color.WHITE);
}


private void styleTable() {
    tblEmployees.setFillsViewportHeight(true);
    tblEmployees.setBackground(Color.BLACK);
    tblEmployees.setForeground(Color.WHITE);
    tblEmployees.setSelectionBackground(Color.BLUE);
    tblEmployees.setSelectionForeground(Color.WHITE);
    tblEmployees.setGridColor(Color.GRAY);
    tblEmployees.setRowHeight(30);

    DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
    headerRenderer.setBackground(Color.DARK_GRAY);
    headerRenderer.setForeground(Color.WHITE);

    for (int i = 0; i < tblEmployees.getModel().getColumnCount(); i++) {
        tblEmployees.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
    }

    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!isSelected) {
                c.setBackground(row % 2 == 0 ? Color.BLACK : Color.DARK_GRAY);
            }
            return c;
        }
    };

    for (int i = 0; i < tblEmployees.getModel().getColumnCount(); i++) {
        tblEmployees.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
    }
}



public class ModernScrollBarUI extends BasicScrollBarUI {
    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(50, 50, 50); // Dark gray thumb
        this.trackColor = new Color(30, 30, 30); // Darker gray track
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton jbutton = new JButton();
        jbutton.setPreferredSize(new Dimension(0, 0));
        jbutton.setMinimumSize(new Dimension(0, 0));
        jbutton.setMaximumSize(new Dimension(0, 0));
        return jbutton;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(trackColor);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(thumbColor);
        g2.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
        g2.dispose();
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployees = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEmployeeNo = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtHireDate = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSalary = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCommissionPct = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbJobID = new javax.swing.JComboBox<>();
        cmbManagerID = new javax.swing.JComboBox<>();
        cmbDeptID = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));

        tblEmployees.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblEmployees.setFocusable(false);
        tblEmployees.setRowHeight(25);
        tblEmployees.setSelectionBackground(new java.awt.Color(223, 57, 95));
        tblEmployees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmployees);

        jLabel1.setText("Employee Number:");

        jLabel2.setText("Last Name:");

        jLabel3.setText("Email Address:");

        txtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameActionPerformed(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(102, 255, 102));
        btnAdd.setText("Add");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(0, 51, 204));
        btnUpdate.setText("Update");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setText("Delete");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel4.setText("First Name:");

        txtFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstNameActionPerformed(evt);
            }
        });

        jLabel5.setText("Phone Number:");

        jLabel6.setText("Hire Date (YYYY-MM-DD):");

        txtHireDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHireDateActionPerformed(evt);
            }
        });

        jLabel7.setText("Job ID:");

        jLabel8.setText("Salary:");

        jLabel9.setText("Commission %:");

        jLabel10.setText("Manager ID:");

        jLabel11.setText("Department ID:");

        cmbJobID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJobIDActionPerformed(evt);
            }
        });

        cmbManagerID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbManagerIDActionPerformed(evt);
            }
        });

        cmbDeptID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDeptIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmployeeNo)
                            .addComponent(txtEmail)
                            .addComponent(txtFirstName)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txtLastName))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHireDate)
                            .addComponent(txtSalary)
                            .addComponent(txtCommissionPct)
                            .addComponent(cmbJobID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbManagerID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbDeptID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 426, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete)))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtEmployeeNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(cmbManagerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtHireDate)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbJobID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCommissionPct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbDeptID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(92, 92, 92))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        refresh();
    }//GEN-LAST:event_formWindowActivated

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        int respond = JOptionPane.showConfirmDialog(null,"Do you want to add?","Confirm?",JOptionPane.YES_NO_OPTION);
        if(respond == JOptionPane.YES_OPTION){
            try{
                conn = ConnectDB.Connect();
                ps = conn.prepareStatement("INSERT INTO HR.EMPLOYEES(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id) "
                                         + "VALUES('"+txtEmployeeNo.getText().trim()+"',"
                                                 + "'"+txtFirstName.getText().trim()+"',"
                                                 + "'"+txtLastName.getText().trim()+"',"
                                                 + "'"+txtEmail.getText().trim()+"',"
                                                 + "'"+txtPhoneNumber.getText().trim()+"',"
                                                 + "TO_DATE('" + txtHireDate.getText().trim().substring(0, Math.min(10, txtHireDate.getText().trim().length())) + "','YYYY-MM-DD'), "
                                                 + "'"+cmbJobID.getSelectedItem().toString() + "',"
                                                 + "'"+txtSalary.getText().trim()+"',"
                                                 + "'"+txtCommissionPct.getText().trim()+"',"
                                                 + "'"+cmbManagerID.getSelectedItem().toString() + "',"
                                                 + "'"+cmbDeptID.getSelectedItem().toString() + "')"
                                                 );
                ps.execute();
            } catch(Exception e){
                System.out.println(e);
            }
            JOptionPane.showMessageDialog(null, "Adding was Successful");
        } else
            JOptionPane.showMessageDialog(null, "Adding was Aborted!");
        refresh();
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        int respond = JOptionPane.showConfirmDialog(null, "Do you want to edit?", "Confirm?", JOptionPane.YES_NO_OPTION);
        if (respond == JOptionPane.YES_OPTION) {
            try {
            conn = ConnectDB.Connect();
            ps = conn.prepareStatement(
                "UPDATE hr.employees "
                + "SET last_name = '" + txtLastName.getText().trim() + "', "
                + "first_name = '" + txtFirstName.getText().trim() + "', "
                + "email = '" + txtEmail.getText().trim() + "', "
                + "phone_number = '" + txtPhoneNumber.getText().trim() + "', "
                + "salary = '" + txtSalary.getText().trim() + "', "
                + "commission_pct = '" + txtCommissionPct.getText().trim() + "', "
                + "hire_date = TO_DATE('" + txtHireDate.getText().trim().substring(0, Math.min(10, txtHireDate.getText().trim().length())) + "', 'YYYY-MM-DD'), "
                + "job_id = '" + cmbJobID.getSelectedItem().toString() + "', "
                + "Manager_id = '" + cmbManagerID.getSelectedItem().toString() + "', "
                + "department_id = '" + cmbDeptID.getSelectedItem().toString() + "' "
                + "WHERE employee_id = '" + txtEmployeeNo.getText().trim() + "'"
            );
            ps.execute();
            JOptionPane.showMessageDialog(null, "Editing is successful!");
            } catch (Exception e) {
            System.out.print(e);
            }
        }
        refresh(); 
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        int respond = JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Confirm?", JOptionPane.YES_NO_OPTION);
        if (respond == JOptionPane.YES_OPTION) {
            try {
                conn = ConnectDB.Connect();

                
                conn = ConnectDB.Connect();

                ps = conn.prepareStatement("DELETE FROM HR.JOB_HISTORY WHERE employee_id = '" + txtEmployeeNo.getText().trim() + "'");
                ps.execute();

                ps = conn.prepareStatement("DELETE FROM HR.EMPLOYEES WHERE employee_id = '" + txtEmployeeNo.getText().trim() + "'");
                ps.execute();


                JOptionPane.showMessageDialog(null, "Deleting was Successful!");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Deleting was Aborted!");
        }
        refresh();
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void tblEmployeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeesMouseClicked
        int row = tblEmployees.getSelectedRow();
        txtEmployeeNo.setText(tblEmployees.getModel().getValueAt(row,0).toString());
        txtFirstName.setText(tblEmployees.getModel().getValueAt(row,1)==null?" ":tblEmployees.getModel().getValueAt(row,1).toString());
        txtLastName.setText(tblEmployees.getModel().getValueAt(row,2)==null?" ":tblEmployees.getModel().getValueAt(row,2).toString());
        txtEmail.setText(tblEmployees.getModel().getValueAt(row,3)==null?" ":tblEmployees.getModel().getValueAt(row,3).toString());
        txtPhoneNumber.setText(tblEmployees.getModel().getValueAt(row,4)==null?" ":tblEmployees.getModel().getValueAt(row,4).toString());
        txtHireDate.setText(tblEmployees.getModel().getValueAt(row,5)==null?" ":tblEmployees.getModel().getValueAt(row,5).toString());
        cmbJobID.setSelectedItem(tblEmployees.getModel().getValueAt(row,6)==null?" ":tblEmployees.getModel().getValueAt(row,6).toString());
        txtSalary.setText(tblEmployees.getModel().getValueAt(row,7)==null?" ":tblEmployees.getModel().getValueAt(row,7).toString());
        txtCommissionPct.setText(tblEmployees.getModel().getValueAt(row,8)==null?" ":tblEmployees.getModel().getValueAt(row,8).toString());
        cmbManagerID.setSelectedItem(tblEmployees.getModel().getValueAt(row,9)==null?" ":tblEmployees.getModel().getValueAt(row,9).toString());
        cmbDeptID.setSelectedItem(tblEmployees.getModel().getValueAt(row,10)==null?" ":tblEmployees.getModel().getValueAt(row,10).toString());
    }//GEN-LAST:event_tblEmployeesMouseClicked

    private void cmbJobIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJobIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJobIDActionPerformed

    private void cmbManagerIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbManagerIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbManagerIDActionPerformed

    private void txtHireDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHireDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHireDateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstNameActionPerformed

    private void cmbDeptIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDeptIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDeptIDActionPerformed

    
    
    
    
    private void updateCmbJobID() {
    try {
        conn = ConnectDB.Connect();
        ps = conn.prepareStatement("SELECT job_title FROM hr.jobs");
        rs = ps.executeQuery();
        cmbJobID.removeAllItems();
        while (rs.next()) {
            cmbJobID.addItem(rs.getString("job_title")); 
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error fetching Job IDs: " + e.getMessage());
    }
}
    
    private void updateCmbManagerID() {
    try {
        conn = ConnectDB.Connect();
        ps = conn.prepareStatement("SELECT manager_id FROM hr.departments");
        rs = ps.executeQuery();
        cmbManagerID.removeAllItems();
        while (rs.next()) {
            cmbManagerID.addItem(rs.getString("manager_id")); 
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error fetching Manager IDs: " + e.getMessage());
    }
}
    
    private void updateCmbDeptID() {
    try {
        conn = ConnectDB.Connect();
        ps = conn.prepareStatement("SELECT department_id FROM hr.departments");
        rs = ps.executeQuery();
        cmbDeptID.removeAllItems();
        while (rs.next()) {
            cmbDeptID.addItem(rs.getString("department_id")); 
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error fetching Department IDs: " + e.getMessage());
    }
}

 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbDeptID;
    private javax.swing.JComboBox<String> cmbJobID;
    private javax.swing.JComboBox<String> cmbManagerID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEmployees;
    private javax.swing.JTextField txtCommissionPct;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmployeeNo;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtHireDate;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSalary;
    // End of variables declaration//GEN-END:variables
}


