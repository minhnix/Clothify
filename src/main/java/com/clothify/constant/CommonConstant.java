package com.clothify.constant;

import java.util.UUID;

public final class CommonConstant {
  public static final String DEFAULT_LANGUAGE_CODE = "vi";
  public static final String SUCCESS = "success";
  public static final String FAILURE = "failure";
  public static final UUID UNKNOWN = UUID.fromString("00000000-0000-0000-0000-000000000000");
  public static final String ROLE_PREFIX = "ROLE_";
  public static final String RULE_PASSWORD =
      "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,32})"; // (?=.*[@#$%])
  public static final String RULE_POSTAL_CODE = "([0-9].{1})";
  public static final String RULE_PHONE_NUMBER = "([0-9].{9,12})";

  public static final String RULE_ROLE =
      "ADMIN|MANAGER|CUSTOMER|MODERATOR_ADMIN|MODERATOR_MANAGER|MEMBER";
  public static final String RULE_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
  public static final UUID SYSTEM_ID = new UUID(0, 0);
  public static final String RULE_INVITE_CODE = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";

  public static final String NOTIFICATION_EXCHANGE_NAME_PREFIX = "notifications/";
  public static final String ADMIN_EXCHANGE_NAME_PREFIX = "admin/notifications/";

  public static final String BASE_PACKAGE_ENDPOINT = "com.sucodev.s_booking.web";
  public static final Double AMOUNT_PURCHASED_PER_POINT = 1000.0;
  public static final String PASSWORD_DEFAULT = "123456Aa";
  public static final Object DYNAMIC_LINK = "https://users.s-booking.net/";
  public static final Object ANDROID_LINK =
      "https://play.google.com/store/apps/details?id=com.sucodev.SBooking";
  public static final Object IOS_LINK = "https://apps.apple.com/app/s-booking/id6477146063";
  public static final String STATUS_CONTACT_ACCOUNT_REGEX = "WAITING|DONE|REJECT";
  public static final String RULE_ACTIVE_STATUS = "ACTIVATE|DEACTIVATE";
  public static final String RULE_NOTIFICATION_TYPE =
      "MESSAGE|ADMIN|BOOKING|PAYMENT|REVIEW|CALL|PRODUCT_DISCOUNT|REQUEST_ACTIVE|REPORT|CONTACT|VOUCHER|SERVICE_DISCOUNT|BRANCH|REQUEST_ACTIVE_BRANCH|INVOICE|MEMBER_IN_BRANCH|CHECKIN|ALL";
  public static final String RULE_BLOCK_TYPE = "START|NORMAL";
  public static final String RULE_USER_INPUT_TYPE = "TEXT_INPUT|QUICK_REPLY|BUTTON";
  public static final String DATE_FORMAT = "yyyy-MM-dd";
  public static final String TIME_FORMAT = "HH:mm:ss:SSS";
  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  //  privileges
  public static final String ADD_PRIVILEGE = "ADD";
  public static final String EDIT_PRIVILEGE = "EDIT";
  public static final String DELETE_PRIVILEGE = "DELETE";
  public static final String VIEW_PRIVILEGE = "VIEW";

  //  branch
  public static final String BRANCH_FUNCTION = "BRANCH";
  public static final String SERVICE_FUNCTION = "SERVICE";
  public static final String ATTRIBUTE_FUNCTION = "ATTRIBUTE";
  public static final String INVOICE_FUNCTION = "INVOICE";
  public static final String PRODUCT_FUNCTION = "PRODUCT";
  //  bank info
  public static final String BANK_INFO_FUNCTION = "BANK_INFO";
  public static final String ADMIN_BANK_INFO = "ADMIN_BANK_INFO";
  // category
  public static final String CATEGORY_FUNCTION = "CATEGORY";
  public static final String BRANCH_CATEGORY_FUNCTION = "BRANCH_CATEGORY";
  public static final String SERVICE_CATEGORY_FUNCTION = "SERVICE_CATEGORY";
  public static final String PRODUCT_CATEGORY_FUNCTION = "PRODUCT_CATEGORY";
  //  contact
  public static final String CONTACT_FUNCTION = "CONTACT";
  public static final String CONTACT_ACCOUNT_FUNCTION = "CONTACT_ACCOUNT";
  public static final String CONTACT_CATEGORY_FUNCTION = "CONTACT_CATEGORY";
  //  report
  public static final String REPORT_FUNCTION = "REPORT";

  //  user
  public static final String USER_FUNCTION = "USER";
  public static final String CUSTOMER_FUNCTION = "CUSTOMER";
  public static final String USER_INFO_FUNCTION = "USER_INFO";
  //  Payment
  public static final String PAYMENT_FUNCTION = "PAYMENT";
  public static final String PAYMENT_DURATION_FUNCTION = "PAYMENT_DURATION";
  public static final String PAYMENT_HISTORY_FUNCTION = "PAYMENT_HISTORY";

  public static final String REASON_DEFAULT = "Không xác định";
  public static final String RULE_PRODUCT_TYPE = "ORDER_SIZE|ORDER_DEFAULT|DEFAULT";
  public static final String RULE_SERVICE_TYPE =
      "BOOKING_DAYS|BOOKING_HOURS|BOTH_BOOKING_DAYS_AND_HOURS";

  public static final String ORDER_SIZE = "ORDER_SIZE";
  public static final String MANAGER_POSITION = "Quản lí";
  public static final String USER_POSITION = "Người dùng";
  public static final String MODERATOR_POSITION = "Quản trị viên";
  public static final String ADMIN_POSITION = "Quản trị viên";
  public static final String CTV_POSITION = "Cộng tác viên";
  public static final String UNKNOWN_POSITION = "Không xác định";

  public static final String PRODUCT_RESOURCE = "PRODUCT";
  public static final String SERVICE_RESOURCE = "SERVICE";
  public static final String BOOKING_RESOURCE = "BOOKING";
  public static final String ATTRIBUTE_RESOURCE = "ATTRIBUTE";
  public static final String NOTIFICATION_RESOURCE = "NOTIFICATION";
  public static final String INVOICE_RESOURCE = "INVOICE";
  public static final String BRANCH_RESOURCE = "BRANCH";

  private CommonConstant() {}
}
