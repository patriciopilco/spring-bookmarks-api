package com.xpoch.bookmarks;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository, UserRepository userRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.userRepository = userRepository;
    }

    public List<BookmarkDTO> findAll() {
        return bookmarkRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<BookmarkDTO> findBookmarkerById(Long id) {
        return bookmarkRepository.findBookmarkById(id);
    }

    public Long createBookmark(CreateBookmarkCmd cmd) {
        var bookmark = new Bookmark();
        bookmark.setTitle(cmd.title());
        bookmark.setUrl(cmd.url());
        bookmark.setUser(userRepository.getReferenceById(cmd.userId()));
        bookmark.setCreatedAt(Instant.now());
        return bookmarkRepository.save(bookmark).getId();
    }
}
