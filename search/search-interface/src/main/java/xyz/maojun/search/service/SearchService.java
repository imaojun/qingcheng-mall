package xyz.maojun.search.service;

import xyz.maojun.common.pojo.SearchResult;

public interface SearchService {
    SearchResult search(String keyword, int page,int rows) throws Exception;
}
