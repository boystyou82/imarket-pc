package kr.co.imarket_pc.mall.dto;

import kr.co.imarket_pc.display.dto.DisplaysMainCategoriesResopnse;
import kr.co.imarket_pc.product.dto.RecentlyViewedProductsResopnse;
import kr.co.imarket_pc.vo.mall.SpecialityMallVo;
import kr.co.imarket_pc.vo.mall.SpecialtyMallCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class MallsSpecialtyResopnse {
	private List<SpecialityMallVo> specialityMallList;
	private List<SpecialtyMallCategoryVo> specialtyMallCategoryList;
}