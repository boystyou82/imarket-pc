package kr.co.imarket_pc.search.dto;

import kr.co.imarket_pc.vo.search.SearchWordVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SearchWordsGnbResopnse {
    private List<SearchWordVo> searchWordList;
}
