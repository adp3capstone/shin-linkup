package com.ethan.adatingapp.repository;

import com.ethan.adatingapp.domain.Preference;
import com.ethan.adatingapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PreferenceRepository extends JpaRepository<Preference,Long> {
    @Query("SELECT p FROM Preference p WHERE p.user.userId = :userid")
    Preference findByUserId(@Param("userid") Long userid);
}
