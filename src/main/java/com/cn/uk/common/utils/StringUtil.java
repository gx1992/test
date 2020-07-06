package com.cn.uk.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 字符串工具类
 */
public final class StringUtil {
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    private static final int PAD_LIMIT = 8192;

    private StringUtil() {
    }

    /**
     * 校验字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }

        return false;
    }

    /**
     * 校验字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 获取格式化查询参数(逗号被当做分隔符使用)
     *
     * @param fields
     * @return
     */
    public static String getFormatQueryParams(String fields) {
        if (fields == null || "".equals(fields.trim())) {
            return "''";
        }

        String sTemp = fields;
        if (sTemp.startsWith("'")) {
            sTemp = sTemp.substring(1);
        }

        if (sTemp.endsWith("'")) {
            sTemp = sTemp.substring(0, sTemp.length() - 1);
        }

        sTemp = sTemp.replaceAll("'{0,1},'{0,1}", ",");
        sTemp = sTemp.replaceAll("'", "''");
        sTemp = sTemp.replaceAll(",", "','");
        sTemp = "'" + sTemp + "'";

        return sTemp;
    }

    /**
     * 容器container中是否包含str字符串。
     *
     * @param str
     * @param container
     * @return
     */
    public static boolean isContainSpcifyStr(String str, String[] container) {
        if (isEmpty(str)) {
            return false;
        }

        if (container == null) {
            return false;
        }

        for (int i = 0; i < container.length; i++) {
            if (str.equals(container[i])) {
                return true;
            }
        }

        return false;
    }


    /**
     * <p>Repeat a String {@code repeat} times to form a
     * new String.</p>
     *
     * <pre>
     * StringUtils.repeat(null, 2) = null
     * StringUtils.repeat("", 0)   = ""
     * StringUtils.repeat("", 2)   = ""
     * StringUtils.repeat("a", 3)  = "aaa"
     * StringUtils.repeat("ab", 2) = "abab"
     * StringUtils.repeat("a", -2) = ""
     * </pre>
     *
     * @param str    the String to repeat, may be null
     * @param repeat number of times to repeat str, negative treated as zero
     * @return a new String consisting of the original String repeated,
     * {@code null} if null String input
     */
    public static String repeat(final String str, final int repeat) {
        // Performance tuned for 2.0 (JDK1.4)

        if (str == null) {
            return null;
        }
        if (repeat <= 0) {
            return EMPTY;
        }
        final int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return str;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT) {
            return repeat(str.charAt(0), repeat);
        }

        final int outputLength = inputLength * repeat;
        switch (inputLength) {
            case 1:
                return repeat(str.charAt(0), repeat);
            case 2:
                final char ch0 = str.charAt(0);
                final char ch1 = str.charAt(1);
                final char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default:
                final StringBuilder buf = new StringBuilder(outputLength);
                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }
                return buf.toString();
        }
    }

    public static String trimStart(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.startsWith(remove)) {
            return str.substring(remove.length());
        }
        return str;
    }

    public static String trimEnd(final String str, final String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    public static String repeat(final String str, final String separator, final int repeat) {
        if (str == null || separator == null) {
            return repeat(str, repeat);
        }
        // given that repeat(String, int) is quite optimized, better to rely on it than try and splice this into it
        final String result = repeat(str + separator, repeat);
        return trimEnd(result, separator);
    }

    public static String repeat(final char ch, final int repeat) {
        final char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--) {
            buf[i] = ch;
        }
        return new String(buf);
    }

    public static String rightPad(final String str, final int size) {
        return rightPad(str, size, ' ');
    }

    public static String rightPad(final String str, final int size, final char padChar) {
        if (str == null) {
            return null;
        }
        final int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(repeat(padChar, pads));
    }

    public static String rightPad(final String str, final int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = SPACE;
        }
        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.concat(new String(padding));
        }
    }

    public static String leftPad(final String str, final int size) {
        return leftPad(str, size, ' ');
    }

    public static String leftPad(final String str, final int size, final char padChar) {
        if (str == null) {
            return null;
        }
        final int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return repeat(padChar, pads).concat(str);
    }

    public static String leftPad(final String str, final int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = SPACE;
        }
        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }


    /***
     * 在字符串左边或者右边填写上指定个数的指定字符串
     * @param srcStr 源字符串
     * @param isFillLeft true:从字符串左边填充； false：从字符串右边填写
     * @param fillStr 填充字符串
     * @param count 需要填充的个数
     * @return 格式后的字符串
     */
    public static String strFormat(String srcStr, boolean isFillLeft, int count, String fillStr) {
        if (isEmpty(fillStr)) {
            return srcStr;
        }

        if (count <= 0) {
            return srcStr;
        }

        String sResult = "";
        String sTemp = buildFormatStr(count, fillStr);
        if (isFillLeft) {
            sResult = sTemp + srcStr;
        } else {
            sResult = srcStr + sTemp;
        }

        return sResult;
    }

    /**
     * 生成指定长度格式化字符串
     *
     * @param srcStr     源字符串
     * @param isFillLeft true:从字符串左边填充； false：从字符串右边填写
     * @param length     生成字符串长度
     * @param fillStr    补充字符串
     * @return
     */
    public static String fill(String srcStr, boolean isFillLeft, int length, String fillStr) {
        if (isEmpty(fillStr)) {
            return srcStr;
        }

        if (length <= 0) {
            return srcStr;
        }

        if (isEmpty(srcStr)) {
            return srcStr;
        }

        int iLen = length - srcStr.length();
        if (iLen <= 0) {
            return srcStr;
        }

        return strFormat(srcStr, isFillLeft, iLen, fillStr);
    }

    private static String buildFormatStr(int count, String fillStr) {
        if (count <= 0 || fillStr == null || "".equals(fillStr)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count; i++) {
            sb.append(fillStr);
        }

        return sb.toString();
    }

    /**
     * 指定字符串str是否在scaleStr范围内
     *
     * @param str      目标字符串
     * @param scaleStr 范围
     * @return true - 存在指定范围内；false-不在指定范围内
     */
    public static boolean isIn(String str, String[] scaleStr) {
        if (scaleStr == null) {
            return false;
        }

        if (str == null) {
            return false;
        }

        for (int i = 0; i < scaleStr.length; i++) {
            if (str.equals(scaleStr[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * 指定字符串str是否不在scaleStr范围内
     *
     * @param str      目标字符串
     * @param scaleStr 范围
     * @return true - 不在指定范围内；false-在指定范围内
     */
    public static boolean isNotIn(String str, String[] scaleStr) {
        return !isIn(str, scaleStr);
    }


    /**
     * 以pStrBas为模板，随机生成长度为pILen的随机字符串
     *
     * @param pILen   长度(大于0)
     * @param pStrBas 生产模板基础(字符串长度大于0)
     * @return 随机字符串
     */
    public static String getRandomStr(int pILen, String pStrBas) {
        Random rand = new Random();
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < pILen; i++) {
            int iNumbr = rand.nextInt(pStrBas.length());
            sBuffer.append(pStrBas.charAt(iNumbr));
        }
        return sBuffer.toString();
    }

    public static int ipToInt(String strIp){
        int[] ip = new int[4];
        // 先找到IP地址字符串中.的位置
        int position1 = strIp.indexOf(".");
        int position2 = strIp.indexOf(".", position1 + 1);
        int position3 = strIp.indexOf(".", position2 + 1);
        // 将每个.之间的字符串转换成整型
        ip[0] = Integer.parseInt(strIp.substring(0, position1));
        ip[1] = Integer.parseInt(strIp.substring(position1 + 1, position2));
        ip[2] = Integer.parseInt(strIp.substring(position2 + 1, position3));
        ip[3] = Integer.parseInt(strIp.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    public static String ipToStr(int ip){
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((ip >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((ip & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((ip & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((ip & 0x000000FF)));
        return sb.toString();
    }

    public static long ipToLong(String ip) {
        String[] ipArray = ip.split("\\.");
        List ipNums = new ArrayList();
        for (int i = 0; i < 4; ++i) {
            ipNums.add(Long.parseLong(ipArray[i].trim()));
        }
        long iPNumTotal = ((Long) ipNums.get(0)).longValue() * 256L * 256L * 256L
                + ((Long) ipNums.get(1)).longValue() * 256L * 256L + ((Long) ipNums.get(2)).longValue() * 256L
                + ((Long) ipNums.get(3)).longValue();

        return iPNumTotal;
    }

    public static String longToIp(long ipaddr) {
        long y = ipaddr % 256;
        long m = (ipaddr - y) / (256 * 256 * 256);
        long n = (ipaddr - 256 * 256 *256 * m - y) / (256 * 256);
        long x = (ipaddr - 256 * 256 *256 * m - 256 * 256 *n - y) / 256;
        return m + "." + n + "." + x + "." + y;
    }

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
