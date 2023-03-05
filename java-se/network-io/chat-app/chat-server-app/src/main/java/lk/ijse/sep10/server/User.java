package lk.ijse.sep10.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class User {
    private Socket localsocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public User(Socket localsocket) throws IOException {
        this.localsocket = localsocket;
        this.objectOutputStream = new ObjectOutputStream(localsocket.getOutputStream());
        objectOutputStream.flush();
    }

    public Socket getLocalsocket() {
        return localsocket;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public ObjectInputStream getObjectInputStream() throws IOException {
        return objectInputStream != null ? objectInputStream :
                new ObjectInputStream(localsocket.getInputStream());
    }
    public String getRemoteIpAddress(){
        return ((InetSocketAddress)localsocket.getRemoteSocketAddress()).getHostString();
    }
}
