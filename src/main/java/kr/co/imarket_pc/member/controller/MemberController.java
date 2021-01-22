package kr.co.imarket_pc.member.controller;

import com.interpark.fw.security.Security;
import com.interpark.fw.security.SecurityFactory;
import kr.co.imarket_pc.member.dto.MembersLoginRequest;
import kr.co.imarket_pc.member.dto.MembersLoginResponse;
import kr.co.imarket_pc.member.service.MemberService;
import kr.co.imarket_pc.session.service.SessionService;
import kr.co.imarket_pc.vo.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private Security security = SecurityFactory.getSecurity();
    public final MemberService memberService;
    public final SessionService sessionService;

    @RequestMapping("/api/members/login")
    public MembersLoginResponse getMembersLogin(
            MembersLoginRequest membersLoginRequest
            , HttpServletRequest req
            , HttpServletResponse res) {

        int resCode = 0;
        String resMsg = "";
        String memCloseYn = "";
        String sucessYN = "Y";

        //아이디 대문자 변환
        membersLoginRequest.setMemberId(
                Optional.ofNullable(membersLoginRequest.getMemberId())
                        .map(String::toUpperCase)
                        .orElse(null)
        );

        //Member 정보 조회
        MemberVo memberVo =
                memberService.getMembersMemberId(membersLoginRequest.getMemberId()).getMemberDto();

        if (ObjectUtils.isEmpty(memberVo)) {
            return MembersLoginResponse.builder()
                    .resCode(-1)
                    .resMsg("아이디가 존재하지 않습니다.")
                    .build();
        }

        // 로그인 실패 횟수 확인 5회 이상 로그인 실패 시
        if (memberVo.getPwdFailCnt() >= 5) {
            return MembersLoginResponse.builder()
                    .resCode(-6)
                    .resMsg("고객님의 패스워드가 5회 이상 일치하지 않았습니다. 비밀번호 찾기에서 임시 비밀번호를 발급 받으시거나 고객센터로 연락주시기 바랍니다.")
                    .build();
        }

        //비밀번호 암호화
        membersLoginRequest.setPwd(
                security.encrypt(
                        Optional.ofNullable(membersLoginRequest.getPwd())
                                .orElse("")));

        if (!memberVo.getPwd().equals(membersLoginRequest.getPwd())) {
            // 아이디는 맞지만 비밀번호가 다를경우 pwd_fail_cnt + 1
            if (200 == memberService.patchMembersFailure(memberVo.getMemberNo())) {
                return MembersLoginResponse.builder()
                        .resCode(-4)
                        .resMsg("비밀번호가 올바르지 않습니다.")
                        .build();
            } else {
                return MembersLoginResponse.builder()
                        .resCode(-999)
                        .resMsg(memberVo.getMemberNo())
                        .build();
            }
        }

        if ("02".equals(memberVo.getMemGrd())) {
            return MembersLoginResponse.builder()
                    .resCode(-999)
                    .resMsg(memberVo.getMemberNo())
                    .build();
        }

        if (!"01".equals(memberVo.getMemStat())
                && !"05".equals(memberVo.getMemStat())) {
            return MembersLoginResponse.builder()
                    .resCode(-5)
                    .resMsg("탈퇴한 아이디입니다.")
                    .build();
        }

        // 휴면회원 여부 확인
//        if ("05".equals(memberVo.getMemStat())) {
//            memCloseYn = "Y";
//
//            // 휴면회원 복원처리
//            resCode = Optional.ofNullable(memberVo.getMemberNo())
//                        .map(memberService::patchMembersActivate)
//                        .orElse(0);
//
//            // 휴면회원 전환 성공 여부 확인
//            if (resCode != 200) {
//                return MembersLoginResponse.builder()
//                        .resCode(-7)
//                        .resMsg("휴면계정 전환이 성공하지 못했습니다. 관리자에게 문의해 주세요.")
//                        .build();
//            } else {
//                memberVo =
//                        Optional.ofNullable(membersLoginRequest.getMemberId())
//                                .map(memberService::getMembersMemberId)
//                                .map(MembersMemberIdResopnse::getMemberDto)
//                                .orElse(null);
//            }
//        }

        // 로그인 성공 시 비밀번호 실패 횟수 초기화
        if (memberService.patchMembersReset(memberVo.getMemberNo()) != 200) {
            return MembersLoginResponse.builder()
                    .resCode(-7)
                    .resMsg("비밀번호 횟수 초기화에 실패 하였습니다. 관리자에게 문의해 주세요.")
                    .build();
        }

        // 로그인 세션 생성
        memberVo = sessionService.createMemberCookieSessionInfo(req, res, memberVo, membersLoginRequest);

        if (ObjectUtils.isEmpty(memberVo)) {
            sucessYN = "N";
        }

        return MembersLoginResponse.builder()
                .resCode(resCode)
                .resMsg(resMsg)
                .memCloseYn(memCloseYn)
                .sucessYN(sucessYN)
                .redirectUri("")
                .isWelcome(membersLoginRequest.getIsWelcome())
                .imfsUserPath(membersLoginRequest.getImfsUserPath())
                .imfsUserQuery(membersLoginRequest.getImfsUserQuery())
                .build();
    }
}
