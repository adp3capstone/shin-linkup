package com.ethan.adatingapp.repository;

import com.ethan.adatingapp.domain.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference,Long> {
}
