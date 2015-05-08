/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi_pelayanan;

import Class.Structure;
import Class.TableViews;
import Class.koneksi;
import Sistem_monitoring_mutasi_ri.DateEditor;
import Sistem_monitoring_mutasi_ri.TableRenderer;
import Sistem_monitoring_mutasi_ri.frmMutasiKamar;
import ToniPopups.RujukanForm;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import sistem_rawat_inap_puskesmas.main_rawat_inap;
import sistem_rawat_inap_puskesmas.pencarian_pelayanan;

/**
 *
 * @author root
 */
public class form_transaksi_panel extends javax.swing.JInternalFrame {

    DateFormat formatter;
    SimpleDateFormat simplaformat;
    Date date;
    JDesktopPane DP;
    TableViews TableViews = new TableViews();
    DefaultTableModel TableModels;
    DefaultTableModel TableModels1;
    DefaultTableModel TableModels2;
    DefaultComboBoxModel combo = new DefaultComboBoxModel();
    private ResultSet res;
    String regid;
    String dokter_id;
    private String awal;
    private String akhir;

    public form_transaksi_panel(JDesktopPane DP, String regid) {
        initComponents();

        this.DP = DP;
        this.regid = regid;
        txtregid.setText(regid);

        tampil_cari();
        table_model_trxlayanan();
        table_model_trxobat();
        table_model_trxkamar();
        Click();
        ClickNewWindow();
    }

    public void table_model_trxlayanan() {
        TableModels = TableViews.getDefaultTableModel(new String[]{
            "Transaksi Layanan ID",
            "Registrasi_id",
            "Nama Pasien",
            "ID Petugas Medis",
            "Nama Petugas Medis",
            "ID Layanan",
            "Nama Layanan",
            "Harga",
            "Satuan",
            "Total",
            "Petugas",
            "Tanggal Dibuat"
        },
                null, new int[]{1, 4, 6, 7, 8}, null);
        tbltransaksilayanan.setModel(TableModels);
        TableViews.table(tbltransaksilayanan, new int[]{200, 100, 100, 150, 150, 150, 150, 100, 50, 100, 100, 100});

        tampil_trxlayanan();

    }

    private void tampil_trxlayanan() {
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "SELECT * FROM transaksi_layanan where regid = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, regid);
            ResultSet res = statement.executeQuery();
            //res = statement.executeQuery("select * from mahasiswa");
            while (res.next()) {
                TableModels.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getString(7),
                    res.getString(8),
                    res.getString(9),
                    res.getString(10),
                    res.getString(11),
                    res.getString(12)

                });
                tbltransaksilayanan.setModel(TableModels);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void tampil_cari() {
        try {
            String query = "SELECT a.medrec_id, b.nama, c.nama_petugasmedis, a.dokter_id"
                    + " FROM regpasien a, master_medrec b, master_petugasmedis c "
                    + "WHERE a.medrec_id = b.medrec_id "
                    + "AND a.dokter_id = c.petugasmedis_id "
                    + "AND a.regid = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, regid);
            ResultSet res = statement.executeQuery();
            int baris = 0;
            while (res.next()) {
                baris = res.getRow();
                txtnomedrec.setText(res.getString(1));
                txtnamapasien.setText(res.getString(2));
                txtdokter.setText(res.getString(3));
                dokter_id = (res.getString(4));

            }
            if ("".equals(txtregid.getText())) {

            } else {
                if (baris == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Pasien Belum Terdaftar");
                } else {

                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void table_model_trxobat() {
        TableModels1 = TableViews.getDefaultTableModel(new String[]{
            "Transaksi Obat ID",
            "Registrasi ID",
            "Nama Pasien",
            "Petugas Medis",
            "Obat ID",
            "Nama Obat",
            "Harga",
            "Satuan",
            "Total",
            "Petugas",
            "Tanggal Dibuat"
        },
                null, new int[]{1, 4, 7, 8}, null);
        tbltransaksiobt.setModel(TableModels1);
        TableViews.table(tbltransaksiobt, new int[]{200, 100, 150, 150, 150, 150, 100, 50, 100, 100, 100});

        tampil_trxobat();

    }

    private void tampil_trxobat() {
        try {
            TableModels1.getDataVector().removeAllElements();
            String query = "SELECT * FROM transaksi_obat where regid = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, regid);
            ResultSet res = statement.executeQuery();
            //res = statement.executeQuery("select * from mahasiswa");
            while (res.next()) {
                TableModels1.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getString(7),
                    res.getString(8),
                    res.getString(9),
                    res.getString(10),
                    res.getString(11)

                });
                tbltransaksiobt.setModel(TableModels1);
            }
            statement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void updatetrxlayanan() {
        int row = tbltransaksilayanan.getSelectedRow();
        int col = tbltransaksilayanan.getSelectedColumn();
        String dataterpilih = tbltransaksilayanan.getValueAt(row, col).toString();
        String trxlyanan_id = tbltransaksilayanan.getValueAt(row, 0).toString();
        String regid = tbltransaksilayanan.getValueAt(row, 1).toString();
        String id_layanan = tbltransaksilayanan.getValueAt(row, 4).toString();
        String harga_layanan = tbltransaksilayanan.getValueAt(row, 6).toString();
        String satuan_layanan = tbltransaksilayanan.getValueAt(row, 7).toString();
        String total_layanan = Integer.toString((Integer.parseInt(harga_layanan)) * (Integer.parseInt(satuan_layanan)));
        //System.out.println(trxlyanan_id + regid + id_layanan + harga_layanan + satuan_layanan + total_layanan);
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "UPDATE trx_layanan SET regid = ?,"
                    + "layanan_id = ?,defaultharga = ?,satuan = ?,totalharga = ?,"
                    + "petugasbuat = 1, tanggalbuat = now() "
                    + "WHERE trxlayanan_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, regid);
            statement.setString(2, id_layanan);
            statement.setString(3, harga_layanan);
            statement.setString(4, satuan_layanan);
            statement.setString(5, total_layanan);
            statement.setString(6, trxlyanan_id);
            statement.executeUpdate();
            statement.close();

            TableModels.getDataVector().removeAllElements();
            table_model_trxlayanan();

            JOptionPane.showMessageDialog(rootPane, "Data Berhasil diubah...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah : " + ex);
        }
    }

    private void updatetrxobat() {
        int row = tbltransaksiobt.getSelectedRow();
        int col = tbltransaksiobt.getSelectedColumn();
        String dataterpilih = tbltransaksiobt.getValueAt(row, col).toString();
        String transobat_id = tbltransaksiobt.getValueAt(row, 0).toString();
        String regid = tbltransaksiobt.getValueAt(row, 1).toString();
        String id_obat = tbltransaksiobt.getValueAt(row, 4).toString();
        String harga_obat = tbltransaksiobt.getValueAt(row, 6).toString();
        String satuan_obat = tbltransaksiobt.getValueAt(row, 7).toString();
        String total_obat = Integer.toString((Integer.parseInt(harga_obat)) * (Integer.parseInt(satuan_obat)));
        //System.out.println(trxlyanan_id + regid + id_layanan + harga_layanan + satuan_layanan + total_layanan);
        try {
            TableModels1.getDataVector().removeAllElements();
            String query = "UPDATE trx_obat SET regid = ?,"
                    + "obat_id = ?,satuan = ?,total = ?,"
                    + "petugasbuat = 1, tanggalbuat = now() "
                    + "WHERE trxobt_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, regid);
            statement.setString(2, id_obat);
            statement.setString(3, satuan_obat);
            statement.setString(4, total_obat);
            statement.setString(5, transobat_id);
            statement.executeUpdate();
            statement.close();

            TableModels1.getDataVector().removeAllElements();
            table_model_trxobat();

            JOptionPane.showMessageDialog(rootPane, "Data Berhasil diubah...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah : " + ex);
        }

    }

    private void deletetrxlayanan() {
        int row = tbltransaksilayanan.getSelectedRow();
        int col = tbltransaksilayanan.getSelectedColumn();
        String dataterpilih = tbltransaksilayanan.getValueAt(row, col).toString();
        String translayan_id = tbltransaksilayanan.getValueAt(row, 0).toString();
        //System.out.println(trxlyanan_id + regid + id_layanan + harga_layanan + satuan_layanan + total_layanan);
        try {
            TableModels.getDataVector().removeAllElements();
            String query = "DELETE FROM trx_layanan WHERE trxlayanan_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, translayan_id);
            statement.executeUpdate();
            statement.close();

            TableModels.getDataVector().removeAllElements();
            table_model_trxlayanan();

            JOptionPane.showMessageDialog(rootPane, "Data Berhasil Dihapus...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus : " + ex);
        }

    }

    private void deletetrxobat() {
        int row = tbltransaksiobt.getSelectedRow();
        int col = tbltransaksiobt.getSelectedColumn();
        String dataterpilih = tbltransaksiobt.getValueAt(row, col).toString();
        String transobat_id = tbltransaksiobt.getValueAt(row, 0).toString();

        //System.out.println(trxlyanan_id + regid + id_layanan + harga_layanan + satuan_layanan + total_layanan);
        try {
            TableModels1.getDataVector().removeAllElements();
            String query = "DELETE FROM trx_obat WHERE trxobt_id = ?";
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, transobat_id);

            statement.executeUpdate();
            statement.close();

            TableModels1.getDataVector().removeAllElements();
            table_model_trxobat();

            JOptionPane.showMessageDialog(rootPane, "Data Berhasil diubah...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah : " + ex);
        }

    }

    /*
     Transaksi Kamar / Mutasi  
     */
    private void table_model_trxkamar() {
        TableModels2 = TableViews.getDefaultTableModel(new String[]{"Nomor", "Nama Kamar", "Tanggal Masuk", "Tanggal Keluar", "Petugas Buat", "Tanggal Buat", "Default Harga", "Jumlah", "Total Harga Kamar", ""}, null, new int[]{3}, null);
        jTable1.setModel(TableModels2);
        TableViews.table(jTable1, new int[]{50, 200, 200, 200, 150, 150, 150, 100, 200, 0});
    }

    private void SettingCalendarModel(String identifier) {
        jTable1.getColumn(identifier).setCellRenderer(new TableRenderer());
        jTable1.getColumn(identifier).setCellEditor(new DateEditor(new JCheckBox()));
    }

    private void tampil_trx_kamar() {
        try {
            TableModels2.getDataVector().removeAll(null);
            String parameter = regid;
            PreparedStatement preparestatement;
            ResultSet resultset;
            Structure query = new Structure();
            preparestatement = (com.mysql.jdbc.PreparedStatement) koneksi.getConnection().prepareStatement(query.TransaksiKamarView());
            preparestatement.setString(1, parameter);
            resultset = preparestatement.executeQuery();
            int n = 0;
            SettingCalendarModel("Tanggal Masuk");
            SettingCalendarModel("Tanggal Keluar");
            formatter = new SimpleDateFormat("yyyy-MM-dd");

            while (resultset.next()) {
                n++;
                Object[] object = new Object[jTable1.getColumnCount()];
                object[0] = n;
                object[1] = resultset.getString(3);
                JDateChooser jm = new JDateChooser();
                jm.setDate(date = formatter.parse(resultset.getString(4)));
                object[2] = jm;
                JDateChooser jd = new JDateChooser();
                jd.setDate(date = formatter.parse(resultset.getString(5)));
                object[3] = jd;
                object[4] = resultset.getString(6);
                object[5] = resultset.getString(7);
                object[6] = resultset.getString(8);
                object[7] = resultset.getString(9);
                object[8] = resultset.getString(10);
                object[9] = resultset.getString(1);
                TableModels2.addRow(object);
            }
        } catch (Exception e) {
        }
    }

    private void aa() throws SQLException {
        com.mysql.jdbc.PreparedStatement preparestatement;
        ResultSet resultset;
        Structure query = new Structure();
        preparestatement = (com.mysql.jdbc.PreparedStatement) koneksi.getConnection().prepareCall("call updatetrxkamar(?,?,?,?)");

        formatter = new SimpleDateFormat("yyyy-MM-dd");
        simplaformat = new SimpleDateFormat("D");
        if (jComboBox1.getSelectedIndex() == 1) {
            OpenWindowsMutasi();
        } else if (jComboBox1.getSelectedIndex() == 2) {
            JDateChooser jdd = (JDateChooser) jTable1.getValueAt(jTable1.getSelectedRow(), 3);
            JDateChooser jdm = (JDateChooser) jTable1.getValueAt(jTable1.getSelectedRow(), 2);
            String jdf = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 9);

            int day1 = Integer.parseInt(simplaformat.format(jdd.getDate()));
            int day2 = Integer.parseInt(simplaformat.format(jdm.getDate()));

            int day3 = day1 - day2;
            String count = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 6);
            int counts = Integer.parseInt(count);
            int sum = counts * day3;

            preparestatement.setString(1, jdf);
            preparestatement.setString(2, formatter.format(jdd.getDate()));
            preparestatement.setInt(3, day3);
            preparestatement.setInt(4, sum);
            preparestatement.executeQuery();
            tampil_trx_kamar();
        }
        jComboBox1.setSelectedIndex(0);
    }

    private void Penghitung() {
        SimpleDateFormat formats = new SimpleDateFormat("D");
        int day1 = Integer.parseInt(formats.format(jTable1.getValueAt(jTable1.getSelectedRow(), 2)));
        JDateChooser jdd = (JDateChooser) jTable1.getValueAt(jTable1.getSelectedRow(), 3);
        int day2 = Integer.parseInt(formats.format(jdd));
        int dayc = day2 - day1;
        System.out.println(day1);
        System.out.println(day2);
        System.out.println(dayc);
    }

//    private void a() {
//        JDateChooser jdd = (JDateChooser) jTable1.getValueAt(1, 3);
//        jdd.addPropertyChangeListener(new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                if ("ancestor".equals(evt.getPropertyName())) {
//                    System.out.println("polo");
//                } else {
//                    System.out.println("fail");
//                }
//            }
//        });
//
//    }
    private void OpenWindowsMutasi() {
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        JDateChooser jdd = (JDateChooser) jTable1.getValueAt(jTable1.getSelectedRow(), 3);
        JDateChooser jdm = (JDateChooser) jTable1.getValueAt(jTable1.getSelectedRow(), 2);
        String Kirim = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 1)
                + " ::: "
                + formatter.format(jdm.getDate())
                + " ::: "
                + formatter.format(jdd.getDate())
                + " ::: "
                + jTable1.getValueAt(jTable1.getSelectedRow(), 4)
                + " ::: "
                + jTable1.getValueAt(jTable1.getSelectedRow(), 5)
                + " ::: "
                + jTable1.getValueAt(jTable1.getSelectedRow(), 6)
                + " ::: "
                + jTable1.getValueAt(jTable1.getSelectedRow(), 7)
                + " ::: "
                + jTable1.getValueAt(jTable1.getSelectedRow(), 8)
                + " ::: "
                + txtregid.getText();
        frmMutasiKamar page = new frmMutasiKamar(DP, Kirim);
        System.out.println(Kirim);
        DP.add(page);
        page.show();
    }

    private void ClickNewWindow() {
        jTable1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    OpenWindowsMutasi();
                }
            }
        });
    }

    private void Click() {
        jTabbedPane1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    if (jTabbedPane1.getSelectedIndex() == 1) {
                        tampil_trx_kamar();
                    }

                }
            }
        });
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
        btnPaseinLedger = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        txtregid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtdokter = new javax.swing.JTextField();
        btncarimasterpasien = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnomedrec = new javax.swing.JTextField();
        txtnamapasien = new javax.swing.JTextField();
        btndetailpasien = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltransaksilayanan = new javax.swing.JTable();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        btntambahlayanan = new javax.swing.JButton();
        btnhapuslayanan = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnCetakLayan = new javax.swing.JButton();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jLayeredPane9 = new javax.swing.JLayeredPane();
        btntambahlayanan1 = new javax.swing.JButton();
        btnhapuslayanan1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnCetakobat = new javax.swing.JButton();
        jLayeredPane15 = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbltransaksiobt = new javax.swing.JTable();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Transaksi Rawat Inap");
        jLayeredPane1.add(jLabel1);
        jLabel1.setBounds(340, 10, 130, 14);

        btnPaseinLedger.setText("Cetak Pasien Ledger");
        btnPaseinLedger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaseinLedgerActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btnPaseinLedger);
        btnPaseinLedger.setBounds(670, 0, 150, 30);

        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Psien"));
        jLayeredPane2.add(txtregid);
        txtregid.setBounds(130, 30, 190, 30);

        jLabel2.setText("Registrasi ID");
        jLayeredPane2.add(jLabel2);
        jLabel2.setBounds(20, 30, 100, 30);

        jLabel3.setText("Petugas Medis");
        jLayeredPane2.add(jLabel3);
        jLabel3.setBounds(20, 70, 90, 30);
        jLayeredPane2.add(txtdokter);
        txtdokter.setBounds(130, 70, 190, 30);

        btncarimasterpasien.setText("Cari Pasien");
        btncarimasterpasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarimasterpasienActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btncarimasterpasien);
        btncarimasterpasien.setBounds(330, 30, 120, 30);

        jLabel8.setText("No Medrec");
        jLayeredPane2.add(jLabel8);
        jLabel8.setBounds(460, 30, 70, 30);

        jLabel6.setText("Nama");
        jLayeredPane2.add(jLabel6);
        jLabel6.setBounds(460, 70, 70, 30);
        jLayeredPane2.add(txtnomedrec);
        txtnomedrec.setBounds(540, 30, 160, 30);
        jLayeredPane2.add(txtnamapasien);
        txtnamapasien.setBounds(540, 70, 160, 30);

        btndetailpasien.setText("Detail Pasien");
        btndetailpasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndetailpasienActionPerformed(evt);
            }
        });
        jLayeredPane2.add(btndetailpasien);
        btndetailpasien.setBounds(710, 30, 110, 30);

        jButton2.setText("Rujukan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(jButton2);
        jButton2.setBounds(330, 70, 120, 30);

        tbltransaksilayanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbltransaksilayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbltransaksilayananKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbltransaksilayanan);

        jLayeredPane3.add(jScrollPane1);
        jScrollPane1.setBounds(22, 110, 770, 240);

        jLayeredPane6.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Transaksi"));
        jLayeredPane3.add(jLayeredPane6);
        jLayeredPane6.setBounds(10, 90, 800, 280);

        jLayeredPane7.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        btntambahlayanan.setText("Tambah");
        btntambahlayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahlayananActionPerformed(evt);
            }
        });
        jLayeredPane7.add(btntambahlayanan);
        btntambahlayanan.setBounds(20, 30, 100, 23);

        btnhapuslayanan.setText("Hapus");
        btnhapuslayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapuslayananActionPerformed(evt);
            }
        });
        jLayeredPane7.add(btnhapuslayanan);
        btnhapuslayanan.setBounds(130, 30, 110, 23);

        jLabel4.setText("Note : Enter to submit update data");
        jLayeredPane7.add(jLabel4);
        jLabel4.setBounds(580, 20, 210, 40);

        btnCetakLayan.setText("Cetak Layanan");
        btnCetakLayan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakLayanActionPerformed(evt);
            }
        });
        jLayeredPane7.add(btnCetakLayan);
        btnCetakLayan.setBounds(260, 30, 120, 20);

        jLayeredPane3.add(jLayeredPane7);
        jLayeredPane7.setBounds(10, 10, 800, 70);

        jTabbedPane1.addTab("Transaksi Pelayanan", jLayeredPane3);

        jLayeredPane9.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        btntambahlayanan1.setText("Tambah");
        btntambahlayanan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahlayanan1ActionPerformed(evt);
            }
        });
        jLayeredPane9.add(btntambahlayanan1);
        btntambahlayanan1.setBounds(20, 30, 100, 23);

        btnhapuslayanan1.setText("Hapus");
        btnhapuslayanan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapuslayanan1ActionPerformed(evt);
            }
        });
        jLayeredPane9.add(btnhapuslayanan1);
        btnhapuslayanan1.setBounds(140, 30, 110, 23);

        jLabel5.setText("Note : Enter to submit update data");
        jLayeredPane9.add(jLabel5);
        jLabel5.setBounds(580, 20, 210, 40);

        btnCetakobat.setText("Cetak Obat");
        btnCetakobat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakobatActionPerformed(evt);
            }
        });
        jLayeredPane9.add(btnCetakobat);
        btnCetakobat.setBounds(260, 30, 120, 20);

        jLayeredPane4.add(jLayeredPane9);
        jLayeredPane9.setBounds(10, 10, 800, 70);

        jLayeredPane15.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Transaksi"));

        tbltransaksiobt.setModel(new javax.swing.table.DefaultTableModel(
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
        tbltransaksiobt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbltransaksiobtKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tbltransaksiobt);

        jLayeredPane15.add(jScrollPane4);
        jScrollPane4.setBounds(10, 20, 790, 210);

        jLayeredPane4.add(jLayeredPane15);
        jLayeredPane15.setBounds(10, 90, 810, 250);

        jTabbedPane1.addTab("Transaksi Obat", jLayeredPane4);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Transaksi Kamar"));

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
        jTable1.setToolTipText("Please double click for room mutation");
        jScrollPane5.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Opsional", "Mutasi", "Selesai" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane5.add(jPanel1);
        jPanel1.setBounds(10, 10, 810, 380);

        jTabbedPane1.addTab("Transaksi Kamar", jLayeredPane5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLayeredPane1)
                    .addComponent(jLayeredPane2)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncarimasterpasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarimasterpasienActionPerformed
        pencarian_pelayanan carpas = new pencarian_pelayanan(DP);
        DP.add(carpas);
        carpas.show();

    }//GEN-LAST:event_btncarimasterpasienActionPerformed

    private void btntambahlayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahlayananActionPerformed
        tambah_layanan layan = new tambah_layanan(regid);
        //DP.add(layan);
        //layan.show();
        layan.add(new JButton("click"));
        layan.add(new JTextField(20));
        layan.add(new JLabel("Label"));
        //JOptionPane.showMessageDialog(null, layan,"information",JOptionPane.INFORMATION_MESSAGE);
        int jop = JOptionPane.showOptionDialog(null,
                layan, "", JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, null,
                new String[]{"Selesai"}, // this is the array
                "default");
        if (jop == JOptionPane.YES_OPTION) {
            this.repaint();
            table_model_trxlayanan();
        }
    }//GEN-LAST:event_btntambahlayananActionPerformed

    private void btntambahlayanan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahlayanan1ActionPerformed
        tambah_obat obat = new tambah_obat(regid);
        obat.add(new JButton("click"));
        obat.add(new JTextField(20));
        obat.add(new JLabel("Label"));
        //JOptionPane.showMessageDialog(null, layan,"information",JOptionPane.INFORMATION_MESSAGE);
        int jop = JOptionPane.showOptionDialog(null,
                obat, "", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, new String[]{"Selesai"}, // this is the array
                "default");
        if (jop == JOptionPane.YES_OPTION) {
            this.repaint();
            table_model_trxobat();
        }
    }//GEN-LAST:event_btntambahlayanan1ActionPerformed

    private void btndetailpasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndetailpasienActionPerformed
        Sistem_monitoring_mutasi_ri.frmCariMedrec medrec = new Sistem_monitoring_mutasi_ri.frmCariMedrec(DP, txtnomedrec.getText(), 1);
        DP.add(medrec);
        medrec.show();
    }//GEN-LAST:event_btndetailpasienActionPerformed

    private void tbltransaksilayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbltransaksilayananKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            updatetrxlayanan();
        }
    }//GEN-LAST:event_tbltransaksilayananKeyPressed

    private void tbltransaksiobtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbltransaksiobtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            updatetrxobat();
        }
    }//GEN-LAST:event_tbltransaksiobtKeyPressed

    private void btnhapuslayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapuslayananActionPerformed
        deletetrxlayanan();
    }//GEN-LAST:event_btnhapuslayananActionPerformed

    private void btnhapuslayanan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapuslayanan1ActionPerformed
        deletetrxobat();
    }//GEN-LAST:event_btnhapuslayanan1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        main_rawat_inap rtp = new main_rawat_inap();
        RujukanForm rf = new RujukanForm(null, true);
        rf.setA(txtregid.getText());
        rf.setMedrec(txtnomedrec.getText());
        System.out.println(txtdokter.getText() + "hahahha");
        rf.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        try {
            aa();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnCetakLayanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakLayanActionPerformed
        try {
            JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/report/Invoicelayanan.jrxml"));
            String c = "SELECT * FROM transaksi_layanan a, master_medrec b, regpasien c"
                    + " where a.regid=c.regid and b.medrec_id=c.medrec_id and a.regid = '" + txtregid.getText() + "'";
            JRDesignQuery query = new JRDesignQuery();
            query.setText(c);
            System.out.println(c + "");
            jd.setQuery(query);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, koneksi.getConnection());
            JasperViewer.viewReport(jp,false);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }//GEN-LAST:event_btnCetakLayanActionPerformed

    private void btnCetakobatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakobatActionPerformed
        try {
            JasperDesign  jd = JRXmlLoader.load(getClass().getResourceAsStream("/report/TindakanObat.jrxml"));
            String c = "SELECT * FROM transaksi_obat a, master_medrec b, regpasien c"
                    + " where a.regid=c.regid and b.medrec_id=c.medrec_id and a.regid = '" + txtregid.getText() + "'";
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

    }//GEN-LAST:event_btnCetakobatActionPerformed

    private void btnPaseinLedgerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaseinLedgerActionPerformed
        try {
            JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/report/PasienLedger.jrxml"));
            String c = "select * from trx_kamar a, master_kamar b, master_ttidur c, regpasien d, master_medrec e "
                    + "where a.regid=d.regid and d.medrec_id=e.medrec_id and a.kamar_id=b.kamar_id and b.kamar_id=c.kamar_id and c.status='1'";
            JRDesignQuery query = new JRDesignQuery();
            query.setText(c);
            jd.setQuery(query);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, koneksi.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnPaseinLedgerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetakLayan;
    private javax.swing.JButton btnCetakobat;
    private javax.swing.JButton btnPaseinLedger;
    private javax.swing.JButton btncarimasterpasien;
    private javax.swing.JButton btndetailpasien;
    private javax.swing.JButton btnhapuslayanan;
    private javax.swing.JButton btnhapuslayanan1;
    private javax.swing.JButton btntambahlayanan;
    private javax.swing.JButton btntambahlayanan1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane15;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JLayeredPane jLayeredPane9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbltransaksilayanan;
    private javax.swing.JTable tbltransaksiobt;
    private javax.swing.JTextField txtdokter;
    private javax.swing.JTextField txtnamapasien;
    private javax.swing.JTextField txtnomedrec;
    private static javax.swing.JTextField txtregid;
    // End of variables declaration//GEN-END:variables
}
