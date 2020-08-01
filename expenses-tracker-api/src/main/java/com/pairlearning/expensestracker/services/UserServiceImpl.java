package com.pairlearning.expensestracker.services;

import com.pairlearning.expensestracker.domain.User;
import com.pairlearning.expensestracker.exceptions.EtAuthException;

public class UserServiceImpl implements UserService {

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        return null;
    }
}
