package main.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_JDBC_URL = "jdbc:h2:~/expenses";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";


    static{
        try{
            Class.forName(DB_DRIVER);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        try{
            return DriverManager.getConnection(DB_JDBC_URL, DB_USER, DB_PASSWORD);
        }catch (SQLException e){
            throw new SQLException("Error with db connection");
        }
    }

    public static void closeConnection(Connection connection) throws SQLException{
        if(connection != null){
            try{
                connection.close();
            }catch (SQLException e){
                throw new SQLException("Error closing db connection", e);
            }
        }
    }
}
