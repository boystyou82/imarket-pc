package kr.co.imarket_pc.util.session;

import kr.co.imarket_pc.util.etc.Constants;
import kr.co.imarket_pc.vo.member.MemberVo;
import kr.co.imarket_pc.vo.plant.SpecialityMainVo;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@UtilityClass
public class SessionUtil {

    public void setSpecialityMallSessionDefault(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute(Constants.EXCLUSIVE_TYPE, "00");
        session.setAttribute(Constants.IS_SP_MALL, "N");
    }

    public void setSpecialityMallSession(HttpServletRequest req, SpecialityMainVo specialityMainVo) {

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

        HttpSession session = req.getSession();
        session.setAttribute(Constants.EXCLUSIVE_TYPE, exclTp);
        session.setAttribute(Constants.LOGO_IMG_URL, logoImgUrl);
        session.setAttribute(Constants.IS_SP_MALL, spmallYn);
        session.setAttribute(Constants.REQ_URL, reqUri);
        session.setAttribute(Constants.MALL_CHNL_NO, chnlNo);
        session.setAttribute(Constants.REFERRAL_CD, chnlNo);
    }

    public void setMemberSessionKey(HttpServletRequest req, MemberVo memberVo) {
        HttpSession session = req.getSession();
        session.setAttribute(Constants.USERINFO_SESSION_KEY, memberVo);
    }

    public MemberVo getMemberSessionKey(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (MemberVo) session.getAttribute(Constants.USERINFO_SESSION_KEY);
    }

    public void removeMemberSessionKey(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.removeAttribute(Constants.USERINFO_SESSION_KEY); // SES_IMFS_USERINFO
    }

    public void removeMemberSession(HttpServletRequest req) {
        removeMemberSessionKey(req);
        expireSpecialityMallSession(req);
    }

    public void expireSpecialityMallSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.removeAttribute(Constants.EXCLUSIVE_TYPE);
        session.removeAttribute(Constants.LOGO_IMG_URL);
        session.removeAttribute(Constants.IS_SP_MALL);
        session.removeAttribute(Constants.REQ_URL);
        session.removeAttribute(Constants.MALL_CHNL_NO);
        session.removeAttribute(Constants.REFERRAL_CD);

        setSpecialityMallSessionDefault(req);
    }

}