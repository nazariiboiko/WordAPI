package net.nazariiboiko.wordapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.nazariiboiko.wordapi.dto.WordDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Favorites", description = "Favorites management APIs")
@RequestMapping("/api/v1/favorite")
public interface FavoriteApi {
    @Operation(summary = "Return the user's favorite words.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<Page<WordDto>> getFavoriteByUser(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int size);

    @Operation(summary = "Add or remove user's favorite word.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<Void> changeFavoriteStatement(@RequestParam Long wordId);
}
