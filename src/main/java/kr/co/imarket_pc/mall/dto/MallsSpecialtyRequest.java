package kr.co.imarket_pc.mall.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MallsSpecialtyRequest {
	private String highDisplayNo;
	private String shopNo;
}