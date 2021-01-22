package kr.co.imarket_pc.session.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginSessionResponse {
    private String sessionId;
    private String cartNo;
    private String memberNo;
    private String loginDate;
    private String remoteIp;
    private String tmpOrdclmNo;
    private String ordclmNo;
    private String stateCd;
    private String cookie;
    private String sesDesc;
    private String ippId;
    private String ippSlotNo;
    private String mediaNm;
    private String channelNm;
    private String adSpecId;
    private String partTp;
    private long dayDiff;
}