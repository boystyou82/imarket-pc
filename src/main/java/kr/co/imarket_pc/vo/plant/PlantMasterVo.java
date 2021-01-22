/*
 * Created by hwkim on 2020. 7. 30
 */

package kr.co.imarket_pc.vo.plant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlantMasterVo {
	// 기획전 마스터
	private String plantId;
	private String templetId;
	private String plantTitle;
	private String plantKind;
	private String plantUseYn;
	private String displayStrDate;
	private String displayEndDate;
	private String displayType;
	private String plantAdmNo;
	private String plantListBanner;
	private String plantGuideSentence;
	private String plantThumbnailBanner;
	private String plantHeader;
	private String plantMobileHeader;
	private String bgColor;
	private String snsShareCnt;
	private String prdImg;
	private String saleUnitcost;
	private String distCost;

	// 기획전 카테고리
	private String regNo;
	private String regNm;
	private String regDts;
	private String modNo;
	private String modNm;
	private String modDts;

	private String cateId;
	private String cateName;
	private String cateType;
	private String cateLink;
	private String rowProductCount;
	private String cateViewOption;
	private String cateContent;

	// 기획전 상품
	private String prdNo;
	private String prdNm;
	private String sort;
	private String displayYn;
	private String prdState;
	private String prdIcon;
	private String saleStatTp;
	private String rn;

	// 기획전 공통코드
	private String cdDtlNo;
	private String cdDtlNm;

	// 기획전 템플릿
	private String templetName;
	private String templetWidth;
	private String basicTextColor;
	private String basicBgColor;
	private String activeTextColor;
	private String activeBgColor;
	private String cateColor;
	private String footContent;
}