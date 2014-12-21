package org.fruit.blueberry.util;

import org.fruit.blueberry.exception.DaoBaseException;
import org.fruit.blueberry.exception.DaoConnectionException;

import java.sql.*;
import java.util.Properties;

/**
 * Created by AFei on 2014/7/27.
 */
public final class JDBCUtil {
    private static Connection connection = null;
    private static Properties properties = null;

    private JDBCUtil() {
    }

    static {
        properties = PropertiesUtil.getJDBCProperties();

        try {
            System.out.println("[Info] Register drivers of jdbc.");
            Class.forName(properties.getProperty("jdbc.drivers"));
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Get the connection to the database according to the configuration file jdbc.properties.
     *
     * @return A connection to the database server.
     */
    public static Connection getConnection() {
        if (connection == null) {
            synchronized (JDBCUtil.class) {
                if (connection == null) {
                    String user = properties.getProperty("user");
                    String password = properties.getProperty("password");
                    String url = properties.getProperty("url");

                    try {
                        System.out.println("[Info] Getting connection to the server......");
                        connection = DriverManager.getConnection(url, user, password);
                    } catch (SQLException e) {
                        throw new DaoConnectionException("[Info] Failed to connect to the server. " +
                                "Make sure the user name, password or url is correct, or the server is running.", e);
                    }
                }
            }
        }

        return connection;
    }

    /**
     * Execute update based on @PreparedStatement.
     *
     * @param sql The sql statement to be performed.
     * @return The affected row number.
     */
    public static int executeUpdate(String sql) {
        Object[] values = null;
        return executeUpdate(sql, values);
    }

    /**
     * Execute update based on @PreparedStatement.
     *
     * @param sql The sql statement to be performed.
     * @param values The parameters of the sql statement.
     * @return The affected row number.
     */
    public static int executeUpdate(String sql, Object... values) {
        connection = getConnection();
        return executeUpdate(connection, sql, values);
    }

    /**
     * Execute update based on @PreparedStatement.
     *
     * @param connection The connection to the server.
     * @param sql The sql statement to be performed.
     * @param values The parameters of the sql statement.
     * @return The affected row number.
     */
    public static int executeUpdate(Connection connection, String sql, Object... values) {
        int cnt = 0;
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(sql);
            setParameters(ps, values);
            System.out.println("[Perform] " + ps.toString());
            cnt = ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoBaseException(e);
        } finally {
            close(ps);
        }

        return cnt;
    }

    /**
     * Execute query based on @PreparedStatement.
     *
     * @param sql The sql statement to be performed.
     * @return The affected row number.
     * @throws java.sql.SQLException
     */
    public static PreparedStatement executeQuery(String sql) throws SQLException {
        Object[] values = null;
        return executeQuery(sql, values);
    }

    /**
     * Execute query based on @PreparedStatement.
     *
     * @param sql The sql statement to be performed.
     * @param values The parameters of the sql statement.
     * @return The affected row number.
     * @throws java.sql.SQLException
     */
    public static PreparedStatement executeQuery(String sql, Object... values) throws SQLException {
        Connection connection = getConnection();
        return executeQuery(connection, sql, values);
    }

    /**
     * Execute query based on @PreparedStatement.
     *
     * @param connection The connection to the server.
     * @param sql The sql statement to be performed.
     * @param values The parameters of the sql statement.
     * @return The affected row number.
     * @throws java.sql.SQLException
     */
    public static PreparedStatement executeQuery(Connection connection, String sql, Object... values) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        setParameters(ps, values);
        System.out.println("[Perform] " + ps.toString());
        return ps;
    }

    /**
     * Close the connection to the database.
     */
    public static void closeConnection() {
        try {
            if (getConnection() != null) {
                System.out.println("[Info] Close the connection to database.");
                getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the @PreparedStatement.
     *
     * @param ps The PreparedStatement.
     */
    public static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the @ResultSet.
     *
     * @param rs The ResultSet
     */
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setParameters(PreparedStatement ps, Object... values) throws SQLException {
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i + 1, values[i]);
            }
        }
    }
}
