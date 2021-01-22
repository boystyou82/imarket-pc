package kr.co.imarket_pc.member.dto;

import kr.co.imarket_pc.vo.member.MemberMainMallVo;
import kr.co.imarket_pc.vo.plant.SpecialityMainVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MembersSpecialityMainResopnse {
	private MemberMainMallVo memberMainMall;
	private SpecialityMainVo specialityMain;
}