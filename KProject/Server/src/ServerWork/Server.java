package ServerWork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException
    {
        ServerSocket serverSocket = new ServerSocket(3320);
        try
        {
            System.out.println("\n----- Сервер запущен -----");
            while(true)
            {

                Socket clientSocket = serverSocket.accept();
                System.out.println("good");
                try
                {
                    new ThreadServer(clientSocket);
                }
                catch (Exception e)
                {
                    clientSocket.close();
                }
            }
        }
        finally
        {
            serverSocket.close();
        }
    }
}
