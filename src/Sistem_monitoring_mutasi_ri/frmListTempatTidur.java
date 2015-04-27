/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistem_monitoring_mutasi_ri;

import Class.Structure;
import Class.TableViews;
import Class.koneksi;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yulianakusumawati
 */
public class frmListTempatTidur extends javax.swing.JInternalFrame {

    DefaultTableModel TableModels = new DefaultTableModel();
    TableViews TableViews = new TableViews();
    JDesktopPane DP;
    PreparedStatement preparestatement;
    ResultSet resultset;
    Structure query = new Structure();
    String parameter;

    /**
     * Creates new form frmListTempatTidur
     */
    public frmListTempatTidur(JDesktopPane DP, String a, String b) {
        initComponents();
        SettingTableModel();
        Data();
        this.DP = DP;
        this.parameter = a;
        this.setTitle(b);
    }

    private void ClearTable() {
        TableModels.getDataVector().removeAllElements();
        jTable1.repaint();
    }

    private void SettingTableModel() {
        TableModels = TableViews.getDefaultTableModel(new String[]{"No", "Nomor Tempat Tidur", "Status", ""}, null, new int[]{1,2}, null);
        jTable1.setModel(TableModels);
        TableViews.table(jTable1, new int[]{50, 100, 100, 0});
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

                preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.TempatTidur());
                preparestatement.setString(1, parameter);
                resultset = preparestatement.executeQuery();
                int n = 0;
                while (resultset.next()) {
                    n++;
                    Object[] object = new Object[jTable1.getColumnCount()];
                    object[0] = n;
                    object[1] = resultset.getString(1);
                    object[2] = resultset.getString(3);
                    TableModels.addRow(object);

                    Thread.sleep(10);
                }

            } catch (Exception e) {
            }
        }
    }

    private void TambahKolom() {
        
        Object[] object = new Object[jTable1.getColumnCount()];
        object[0] = jTable1.getRowCount() + 1;
        object[1] = "";
        object[2] = "";
        TableModels.addRow(object);
    }
    
    private void Save() {
        String a = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 1);
        String b = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 2);
        String c = parameter;
        try {
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareCall("call tambahtempattidur(?, ?, ?)");
            preparestatement.setString(1, a);
            preparestatement.setString(2, c);
            preparestatement.setString(3, b);
            preparestatement.executeQuery();
            System.out.println(a + " - " + b + " - " + c);
            JOptionPane.showMessageDialog(rootPane, "Tambah kamar berhasil");
        } catch (Exception ex) {
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
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setClosable(true);

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

        jMenu1.setText("Opsional");

        jMenuItem1.setText("Tambah");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Simpan");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        TambahKolom();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Save();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
