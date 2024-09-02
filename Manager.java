import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Manager {
    private static final int PORT = 8080;
    private static final String SERVER_IP = "";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, PORT);
        System.out.println("Connected to server: " + socket.getInetAddress());


        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Prompt the user for a command
            System.out.println("Enter a command to send to the server (enter 'quit' to exit):");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("quit")) {
                break;
            }

            // Send the command to the server
            output.println(command);

            // Read the server's response
            String response = input.readLine();
            while (response != null){
                System.out.println("Server response: " + response);
                response = input.readLine();
            }


        }

        input.close();
        output.close();
        socket.close();
        scanner.close();
    }
}
