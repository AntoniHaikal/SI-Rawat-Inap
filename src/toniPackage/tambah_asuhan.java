/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toniPackage;

import Class.TableViews;
import Class.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class tambah_asuhan extends javax.swing.JPanel {

    private ResultSet res;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    DefaultTableModel TableModels1;
    DefaultTableModel TableModels2;
    DefaultComboBoxModel combo = new DefaultComboBoxModel();
    String petugas_id;
    String regid, nomedrec;
    private String awal;
    private String akhir;

    public tambah_asuhan(String regid, String nomedrec) {
        initComponents();
        this.regid = regid;
        this.nomedrec=nomedrec;
        SettingTableCariMasterPetugasMedis();
    }

    private void SettingTableCariMasterPetugasMedis() {
        TableModels1 = TableViews.getDefaultTableModel(new String[]{
            "ID_Petugas Medis",
            "Nama Petugas Medis",
            "Jenis Kelamin",
            "No Telp",
            "Alamat",
            "Kategori"

        },
                null, new int[]{}, null);
        tblcaripetugasmedis.setModel(TableModels1);
        TableViews.table(tblcaripetugasmedis, new int[]{150, 100, 150, 100, 100, 110});

        TampilCariMasterPetugasMedis();
    }

    private void TampilCariMasterPetugasMedis() {
        try {
            TableModels1.getDataVector().removeAllElements();
            String query = "SELECT * FROM master_petugasmedis";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                TableModels1.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6)
                });
                tblcaripetugasmedis.setModel(TableModels1);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void caripetugasmedis() {
        TableModels1.getDataVector().removeAllElements();
        TableModels1 = TableViews.getDefaultTableModel(new String[]{
            "ID_Petugas Medis",
            "Nama Petugas Medis",
            "Jenis Kelamin",
            "No Telp",
            "Alamat",
            "Kategori"

        },
                null, new int[]{}, null);
        tblcaripetugasmedis.setModel(TableModels1);
        TableViews.table(tblcaripetugasmedis, new int[]{150, 100, 150, 100, 100, 110});

        try {
            String query = "SELECT * FROM master_petugasmedis where petugasmedis_id like ? "
                    + "AND nama_petugasmedis like ? "
                    + "AND kategori like ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, "%" + txtidcaripetugasmedis.getText() + "%");
            statement.setString(2, "%" + txtnamacaripetugasmedis.getText() + "%");
            statement.setString(3, "%" + txtstatuspetugasmedis.getText() + "%");
            ResultSet res = statement.executeQuery();
            int baris = 0;
            while (res.next()) {
                baris = res.getRow();
                TableModels1.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6)

                });
                tblcaripetugasmedis.setModel(TableModels1);
            }
            if (baris == 0) {
                JOptionPane.showMessageDialog(null, "Hasil Pencarian = " + baris);
            } else {
                JOptionPane.showMessageDialog(null, "Hasil Pencarian = " + baris);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void ambilcaripetugasmedis() {
        int row = tblcaripetugasmedis.getSelectedRow();
        int col = tblcaripetugasmedis.getSelectedColumn();
        String dataterpilih = tblcaripetugasmedis.getValueAt(row, col).toString();
        String idpetugas = tblcaripetugasmedis.getValueAt(row, 0).toString();
        String namapetugas = tblcaripetugasmedis.getValueAt(row, 1).toString();
        petugas_id = idpetugas;
        txtpetugasmedis.setText(namapetugas);

    }

    private void inserttransaksiasuhan() {
        try {
            String query = "INSERT INTO `rawat_inap`.`asuhankebidanan` "
                    + "(`asuhan_id`, `regid`, `medrec_id`, `tanggalbuat`, "
                    + "`subjektif`, `objektif`, `analisa`, `penatalaksaan`, `petugasmedis`) "
                    + "VALUES (?, ?, ?, NOW(), ?, ?, ?, ?, ?)";
            PreparedStatement s = koneksi.getConnection().prepareStatement(query);
            s.setNull(1, Types.INTEGER);
            s.setString(2, regid);
            s.setString(3, nomedrec);
            s.setString(4, txtsubjektif.getText());
            s.setString(5, txtobjektif.getText());
            s.setString(6, txtanalisa.getText());
            s.setString(7, txtpenatalaksanaan.getText());
            s.setString(8, txtpetugasmedis.getText());
            System.out.println(""+s);
            s.executeUpdate();
            s.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Masuk...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dimasukan : " + e);
        }

    }

    private void bersih() {
        txtobjektif.setText("");
        txtsubjektif.setText("");
        txtpetugasmedis.setText("");
        txtanalisa.setText("");
        txtpenatalaksanaan.setText("");
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
        txtobjektif = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtanalisa = new javax.swing.JTextField();
        btnsimpanlayan = new javax.swing.JButton();
        btnbersihlayan = new javax.swing.JButton();
        txtsubjektif = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtpenatalaksanaan = new javax.swing.JTextField();
        txtpetugasmedis = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblcaripetugasmedis = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtidcaripetugasmedis = new javax.swing.JTextField();
        txtnamacaripetugasmedis = new javax.swing.JTextField();
        txtstatuspetugasmedis = new javax.swing.JTextField();
        btncaripetugasmedis = new javax.swing.JButton();

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Masukan Asuhan");
        jLayeredPane1.add(jLabel1);
        jLabel1.setBounds(110, 10, 160, 14);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data"));

        jLabel3.setText("Subjektif");
        jLayeredPane2.add(jLabel3);
        jLabel3.setBounds(20, 20, 110, 30);

        jLabel4.setText("Objektif");
        jLayeredPane2.add(jLabel4);
        jLabel4.setBounds(20, 70, 100, 30);
        jLayeredPane2.add(txtobjektif);
        txtobjektif.setBounds(140, 70, 170, 30);

        jLabel5.setText("Analisa");
        jLayeredPane2.add(jLabel5);
        jLabel5.setBounds(20, 120, 60, 30);

        txtanalisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtanalisaKeyPressed(evt);
            }
        });
        jLayeredPane2.add(txtanalisa);
        txtanalisa.setBounds(140, 120, 110, 30);

        btnsimpanlayan.setText("Simpan");
        btnsimpanlayan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanlayanActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btnsimpanlayan);
        btnsimpanlayan.setBounds(20, 330, 90, 23);

        btnbersihlayan.setText("Bersihkan");
        btnbersihlayan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbersihlayanActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btnbersihlayan);
        btnbersihlayan.setBounds(250, 330, 100, 23);
        jLayeredPane2.add(txtsubjektif);
        txtsubjektif.setBounds(140, 20, 170, 30);

        jLabel9.setText("Penatalaksanaan");
        jLayeredPane2.add(jLabel9);
        jLabel9.setBounds(20, 170, 90, 30);

        txtpenatalaksanaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpenatalaksanaanMouseClicked(evt);
            }
        });
        jLayeredPane2.add(txtpenatalaksanaan);
        txtpenatalaksanaan.setBounds(140, 170, 170, 30);

        txtpetugasmedis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpetugasmedisMouseClicked(evt);
            }
        });
        jLayeredPane2.add(txtpetugasmedis);
        txtpetugasmedis.setBounds(140, 220, 170, 30);

        jLabel16.setText("Petugas Medis");
        jLayeredPane2.add(jLabel16);
        jLabel16.setBounds(20, 220, 100, 30);

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Petugas Medis"));

        tblcaripetugasmedis.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcaripetugasmedis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcaripetugasmedisMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblcaripetugasmedis);

        jLayeredPane4.add(jScrollPane2);
        jScrollPane2.setBounds(10, 120, 420, 250);

        jLabel10.setText("ID Petugas Medis");
        jLayeredPane4.add(jLabel10);
        jLabel10.setBounds(20, 30, 130, 30);

        jLabel11.setText("Nama Petugas Medis");
        jLayeredPane4.add(jLabel11);
        jLabel11.setBounds(20, 70, 140, 30);

        jLabel12.setText("Status");
        jLayeredPane4.add(jLabel12);
        jLabel12.setBounds(270, 30, 60, 30);
        jLayeredPane4.add(txtidcaripetugasmedis);
        txtidcaripetugasmedis.setBounds(170, 30, 90, 30);
        jLayeredPane4.add(txtnamacaripetugasmedis);
        txtnamacaripetugasmedis.setBounds(170, 70, 90, 30);

        txtstatuspetugasmedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstatuspetugasmedisActionPerformed(evt);
            }
        });
        jLayeredPane4.add(txtstatuspetugasmedis);
        txtstatuspetugasmedis.setBounds(340, 30, 90, 30);

        btncaripetugasmedis.setText("Cari");
        btncaripetugasmedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripetugasmedisActionPerformed(evt);
            }
        });
        jLayeredPane4.add(btncaripetugasmedis);
        btncaripetugasmedis.setBounds(340, 70, 90, 30);

        jTabbedPane1.addTab("Petugas Medis", jLayeredPane4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addComponent(jLayeredPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanlayanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanlayanActionPerformed
        inserttransaksiasuhan();
    }//GEN-LAST:event_btnsimpanlayanActionPerformed

    private void txtpenatalaksanaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpenatalaksanaanMouseClicked
    }//GEN-LAST:event_txtpenatalaksanaanMouseClicked

    private void txtanalisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtanalisaKeyPressed
    }//GEN-LAST:event_txtanalisaKeyPressed

    private void txtpetugasmedisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpetugasmedisMouseClicked
    }//GEN-LAST:event_txtpetugasmedisMouseClicked

    private void btnbersihlayanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbersihlayanActionPerformed
        bersih();
    }//GEN-LAST:event_btnbersihlayanActionPerformed

    private void btncaripetugasmedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripetugasmedisActionPerformed
        caripetugasmedis();
    }//GEN-LAST:event_btncaripetugasmedisActionPerformed

    private void txtstatuspetugasmedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstatuspetugasmedisActionPerformed
    }//GEN-LAST:event_txtstatuspetugasmedisActionPerformed

    private void tblcaripetugasmedisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcaripetugasmedisMouseClicked
        ambilcaripetugasmedis();
    }//GEN-LAST:event_tblcaripetugasmedisMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbersihlayan;
    private javax.swing.JButton btncaripetugasmedis;
    private javax.swing.JButton btnsimpanlayan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblcaripetugasmedis;
    private javax.swing.JTextField txtanalisa;
    private javax.swing.JTextField txtidcaripetugasmedis;
    private javax.swing.JTextField txtnamacaripetugasmedis;
    private javax.swing.JTextField txtobjektif;
    private javax.swing.JTextField txtpenatalaksanaan;
    private javax.swing.JTextField txtpetugasmedis;
    private javax.swing.JTextField txtstatuspetugasmedis;
    private static javax.swing.JTextField txtsubjektif;
    // End of variables declaration//GEN-END:variables
}
