package kr.co.imarket_pc.vo.plant;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class SpecialityMainVo {
    private String menuCode;
    private String cateNo;
    private String chnlNo;
    private String mallNm;
    private String mainUrl;
    private String exclTp;
    private String logoImgUrl;
    private Date dispStrtDts;
    private Date dispEndDts;
    private String reqUri;
    private String spmallYn;
}
