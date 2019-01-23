package com.cignex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cignex.entity.CustomUserDetail;
import com.cignex.entity.User;
import com.cignex.repository.UserRepository;
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user=userRepository.findByName(name);
		if(user==null) {
			throw new UsernameNotFoundException("sorry user not  found");
		}
		System.out.println(user.getName());
		return new CustomUserDetail(user);
	}

}
