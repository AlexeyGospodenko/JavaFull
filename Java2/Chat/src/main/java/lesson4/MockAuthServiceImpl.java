package lesson4;

import java.util.HashMap;

public class MockAuthServiceImpl implements AuthService {
    private static MockAuthServiceImpl instance;
    private HashMap<String, String> userDao;

    private MockAuthServiceImpl() {
        userDao = new HashMap();
        userDao.put("admin", "pass");
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
        return userDao.get(name).equals(pass);
    }
}