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
    public boolean freshStart = false;
    public boolean slot1 = false;
    public boolean slot2 = false;
    public boolean slot3 = false;
    public String animal1;
    public String animal2;
    public String animal3;

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
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private void dbSetup() throws Throwable {
        try {
            if (!databaseExist(url)) {
                url = url + ";create=true"; // Add create=true if the database doesn't exist
            }
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            if (!checkTableExisting(conn, tablename)) {
                createTable(conn, tablename);
            }
            isTableEmpty(conn, tablename);
        } catch (Throwable e) {
            e.printStackTrace();
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
                        freshStart = true;
                    }
                } else {
                    freshStart = false;
                }
            }
        }
    }

    public void checkTable() throws SQLException {
        try ( Statement statement = conn.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT TYPE FROM " + tablename)) {
            slot1 = false;
            slot2 = false;
            slot3 = false;
            int row = 1;
            while (resultSet.next()) {
                String petType = resultSet.getString("TYPE");
                switch (petType) {
                    case "Type":
                        if (row == 1) {
                            slot1 = true;
                            animal1 = "Type";
                        } else if (row == 2) {
                            slot2 = true;
                            animal2 = "Type";
                        } else if (row == 3) {
                            slot3 = true;
                            animal3 = "Type";
                        }
                        break;
                    case "Dog":
                        if (row == 1) {
                            animal1 = "Dog";
                        } else if (row == 2) {
                            animal2 = "Dog";
                        } else if (row == 3) {
                            animal3 = "Dog";
                        }
                        break;
                    case "Cat":
                        if (row == 1) {
                            animal1 = "Cat";
                        } else if (row == 2) {
                            animal2 = "Cat";
                        } else if (row == 3) {
                            animal3 = "Cat";
                        }
                        break;
                    case "Rabbit":
                        if (row == 1) {
                            animal1 = "Rabbit";
                        } else if (row == 2) {
                            animal2 = "Rabbit";
                        } else if (row == 3) {
                            animal3 = "Rabbit";
                        }
                        break;
                    case "Mouse":
                        if (row == 1) {
                            animal1 = "Mouse";
                        } else if (row == 2) {
                            animal2 = "Mouse";
                        } else if (row == 3) {
                            animal3 = "Mouse";
                        }
                        break;
                }
                row++;
            }
        }
    }

    public boolean getSlot1() {
        return this.slot1;
    }

    public boolean getSlot2() {
        return this.slot2;
    }

    public boolean getSlot3() {
        return this.slot3;
    }

    public boolean getFreshStart() {
        return this.freshStart;
    }

    public String getAnimal1() {
        return animal1;
    }

    public String getAnimal2() {
        return animal2;
    }

    public String getAnimal3() {
        return animal3;
    }

    public void updateData(int petID, Map<String, Object> updates) throws SQLException {
        try ( Connection connection = conn) {
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String columnName = entry.getKey();
                Object columnValue = entry.getValue();
                String updateQuery = null;
                if (columnValue instanceof String || columnValue instanceof Integer) {
                    updateQuery = "UPDATE " + tablename + " SET " + columnName + " = ? WHERE ID = " + petID;
                } else if (columnValue instanceof Timestamp) {
                    updateQuery = "UPDATE " + tablename + " SET " + columnName + " = ? WHERE ID = " + petID;
                }
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
                    } catch (SQLException e) {
                        System.err.println("Error updating column " + columnName + ": " + e.getMessage());
                    }
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return petInfoList;
    }

}
