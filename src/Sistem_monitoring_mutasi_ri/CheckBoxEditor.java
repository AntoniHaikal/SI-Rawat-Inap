package Sistem_monitoring_mutasi_ri;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class CheckBoxEditor extends DefaultCellEditor implements ItemListener {

    private JCheckBox cb;

    public CheckBoxEditor(JCheckBox checkBox) {
        super(checkBox);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value == null) {
            return null;
        }
        cb = (JCheckBox) value;
        return (Component) value;
    }

    @Override
    public Object getCellEditorValue() {
        return cb;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}