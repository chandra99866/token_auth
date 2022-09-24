package com.example.token.token.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.token.token.models.Clients;
import com.example.token.token.models.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.token.token.repo.ClientRepo;
import com.example.token.token.repo.RoleRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class ClientsImpl implements ClientService, UserDetailsService {
    private final ClientRepo clientRepo;
    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;


    @Override
    public Clients saveClient(Clients clients) {
        log.info("saving user");
        clients.setPassword(passwordEncoder.encode(clients.getPassword()));
    return clientRepo.save(clients);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("save role",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String name, String roleName) {
        log.info("adding role",roleName,name);
        Clients clients = clientRepo.findByuserName(name);
        Role role = roleRepo.findByName(roleName);
        log.info(clients.getId().toString()+"  "+clients.getName());
        clients.getRole().add(role);

    }

    @Override
    public Clients getClient(String userName) {
        log.info("geting user",userName);
        return clientRepo.findByuserName(userName);
    }

    @Override
    public List<Clients> getClients() {
        log.info("getting all users");
        return clientRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Clients clients = clientRepo.findByuserName(username);
        if(clients==null){
            log.error("no user found in the database");
            throw  new UsernameNotFoundException("user not found in the database");

        }else {
            log.info("user found "+clients.getName());
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        clients.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(clients.getUserName(),clients.getPassword(),authorities);
    }
}
