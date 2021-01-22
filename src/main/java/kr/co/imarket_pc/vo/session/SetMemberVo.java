package kr.co.imarket_pc.vo.session;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SetMemberVo {
    private String memberNo;
    private String sessionId;
    private String enterpriseNo;
    private String supplyCtrtSeq;
    private String exclTp;
}
