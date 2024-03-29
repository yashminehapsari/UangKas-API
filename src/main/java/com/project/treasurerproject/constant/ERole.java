package com.project.treasurerproject.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum ERole {
    ROLE_MEMBER(Set.of(Permissions.M_CREATE,Permissions.M_UPDATE,Permissions.M_DELETE,Permissions.M_READ)),
    ROLE_ADMIN(Set.of(Permissions.A_CREATE,Permissions.A_UPDATE,Permissions.A_DELETE,Permissions.A_READ))
    ;
    private final Set<Permissions> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
        .stream()
        .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
        .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority(this.name()));
        return authorities;
    }

}
