package com.chat.aj.unote.Accounts.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Accounts {
    @Id
    public Long id;
    public String username;
    public String email;
    public String password;
}
