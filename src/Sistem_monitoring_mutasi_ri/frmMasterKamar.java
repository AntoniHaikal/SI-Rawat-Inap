/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistem_monitoring_mutasi_ri;

import Class.Structure;
import Class.TableViews;
import Class.koneksi;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yulianakusumawati
 */
public class frmMasterKamar extends javax.swing.JInternalFrame {

    DefaultTableModel TableModels = new DefaultTableModel();
    TableViews TableViews = new TableViews();
    JDesktopPane DP;
    PreparedStatement preparestatement;
    ResultSet resultset;
    Structure query = new Structure();
    //String id;

    public frmMasterKamar(JDesktopPane DP) {
        initComponents();
        this.DP = DP;
        Click();
        SettingTableModel();
        Data();
        jPanel2.setVisible(false);
    }

    private void ClearTable() {
        TableModels.getDataVector().removeAllElements();
        TableModels.fireTableDataChanged();
        jTable1.repaint();
        jTable1.removeAll();
    }

    private void SettingTableModel() {
        if (jCheckBox2.isSelected()) {
            jCheckBox3.setVisible(true);
            jCheckBox4.setVisible(true);
            TableModels = TableViews.getDefaultTableModel(new String[]{"No", "Kamar ID", "Nama Kamar", "Harga", "Aksi", ""}, null, new int[]{2, 3, 4}, null);
            jTable1.setModel(TableModels);
            TableViews.table(jTable1, new int[]{50, 150, 100, 100, 50, 0});
            Data();
        } else {
            jCheckBox3.setVisible(false);
            jCheckBox4.setVisible(false);
            TableModels = TableViews.getDefaultTableModel(new String[]{"No", "Kamar ID", "Nama Kamar", "Harga", "Aksi", ""}, null, null, null);
            jTable1.setModel(TableModels);
            TableViews.table(jTable1, new int[]{50, 150, 100, 100, 50, 0});
            //Click();
            Data();
        }
    }

    private void SettingCheckBoxModel(String identifier) {
        jTable1.getColumn(identifier).setCellRenderer(new TableRenderer());
        jTable1.getColumn(identifier).setCellEditor(new CheckBoxEditor(new JCheckBox()));
    }
    
    private void Click() {
        jTable1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    String a = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 1);
                    String b = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 2);
                    frmListTempatTidur page = new frmListTempatTidur(DP, a, b);
                    DP.repaint();
                    DP.add(page);
                    page.show();
                }
            }
        });
    }

    private void Data() {
        try {
                ClearTable();

                String parameter = jTextField1.getText();

                preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.Kamar());
                preparestatement.setString(1, "%" + parameter + "%");
                preparestatement.setString(2, "%" + parameter + "%");
                resultset = preparestatement.executeQuery();
                int n = 0;
                SettingCheckBoxModel("Aksi");
                while (resultset.next()) {
                    n++;
                    Object[] object = new Object[jTable1.getColumnCount()];
                    object[0] = n;
                    object[1] = resultset.getString(1);
                    object[2] = resultset.getString(2);
                    object[3] = resultset.getString(3);
                    object[4] = new JCheckBox();
                    TableModels.addRow(object);

                    Thread.sleep(10);
                }

            } catch (Exception e) {
            }
    }


    private void SetupForm() {
        if (jCheckBox1.isSelected()) {
            jPanel2.setVisible(true);
        } else {
            jPanel2.setVisible(false);
        }
    }

    private void Save() {
        String a = jTextField2.getText();
        String b = jTextField3.getText();
        String c = jTextField4.getText();
        try {
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareCall("call tambahkamar(?, ?, ?)");
            preparestatement.setString(1, a);
            preparestatement.setString(2, b);
            preparestatement.setString(3, c);
            preparestatement.executeQuery();
            JOptionPane.showMessageDialog(rootPane, "Tambah kamar berhasil");
        } catch (Exception ex) {
        }
    }

    private void Update() {
        int x = 0;
        int y = jTable1.getRowCount();
        for (x = 0; x < y; x++) {
     
            String a = (String) jTable1.getValueAt(x, 1);
            String b = (String) jTable1.getValueAt(x, 2);
            String c = (String) jTable1.getValueAt(x, 3);
            try {
                preparestatement = (PreparedStatement) koneksi.getConnection().prepareCall("call updatekamar(?, ?, ?)");
                preparestatement.setString(1, a);
                preparestatement.setString(2, b);
                preparestatement.setString(3, c);
                preparestatement.executeQuery();
                System.out.println("update " + x);
            } catch (Exception ex) {
            }
        }
        JOptionPane.showMessageDialog(rootPane, "Update kamar berhasil");
        jCheckBox3.setSelected(false);
        Data();
    }

    private void Delete() {
        String Primary = "";
        JCheckBox CheckBox;
        int x = 0;
        for (x = 0; x < jTable1.getRowCount(); x++) {
            Primary = (String) jTable1.getValueAt(x, 1);
            CheckBox = (JCheckBox) jTable1.getValueAt(x, 4);
            if (CheckBox.isSelected()) {
                try {
                    preparestatement = (PreparedStatement) koneksi.getConnection().prepareCall("call hapuskamar(?)");
                    preparestatement.setString(1, Primary);
                    preparestatement.executeQuery();
                } catch (Exception e) {
                }
            } else {
            }
        }
        JOptionPane.showMessageDialog(rootPane, "Hapus kamar berhasil");
        jCheckBox4.setSelected(false);
        Data();
    }

    private void GenerateId() {
        try {
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.Generate());
            resultset = preparestatement.executeQuery();
            int y = 0;
            while (resultset.next()) {
                String x = resultset.getString(2);
                y = Integer.parseInt(x) + 1;
                jTextField2.setText("KA" + Integer.toString(y));
                //id = Integer.toString(y);
            }
            //String a = jTextField1.getText();
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareCall("call generateid(1, ?)");
            preparestatement.setInt(1, y);
            preparestatement.executeQuery();
        } catch (Exception e) {
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setClosable(true);
        setResizable(true);
        setTitle("Master Kamar");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tambah Kamar"));
        jPanel2.setMaximumSize(new java.awt.Dimension(360, 215));
        jPanel2.setMinimumSize(new java.awt.Dimension(360, 215));

        jLabel1.setText("Kode Kamar");

        jLabel2.setText("Nama Kamar");

        jTextField2.setEnabled(false);

        jLabel3.setText("Harga Kamar");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simpan.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(22, 22, 22)
                        .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(jTextField2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(24, 24, 24)
                        .addComponent(jTextField3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel5.setText("Pencarian");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cari.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Tambah Kamar");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jCheckBox2.setText("Mode Editor");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setForeground(new java.awt.Color(255, 0, 0));
        jCheckBox3.setText("Ubah");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox4.setForeground(new java.awt.Color(255, 51, 0));
        jCheckBox4.setText("Hapus");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox2)
                        .addGap(24, 24, 24)
                        .addComponent(jCheckBox3)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox4)
                        .addGap(0, 485, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox4))
                .addGap(0, 50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        Data();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Data();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        SetupForm();
        GenerateId();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Save();
        GenerateId();
        Data();
        jTextField3.setText("");
        jTextField4.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        SettingTableModel();
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        Update();
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        Delete();
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
