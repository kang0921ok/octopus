package osc.gobaby.octopus.service.user.repository;


import osc.gobaby.octopus.support.BaseMapper;
import osc.gobaby.octopus.service.user.entity.User;

import java.util.List;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
public interface UserMapper extends BaseMapper {
	public User selectOne(String userId);
    public List<User> selectList();
    public int insert(User user);
    public int update(User user);
}
