package osc.gobaby.octopus.user.entity;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
public class User {
    private String userId;
    private String userPwd;
    private Boolean master;
    private String secretKey;

    public User() {
    }

    public User(String userId, String userPwd, Boolean master, String secretKey) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.master = master;
        this.secretKey = secretKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Boolean isMaster() {
        return master;
    }

    public void setMaster(Boolean master) {
        this.master = master;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
