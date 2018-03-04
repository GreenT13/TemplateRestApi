package com.apon.database.jooq;

import com.apon.log.Log;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.JDBCUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SuppressWarnings("UnusedReturnValue")
public class Context {
    private DataSource dataSource;
    private Connection connection;
    private Configuration configuration;

    public Context(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;

        // Configure the context based on the dataSource.
        connection = dataSource.getConnection();
        configuration = DSL.using(connection, JDBCUtils.dialect(connection)).configuration();
    }

    public boolean commit() {
        try {
            connection.commit();
            return true;
        } catch (SQLException e) {
            Log.logError("Could not commit connection.", e);
            return false;
        }
    }

    public boolean rollback() {
        try {
            connection.rollback();
            return true;
        } catch (SQLException e) {
            Log.logError("Could not rollback connection.", e);
            return false;
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
