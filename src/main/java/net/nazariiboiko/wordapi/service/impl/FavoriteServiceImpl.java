package net.nazariiboiko.wordapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.nazariiboiko.wordapi.dto.WordDto;
import net.nazariiboiko.wordapi.entity.FavoritesEntity;
import net.nazariiboiko.wordapi.entity.UserEntity;
import net.nazariiboiko.wordapi.entity.WordEntity;
import net.nazariiboiko.wordapi.exception.NotFoundException;
import net.nazariiboiko.wordapi.mapper.WordMapper;
import net.nazariiboiko.wordapi.repository.FavoritesRepository;
import net.nazariiboiko.wordapi.repository.UserRepository;
import net.nazariiboiko.wordapi.repository.WordRepository;
import net.nazariiboiko.wordapi.service.FavoriteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoritesRepository favoritesRep;
    private final UserRepository userRep;
    private final WordRepository wordRep;

    private WordMapper wordMapper = WordMapper.INSTANCE;

    @Override
    @Transactional
    public void changeStatement(Long userId, Long wordId) {
        if(favoritesRep.existsByUserIdAndWordId(userId, wordId)) {
            favoritesRep.deleteByUserIdAndWordId(userId, wordId);
        }
        else {
            UserEntity userEntity = userRep.findById(userId)
                    .orElseThrow(() -> new NotFoundException("User not found."));
            WordEntity wordEntity = wordRep.findById(wordId)
                    .orElseThrow(() -> new NotFoundException("Word not found."));
            FavoritesEntity newFavorite = new FavoritesEntity();
            newFavorite.setUser(userEntity);
            newFavorite.setWord(wordEntity);
            favoritesRep.save(newFavorite);
        }
    }

    public Page<WordDto> getPageOfFavorites(Pageable pageable, Long userId) {
        UserEntity userFromDb = userRep.getById(userId);
        List<WordDto> wordList = userFromDb.getFavorites().stream()
                .map(x -> wordMapper.mapEntityToDto(x.getWord()))
                .collect(Collectors.toList());
        return new PageImpl<>(wordList, pageable, wordList.size());
    }
}
