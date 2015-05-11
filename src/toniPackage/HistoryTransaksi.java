/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toniPackage;

import Class.TableViews;
import Class.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Toni
 */
public class HistoryTransaksi extends javax.swing.JInternalFrame {

    /**
     * Creates new form HistoryTransaksi
     */
    SimpleDateFormat format;
    Date perkutut;
    TableViews TableViews = new TableViews();
    TableViews TableViewsobat = new TableViews();
    DefaultTableModel TableModels;
    DefaultTableModel TableModelsobat;

    public HistoryTransaksi() {
        initComponents();
        defaultloadlayanan();
        defaultloadobat();
    }

    private void SettingTableModelLayanan() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
            "No. Registrasi",
            "No. Medrec",
            "Nama Pasien",
            "Nomor Transaksi",
            "Nomor Layanan",
            "Nama layanan"
        },
                null, new int[]{4, 6, 8}, null);
        tblLayanan.setModel(TableModels);
        TableViews.table(tblLayanan, new int[]{90, 100, 150, 150, 150, 100});
    }

    private void SettingTableModelObat() {
        TableModelsobat = TableViewsobat.getDefaultTableModel(new String[]{
            "No. Registrasi",
            "No. Medrec",
            "Nama Pasien",
            "Nomor Transaksi",
            "Kode Obat",
            "Nama Obat"
        },
                null, new int[]{4, 6, 8}, null);
        tblObat.setModel(TableModelsobat);
        TableViewsobat.table(tblObat, new int[]{90, 100, 150, 150, 150, 100});
    }

    private void tampilkansemua() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT a.regid, b.medrec_id, a.nama, a.trxlayanan_id, a.layanan_id, a.namalayanan "
                    + "FROM transaksi_layanan a, master_medrec b, regpasien c "
                    + "where a.regid=c.regid and b.medrec_id=c.medrec_id "
                    + "limit 20 ";
            PreparedStatement s = koneksi.getConnection().prepareStatement(query);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                TableModels.addRow(new Object[]{
                    r.getString(1),
                    r.getString(2),
                    r.getString(3),
                    r.getString(4),
                    r.getString(5),
                    r.getString(6)});
                tblLayanan.setModel(TableModels);
            }
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampilkansemuaobat() {
        try {
            TableModelsobat.getDataVector().removeAllElements();
            String query = "SELECT a.regid, b.medrec_id, a.nama, a.trxobt_id, a.obat_id, a.namaobat "
                    + "FROM transaksi_obat a, master_medrec b, regpasien c "
                    + "where a.regid=c.regid and b.medrec_id=c.medrec_id "
                    + "limit 20 ";
            PreparedStatement s = koneksi.getConnection().prepareStatement(query);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                TableModelsobat.addRow(new Object[]{
                    r.getString(1),
                    r.getString(2),
                    r.getString(3),
                    r.getString(4),
                    r.getString(5),
                    r.getString(6)});
                tblObat.setModel(TableModelsobat);
            }
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void cariData() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "Select a.regid, b.medrec_id, a.nama, a.trxlayanan_id, a.layanan_id, a.namalayanan "
                    + "from transaksi_layanan a, master_medrec b, regpasien c "
                    + "where a.regid=c.regid and b.medrec_id=c.medrec_id and a.regid like ? and b.medrec_id like ? and a.nama like ? "
                    + "and a.tanggalbuat between ? and ? ";
            PreparedStatement s = koneksi.getConnection().prepareStatement(query);
            s.setString(1, "%" + regpasFil.getText() + "%");
            s.setString(2, "%" + nomedrec.getText() + "%");
            s.setString(3, "%" + namaFil1.getText() + "%");
            s.setString(4, format.format(jDateChooser1.getDate()));
            s.setString(5, format.format(jDateChooser3.getDate()));
            ResultSet r = s.executeQuery();
            while (r.next()) {
                TableModels.addRow(new Object[]{
                    r.getString(1),
                    r.getString(2),
                    r.getString(3),
                    r.getString(4),
                    r.getString(5),
                    r.getString(6)});
                tblLayanan.setModel(TableModels);
            }
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void cariDataObat() {
        try {
            TableModelsobat.getDataVector().removeAllElements();
            String query = "Select a.regid, b.medrec_id, a.nama, a.trxobt_id, a.obat_id, a.namaobat "
                    + "from transaksi_obat a, master_medrec b, regpasien c "
                    + "where a.regid=c.regid and b.medrec_id=c.medrec_id and a.regid like ? and b.medrec_id like ? and a.nama like ? "
                    + "and a.tanggalbuat between ? and ? ";
            PreparedStatement s = koneksi.getConnection().prepareStatement(query);
            s.setString(1, "%" + regpasFil1.getText() + "%");
            s.setString(2, "%" + nomedrec1.getText() + "%");
            s.setString(3, "%" + namaFil2.getText() + "%");
            s.setString(4, format.format(jDateChooser2.getDate()));
            s.setString(5, format.format(jDateChooser4.getDate()));
            ResultSet r = s.executeQuery();
            while (r.next()) {
                TableModelsobat.addRow(new Object[]{
                    r.getString(1),
                    r.getString(2),
                    r.getString(3),
                    r.getString(4),
                    r.getString(5),
                    r.getString(6)});
                tblObat.setModel(TableModelsobat);
            }
            s.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void defaultloadlayanan() {
        SettingTableModelLayanan();
        tampilkansemua();
        setDate();
    }

    private void defaultloadobat() {
        SettingTableModelObat();
        tampilkansemuaobat();
        setDate();
    }

    private void setDate() {
        format = new SimpleDateFormat("yyyy-MM-dd");
        Date haha = new Date();
        perkutut = new Date(haha.getTime());
        try {
            jDateChooser1.setDate(haha = format.parse("2015-01-01"));
            jDateChooser2.setDate(haha = format.parse("2015-01-01"));
            jDateChooser3.setDate(perkutut);
            jDateChooser4.setDate(perkutut);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelLayana = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nomedrec = new javax.swing.JTextField();
        regpasFil = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnCari = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCetakRekap = new javax.swing.JButton();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLayanan = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        namaFil1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnCetakUlang = new javax.swing.JButton();
        panelObat = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nomedrec1 = new javax.swing.JTextField();
        regpasFil1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnCari1 = new javax.swing.JButton();
        btnReset1 = new javax.swing.JButton();
        btnCetakRekap1 = new javax.swing.JButton();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblObat = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        namaFil2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnCetakUlang2 = new javax.swing.JButton();

        jLabel1.setText("No. Medrec ");

        nomedrec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomedrecActionPerformed(evt);
            }
        });

        regpasFil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regpasFilActionPerformed(evt);
            }
        });

        jLabel2.setText("Regpas");

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnCetakRekap.setText("Cetak Rekap Per Tanggal");
        btnCetakRekap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakRekapActionPerformed(evt);
            }
        });

        jDateChooser3.setDateFormatString("yyyy-MM-dd");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jLabel3.setText("Tanggal Berobat");

        tblLayanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblLayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLayananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLayanan);

        jLabel4.setText("Nama");

        namaFil1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaFil1ActionPerformed(evt);
            }
        });

        jLabel5.setText("S/D");

        btnCetakUlang.setText("Cetak Ulang Struk Per Regis");
        btnCetakUlang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakUlangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayanaLayout = new javax.swing.GroupLayout(panelLayana);
        panelLayana.setLayout(panelLayanaLayout);
        panelLayanaLayout.setHorizontalGroup(
            panelLayanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayanaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelLayanaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomedrec, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regpasFil, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namaFil1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayanaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCetakRekap, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCetakUlang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayanaLayout.setVerticalGroup(
            panelLayanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayanaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nomedrec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(regpasFil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(btnReset)
                    .addComponent(jLabel4)
                    .addComponent(namaFil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCetakRekap)
                        .addComponent(btnCetakUlang))
                    .addGroup(panelLayanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Transaksi kamar", panelLayana);

        jLabel6.setText("No. Medrec ");

        nomedrec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomedrec1ActionPerformed(evt);
            }
        });

        regpasFil1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regpasFil1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Regpas");

        btnCari1.setText("Cari");
        btnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCari1ActionPerformed(evt);
            }
        });

        btnReset1.setText("Reset");
        btnReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset1ActionPerformed(evt);
            }
        });

        btnCetakRekap1.setText("Cetak Rekap Per Tanggal");
        btnCetakRekap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakRekap1ActionPerformed(evt);
            }
        });

        jDateChooser4.setDateFormatString("yyyy-MM-dd");

        jDateChooser2.setDateFormatString("yyyy-MM-dd");

        jLabel8.setText("Tanggal Berobat");

        tblObat.setModel(new javax.swing.table.DefaultTableModel(
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
        tblObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblObatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblObat);

        jLabel9.setText("Nama");

        namaFil2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaFil2ActionPerformed(evt);
            }
        });

        jLabel10.setText("S/D");

        btnCetakUlang2.setText("Cetak Ulang Struk Per Regis");
        btnCetakUlang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakUlang2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelObatLayout = new javax.swing.GroupLayout(panelObat);
        panelObat.setLayout(panelObatLayout);
        panelObatLayout.setHorizontalGroup(
            panelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelObatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(panelObatLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomedrec1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regpasFil1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namaFil2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelObatLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCetakRekap1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCetakUlang2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelObatLayout.setVerticalGroup(
            panelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelObatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nomedrec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(regpasFil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari1)
                    .addComponent(btnReset1)
                    .addComponent(jLabel9)
                    .addComponent(namaFil2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCetakRekap1)
                        .addComponent(btnCetakUlang2))
                    .addGroup(panelObatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Transaksi Obat", panelObat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nomedrecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomedrecActionPerformed
        // TODO add your handling code here:
        SettingTableModelLayanan();
        cariData();
    }//GEN-LAST:event_nomedrecActionPerformed

    private void regpasFilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regpasFilActionPerformed
        // TODO add your handling code here:
        SettingTableModelLayanan();
        cariData();
    }//GEN-LAST:event_regpasFilActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        SettingTableModelLayanan();
        cariData();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        nomedrec.setText("");
        regpasFil.setText("");
        namaFil1.setText("");
        defaultloadlayanan();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCetakRekapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakRekapActionPerformed
        String awal = format.format(jDateChooser1.getDate());
        String akhir = format.format(jDateChooser3.getDate());
        Map parameters = new HashMap();
        parameters.put("Parameter1", awal);
        parameters.put("Parameter2", akhir);
        try {
            JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/report/historyTrxlayanan.jrxml"));
            String c = "select trxlayanan_id, layanan_id, namalayanan from "
                    + "transaksi_layanan "
                    + "where tanggalbuat between '" + awal + "' and '" + akhir + "'";
            JRDesignQuery query = new JRDesignQuery();
            query.setText(c);
            jd.setQuery(query);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, koneksi.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnCetakRekapActionPerformed

    private void tblLayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLayananMouseClicked

    }//GEN-LAST:event_tblLayananMouseClicked

    private void namaFil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaFil1ActionPerformed
        // TODO add your handling code here:
        SettingTableModelLayanan();
        cariData();
    }//GEN-LAST:event_namaFil1ActionPerformed

    private void nomedrec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomedrec1ActionPerformed
        // TODO add your handling code here:
        SettingTableModelObat();
        cariDataObat();
    }//GEN-LAST:event_nomedrec1ActionPerformed

    private void regpasFil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regpasFil1ActionPerformed
        // TODO add your handling code here:
        SettingTableModelObat();
        cariDataObat();
    }//GEN-LAST:event_regpasFil1ActionPerformed

    private void btnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari1ActionPerformed
        // TODO add your handling code here:
        SettingTableModelObat();
        cariDataObat();
    }//GEN-LAST:event_btnCari1ActionPerformed

    private void btnReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset1ActionPerformed
        // TODO add your handling code here:
        nomedrec1.setText("");
        regpasFil1.setText("");
        namaFil2.setText("");
        defaultloadobat();
    }//GEN-LAST:event_btnReset1ActionPerformed

    private void btnCetakRekap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakRekap1ActionPerformed
        // TODO add your handling code here:
         String awal = format.format(jDateChooser2.getDate());
        String akhir = format.format(jDateChooser4.getDate());
        Map parameters = new HashMap();
        parameters.put("Parameter1", awal);
        parameters.put("Parameter2", akhir);
        try {
            JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/report/historyTrxobat.jrxml"));
            String c = "select trxobt_id,obat_id,namaobat from "
                    + "transaksi_obat "
                    + "where tanggalbuat between '" + awal + "' and '" + akhir + "'";
            JRDesignQuery query = new JRDesignQuery();
            query.setText(c);
            jd.setQuery(query);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, koneksi.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnCetakRekap1ActionPerformed

    private void tblObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblObatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblObatMouseClicked

    private void namaFil2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaFil2ActionPerformed
        // TODO add your handling code here:
        SettingTableModelObat();
        cariDataObat();
    }//GEN-LAST:event_namaFil2ActionPerformed

    private void btnCetakUlang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakUlang2ActionPerformed
        // TODO add your handling code here:
        int w = tblObat.getSelectedRow();
        String d = null;
        if (tblObat.getRowCount() != 0 && tblObat.getSelectedRowCount() != 0 && (regpasFil1.getText()).equals("")) {
            d = (String) TableModelsobat.getValueAt(w, 0);
            try {
                JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/report/TindakanObat.jrxml"));
                String c = "SELECT * FROM transaksi_obat a, master_medrec b, regpasien c"
                        + " where a.regid=c.regid and b.medrec_id=c.medrec_id and a.regid = '" + d + "'";
                JRDesignQuery query = new JRDesignQuery();
                query.setText(c);
                System.out.println(c + "");
                jd.setQuery(query);
                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, koneksi.getConnection());
                JasperViewer.viewReport(jp, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data belum ada yang di diklik");
        }

    }//GEN-LAST:event_btnCetakUlang2ActionPerformed

    private void btnCetakUlangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakUlangActionPerformed
        // TODO add your handling code here:
        int i = tblLayanan.getSelectedRow();
        String b = null;
        if (tblLayanan.getRowCount() != 0 && tblLayanan.getSelectedRowCount() != 0 && (regpasFil.getText()).equals("")) {
            b = (String) TableModels.getValueAt(i, 0);
            try {
                JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/report/Invoicelayanan.jrxml"));
                String c = "SELECT * FROM transaksi_layanan a, master_medrec b, regpasien c"
                        + " where a.regid=c.regid and b.medrec_id=c.medrec_id and a.regid = '" + b + "'";
                JRDesignQuery query = new JRDesignQuery();
                query.setText(c);
                System.out.println(c + "");
                jd.setQuery(query);
                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, koneksi.getConnection());
                JasperViewer.viewReport(jp, false);
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Data belum ada yang di diklik");
        }

    }//GEN-LAST:event_btnCetakUlangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCari1;
    private javax.swing.JButton btnCetakRekap;
    private javax.swing.JButton btnCetakRekap1;
    private javax.swing.JButton btnCetakUlang;
    private javax.swing.JButton btnCetakUlang2;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnReset1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField namaFil1;
    private javax.swing.JTextField namaFil2;
    private javax.swing.JTextField nomedrec;
    private javax.swing.JTextField nomedrec1;
    private javax.swing.JPanel panelLayana;
    private javax.swing.JPanel panelObat;
    private javax.swing.JTextField regpasFil;
    private javax.swing.JTextField regpasFil1;
    private javax.swing.JTable tblLayanan;
    private javax.swing.JTable tblObat;
    // End of variables declaration//GEN-END:variables
}
