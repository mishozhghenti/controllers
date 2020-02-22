package com.example.demo.model;

import java.sql.*;

public class Repository {
    protected void closeDAO(Connection connection, PreparedStatement preparedStatement, Statement statement) {
        closeExceptResultSet(connection, preparedStatement, statement);
    }

    protected void closeDAO(Connection connection, PreparedStatement preparedStatement, Statement statement, ResultSet resultSet) {
        closeExceptResultSet(connection, preparedStatement, statement);
        if (resultSet != null) try {
            resultSet.close();
        } catch (SQLException ignored) {
        }
    }

    private void closeExceptResultSet(Connection connection, PreparedStatement preparedStatement, Statement statement) {
        if (connection != null) try {
            // Returns the connection to the pool.
            connection.close();
        } catch (Exception ignored) {
        }

        if (preparedStatement != null) try {
            // Returns the connection to the pool.
            preparedStatement.close();
        } catch (Exception ignored) {
        }

        if (statement != null) try {
            // Returns the connection to the pool.
            statement.close();
        } catch (Exception ignored) {
        }
    }
}
