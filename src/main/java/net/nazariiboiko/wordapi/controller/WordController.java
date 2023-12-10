package net.nazariiboiko.wordapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nazariiboiko.wordapi.dto.WordDto;
import net.nazariiboiko.wordapi.model.WordFilterModel;
import net.nazariiboiko.wordapi.service.WordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/word")
public class WordController {
    private final WordService wordService;

    @GetMapping
    public ResponseEntity<WordDto> getDailyWord() {
        WordDto wordDto = wordService.getDailyWord();
        return ResponseEntity.ok(wordDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WordDto> getWordById(@PathVariable Long id) {
        WordDto wordDto = wordService.getWord(id);
        return ResponseEntity.ok(wordDto);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<WordDto>> getPageOfWords(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<WordDto> page = wordService.getAllWords(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/filter")
    public ResponseEntity<Page<WordDto>> getPageOfWordsByFilter(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestBody WordFilterModel filter)
    {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<WordDto> page = wordService.getWordsByFilter(pageable, filter);
        log.info("get model {}", filter);
        return ResponseEntity.ok(page);
    }
}
