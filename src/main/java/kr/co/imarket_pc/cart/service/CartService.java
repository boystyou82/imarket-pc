package kr.co.imarket_pc.cart.service;

import kr.co.imarket_pc.cart.dto.CartsGnbResopnse;
import kr.co.imarket_pc.common.RestTemplateApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final RestTemplateApi<CartsGnbResopnse> cartsGnbResopnseApi;

    public CartsGnbResopnse getCartsGnb(String cartNo, String memberNo) {

        if (StringUtils.isBlank(cartNo) && StringUtils.isBlank(memberNo)) {
            return new CartsGnbResopnse();
        }

        Map<String, String> UriParams = new HashMap<String, String>() {{
            put("cartNo", cartNo);
            put("memberNo", memberNo);
        }};

        String url = StringUtils.isEmpty(memberNo) ? "/v1/carts/{cartNo}/gnb" : "/v1/members/{memberNo}/carts/gnb";
        return cartsGnbResopnseApi.ptch(
                url
                , UriParams
                , null
                , CartsGnbResopnse.class);
    }
}
