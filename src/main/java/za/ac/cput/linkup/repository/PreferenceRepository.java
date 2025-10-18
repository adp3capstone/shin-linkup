package za.ac.cput.linkup.repository;

/**
 * PreferenceRepository.java
 * Author: Ethan Le Roux (222622172)
 */

import za.ac.cput.linkup.domain.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PreferenceRepository extends JpaRepository<Preference,Long> {
    @Query("SELECT p FROM Preference p WHERE p.user.userId = :userid")
    Preference findByUserId(@Param("userid") Long userid);
}
