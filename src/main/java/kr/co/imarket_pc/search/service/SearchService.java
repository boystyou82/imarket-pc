package kr.co.imarket_pc.search.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.imarket_pc.common.RestTemplateApi;
import kr.co.imarket_pc.search.dto.SearchWordsGnbResopnse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    private final RestTemplateApi<SearchWordsGnbResopnse> searchWordsGnbResopnseApi;
    public final ObjectMapper objectMapper;

    public SearchWordsGnbResopnse getSearchWordsGnb() {
        return searchWordsGnbResopnseApi.get("/v1/search/words/gnb"
                , null
                , null
                , SearchWordsGnbResopnse.class);
    }
}
