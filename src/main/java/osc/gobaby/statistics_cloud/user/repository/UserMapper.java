package osc.gobaby.statistics_cloud.user.repository;


import osc.gobaby.statistics_cloud.support.BaseMapper;
import osc.gobaby.statistics_cloud.user.User;

import java.util.List;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
public interface UserMapper extends BaseMapper {
    public User selectOne(String userId);
    public List<User> selectList();
    public int insert(User user);
    public void update(User user);
}
