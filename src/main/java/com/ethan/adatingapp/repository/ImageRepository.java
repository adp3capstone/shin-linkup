package com.ethan.adatingapp.repository;

/* Image Factory.java
Author: Ethan Le Roux (222622172)
Date:10 July 2025
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ethan.adatingapp.domain.Image;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByUser_UserId(Long userId);
}
