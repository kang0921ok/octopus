package osc.gobaby.octopus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import osc.gobaby.octopus.user.entity.User;
import osc.gobaby.octopus.user.UserService;

import static org.junit.Assert.*;
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
        User user = new User(userId, "kangtest3", false, "1234567");

        //when
        userService.join(user);
        boolean actual = userService.login(user);

        //then
        assertTrue(actual);
    }

    @Test
    public void loginFalseTest(){
        //given
        String userId = "kangtest3";
        User user = new User(userId, "kangtest3", false, "1234567");

        //when
        userService.join(user);
        user.setUserPwd("errorPwd");
        boolean actual = userService.login(user);

        //then
        assertFalse(actual);
    }

    @Test
    public void joinTest() {
        //given
        String userId = "kangtest3";
        User user = new User(userId, "kangtest3", false, "1234567");

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
        User user = new User(userId, "kangtest3", false, "1234567");

        //when
        boolean actual = userService.join(user);

        //then
        assertTrue(actual);
    }

    @Test
    public void isDuplicateTest() {
        //given
        String userId = "kangtest3";
        User user = new User(userId, "kangtest3", false, "1234567");

        //when
        userService.join(user);
        boolean actual = userService.isDuplicate(user);

        //then
        assertTrue(actual);
    }
}