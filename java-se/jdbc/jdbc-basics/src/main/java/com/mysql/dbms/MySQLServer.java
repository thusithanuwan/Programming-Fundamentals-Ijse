package com.mysql.dbms;


public class MySQLServer {
    public byte[] run(String msg) {
        return ("MySQL Server " + msg).getBytes();
    }
}
