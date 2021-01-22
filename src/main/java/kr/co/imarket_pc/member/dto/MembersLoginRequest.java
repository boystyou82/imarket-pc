package kr.co.imarket_pc.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MembersLoginRequest {
    private String saveMemId;
    private String saveLogin;
    private String memberId="jeff";
    private String pwd="dktkekf007";

    private String isAjax;
    private String callBy;
    private String isPopup;
    private String askYn;
    private String isWelcome;
    private String sslOriginYn;
    private String italk;
    private String reqSite;
    private String imfsUserPath;
    private String imfsUserQuery;
    private String imfsReferer;
    private String exclTp;

    private String BIZ_CD;
    private String slot_no;
}
