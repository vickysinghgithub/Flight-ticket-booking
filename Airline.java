import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Date;
import java.io.*;

public class Airline {
    private JFrame frame;
    private JPanel airPanel;
    private JTextField nameData;
    private JComboBox<String> sourceComboBox;
    private JComboBox<String> destinationComboBox;
    private JSpinner spinner1;
    private JLabel fare;

    public Airline() {
        createGUI();     
    }

    private void createGUI() {
        frame = new JFrame("Booking Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        airPanel = new JPanel();
        airPanel.setLayout(new GridLayout(6, 3, 10, 10));
        frame.setVisible(true);

        airPanel.add(new JLabel("Name: "));
        nameData = new JTextField(20);
        airPanel.add(nameData);

        airPanel.add(new JLabel("Source: "));
        String[] sourceList = {"Delhi", "Mumbai", "Patna", "Banglore", "Bhubaneswar"};
        sourceComboBox = new JComboBox<>(sourceList);
        airPanel.add(sourceComboBox);

        airPanel.add(new JLabel("Destination: "));
        String[] destinationList = {"Delhi", "Mumbai", "Patna", "Banglore", "Bhubaneswar"};
        destinationComboBox = new JComboBox<>(destinationList);
        airPanel.add(destinationComboBox);
        destinationComboBox.setSelectedIndex(1);

        airPanel.add(new JLabel("Date of Journey: "));
        spinner1 = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner1, "dd/MM/yyyy");
        spinner1.setEditor(editor);
        airPanel.add(spinner1);

        airPanel.add(new JLabel("Fare: "));
        fare = new JLabel("");
        airPanel.add(fare);

        JButton bookButton = new JButton("Book");
        JButton resetButton = new JButton("Reset");

        bookButton.addActionListener(e -> bookFlight());
        resetButton.addActionListener(e -> resetFields());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bookButton);
        buttonPanel.add(resetButton);

        frame.getContentPane().add(airPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    
    private Object resetFields() {
        nameData.setText("");
        sourceComboBox.setSelectedIndex(0);
        destinationComboBox.setSelectedIndex(1);
        spinner1.setValue(new Date());
        fare.setText("");
        return null;
    }

    private void bookFlight()
    {
        Date date = (Date) spinner1.getValue();
        int fareAmount ;          
        String source = (String) sourceComboBox.getSelectedItem();
        String destination = (String) destinationComboBox.getSelectedItem();

    if (source.equals(destination)) {
        JOptionPane.showMessageDialog(frame, "Cannot book ticket with the same source and destination.");
        return;
    }
    
             System.out.println("Booking flight for " + nameData.getText() +
             " from " + source + " to " + destination +
             " on " + date.toString());
        
    if (source.equals("Delhi") && destination.equals("Mumbai")) {
        fareAmount = 2000;
    } else if (source.equals("Mumbai") && destination.equals("Delhi")) {
        fareAmount = 2600;
    } else if (source.equals("Delhi") && destination.equals("Patna")) {
        fareAmount = 1500;
    } else if (source.equals("Patna") && destination.equals("Delhi")) {
        fareAmount = 2000;
    } else if (source.equals("Mumbai") && destination.equals("Patna")) {
        fareAmount = 3000;
    } else if (source.equals("Patna") && destination.equals("Mumbai")) {
        fareAmount = 3500;
    } else if (source.equals("Delhi") && destination.equals("Banglore")) {
        fareAmount = 4000;
    } else if (source.equals("Banglore") && destination.equals("Delhi")) {
        fareAmount = 4500;
    } else if (source.equals("Mumbai") && destination.equals("Bhubaneswar")) {
        fareAmount = 1500;
    } else if (source.equals("Bhubaneswar") && destination.equals("Mumbai")) {
        fareAmount = 2000;
    } else {
        fareAmount = 2500; 
    }


        NumberFormat format = NumberFormat.getCurrencyInstance();
        String fareString = format.format(fareAmount);
        JOptionPane.showMessageDialog(frame, "The fare for the selected journey is " + fareString);
        fare.setText(fareString);
    
        String ticketDetails = "Name: " + nameData.getText() + "\n"
                + "Source: " + sourceComboBox.getSelectedItem() + "\n"
                + "Destination: " + destinationComboBox.getSelectedItem() + "\n"
                + "Date of Journey: " + date.toString() + "\n"
                + "Fare: " + format.format(fareAmount) + "\n\n";

    
        try {
            FileWriter writer = new FileWriter("booked_tickets.txt",true);
            writer.write(ticketDetails);
writer.close();
JOptionPane.showMessageDialog(frame, "Ticket booked successfully!");
} catch (IOException ex) {
JOptionPane.showMessageDialog(frame,"An error occurred while booking the ticket.");
ex.printStackTrace();
}
}
public static void main(String[] args)
{
    new Airline();
}
}