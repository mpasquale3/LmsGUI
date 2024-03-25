/*
McKenna Pasquale
Software Development 1: 202420 - CEN - 3024C - 24667
LoadScreen class- building of the frame, labels, buttons and variables that belong to them.
 */

package pasquale3.library.model.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoadScreen extends JFrame {

    private JPanel panel;
    private JLabel jlMessage;
    private JButton jbNew, jbLoad, jbExit;

    public LoadScreen(String title) {
        
        super(title);
        
        initWidgets();
        addWidgets();
        
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(320,115);
        setResizable(false);
    }

    private void addWidgets() {
        panel.add(jlMessage);
        panel.add(jbLoad);
        panel.add(jbNew);
        panel.add(jbExit);
        panel.setBackground(new Color(194,230,248));
        setContentPane(panel);


    }

    private void initWidgets() {

        panel = new JPanel(new FlowLayout());
        jlMessage = new JLabel("Would you like to start a new library? Or load one up?");

        jbNew = new JButton("Start new library");
        jbLoad = new JButton("Load saved library");
        jbExit = new JButton("Exit");

    }

    public void addActionListener(ActionListener l){
        jbNew.addActionListener(l);
        jbLoad.addActionListener(l);
        jbExit.addActionListener(l);
    }

    public JButton getButtonLoad(){
        return jbLoad;
    }

    public JButton getButtonNew(){
        return jbNew;

    }

    public JButton getButtonExit(){
        return jbExit;

    }
}
