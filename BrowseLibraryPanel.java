/*
McKenna Pasquale
Software Development 1: 202420 - CEN - 3024C - 24667
BrowseLibaryPanel class - second tab of the GUI, includes the overall layout and variables for it. Includes action listener to resize GUI when this tab is clicked.
 */



package pasquale3.library.model.view;

import pasquale3.library.model.view.controller.LibrarySystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class BrowseLibraryPanel extends JPanel {

    private Box mainBox, hBox1, hBox2, hBox3, hBox4, hBox5;
    private JLabel jlBookTable, jlFileTable;
    private JButton bOpenBook, bDeleteBook, bOpenFile, bDeleteFile, bSave, bSaveAndQuit;
    private JTable tBooks, tFiles;
    private DefaultTableModel model;
    private JScrollPane spBookTable, spFileTable;

    String[] bookColumns = {"Title", "Author", "Price", "Serial Number"};
    String[] fileColumns = {"Sounds", "Images", "Videos"};
    String[][]  bookData = { {"", "", "", ""}};//array of arrays-rows
    String[][] fileData = { {"", "", ""}}; //array of arrays-columns

    public BrowseLibraryPanel(){

        super(new FlowLayout());
        intWidgets();
        addWidgets();
        setBackground(new Color(194,230,248)); //create background color
    }



    private void addWidgets() {


        hBox1.add(jlBookTable);
        hBox1.add(Box.createHorizontalStrut(50));
        hBox1.add(bDeleteBook);
        hBox1.add(bOpenBook);

        hBox2.add(spBookTable);

        hBox3.add(jlFileTable);
        hBox3.add(Box.createHorizontalStrut(65));
        hBox3.add(bDeleteFile);
        hBox3.add(bOpenFile);

        hBox4.add(spFileTable);

        hBox5.add(Box.createHorizontalStrut(185));
        hBox5.add(bSave);
        hBox5.add(Box.createHorizontalStrut(5));
        hBox5.add(bSaveAndQuit);

        mainBox.add(hBox1);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox2);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox3);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox4);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox5);

        add(mainBox);



    }

    private void intWidgets() {

        mainBox = Box.createVerticalBox();//adding stuff from the top to the bottom
        //adding stuff from left to right
        hBox1 = Box.createHorizontalBox();
        hBox2 = Box.createHorizontalBox();
        hBox3 = Box.createHorizontalBox();
        hBox4 = Box.createHorizontalBox();
        hBox5 = Box.createHorizontalBox();

        jlBookTable = new JLabel("Showing All Books In Library");


        jlFileTable = new JLabel("Showing All Files In Book");
        jlFileTable = new JLabel("Showing all Books currently checked out");


        bOpenBook = new JButton("Open Book");
        bDeleteBook = new JButton("Delete Book");
        bOpenFile = new JButton("Open File");
        bDeleteFile = new JButton("Delete File");
        bSave = new JButton("Save");
        bSaveAndQuit = new JButton("Save and Exit");

        //instantiate book table
        model = new MyTableModel(bookData, bookColumns); //create new table
        tBooks = new JTable(model); //make the new table, this model
        tBooks.setPreferredScrollableViewportSize(new Dimension(328,120));
        tBooks.setFillsViewportHeight(true);
        tBooks.getColumnModel().getColumn(0).setPreferredWidth(200);//making title column a certain size
        tBooks.getColumnModel().getColumn(1).setPreferredWidth(150);//making author column a certain size
        tBooks.setAutoCreateRowSorter(true); //makes column header clickable and gives a default row filter/sorter
        //override default row sorter/filter
        tBooks.getTableHeader().setReorderingAllowed(false);//don't allow users to adjust the column size/order

        //instantiate file table
        model = new MyTableModel(fileData, fileColumns); //create new table
        tFiles = new JTable(model); //assign the new table to this model
        tFiles.setPreferredScrollableViewportSize(new Dimension(328,80));
        tFiles.setFillsViewportHeight(true);
        tFiles.getColumnModel().getColumn(0).setPreferredWidth(200);//making title column a certain size
        tFiles.getColumnModel().getColumn(1).setPreferredWidth(150);//making author column a certain size
        tFiles.setAutoCreateRowSorter(true); //makes column header clickable and gives a default row filter/sorter
        //override default row sorter/filter
        tFiles.getTableHeader().setReorderingAllowed(false);//don't allow users to adjust the column size/order

        //instantiate scroll panes
        spBookTable = new JScrollPane(tBooks);
        spFileTable = new JScrollPane(tFiles);
        spBookTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        spBookTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        spFileTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        spFileTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


    }




    public void addActionListener(ActionListener a) {
        bOpenBook.addActionListener(a);
        bDeleteBook.addActionListener(a);
        bDeleteFile.addActionListener(a);
        bSave.addActionListener(a);
        bSaveAndQuit.addActionListener(a);

    }

    //getters
    public JButton getButtonOpenBook(){
        return bOpenBook;
    }
    public JButton getButtonDeleteBook() {
        return bDeleteBook;
    }
    public JButton getButtonOpenFile() {
        return bOpenFile;
    }
    public JButton getButtonDeleteFile() {
        return bDeleteFile;
    }
    public JButton getButtonSave() {
        return bSave;
    }
    public JButton getButtonSaveAndQuit() {
        return bSaveAndQuit;
    }


    public JTable getBookTable() {
        return tBooks;
    }

    public JTable getFileTable() {
        return tFiles;
    }
}



