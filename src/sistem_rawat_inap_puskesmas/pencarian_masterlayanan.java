/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem_rawat_inap_puskesmas;

import Class.TableViews;
import Class.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class pencarian_masterlayanan extends javax.swing.JInternalFrame {

    JDesktopPane DP;
    private String dataku = null;
    private ResultSet res;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    String nol;
    
    public pencarian_masterlayanan(JDesktopPane DP) {
        initComponents();
        SettingTableModel();
        this.DP = DP;
        clear();
    }
    
     private void SettingTableModel() {
        TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "ID_Layanan", 
                    "Nama_Layanan", 
                    "Harga" 
                    },
                null, new int[]{}, null);
        tblcarimasterlayanan.setModel(TableModels);
        TableViews.table(tblcarimasterlayanan, new int[]{200, 200, 250});
        
        tampil();

    }
     
     private void tampil(){
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM master_layanan";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();            
            //res = statement.executeQuery("select * from mahasiswa");
            while (res.next()) {
                TableModels.addRow(new Object[]{
                            res.getString("layanan_id"),
                            res.getString("namalayanan"),
                            res.getString("defaultharga")
                        });
                tblcarimasterlayanan.setModel(TableModels); 
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     private void cari(){
           TableModels.getDataVector().removeAllElements();
           TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "ID_Layanan", 
                    "Nama_Layanan", 
                    "Harga" 
                    },
                null, new int[]{}, null);
        tblcarimasterlayanan.setModel(TableModels);
        TableViews.table(tblcarimasterlayanan, new int[]{200, 200, 250});
        
               try{
                    String query = "SELECT * FROM master_layanan where layanan_id like ? "
                            + "AND namalayanan like ? "
                            + "AND defaultharga like ?";
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txtidlayanan.getText()+ "%");
                    statement.setString(2, "%" +txtnamalayanan.getText()+ "%");
                    statement.setString(3, "%" +txthargalayanan.getText()+ "%");
                    ResultSet res = statement.executeQuery();
                    int baris = 0;
                    while (res.next()) {
                        baris = res.getRow();
                        TableModels.addRow(new Object[]{
                                    res.getString(1),
                                    res.getString(2),
                                    res.getString(3)

                                });
                        tblcarimasterlayanan.setModel(TableModels); 
                    }
                    if (baris == 0) {
                            JOptionPane.showMessageDialog(rootPane, "Hasil Pencarian = " + baris);
                        }else{
                        JOptionPane.showMessageDialog(rootPane, "Hasil Pencarian = " + baris);
                    }
                        statement.close();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            
      }
     
     private void ambil(){
            int row = tblcarimasterlayanan.getSelectedRow();
            int col = tblcarimasterlayanan.getSelectedColumn();
            String dataterpilih = tblcarimasterlayanan.getValueAt(row, col).toString();
            String idlayan = tblcarimasterlayanan.getValueAt(row, 0).toString();
            
           
            frm_transaksi trans = new frm_transaksi(DP,"");
            DP.removeAll();
            DP.repaint();
            DP.add(trans);
            trans.show();
            this.dispose();
        }
     
     private void clear(){
         txthargalayanan.setText("");
         txtidlayanan.setText("");
         txtnamalayanan.setText("");
         
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        txtidlayanan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtnamalayanan = new javax.swing.JTextField();
        txthargalayanan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnbatal = new javax.swing.JButton();
        btncari1 = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcarimasterlayanan = new javax.swing.JTable();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Pencarian Layanan");
        jLabel1.setBounds(330, 20, 130, 20);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Masukan Kriteria Pencarian"));

        jLabel2.setText("Id Layanan");
        jLabel2.setBounds(10, 40, 54, 30);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtidlayanan.setBounds(80, 40, 130, 30);
        jLayeredPane2.add(txtidlayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText("Nama Layanan");
        jLabel3.setBounds(220, 40, 90, 30);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtnamalayanan.setBounds(320, 40, 130, 30);
        jLayeredPane2.add(txtnamalayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txthargalayanan.setText("jTextField3");
        txthargalayanan.setBounds(570, 40, 130, 30);
        jLayeredPane2.add(txthargalayanan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("Harga Layanan");
        jLabel4.setBounds(460, 40, 90, 30);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnbatal.setText("Batal");
        btnbatal.setBounds(570, 90, 130, 30);
        jLayeredPane2.add(btnbatal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncari1.setText("Cari");
        btncari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncari1ActionPerformed(evt);
            }
        });
        btncari1.setBounds(420, 90, 130, 30);
        jLayeredPane2.add(btncari1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Table Layanan"));

        tblcarimasterlayanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcarimasterlayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcarimasterlayananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblcarimasterlayanan);

        jScrollPane1.setBounds(22, 30, 700, 110);
        jLayeredPane3.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
                    .addComponent(jLayeredPane2)
                    .addComponent(jLayeredPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncari1ActionPerformed
       cari();
    }//GEN-LAST:event_btncari1ActionPerformed

    private void tblcarimasterlayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcarimasterlayananMouseClicked
       ambil();
    }//GEN-LAST:event_tblcarimasterlayananMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncari1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblcarimasterlayanan;
    private javax.swing.JTextField txthargalayanan;
    private javax.swing.JTextField txtidlayanan;
    private javax.swing.JTextField txtnamalayanan;
    // End of variables declaration//GEN-END:variables
}
