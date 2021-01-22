package kr.co.imarket_pc.display.dto;

import kr.co.imarket_pc.vo.display.MainTotalCategoryVo;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class DisplaysMainCategoriesResopnse {
	private List<MainTotalCategoryVo> mainTotalCategoryList;
}