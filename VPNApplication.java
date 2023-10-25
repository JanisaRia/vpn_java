import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VPNApplication extends JFrame implements ActionListener {
    private JLabel statusLabel;
    private boolean isConnected = false; // Track connection status
    private String vpnServerAddress = "vpn.example.com"; // Default VPN server address

    public VPNApplication() {
        setTitle("VPN Application");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(this);
        panel.add(connectButton);

        JButton disconnectButton = new JButton("Disconnect");
        disconnectButton.addActionListener(this);
        panel.add(disconnectButton);

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(this);
        panel.add(settingsButton);

        JButton defaultServerButton = new JButton("Connect to Default Server");
        defaultServerButton.addActionListener(this);
        panel.add(defaultServerButton);

        statusLabel = new JLabel("Status: Disconnected");
        panel.add(statusLabel);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("Connect") && !isConnected) {
            setStatus("Connecting to VPN server " + vpnServerAddress + " ...");
            simulateConnection();
            System.out.println("Connecting.......");
        }

        else if (actionCommand.equals("Disconnect") && isConnected) {
            setStatus("Disconnecting from VPN...");
            simulateDisconnection();
            System.out.println("Disconnecting.......");
        }

        else if (actionCommand.equals("Settings")) {
            showSettingsDialog();
        }

        else if (actionCommand.equals("Connect to Default Server") && !isConnected) {
            connectToDefaultServer();
        }
    }

    private void setStatus(String status) {
        statusLabel.setText("Status: " + status);
    }

    private void simulateConnection() {
        // Simulate a connection delay
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                try {
                    Thread.sleep(2000); // Sleep for 2 seconds to simulate the connection process
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void done() {
                setStatus("Connected");
                isConnected = true;
            }
        }.execute();
    }

    private void simulateDisconnection() {
        // Simulate a disconnection delay
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                try {
                    Thread.sleep(1000); // Sleep for 1 second to simulate the disconnection process
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void done() {
                setStatus("Disconnected");
                isConnected = false;
            }
        }.execute();
    }

    private void showSettingsDialog() {
        // Create a settings dialog
        JTextField serverAddressField = new JTextField(vpnServerAddress, 20);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(new JLabel("Server Address:"));
        panel.add(serverAddressField);

        int result = JOptionPane.showConfirmDialog(this, panel, "VPN Settings", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            vpnServerAddress = serverAddressField.getText();
            JOptionPane.showMessageDialog(this, "Settings updated. New Server Address: " + vpnServerAddress);
        }
    }

    private void connectToDefaultServer() {
        setStatus("Connecting to VPN server " + vpnServerAddress + " ...");
        simulateConnection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VPNApplication app = new VPNApplication();
                app.setVisible(true);
            }
        });
    }
}
