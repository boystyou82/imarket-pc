package kr.co.imarket_pc.util.etc;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public final int CERTIFICATION_CODE_SUCCESS = 0;
    public final int CERTIFICATION_CODE_SESSION_NOT_EQUAL = -98;
    public final int CERTIFICATION_CODE_ERROR = -99;
    public final int CERTIFICATION_CODE_NICE_ERROR = -100;

    public final String INPK_LINK_SEED_KEY = "dkdlakzptzhfldk0";


    // SESSION, COOKIE
    public final String DEFAULT_PATH = "/";
    public final String DEFAULT_DOMAIN = ".imarket.co.kr";
    public final int DEFAULT_EXPIRE = -1;
    public final String SESSION_ID_KEY = "imfsSno";              // 1. 세션ID(ses_id)
    public final String MEM_NO_KEY = "imfsMno";                  // 2. 회원번호(mem_no)
    public final String USER_ID_KEY = "imfsUsrId";               // 8. 회원 아이디 : 아이디 기억하기일 경우 max age = 31536000
    public final String MEM_REAL_NM_KEY = "memRealName";         // 22. 회원명(자바스크립트 escape 인코딩된)
    public final String AL_MEM_NO_KEY = "alKey";                 // 31. 자동로그인 회원번호
    public final String AL_SESSION_ID_KEY = "alSno";             // 32. 자동로그인 세션아이디
    public final String ALREADY_VIEW_PRDNO = "alreadyViewPrdNo"; // 33. 최근 본 상품
    public final String EXCLUSIVE_TYPE = "exclTp";               // 35. 전문간 구분(유입 채널 구분): 00=해당없음, 01=의사협회
    public final String VERIFIED_AUTOLOGIN_YN = "imfsvay";       // 36. 다시 로그인한 자동로그인 여부
    public final String IPIN_CI = "CI";                          // 37. IPIN 실명인증 연계정보
    public final String IPIN_DI = "DI";                          // 38. IPIN 실명인증 중복확인값
    public final String CHNL_SSO_YN = "chnlSsoYn";               // 39. 채널고객사에서 넘어온 로그인(SSO) 여부. YN: MP공급사 SSO, YY: MP공급사 외 SSO
    public final String SES_CART_NO = "readyCartNo";             // 40. 비로그인 장바구시 사용 하는 cartNo
    public final String EN_TR_NO = "entrNo";
    public final String SUPPLY_CTRT_SEQ = "supplyCtrtSeq";
    public final String LOGO_IMG_URL = "logoImgUrl";
    public final String IS_SP_MALL = "isSpmall";
    public final String REQ_URL = "reqUri";
    public final String MALL_CHNL_NO = "mallChnlNo";
    public final String REFERRAL_CD = "referralCd";
    public final String SAVE_LOGIN = "saveLogin";
    public final String IPIN_REQ_SEQ = "ipinReqSeq";
    public final String CHECKPLUS_REQ_SEQ = "checkplusReqSeq";
    public final String TIMESTAMP_KEY = "imarketstamp";          // 로그인 유지용 타임 스탬프
    public final String SAVEID_KEY = "SAVEID";                   // 아이디 기억하기 여부
    public final String USERINFO_SESSION_KEY = "SES_IMFS_USERINFO";
    public final String DEFAULT_IPP_ID = "000000";

    public final int COOKIE_EXPIRE_3MONTH = 60 * 60 * 24 * 90;
    public final int COOKIE_EXPIRE_1YEAR = 60 * 60 * 24 * 365;

}