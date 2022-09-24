package com.example.token.token.repo;

import com.example.token.token.models.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Clients, Long> {
    Clients findByuserName(String userName);
    Clients findByName(String name);
}
