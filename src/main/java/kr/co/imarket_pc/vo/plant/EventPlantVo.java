package kr.co.imarket_pc.vo.plant;

import lombok.Getter;

import java.util.List;

@Getter
public class EventPlantVo {
    private String plantBoardBanner;
    private String plantId;
    private String plantSort;
    private String plantTitle;
    private List<EventPlantProductVo> eventPlantProductList;
}
