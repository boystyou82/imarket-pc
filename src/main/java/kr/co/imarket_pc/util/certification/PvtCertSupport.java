package kr.co.imarket_pc.util.certification;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public abstract class PvtCertSupport {
	protected String requestReplace(String paramValue, String gubun) {
		String result = "";
		if (paramValue != null) {
			paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			paramValue = paramValue.replaceAll("\\*", "");
			paramValue = paramValue.replaceAll("\\?", "");
			paramValue = paramValue.replaceAll("\\[", "");
			paramValue = paramValue.replaceAll("\\{", "");
			paramValue = paramValue.replaceAll("\\(", "");
			paramValue = paramValue.replaceAll("\\)", "");
			paramValue = paramValue.replaceAll("\\^", "");
			paramValue = paramValue.replaceAll("\\$", "");
			paramValue = paramValue.replaceAll("'", "");
			paramValue = paramValue.replaceAll("@", "");
			paramValue = paramValue.replaceAll("%", "");
			paramValue = paramValue.replaceAll(";", "");
			paramValue = paramValue.replaceAll(":", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll("#", "");
			paramValue = paramValue.replaceAll("--", "");
			paramValue = paramValue.replaceAll("-", "");
			paramValue = paramValue.replaceAll(",", "");
			if (gubun != "encodeData") {
				paramValue = paramValue.replaceAll("\\+", "");
				paramValue = paramValue.replaceAll("/", "");
				paramValue = paramValue.replaceAll("=", "");
			}
			result = paramValue;
		}
		return result;
	}

	/**
	 * 생년월일로 나이를 계산하여 만 19세가 되지 않았으면 미성년자로 판단<br/>
	 * 나이 계산 시 생일을 지났을때만 +1세
	 * <pre>판단기준:
	 *   민법 제4조(성년) 사람은 19세로 성년에 이르게 된다.
	 *   [시행 2013.7.1.] [법률 제11300호, 2012.2.10., 일부개정]</pre>
	 * @param birthDate
	 * @return 성년:Y | 미성년:N
	 */
	protected String getAdultYn(String birthDate) {
		int year = Integer.parseInt(birthDate.substring(0, 4));
		int month = Integer.parseInt(birthDate.substring(4, 6));
		int date = Integer.parseInt(birthDate.substring(6, 8));

		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.YEAR, year);
		cd.set(Calendar.MONTH, month - 1);
		cd.set(Calendar.DATE, date);

		Date dt = cd.getTime();

		Calendar birth = new GregorianCalendar();
		Calendar today = new GregorianCalendar();

		birth.setTime(dt);
		today.setTime(new Date());

		int factor = 0;
		if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
			factor = -1;
		}

		int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + factor;

		if (age >= 19) {
			return "Y";
		} else {
			return "N";
		}
	}

	/**
	 * 리퀘스트에 실명인증에 필요한 암호화 데이터 설정 <br/>
	 * 이 데이터는 화면에서 실명인증 페이지 호출 시 페이지로 사용됨. <br/>
	 * 실명인증이 필요한 페이지를 포워딩 할 때마다 매번 호출하지 않으면 세션오류가 발생할 수 있다. <br/><br/>
	 * req.setAttribute("niceIpinEncData", "[아이핀 모듈 encode data]"); <br/>
	 * req.setAttribute("niceChkPlusEncData", "[휴대폰인증 모듈 encode data]");
	 * @param req
	 */
	public static void setAttrEncData(HttpServletRequest req, HttpServletResponse res) {
		String ipinEncodeData = new NiceIpin().getEncodeData(req, res);
		String chkPlusEncodeData = new NiceChkPlus().getEncodeData(req, res);

		req.setAttribute("niceIpinEncData", ipinEncodeData);
		req.setAttribute("niceChkPlusEncData", chkPlusEncodeData);
	}

}
