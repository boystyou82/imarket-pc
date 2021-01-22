package kr.co.imarket_pc.product.service;

import kr.co.imarket_pc.common.RestTemplateApi;
import kr.co.imarket_pc.product.dto.RecentlyViewedProductsResopnse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final RestTemplateApi<RecentlyViewedProductsResopnse> recentlyViewedProductsResopnseApi;

    public RecentlyViewedProductsResopnse getRecentlyViewedProducts(List<String> productNoList) {

        MultiValueMap<String, String> recentlyViewedProductsRequestQueryParams = new LinkedMultiValueMap<>();
        recentlyViewedProductsRequestQueryParams.addAll("productNoList", productNoList);

        return recentlyViewedProductsResopnseApi.get("/v1/products/viewed"
                , null
                , recentlyViewedProductsRequestQueryParams
                , RecentlyViewedProductsResopnse.class);
    }
}
