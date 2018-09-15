package osc.gobaby.statistics_cloud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.gobaby.statistics_cloud.user.repository.UserMapper;

import java.util.List;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUser(String userId){
      return userMapper.selectOne(userId);
    }

    public List<User> findUserList(){
        return userMapper.selectList();
    }

    public void insertUser(User user) {
        userMapper.insert(user);
    }

    public void updateUser(User user) {
        userMapper.update(user);
    }
}
