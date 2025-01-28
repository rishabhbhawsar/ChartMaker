import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Run the application UI in the event-dispatching thread
        SwingUtilities.invokeLater(() -> {
            new ChartMaker().showUI();
        });
    }
}
