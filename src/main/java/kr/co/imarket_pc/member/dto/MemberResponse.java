package kr.co.imarket_pc.member.dto;

import kr.co.imarket_pc.vo.member.MemberVo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {
    private MemberVo memberDto;
    private MemberVo enterpriseDto;
}
