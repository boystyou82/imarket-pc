package kr.co.imarket_pc.board.dto;

import kr.co.imarket_pc.vo.board.NoticeVo;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class BoardsResopnse {
    private List<NoticeVo> noticeInfoList;
}
