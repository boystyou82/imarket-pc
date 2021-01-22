package kr.co.imarket_pc.display.dto;

import kr.co.imarket_pc.cart.dto.CartsGnbResopnse;
import kr.co.imarket_pc.mall.dto.MallsSpecialtyResopnse;
import kr.co.imarket_pc.member.dto.MembersOrdersCountResopnse;
import kr.co.imarket_pc.product.dto.RecentlyViewedProductsResopnse;
import kr.co.imarket_pc.search.dto.SearchWordsGnbResopnse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class DisplaysGnbResopnse {
	private DisplaysMainCategoriesResopnse displaysMainCategoriesResopnse;
	private RecentlyViewedProductsResopnse recentlyViewedProductsResopnse;
	private List<MallsSpecialtyResopnse> mallsSpecialtyResopnse;
	private SearchWordsGnbResopnse searchWordsGnbResopnse;
	private MembersOrdersCountResopnse membersOrdersCountResopnse;
	private CartsGnbResopnse cartsGnbResopnse;
	private DisplaysShopsBannersResopnse displaysShopsBannersResopnse;
}