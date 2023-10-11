/*
*Jamie Parker
*20101511
 */
package Assignment02;

// Used to handle database connection and throughput
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

    public Database() throws Throwable {
        try {
            dbSetup();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean databaseExist(String url) {
        try {
            DriverManager.getConnection(url);
            System.out.println("Database Exists");
            return true; // The database exists
        } catch (SQLException e) {
            System.out.println("Database Does not Exist");
            return false; // The database doesn't exist
        }
    }

    private void dbSetup() throws Throwable {
        try {
            if (!databaseExist(url)) {
                url = url + ";create=true"; // Add create=true if the database doesn't exist
            }
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            System.out.println(conn);
            System.out.println(url + " Connected...");
            if (!checkTableExisting(conn, tablename)) {
                createTable(conn, tablename);

            }

            isTableEmpty(conn, tablename);

        } catch (Throwable e) {
            e.printStackTrace();
            System.err.println("Database setup error: " + e.getMessage());
        }
    }

    public void openConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
        }
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

    private boolean checkTableExisting(Connection conn, String tablename) throws SQLException {
        try {
            if (conn != null) {
                String[] types = {"TABLE"};
                boolean tableExists = conn.getMetaData().getTables(null, null, tablename, types).next();
                if (tableExists) {
                    System.out.println("Table exists");
                }
                return tableExists;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
        System.out.println("Database Closed");
    }

    public void insertData(int petID, Map<String, Object> petData, Connection conn) throws SQLException {
        String insertQuery = "INSERT INTO " + tablename + " (ID, TYPE, COLOUR, NAME, CREATED, SAVED, HUNGER, TIRED, BORED, HYGIENE, ILL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement statement = conn.prepareStatement(insertQuery)) {
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
            System.out.println("Data inserted successfully.");
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
                    for (int i = 1; i <= rowsToInsert; i++) {
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
                        System.out.println("Pet " + i + " Populated");
                    }
                }
            }
        }
    }

    public void updateData(int petID, Map<String, Object> updates) throws SQLException {
        System.out.println(conn);
        try ( Connection connection = conn) {
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String columnName = entry.getKey();
                Object columnValue = entry.getValue();

                System.out.println("Column Name: " + columnName);
                System.out.println("Column Value: " + columnValue);

                String updateQuery = null;

                if (columnValue instanceof String || columnValue instanceof Integer) {
                    updateQuery = "UPDATE " + tablename + " SET " + columnName + " = ? WHERE ID = " + petID;
                } else if (columnValue instanceof Timestamp) {
                    updateQuery = "UPDATE " + tablename + " SET " + columnName + " = ? WHERE ID = " + petID;
                }

                System.out.println("Update Query: " + updateQuery);

                if (updateQuery != null) {
                    try ( PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        if (columnValue instanceof String) {
                            preparedStatement.setString(1, (String) columnValue);
                        } else if (columnValue instanceof Integer) {
                            preparedStatement.setInt(1, (int) columnValue);
                        } else if (columnValue instanceof Timestamp) {
                            preparedStatement.setTimestamp(1, (Timestamp) columnValue);
                        }

                        preparedStatement.executeUpdate();
                        System.out.println("Update successful for column: " + columnName);
                    } catch (SQLException e) {
                        System.err.println("Error updating column " + columnName + ": " + e.getMessage());
                    }
                } else {
                    System.out.println("Update Query is null. Skipping...");
                }
            }
        }
    }

    public List<Map<String, Object>> getInfo() throws SQLException {
        openConnection();
        
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
            System.out.println(petInfoList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return petInfoList;
    }

}
