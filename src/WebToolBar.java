import java.awt.event.*;
import java.net.*;

import javax.swing.*;
import javax.swing.event.*;

public class WebToolBar extends JToolBar
    implements HyperlinkListener {
    private WebBrowserPane webBrowserPane;
    private JButton backButton;
    private JButton forwardButton;
    private JTextField urlTextField;

    // WebToolBar Constructor
    public WebToolBar(WebBrowserPane browser){
        super( "Web Navigator ");

        //register for hyperlink event
        webBrowserPane = browser;
        webBrowserPane.addHyperlinkListener( this );

        //create JTextField for entering URLs
        urlTextField = new JTextField( 25);
        urlTextField.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            URL url = new URL(urlTextField.getText());
                            webBrowserPane.gotoUrl(url);
                        }
                        catch (MalformedURLException urlException){
                            urlException.printStackTrace();
                        }
                    }
                }
        );

        backButton = new JButton();
        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        URL url = webBrowserPane.back();
                        urlTextField.setText(url.toString());
                    }
                }
        );

        forwardButton = new JButton();
        forwardButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        URL url = webBrowserPane.forward();
                        urlTextField.setText(url.toString());
                    }
                }
        );

        add (backButton);
        add (forwardButton);
        add (urlTextField);
    }


    public void hyperlinkUpdate(HyperlinkEvent event) {
        if  ( event.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
            URL url = event.getURL();
            webBrowserPane.gotoUrl( url );
            urlTextField.setText( url.toString());
        }
    }
}
