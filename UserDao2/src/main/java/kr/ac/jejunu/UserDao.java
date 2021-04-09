package kr.ac.jejunu;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User findById(Integer id) throws SQLException {
        // 변하는 것 stretagy
        // 변하는 것은 interface로 선언하여 뽑아내기
        // 변하지 않는 것 context
        String sql = "select * from  userinfo where id = ?";
        Object[] params = new Object[] {id};
        return findById(sql, params);
    }

    private User findById(String sql, Object[] params) throws SQLException {
        return jdbcContext.jdbcContextForFindById(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql
            );
            for (int i = 0; i < params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        });
    }

    public void insert(User user) throws SQLException {
        String sql = "insert into userinfo (name, password) values ( ?, ? )";
        Object[] params = new Object[] {user.getName(), user.getPassword()};
        jdbcContext.insert(user, sql, params);
    }

    public void update(User user) throws SQLException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = new Object[] {user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, params);
    }
    public void delete(Integer id) throws SQLException {
        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[] {id};
        jdbcContext.update(sql, params);
    }
}