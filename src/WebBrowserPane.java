import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;

public class WebBrowserPane extends JEditorPane {
    private List<URL> history = new ArrayList<>();
    private int historyIndex = -1; // Start with no history

    public WebBrowserPane() {
        // Disable editing to enable hyperlinks
        setEditable(false);
    }

    // Display the given URL and add it to history
    public void gotoUrl(URL url) {
        displayPage(url);
        if (historyIndex < history.size() - 1) {
            history = history.subList(0, historyIndex + 1);
        }
        history.add(url);
        historyIndex = history.size() - 1;
    }

    // Display the next history URL in the editorPane
    public URL forward() {
        if (historyIndex < history.size() - 1) {
            historyIndex++;
            URL url = history.get(historyIndex);
            displayPage(url);
            return url;
        }
        return null; // No forward history available
    }

    // Display the previous history URL in the editorPane
    public URL back() {
        if (historyIndex > 0) {
            historyIndex--;
            URL url = history.get(historyIndex);
            displayPage(url);
            return url;
        }
        return null; // No back history available
    }

    public void displayPage(URL pageUrl) {
        try {
            setPage(pageUrl);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
