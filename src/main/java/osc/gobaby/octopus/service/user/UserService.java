package osc.gobaby.octopus.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import osc.gobaby.octopus.service.user.entity.User;
import osc.gobaby.octopus.service.user.repository.UserMapper;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    public User login(User user) {
        User sUser = findUser(user.getUserId());
        if (sUser == null) {
            return null;
        }
        return user.getUserPwd().equals(sUser.getUserPwd()) ? sUser : null;
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

    public boolean authorize(String userId, String secretKey) {
        User user = userMapper.selectOne(userId);
        if (user == null || user.getSecretKey() == null) {
            return false;
        }

        if (user.getSecretKey().equals(secretKey)) {
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

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return findUser(userId);
    }
}
