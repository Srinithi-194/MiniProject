package com.mph.MiniProject.Service;

public class LoginServiceImpl implements LoginService{

	 private static final String USERNAME = "admin";
	    private static final String PASSWORD = "admin123";

	    @Override
	    public boolean login(String username, String password) {
	        return username.equals(USERNAME) && password.equals(PASSWORD);
	}

}
