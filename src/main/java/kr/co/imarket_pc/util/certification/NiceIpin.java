package kr.co.imarket_pc.util.certification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Kisinfo.Check.IPIN2Client;
import kr.co.imarket_pc.util.etc.Constants;
import kr.co.imarket_pc.util.cookie.CookieUtil;
import kr.co.imarket_pc.util.etc.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NiceIpin extends PvtCertSupport {

    /**
     * NICE신용평가정보로부터 부여받은 아이핀사이트 코드
     */
    private final static String SITE_CODE = "G269";
    /**
     * NICE신용평가정보로부터 부여받은 아이핀사이트 패스워드
     */
    private final static String SITE_PASSWORD = "34164064";
    /**
     * 결과 수신 URL
     */
    private final static String RETURN_URL = ServletUtils.IMFS_URL_SSL + "/member/MemberSignUp.do"
            + "?_method=pvtCertResult&certType=niceIpin";

    /**
     * encode data를 리턴한다. <br/>
     * 이 데이터는 실명인증 페이지 팝업 호출 시 파라미터로 사용된다.
     *
     * @param
     * @return 암호화된 사용자 정보
     */
    public String getEncodeData(HttpServletRequest req, HttpServletResponse res) {
        // 클라이언트 객체 생성
        IPIN2Client ipc = new IPIN2Client();

		/*
		┌ requestNo 변수에 대한 설명  ───────────────────────────────────────────────────
			[CP 요청번호]로 귀사에서 데이타를 임의로 정의하거나, 당사에서 배포된 모듈로 데이타를 생성할 수 있습니다.

			CP 요청번호는 인증 완료 후, 암호화된 결과 데이타에 함께 제공되며
			데이타 위변조 방지 및 특정 사용자가 요청한 것임을 확인하기 위한 목적으로 이용하실 수 있습니다.

			따라서 귀사의 프로세스에 응용하여 이용할 수 있는 데이타이기에, 필수값은 아닙니다.
		└────────────────────────────────────────────────────────────────────
		*/
        String requestNo = ipc.getRequestNO(SITE_CODE);
        CookieUtil.setCookie(res, "ipinReqSeq", requestNo);
        log.debug("실명인증: 요청 세션 쿠키 설정-ipinReqSeq: " + requestNo);

        // 결과값(returnCode)에 따라, 프로세스 진행여부를 파악합니다.
        int returnCode = ipc.fnRequest(SITE_CODE, SITE_PASSWORD, requestNo, RETURN_URL);

        // 사용자 요청정보 암호화
        String encodeData = ipc.getCipherData();

        // 결과값에 따른 처리사항
        switch (returnCode) {
            case 0:
                // fnRequest 함수 처리시 업체정보를 암호화한 데이터를 추출합니다.
                // 추출된 암호화된 데이타는 당사 팝업 요청시, 함께 보내주셔야 합니다.
                log.debug("요청정보_암호화_성공[ : " + encodeData + "]");
                return encodeData;
            case -1:
            case -2:
                log.debug("배포해 드린 서비스 모듈 중, 귀사 서버환경에 맞는 모듈을 "
                        + "이용해 주시기 바랍니다.\n귀사 서버환경에 맞는 모듈이 없다면\n"
                        + "iRtn 값, 서버 환경정보를 정확히 확인하여 메일로 요청해 주시기 바랍니다.");
                return "";
            case -9:
                log.debug("입력값 오류 : fnRequest 함수 처리시, 필요한 4개의"
                        + "파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");
            default:
                log.debug("iRtn 값 확인 후, NICE신용평가정보 개발 담당자에게 문의해 주세요.");
                return "";
        }
    }

    public int checkResult(HttpServletRequest req, HttpServletResponse res) {
        // 클라이언트 객체 생성
        IPIN2Client ipc = new IPIN2Client();

        // 사용자 정보 및 CP 요청번호를 암호화한 데이타입니다.
        String responseData = requestReplace(req.getParameter("enc_data"), "encodeData");
        if (StringUtils.isEmpty(responseData)) {
            log.error("실명인증 처리 중 에러가 발생했습니다. responseData is null");
            return Constants.CERTIFICATION_CODE_ERROR;
        }

        /*
         * CP 요청번호 : getEncodeData()에서 세션 처리한 requestNo
         * sessionRequestNo가 null일 경우엔 요청세션과 리턴세션을 비교하지 않고
         * 정상(returnCode=1) 처리된다. 주의
         */
        String sessionRequestNo = CookieUtil.getCookieValue(req, "ipinReqSeq");
        log.debug("실명인증: 요청 세션 쿠키 가져옴-ipinReqSeq: " + sessionRequestNo);
        if (StringUtils.isBlank(sessionRequestNo)) {
            sessionRequestNo = "0";
        }

        int returnCode = ipc.fnResponse(SITE_CODE, SITE_PASSWORD, responseData, sessionRequestNo);

        // Method 결과값에 따른 처리사항
        switch (returnCode) {
            case 1:
                // returnCode가 1일 경우 인증정보 처리 시작
                if (StringUtils.isBlank(ipc.getCoInfo1())) {
                    log.error("실명인증 처리 중 에러가 발생했습니다. CI is empty");
                    return Constants.CERTIFICATION_CODE_NICE_ERROR;
                }
                if (StringUtils.isBlank(ipc.getDupInfo())) {
                    log.error("실명인증 처리 중 에러가 발생했습니다. DI is empty");
                    return Constants.CERTIFICATION_CODE_NICE_ERROR;
                }

                // 실명인증 정보 설정
                CookieUtil.setCookie(res, Constants.IPIN_CI, ipc.getCoInfo1());
                CookieUtil.setCookie(res, Constants.IPIN_DI, ipc.getDupInfo());

                req.setAttribute("name", ipc.getName());
                req.setAttribute("birthDate", ipc.getBirthDate());
                req.setAttribute("adultYn", getAdultYn(ipc.getBirthDate()));

                /*
                 *  나이스 아이핀 모듈은 처리결과 중 성별 코드를 남자:1, 여자:0 으로 전송한다.
                 *  이를 각각 "M", "F"로 변경하여 화면에 표시한다.
                 */
                if ("1".equals(ipc.getGenderCode())) {
                    req.setAttribute("gender", "M");
                } else if ("0".equals(ipc.getGenderCode())) {
                    req.setAttribute("gender", "F");
                }
                return Constants.CERTIFICATION_CODE_SUCCESS;
            case -1:
            case -4:
            case -6:
            case -9:
            case -12:
                log.error("실명인증 처리 중 에러가 발생했습니다. returnCode: " + returnCode);
                return Constants.CERTIFICATION_CODE_ERROR;
            case -13:
                log.debug("세션이 다릅니다.");
                log.debug("CPREQUEST: " + sessionRequestNo);
                return Constants.CERTIFICATION_CODE_SESSION_NOT_EQUAL;
            default:
                log.error("실명인증 처리 중 에러가 발생했습니다. returnCode: " + returnCode);
                return Constants.CERTIFICATION_CODE_NICE_ERROR;
        }
    }
}
