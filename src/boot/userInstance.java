package boot;

public final class userInstance {

    private static userInstance instance;
    private String username;

    public userInstance() {}

    public userInstance(String username) {
        this.username = username;
    }

    public static userInstance getInstance() {
        if(instance == null) {
            instance = new userInstance();
        }
        return instance;
    }

    public static userInstance getInstance(String username) {
        if(instance == null) {
            instance = new userInstance(username);
        }
        return instance;
    }

    public String getUsername() {
        return this.username;
    }
}
