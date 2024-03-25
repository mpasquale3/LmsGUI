/*
McKenna Pasquale
Software Development 1: 202420 - CEN - 3024C - 24667
LibraryInterface class - adding the two tabs to the layout and assigning variables to them.
 */

package pasquale3.library.model.view;
import javax.swing.*;

public class LibraryInterface extends JFrame{
    //putting two panels


    private AddBookPanel abp;
    private BrowseLibraryPanel blp;
    private JTabbedPane jtp;
    private String filler;


    public LibraryInterface(String title){
        super(title); //because we're extending jFrame, need to use super instead of this
        //this.blp = blp;


        jtp = new JTabbedPane();
        abp = new AddBookPanel();
       blp = new BrowseLibraryPanel();

        filler = "      ";//6 spaces

        //add panel into pane
        jtp.addTab(filler + " Add Book " + filler, abp);
        jtp.addTab(filler + " Browse Library " + filler, blp);
        //add to JFrame
        add(jtp);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 460); //usually not sure what the size is going to be, use trial and error to decide overall size of GUI //320x460
        //pack();//automatically changes size to fit the screen so everything shows on the screen.
        setResizable(false); //users cannot resize the GUI


    }

    public AddBookPanel getAddBookPanel(){
        return abp;

    }

  public BrowseLibraryPanel getBrowseLibraryPanel(){

      return blp;
            }

    public JTabbedPane getTabbedPane(){
        return jtp;
    }

    public String getFiller(){
        return filler;
    }



}
