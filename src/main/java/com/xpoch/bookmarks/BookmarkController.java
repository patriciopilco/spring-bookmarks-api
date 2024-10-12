package com.xpoch.bookmarks;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    List<BookmarkDTO> findAll() {
        return bookmarkService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<BookmarkDTO> findById(@PathVariable Long id) {
        return bookmarkService.findBookmarkerById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<BookmarkDTO> create(@RequestBody @Valid CreateBookmarkRequest payload) {
        CreateBookmarkCmd cmd = new CreateBookmarkCmd(payload.title(), payload.url(), 1L);
        Long id  = bookmarkService.createBookmark(cmd);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .build(id);
        return ResponseEntity.created(uri).build();
    }

    record CreateBookmarkRequest(@NotEmpty String url, @NotEmpty String title) {

    }
}
