package osc.gobaby.statistics_cloud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.gobaby.statistics_cloud.logstash.entity.LogStash;
import osc.gobaby.statistics_cloud.user.entity.User;
import osc.gobaby.statistics_cloud.user.repository.UserMapper;

import java.util.List;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean login(User user) {
        User sUser = findUser(user.getUserId());
        return user.getUserPwd().equals(sUser.getUserPwd()) ? true : false;
    }

    public boolean join(User user) {
        int insertNum = userMapper.insert(user);
        return insertNum == 1 ? true : false;
    }

    public boolean isDuplicate(User user) {
        User sUser = userMapper.selectOne(user.getUserId());
        return sUser != null ? true : false;
    }

    public User findUser(String userId) {
        return userMapper.selectOne(userId);
    }

    public boolean authorize(String userId, String secretKey){
        User user = userMapper.selectOne(userId);
        if(user.getSecretKey().equals(secretKey)){
            return true;
        }

        return false;
    }

    public List<User> findUserList() {
        return userMapper.selectList();
    }

    public void updateUser(User user) {
        userMapper.update(user);
    }
}
