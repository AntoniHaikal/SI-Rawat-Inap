/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author yulianakusumawati
 */
public class Structure {
    
    public String Medrec(String Field) {
        return "SELECT * FROM master_medrec WHERE " + Field + " LIKE ? ORDER BY nama ASC";
    }
    
    public String Kamar() {
        return "SELECT * FROM master_kamar WHERE nama_kamar LIKE ? OR kamar_id LIKE ? ORDER BY nama_kamar ASC";
    }
    
    public String TransaksiKamar() {
        return "SELECT * FROM trx_kamar WHERE regid LIKE ? ORDER BY trxkamar_id ASC";
    }
    
    public String TransaksiKamarView() {
        return "SELECT * FROM viewtrxkamar WHERE regid LIKE ? ORDER BY trxkamar_id ASC";
    }
    
    public String CekKamar() {
        return "SELECT * FROM viewkamar WHERE status = ?";
    }
    
    public String TempatTidur() {
        return "SELECT * FROM master_ttidur WHERE kamar_id = ? ORDER BY no_ttidur ASC";
    }
    
    public String CariPasien(String Field) {
        return "SELECT * FROM regpasien WHERE " + Field + " LIKE ? ORDER BY nama ASC";
    }
    
    public String CariMasterKamar(String Field) {
        return "SELECT * FROM master_kamar WHERE " + Field + " LIKE ? ORDER BY tipekamar ASC";
    }
    
    public String MasterDokter() {
        return "SELECT * FROM master_petugasmedis";
    }
    
    public String MasterDiagnosa() {
        return "SELECT * FROM icd10";
    }
    
    public String MasterTipeKamar() {
        return "SELECT * FROM master_kamar";
    }
    
    public String MasterTempatTidur() {
        return "SELECT * FROM master_ttidur WHERE kamar_id = ? AND status = 1";
    }
    
    public String MasterTempatTidurX() {
        return "SELECT * FROM master_ttidur WHERE status = 1";
    }

    public String MasterKelasKamar() {
        return "SELECT * FROM masterkelaskamar WHERE kamar_id = ?";
    }
    
     public String MasterHargaKamar() {
        return "SELECT * FROM master_kamar WHERE kamar_id = ?";
    }
    
    public String MasterHargaKelasKamar() {
        return "SELECT * FROM masterkelaskamar WHERE kelas_id = ?";
    }
    
    public String Generate() {
        return "SELECT * FROM generate LIMIT 1";
    }
    
    public String MasterUser() {
        return "SELECT * FROM master_user";
    }   
}
