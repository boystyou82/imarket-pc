package kr.co.imarket_pc.display.dto;

import kr.co.imarket_pc.vo.display.DisplaysShopsBannerVo;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisplaysShopsBannersResopnse {
	private List<DisplaysShopsBannerVo> displayBannerInfoList;
}