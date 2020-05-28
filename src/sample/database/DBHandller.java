package sample.database;
import sample.model.Paper;
import sample.model.User;

import java.sql.*;

public class DBHandller {

    private PreparedStatement preparedStatement;

    private Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/paper","root","root");
        return connection;
    }

    public ResultSet getQandA() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=null;
        preparedStatement=getConnection().prepareStatement("SELECT * FROM qa");
        resultSet=preparedStatement.executeQuery();
        return resultSet;
    }

    public void getDelete(int id) throws SQLException, ClassNotFoundException {
        preparedStatement=getConnection().prepareStatement("DELETE FROM qa where qid=?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    public void setData(Paper paper) throws SQLException, ClassNotFoundException {
        preparedStatement=getConnection().prepareStatement("INSERT INTO qa (q,aOne,aTwo,ca,aThree) VALUES (?,?,?,?,?)");
        preparedStatement.setString(1,paper.getQuestion());
        preparedStatement.setString(2,paper.getAnswars().get(0));
        preparedStatement.setString(3,paper.getAnswars().get(1));
        preparedStatement.setString(4,paper.getAnswars().get(2));
        preparedStatement.setString(5,paper.getAnswars().get(3));
        preparedStatement.executeUpdate();
    }

    public void setTime(User user) throws SQLException, ClassNotFoundException {
        preparedStatement=getConnection().prepareStatement("UPDATE users set datee=?,timee =?");
        preparedStatement.setString(1,user.getDate());
        preparedStatement.setString(2,user.getTime());
        preparedStatement.executeUpdate();
    }

}
