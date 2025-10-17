package za.ac.cput.linkup.service;

/**
 * AdminService.java
 * Author: Ethan Le Roux (222622172)
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.linkup.domain.Admin;
import za.ac.cput.linkup.repository.AdminRepository;

import java.util.List;
import java.util.Optional;
import za.ac.cput.linkup.domain.*;
import za.ac.cput.linkup.repository.*;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin update(Admin admin) {
        if (admin.getUserId() == null || !adminRepository.existsById(admin.getUserId())) {
            return null;
        }
        return adminRepository.save(admin);
    }

    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

    public Admin findByUsernameAndPassword(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }
}
