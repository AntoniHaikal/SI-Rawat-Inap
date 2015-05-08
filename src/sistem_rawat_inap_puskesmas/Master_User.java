/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistem_rawat_inap_puskesmas;

import Class.TableViews;
import Class.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class Master_User extends javax.swing.JInternalFrame {

    private final String dataku = null;
    private ResultSet res;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    JDesktopPane DP;
    private int id;

    public Master_User(JDesktopPane DP) {
        initComponents();
        this.DP = DP;
        SettingTableModel();
        clear();

    }

    private void SettingTableModel() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
            "ID",
            "Username",
            "Role",
            "Nama user"
        },
                null, new int[]{}, null);
        tabellayanan.setModel(TableModels);
        TableViews.table(tabellayanan, new int[]{50, 150, 250, 250});

        tampil();

    }

    private void tampil() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM master_user";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();
            //res = statement.executeQuery("select * from mahasiswa");
            while (res.next()) {
                TableModels.addRow(new Object[]{
                    res.getString("user_id"),
                    res.getString("username"),
                    res.getString("role"),
                    res.getString("namauser")
                });
                tabellayanan.setModel(TableModels);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void insert() {
        try {
            int a = 0;
            TableModels.getDataVector().removeAllElements();
            String query = "insert into master_user (user_id, username,password,role,namauser,gambar,telepon)values(?,?,?,?,?,?,?)";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setNull(1, Types.INTEGER);
            statement.setString(2, txtusername.getText());
            statement.setString(3, "1234567");
            statement.setString(4, (String) comboRole.getSelectedItem());
            statement.setString(5, txtnama.getText());
            statement.setString(6, "gambar");
            statement.setString(7, "telpon");
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            clear();
            tampil();
            statement.close();
            JOptionPane.showMessageDialog(rootPane, "Data Berhasil Masuk...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dimasukan : " + e);
        }

    }

    private void reset() {
        if (id != 0) {
            try {
                TableModels.getDataVector().removeAllElements();
                String query = "update master_user set password =? where user_id = ?";
                PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
                statement.setString(1, "1234567");
                statement.setInt(2, id);
                statement.executeUpdate();
                TableModels.getDataVector().removeAllElements();
                clear();
                tampil();
                statement.close();
                JOptionPane.showMessageDialog(rootPane, "Data Berhasil diubah...");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal diubah : " + e);
            }
        }else {
            JOptionPane.showMessageDialog(rootPane, "Data belum di klik");
        }

    }

    private void update() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "update master_user set username =?, role=?,namauser=? where user_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, txtusername.getText());
            statement.setString(2, (String) comboRole.getSelectedItem());
            statement.setString(3, txtnama.getText());
            statement.setInt(4, id);
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            clear();
            tampil();
            statement.close();
            JOptionPane.showMessageDialog(rootPane, "Data Berhasil diubah...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah : " + e);
        }
    }

    private void delete() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "delete from master_user where user_id =?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            TableModels.getDataVector().removeAllElements();
            clear();
            tampil();
            statement.close();
            JOptionPane.showMessageDialog(rootPane, "Data Berhasil Di hapus...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus : " + e);
        }
    }

    private void cari() {
        try {
            String sql = "";
            String awal = "";
            String akhir = "";
            if (cmbcari.getSelectedIndex() == 0) {
                sql = "SELECT * FROM master_user where username like ?";
                awal = "%";
                akhir = "%";
            }
            if ("nama layanan".equals(cmbcari.getSelectedItem())) {
                sql = "SELECT * FROM master_user where role like ?";
                awal = "%";
                akhir = "%";
            }
            if ("default harga".equals(cmbcari.getSelectedItem())) {
                sql = "SELECT * FROM master_user where namauser like ?";
                awal = "%";
                akhir = "%";
            }
            String query = sql;
            String cari = txtcari.getText();
            TableModels.getDataVector().removeAllElements();
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, awal + cari + akhir);
            ResultSet res = statement.executeQuery();
            int baris = 0;
            while (res.next()) {
                baris = res.getRow();
                TableModels.addRow(new Object[]{
                    res.getString("user_id"),
                    res.getString("username"),
                    res.getString("role"),
                    res.getString("namauser")
                });
                tabellayanan.setModel(TableModels);
            }
            if (baris == 0) {
                JOptionPane.showMessageDialog(rootPane, "Hasil Pencarian = " + baris);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Hasil Pencarian = " + baris);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ambil() {
        int i = tabellayanan.getSelectedRow();
        id = Integer.parseInt((String) tabellayanan.getValueAt(i, 0));
        String sql = " Select * from master_user where user_id=?";
        try {
            PreparedStatement s = koneksi.getConnection().prepareStatement(sql);
            s.setString(1, (String) tabellayanan.getValueAt(i, 0));
            ResultSet res = s.executeQuery();
            while (res.next()) {
                txtnama.setText(res.getString("namauser"));
                txtusername.setText(res.getString("username"));
                comboRole.setSelectedItem(res.getString("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Master_User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clear() {
        txtusername.setText("");
        txtnama.setText("");
        txtcari.setText("");
        btnsimpan.setEnabled(false);
        btnedit.setEnabled(false);
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
        txtnama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comboRole = new javax.swing.JComboBox();
        btnResetPassword = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabellayanan = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        cmbcari = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Master User");
        jLayeredPane1.add(jLabel1);
        jLabel1.setBounds(270, 20, 150, 14);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data user"));

        jLabel2.setText("Nama user");

        jLabel3.setText("Username");

        jLabel6.setText("Role");

        comboRole.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2" }));

        btnResetPassword.setText("Reset Password");
        btnResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtusername)
                            .addComponent(comboRole, 0, 180, Short.MAX_VALUE)))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnResetPassword)
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnResetPassword)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jLayeredPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtnama, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtusername, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(comboRole, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnResetPassword, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        jLayeredPane3.add(btnsimpan);
        btnsimpan.setBounds(50, 20, 90, 23);

        btnedit.setText("Update");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        jLayeredPane3.add(btnedit);
        btnedit.setBounds(50, 50, 90, 23);

        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        jLayeredPane3.add(btndelete);
        btndelete.setBounds(50, 80, 90, 23);

        jButton1.setText("Batal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jLayeredPane3.add(jButton1);
        jButton1.setBounds(50, 110, 90, 23);

        jButton2.setText("Tambah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jLayeredPane3.add(jButton2);
        jButton2.setBounds(10, 140, 100, 23);

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jLayeredPane3.add(jButton3);
        jButton3.setBounds(113, 140, 90, 23);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel User"));

        tabellayanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabellayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabellayananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabellayanan);

        jLayeredPane4.add(jScrollPane1);
        jScrollPane1.setBounds(10, 80, 650, 150);

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        jLayeredPane4.add(txtcari);
        txtcari.setBounds(280, 30, 180, 30);

        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });
        jLayeredPane4.add(btncari);
        btncari.setBounds(470, 30, 73, 30);

        cmbcari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "username", "role", "namauser", " " }));
        jLayeredPane4.add(cmbcari);
        cmbcari.setBounds(110, 30, 150, 30);

        jLabel4.setText("Pencarian");
        jLayeredPane4.add(jLabel4);
        jLabel4.setBounds(20, 30, 80, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane4)
                    .addComponent(jLayeredPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane3)
                    .addComponent(jLayeredPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        insert();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        cari();
    }//GEN-LAST:event_btncariActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        cari();
    }//GEN-LAST:event_txtcariActionPerformed

    private void tabellayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabellayananMouseClicked
        ambil();
    }//GEN-LAST:event_tabellayananMouseClicked

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        update();
    }//GEN-LAST:event_btneditActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        delete();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        clear();
        btnsimpan.setEnabled(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        clear();
        btnedit.setEnabled(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPasswordActionPerformed
        // TODO add your handling code here:
        reset();
        clear();
    }//GEN-LAST:event_btnResetPasswordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResetPassword;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox cmbcari;
    private javax.swing.JComboBox comboRole;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabellayanan;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
