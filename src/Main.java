import java.awt.*;
import java.awt.event.*;
import java.net.*;

import javax.swing.*;
import javax.swing.event.*;


public class Main extends JFrame{
    private WebToolBar toolBar;
    private WebBrowserPane browserPane;

    private Main(){
        super( "CEO WEB BROWSER" );

        browserPane = new WebBrowserPane();
        toolBar = new WebToolBar(browserPane);

        Container contentPane = getContentPane();
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(browserPane), BorderLayout.CENTER);
    }

    public static void main (String[] args){
        Main browser = new Main();
        browser.setDefaultCloseOperation(EXIT_ON_CLOSE);
        browser.setVisible(true);
    }
}