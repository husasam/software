package MySystem;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SalesTableModel extends AbstractTableModel {
    private ResultSet rs;

    public SalesTableModel(ResultSet rs) {
        this.rs = rs;
        try {
            rs.beforeFirst(); // Reset the cursor position
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        try {
            rs.beforeFirst(); // Move cursor to the beginning
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        try {
            return rs.getMetaData().getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            rs.absolute(rowIndex + 1); // Move cursor to the specific row
            return rs.getObject(columnIndex + 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}