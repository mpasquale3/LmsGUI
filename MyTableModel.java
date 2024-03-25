/*
McKenna Pasquale
Software Development 1: 202420 - CEN - 3024C - 24667
MyTableModel class - creates the table in which books are listed in and you can then edit/delete/open files that are shown after clicking on them.
 */

package pasquale3.library.model.view;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

    public MyTableModel(String[][] data, String[] columns){
        super(data, columns);

    }


    //cells in Browse Library are not editable by users
        @Override
    public boolean isCellEditable(int row, int column) {
        return false; //super.isCellEditable(row, column);
    }
}
