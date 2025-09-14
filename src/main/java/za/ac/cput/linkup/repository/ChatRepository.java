package za.ac.cput.linkup.repository;

import za.ac.cput.linkup.domain.Chat;
import za.ac.cput.linkup.domain.Match;
import za.ac.cput.linkup.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
    Optional<Chat> findByMatch(Match match);
    @Query("SELECT c FROM Chat c WHERE c.match.user1 = :user OR c.match.user2 = :user")
    List<Chat> findChatsForUser(@Param("user") User user);
}
