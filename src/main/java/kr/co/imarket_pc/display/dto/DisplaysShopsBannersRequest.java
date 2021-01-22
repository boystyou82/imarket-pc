package kr.co.imarket_pc.display.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DisplaysShopsBannersRequest {
	private String displayNo;
	private String shopNo;
}