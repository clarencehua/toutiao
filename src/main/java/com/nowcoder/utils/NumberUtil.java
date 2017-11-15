package com.nowcoder.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * ��ֵ��صĹ�����
 */
public class NumberUtil {

	/**
	 * ��ֵ������λС��
	 */
	public static String changeNumberType(BigDecimal big) {

		big.setScale(2, RoundingMode.HALF_UP);
		DecimalFormat format = new DecimalFormat("##0.00");
		return format.format(big);
	}

	/**
	 * �ж��ַ����Ƿ�������
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * �ж��ַ����Ƿ��Ǹ�����
	 */
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains("."))
				return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * �ж��ַ����Ƿ�������
	 */
	public static boolean isNumber(String value) {
		if(StringUtil.isEmpty(value)){
			return false;
		}
		return isInteger(value) || isDouble(value);
	}
}
