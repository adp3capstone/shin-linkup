package za.ac.cput.linkup.controller;

import za.ac.cput.linkup.domain.Chat;
import za.ac.cput.linkup.domain.Match;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.service.ChatService;
import za.ac.cput.linkup.service.MatchService;
import za.ac.cput.linkup.service.UserService;
import za.ac.cput.linkup.util.MatchDTO;
import za.ac.cput.linkup.util.MatchWithUserDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/match")
@CrossOrigin(origins = "http://localhost:8081")
public class MatchController {
    private final MatchService matchService;
    private final UserService userService;
    private final ChatService chatService;

    public MatchController(MatchService matchService, UserService userService, ChatService chatService) {
        this.matchService = matchService;
        this.userService = userService;
        this.chatService = chatService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMatch(@RequestParam Long user1Id, @RequestParam Long user2Id) {
        User user1 = userService.read(user1Id);
        User user2 = userService.read(user2Id);

        if (user1 == null || user2 == null) {
            return ResponseEntity.badRequest().body("One or both users not found");
        }

        Match match = matchService.createMatchIfLikedBack(user1, user2);

        if (match == null) {
            return ResponseEntity.ok("No match yet. Waiting for both users to like each other.");
        }
        Chat chat = chatService.getOrCreateChat(match);

        return ResponseEntity.ok(new MatchDTO(match));
    }


    @GetMapping("/allbyid/{id}")
    public ResponseEntity<?> getAllUserMatches(@PathVariable Long id){
        User user = userService.read(id);
        if(user!=null){
            List<Match> matches = matchService.findAllMatchesForUser(user);
            List<MatchDTO> matchDTOS =  matches.stream().map(match -> new MatchDTO(match)).collect(Collectors.toList());

            if(matches.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(matchDTOS);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<?> getMatchById(@PathVariable Long matchId){
        Match match = matchService.read(matchId);
        if(match==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MatchWithUserDetailsDTO(match));
    }
}
