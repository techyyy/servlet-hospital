package com.hospital.Hospital.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used for managing database connection
 */

public class ConnectionPool {

    private static final Logger LOG = LogManager.getLogger(ConnectionPool.class);

    public static DataSource ds;
    private static ConnectionPool instance;

    public static void setDataSource(DataSource ds) {
        ConnectionPool.ds = ds;
    }

    private ConnectionPool() {
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            if(ds == null) {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                ds = (DataSource) envContext.lookup("jdbc/myhospital");
            }
            con = ds.getConnection();
            con.setAutoCommit(false);
        } catch (NamingException | SQLException ex) {
            LOG.error("Can't get connection" + ex.getMessage());
        }
        return con;
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOG.error("Can't close result set" + e.getMessage());
            }
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            LOG.error("Can't close connection" + ex.getMessage());
        }
    }

    public static void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException ex) {
            LOG.error("Can't rollback" + ex.getMessage());
        }
    }

    public static void commit(Connection con) {
        try {
            con.commit();
        } catch (SQLException e) {
            LOG.error("Can't commit" + e.getMessage());
        }
    }
}