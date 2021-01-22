package kr.co.imarket_pc.util.certification;

import NiceID.Check.CPClient;
import kr.co.imarket_pc.util.cookie.CookieUtil;
import kr.co.imarket_pc.util.etc.Constants;
import kr.co.imarket_pc.util.etc.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * NICE 체크플러스(CHECK PLUS SAFE-휴대폰인증) 실명인증
 *
 * @author fixalot
 * @since 2014-04-29
 * @version 1.0.0
 */
@Slf4j
@Component
public class NiceChkPlus extends PvtCertSupport {

	/** NICE신용평가정보로부터 부여받은 사이트 코드 */
	private final static String SITE_CODE	= "G4506";

	/** NICE신용평가정보부터 부여받은 사이트 패스워드*/
	private final static String SITE_PASSWORD	= "RJXRKU1PPG7P";

	/** 성공시 이동될 URL */
	private final static String RETURN_URL = ServletUtils.IMFS_URL_SSL + "/member/MemberSignUp.do"
			+ "?_method=pvtCertResult&certType=niceChkPlus&success=Y";

	/** 실패시 이동될 URL */
	private final static String ERROR_URL = ServletUtils.IMFS_URL_SSL + "/member/MemberSignUp.do"
			+ "?_method=pvtCertResult&certType=niceChkPlus&success=N";

	/** 성공시 이동될 URL */
	private final static String MOBILE_RETURN_URL = "http://m.imarket.co.kr/user/niceCallback.do";

	/** 실패시 이동될 URL */
	private final static String MOBILE_ERROR_URL = "http://m.imarket.co.kr/user/niceCallback.do";

	/**
	 * encode data를 리턴한다. <br/>
	 * 이 데이터는 실명인증 페이지 팝업 호출 시 파라미터로 사용된다.
	 * @param
	 * @return 암호화된 사용자 정보
	 */
	public String getEncodeData(HttpServletRequest req, HttpServletResponse res) {
		// 클라이언트 객체 생성
		CPClient cpc = new CPClient();

		// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로
		// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
		// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
		String requestNo = cpc.getRequestNO(SITE_CODE);

		CookieUtil.setCookie(res, "checkplusReqSeq", requestNo);
		log.debug("실명인증: 요청 세션 쿠키 설정-checkplusReqSeq: " + requestNo);

		String authType = ""; // 없으면 기본 선택화면, M: 핸드폰, C: 신용카드, X: 공인인증서
		String popupGubun = "N"; // Y : 취소버튼 있음 / N : 취소버튼 없음
		String customize = ""; // 없으면 기본 웹페이지 / Mobile : 모바일페이지

		// 입력될 plain 데이타를 만든다.
		String plainData = "7:REQ_SEQ" + requestNo.getBytes().length + ":" + requestNo
				+ "8:SITECODE" + SITE_CODE.getBytes().length + ":" + SITE_CODE
				+ "9:AUTH_TYPE" + authType.getBytes().length + ":" + authType
				+ "7:RTN_URL" + RETURN_URL.getBytes().length + ":" + RETURN_URL
				+ "7:ERR_URL" + ERROR_URL.getBytes().length + ":" + ERROR_URL
				+ "11:POPUP_GUBUN" + popupGubun.getBytes().length + ":" + popupGubun
				+ "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize;

		int returnCode = cpc.fnEncode(SITE_CODE, SITE_PASSWORD, plainData);

		String encodeData = cpc.getCipherData();

		switch (returnCode) {
			case 0:
				log.debug("요청정보_암호화_성공[ : " + encodeData + "]");
				return encodeData;
			case -1:
				log.debug("암호화 시스템 에러입니다.");
				return "";
			case -2:
				log.debug("암호화 처리오류입니다.");
				return "";
			case -3:
				log.debug("암호화 데이터 오류입니다.");
				return "";
			case -9:
				log.debug("입력 데이터 오류입니다.");
				return "";
			default:
				log.debug("알수 없는 에러 입니다. iReturn : " + returnCode);
				return "";
		}
	}

	/**
	 * encode data를 리턴한다. <br/>
	 * 이 데이터는 실명인증 페이지 팝업 호출 시 파라미터로 사용된다.
	 * @param
	 * @return 암호화된 사용자 정보
	 */
	public String getMobileEncodeData(HttpServletRequest req, HttpServletResponse res) {
		// 클라이언트 객체 생성
		CPClient cpc = new CPClient();

		// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로
		// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
		// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
		String requestNo = cpc.getRequestNO(SITE_CODE);

		CookieUtil.setCookie(res, "checkplusReqSeq", requestNo);
		log.debug("실명인증: 요청 세션 쿠키 설정-checkplusReqSeq: " + requestNo);

		String authType = "M"; // 없으면 기본 선택화면, M: 핸드폰, C: 신용카드, X: 공인인증서
		String popupGubun = "N"; // Y : 취소버튼 있음 / N : 취소버튼 없음
		String customize = "Mobile"; // 없으면 기본 웹페이지 / Mobile : 모바일페이지

		// 입력될 plain 데이타를 만든다.
		String plainData = "7:REQ_SEQ" + requestNo.getBytes().length + ":" + requestNo
				+ "8:SITECODE" + SITE_CODE.getBytes().length + ":" + SITE_CODE
				+ "9:AUTH_TYPE" + authType.getBytes().length + ":" + authType
				+ "7:RTN_URL" + RETURN_URL.getBytes().length + ":" + RETURN_URL
				+ "7:ERR_URL" + ERROR_URL.getBytes().length + ":" + ERROR_URL
				+ "11:POPUP_GUBUN" + popupGubun.getBytes().length + ":" + popupGubun
				+ "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize;

		int returnCode = cpc.fnEncode(SITE_CODE, SITE_PASSWORD, plainData);

		String encodeData = cpc.getCipherData();

		switch (returnCode) {
			case 0:
				log.debug("요청정보_암호화_성공[ : " + encodeData + "]");
				return encodeData;
			case -1:
				log.debug("암호화 시스템 에러입니다.");
				return "";
			case -2:
				log.debug("암호화 처리오류입니다.");
				return "";
			case -3:
				log.debug("암호화 데이터 오류입니다.");
				return "";
			case -9:
				log.debug("입력 데이터 오류입니다.");
				return "";
			default:
				log.debug("알수 없는 에러 입니다. iReturn : " + returnCode);
				return "";
		}
	}

	/**
	 * encode data를 리턴한다. <br/>
	 * 이 데이터는 실명인증 페이지 팝업 호출 시 파라미터로 사용된다.
	 * @param
	 * @return 암호화된 사용자 정보
	 */
	public String getEncodeDataHp(HttpServletRequest req, HttpServletResponse res) {
		// 클라이언트 객체 생성
		CPClient cpc = new CPClient();

		// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로
		// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
		// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
		String requestNo = cpc.getRequestNO(SITE_CODE);
		req.setAttribute("CHECKPLUS_REQ_SEQ", requestNo);

		CookieUtil.setCookie(res, "checkplusReqSeq", requestNo);
		log.debug("실명인증: 요청 세션 쿠키 설정-checkplusReqSeq: " + requestNo);

		String authType = "M"; // 없으면 기본 선택화면, M: 핸드폰, C: 신용카드, X: 공인인증서
		String popupGubun = "N"; // Y : 취소버튼 있음 / N : 취소버튼 없음
		String customize = ""; // 없으면 기본 웹페이지 / Mobile : 모바일페이지

		// 입력될 plain 데이타를 만든다.
		String plainData = "7:REQ_SEQ" + requestNo.getBytes().length + ":" + requestNo
				+ "8:SITECODE" + SITE_CODE.getBytes().length + ":" + SITE_CODE
				+ "9:AUTH_TYPE" + authType.getBytes().length + ":" + authType
				+ "7:RTN_URL" + MOBILE_RETURN_URL.getBytes().length + ":" + MOBILE_RETURN_URL
				+ "7:ERR_URL" + MOBILE_ERROR_URL.getBytes().length + ":" + MOBILE_ERROR_URL
				+ "11:POPUP_GUBUN" + popupGubun.getBytes().length + ":" + popupGubun
				+ "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize;

		int returnCode = cpc.fnEncode(SITE_CODE, SITE_PASSWORD, plainData);

		String encodeData = cpc.getCipherData();

		switch (returnCode) {
			case 0:
				log.debug("요청정보_암호화_성공[ : " + encodeData + "]");
				return encodeData;
			case -1:
				log.debug("암호화 시스템 에러입니다.");
				return "";
			case -2:
				log.debug("암호화 처리오류입니다.");
				return "";
			case -3:
				log.debug("암호화 데이터 오류입니다.");
				return "";
			case -9:
				log.debug("입력 데이터 오류입니다.");
				return "";
			default:
				log.debug("알수 없는 에러 입니다. iReturn : " + returnCode);
				return "";
		}
	}

	public int checkResult(HttpServletRequest req, HttpServletResponse res) {
		// 클라이언트 객체
		CPClient cpc = new CPClient();

		// 인증화면에서 넘어온 encode data
		String encodeData = requestReplace(req.getParameter("EncodeData"), "encodeData");
		if (StringUtils.isEmpty(encodeData)) {
			log.error("실명인증 처리 중 에러가 발생했습니다. encodeData is null");
			return Constants.CERTIFICATION_CODE_ERROR;
		}

		int returnCode = cpc.fnDecode(SITE_CODE, SITE_PASSWORD, encodeData);
		switch (returnCode) {
			case 0:
				// 데이터 추출
				String plainData = cpc.getPlainData();
				@SuppressWarnings("unchecked")
				HashMap<String, String> mapResult = (HashMap<String, String>) cpc.fnParse(plainData);
				if (mapResult == null) {
					log.error("실명인증 처리 중 에러가 발생했습니다. mapResult is null");
					return Constants.CERTIFICATION_CODE_NICE_ERROR;
				}

				// request.parameter.success가 없거나 "N"일 경우 인증 실패로 간주한다.
				String successYn = (String) req.getParameter("success");
				if (StringUtils.isEmpty(successYn) || "N".equals(successYn)) {

					// 오류코드가 존재할경우 코드값을 Return
					String err_code = mapResult.get("ERR_CODE");
					if( err_code !=null && !"".equals(err_code)){
						return Integer.valueOf(err_code);
					}

					return Constants.CERTIFICATION_CODE_ERROR;
				}

				// 실명인증 모듈 사용자 암호화 시에 생성한
				// requestNO와 세션을 비교하여 다르면 비정상 접속으로 간주한다.
				String requestNo = (String) mapResult.get("REQ_SEQ");
				log.debug("NICE에서 리턴한 세션정보: " + requestNo);

				String sessionRequestNo = CookieUtil.getCookieValue(req, "checkplusReqSeq");
				log.debug("실명인증: 요청 세션 쿠키 가져옴-checkplusReqSeq: " + sessionRequestNo);

				if (!requestNo.equals(sessionRequestNo)) {
					log.debug("세션값이 다릅니다.");
					return Constants.CERTIFICATION_CODE_SESSION_NOT_EQUAL;
				}

				// returnCode가 0이며 비정상 접속이 아닐 경우 실명인증 정보 설정
				CookieUtil.setCookie(res, Constants.IPIN_CI, mapResult.get("CI"));
				CookieUtil.setCookie(res, Constants.IPIN_DI, mapResult.get("DI"));

				req.setAttribute("name", mapResult.get("NAME"));
				req.setAttribute("mobileNo", mapResult.get("MOBILE_NO"));
				req.setAttribute("mobileCo", mapResult.get("MOBILE_CO"));
				req.setAttribute("birthDate", mapResult.get("BIRTHDATE"));
				req.setAttribute("adultYn", getAdultYn(mapResult.get("BIRTHDATE")));

				/*
				 *  나이스 체크플러스 모듈은 처리결과 중 성별 코드를 남자:1, 여자:0 으로 전송한다.
				 *  이를 각각 "M", "F"로 변경하여 화면에 표시한다.
				 */
				String gender = mapResult.get("GENDER");
				if ("1".equals(gender)) {
					req.setAttribute("gender", "M");
				} else if ("0".equals(gender)) {
					req.setAttribute("gender", "F");
				}
				return Constants.CERTIFICATION_CODE_SUCCESS;

			default:
				log.error("실명인증 처리 중 에러가 발생했습니다. returnCode: " + returnCode);
				return returnCode;
		}
	}

}
