package com.epita.fr;

import com.epita.fr.models.Users;
import com.epita.fr.services.UserService;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Users users = new Users();
        UserService userService  = new UserService();
        userService.authenticateUser(users);

    }
}
