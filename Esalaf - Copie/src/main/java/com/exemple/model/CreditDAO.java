package com.exemple.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditDAO extends BaseDAO<Credit> {
    public CreditDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Credit object) throws SQLException {
        String req = "INSERT INTO credit (nom, credi) VALUES (?, ?)";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setString(1, object.getNom());
        this.preparedStatement.setString(2, object.getCredi());
        this.preparedStatement.execute();
    }

    @Override
    public void update(Credit object) throws SQLException {
        String req = "UPDATE credit SET nom=?, credi=? WHERE id_credit=?";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setString(1, object.getNom());
        this.preparedStatement.setString(2, object.getCredi());
        this.preparedStatement.setInt(3, object.getId_credit());
        this.preparedStatement.executeUpdate();
    }
    /*
    public List<Credit> search(String query) throws SQLException {
        List<Credit> result = new ArrayList<>();

        String req = "SELECT * FROM credit WHERE nom LIKE ?";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setString(1, "%" + query + "%");

        ResultSet rs = this.preparedStatement.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_credit");
            String nom = rs.getString("nom");
            String credi = rs.getString("credi");
            Credit credit = new Credit(id, nom, credi);
            result.add(credit);
        }

        return result;
    }*/

    @Override
    public void delete(Credit object) throws SQLException {
        String req = "DELETE FROM credit WHERE id_credit=?";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setInt(1, object.getId_credit());
        this.preparedStatement.execute();
    }

    @Override
    public Credit getOne(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Credit> getAll() throws SQLException {
        List<Credit> mylist = new ArrayList<Credit>();
        String req = "select * from credit";
        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(req);
        while (this.resultSet.next()) {
            mylist.add(new Credit(
                    this.resultSet.getInt(1),
                    this.resultSet.getString(2),
                    this.resultSet.getString(3)));
        }
        return mylist;
    }
}





