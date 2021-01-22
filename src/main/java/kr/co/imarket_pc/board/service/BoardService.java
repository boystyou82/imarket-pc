package kr.co.imarket_pc.board.service;

import kr.co.imarket_pc.board.dto.BoardsResopnse;
import kr.co.imarket_pc.common.RestTemplateApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final RestTemplateApi<BoardsResopnse> boardsResopnseApi;

    public BoardsResopnse getBoards() {
        return boardsResopnseApi.get("/v1/boards"
                , null
                , null
                , BoardsResopnse.class);
    }
}
