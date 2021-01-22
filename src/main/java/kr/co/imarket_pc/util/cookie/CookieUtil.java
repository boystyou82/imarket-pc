package kr.co.imarket_pc.util.cookie;

import com.interpark.fw.security.Security;
import com.interpark.fw.security.SecurityFactory;
import kr.co.imarket_pc.util.etc.Constants;
import kr.co.imarket_pc.vo.member.MemberVo;
import kr.co.imarket_pc.vo.plant.SpecialityMainVo;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Optional;

@UtilityClass
public class CookieUtil {

    public Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (!ObjectUtils.isEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public void setCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = createCookie(name, value);
        response.addCookie(cookie);
    }

    public void setCookie(HttpServletResponse response, String name, String value, int expire) {
        Cookie cookie = createCookie(name, value, expire);
        response.addCookie(cookie);
    }

    public Cookie createCookie(String name, String value) {
        return createCookie(name, value, Constants.DEFAULT_EXPIRE);
    }

    public Cookie createCookie(String name, String value, int expire) {
        return createCookie(name, value, Constants.DEFAULT_PATH, Constants.DEFAULT_DOMAIN, expire, false);
    }

    public Cookie createCookie(String name, String value, String path, String domain, int expire, boolean secure) {
        if ((StringUtils.defaultString(name)).equals(Constants.MEM_NO_KEY)
                || (StringUtils.defaultString(name)).equals(Constants.USER_ID_KEY)
                || (StringUtils.defaultString(name)).equals(Constants.AL_MEM_NO_KEY)
                || (StringUtils.defaultString(name)).equals(Constants.VERIFIED_AUTOLOGIN_YN)
                || (StringUtils.defaultString(name)).equals(Constants.IPIN_CI)
                || (StringUtils.defaultString(name)).equals(Constants.IPIN_DI)) {

            //1. Security 초기화 - 쿠키암/복호화 시, Security.TYPE_COOKIE
            Security cookieSecurity = SecurityFactory.getSecurity(Security.TYPE_COOKIE);

            //2. 쿠키값에 대한 암/복호화 수행
            if (!ObjectUtils.isEmpty(cookieSecurity)) {
                value = URLEncoder.encode(cookieSecurity.encrypt(value)); //암호화
            }
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        cookie.setDomain(domain);
        cookie.setMaxAge(expire);
        cookie.setSecure(secure);
        cookie.setValue(value);

        return cookie;
    }

    public String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        String value = "";

        if (!ObjectUtils.isEmpty(cookie)) {
            if (Constants.MEM_NO_KEY.equals(name)
                    || Constants.USER_ID_KEY.equals(name)
                    || Constants.AL_MEM_NO_KEY.equals(name)
                    || Constants.VERIFIED_AUTOLOGIN_YN.equals(name)
                    || Constants.IPIN_CI.equals(name)
                    || Constants.IPIN_DI.equals(name)
            ) {
                //1. Security 초기화 - 쿠키암/복호화 시, Security.TYPE_COOKIE
                Security cookieSecurity = SecurityFactory.getSecurity(Security.TYPE_COOKIE);

                //2. 쿠키값에 대한 암/복호화 수행
                if (!ObjectUtils.isEmpty(cookieSecurity)) {
                    value = cookieSecurity.decrypt(URLDecoder.decode(cookie.getValue())); //복호화
                }
            } else {
                value = cookie.getValue();
            }

        }
        return value;
    }

    //기본정보 쿠키 세팅
    public void setMemberLoginDefault(HttpServletResponse res, MemberVo memberVo) {
        // 쿠키생성: 회원번호
        setCookie(res, Constants.MEM_NO_KEY, memberVo.getMemberNo());
        // 쿠키생성: 세션아이디
        setCookie(res, Constants.SESSION_ID_KEY, memberVo.getSessionId());

        // 쿠키생성: Timestamp
        CookieUtil.setTimestampKeyCookie(res);

        // 로그인상태유지(자동로그인) 체크 여부가 "Y"일 경우 자동로그인 쿠키 생성: "alKey", "alSno",
        // 자동 로그인여부 쿠키에 2가지 정보를 넣는다
        // 1 : 자동 로그인 사용자 인지 여부 (Y:자동로그인사용자, N:미대상)
        // 2 : 현재의 상태 (N : 일반로그인 페이지를 통해서 로그인함, Y : 자동로그인으로 로그인됨)
        //  - 만료일: 3달
        if ("Y".equals(memberVo.getSaveLogin())) {
            setCookie(res, Constants.AL_MEM_NO_KEY, memberVo.getMemberNo(), Constants.COOKIE_EXPIRE_3MONTH);
            setCookie(res, Constants.AL_SESSION_ID_KEY, memberVo.getSessionId(), Constants.COOKIE_EXPIRE_3MONTH);
            setCookie(res, Constants.VERIFIED_AUTOLOGIN_YN, "Y_N", Constants.COOKIE_EXPIRE_3MONTH);
        }
    }

    public void setSaveLoginIdCookie(HttpServletResponse res, String memberId) {
        setCookie(res, Constants.SAVEID_KEY, "Y", Constants.COOKIE_EXPIRE_3MONTH);
        setCookie(res, Constants.USER_ID_KEY, memberId, Constants.COOKIE_EXPIRE_3MONTH);
    }

    public void setExclusiveTypeCookie(HttpServletResponse res, String exclTp) {
        CookieUtil.setCookie(res, Constants.EXCLUSIVE_TYPE, exclTp, Constants.COOKIE_EXPIRE_1YEAR);
    }

    public void setTimestampKeyCookie(HttpServletResponse res) {
        long milli = System.currentTimeMillis();
        setCookie(res, Constants.TIMESTAMP_KEY, encode(milliToString(milli))); //현재 miillisecond를 암호화하여 쿠키객체 생성
    }

    public void setSpecialityMallDefaultCookie(HttpServletResponse res) {
        setCookie(res, Constants.EXCLUSIVE_TYPE, "00", Constants.COOKIE_EXPIRE_1YEAR);
        setCookie(res, Constants.IS_SP_MALL, "N", Constants.COOKIE_EXPIRE_1YEAR);
    }

    public void setSpecialityMallCookie(
            HttpServletResponse res
            , SpecialityMainVo specialityMainVo) {

        String exclTp =
                Optional.ofNullable(specialityMainVo.getExclTp())
                        .orElse("");
        String logoImgUrl =
                Optional.ofNullable(specialityMainVo.getLogoImgUrl())
                        .orElse("");
        String spmallYn =
                Optional.ofNullable(specialityMainVo.getSpmallYn())
                        .orElse("");
        String reqUri =
                Optional.ofNullable(specialityMainVo.getReqUri())
                        .orElse("");
        String chnlNo =
                Optional.ofNullable(specialityMainVo.getChnlNo())
                        .orElse("");

        setCookie(res, Constants.EXCLUSIVE_TYPE, exclTp, Constants.COOKIE_EXPIRE_1YEAR);
        setCookie(res, Constants.LOGO_IMG_URL, logoImgUrl, Constants.COOKIE_EXPIRE_1YEAR);
        setCookie(res, Constants.IS_SP_MALL, spmallYn, Constants.COOKIE_EXPIRE_1YEAR);
        setCookie(res, Constants.REQ_URL, reqUri, Constants.COOKIE_EXPIRE_1YEAR);
        setCookie(res, Constants.MALL_CHNL_NO, chnlNo, Constants.COOKIE_EXPIRE_1YEAR);
        setCookie(res, Constants.REFERRAL_CD, chnlNo, Constants.COOKIE_EXPIRE_1YEAR);
    }

    public void removeMemberCookies(HttpServletResponse res) {
        expireCookie(res, Constants.MEM_NO_KEY); // imfsMno
        expireCookie(res, Constants.SESSION_ID_KEY); // imfsSno
        expireCookie(res, Constants.MEM_REAL_NM_KEY); // memRealName
        expireCookie(res, Constants.CHNL_SSO_YN); // chnlSsoYn
        expireCookie(res, Constants.SES_CART_NO);
        removeAutoLoginCookies(res);
        removeSaveLoginIdCookie(res); // 자동로그인용 회원번호, 세션아이디 삭제
        removePvtCertCookies(res);
        expireSpecialityMallCookie(res);
    }

    public void removeAutoLoginCookies(HttpServletResponse res) {
        expireCookie(res, Constants.AL_MEM_NO_KEY); // alKey
        expireCookie(res, Constants.AL_SESSION_ID_KEY); // alSno
        expireCookie(res, Constants.VERIFIED_AUTOLOGIN_YN); // imfsvay
        expireCookie(res, Constants.SAVE_LOGIN); // saveLogin  모바일용 autologin 여부
    }

    public void removeSaveLoginIdCookie(HttpServletResponse res) {
        expireCookie(res, Constants.SAVEID_KEY);
        expireCookie(res, Constants.USER_ID_KEY);
    }

    public void removePvtCertCookies(HttpServletResponse res) {
        expireCookie(res, Constants.IPIN_REQ_SEQ);
        expireCookie(res, Constants.CHECKPLUS_REQ_SEQ);
        expireCookie(res, Constants.IPIN_CI);
        expireCookie(res, Constants.IPIN_DI);
    }

    public void expireSpecialityMallCookie(HttpServletResponse res) {
        expireCookie(res, Constants.EXCLUSIVE_TYPE); // 전문몰 구분
        expireCookie(res, Constants.LOGO_IMG_URL); // 전문몰 로고
        expireCookie(res, Constants.IS_SP_MALL); // 전문몰 여부
        expireCookie(res, Constants.REQ_URL); // 전문몰 메인
        expireCookie(res, Constants.MALL_CHNL_NO); // 전문몰 채널번호
        expireCookie(res, Constants.REFERRAL_CD); // 주문추적용

        //전문몰 관련 초기값 세팅
        setSpecialityMallDefaultCookie(res);
    }

    public void expireCookie(HttpServletResponse res, String name) {
        setCookie(res, name, "", 0);
    }

    private String milliToString(long milli) {
        String sMilli = Long.toString(milli);
        StringBuilder pStr = new StringBuilder();
        int length = 14 - sMilli.length();

        for (int i = 1; i <= length; i++) {
            pStr.append("0");
        }

        return pStr + sMilli;
    }

    private String encode(String str) {
        StringBuilder encodedStr = new StringBuilder();
        StringBuilder pRandom = new StringBuilder();
        StringBuilder aRandom = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            pRandom.append(Math.round(Math.random() * 10));
            aRandom.append(Math.round(Math.random() * 10));
        }
        str = pRandom.substring(0, 10) + str + aRandom.substring(0, 10);
        for (int i = 33; i >= 0; i--) {
            encodedStr.append(str.charAt(i));
        }
        return encodedStr.toString();
    }
}