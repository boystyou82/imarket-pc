package kr.co.imarket_pc.plant.dto;

import kr.co.imarket_pc.vo.plant.PlantEventCategoryVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PlantsEventsCategoriesResopnse {
    private List<PlantEventCategoryVo> plantEventCategoryList;
}
