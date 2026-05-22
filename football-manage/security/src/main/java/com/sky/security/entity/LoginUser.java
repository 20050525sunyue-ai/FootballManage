package com.sky.security.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.sky.content.model.po.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private SysUser sysUser;

    private List<String> permissions;


    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> list = new ArrayList<>();


    public LoginUser(SysUser sysUser, List<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (list.isEmpty() && permissions != null) {
            permissions.forEach(item -> list.add(new SimpleGrantedAuthority(item)));
        }
        return list;
    }

    /*
    list 里面存的是 SimpleGrantedAuthority 权限对象，它其实是根据 permissions 动态转换出来的运行时数据，不是真正的核心用户数据。
真正需要长期保存的是：

permissions = ["course:add","course:delete"]

当 Spring Security 需要权限时，会自动调用：

getAuthorities()

再把字符串权限：

"course:add"

转换成：

new SimpleGrantedAuthority("course:add")

也就是说：

list

随时都可以重新生成。

所以在把 LoginUser 存入 Redis 时，没必要把 list 也序列化进去，否则：

Redis 数据会变大
会产生重复数据
SimpleGrantedAuthority 这类 Security 对象序列化可能复杂甚至报错
权限对象本来就是给 Security 临时使用的，不适合长期存储

因此：

@JSONField(serialize = false)

就是告诉 FastJSON：

“这个字段不要转成 JSON，不要存入 Redis，需要时再动态生成即可。”
     */


    @Override
    public String getPassword() {
        return sysUser == null ? null : sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser == null ? null : sysUser.getUsername();
    }

    // 账号是否过期
    @Override
    public boolean isAccountNonExpired() {
        return sysUser != null;
    }

/*    账号是否未被锁定
 *    true：正常
 *    false：锁定
 */
    @Override
    public boolean isAccountNonLocked() {
        return sysUser != null;
    }

//    密码是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return sysUser != null;
    }


//    账号是否启用
    @Override
    public boolean isEnabled() {
        return sysUser != null;
    }
}
