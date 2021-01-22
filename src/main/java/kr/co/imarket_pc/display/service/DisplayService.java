package kr.co.imarket_pc.display.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.imarket_pc.common.RestTemplateApi;
import kr.co.imarket_pc.display.dto.DisplaysMainCategoriesResopnse;
import kr.co.imarket_pc.display.dto.DisplaysShopsBannersRequest;
import kr.co.imarket_pc.display.dto.DisplaysShopsBannersResopnse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisplayService {
    private final RestTemplateApi<DisplaysMainCategoriesResopnse> displaysMainCategoriesResopnseApi;
    private final RestTemplateApi<DisplaysShopsBannersResopnse> displaysShopsBannersResopnseApi;
    public final ObjectMapper objectMapper;

    public DisplaysMainCategoriesResopnse getDisplaysMainCategories(String memberNo) {

        MultiValueMap<String, String> requestQueryParams = new LinkedMultiValueMap<>();
        requestQueryParams.add("memberNo", memberNo);

        return displaysMainCategoriesResopnseApi.get("/v1/displays/main/categories"
                , null
                , requestQueryParams
                , DisplaysMainCategoriesResopnse.class);
    }

    public DisplaysShopsBannersResopnse getDisplaysShopsBanners(DisplaysShopsBannersRequest displaysShopsBannersRequest) {

        if (StringUtils.isBlank(displaysShopsBannersRequest.getDisplayNo())
                || StringUtils.isBlank(displaysShopsBannersRequest.getDisplayNo())) {
            return new DisplaysShopsBannersResopnse();
        }

        Map<String, String> requestUriParams =
                objectMapper.convertValue(displaysShopsBannersRequest, new TypeReference<Map<String, String>>() {
                });

        return displaysShopsBannersResopnseApi.get("/v1/displays/{displayNo}/shops/{shopNo}/banners"
                , requestUriParams
                , null
                , DisplaysShopsBannersResopnse.class);
    }
}
