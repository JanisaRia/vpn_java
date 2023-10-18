import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VPNApplication extends JFrame implements ActionListener {
    private JLabel statusLabel;
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

        statusLabel = new JLabel("Status: Disconnected");
        panel.add(statusLabel);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("Connect")) {            
            System.out.println("Connecting to VPN...");
            statusLabel.setText("Status: Connected");
        } 
        
        else if (actionCommand.equals("Disconnect")) {
            System.out.println("Disconnecting from VPN...");
            statusLabel.setText("Status: Disconnected");
        }
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
