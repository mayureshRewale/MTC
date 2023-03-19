package neo.mtc.mtcsecurity.config;

import lombok.RequiredArgsConstructor;
import neo.mtc.mtcdao.entity.MtcRoleEntity;
import neo.mtc.mtcdao.entity.MtcUserEntity;
import neo.mtc.mtcdao.repository.MtcUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@ComponentScan({"neo.mtc"})
public class MtcApplicationConfig {

    private final MtcUserRepository qbUserRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            MtcUserEntity mtcUserEntity = qbUserRepository.findByMuUsername(username);
            User user = new User(mtcUserEntity.getMuUsername(), mtcUserEntity.getMuPassword(), Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, getGrantedAuthorities(mtcUserEntity.getMtcRoles()));
            return user;
        };
    }

    private List<GrantedAuthority> getGrantedAuthorities(Collection<MtcRoleEntity> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (MtcRoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getMrName()));
        }
        return authorities;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
