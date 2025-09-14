package za.ac.cput.linkup.repository;

import za.ac.cput.linkup.domain.Match;
import za.ac.cput.linkup.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {
    Match findByUser1AndUser2(User user1, User user2);
    List<Match> findByUser1OrUser2(User user1, User user2);

    @Query("SELECT m FROM Match m WHERE m.user1 = :user OR m.user2 = :user")
    List<Match> findAllMatchesForUser(@Param("user") User user);

    @Query("SELECT m FROM Match m WHERE (m.user1 = :user1 AND m.user2 = :user2) " +
            "OR (m.user1 = :user2 AND m.user2 = :user1)")
    Match findByUsers(@Param("user1") User user1, @Param("user2") User user2);

}
