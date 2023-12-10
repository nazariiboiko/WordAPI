package net.nazariiboiko.wordapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.nazariiboiko.wordapi.dto.WordDto;
import net.nazariiboiko.wordapi.model.WordFilterModel;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Words", description = "Word management APIs")
@RequestMapping("/api/v1/word")
public interface WordApi {

    @Operation(summary = "Daily Word", description = "Return the daily word that changes every 24 hours.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<WordDto> getDailyWord();

    @Operation(summary = "Word by Id", description = "Return word by it's id.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<WordDto> getWordById(@PathVariable Long id);

    @Operation(summary = "List of words", description = "Return a page of all words")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<WordDto>> getPageOfWords(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int size);

    @Operation(summary = "Filtered words", description = "Return a page of filtered words")
    @PostMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<WordDto>> getPageOfWordsByFilter(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int size,
            @RequestBody WordFilterModel filter);
}
