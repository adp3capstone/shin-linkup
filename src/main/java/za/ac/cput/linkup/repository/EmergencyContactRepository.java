package za.ac.cput.linkup.repository;

import za.ac.cput.linkup.domain.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
    public List<EmergencyContact> findAllByUser_userId(Long  userId);
}
