package kr.co.imarket_pc.plant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co.imarket_pc.vo.plant.EventPlantProductVo;
import kr.co.imarket_pc.vo.plant.EventPlantVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PlantsEventsProductsResopnse {
    private List<EventPlantVo> eventPlantList;
}
