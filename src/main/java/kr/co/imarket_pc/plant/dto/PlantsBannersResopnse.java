package kr.co.imarket_pc.plant.dto;

import kr.co.imarket_pc.vo.plant.SpecialDisplayVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PlantsBannersResopnse {
    private List<SpecialDisplayVo> eventBandBannerList;
    private List<SpecialDisplayVo> eventPcBannerList;
    private List<SpecialDisplayVo> mainRightFloatingBannerList;
    private List<SpecialDisplayVo> plantLeftFooterBannerList;
}
