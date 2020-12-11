package Chat;

import java.util.HashMap;

public class MockAuthServiceImpl implements AuthService {
    private static MockAuthServiceImpl instance;
    private HashMap<String, String> userDao;
    private String userName;

    private MockAuthServiceImpl() {
        userDao = new HashMap();
        userDao.put("admin", "pass");
        userDao.put("Alex", "123");
    }

    public static MockAuthServiceImpl getInstance() {
        if (instance == null) {
            instance = new MockAuthServiceImpl();
        }
        return instance;
    }

    public void addUser(String name, String pass) {
        userDao.put(name, pass);
    }

    public boolean auth(String name, String pass) {
        if (userDao.get(name) != null) {
            userName = name;
            return (userDao.get(name).equals(pass));
        } else return false;
    }

    public String getUserName() {
        return userName;
    }
}