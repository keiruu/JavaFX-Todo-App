package sample.database;
import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signupUser(User user){
        String insert = "INSERT INTO " + Const.USERS_TABLE + " (" + Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME + "," + Const.USERS_EMAIL + "," + Const.USERS_PASSWORD + ","
                + Const.USERS_GENDER + ") " + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pst = getDbConnection().prepareStatement(insert);

            pst.setString(1, user.getFirstname());
            pst.setString(2, user.getLastname());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPass());
            pst.setString(5, user.getGender());

            pst.executeUpdate();

            System.out.println("You have successfully logged in!");
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){
        ResultSet rs = null;
        if(!user.getEmail().equals("") || !user.getPass().equals("")){
            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_EMAIL + "=?" + " AND " + Const.USERS_PASSWORD + " =?";

            try {
                PreparedStatement pst = getDbConnection().prepareStatement(query);
                pst.setString(1, user.getEmail());
                pst.setString(2, user.getPass());

                 rs = pst.executeQuery();

                 return rs;
            } catch (SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }

        } else {
            System.out.println("Please enter your credentials");
        }

        return rs;
    }


}
