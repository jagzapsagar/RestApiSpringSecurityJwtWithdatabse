package com.example.demo.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.UsersInfo;

public class UserInfoUserDetails implements UserDetails {

	private String name;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserInfoUserDetails(UsersInfo userInfo) {
		name = userInfo.getName();
		password = userInfo.getPassword();
		authorities = Arrays.stream(userInfo.getRoles().split(",")).map(role -> "ROLE_" + role)
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		//System.out.println("In UserInfoUserDetails 27 " + authorities);

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//System.out.println("in GrantedAuthority " + authorities);
		return authorities;
	}

	@Override
	public String getPassword() { // TODO Auto-generated method stub
		//System.out.println(password);
		return password;
	}

	@Override
	public String getUsername() { // TODO Auto-generated method stub
		//System.out.println(name);
		return name;
	}

}
