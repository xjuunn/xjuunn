# Security

#### 认证

判断用户的身份是否合法，用户去访问系统要求验证用户的身份信息，身份合法方可继续访问，不合法则拒绝访问。常见的用户身份认证方式：

* 用户名密码登录
* 二维码登录
* 手机短信登录
* 指纹认证

~~~ java
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/l1/**").hasRole("v1")
                .antMatchers("/l2/**").hasRole("v2")
                .antMatchers("/l1/**").hasRole("v3");

        // 没有权限默认跳转到登录页面
        http.formLogin();

    }
}

~~~

