package kr.co.imarket_pc.plant.dto;

import kr.co.imarket_pc.member.dto.MemberResponse;
import kr.co.imarket_pc.vo.plant.PlantMasterVo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PlantMainResopnse {
    private PlantMasterVo plantMasterVo;
    private PlantResopnse plantResopnse;
    private PlantsBannersResopnse plantsBannersResopnse;
    private PlantsResopnse plantsResopnse;
    private MemberResponse memberResopnse;
    private String encInpkMemNo;
    private String userChnlYn;
    private String forwardPage;
}
