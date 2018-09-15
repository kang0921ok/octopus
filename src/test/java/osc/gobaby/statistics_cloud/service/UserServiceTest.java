package osc.gobaby.statistics_cloud.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import osc.gobaby.statistics_cloud.user.User;
import osc.gobaby.statistics_cloud.user.UserService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void userServiceInsertTest() {
        //given
        String userId = "kangtest3";
        User user = new User(userId, "kangtest3", false, 1234567);

        //when
        userService.insertUser(user);

        //then
        User actual = userService.findUser(userId);
        assertThat(actual.getUserId(), is(userId));
    }


}