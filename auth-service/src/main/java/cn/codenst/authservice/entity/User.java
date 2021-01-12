package cn.codenst.authservice.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/28 12:04
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class User implements UserDetails {
    private static final long serialVersionUID = 2020L;
    private String password;
    private String username;
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled = true;
    private Long id;
    String roles;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void setRoles(String roles) {
        this.roles = roles;
        if (StringUtils.isEmpty(roles) == false) {
            this.authorities = new HashSet<>();
            List<GrantedAuthority> tmp = AuthorityUtils.commaSeparatedStringToAuthorityList(this.getRoles());
            tmp.stream().forEach(x -> this.authorities.add(x));
        }
    }
}
