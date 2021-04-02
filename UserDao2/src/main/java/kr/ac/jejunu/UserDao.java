package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findById(Integer id) throws SQLException {
        // 변하는 것 stretagy
        // 변하는 것은 interface로 선언하여 뽑아내기
        Connection connection = dataSource.getConnection();
        // 변하지 않는 것 context
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from  userinfo where id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return user;
    }

    public void insert(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into userinfo (name, password) values (? , ?)"
                , Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();

        user.setId(resultSet.getInt(1));

        preparedStatement.close();
        connection.close();
    }
}
