package kr.co.imarket_pc.plant.controller;

import kr.co.imarket_pc.plant.dto.*;
import kr.co.imarket_pc.plant.service.PlantService;
import kr.co.imarket_pc.session.service.SessionService;
import kr.co.imarket_pc.vo.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PlantController {

    public final SessionService sessionService;
    public final PlantService plantService;

    @RequestMapping("/api/plants/{plantId}")
    public PlantMainResopnse getPlant(PlantMainRequest plantMainRequest
            , HttpServletRequest req
            , HttpServletResponse res) {

        MemberVo memberVo = sessionService.getMemberCookieSession(req, res); //사용자 정보 조회 (쿠키 및 세션)
        plantMainRequest.setMemberNo(Optional.ofNullable(memberVo)
                                    .map(MemberVo::getMemberNo)
                                    .orElse(null));
        PlantMainResopnse plantMainResopnse = plantService.getPlant(plantMainRequest);

        if ("N".equals(plantMainResopnse.getUserChnlYn())) {
            plantMainResopnse.setForwardPage("/display/malls.do?_method=welcome");
        }

        return plantMainResopnse;
    }

    @RequestMapping("/api/plants/events/categories")
    public PlantsEventsCategoriesResopnse getPlantsEventsCategorys(
            HttpServletRequest req
            , HttpServletResponse res
            , PlantsEventsCategoriesRequest plantsEventsCategoriesRequest) {

        MemberVo memberVo = sessionService.getMemberCookieSession(req, res); //사용자 정보 조회 (쿠키 및 세션)
        plantsEventsCategoriesRequest.setMemberNo(
                Optional.ofNullable(memberVo)
                        .map(MemberVo::getMemberNo)
                        .orElse(null)
        );

        return plantService.getPlantsEventsCategories(plantsEventsCategoriesRequest);
    }

    @RequestMapping("/api/plants/events/products")
    public PlantsEventsProductsResopnse getPlantsEventsProducts(
            HttpServletRequest req
            , HttpServletResponse res
            , PlantsEventsProductsRequest plantsEventsProductsRequest) {

        MemberVo memberVo = sessionService.getMemberCookieSession(req, res); //사용자 정보 조회 (쿠키 및 세션)

        plantsEventsProductsRequest.setMemberNo(
                Optional.ofNullable(memberVo)
                        .map(MemberVo::getMemberNo)
                        .orElse(null));

        return plantService.getPlantsEventsProducts(plantsEventsProductsRequest);
    }

    @RequestMapping("/api/plants/banners")
    public PlantsBannersResopnse getPlantsBanners() {
        return plantService.getPlantsBanners();
    }

}
