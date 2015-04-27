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
import javax.swing.JDesktopPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yulianakusumawati
 */
public class frmMonitoringKamar extends javax.swing.JInternalFrame {

    private String dataku = null;
    DefaultTableModel TableModels = new DefaultTableModel();
    TableViews TableViews = new TableViews();
    PreparedStatement preparestatement;
    ResultSet resultset;
    JDesktopPane DP;
    Structure query = new Structure();
    
    public frmMonitoringKamar(JDesktopPane DP) {
        initComponents();
        Data();
        SettingTableModel();
        Click();
        this.DP = DP;
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
    
    private void ClearTable() {
        TableModels.getDataVector().removeAllElements();
        jTable1.repaint();
    }
    
    private void SettingTableModel() {
        TableModels = TableViews.getDefaultTableModel(new String[]{"No", "Kamar ID", "Nama Kamar", "Harga", "Tempat Tidur", ""}, null, null, null);
        jTable1.setModel(TableModels);
        TableViews.table(jTable1, new int[]{50, 100, 150, 150, 100, 0});
    }
    
    private void Data() {
        Thread thread = new Thread(new RunData());
        thread.start();
    }
    
    private class RunData implements Runnable {

        @Override
        public void run() {
            try {
                ClearTable();

                int parameters = jComboBox1.getSelectedIndex();
                String parameter = Integer.toString(parameters);

                preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.CekKamar());
                preparestatement.setString(1, parameter);
                resultset = preparestatement.executeQuery();
                int n = 0;
                while (resultset.next()) {
                    n++;
                    Object[] object = new Object[jTable1.getColumnCount()];
                    object[0] = n;
                    object[1] = resultset.getString(1);
                    object[2] = resultset.getString(2);
                    object[3] = resultset.getString(3);
                    object[4] = resultset.getString(4);
                    TableModels.addRow(object);

                    Thread.sleep(10);
                }

            } catch (Exception e) {
            }
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

        jTextField1 = new javax.swing.JTextField();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Monitoring Kamar");

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Kamar"));

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

        jScrollPane1.setBounds(10, 80, 620, 310);
        jLayeredPane2.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText("Pencarian");
        jLabel2.setBounds(20, 40, 70, 14);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tersedia", "Tidak Tersedia" }));
        jComboBox1.setBounds(100, 30, 180, 30);
        jLayeredPane2.add(jComboBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cari.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(300, 30, 70, 30);
        jLayeredPane2.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Data();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}