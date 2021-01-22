package kr.co.imarket_pc.plant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PlantMainRequest {
    private String plantId;
    private String memberNo;
    private List<String> productList;
    private String useType="PC";
}
