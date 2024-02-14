package com.rtkit.golos.core.service.implementation;

import com.rtkit.golos.core.access.UserRepo;
import com.rtkit.golos.core.dto.UserDto;
import com.rtkit.golos.core.entity.GolosUser;
import com.rtkit.golos.core.entity.UserRole;
import com.rtkit.golos.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        GolosUser user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Не найден пользователь с почтой: " + email);
        }

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;


        return new User(user.getEmail(), user.getPassHash(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getRole()));
    }

    private static List<GrantedAuthority> getAuthorities(UserRole userRole) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userRole.getTextRole()));
        return authorities;
    }

    private static List<GrantedAuthority> getAuthorities(Collection<UserRole> userRole) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : userRole) {
            authorities.add(new SimpleGrantedAuthority(role.getTextRole()));
        }

        return authorities;
    }
}
