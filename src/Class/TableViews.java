package Class;

import javax.swing.JTable;

public class TableViews {
    
    public javax.swing.table.DefaultTableModel getDefaultTableModel(Object[] column, final int[] editedRow, final int[] editedColumn, Object[][] obj) {
        return new javax.swing.table.DefaultTableModel(obj, column) {

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean is = false;
                if (editedRow != null && editedColumn != null) {
                    for (int x = 0; x < editedRow.length; x++) {
                        for (int y = 0; y < editedColumn.length; y++) {
                            if (rowIndex == editedRow[x] && colIndex == editedColumn[y]) {
                                is = true;
                            }
                        }
                    }
                } else if (editedRow == null && editedColumn != null) {
                    for (int y = 0; y < editedColumn.length; y++) {
                        if (colIndex == editedColumn[y]) {
                            is = true;
                        }
                    }
                } else if (editedRow != null && editedColumn == null) {
                    for (int x = 0; x < editedRow.length; x++) {
                        if (rowIndex == editedRow[x]) {
                            is = true;
                        }
                    }
                }
                return is;
            }
        };
    }
    
    public void table(javax.swing.JTable tbl, int width[]) {        
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int column = tbl.getColumnCount();
        for (int x = 0; x < column; x++) {
            javax.swing.table.TableColumn tbc = tbl.getColumnModel().getColumn(x);
            tbc.setPreferredWidth(width[x]);
            tbl.setRowHeight(25);
            if (column - 1 == x) {
                tbc.setResizable(false);
            }
        }
    }
    
}
