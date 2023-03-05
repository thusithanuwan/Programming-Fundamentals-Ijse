package lk.ijse.sep10.server;

import lk.ijse.dep10.shared.Dep10Headers;
import lk.ijse.dep10.shared.Dep10Message;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AppInitializer {

    private static ArrayList<User> userList = new ArrayList<>();
    private static String chatHistory = "";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5050);
        System.out.println("Server is listening to the 5050.");


        while (true) {
            Socket localSocket = serverSocket.accept();
            User user = new User(localSocket);
            userList.add(user);
            sendChatHistory(user);
            broadCastLoggedUsers();
        }


    }

    private static void broadCastLoggedUsers() {
        Dep10Message dep10Message = new Dep10Message(Dep10Headers.USERS, userList);
        for (User user : userList) {
            new Thread(() -> {
                try {
                    ObjectOutputStream oos = user.getObjectOutputStream();
                    oos.writeObject(dep10Message);
                    oos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    private static void sendChatHistory(User user) throws IOException {
        Dep10Message dep10Message = new Dep10Message(Dep10Headers.MSG, chatHistory);
        ObjectOutputStream oos = user.getObjectOutputStream();
        oos.writeObject(dep10Message);
        oos.flush();
    }
}


