/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toniPackage;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Toni
 */
public class TabelModelHistoryRujukan extends AbstractTableModel {

    List<HistoryRujukanEntity> md;

    public TabelModelHistoryRujukan(List<HistoryRujukanEntity> md) {
        this.md = md;
    }

    @Override
    public int getRowCount() {
        return md.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int baris, int kolum) {
        HistoryRujukanEntity r = md.get(baris);
        if (kolum == 0) {
            return r.getHistoryrujukan_id();
        }
        if (kolum == 1) {
            return r.getRegid();
        }
        if (kolum == 2) {
            return r.getMedrec_id();
        }
        if (kolum == 3) {
            return r.getAsalrujukan();
        }
        if (kolum == 4) {
            return r.getPetugasdirujuk();
        }
        if (kolum == 5) {
            return r.getTujuanrujukan();
        }
        if (kolum == 6) {
            return r.getPerlakuanSebelumnya();
        }
        if (kolum == 7) {
            return r.getTanggalrujukan();
        }
        if (kolum == 8) {
            return r.getKet_rujuk();
        }
        if (kolum == 9) {
            return r.getStatus_rujukan();
        }
        return "gak ade";
    }

    @Override
    public String getColumnName(int kolum) {
        switch (kolum) {
            case 0:
                return "No History";
            case 1:
                return "Registrasi Pasien";
            case 2:
                return "Medrec";
            case 3:
                return "Asal Rujukan";
            case 4:
                return "Petugas Dirujuk ";
            case 5:
                return "Tujuan Rujukan ";
            case 6:
                return "Perlakuan Sebelumnya ";
            case 7:
                return "Tanggal Dirujuk ";
            case 8:
                return "Keterangan Rujukan / Diagnosa ";
            case 9:
                return "Status Rujukan";
            default:
                return "undefined";
        }
    }

}
