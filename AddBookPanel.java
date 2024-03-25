/*
McKenna Pasquale
Software Development 1: 202420 - CEN - 3024C - 24667
AddBookPanel class - creates the overall layout and placement of buttons on the first tab on the GUI; including action listeners and resizing when the first tab is clicked again.
 */

package pasquale3.library.model.view;

import pasquale3.library.model.view.controller.LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class AddBookPanel extends JPanel {



    private Box mainBox, hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7, hBox8; //adding all buttons and widgets into these boxes
    //widgets
    private JLabel jlISBN, jlTitle, jlAuthor, jlPrice, jlFile, jlLogDog;
    private JTextField jtISBN, jtTitle, jtAuthor, jtPrice, jtFile;
    private JButton bBrowse, bAddFile, bAddBook, bSave, bSaveAndQuit, bListAllBooks;
    private JTextArea jtaLog;
    private JScrollPane scrollPane;

    public AddBookPanel() {
        super(new FlowLayout());//extending jpanel

        initWidgets(); //initialize the widgets

        addWidgets(); //add the widgets

        setBackground(new Color(194,230,248)); //create background color

    }

    private void initWidgets(){
        mainBox = Box.createVerticalBox();//adding stuff from the top to the bottom
        //adding stuff from left to right
        hBox1 = Box.createHorizontalBox();
        hBox2 = Box.createHorizontalBox();
        hBox3 = Box.createHorizontalBox();
        hBox4 = Box.createHorizontalBox();
        hBox5 = Box.createHorizontalBox();
        hBox6 = Box.createHorizontalBox();
        hBox7 = Box.createHorizontalBox();
        hBox8 = Box.createHorizontalBox();


        jlISBN = new JLabel(" Enter ISBN:     ");
        jlTitle = new JLabel(" Enter Title:      ");
        jlAuthor = new JLabel(" Enter Author:  ");
        jlPrice = new JLabel(" Enter Price:     ");
        jlFile = new JLabel(" Enter File:       ");
        jlLogDog = new JLabel(" LogDog   ");



        jtISBN = new JTextField(19);
        jtTitle = new JTextField(19);
        jtAuthor = new JTextField(19);
        jtPrice = new JTextField(19);
        jtFile = new JTextField(19);
        jtFile.setText("Optional");


        //make sure text is entered on the right side of the box.
        jtISBN.setHorizontalAlignment(JTextField.RIGHT);
        jtTitle.setHorizontalAlignment(JTextField.RIGHT);
        jtAuthor.setHorizontalAlignment(JTextField.RIGHT);
        jtPrice.setHorizontalAlignment(JTextField.RIGHT);
        jtFile.setHorizontalAlignment(JTextField.RIGHT);

        bBrowse = new JButton(" Browse");
        bAddFile = new JButton(" Add File To Book");
        bAddBook = new JButton(" Add Book To Library");
        bSave = new JButton(" Save");
        bSaveAndQuit = new JButton(" Save & Quit");
        bListAllBooks = new JButton("List All Books");

        jtaLog = new JTextArea(10,24);
        jtaLog.setEditable(false);//don't allow people to edit what is entered here
        scrollPane = new JScrollPane(jtaLog);
        //always show scrollbar
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy((JScrollPane.VERTICAL_SCROLLBAR_ALWAYS));


    }

    private void addWidgets(){
        //assigning widget to box space
        hBox1.add(jlISBN);
        hBox1.add(jtISBN);
        hBox2.add(jlTitle);
        hBox2.add(jtTitle);
        hBox3.add(jlAuthor);
        hBox3.add(jtAuthor);
        hBox4.add(jlPrice);
        hBox4.add(jtPrice);
        hBox5.add(jlFile);
        hBox5.add(jtFile);
        hBox6.add(Box.createHorizontalStrut(83));
        hBox6.add(bBrowse);
        hBox6.add(Box.createHorizontalStrut(5)); //struts = invisible spaces
        hBox6.add(bAddFile);
        hBox7.add(jlLogDog);
        hBox7.add(Box.createHorizontalStrut(99));
        hBox7.add(bAddBook);
        hBox8.add(bListAllBooks);
        hBox6.add(Box.createHorizontalStrut(5));
        hBox8.add(bSave);
        hBox8.add(Box.createHorizontalStrut(20));
        hBox8.add(bSaveAndQuit);

        //adding everything to main box
        mainBox.add(hBox1);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox2);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox3);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox4);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox5);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox6);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(hBox7);
        mainBox.add(Box.createVerticalStrut(5));
        mainBox.add(scrollPane);
        mainBox.add(hBox8);

        //add main box
        add(mainBox);



    }


    public void addActionListener(ActionListener a) {
        bBrowse.addActionListener(a);
        bAddFile.addActionListener(a);
        bAddBook.addActionListener(a);
        bSave.addActionListener(a);
        bSaveAndQuit.addActionListener(a);
        bListAllBooks.addActionListener(a);
    }

    //getters
    public JButton getButtonBrowse(){
        return bBrowse;
    }
    public JButton getButtonAddFile(){
        return bAddFile;
    }
    public JButton getButtonAddBook(){
        return bAddBook;
    }
    public JButton getButtonSave(){
        return bSave;
    }
    public JButton getButtonSaveAndQuit(){
        return bSaveAndQuit;
    }
    public JButton getButtonListAllBooks(){
        return bListAllBooks;
    }

    public JTextField getTextFieldISBN(){
        return jtISBN;
    }
    public JTextField getTextFieldTitle(){
        return jtTitle;
    }
    public JTextField getTextFieldAuthor(){
        return jtAuthor;
    }
    public JTextField getTextFieldPrice(){
        return jtPrice;
    }
    public JTextField getTextFieldFile(){
        return jtFile;
    }

    public JTextArea getTextAreaLog(){
        return jtaLog;
    }

}
