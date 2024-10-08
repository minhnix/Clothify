package com.clothify.constant;

public final class MessageConstant {

  public static final String USER_NOT_FOUND = "user_not_found";

  // Page not found
  public static final String PAGE_NOT_FOUND = "page_not_found";
  // Forbidden error
  public static final String FORBIDDEN_ERROR = "forbidden_error";
  // Unauthorized
  public static final String UNAUTHORIZED = "unauthorized";

  // Internal server error
  public static final String INTERNAL_SERVER_ERROR = "internal_server_error";

  // User
  public static final String CHANGE_CREDENTIAL_FAIL = "change_password_failed";
  public static final String RESET_CREDENTIAL_FAIL = "reset_password_failed";
  public static final String INCORRECT_EMAIL_OR_PASSWORD = "incorrect_email_or_password";
  public static final String ACCOUNT_NOT_EXISTS = "account_not_exists";
  public static final String ACCOUNT_BLOCKED = "account_blocked";
  public static final String ACCOUNT_NOT_ACTIVATED = "account_not_activated";
  public static final String USER_IS_ENABLED = "user_is_enabled";
  public static final String INCORRECT_CONFIRMATION_TOKEN = "incorrect_confirmation_token";
  public static final String CHANGE_CREDENTIAL_NOT_CORRECT = "old_password_not_correct";
  public static final String PASSWORD_FIELDS_MUST_MATCH = "password_fields_must_match";
  public static final String NEW_PASSWORD_NOT_SAME_OLD_PASSWORD =
      "new_password_not_same_old_password";
  public static final String TOKEN_REMOVE_ACCOUNT_NOT_CORRECT = "token_remove_account_not_correct";
  public static final String TOKEN_REMOVE_ACCOUNT_EXPIRED = "token_remove_account_expired";
  // Upload file
  public static final String FILE_NOT_FORMAT = "file_not_format";
  public static final String MAXIMUM_UPLOAD_SIZE_EXCEEDED = "maximum_upload_size_exceeded";
  public static final String FILE_IS_DELETED_FAILED = "file_is_deleted_failed";

  public static final String FILE_URL_IS_ERROR = "file_url_is_error";

  public static final String FILE_NOT_FOUND = "file_not_found";

  // Authentication
  public static final String INVALID_TOKEN = "invalid_token";
  public static final String EXPIRED_TOKEN = "expired_token";
  public static final String REVOKED_TOKEN = "revoked_token";
  public static final String INVALID_REFRESH_TOKEN = "invalid_refresh_token";
  public static final String EXPIRED_REFRESH_TOKEN = "expired_refresh_token";

  public static final String CONVERSATION_NOT_FOUND = "conversation_not_found";

  public static final String MESSAGE_NOT_FOUND = "message_not_found";

  public static final String INDEX_IS_NOT_VALID = "index_is_not_valid";

  public static final String MESSAGE_NOT_IN_CONVERSATION = "message_not_in_conversation";
  public static final String CANNOT_EDIT_MESSAGE = "cannot_edit_message";
  public static final String CANNOT_DELETE_MESSAGE = "cannot_delete_message";
  public static final String CANNOT_DELETE_FILE = "cannot_delete_file";

  public static final String FILE_AND_CONTENT_MUST_NOT_BE_NULL =
      "file_and_content_must_not_be_null";

  public static final String NOTIFICATION_NOT_FOUND = "notification_not_found";

  public static final String DYNAMIC_LINK_PROPERTY_NOT_FOUND = "dynamic_link_property_not_found";
  // Customer

  public static final String INVOICE_NOT_FOUND = "invoice_not_found";
  public static final String ADDRESS_NOT_FOUND = "address_not_found";
  public static final String BRANCH_NOT_FOUND = "branch_not_found";
  public static final String BRANCH_EXIST = "branch_exist";
  public static final String PRODUCT_NOT_FOUND = "product_not_found";
  public static final String EMAIL_ALREADY_EXISTS = "email_already_exists";
  public static final String START_DATE_IS_GREATER_THAN_END_DATE =
      "start_date_is_greater_than_end_date";
  public static final String PRODUCT_DELETED = "product_deleted";
  public static final String PRODUCT_AVAILABLE = "product_available";
  public static final String SERVICE_NOT_FOUND = "service_not_found";
  public static final String SERVICE_DELETED = "service_deleted";
  public static final String SERVICE_AVAILABLE = "service_available";
  public static final String ATTRIBUTE_NOT_FOUND = "attribute_not_found";

  public static final String ATTRIBUTE_IS_BUSY = "attribute_is_busy";
  public static final String BRANCH_CATEGORY_NOT_FOUND = "branch_category_not_found";
  public static final String BRANCH_CATEGORY_EXISTED = "branch_category_existed";

  // Booking
  public static final String BOOKING_NOT_FOUND = "booking_not_found";

  public static final String BOOKING_STATUS_MUST_BE_COMPLETED = "booking_status_must_be_completed";

  public static final String ONLY_ONE_REVIEW_FOR_EACH_BOOKING = "only_one_review_for_each_booking";

  public static final String BOOKING_STATUS_MUST_BE_CONFIRMED = "booking_status_must_be_confirmed";

  public static final String THE_BOOKING_DUPLICATES_AN_ORDER_TIME =
      "the_booking_duplicates_an_order_time";
  // Review
  public static final String REVIEW_NOT_FOUND = "review_not_found";

  // Payment History
  public static final String PAYMENT_HISTORY_NOT_FOUND = "payment_history_not_found";

  public static final String PAYMENT_HISTORY_IS_CURRENTLY_RECEIVED =
      "payment_history_is_currently_received";

  public static final String PAYMENT_HISTORY_IS_CURRENTLY_NOT_RECEIVED =
      "payment_history_is_currently_not_received";
  public static final String ACCOUNT_BLOCKED_BY_ADMIN = "account_block_by_admin";
  public static final String REPORT_NOT_FOUND = "report_not_found";
  public static final String DURING_IS_GREATER_THAN_WORKING_TIME =
      "during_is_greater_than_working_time";

  // Check in

  public static final String CHECK_IN_NOT_FOUND = "check_in_not_found";
  public static final String BOOKMARK_NOT_FOUND = "bookmark_not_found";
  public static final String DURATION_NOT_FOUND = "duration_not_found";
  public static final String WORKING_TIME_NOT_FOUND = "working_time_not_found";
  public static final String SERVICE_CATEGORY_NOT_FOUND = "service_category_not_found";
  public static final String PRODUCT_CATEGORY_NOT_FOUND = "product_category_not_found";
  public static final String INVALID_URL = "invalid_url";
  // Session manage
  public static final String SESSION_NOT_FOUND = "session_not_found";
  public static final String THE_CALL_ENDED = "the_call_ended";
  public static final String CATEGORY_NOT_FOUND = "category_not_found";

  // Admin bank info
  public static final String ADMIN_BANK_INFO_NOT_FOUND = "admin_bank_info_not_found";
  public static final String MAXIMUM_IMAGE_EXCEEDED = "images_description_max_size_is_10";
  public static final String INVALID_SERVICE_WORKING_TIME = "invalid_service_working_time";
  public static final String BRANCH_WORKING_TIME_NOT_FOUND = "branch_working_time_not_found";
  public static final String SERVICE_WORKING_TIME_NOT_FOUND = "service_working_time_not_found";
  public static final String ATTRIBUTE_TIME_INVALID = "attribute_time_invalid";
  public static final String INVALID_TIME_FORMAT = "invalid_time_format";
  public static final String BRANCH_ADDRESS_MISSING = "branch_address_missing";

  // Bank
  public static final String BANK_NOT_FOUND = "bank_not_found";

  // Payment duration
  public static final String PAYMENT_DURATION_NOT_FOUND = "payment_duration_not_found";
  public static final String DISCOUNT_EXPIRED_INVALID = "discount_expired_invalid";
  public static final String ACCOUNT_DELETED = "account_deleted";
  public static final String OBJECT_NOT_FOUND = "object_not_found";
  public static final String REPORT_LIMIT = "report_limit";
  public static final String ATTRIBUTE_REQUIRED = "attribute_required";
  public static final String CONTACT_NOT_FOUND = "contact_not_found";
  public static final String CONTACT_CATEGORY_NOT_FOUND = "contact_category_not_found";
  public static final String ATTRIBUTE_USED_IN_BOOKING = "attribute_used_in_booking";
  public static final String END_CALL_FAILED = "end_call_failed";
  public static final String THE_CALL_IS_FULL = "the_call_is_full";
  public static final String VOUCHER_NOT_FOUND = "voucher_not_found";
  public static final String DATE_FORMAT_IS_INVALID = "date_format_is_invalid";
  public static final String VOUCHER_IS_OUT_OF_STOCK = "voucher_is_out_of_stock";
  public static final String VOUCHER_IS_NOT_APPLICABLE = "voucher_is_not_applicable";
  public static final String VOUCHER_IS_NOT_AVAILABLE_IN_THIS_TIME =
      "voucher_is_not_available_in_this_time";
  public static final String VOUCHER_IS_NOT_AVAILABLE = "voucher_is_not_available";
  public static final String INVALID_TYPE_SERVICE = "invalid_type_service";
  // Bookmark Message
  public static final String MESSAGE_HAS_BOOKMARK = "message_has_bookmark";

  public static final String BOOKMARK_MESSAGE_NOT_FOUND = "bookmark_message_not_found";
  public static final String NOT_ENOUGH_POINT_TO_USE_VOUCHER = "not_enough_point_to_use_voucher";
  public static final String VOUCHER_NOT_FOUND_IN_BOOKING = "voucher_not_found_in_booking";
  public static final String ATTRIBUTE_LOCKED = "attribute_locked";
  public static final String ATTRIBUTE_UNLOCKED = "attribute_unlocked";
  public static final String BOOKING_HAS_BEEN_CONFIRMED = "booking_has_been_confirmed";
  public static final String BOOKING_CODE_INVALID = "booking_code_invalid";
  public static final String BOOKING_HAS_BEEN_BOOKED_BY_OTHER = "booking_has_been_booked_by_other";
  public static final String PRODUCT_MUST_NOT_CANCEL = "product_must_not_cancel";
  public static final String STATUS_NOT_FOUND = "status_not_found";
  public static final String BRANCH_EXIST_SERVICE_PRODUCT = "branch_exist_service_product";
  public static final String CONTACT_ACCOUNT_NOT_FOUND = "contact_account_not_found";
  public static final String FUNCTION_NOT_FOUND = "function_not_found";
  public static final String PERMISSION_NOT_FOUND = "permission_not_found";
  public static final String PRIVILEGE_NOT_FOUND = "privilege_not_found";
  public static final String INVOICE_ALREADY_REQUEST_PAID = "invoice_already_request_paid";
  public static final String VOUCHER_ALREADY_ADDED = "voucher_already_added";
  public static final String INVOICE_STATUS_MUST_BE_REQUEST_CANCEL =
      "invoice_status_must_be_request_cancel";

  public static final String MEMBER_NOT_FOUND = "member_not_found";

  public static final String DATE_START_AFTER_END = "date_start_after_end";
  public static final String ACTIVATE_STATUS_MUST_NOT_EQUAL_OLD_STATUS =
      "activate_status_must_not_equal_old_status";
  public static final String FUNCTION_ALREADY_EXIST = "function_already_exist";
  public static final String PERMISSION_ALREADY_EXIST = "permission_already_exist";
  public static final String PRIVILEGE_ALREADY_EXIST = "privilege_already_exist";
  public static final String ACTIVATE_STATUS_MUST_NOT_BE_ACTIVATE =
      "activate_status_must_not_be_activate";
  public static final String PRODUCT_SIZE_NOT_FOUND = "product_size_not_found";
  public static final String PRODUCT_TOPPING_CATEGORY_NOT_FOUND =
      "product_topping_category_not_found";
  public static final String PRODUCT_TOPPING_NOT_FOUND = "product_topping_not_found";
  public static final String RESOURCE_NOT_FOUND = "resource_not_found";
  public static final String MEMBER_PERMISSION_NOT_FOUND = "member_permission_not_found";

  public static final String GROUP_PERMISSION_NOT_FOUND = "group_permission_not_found";

  public static final String MEMBER_IN_GROUP_PERMISSION_NOT_FOUND =
      "member_in_group_permission_not_found";
  public static final String ACCOUNT_HAS_ANOTHER_ROLE = "account_has_another_role";
  public static final String MEMBER_EXISTED = "member_existed";
  public static final String PRINTER_NOT_FOUND = "printer_not_found";
  public static final String PRINTER_ALREADY_EXIST = "printer_already_exist";
  public static final String PRINTER_CATEGORY_NOT_FOUND = "printer_category_not_found";
  public static final String ATTRIBUTE_IS_DELETED = "attribute_is_deleted";
  public static final String ATTRIBUTE_IS_NOT_AVAILABLE = "attribute_is_not_available";
  public static final String SERVICE_IS_NOT_AVAILABLE = "service_is_not_available";
  public static final String BRANCH_IS_NOT_AVAILABLE = "branch_is_not_available";

  public static final String PRODUCT_OPTION_NOT_FOUND = "product_option_not_found";
  public static final String PRODUCT_OPTION_ELEMENT_NOT_FOUND = "product_option_element_not_found";
  public static final String CANCEL_QUANTITY_MUST_LESS_THAN_OR_EQUAL_QUANTITY =
      "cancel_quantity_must_less_than_or_equal_quantity";
  public static final String BOOKING_ALREADY_HAVE_ATTRIBUTE = "booking_already_have_attribute";
  public static final String ATTRIBUTE_MISSING_IN_BOOKING = "attribute_missing_in_booking";
  public static final String BOOKING_LEFT_IS_ZERO = "booking_left_is_zero";
  public static final String BOOKING_HAS_BEEN_LEFT = "booking_has_been_left";
  public static final String SERVICE_IS_BUSY_IN_THIS_TIME = "service_is_busy_in_this_time";
  public static final String ATTRIBUTE_NOT_IN_INVOICE = "attribute_not_in_invoice";
  public static final String INVOICE_MUST_NOT_MERGE_OR_SPLIT_BY_STATUS_INVOICE =
      "invoice_must_not_merge_or_split_by_status_invoice";
  public static final String PRICE_DISCOUNT_MUST_LESS_THAN_TOTAL_PRICE =
      "price_discount_must_less_than_total_price";
  public static final String PRODUCT_OUT_OF_STOCK = "product_out_of_stock";
  public static final String ATTRIBUTE_USED_IN_INVOICE = "attribute_used_in_invoice";
  public static final String STATUS_MUST_NOT_EQUAL_OLD_STATUS = "status_must_not_equal_old_status";
  public static final String LANGUAGE_NOT_FOUND = "language_not_found";
  public static final String INVALID_ROLE = "invalid_role";
  public static final String TIME_BOOKING_NOT_VALID = "time_booking_not_valid";
  public static final String BLOCK_CONVERSATION_NOT_FOUND = "block_conversation_not_found";
    public static final String ACTION_NOT_FOUND = "action_not_found";
    public static final String INVALID_USER_INPUT = "invalid_user_input";
    public static final String BLOCK_MESSAGE_MUST_NOT_BE_EMPTY = "block_message_must_not_be_empty";
}
