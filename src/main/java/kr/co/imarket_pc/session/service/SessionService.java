package kr.co.imarket_pc.session.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.imarket_pc.common.RestTemplateApi;
import kr.co.imarket_pc.member.dto.MemberResponse;
import kr.co.imarket_pc.member.dto.MembersLoginRequest;
import kr.co.imarket_pc.member.dto.MembersSpecialityMainResopnse;
import kr.co.imarket_pc.member.service.MemberService;
import kr.co.imarket_pc.session.dto.LoginSessionResponse;
import kr.co.imarket_pc.util.cookie.CookieUtil;
import kr.co.imarket_pc.util.etc.Constants;
import kr.co.imarket_pc.util.session.SessionUtil;
import kr.co.imarket_pc.vo.member.MemberVo;
import kr.co.imarket_pc.vo.plant.SpecialityMainVo;
import kr.co.imarket_pc.vo.session.SetMemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionService {

    public final ObjectMapper objectMapper;
    private final RestTemplateApi<LoginSessionResponse> loginSessionApi;
    private final RestTemplateApi<Integer> updateSessionLoginApi;
    private final RestTemplateApi<LoginSessionResponse> insertSessionInfoApi;

    public final MemberService memberService;

    public MemberVo getMemberCookieSession(
            HttpServletRequest req
            , HttpServletResponse res) {

        String cookieMemberNo = CookieUtil.getCookieValue(req, Constants.MEM_NO_KEY);                     // 회원번호(mem_no)
        String cookieSessionId = CookieUtil.getCookieValue(req, Constants.SESSION_ID_KEY);                // 세션ID(ses_id)
        String cookieVerifiedAutologin = CookieUtil.getCookieValue(req, Constants.VERIFIED_AUTOLOGIN_YN); // 자동로그인 여부
        String cookieAlMemberNo = CookieUtil.getCookieValue(req, Constants.AL_MEM_NO_KEY);                // 자동로그인 회원번호(mem_no)
        String cookieAlSessionId = CookieUtil.getCookieValue(req, Constants.AL_SESSION_ID_KEY);           // 자동로그인 세션ID(ses_id)
        String cookieEntrNo = CookieUtil.getCookieValue(req, Constants.EN_TR_NO);
        String cookieSupplyCtrtSeq = CookieUtil.getCookieValue(req, Constants.SUPPLY_CTRT_SEQ);
        String cookieExclusiveType = CookieUtil.getCookieValue(req, Constants.EXCLUSIVE_TYPE);

        String exclusiveType = req.getParameter(Constants.EXCLUSIVE_TYPE);
        String exclTp = (!StringUtils.isBlank(exclusiveType)) ? exclusiveType : cookieExclusiveType;

        List<String> autoYn =
                new ArrayList<>(Arrays.asList(cookieVerifiedAutologin.split("_")));
        String memberNo = StringUtils.defaultIfEmpty(cookieMemberNo, cookieAlMemberNo);
        String sessionId = StringUtils.defaultIfEmpty(cookieSessionId, cookieAlSessionId);

        // 쿠키 memberNo 또는 쿠키 sessionId 값이 없으면 종료
        if (StringUtils.isBlank(memberNo) || StringUtils.isBlank(sessionId)) {
            return null;
        }

        MemberVo memberVo = SessionUtil.getMemberSessionKey(req);
        if (!ObjectUtils.isEmpty(memberVo)
                && sessionId.equals(memberVo.getSessionId())) {
            return memberVo;
        }

        LoginSessionResponse loginSessionResponse = getSessionInfo(sessionId);
        if (ObjectUtils.isEmpty(loginSessionResponse)
                || StringUtils.isBlank(String.valueOf(loginSessionResponse.getMemberNo()))) {
            return null;
        }

        if (!memberNo.equals(loginSessionResponse.getMemberNo())) {
            return null;
        }

        // 로그인 상태를 로그인으로 변경
        if (!"1".equals(loginSessionResponse.getStateCd())) {
            loginSessionResponse.setStateCd("1"); //회원 로그인 상태 변경
            if (200 != updateSessionInfo(loginSessionResponse)) {
                return null;
            }
        }

        //자동로그인 회원번호(mem_no)와 세션ID(ses_id)이 있을경우 자동로그인 관련 쿠키값 세팅
        if ("Y".equals(autoYn.get(0))
                && StringUtils.isNotBlank(cookieAlMemberNo)
                && StringUtils.isNotBlank(cookieAlSessionId)) {
            CookieUtil.setCookie(res, Constants.VERIFIED_AUTOLOGIN_YN, "Y_Y", Constants.COOKIE_EXPIRE_3MONTH);
        }

        return setMemberCookieSession(req
                , res
                , SetMemberVo.builder()
                        .memberNo(memberNo)
                        .sessionId(sessionId)
                        .enterpriseNo(cookieEntrNo)
                        .supplyCtrtSeq(cookieSupplyCtrtSeq)
                        .exclTp(exclTp)
                        .build());
    }

    public LoginSessionResponse getSessionInfo(String sessionId) {
        if (StringUtils.isBlank(sessionId)) {
            return new LoginSessionResponse();
        }

        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("sessionId", sessionId);
        return loginSessionApi.get("/v1/sessions/{sessionId}"
                , uriParams
                , null
                , LoginSessionResponse.class);
    }

    public Integer updateSessionInfo(LoginSessionResponse loginSessionResponse) {
        if (StringUtils.isBlank(loginSessionResponse.getSessionId())
                || StringUtils.isBlank(loginSessionResponse.getCartNo())
                || StringUtils.isBlank(loginSessionResponse.getIppId())
                || StringUtils.isBlank(loginSessionResponse.getMemberNo())
                || StringUtils.isBlank(loginSessionResponse.getStateCd())) {
            return 0;
        }

        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("sessionId", loginSessionResponse.getSessionId());
        return updateSessionLoginApi.ptchCode("/v1/sessions/{sessionId}"
                , uriParams
                , loginSessionResponse
                , null);
    }

    public MemberVo setMemberCookieSession(
            HttpServletRequest req
            , HttpServletResponse res
            , SetMemberVo setMemberVo) {

        MemberVo memberVo;
        if (StringUtils.isBlank(setMemberVo.getEnterpriseNo())
                || StringUtils.isBlank(setMemberVo.getSupplyCtrtSeq())) {
            memberVo =
                    Optional.ofNullable(
                            memberService.getMembersMemberNo(setMemberVo.getMemberNo()))
                            .orElseGet(MemberResponse::new).getMemberDto();
        } else {
            memberVo =
                    Optional.ofNullable(
                            memberService.getMembersEnterpriseNo(setMemberVo))
                            .orElseGet(MemberResponse::new).getEnterpriseDto();
        }

        if (ObjectUtils.isEmpty(memberVo)) {
            CookieUtil.removeMemberCookies(res);
            SessionUtil.removeMemberSessionKey(req);
            return null;
        } else {
            memberVo.setEntrTp("00");
            memberVo.setMemId("Y");
            memberVo.setSessionId(setMemberVo.getSessionId());
        }

        // 로그인 세션, 쿠키 생성
        SessionUtil.setMemberSessionKey(req, memberVo);
        CookieUtil.setMemberLoginDefault(res, memberVo);

        if (!StringUtils.isBlank(setMemberVo.getExclTp())) {
            CookieUtil.setExclusiveTypeCookie(res, setMemberVo.getExclTp());
        } else {
            CookieUtil.expireSpecialityMallCookie(res);
            SessionUtil.expireSpecialityMallSession(req);
        }

        return memberVo;
    }

    public MemberVo createMemberCookieSessionInfo(
            HttpServletRequest req
            , HttpServletResponse res
            , MemberVo memberVo
            , MembersLoginRequest membersLoginRequest) {

        //readyCartNo 20201120165048810
        //비로그인 상태의 장바구니 상품을 로그인한 회원의 장바구니로 이관
        String cartNo = CookieUtil.getCookieValue(req, Constants.SES_CART_NO);

        if (200 != memberService.patchMembersCartMerge(cartNo, memberVo.getMemberNo())) {
            return null;
        }

        // 기존 쿠키 데이터 삭제
        CookieUtil.removeMemberCookies(res);
        SessionUtil.removeMemberSessionKey(req);

        String ippId =
                Optional.ofNullable(membersLoginRequest.getBIZ_CD())
                        .orElse("");

        String ippSlotNo =
                Optional.ofNullable(membersLoginRequest.getSlot_no())
                        .orElse("");

        String remoteIp = StringUtils.defaultString(req.getHeader("X-Forwarded-For"));

        if (StringUtils.isBlank(remoteIp)) {
            remoteIp = StringUtils.defaultString(req.getRemoteAddr());
        }

        if (StringUtils.isBlank(ippId) || ippId.length() != 7) {
            ippId = Constants.DEFAULT_IPP_ID;
        }

        // 업체에서 slot_no에 문자가 섞여오는 경우가 있음.
        ippSlotNo = String.valueOf(NumberUtils.toInt(ippSlotNo, 0));

        //insert api 로직 처리
        LoginSessionResponse loginSessionResponse =
                insertSessionInfo(LoginSessionResponse.builder()
                        .ippId(ippId)
                        .ippSlotNo(ippSlotNo)
                        .remoteIp(remoteIp)
                        .build());

        if (ObjectUtils.isEmpty(loginSessionResponse)) {
            return null;
        }

        //member 정보 세팅
        memberVo.setSessionId(loginSessionResponse.getSessionId());
        CookieUtil.setMemberLoginDefault(res, memberVo);

        // member 아이디 저장 여부
        if ("Y".equals(membersLoginRequest.getSaveMemId())) {
            CookieUtil.setSaveLoginIdCookie(res, membersLoginRequest.getMemberId());
            memberVo.setSaveMemberId(membersLoginRequest.getMemberId());
        }

        // member 자동로그인
        if (StringUtils.isBlank(membersLoginRequest.getSaveLogin())) {
            CookieUtil.removeAutoLoginCookies(res);
        } else {
            memberVo.setSaveLogin(membersLoginRequest.getSaveLogin());
        }

        // 세션에 회원정보 set
        SessionUtil.setMemberSessionKey(req, memberVo);

        //전문몰 데이터 조회
        SpecialityMainVo specialityMainVo =
                Optional.ofNullable(memberService.getMmembersSpecialityMain(memberVo.getMemberNo()))
                        .map(MembersSpecialityMainResopnse::getSpecialityMain)
                        .orElseGet(SpecialityMainVo::new);

        if (!StringUtils.isBlank(specialityMainVo.getMainUrl())) {
            //전물몰 관련 쿠키 설정
            CookieUtil.setSpecialityMallCookie(res, specialityMainVo);
            SessionUtil.setSpecialityMallSession(req, specialityMainVo);
        }

        loginSessionResponse.setMemberNo(memberVo.getMemberNo());
        loginSessionResponse.setStateCd("1");

        if (200 != updateSessionInfo(loginSessionResponse)) {
            // 기존 쿠키 및 세션 데이터 삭제
            CookieUtil.removeMemberCookies(res);
            SessionUtil.removeMemberSession(req);
            return null;
        }

        // 로그인 이력 생성
        memberService.postMembersAccessLog(memberVo.getMemberNo(), req.getRemoteAddr());

        return memberVo;
    }

    public LoginSessionResponse insertSessionInfo(LoginSessionResponse loginSessionResponse) {
        if (StringUtils.isBlank(loginSessionResponse.getIppId())
                || StringUtils.isBlank(loginSessionResponse.getRemoteIp())) {
            return null;
        }

        JSONObject jSONObject = new JSONObject();
        jSONObject.put("ippId", loginSessionResponse.getIppId());
        jSONObject.put("ippSlotNo", loginSessionResponse.getIppSlotNo());
        jSONObject.put("remoteIp", loginSessionResponse.getRemoteIp());

        return insertSessionInfoApi.post("/v1/sessions"
                , null
                , jSONObject
                , LoginSessionResponse.class);
    }
}
