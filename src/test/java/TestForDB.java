import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestForDB {

    Connection conn;
    Statement statement;

    @Before
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:students.db");
            statement = conn.createStatement();
            conn.setAutoCommit(false);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void disconnect() {
        try {
            statement.close();
            conn.rollback();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectTrue() throws SQLException {
        Assert.assertTrue(Task6.checkData(statement,"SELECT * FROM students WHERE score > 8;"));
    }
    @Test
    public void testSelectFalse() throws SQLException {
        Assert.assertEquals(false,Task6.checkData(statement,"SELECT * FROM students WHERE score > 10;"));
    }
    @Test
    public void testDelete() throws SQLException {
        Assert.assertTrue(Task6.checkBase(statement,"DELETE FROM students;"));
    }
    @Test
    public void addData() throws SQLException {
        Assert.assertTrue(Task6.checkBase(statement,"INSERT INTO students VALUES (26, 'студент6', 12)"));
    }
    @Test
    public void testInsert() throws SQLException {
        Assert.assertTrue(Task6.checkBase(statement,"INSERT INTO students(name, score) VALUES('студент1',77);"));
    }
    @Test
    public void testUpdate() throws SQLException {
        Assert.assertTrue(Task6.checkBase(statement,"UPDATE students SET score = 40 WHERE name = 'студент2';"));
    }

}
