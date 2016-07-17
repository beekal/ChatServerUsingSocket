# ChatServerUsingSocket

The client sends messages to the server by typing them on the keyboard. 

The server will respond to each message automatically. 

To end the conversation, the client types “bye”.

To compile the code after making changes

You can choose to compile the code in any IDE or Java compiler. The instructions below can be used to compile the code for the chat server using the command line in Windows. The instructions can be used to compile the code for the chat client by replacing “server” with “client”.

1.	Start the command prompt: Start -> Run… -> type “cmd”

2.	Go to the directory where you have the server: 

cd <Chat_Server_Directory>

3.	Run the command:

javac -d bin -sourcepath src src/systint/server/ChatServer.java


To run the example

After making the required changes to the source code and compiling it, you need to run the example. The instructions below are for running the chat server. To run the client, replace every occurrence of “server” with “client”. 

1.	Start the command prompt: Start -> Run… -> type “cmd” 

2.	Go to the bin directory in the directory where you have the server:

cd <Chat_Server_Directory>/bin

3.	Run the server using the command:

java –cp . systint.server.ChatServer

PS : Project done as a part of System Integration Course Assignment at Stockholm University
