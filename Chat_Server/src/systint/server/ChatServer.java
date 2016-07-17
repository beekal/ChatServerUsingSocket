package systint.server;
import java.io.*;
import java.net.*;

// CHANGE <default> TO YOUR OWN GROUP NUMBER IN THE CODE BELOW

public class ChatServer {
	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;

	ChatServer() {
	}

	void run() {
		try {
			// 1. creating a server socket
			providerSocket = new ServerSocket(2013, 1);

			// 2. Wait for connection
			System.out.println("This is Chat Server Group 12");
			System.out.println("Waiting for a group to connect");
			// wait until new connection at socket is provided
			connection = providerSocket.accept();

			// 3. get Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());

			// 4. get group number
			String groupId = (String) in.readObject();
			System.out.print("Connection received from Group " + groupId + " at " + connection.getInetAddress().getHostName());

			// 4. The two parts communicate via the input and output streams
			do {
				try {
					message = (String) in.readObject();
					System.out.println();
					System.out.println("Group " + groupId + ": " + message);

					sendMessage("You said \"" + message + "\"");

					if (message.equals("bye")){
						System.out.println();
						System.out.println("Ending conversation with Group Group 12.");
					}

				} catch (ClassNotFoundException e) {
					System.err.println("Data received in unknown format");
				}
			} while (!message.equals("bye"));

		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("Data received in unknown format");
		}
		finally {

			// 4: Closing connection
			try {
				in.close();
				out.close();
				providerSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.print("Server: " + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ChatServer server = new ChatServer();
		while (true) {
			server.run();
		}
	}
}
