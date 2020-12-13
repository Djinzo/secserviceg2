package org.sid.secserviceg2.service;

import lombok.AllArgsConstructor;
import org.sid.secserviceg2.entities.AppRole;
import org.sid.secserviceg2.entities.AppUser;
import org.sid.secserviceg2.repositorys.AppRoleRepository;
import org.sid.secserviceg2.repositorys.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements  AccountService{
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;



    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw=appUser.getPassword(); appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username); AppRole appRole=appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }
}
