package lk.ijse.dep10.jdbc;

import com.microsoft.dbms.SQLServer;
import com.mysql.dbms.MySQLServer;
import com.oracle.dbms.OracleServer;
import jdbc.JdbcApi;

public class ClientApp {
    public static void main(String[] args) {
        // Without implementing JDBC Api

//        OracleServer oracleServer = new OracleServer();
//        String response = oracleServer.executeCommand("Hello");
//        System.out.println(response);
//
//        MySQLServer mySQLServer = new MySQLServer();
//        byte[] bytes = mySQLServer.run("Hello");
//        response = new String(bytes);
//        System.out.println(response);
//
//        SQLServer sqlServer = new SQLServer();
//        StringBuilder interpret = sqlServer.interpret("Hello".getBytes());
//        response = interpret.toString();
//        System.out.println(response);



        // implementing JDBC Api
        JdbcApi oracleServer = new OracleServer();
        String response = oracleServer.execute("Hello");
        System.out.println(response);

        



    }
}
