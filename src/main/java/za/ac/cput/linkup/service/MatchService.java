package za.ac.cput.linkup.service;

import za.ac.cput.linkup.domain.Match;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.repository.LikeRepository;
import za.ac.cput.linkup.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final LikeRepository likeRepository;

    public MatchService(MatchRepository matchRepository, LikeRepository likeRepository) {
        this.matchRepository = matchRepository;
        this.likeRepository = likeRepository;
    }

    public Match createMatchIfLikedBack(User user1, User user2) {
        // Check if match already exists in either order
        Match existingMatch = matchRepository.findByUser1AndUser2(user1, user2);
        if (existingMatch == null) {
            existingMatch = matchRepository.findByUser1AndUser2(user2, user1);
        }
        if (existingMatch != null) {
            return existingMatch; // Don’t create a duplicate
        }

        // Check if user2 liked user1 back
        boolean likedBack = likeRepository.existsByLikerAndLiked(user2, user1);
        if (!likedBack) {
            return null;
        }

        // Create new match
        Match match = new Match();
        match.setUser1(user1);
        match.setUser2(user2);
        match.setMatchedAt(LocalDateTime.now());
        return matchRepository.save(match);
    }


    public List<Match> findAllMatchesForUser(User user) {
        return matchRepository.findAllMatchesForUser(user);
    }

    public Match read(Long matchId){
        return matchRepository.findById(matchId).orElse(null);
    }
}
