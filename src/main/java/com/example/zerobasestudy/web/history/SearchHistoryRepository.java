package com.example.zerobasestudy.web.history;

import java.util.List;

public interface SearchHistoryRepository {
    void createSearchHistory(SearchHistory searchHistory);
    void deleteSearchHistoryById(Long id);
    List<SearchHistory> findAll();

}
