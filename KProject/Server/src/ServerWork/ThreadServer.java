package ServerWork;

import Database.Database;

import java.net.Socket;
import java.io.*;
import java.sql.SQLException;

public class ThreadServer extends Thread
{
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    Socket clientSocket;
    Database database;

    public ThreadServer(Socket clientSocket)throws IOException, ClassNotFoundException
    {
        this.clientSocket = clientSocket;
        bufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        printWriter = new PrintWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()), true);
        try
        {
            database = new Database();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run()
    {
        ServerWork serverWork = new ServerWork(bufferedReader, printWriter, database);
        int idOperation;
        try
        {
            while(true)
            {
                String buffer = bufferedReader.readLine();
                idOperation = Integer.parseInt(buffer);
                serverWork.getId(idOperation);
            }
        }
        catch (IOException | SQLException e)
        {
            e.printStackTrace();
        }

    }
}