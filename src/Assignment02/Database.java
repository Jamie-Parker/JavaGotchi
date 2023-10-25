/*
*Jamie Parker
*20101511
*DataBase is used to create a database and table. Inludes functions for checking of databse and table existance and contents
 */
package Assignment02;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    public static Connection conn;
    public static String url = "jdbc:derby:DBJavaGotchi";
    public static String dbusername = "root";
    public static String dbpassword = "root";
    public static String tablename = "PETINFO";
    public boolean freshStart = false;
    public String[] animalType = new String[3];
    public boolean[] animalSlot = new boolean[3];

    public Database(){
        try {
            dbSetup();
        } catch (Throwable ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void openConnection(){
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, dbusername, dbpassword);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean databaseExist(String url) {
        try {
            DriverManager.getConnection(url);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private boolean checkTableExisting(Connection conn, String tablename) throws SQLException {
        try {
            if (conn != null) {
                String[] types = {"TABLE"};
                boolean tableExists = conn.getMetaData().getTables(null, null, tablename, types).next();
                return tableExists;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createTable(Connection conn, String tableName) throws SQLException {
        try ( Statement statement = conn.createStatement()) {
            statement.executeUpdate("CREATE TABLE " + tableName + " ("
                    + "ID int,"
                    + "TYPE VARCHAR(10), COLOUR VARCHAR(10), NAME VARCHAR(20), "
                    + "CREATED TIMESTAMP, SAVED TIMESTAMP, "
                    + "HUNGER INT, TIRED INT, BORED INT, HYGIENE INT, ILL INT)");
        }
    }

    public void insertData(int petID, Map<String, Object> petData, Connection conn) throws SQLException {
        String insertQuery = "INSERT INTO " + tablename + " (ID, TYPE, COLOUR, NAME, CREATED, SAVED, HUNGER, TIRED, BORED, HYGIENE, ILL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement statement = conn.prepareStatement(insertQuery)) {//Converts a petStatus object into a map that can be inserted into the  Table
            statement.setInt(1, petID);
            statement.setString(2, (String) petData.get("TYPE"));
            statement.setString(3, (String) petData.get("COLOUR"));
            statement.setString(4, (String) petData.get("NAME"));
            statement.setTimestamp(5, (Timestamp) petData.get("CREATED"));
            statement.setTimestamp(6, (Timestamp) petData.get("SAVED"));
            statement.setInt(7, (int) petData.get("HUNGER"));
            statement.setInt(8, (int) petData.get("TIRED"));
            statement.setInt(9, (int) petData.get("BORED"));
            statement.setInt(10, (int) petData.get("HYGIENE"));
            statement.setInt(11, (int) petData.get("ILL"));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    private void isTableEmpty(Connection conn, String tablename) throws SQLException {
        try ( Statement statement = conn.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + tablename)) {
            if (resultSet.next()) {
                int rowCount = resultSet.getInt(1);
                int rowsToInsert = 3 - rowCount;
                if (rowsToInsert > 0) {
                    for (int i = 1; i <= rowsToInsert; i++) {//If the table is empty, fill first 3 rows with placeholder information
                        Map<String, Object> petData = new LinkedHashMap<>();
                        Timestamp timeStamp = Timestamp.valueOf("2001-01-01 00:00:00.000");
                        petData.put("ID", i);
                        petData.put("TYPE", "Type");
                        petData.put("COLOUR", "Colour");
                        petData.put("NAME", "Name");
                        petData.put("CREATED", timeStamp);
                        petData.put("SAVED", timeStamp);
                        petData.put("HUNGER", 0);
                        petData.put("TIRED", 0);
                        petData.put("BORED", 0);
                        petData.put("HYGIENE", 0);
                        petData.put("ILL", 0);
                        insertData(i, petData, conn);
                        freshStart = true;
                    }
                } else {
                    freshStart = false;
                }
            }
        }
    }
    
    private void dbSetup() {
        try {
            if (!databaseExist(url)) {//Check if Database Exists
                url = url + ";create=true"; // Add create=true if the database doesn't exist to create one
            }
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            if (!checkTableExisting(conn, tablename)) {//Check if Table exists
                createTable(conn, tablename);
            }
            isTableEmpty(conn, tablename);//Check if the Table has no content
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void updateData(int petID, Map<String, Object> updates) throws SQLException {
        try ( Connection connection = conn) {//Used to update table After placeholders have been established
            for (Map.Entry<String, Object> entry : updates.entrySet()) {//Uses a map to maintain table coloumn names and object value
                String columnName = entry.getKey();
                Object columnValue = entry.getValue();
                String updateQuery = null;
                if (columnValue instanceof String || columnValue instanceof Integer) {
                    updateQuery = "UPDATE " + tablename + " SET " + columnName + " = ? WHERE ID = " + petID;
                } else if (columnValue instanceof Timestamp) {
                    updateQuery = "UPDATE " + tablename + " SET " + columnName + " = ? WHERE ID = " + petID;
                }
                if (updateQuery != null) {//Hanldes different query types for insert - String / Int / TimeStamp
                    try ( PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        if (columnValue instanceof String) {
                            preparedStatement.setString(1, (String) columnValue);
                        } else if (columnValue instanceof Integer) {
                            preparedStatement.setInt(1, (int) columnValue);
                        } else if (columnValue instanceof Timestamp) {
                            preparedStatement.setTimestamp(1, (Timestamp) columnValue);
                        }
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        System.err.println("Error updating column " + columnName + ": " + e.getMessage());
                    }
                }
            }
        }
    }

    public void checkTable(){
        try ( Statement statement = conn.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT TYPE FROM " + tablename)) {
            int row = 0;
            while (resultSet.next()) {//Used to query the table for the word TYPE in type section - used to determine pictures for load and save slot
                String petType = resultSet.getString("TYPE");
                if (row < animalType.length) {
                    animalType[row] = petType;
                    row++;
                }
            }
            for (int i = 0; i < 3; i++) {
                animalSlot[i] = "Type".equals(animalType[i]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getAnimalType(int slot) {
        return animalType[slot];
    }

    public boolean getFreshStart() {
        return this.freshStart;
    }

    public List<Map<String, Object>> getInfo() {
        openConnection();//Used to load a pet object from the table for FileController LoadPetInfo
        List<Map<String, Object>> petInfoList = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + tablename);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> petInfo = new HashMap<>();
                petInfo.put("ID", rs.getInt("ID"));
                petInfo.put("TYPE", rs.getString("TYPE"));
                petInfo.put("COLOUR", rs.getString("COLOUR"));
                petInfo.put("NAME", rs.getString("NAME"));
                petInfo.put("CREATED", rs.getTimestamp("CREATED"));
                petInfo.put("SAVED", rs.getTimestamp("SAVED"));
                petInfo.put("HUNGER", rs.getInt("HUNGER"));
                petInfo.put("TIRED", rs.getInt("TIRED"));
                petInfo.put("BORED", rs.getInt("BORED"));
                petInfo.put("HYGIENE", rs.getInt("HYGIENE"));
                petInfo.put("ILL", rs.getInt("ILL"));
                petInfoList.add(petInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return petInfoList;
    }

}
