package lk.ultratech.agent.sys.db;

import javafx.scene.control.Alert;
import lk.ijse.dep.crypto.DEPCrypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    public static String host;
    public static String port;
    public static String db;
    public static String user;
    public static String password;

    private DBConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            File file = new File("resources/application.properties");
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
            fis.close();

            String ip = properties.getProperty("cementAgent.ip");
            DBConnection.host = ip;
            String port = properties.getProperty("cementAgent.port");
            DBConnection.port = port;
            String db = properties.getProperty("cementAgent.db");
            DBConnection.db = db;
            String user = DEPCrypt.decode(properties.getProperty("cementAgent.user"),"123");
            DBConnection.user = user;
            String password = DEPCrypt.decode(properties.getProperty("cementAgent.password"),"123");
            DBConnection.password = password;

            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db + "?createDatabaseIfNotExist=true&allowMultiQueries=true", user, password);
            PreparedStatement pstm = connection.prepareStatement("SHOW TABLES");
            ResultSet resultSet = pstm.executeQuery();
            if (!resultSet.next()) {
                File dbScriptFile = new File("db-script.sql");
                if (!dbScriptFile.exists()){
                    new Alert(Alert.AlertType.ERROR,"Database File Not found");
                    throw  new RuntimeException("Unable to read DB Script");
                }
                StringBuilder sb= new StringBuilder();
                BufferedReader brScript = new BufferedReader(new InputStreamReader(new FileInputStream(dbScriptFile)));
                brScript.lines().forEach(s -> sb.append(s));
                brScript.close();
                System.out.println(sb.toString());
                pstm = connection.prepareStatement(sb.toString());
                pstm.execute();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        return (dbConnection == null) ? (dbConnection = new DBConnection()) : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
