package com.springbootionic.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.springbootionic.cursomc.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // usuário logado

		} catch (Exception e) {
			return null;
		}
	}

}
