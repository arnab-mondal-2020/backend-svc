package com.bp.micro.svc.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public final class CommonUtil implements CommonConstants {
	private CommonUtil(){}
	
	public static boolean isEmptyOrNull(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static boolean containsNoNumericOrSpecialCharacter(String str) {
		Pattern pattern = Pattern.compile(EXPR_ALPHA_NUMERIC);
		Matcher matcher = pattern.matcher(str);
		return !matcher.matches();
	}

	public static boolean isNumericOnly(String str) {
		return str.matches(EXPR_NUMERIC);
	}

	public static boolean isAlphaOnly(String str) {
		Pattern pattern = Pattern.compile(EXPR_ALPHA);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static boolean isValidDate(String dateOfBirth) {
		try {
			String[] tokens = dateOfBirth.split(DELIMITER);
			int dd = new Integer(tokens[0]);
			int mm = new Integer(tokens[1]);
			int yy = new Integer(tokens[2]);
			if (yy < 1990 || yy > 2006) {
				return false;
			}
			boolean isLeapYear = checkForLeapYear(yy);

			if (isLeapYear) {
				if (mm == 2) {
					if (dd < 1 || dd > 29) {
						return false;
					}
				}
			} else {
				if (mm == 2) {
					if (dd < 1 || dd > 28) {
						return false;
					}
				}
			}
			if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
				if (dd < 1 || dd > 31) {
					return false;
				}
			}
			if (mm == 4 || mm == 6 || mm == 9 || mm == 11) {
				if (dd < 1 || dd > 30) {
					return false;
				}
			}
			if (mm < 1 || mm > 12) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private static boolean checkForLeapYear(int yy) {
		return (yy % 100 == 0 && yy % 400 == 0) || (yy % 100 != 0 && yy % 4 == 0);
	}

	public static boolean isValidContactNo(String contactNo) {
		if (isNumericOnly(contactNo)) {
			if (contactNo.length() == 10) {
				if (contactNo.startsWith("0")) {
					return false;
				}
			} else if (contactNo.length() > 10 && contactNo.length() < 16) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean isValidEmail(String email) {
		if (isEmptyOrNull(email)) {
			return true;
		}
		int atIndex = email.indexOf("@");
		if (atIndex == -1) {
			return false;
		}
		String[] emailTokens = email.split("@");
		if (emailTokens.length > 2) {
			return false;
		}
		String userId = emailTokens[0];
		String domain = emailTokens[1];
		if (whiteSpaceChar(userId) || whiteSpaceChar(domain)) {
			return false;
		}
		if (userId.length() < 3) {
			return false;
		}
		String[] domainTokens = domain.split("\\.");
		for (String token : domainTokens) {
			if (token.length() < 2 || !containsNoNumericOrSpecialCharacter(token)) {
				return false;
			}
		}
		return true;
	}

	public static boolean whiteSpaceChar(String str) {
		char[] whiteSpaceChars = WHITESPACE_CHARS.toCharArray();
		for (char c : whiteSpaceChars) {
			if (str.contains("" + c)) {
				return true;
			}
		}
		return false;
	}

	public static void setWelcomeMessage(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String dateStr = sdf.format(new Date());
		String hrStr = dateStr.split(":")[0];

		int hour = new Integer(hrStr);
		if (hour >= 0 && hour < 12) {
			request.setAttribute(WELCOME_MSG, GOOD_MORNING);
		} else if (hour > 12 && hour < 18) {
			request.setAttribute(WELCOME_MSG, GOOD_AFTERNOON);
		} else {
			request.setAttribute(WELCOME_MSG, GOOD_EVENING);
		}

	}

	public static void loadFail(HttpServletRequest request) {
		request.setAttribute(BODY_INC, FAIL);
	}

	private static Map<String, String> actMap;

	static {
		actMap = new HashMap<String, String>();
		actMap.put(ACTIVITY_ADD_STUDENT, "Add Student");
		actMap.put(ACTIVITY_ADD_USER, "Add User");
		actMap.put(ACTIVITY_EDIT_STUDENT, "Edit Student");
		actMap.put(ACTIVITY_EDIT_USER, "Edit User");
		actMap.put(ACTIVITY_REG, "Register");
	}

	public static final Map<String, String> getActivitiesMap() {
		return actMap;
	}
}
