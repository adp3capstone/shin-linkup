package za.ac.cput.linkup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.linkup.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
