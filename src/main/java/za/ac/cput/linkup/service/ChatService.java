package za.ac.cput.linkup.service;

import za.ac.cput.linkup.domain.Chat;
import za.ac.cput.linkup.domain.Match;
import za.ac.cput.linkup.domain.User;
import za.ac.cput.linkup.repository.ChatRepository;
import za.ac.cput.linkup.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final MatchRepository matchRepository;

    public ChatService(ChatRepository chatRepository, MatchRepository matchRepository) {
        this.chatRepository = chatRepository;
        this.matchRepository = matchRepository;
    }

    public List<Chat> getChatsForUser(User user) {
        return chatRepository.findChatsForUser(user);
    }

    public Chat getOrCreateChat(Match match) {
        return chatRepository.findByMatch(match)
                .orElseGet(() -> {
                    Chat chat = new Chat();
                    chat.setMatch(match);
                    return chatRepository.save(chat);
                });
    }

    public Chat getChat(Long chatId){
        return chatRepository.findById(chatId).orElse(null);
    }

    public Chat createWithMatch(Long  matchId) {
        Match match = matchRepository.findById(matchId).orElse(null);
        if(match == null) {
            return null;
        }
        Chat chat = new Chat();
        chat.setMatch(match);
        return chatRepository.save(chat);
    }
}
