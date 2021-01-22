package kr.co.imarket_pc.vo.plant;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PlantMasterCategoryVo {
    private String plantId;
    private String cateId;
    private String cateName;
    private String cateType;
    private String cateLink;
    private String rowProductCount;
    private String cateViewOption;
    private String cateContent;
    private String regNo;
    private Date regDts;
    private String modNo;
    private Date modDts;
    private int cateSort;
}
