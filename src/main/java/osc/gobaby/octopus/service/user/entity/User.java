package osc.gobaby.octopus.service.user.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
public class User implements UserDetails {
	private String userId;
	private String userPwd;
	private Boolean master;
	private String secretKey;

	private List<? extends GrantedAuthority> authorities;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;

	public User() {
	}

	public User(String userId, String userPwd, Boolean master, String secretKey) {
		this.userId = userId;
		this.userPwd = userPwd;
		this.master = master;
		this.secretKey = secretKey;
	}

	public User(String userId, String userPwd) {
		this.userId = userId;
		this.userPwd = userPwd;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.userPwd;
	}

	@Override
	public String getUsername() {
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
}
