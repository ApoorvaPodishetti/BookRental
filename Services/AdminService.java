package Book.demo.Services;

import Book.demo.Entity.Admin;
import Book.demo.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long adminId, Admin adminDetails) {
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setUsername(adminDetails.getUsername());
            admin.setPassword(adminDetails.getPassword());
            admin.setFullName(adminDetails.getFullName());
            admin.setEmail(adminDetails.getEmail());
            return adminRepository.save(admin);
        }
        return null;
    }

    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }
}
