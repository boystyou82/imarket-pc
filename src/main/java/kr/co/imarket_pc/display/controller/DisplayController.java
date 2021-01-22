package kr.co.imarket_pc.display.controller;

import kr.co.imarket_pc.board.service.BoardService;
import kr.co.imarket_pc.cart.service.CartService;
import kr.co.imarket_pc.display.dto.DisplaysFooterResopnse;
import kr.co.imarket_pc.display.dto.DisplaysGnbResopnse;
import kr.co.imarket_pc.display.dto.DisplaysShopsBannersRequest;
import kr.co.imarket_pc.display.service.DisplayService;
import kr.co.imarket_pc.mall.dto.MallsSpecialtyRequest;
import kr.co.imarket_pc.mall.service.MallService;
import kr.co.imarket_pc.member.service.MemberService;
import kr.co.imarket_pc.product.service.ProductService;
import kr.co.imarket_pc.search.service.SearchService;
import kr.co.imarket_pc.session.service.SessionService;
import kr.co.imarket_pc.util.cookie.CookieUtil;
import kr.co.imarket_pc.util.etc.Constants;
import kr.co.imarket_pc.vo.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DisplayController {

    public final SessionService sessionService;
    public final DisplayService displayService;
    public final ProductService productService;
    public final SearchService searchService;
    public final MemberService memberService;
    public final MallService mallService;
    public final CartService cartService;
    public final BoardService boardService;

    @RequestMapping("/api/displays/gnb")
    public DisplaysGnbResopnse getDisplaysGnb(HttpServletRequest req
            , HttpServletResponse res) {

        MemberVo memberVo =
                Optional.ofNullable(sessionService.getMemberCookieSession(req, res))
                        .orElseGet(MemberVo::new); //사용자 정보 조회 (쿠키 및 세션)

        //alreadyViewPrdNo 1037584734^1039114965^1038050887^1037537566^1037486898^1039120819^1039064965^1038902643^1038143525^1039214158^1039092339^1039002243^1039130640^1038643924^1038897475^1037467177^1037579287^
        String recentlyViewedProduct = CookieUtil.getCookieValue(req, Constants.ALREADY_VIEW_PRDNO);
        String cartNo = CookieUtil.getCookieValue(req, Constants.SES_CART_NO);

        return DisplaysGnbResopnse.builder()
                .displaysMainCategoriesResopnse(
                        displayService.getDisplaysMainCategories(memberVo.getMemberNo()))
                .recentlyViewedProductsResopnse(
                        productService.getRecentlyViewedProducts(
                                Arrays.asList(recentlyViewedProduct.split("\\^"))))
                .mallsSpecialtyResopnse(
                        mallService.getMallsSpecialty(
                                MallsSpecialtyRequest.builder()
                                        .highDisplayNo("101028")
                                        .shopNo("0000200000")
                                        .build()))
                .searchWordsGnbResopnse(
                        searchService.getSearchWordsGnb())
                .membersOrdersCountResopnse(
                        memberService.getMembersOrdersCount(memberVo.getMemberNo()))
                .cartsGnbResopnse(
                        cartService.getCartsGnb(cartNo, memberVo.getMemberNo()))
                .displaysShopsBannersResopnse(
                        displayService.getDisplaysShopsBanners(
                                DisplaysShopsBannersRequest.builder()
                                        .displayNo("101032")
                                        .shopNo("0000200000")
                                        .build()))
                .build();
    }

    @RequestMapping("/api/displays/footer")
    public DisplaysFooterResopnse getDisplaysFooter() {
        return DisplaysFooterResopnse.builder()
                .boardsResopnse(boardService.getBoards())
                .build();
    }
}