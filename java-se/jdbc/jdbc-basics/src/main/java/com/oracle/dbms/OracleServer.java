package com.oracle.dbms;

import jdbc.JdbcApi;

public class OracleServer implements JdbcApi {
    public String executeCommand(String msg) {
        return "Oracle Server " + msg;
    }

    @Override
    public String execute(String msg) {
        return executeCommand(msg);
    }
}
