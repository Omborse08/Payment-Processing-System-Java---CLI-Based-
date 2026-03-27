package Payment;

import java.util.HashMap;
import java.util.Map;

class user {
    private int userId;
    private String name;

    user(int userid,String name) {
        this.userId = userid;
        this.name = name;
    }

    public int getUserid() {
        return userId;
    }

    public String getName() {
        return name;
    }
}

class user_repo {
    private Map<Integer,user> user_repo = new HashMap<>();

    public void addUser(user user) {
        user_repo.put(user.getUserid(),user);
    }
}