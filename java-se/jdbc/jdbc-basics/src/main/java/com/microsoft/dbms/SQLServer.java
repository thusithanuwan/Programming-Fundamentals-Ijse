package com.microsoft.dbms;

public class SQLServer {
    public StringBuilder interpret(byte[] command) {
       return new StringBuilder().append("SQL Server ")
                .append(new String(command));

    }
}
