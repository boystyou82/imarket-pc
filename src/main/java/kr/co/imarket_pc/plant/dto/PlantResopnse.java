package kr.co.imarket_pc.plant.dto;

import kr.co.imarket_pc.vo.coupon.ProductCouponVo;
import kr.co.imarket_pc.vo.plant.PlantMasterCategoryProductVo;
import kr.co.imarket_pc.vo.plant.PlantMasterCategoryVo;
import kr.co.imarket_pc.vo.plant.PlantMasterVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PlantResopnse {
    private PlantMasterVo plantMasterInfo;
    private List<PlantMasterCategoryVo> plantCategoryList;
    private List<PlantMasterCategoryProductVo> plantCategoryProductList;
    private List<ProductCouponVo> productCouponList;
    private List<String> plantChannelList;
}
