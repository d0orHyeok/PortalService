package kr.ac.jejunu;

import javax.sql.DataSource;
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
        StatementStrategy statementStrategy = new FindByIdStatementStrategy(id);
        return jdbcContext.jdbcContextForFindById(statementStrategy);
    }
    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy =  new InsertStatementStrategy(user);
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }
    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
    public void delete(Integer id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
