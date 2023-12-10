package net.nazariiboiko.wordapi.service.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nazariiboiko.wordapi.dto.WordDto;
import net.nazariiboiko.wordapi.entity.WordEntity;
import net.nazariiboiko.wordapi.exception.NotFoundException;
import net.nazariiboiko.wordapi.mapper.WordMapper;
import net.nazariiboiko.wordapi.model.WordFilterModel;
import net.nazariiboiko.wordapi.repository.WordRepository;
import net.nazariiboiko.wordapi.service.WordService;
import net.nazariiboiko.wordapi.util.WordFilterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRep;
    private final WordMapper wordMapper = WordMapper.INSTANCE;
    private final EntityManager entityManager;
    private Long dailyId;

    @Override
    public Page<WordDto> getAllWords(Pageable pageable) {
        return wordRep
                .findAll(pageable)
                .map(wordMapper::mapEntityToDto);
    }

    @Override
    public Page<WordDto> getWordsByFilter(Pageable pageable, WordFilterModel model) {
        List<WordEntity> filteredList = WordFilterBuilder.buildQuery(model, entityManager);
        int startIndex = pageable.getPageNumber() * pageable.getPageSize();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), filteredList.size());
        long total = filteredList.size();
        if(startIndex < endIndex) {
            List<WordDto> dtoList = filteredList.subList(startIndex, endIndex).stream()
                    .map(wordMapper::mapEntityToDto)
                    .collect(Collectors.toList());

            return new PageImpl<>(dtoList, pageable, total - pageable.getPageSize());
        }
        return null;
    }

    @Override
    public WordDto getDailyWord() {
        return wordRep.findById(dailyId)
                .map(wordMapper::mapEntityToDto)
                .orElseThrow(() -> new NotFoundException("Word not found."));
    }

    @Override
    public WordDto getWord(Long id) {
        return wordRep.findById(id)
                .map(wordMapper::mapEntityToDto)
                .orElseThrow(() -> new NotFoundException("Word not found."));
    }

    @Override
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000, initialDelay = 0)
    public void updateDailyId() {
        dailyId = generateDailyId();
        log.info("Daily ID updated: " + dailyId);
    }

    private Long generateDailyId() {
        return wordRep.getRandomWord(1L).get(0).getId();
    }
}
