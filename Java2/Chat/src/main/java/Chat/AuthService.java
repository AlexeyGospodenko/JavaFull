package Chat;

import java.sql.SQLException;

public interface AuthService {

    void addUser(String name, String pass, String nickName) throws SQLException;

    String auth(String name, String pass) throws SQLException;

    boolean isLoginExists (String login) throws SQLException;

    boolean isNicknameExists (String nickName) throws SQLException;
}