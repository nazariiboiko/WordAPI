package net.nazariiboiko.wordapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nazariiboiko.wordapi.dto.WordDto;
import net.nazariiboiko.wordapi.security.jwt.JwtUser;
import net.nazariiboiko.wordapi.service.FavoriteService;
import net.nazariiboiko.wordapi.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorite")
public class FavoriteController {

    private final UserService userService;
    private final FavoriteService favoriteService;


    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<WordDto>> getFavoriteByUser(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int size
    ) {
        JwtUser user = userService.getPrincipal();
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<WordDto> page = favoriteService.getPageOfFavorites(pageable, user.getId());
        return new ResponseEntity(page, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> changeFavoriteStatement(@RequestParam Long wordId) {
        JwtUser user = userService.getPrincipal();
        favoriteService.changeStatement(user.getId(), wordId);
        return ResponseEntity.ok().build();
    }
}
