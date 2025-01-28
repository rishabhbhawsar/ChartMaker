import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartMaker {
    private JFrame frame;
    private JTextField xInput, yInput;
    private JTextArea dataArea;

    public void showUI() {
        // Create the main frame
        frame = new JFrame("Chart Maker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel with GridLayout for the UI
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel xLabel = new JLabel("Enter X value");
        JLabel yLabel = new JLabel("Enter Y value");
        xInput = new JTextField();
        yInput = new JTextField();
        JButton addButton = new JButton("Add Data");
        dataArea = new JTextArea();
        JButton generateChart = new JButton("Generate Chart");

        // Add Action listeners for buttons
        addButton.addActionListener(e -> addData());
        generateChart.addActionListener(e -> generateChart());

        // Add components to the panel
        panel.add(xLabel);
        panel.add(xInput);
        panel.add(yLabel);
        panel.add(yInput);
        panel.add(addButton);
        panel.add(new JScrollPane(dataArea));
        panel.add(new JLabel("")); // Empty Space
        panel.add(generateChart);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

    private void addData() {
        // Get data from text fields and add to the data area
        String x = xInput.getText();
        String y = yInput.getText();
        if (!x.isEmpty() && !y.isEmpty()) {
            dataArea.append(x + "," + y + "\n");
            xInput.setText("");
            yInput.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter both X and Y values.");
        }
    }

    private void generateChart() {
        // Parse data from the data area and create a dataset
        String[] lines = dataArea.getText().split("\n");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String line : lines) {
            String[] values = line.split(",");
            if (values.length == 2) {
                try {
                    String x = values[0];
                    double y = Double.parseDouble(values[1]);
                    dataset.addValue(y, "Values", x);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Invalid data format. Ensure Y is numeric.");
                    return;
                }
            }
        }

        // Create a bar chart
        JFreeChart chart = ChartFactory.createBarChart(
            "User Chart", "X Values", "Y Values", dataset, PlotOrientation.VERTICAL, false, true, false
        );

        // Display the chart in a new window
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame chartFrame = new JFrame("Generated Chart");
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.add(chartPanel);
        chartFrame.pack();
        chartFrame.setVisible(true);
    }
}
