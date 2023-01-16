package com.bs.spring.member.model.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member implements UserDetails{
	private String userId;
	private String password;
	private String userName;
	private String gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 사용자의 권한을 등록해주는 메소드, DB에 권한 컬럼을 만들었으면 그걸 쓰면 되고, 아니면 이 메소드를 쓰면 된다.
		// SimpleGrantedAuthority 클래스를 이용해서 권한을 등록
		List<GrantedAuthority> auth = new ArrayList();
		auth.add(new SimpleGrantedAuthority("ROLE_USER"));
		if(userId.equals("admin")) auth.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		return auth;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userId;
	}
	@Override
	public boolean isAccountNonExpired() {
		//true 처리해야함..
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		//true 처리해야함..		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		//true 처리해야함..
		return true;
	}
	@Override
	public boolean isEnabled() {
		//true 처리해야함..
		return true;
	}
}
