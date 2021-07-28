package com.tainamoreno.helpdesk;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tainamoreno.api.repository.UserRepository;
import com.tainamoreno.api.security.entity.User;
import com.tainamoreno.api.security.enums.ProfileEnum;

@EntityScan("com.tainamoreno.api")
@EnableJpaRepositories("com.tainamoreno.api.repository")
@SpringBootApplication
public class HelpDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}
	
    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            initUsers(userRepository, passwordEncoder);
        };
	}
    
  //Ao iniciar a aplicação já vai criar o usuário
	//inserindo usuário no banco
    private void initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        User admin = new User();
        admin.setEmail("admin@helpdesk.com");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setProfile(ProfileEnum.ROLE_ADMIN);

        User find = userRepository.findByEmail("admin@helpdesk.com");
        if (find == null) {
            userRepository.save(admin);
        }
    }
}
