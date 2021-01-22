package kr.co.imarket_pc.plant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PlantsEventsCategoriesRequest {
    private String displayNo;
    private String memberNo;
    private int page=1;
    private int rowPerPage=20;
}
