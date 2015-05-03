package Sistem_monitoring_mutasi_ri;

import Class.Structure;
import Class.YuliInterface;
import Class.koneksi;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmPendaftaran extends javax.swing.JInternalFrame implements YuliInterface {

    private String dataku = null;
    private ResultSet res;
    DefaultTableModel tbl = new DefaultTableModel();
    JDesktopPane DP;
    PreparedStatement preparestatement;
    ResultSet resultset;
    Structure query = new Structure();
    DefaultComboBoxModel combo1 = new DefaultComboBoxModel();
    DefaultComboBoxModel combo2 = new DefaultComboBoxModel();
    DefaultComboBoxModel combo3 = new DefaultComboBoxModel();
    DefaultComboBoxModel combo4 = new DefaultComboBoxModel();
    DefaultComboBoxModel combo5 = new DefaultComboBoxModel();
    DefaultComboBoxModel combo6 = new DefaultComboBoxModel();
    String Table;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date date;
    int status = 0;

    public frmPendaftaran(JDesktopPane DP) {
        initComponents();
        this.DP = DP;
        GenerateId();
        DataDokter();
        DataTipeKamar();
        DataKelasKamar();
        DataUser();
        DataDiagnosa();
        DataTempatTidur();
        //jComboBox1.setEnabled(false);
    }

    private void DataDokter() {
        try {

            preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.MasterDokter());
            resultset = preparestatement.executeQuery();
            while (resultset.next()) {
                combo1.addElement(resultset.getString(2) + " - " + resultset.getString(1));
            }
            jComboBox2.setModel(combo1);
        } catch (Exception e) {
        }
    }

    private void DataDiagnosa() {
        try {

            preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.MasterDiagnosa());
            resultset = preparestatement.executeQuery();
            while (resultset.next()) {
                combo5.addElement(resultset.getString(2) + " - " + resultset.getString(3));
            }
            jComboBox1.setModel(combo5);
        } catch (Exception e) {
        }
    }

    private void DataTipeKamar() {
        try {

            preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.MasterTipeKamar());
            resultset = preparestatement.executeQuery();
            while (resultset.next()) {
                combo2.addElement(resultset.getString(2) + " - " + resultset.getString(1));
            }
            jComboBox4.setModel(combo2);
        } catch (Exception e) {
        }
    }
    
    private void DataHargaKamar() {
        try {
            String pre_f = (String) jComboBox4.getSelectedItem();
            String[] pra_f = pre_f.split(" - ");
            String primary = pra_f[1];
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.MasterHargaKamar());
            preparestatement.setString(1, primary);
            resultset = preparestatement.executeQuery();
            while (resultset.next()) {
                jTextField4.setText(resultset.getString(3));
            }
        } catch (Exception e) {
        }
    }
    
    private void DataTempatTidur() {
        try {
            combo6.removeAllElements();
            String pre_f = (String) jComboBox4.getSelectedItem();
            String[] pra_f = pre_f.split(" - ");
            String primary = pra_f[1];
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.MasterTempatTidur());
            preparestatement.setString(1, primary);
            resultset = preparestatement.executeQuery();
            while (resultset.next()) {
                combo6.addElement(resultset.getString(2) + " - " + resultset.getString(1));
            }
            jComboBox5.setModel(combo6);
        } catch (Exception e) {
        }
    }

    private void DataKelasKamar() {
        try {
            combo3.removeAllElements();
            String pre_f = (String) jComboBox4.getSelectedItem();
            String[] pra_f = pre_f.split(" - ");
            String primary = pra_f[1];
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.MasterKelasKamar());
            preparestatement.setString(1, primary);
            resultset = preparestatement.executeQuery();
            while (resultset.next()) {
                combo3.addElement(resultset.getString(2) + " - " + resultset.getString(1));
            }
            jComboBox1.setModel(combo3);
        } catch (Exception e) {
        }
    }
    

    private void DataHargaKelasKamar() {
        try {
            String pre_f = (String) jComboBox1.getSelectedItem();
            String[] pra_f = pre_f.split(" - ");
            String primary = pra_f[1];
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.MasterHargaKelasKamar());
            preparestatement.setString(1, primary);
            resultset = preparestatement.executeQuery();
            while (resultset.next()) {
                jTextField4.setText(resultset.getString(3));
            }
        } catch (Exception e) {
        }
    }


    private void DataUser() {
        try {
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.MasterUser());
            resultset = preparestatement.executeQuery();
            while (resultset.next()) {
                combo4.addElement(resultset.getString(5) + " - " + resultset.getString(1));
            }
            jComboBox3.setModel(combo4);
        } catch (Exception e) {
        }
    }

    private void GenerateId() {
        try {
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareStatement(query.Generate());
            resultset = preparestatement.executeQuery();
            int y = 0;
            while (resultset.next()) {
                String x = resultset.getString(2);
                y = Integer.parseInt(x) + 1;
                jTextField1.setText(y + "");
            }
            String a = jTextField1.getText();
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareCall("call generateid(1, ?)");
            preparestatement.setInt(1, y);
            preparestatement.executeQuery();
        } catch (Exception e) {
        }
    }

    private void Check() {
        status = 0;
        if ("".equals(jTextField2.getText())) {
            status = status + 1;
        } else if ("".equals(jTextField3.getText())) {
            status = status + 1;
        }
    }

    private void Save() {
        String a = jTextField1.getText();
        String b = jTextField2.getText();
        String c = jTextField3.getText();
        String d = format.format(jDateChooser1.getDate());
        String pre_e = (String) jComboBox2.getSelectedItem();
        String[] pra_e = pre_e.split(" - ");
        String e = pra_e[1];
        String pre_f = (String) jComboBox4.getSelectedItem();
        String[] pra_f = pre_f.split(" - ");
        String f = pra_f[1];
        String pre_g = (String) jComboBox1.getSelectedItem();
        String[] pra_g = pre_g.split(" - ");
        String g = pra_g[0];
        String pre_j = (String) jComboBox3.getSelectedItem();
        String[] pra_j = pre_j.split(" - ");
        String j = pra_j[1];
        String k = jTextField4.getText();
        String pre_l = (String) jComboBox5.getSelectedItem();
        String[] pra_l = pre_l.split(" - ");
        String l = pra_l[1];
        System.out.println(l);
        try {
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareCall("call tambahregpasien(?, ?, ?, ?, ?)");
            preparestatement.setString(1, a);
            preparestatement.setString(2, b);
            preparestatement.setString(3, d);
            preparestatement.setString(4, e);
            preparestatement.setString(5, j);
            preparestatement.executeQuery();
        } catch (Exception ex) {
        }
        try {
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareCall("call tambahtrxkamar(?, ?, ?, ?)");
            preparestatement.setString(1, a);
            preparestatement.setString(2, f);
            preparestatement.setString(3, j);
            preparestatement.setString(4, k);
            preparestatement.executeQuery();
        } catch (Exception ex) {
        }
        try {
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareCall("call tambahdiagnosa( ?, ?, ?)");
            preparestatement.setString(1, a);
            preparestatement.setString(2, g);
            preparestatement.setString(3, j);
            preparestatement.executeQuery();
        } catch (Exception ex) {
        }
        try {
            preparestatement = (PreparedStatement) koneksi.getConnection().prepareCall("call aturtempattidur(?)");
            preparestatement.setString(1, l);
            preparestatement.executeQuery();
            JOptionPane.showMessageDialog(rootPane, "Registrasi berhasil");
        } catch (Exception ex) {
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane2 = new javax.swing.JLayeredPane();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Registrasi Pasien Rawat Inap");

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pendaftaran"));
        jTextField1.setBounds(140, 30, 250, 30);
        jLayeredPane2.add(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText("Registrasi #");
        jLabel3.setBounds(10, 30, 80, 14);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jTextField2.setBounds(140, 70, 250, 30);
        jLayeredPane2.add(jTextField2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setText("No. Medical Record");
        jLabel4.setBounds(10, 70, 110, 14);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setText("Nama Pasien");
        jLabel5.setBounds(10, 110, 100, 14);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setText("Tanggal Lahir");
        jLabel6.setBounds(10, 150, 120, 14);
        jLayeredPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setText("Dokter");
        jLabel7.setBounds(10, 190, 90, 14);
        jLayeredPane2.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel8.setText("Tipe Kamar");
        jLabel8.setBounds(10, 270, 80, 14);
        jLayeredPane2.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jTextField3.setBounds(140, 110, 250, 30);
        jLayeredPane2.add(jTextField3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel9.setText("Diagnosa");
        jLabel9.setBounds(10, 230, 90, 14);
        jLayeredPane2.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setBounds(140, 190, 250, 30);
        jLayeredPane2.add(jComboBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBounds(140, 230, 250, 30);
        jLayeredPane2.add(jComboBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDateChooser1.setBounds(140, 150, 250, 30);
        jLayeredPane2.add(jDateChooser1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        jComboBox4.setBounds(140, 270, 250, 30);
        jLayeredPane2.add(jComboBox4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setText("Harga");
        jLabel1.setBounds(10, 310, 50, 20);
        jLayeredPane2.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField4.setText("------");
        jTextField4.setBounds(140, 310, 250, 30);
        jLayeredPane2.add(jTextField4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.setBounds(140, 350, 250, 30);
        jLayeredPane2.add(jComboBox5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setText("Tempat Tidur");
        jLabel2.setBounds(10, 360, 100, 14);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel12.setText("Penanggungjawab");
        jLabel12.setBounds(10, 400, 110, 14);
        jLayeredPane2.add(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setBounds(140, 390, 250, 30);
        jLayeredPane2.add(jComboBox3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLayeredPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        jButton7.setText("Monitoring Kamar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.setBounds(20, 70, 140, 23);
        jLayeredPane3.add(jButton7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cari.png"))); // NOI18N
        jButton8.setText("Cari Medrec");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jButton8.setBounds(20, 30, 140, 25);
        jLayeredPane3.add(jButton8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simpan.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLayeredPane2.getAccessibleContext().setAccessibleName("Data Registrasi");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        frmCariMedrec page = new frmCariMedrec(DP,"",0);
        //DP.removeAll();
        //DP.repaint();
        page.setInterface(this);
        DP.add(page);
        page.show();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int show = JOptionPane.showConfirmDialog(rootPane, "Lanjutkan penyimpanan ?", "Warning!", JOptionPane.YES_NO_OPTION);
        if (show == JOptionPane.YES_OPTION) {
            Check();
            if (status == 0) {
                Save();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan cari data medrec dulu!");
            }
        } else {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        //DataKelasKamar();
        //jComboBox1.setEnabled(true);
        DataHargaKamar();
        DataTempatTidur();
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        frmMonitoringKamar page = new frmMonitoringKamar(DP);
        //DP.removeAll();
        DP.repaint();
        DP.add(page);
        page.show();
    }//GEN-LAST:event_jButton7ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

    @Override
    public void DataReceiver(String a, String b, String c) {
        jTextField2.setText(a);
        jTextField3.setText(b);
        try {
            jDateChooser1.setDate(date = format.parse(c));
        } catch (Exception e) {
        }
    }
}
