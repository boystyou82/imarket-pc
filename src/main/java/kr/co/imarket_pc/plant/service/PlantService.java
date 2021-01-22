package kr.co.imarket_pc.plant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interpark.fw.security.Security;
import com.interpark.fw.security.SecurityFactory;
import kr.co.imarket_pc.common.RestTemplateApi;
import kr.co.imarket_pc.member.service.MemberService;
import kr.co.imarket_pc.plant.dto.*;
import kr.co.imarket_pc.session.service.SessionService;
import kr.co.imarket_pc.util.etc.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlantService {

    private final Security security2 = SecurityFactory.getSecurity("SEED", Constants.INPK_LINK_SEED_KEY);

    public final MemberService memberService;
    public final SessionService sessionService;

    private final RestTemplateApi<PlantResopnse> plantResopnseApi;
    private final RestTemplateApi<PlantsResopnse> plantsResopnseApi;
    private final RestTemplateApi<PlantsBannersResopnse> plantsBannersResopnseApi;
    private final RestTemplateApi<PlantsEventsCategoriesResopnse> plantsEventsCategoriesResopnseApi;
    private final RestTemplateApi<PlantsEventsProductsResopnse> plantsEventsProductsResopnseApi;

    public final ObjectMapper objectMapper;

    public PlantMainResopnse getPlant(PlantMainRequest plantRequest) {

//        MembersSpecialityMainResopnse membersSpecialityMainResopnse =
//                Optional.ofNullable(plantRequest.getMemberNo())
//                        .map(memberService::getMmembersSpecialityMain)
//                        .orElseGet(MembersSpecialityMainResopnse::new);

//        PvtCertSupport.setAttrEncData(req, res); // 실명인증 페이지 호출 시 필요 파라미터 설정
//
//        MemberMainMallVo memberMainMallVo =
//                Optional.ofNullable(membersSpecialityMainResopnse.getMemberMainMall())
//                        .orElseGet(MemberMainMallVo::new);
//        SpecialityMainVo specialityMainVo =
//                Optional.ofNullable(membersSpecialityMainResopnse.getSpecialityMain())
//                        .orElseGet(SpecialityMainVo::new); //전문몰 데이터
//
//        if (!StringUtils.isBlank(memberMainMallVo.getExclTp())
//                && !StringUtils.isBlank(specialityMainVo.getMainUrl())) {
//            // 전문몰 관련쿠키 등록
//            CookieUtil.setSpecialityMallCookie(res, specialityMainVo);
//            SessionUtil.setSpecialityMallSession(req, specialityMainVo);
//        } else {
//            // 전문몰 관련쿠키 삭제
//            CookieUtil.expireSpecialityMallCookie(res);
//            SessionUtil.expireSpecialityMallSession(req);
//        }

        String encInpkMemNo =
                Optional.ofNullable(plantRequest.getMemberNo())
                        .orElse("");

        return PlantMainResopnse.builder()
                .plantResopnse(getPlantInfo(plantRequest))  // 기획전 상세 조회
                .plantsBannersResopnse(getPlantsBanners())  // 기획전 배너  조회
                .plantsResopnse(getPlants())                // 기획전 리스트 조회
                .memberResopnse(memberService.getMembersMemberNo(plantRequest.getMemberNo()))             // 회원 정보 조회
                .encInpkMemNo(URLEncoder.encode(security2.encrypt(encInpkMemNo)))
                .build();
    }

    public PlantsEventsCategoriesResopnse getPlantsEventsCategories(PlantsEventsCategoriesRequest plantsEventsCategoriesRequest) {

        MultiValueMap<String, String> plantsEventsCategoriesQueryParams = new LinkedMultiValueMap<>();
        Map<String, String> plantsEventsCategoriesUriParams = objectMapper.convertValue(plantsEventsCategoriesRequest, new TypeReference<Map<String, String>>() {
        });
        plantsEventsCategoriesQueryParams.setAll(plantsEventsCategoriesUriParams);

        return plantsEventsCategoriesResopnseApi.get("/v1/plants/events/categories"
                , null
                , plantsEventsCategoriesQueryParams
                , PlantsEventsCategoriesResopnse.class);
    }

    public PlantsEventsProductsResopnse getPlantsEventsProducts(PlantsEventsProductsRequest plantsEventsProductsRequest) {

        MultiValueMap<String, String> plantsEventsProductsQueryParams = new LinkedMultiValueMap<>();
        Map<String, String> plantsEventsProductsUriParams = objectMapper.convertValue(plantsEventsProductsRequest, new TypeReference<Map<String, String>>() {
        });
        plantsEventsProductsQueryParams.setAll(plantsEventsProductsUriParams);

        return plantsEventsProductsResopnseApi.get("/v1/plants/events/products"
                , null
                , plantsEventsProductsQueryParams
                , PlantsEventsProductsResopnse.class);
    }

    public PlantsBannersResopnse getPlantsBanners() {
        return plantsBannersResopnseApi.get("/v1/plants/banners"
                , null
                , null
                , PlantsBannersResopnse.class);
    }

    public PlantsResopnse getPlants() {
        return plantsResopnseApi.get("/v1/plants"
                , null
                , null
                , PlantsResopnse.class);
    }

    public PlantResopnse getPlantInfo(PlantMainRequest plantRequest) {
        if (StringUtils.isBlank(plantRequest.getPlantId())) {
            return new PlantResopnse();
        }

        Map<String, String> uriParams = objectMapper.convertValue(plantRequest, new TypeReference<Map<String, String>>() {
        });
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.setAll(uriParams);

        return plantResopnseApi.get("/v1/plants/{plantId}"
                , uriParams
                , queryParams
                , PlantResopnse.class);
    }


}