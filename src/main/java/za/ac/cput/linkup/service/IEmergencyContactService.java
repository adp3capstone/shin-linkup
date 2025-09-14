package za.ac.cput.linkup.service;

import za.ac.cput.linkup.domain.EmergencyContact;

import java.util.Optional;

public interface IEmergencyContactService extends IService<EmergencyContact, Long> {
    boolean delete(long id);
    EmergencyContact findById(Long id);
    Optional<EmergencyContact> findByUser(Long userId);
}
