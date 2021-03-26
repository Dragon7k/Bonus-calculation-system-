package ClientWork;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketStream {
    private static Socket clientSocket;
    private static BufferedReader bufferedReader;
    private static PrintWriter printWriter;

    public void Connection() throws IOException
    {
        InetAddress inetAddress = InetAddress.getByName(null);
        clientSocket = new Socket(inetAddress, 3320);
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
    }

    public void sendInt(int i)
    {
        printWriter.println(Integer.toString(i));
    }

    public void sendString(String str)
    {
        printWriter.println(str);
    }

    public int getInt() throws IOException
    {
        return Integer.parseInt(bufferedReader.readLine());
    }
    public float getFloat() throws IOException
    {
        return Float.parseFloat(bufferedReader.readLine());
    }
    public String getString() throws IOException
    {
        return bufferedReader.readLine();
    }
}
