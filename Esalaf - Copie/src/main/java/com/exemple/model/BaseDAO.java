package com.exemple.model;

import java.sql.*;
import java.util.List;


// transaction
public abstract class BaseDAO<T> {

    // Intefaces JDBC

    protected static Connection connection ;
    protected  Statement statement ;
    protected PreparedStatement preparedStatement ;
    protected ResultSet resultSet ;

    public String url = "jdbc:mysql://127.0.0.1:3306/esalaf" ;

    public String login = "root";

    public String password = "";

     public BaseDAO() throws SQLException {

         this.connection = DriverManager.getConnection(url , login , password);

    }

    public static Connection getConnection() {
        return connection;
    }


    public abstract void save(T object)throws SQLException ;

    public abstract void update(T object) throws SQLException;

    public abstract void delete(T object) throws SQLException;


    public abstract T getOne(Long id) throws SQLException;


    public abstract List<T> getAll() throws SQLException;
}
