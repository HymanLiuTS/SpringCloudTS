package cn.codenst.authservice.mapper;

import cn.codenst.authservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/28 12:10
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class UserMapper {
    private HashMap<String, User> users = null;

    public UserMapper() {
        users = new HashMap<>();
        User zhangsan = new User();
        zhangsan.setId(1L);
        zhangsan.setUsername("zhangsan");
        zhangsan.setPassword(new BCryptPasswordEncoder().encode("123456"));
        zhangsan.setRoles("ROLE_USER");
        users.put("zhangsan", zhangsan);
        User lisi = new User();
        lisi.setId(2L);
        lisi.setUsername("lisi");
        lisi.setPassword(new BCryptPasswordEncoder().encode("123456"));
        lisi.setRoles("ROLE_USER");
        users.put("lisi", lisi);
    }

    public User findByUserName(String username) {
        if (users.containsKey(username)) {
            return users.get(username);
        }
        return null;
    }
}
