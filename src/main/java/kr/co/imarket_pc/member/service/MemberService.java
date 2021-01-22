package kr.co.imarket_pc.member.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.imarket_pc.common.RestTemplateApi;
import kr.co.imarket_pc.member.dto.MemberResponse;
import kr.co.imarket_pc.member.dto.MembersOrdersCountResopnse;
import kr.co.imarket_pc.member.dto.MembersSpecialityMainResopnse;
import kr.co.imarket_pc.vo.session.SetMemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final RestTemplateApi<MemberResponse> membersResopnseApi;
    private final RestTemplateApi<MembersSpecialityMainResopnse> membersSpecialityMainResopnseApi;
    private final RestTemplateApi<MembersOrdersCountResopnse> membersOrdersCountResopnseApi;
    private final RestTemplateApi<String> membersFailureApi;
    private final RestTemplateApi<String> membersActivateApi;
    private final RestTemplateApi<Integer> membersResetApi;
    private final RestTemplateApi<Integer> membersCartMergeApi;
    private final RestTemplateApi<Integer> membersAccessLogApi;
    public final ObjectMapper objectMapper;

    public MemberResponse getMembersMemberNo(String memberNo) {

        if (!StringUtils.isBlank(memberNo)) {
            return new MemberResponse();
        }

        Map<String, String> UriParams = new HashMap<String, String>() {{
            put("memberNo", memberNo);
        }};

        return membersResopnseApi.get("/v1/members/{memberNo}"
                , UriParams
                , null
                , MemberResponse.class);
    }

    public MemberResponse getMembersEnterpriseNo(SetMemberVo setMemberVo) {

        if (StringUtils.isBlank(setMemberVo.getEnterpriseNo())
                || StringUtils.isBlank(setMemberVo.getMemberNo())
                || StringUtils.isBlank(setMemberVo.getSupplyCtrtSeq())) {
            return new MemberResponse();
        }

        Map<String, String> uriParams = objectMapper.convertValue(setMemberVo, new TypeReference<Map<String, String>>() {
        });

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.setAll(uriParams);

        return membersResopnseApi.get("/v1/members/{memberNo}/enterprises/{enterpriseNo}"
                , uriParams
                , queryParams
                , MemberResponse.class);
    }


    public MemberResponse getMembersMemberId(String memberId) {

        if (StringUtils.isBlank(memberId)) {
            return new MemberResponse();
        }

        MultiValueMap<String, String> requestQueryParams = new LinkedMultiValueMap<>();
        requestQueryParams.add("memberId", memberId);

        return membersResopnseApi.get("/v1/members"
                , null
                , requestQueryParams
                , MemberResponse.class);
    }

    public MembersSpecialityMainResopnse getMmembersSpecialityMain(String memberNo) {

        if (StringUtils.isBlank(memberNo)) {
            return new MembersSpecialityMainResopnse();
        }

        Map<String, String> UriParams = new HashMap<String, String>() {{
            put("memberNo", memberNo);
        }};

        return membersSpecialityMainResopnseApi.get("/v1/members/{memberNo}/mall"
                , UriParams
                , null
                , MembersSpecialityMainResopnse.class);
    }

    public MembersOrdersCountResopnse getMembersOrdersCount(String memberNo) {

        if (StringUtils.isBlank(memberNo)) {
            return new MembersOrdersCountResopnse();
        }

        Map<String, String> UriParams = new HashMap<String, String>() {{
            put("memberNo", memberNo);
        }};

        return membersOrdersCountResopnseApi.get("/v1/members/{memberNo}/orders/count"
                , UriParams
                , null
                , MembersOrdersCountResopnse.class);
    }

    public Integer patchMembersFailure(String memberNo) {

        if (StringUtils.isBlank(memberNo)) {
            return 0;
        }

        Map<String, String> UriParams = new HashMap<String, String>() {{
            put("memberNo", memberNo);
        }};
        return membersFailureApi.ptchCode("/v1/members/{memberNo}/failure"
                , UriParams
                , null
                , null);
    }

    public Integer patchMembersActivate(String memberNo) {

        if (StringUtils.isBlank(memberNo)) {
            return 0;
        }

        Map<String, String> UriParams = new HashMap<String, String>() {{
            put("memberNo", memberNo);
        }};

        return membersActivateApi.ptchCode("/v1/members/{memberNo}/activate"
                , UriParams
                , null
                , null);
    }

    public Integer patchMembersReset(String memberNo) {

        Map<String, String> UriParams = new HashMap<String, String>() {{
            put("memberNo", memberNo);
        }};

        return membersResetApi.ptchCode("/v1/members/{memberNo}/reset"
                , UriParams
                , null
                , null);
    }

    public Integer patchMembersCartMerge(String cartNo, String memberNo) {

        if (StringUtils.isBlank(cartNo)) {
            return 200;
        } else if (!StringUtils.isBlank(memberNo)) {
            Map<String, String> UriParams = new HashMap<>();
            UriParams.put("memberNo", memberNo);
            UriParams.put("cartNo", cartNo);
            return membersCartMergeApi.ptchCode("/v1/members/{memberNo}/carts/{cartNo}"
                    , UriParams
                    , null
                    , null);
        } else {
            return 0;
        }
    }

    public Integer postMembersAccessLog(String memberNo, String ip) {

        if (StringUtils.isBlank(memberNo)
                || StringUtils.isBlank(ip)) {
            return 0;
        }

        JSONObject jSONObject = new JSONObject();
        jSONObject.put("memberNo", memberNo);
        jSONObject.put("ip", ip);

        return membersAccessLogApi.post("/v1/members/access-log"
                , null
                , jSONObject
                , null);
    }
}
