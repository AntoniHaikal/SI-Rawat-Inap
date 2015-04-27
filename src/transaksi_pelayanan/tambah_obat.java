/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi_pelayanan;

import Class.TableViews;
import Class.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class tambah_obat extends javax.swing.JPanel {

    private ResultSet res;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    DefaultComboBoxModel combo = new DefaultComboBoxModel();
    String obat_id;
    String regid;
    private String awal;
    private String akhir;
    String generate_trxobatid;
    
    public tambah_obat(String regid) {
        initComponents();
        this.regid = regid;
        
        SettingTableCariMasterobt();
    }

   private void SettingTableCariMasterobt() {
        TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "ID Obat", 
                    "Nama Obat", 
                    "Harga Obat" 
                    },
                null, new int[]{}, null);
        tblcarimasterobat.setModel(TableModels);
        TableViews.table(tblcarimasterobat, new int[]{150, 150, 100});
        
        TampilCariMasterobat();
    }
    
    private void TampilCariMasterobat(){
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM master_obat";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();            
            //res = statement.executeQuery("select * from mahasiswa");
            while (res.next()) {
                TableModels.addRow(new Object[]{
                            res.getString("obat_id"),
                            res.getString("namaobat"),
                            res.getString("defaulharga")
                        });
                tblcarimasterobat.setModel(TableModels); 
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void carimasterobat(){
        TableModels.getDataVector().removeAllElements();
           TableModels = TableViews.getDefaultTableModel
                (new String[]{
                    "ID Obat", 
                    "Nama Obat", 
                    "Harga Obat" 
                    },
                null, new int[]{}, null);
        tblcarimasterobat.setModel(TableModels);
        TableViews.table(tblcarimasterobat, new int[]{150, 150, 100});
        
               try{
                    String query = "SELECT * FROM master_obat where obat_id like ? "
                            + "AND namaobat like ? "
                            + "AND defaulharga like ?";
                    PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                    statement.setString(1, "%" +txtidcariobt.getText()+ "%");
                    statement.setString(2, "%" +txtnamacariobt.getText()+ "%");
                    statement.setString(3, "%" +txthargacariobt.getText()+ "%");
                    ResultSet res = statement.executeQuery();
                    int baris = 0;
                    while (res.next()) {
                        baris = res.getRow();
                        TableModels.addRow(new Object[]{
                                    res.getString(1),
                                    res.getString(2),
                                    res.getString(3)

                                });
                        tblcarimasterobat.setModel(TableModels); 
                    }
                    if (baris == 0) {
                            JOptionPane.showMessageDialog(null, "Hasil Pencarian = " + baris);
                        }else{
                        JOptionPane.showMessageDialog(null, "Hasil Pencarian = " + baris);
                    }
                        statement.close();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            
      }
    
      private void ambilcarimasterobat(){
            int row = tblcarimasterobat.getSelectedRow();
            int col = tblcarimasterobat.getSelectedColumn();
            String dataterpilih = tblcarimasterobat.getValueAt(row, col).toString();
            String idobat = tblcarimasterobat.getValueAt(row, 0).toString();
            String namaobat = tblcarimasterobat.getValueAt(row, 1).toString();
            String hargaobat = tblcarimasterobat.getValueAt(row, 2).toString();
           
           obat_id = idobat;
           txtobat.setText(namaobat);
           txthargaobt.setText(hargaobat);
           
          
     }
    
      private void inserttransaksiobat(){
        try {
            generate();
            TableModels.getDataVector().removeAllElements();           
            String query = "insert into trx_obat values(?,?,?,?,?,1,now())";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, generate_trxobatid);
            statement.setString(2, regid);
            statement.setString(3, obat_id);
            statement.setString(4, txtsatuanobt.getText());
            statement.setString(5, txttotalobt.getText());
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            
            statement.close();           
           JOptionPane.showMessageDialog(null, "Data Berhasil Masuk...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dimasukan : " + e);
        }
        
     }
    
       private void generate(){
         DateFormat hour = new SimpleDateFormat("HHmmssddMMyyyy");
         Date date = new Date();
         String now = hour.format(date);
         generate_trxobatid = ("trxobat" + now); 
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txthargaobt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtsatuanobt = new javax.swing.JTextField();
        txttotalobt = new javax.swing.JTextField();
        btnsimpanobat = new javax.swing.JButton();
        btnbersihobt = new javax.swing.JButton();
        txtobat = new javax.swing.JTextField();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcarimasterobat = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtidcariobt = new javax.swing.JTextField();
        txtnamacariobt = new javax.swing.JTextField();
        txthargacariobt = new javax.swing.JTextField();
        btncariobt = new javax.swing.JButton();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Masukan Transaksi Obat");
        jLabel1.setBounds(110, 10, 160, 14);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data"));

        jLabel3.setText("Obat");
        jLabel3.setBounds(20, 20, 110, 30);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("Harga");
        jLabel4.setBounds(20, 70, 100, 30);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txthargaobt.setBounds(140, 70, 170, 30);
        jLayeredPane2.add(txthargaobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("Satuan");
        jLabel5.setBounds(20, 120, 60, 30);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setText("Total Harga");
        jLabel7.setBounds(20, 170, 70, 30);
        jLayeredPane2.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtsatuanobt.setBounds(140, 120, 110, 30);
        jLayeredPane2.add(txtsatuanobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txttotalobt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttotalobtMouseClicked(evt);
            }
        });
        txttotalobt.setBounds(140, 170, 170, 30);
        jLayeredPane2.add(txttotalobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnsimpanobat.setText("Simpan");
        btnsimpanobat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanobatActionPerformed(evt);
            }
        });
        btnsimpanobat.setBounds(20, 220, 90, 23);
        jLayeredPane2.add(btnsimpanobat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btnbersihobt.setText("Bersihkan");
        btnbersihobt.setBounds(243, 220, 100, 23);
        jLayeredPane2.add(btnbersihobt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtobat.setBounds(140, 20, 170, 30);
        jLayeredPane2.add(txtobat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Obat"));

        tblcarimasterobat.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcarimasterobat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcarimasterobatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblcarimasterobat);

        jScrollPane1.setBounds(10, 120, 400, 180);
        jLayeredPane3.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText("ID Obat");
        jLabel2.setBounds(20, 30, 70, 14);
        jLayeredPane3.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText("Nama Obat");
        jLabel6.setBounds(20, 70, 90, 30);
        jLayeredPane3.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel8.setText("Harga Obat");
        jLabel8.setBounds(220, 30, 90, 30);
        jLayeredPane3.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtidcariobt.setBounds(120, 30, 90, 30);
        jLayeredPane3.add(txtidcariobt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        txtnamacariobt.setBounds(120, 70, 90, 30);
        jLayeredPane3.add(txtnamacariobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        txthargacariobt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargacariobtActionPerformed(evt);
            }
        });
        txthargacariobt.setBounds(320, 30, 90, 30);
        jLayeredPane3.add(txthargacariobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        btncariobt.setText("Cari");
        btncariobt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariobtActionPerformed(evt);
            }
        });
        btncariobt.setBounds(310, 70, 100, 30);
        jLayeredPane3.add(btncariobt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addComponent(jLayeredPane2))
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txttotalobtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttotalobtMouseClicked
        int total = (Integer.parseInt(txthargaobt.getText()) * (Integer.parseInt(txtsatuanobt.getText())));
        txttotalobt.setText(Integer.toString(total));
    }//GEN-LAST:event_txttotalobtMouseClicked

    private void btnsimpanobatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanobatActionPerformed
        inserttransaksiobat();
    }//GEN-LAST:event_btnsimpanobatActionPerformed

    private void tblcarimasterobatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcarimasterobatMouseClicked
        ambilcarimasterobat();
    }//GEN-LAST:event_tblcarimasterobatMouseClicked

    private void txthargacariobtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargacariobtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargacariobtActionPerformed

    private void btncariobtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariobtActionPerformed
        carimasterobat();
    }//GEN-LAST:event_btncariobtActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbersihobt;
    private javax.swing.JButton btncariobt;
    private javax.swing.JButton btnsimpanobat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblcarimasterobat;
    private javax.swing.JTextField txthargacariobt;
    private javax.swing.JTextField txthargaobt;
    private javax.swing.JTextField txtidcariobt;
    private javax.swing.JTextField txtnamacariobt;
    private static javax.swing.JTextField txtobat;
    private javax.swing.JTextField txtsatuanobt;
    private javax.swing.JTextField txttotalobt;
    // End of variables declaration//GEN-END:variables
}
