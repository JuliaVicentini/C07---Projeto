package org;

import java.sql.*;

public abstract class ConnectionDAO {
    Connection connection;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    String database = "FaculdadeDB";
    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/" + database;

    public void connectToDB() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(true); // Isso garante que cada operação seja commitada automaticamente.
            System.out.println("Conexão realizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
}