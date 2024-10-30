package src.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {

        private final String url = "jdbc:postgresql://localhost:5432/Task_Management_System";
        private final String user = "postgres";
        private final String password = "Ammubca57&";
        private Connection conn = null;
        private DBConnection()
        {
            try
            {
                conn = DriverManager.getConnection(url,user,password);
                if(conn!=null)
                    System.out.println("\nConnection Established !");
                else
                    System.out.println("Connection Failed !");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        public static DBConnection Connection()
        {
            DBConnection db = new DBConnection();
            return db;
        }
        public Connection connect()
        {
            return conn;
        }
        public void closeConnect() {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("\nConnection closed.\n");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

