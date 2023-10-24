import java.awt.*;
import java.applet.*;

public class VPNApplication extends Applet {
    private String selectedNetwork;

    public void init() {
        // Initialize the applet
        selectedNetwork = "";
        // Add network selection options
        Choice networkChoice = new Choice();
        networkChoice.add("Network 1");
        networkChoice.add("Network 2");
        networkChoice.add("Network 3");
        networkChoice.addItemListener(e -> {
            selectedNetwork = networkChoice.getSelectedItem();
            repaint();
        });
        add(networkChoice);
    }

    public void paint(Graphics g) {
        // Display the selected network
        g.drawString("Selected Network: " + selectedNetwork, 10, 50);
    }
}
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class VPNApplication {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("VPN server started on port 8080");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Handle client connection
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Handle client request and response
            // Your implementation here
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
