package za.ac.cput.linkup.repository;

/* Image Factory.java
Author: Ethan Le Roux (222622172)
Date:10 July 2025
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.linkup.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by userId or imageUrl
}
