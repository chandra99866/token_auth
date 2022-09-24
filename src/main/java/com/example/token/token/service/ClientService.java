package com.example.token.token.service;

import com.example.token.token.models.Clients;
import com.example.token.token.models.Role;

import java.util.List;

public interface ClientService {
    Clients saveClient(Clients clients);
    Role saveRole(Role role);
    void addRoleToUser(String name,String roleName);
    Clients getClient(String name);
    List<Clients> getClients();
}
