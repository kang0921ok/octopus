package osc.gobaby.octopus.support;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import osc.gobaby.octopus.service.user.UserService;
import osc.gobaby.octopus.service.user.entity.User;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
 
    @Autowired
    UserService userService;
 
    @Override
    public Authentication authenticate(Authentication authentication) 
            throws AuthenticationException {
        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();
 
        User user = userService.login(new User(userName, password));
        if (user == null)
            throw new BadCredentialsException("Login Error !!");
        user.setUserPwd(null);
 
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        if (user.isMaster() != null && user.isMaster()) {
        	authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }
 
    @Override
    public boolean supports(Class authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
