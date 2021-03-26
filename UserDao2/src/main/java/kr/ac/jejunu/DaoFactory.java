package kr.ac.jejunu;

// dependency injection
// spring framework 기능
public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }

    private JejuConnectionMaker getConnectionMaker() {
        return new JejuConnectionMaker();
    }
}
