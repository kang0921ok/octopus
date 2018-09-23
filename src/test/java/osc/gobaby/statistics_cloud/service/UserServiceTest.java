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
    public void loginTrueTest(){
        //given
        String userId = "kangtest3";
        User user = new User(userId, "kangtest3", false, 1234567);

        //when
        userService.join(user);
        boolean actual = userService.login(user);

        //then
        assertThat(actual, is(true));
    }

    @Test
    public void loginFalseTest(){
        //given
        String userId = "kangtest3";
        User user = new User(userId, "kangtest3", false, 1234567);

        //when
        userService.join(user);
        user.setUserPwd("errorPwd");
        boolean actual = userService.login(user);

        //then
        assertThat(actual, is(false));
    }

    @Test
    public void joinTest() {
        //given
        String userId = "kangtest3";
        User user = new User(userId, "kangtest3", false, 1234567);

        //when
        userService.join(user);
        User actual = userService.findUser(userId);

        //then
        assertThat(actual.getUserId(), is(userId));
    }

    @Test
    public void joinReturnValueTest() {
        //given
        String userId = "kangtest3";
        User user = new User(userId, "kangtest3", false, 1234567);

        //when
        boolean actual = userService.join(user);

        //then
        assertThat(actual, is(true));
    }

    @Test
    public void isDuplicateTest() {
        //given
        String userId = "kangtest3";
        User user = new User(userId, "kangtest3", false, 1234567);

        //when
        userService.join(user);
        boolean actual = userService.isDuplicate(user);

        //then
        assertThat(actual, is(true));
    }
}