package systint.client;
import java.io.*;
import java.net.*;
import java.util.Scanner;

// CHANGE <default> TO YOUR OWN GROUP NUMBER IN THE CODE BELOW

public class ChatClient {
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	Scanner keyboard_in;

	ChatClient() {
	}

	void run() {
		try {
			// 1. creating a socket to connect to the server
			requestSocket = new Socket("localhost", 2013);
			System.out.println("This is Chat Client : Group_12");
			System.out.println("Connected to Chat Server Group_12 on port 2013");

			// 2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
            // listener at the port 2013
			in = new ObjectInputStream(requestSocket.getInputStream());
			// listen for inputs from keyboard
			keyboard_in = new Scanner(System.in);

			// 3. send group number to server
			sendMessage("Group_12");

			// 4. Communicating with the server
			do {
				try {
					System.out.print("Group Group_12: ");
					message = keyboard_in.nextLine();
					sendMessage(message);

                    // replyMessage from the Server, port 2013
					String replyMessage = (String) in.readObject();
					System.out.println("Server: " + replyMessage);
				} catch (ClassNotFoundException classNot) {
					System.err.println("data received in unknown format");
				}
			} while (!message.equals("bye"));

		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (ConnectException e) {
			System.err.println("Chat server is not running. Start the server then try again.");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 5. Closing connection
			try {
				if (requestSocket != null) {
					in.close();
					out.close();
					requestSocket.close();
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ChatClient client = new ChatClient();
		if (client != null)
			client.run();
	}
}
