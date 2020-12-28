package cn.codenst.authservice.service;

import cn.codenst.authservice.entity.User;
import cn.codenst.authservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/28 11:59
 * @description：
 * @modified By：
 * @version: $
 */
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userMapper.findByUserName(username);
        List<? extends GrantedAuthority> authorities = new ArrayList();
        return  new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
