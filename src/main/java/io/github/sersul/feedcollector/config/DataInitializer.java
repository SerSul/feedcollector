package io.github.sersul.feedcollector.config;

import io.github.sersul.feedcollector.entity.security.Role;
import io.github.sersul.feedcollector.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RolesRepository roleRepository;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        createRoleIfNotExists("ROLE_USER");
        createRoleIfNotExists("ROLE_ADMIN");
    }

    private void createRoleIfNotExists(String roleName) {
        boolean exists = roleRepository.existsByName(roleName);
        if (!exists) {
            var role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
            System.out.println("Role created: " + roleName);
        }
    }
}

