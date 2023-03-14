package com.mysql.dbms;


import jdbc.JdbcApi;

public class MySQLServer implements JdbcApi {
    public byte[] run(String msg) {
        return ("MySQL Server " + msg).getBytes();
    }

    @Override
    public String execute(String msg) {
        return new String(run(msg));
    }
}
