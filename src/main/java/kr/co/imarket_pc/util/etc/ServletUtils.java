package kr.co.imarket_pc.util.etc;


import javax.servlet.ServletContext;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;
import enet.cnt.config.DefaultConfig;

@UtilityClass
public class ServletUtils {

    // config.xml의 section name
    protected static final String URL_SECTION_NAME = "url";

    // Mall front의 SSL을 위한 url 패턴으로 https://www.interpark.com 과 같은 형식이다.
    protected static final String IMFS_URL_KEY_HTTPS = "imfs.https";

    /**
     * Mall front의 SSL용 url 패턴이다.
     * InterparkServletContextListener 에서 초기에 설정해준다.
     */
    public static String IMFS_URL_SSL = null;

    /**
     * <PRE>
     * 이 객체의 모든 값들을 초기화 한다.<br>
     * Servlet context가 초기화 될 당시에 이미지 url을 초기화한 후
     * 일반적인 프로그램에서는 ServletUtils.IMG_URL과 ServletUtils.IMG_URL_SSL 을 사용하도록 한다.<br>
     * 기존의 이미지를 접근하기 위해서는 ServletUtils.IMG_URL_STATIC과 ServletUtils.IMG_URL_SSL_STATIC 을 사용하도록 한다.<br>
     * 유틸리티 애플릿을 삽입하기 위해서는 ServletUtils.UTIL_APPLET_TAG를 사용하도록 한다.<br>
     * Mall Front쪽의 url패턴을 사용하고자 한다면 ServletUtils.IMFS_URL 및 ServletUtils.IMFS_URL_SSL 을 사용하도록 하는데,
     * 각각 기본적인 url 패턴 및 SSL용 url 패턴을 나타낸다.
     * </PRE>
     *
     * @param servletContext ServletContext 객체
     */
    public static void initAll(ServletContext servletContext) {
        initAllNotCode();
    }

    /**
     * <PRE>
     * 이 객체의 모든 값들을 초기화 한다.(코드 메니저를 제외한)<br>
     * Servlet context가 초기화 될 당시에 이미지 url을 초기화한 후
     * 일반적인 프로그램에서는 ServletUtils.IMG_URL과 ServletUtils.IMG_URL_SSL 을 사용하도록 한다.<br>
     * 기존의 이미지를 접근하기 위해서는 ServletUtils.IMG_URL_STATIC과 ServletUtils.IMG_URL_SSL_STATIC 을 사용하도록 한다.<br>
     * 유틸리티 애플릿을 삽입하기 위해서는 ServletUtils.UTIL_APPLET_TAG를 사용하도록 한다.<br>
     * Mall Front쪽의 url패턴을 사용하고자 한다면 ServletUtils.IMFS_URL 및 ServletUtils.IMFS_URL_SSL 을 사용하도록 하는데,
     * 각각 기본적인 url 패턴 및 SSL용 url 패턴을 나타낸다.
     * </PRE>
     * <p>
     * param servletContext ServletContext 객체
     */
    public static void initAllNotCode() {
        IMFS_URL_SSL = getImfsUrlPrefixSSL();
    }

    /**
     * Mall Front에서 사용되는 SSL용의 URL 접두문자열이다.
     * config.xml에 등록되어 있는 SSL용의 URL 접두문자열을 얻는다.
     * <br><br>
     * config.xml에는 다음과 같이 등록되어 있어야 한다.
     * <br><br>
     * <PRE>
     * <section name="url">
     * <entry key="imfs.https" value="https://www.interpark.com" />
     * </section>
     * </PRE>
     *
     * @return config.xml에 정의되어 있는 Mall Front의 URL 접두문자열
     */
    public static String getImfsUrlPrefixSSL() {
        String url = DefaultConfig.getValue(URL_SECTION_NAME, IMFS_URL_KEY_HTTPS);
        return StringUtils.defaultString(url);
    }
}
