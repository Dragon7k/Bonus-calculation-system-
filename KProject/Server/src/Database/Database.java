package Database;

import java.sql.*;

public class Database implements IDatabase
{
    private PreparedStatement preparedStatement;
    private Connection connection;

    public Database() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection =  DriverManager.getConnection("jdbc:mysql://localhost/comp?serverTimezone=Europe/Moscow&useSSL=false",
                "root", "1111");

    }

    @Override
    public void insert(String sql) {
        try
        {
            preparedStatement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(String sql) {

        try
        {
            preparedStatement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(String sql) {
        try
        {
            preparedStatement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    @Override
    public ResultSet select(String sql) {
        ResultSet resultSet = null;
        try
        {
            preparedStatement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = preparedStatement.executeQuery();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public void close() {


    }
}
