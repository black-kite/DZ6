import java.sql.*;

public class Task6 {

    private static Connection conn;
    private static Statement statement;

    public int[] checkArray(int[] arr, int val) {

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == val) {
                int[] outArray = new int[arr.length - i - 1];
                System.arraycopy(arr, i + 1, outArray, 0, outArray.length);
                return outArray;
            }
        }
        throw new RuntimeException("В массиве нет значения " + val);
    }

    public boolean isArrayCorrect(int[] arr, int val1, int val2) {
        boolean[] res = new boolean[2];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val1) {
                res[0] = true;
            }
            if (arr[i] == val2) {
                res[1] = true;
            }
        }
        return res[0] && res[1];
    }

    public static void main(String[] args) {

        try {
            connect();
            //initTable();
            //addData();
            select();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:students.db");
        statement = conn.createStatement();
    }

    public static void disconnect() {
        try {
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initTable() throws SQLException {
        boolean table = statement.execute("CREATE TABLE IF NOT EXISTS Students(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name STRING NOT NULL, score INTEGER NOT NULL);");
        if (!table) {
            statement.execute("DELETE FROM students;");
        }
    }

    private static void addData() throws SQLException {
        conn.setAutoCommit(false);
        PreparedStatement ps = conn.prepareStatement("INSERT INTO students(name, score) VALUES (?,?);");
        for (int i = 1; i <= 5; i++) {
            ps.setString(1, "студент" + i);
            ps.setInt(2, (i * 2));
            ps.executeUpdate();
        }
        conn.commit();
    }

    public static void select() throws SQLException {
        ResultSet res = statement.executeQuery("Select * from students");
        while (res.next()) {
            System.out.println("id: " + res.getInt(1) + " имя: " + res.getString(2) +
                    " баллы: " + res.getString(3));
        }
    }

    public static boolean checkData(Statement statement, String query) {
        boolean isNext = false;
        try {
            ResultSet res = statement.executeQuery(query);
            isNext = res.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isNext;
    }

    public static boolean checkBase(Statement statement, String query) {
        int i = 0;
        try {
            i = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i != 0;
    }
}
