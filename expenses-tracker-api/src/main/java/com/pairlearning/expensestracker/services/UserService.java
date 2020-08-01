package com.pairlearning.expensestracker.services;

import com.pairlearning.expensestracker.domain.User;
import com.pairlearning.expensestracker.exceptions.EtAuthException;

public interface UserService {
    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;
}
