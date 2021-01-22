package kr.co.imarket_pc.plant.dto;

import kr.co.imarket_pc.vo.plant.PlantMasterVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PlantsResopnse {
    private List<PlantMasterVo> plantList;
}
