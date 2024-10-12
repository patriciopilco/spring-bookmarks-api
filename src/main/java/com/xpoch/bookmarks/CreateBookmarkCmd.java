package com.xpoch.bookmarks;

public record CreateBookmarkCmd(String title, String url, Long userId) {
}
