/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toniPackage;

import Class.koneksi;
import ToniPopups.DetailHistoryPop;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
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
 * @author toni
 */
public class HistoryRujukan extends javax.swing.JInternalFrame {

    /**
     * Creates new form HistoryMutasi
     */
    RujukanDao daoRukan;
    List<HistoryRujukanEntity> md;
    TabelModelHistoryRujukan tmr;
    DetailHistoryPop dhp;
    SimpleDateFormat format;
    Date perkutut;

    public HistoryRujukan() {
        initComponents();
        defaultload();

    }

    private void defaultload() {
        daoRukan = new RujukanDao();
        md = daoRukan.semua();
        tmr = new TabelModelHistoryRujukan(md);
        tblRujukan.setModel(tmr);
        lebarkolum();
        setDate();
    }

    private void setDate() {
        format = new SimpleDateFormat("yyyy-MM-dd");
        Date haha = new Date();
        perkutut = new Date(haha.getTime());
        try {
            jDateChooser1.setDate(perkutut);
            jDateChooser3.setDate(perkutut);
        } catch (Exception e) {
        }

    }

    private void lebarkolum() {
        TableColumn kolum;
        tblRujukan.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        int[] lebar = {90, 120, 90, 120, 120, 120, 120, 120, 120, 90};
        for (int i = 0; i < tblRujukan.getColumnCount(); i++) {
            kolum = tblRujukan.getColumnModel().getColumn(i);
            kolum.setPreferredWidth(lebar[i]);
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
        nohistoryFil = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        regpasFil = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCari = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRujukan = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        btnDetail = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HISTORY RUJUKAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 24))); // NOI18N
        jPanel1.setLayout(new java.awt.CardLayout());

        jLabel1.setText("No History ");

        jLabel2.setText("Regpas");

        jLabel3.setText("Tanggal Rujukani");

        jLabel4.setText("s/d");

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

        btnCetak.setText("Cetak Ulang Form");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        tblRujukan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRujukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRujukanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRujukan);

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jDateChooser3.setDateFormatString("yyyy-MM-dd");

        btnDetail.setText("Detail");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        jButton1.setText("Cetak Rekap Per Tanggal");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nohistoryFil, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(regpasFil, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(btnCetak)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nohistoryFil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(regpasFil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(btnReset)
                    .addComponent(btnDetail))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCetak)
                        .addComponent(jButton1))
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, "card2");

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblRujukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRujukanMouseClicked
//        
    }//GEN-LAST:event_tblRujukanMouseClicked

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        HistoryRujukanEntity hre = new HistoryRujukanEntity();
        hre.setHistoryrujukan_id(nohistoryFil.getText());
        hre.setRegid(regpasFil.getText());
        String awal = format.format(jDateChooser1.getDate());
        String akhir = format.format(jDateChooser3.getDate());
        md = daoRukan.cariHistory(hre, awal, akhir);
        tmr = new TabelModelHistoryRujukan(md);
        tblRujukan.setModel(tmr);
        lebarkolum();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        nohistoryFil.setText("");
        regpasFil.setText("");
        defaultload();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        dhp = new DetailHistoryPop();
        int i = tblRujukan.getSelectedRow();
        if (i >= 0) {
            String icd = (String) tmr.getValueAt(i, 0);
            dhp.tampilDetailRujukan(icd);
            int option = JOptionPane.showConfirmDialog(null, dhp, "Detail Rujukan", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Data belum ada yang di diklik");
        }
    }//GEN-LAST:event_btnDetailActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
        int i = tblRujukan.getSelectedRow();
        if (i >= 0) {
            try {
                JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream("/report/RujukanCetak2.jrxml"));
                String b = (String) tmr.getValueAt(i, 0);
                String c = "select * from "
                        + "historyrujukan a, master_medrec b "
                        + "where a.medrec_id = b.medrec_id AND a.historyrujukan_id = '" + b + "'";
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
        }else {
            JOptionPane.showMessageDialog(null, "Data belum ada yang di diklik");
        }


    }//GEN-LAST:event_btnCetakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nohistoryFil;
    private javax.swing.JTextField regpasFil;
    private javax.swing.JTable tblRujukan;
    // End of variables declaration//GEN-END:variables
}
