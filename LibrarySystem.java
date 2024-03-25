/*
McKenna Pasquale
Software Development 1: 202420 - CEN - 3024C - 24667
Library System class - the main build of the buttons and functions in the GUI
 */




package pasquale3.library.model.view.controller;

import pasquale3.library.model.Book;
import pasquale3.library.model.Library;
import pasquale3.library.model.VIM;
import pasquale3.library.model.view.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.loadLibrary;
import static jdk.jfr.consumer.EventStream.openFile;

public class LibrarySystem implements ChangeListener, ActionListener {

    private LibraryInterface screen;
    private AddBookPanel abp;
    private BrowseLibraryPanel blp;
    private LoadScreen ls;

    private JFileChooser chooser;
    private FileFilter filter, filter2;
    private int resultCode;
    private File vimFile, saveFile, libFile;
    private Library lib;
    private Book book;
    private List<VIM> vimCache;
    private FileInputStream fis;
    private FileOutputStream fos;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String fileName;
    private boolean exit;
    private String[][] dataBook;
    private String[][] dataFile;
    private String[] validFileTypes = {".wav", ".mp3", ".avi", ".mp4", ".png", ".jpeg"};
    private String validFileTypeReminder;


    public LibrarySystem() {
        initEventAttributes();

        screen = new LibraryInterface("Library System");
        abp = screen.getAddBookPanel();
        blp = screen.getBrowseLibraryPanel();

        screen.getTabbedPane().addChangeListener(this);
        abp.addActionListener(this);
        blp.addActionListener(this);


        ls = new LoadScreen("Welcome");
        ls.addActionListener(this);
        ls.setVisible(true);


        screen.setVisible(true);



    }

    private void initEventAttributes() {
        chooser = new JFileChooser();
        filter = new FileNameExtensionFilter("Video/Image/Music Files",
                "wav", "mp3", "avi", "mp4", "png", "jpeg");
        filter2 = new FileNameExtensionFilter("Library Files", "ser");
        chooser.addChoosableFileFilter(filter);
        lib = new Library();
        exit = false;

        vimCache = new ArrayList<VIM>();
        vimFile = null;
        validFileTypeReminder = "Valid file types end with .wav, .mp3, .avi, .mp4, .png, .jpeg";


    }

    @Override //called when tab changes
    public void stateChanged(ChangeEvent e) {
        //if current tab is add book, and user click on browse library tab, this listener is called.
        if (screen.getTabbedPane().getSelectedIndex() == 1) {
            //means we just clicked on the browse library tab.
            screen.getTabbedPane().setTitleAt(1, screen.getFiller() + screen.getFiller() + " Browse Library ");
            screen.setSize(500, 440);

        }
        //if current tab is browse library and user clicks on add book tab, reset sizes to original
        else {
            screen.getTabbedPane().setTitleAt(1, screen.getFiller() + " Browse Library " + screen.getFiller());
            screen.setSize(400, 460);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        //abp
        if (e.getSource() == abp.getButtonBrowse()) {
            openChooserAndSetTheVIMFile();
        }

        else if (e.getSource() == abp.getButtonAddFile()) {
            addVimFileToVimCache();

        }

        else if (e.getSource() == abp.getButtonAddBook()) {
            addVimFilesInVimCacheToBookAndAddBookToLibrary();
            reloadDataBook();
            reloadDataFile();
        }

        else if (e.getSource() == abp.getButtonListAllBooks()) {
            listAllBooksInLibrary();
        }

        else if (e.getSource() == abp.getButtonSave()) {
            save();

        }

        else if (e.getSource() == abp.getButtonSaveAndQuit()) {
            saveAndQuit();

        }

        // blp
        else if (e.getSource() == blp.getButtonOpenBook()) {
            openBook();

        } else if (e.getSource() == blp.getButtonDeleteBook()) {

        } else if (e.getSource() == blp.getButtonOpenFile()) {
            openFiles();

        } else if (e.getSource() == blp.getButtonDeleteFile()) {

        } else if (e.getSource() == blp.getButtonSave()) {
            save();

        } else if (e.getSource() == blp.getButtonSaveAndQuit()) {
            saveAndQuit();
        } else if (e.getSource() == ls.getButtonLoad()) {
            ((MyTableModel) blp.getBookTable().getModel()).getDataVector().clear();
            ((MyTableModel) blp.getFileTable().getModel()).getDataVector().clear();
            loadLibrary();
            chooser.setFileFilter(filter);
            screen.setVisible(true);
           reloadDataBook();
            reloadDataFile();
            loadLibrary();
            chooser.setFileFilter(filter);
            screen.setVisible(true);

        } else if (e.getSource() == ls.getButtonNew()) {
            ls.dispose();
            screen.setVisible(true);

        } else if (e.getSource() == ls.getButtonExit()) {
            System.exit(0);

        } else  //SaveAndQuit in Browse Library Tab.
        {
            saveAndQuit();
        }


    }


    private void openFiles() {
        int row, column;
        VIM v;
        String fileName;
        File file;

        row = ((JTable) blp.getFileTable()).getSelectedRow();
        column = ((JTable) blp.getFileTable()).getSelectedColumn();
        fileName = ((JTable) blp.getFileTable()).getValueAt(row, column).toString();

        v = book.getVIMByName(fileName);
        try {
            file = new File(v.getName());
            fos = new FileOutputStream(file);
            fos.write(v.getData());
            fos.close();
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openBook() {
        int row, column;
        String isbn;


        row = ((JTable) blp.getBookTable()).getSelectedRow();
        column = 3; //ISBN column
        isbn = ((JTable) blp.getBookTable()).getValueAt(row, column).toString();
        book = lib.getBookByISBN(isbn);

        dataFile = book.toStringVectorFiles();
        reloadDataFile();

    }

    private void reloadDataBook() {
        ((MyTableModel) blp.getBookTable().getModel()).getDataVector().clear();
        while (((MyTableModel) blp.getBookTable().getModel()).getRowCount() > 0);{
            ((MyTableModel) blp.getBookTable().getModel()).removeRow(0);
        }
        dataBook = lib.toStringVector();
        for (int i = 0; i < dataBook.length; i++) {
            ((MyTableModel) blp.getBookTable().getModel()).addRow(dataBook[i]);
        }
    }
    private void reloadDataFile() {
        while (((MyTableModel) blp.getFileTable().getModel()).getRowCount() > 0);{
            ((MyTableModel) blp.getBookTable().getModel()).removeRow(0);
        }
        if (dataFile != null)
            for(int i=0; i<dataFile.length; i++){
                ((MyTableModel) blp.getFileTable().getModel()).insertRow(i, dataFile[i]);
            }


    }


    private void loadLibrary() {
        chooser.setFileFilter(filter2);
        resultCode = chooser.showOpenDialog(screen);
        if (resultCode == JFileChooser.APPROVE_OPTION) {
            libFile = chooser.getSelectedFile();
            try {
                fis = new FileInputStream(libFile);
                in = new ObjectInputStream(fis);
                lib = (Library) in.readObject();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ls.dispose();
            reloadDataBook();

            //load book table



        screen.setVisible(true);
    }
}




    private void saveAndQuit() {
        //save();
        //if(fileName != null){
       System.exit(0);
        if(exit)
            System.exit(0);
    }

    private void save() {
        String fileName = JOptionPane.showInputDialog(screen, "Enter file name to save as...", "Save", JOptionPane.INFORMATION_MESSAGE);
        if (fileName != null) {
            if(!fileName.trim().contentEquals("")){
            FileOutputStream fos = null;
            ObjectOutputStream out = null;
            try {
                saveFile = new File(fileName.trim());
                if(!saveFile.exists()) {
                    fos = new FileOutputStream(saveFile);
                    out = new ObjectOutputStream(fos);
                    out.writeObject(lib);
                    fos.close();
                    out.close();
                    exit = true;
                }
                else{
                    int resultCode = JOptionPane.showConfirmDialog(screen, "File with this name already exists, would you like to override it?", "Warning",
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if(resultCode == JOptionPane.YES_OPTION){
                        fos = new FileOutputStream(saveFile);
                        out = new ObjectOutputStream(fos);
                        out.writeObject(lib);
                        fos.close();
                        out.close();
                        exit = true;

                    }
                    else{
                        abp.getTextAreaLog().append("\n>  Save cancelled");
                        exit = false;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
            else{
                    abp.getTextAreaLog().append("\n>  Save cancelled.");
                }
        }
        else{
            abp.getTextAreaLog().append("\n>  Save cancelled.");
        }
    }

    private void listAllBooksInLibrary() {
        abp.getTextAreaLog().setText("> Listing all books in library . . .");
        abp.getTextAreaLog().append("   " + lib.toString());
    }

    private void addVimFilesInVimCacheToBookAndAddBookToLibrary() {
        //isbn must be a unique number,
        boolean IsbnAlreadyExist = false;
        boolean IsbnIsANumber = false;
        boolean PriceIsANumber = false;
        boolean AllFieldsAreFilled = false;
        int isbn = 0;
        double price = 0.0;
        Book b;

        if
                (!abp.getTextFieldISBN().getText().trim().contentEquals("") &&
                !abp.getTextFieldTitle().getText().trim().contentEquals("") &&
                !abp.getTextFieldAuthor().getText().trim().contentEquals("") &&
                !abp.getTextFieldPrice().getText().trim().contentEquals("")){
            AllFieldsAreFilled = true;

        }
        if(AllFieldsAreFilled) {
            try {
                isbn = Integer.parseInt(abp.getTextFieldISBN().getText().trim());
                price = Double.parseDouble(abp.getTextFieldPrice().getText().trim());
                IsbnAlreadyExist = lib.doesIsbnAlreadyExist(isbn);
                if (IsbnAlreadyExist) {
                    JOptionPane.showMessageDialog(screen, isbn
                            + " already exists!\nPlease use another ISBN!");
                }
                else{
                b = new Book(isbn, abp.getTextFieldTitle().getText().trim(),
                        abp.getTextFieldAuthor().getText().trim(), price);
                for (int i =0; i<vimCache.size(); i++){
                    b.addVIM(vimCache.get(i));
                }
                lib.addBook(b);
                //clear text after a book is successfully added to the library
                abp.getTextFieldISBN().setText("");
                abp.getTextFieldTitle().setText("");
                abp.getTextFieldAuthor().setText("");
                abp.getTextFieldPrice().setText("");
                abp.getTextAreaLog().append("\n>   " + b.getTitle() + " has been added to the library!" );

                vimCache = new ArrayList<VIM>();


            } }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(screen, "ISBN or Price is not a number!");
            }
        }
        else{
            JOptionPane.showMessageDialog(screen, "Please fill out all non-optional fields!");
        }


    }

    private void addVimFileToVimCache() {
        if(vimFile != null) {
            for (int i = 0; i < validFileTypes.length; i++) {
                if (abp.getTextFieldFile().getText().trim().endsWith(validFileTypes[i])) {
                    byte[] data = new byte[(int) vimFile.length()];
                    try {
                        fis = new FileInputStream(vimFile);
                        fis.read(data);
                        fis.close();
                    } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(screen, "File not found!");

                    } catch (IOException e) {
                        //"Error reading file!"
                        JOptionPane.showMessageDialog(screen, "Error reading file!");
                    }

                    VIM v = new VIM(abp.getTextFieldFile().getText().trim(), data);
                    vimCache.add(v);
                    vimFile = null;
                    abp.getTextAreaLog().append("\n>   " + abp.getTextFieldFile().getText().trim()
                            + " is ready to be added to book.");
                    abp.getTextFieldFile().setText("optional");
                    return;

                }

            }
            JOptionPane.showMessageDialog(screen, "Something went wrong, please try again!");


        }
        else{

            JOptionPane.showMessageDialog(screen, abp.getTextFieldFile().getText()
                    + " is not a valid file type!\n" + validFileTypeReminder);
        }
    }

    private void openChooserAndSetTheVIMFile() {
        resultCode = chooser.showOpenDialog(screen);
        if(resultCode == JFileChooser.APPROVE_OPTION){
            vimFile = chooser.getSelectedFile();
            abp.getTextFieldFile().setText(vimFile.getName());


        }

         }


}
