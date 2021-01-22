package kr.co.imarket_pc.product.dto;

import kr.co.imarket_pc.vo.product.RecentlyViewedProductVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RecentlyViewedProductsResopnse {
	private List<RecentlyViewedProductVo> recentlyViewedProductList;
}