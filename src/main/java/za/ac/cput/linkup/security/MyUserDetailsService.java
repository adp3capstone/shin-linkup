package za.ac.cput.linkup.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.ac.cput.linkup.repository.AdminRepository;
import za.ac.cput.linkup.repository.UserRepository;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public MyUserDetailsService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user != null) return new MyUserDetails(user);

        var admin = adminRepository.findByUsername(username);
        if (admin != null) return new MyUserDetails(admin);

        throw new UsernameNotFoundException("User or admin not found: " + username);
    }
}
