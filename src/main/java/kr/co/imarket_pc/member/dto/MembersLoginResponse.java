package kr.co.imarket_pc.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MembersLoginResponse {
    private String sslOriginYn;
    private String italk;
    private String imfsUserPath;
    private String imfsUserQuery;
    private String imfsReferer;
    private String isPopup;
    private String isWelcome;
    private String reqSite;
    private String callBy;
    private String askYn;
    private String reqUrl;
    private String loginReferrer;
    private String memCloseYn;
    private String sucessYN;
    private String redirectUri;
    private String _baseAction_forwardURL;

    private int resCode;
    private String resMsg;
}
