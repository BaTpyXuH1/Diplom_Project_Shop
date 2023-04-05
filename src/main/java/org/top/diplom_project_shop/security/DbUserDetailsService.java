package org.top.diplom_project_shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.top.diplom_project_shop.model.dao.client.IDaoClient;
import org.top.diplom_project_shop.model.entity.Client;



@Service
public class DbUserDetailsService implements UserDetailsService {

   @Autowired
   private IDaoClient daoClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = daoClient.getClientByLogin(username);
        if (client == null) {
            throw new UsernameNotFoundException(username);
        }
        return client;
    }
}
