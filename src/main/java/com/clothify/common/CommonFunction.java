package com.clothify.common;

import com.clothify.constant.CommonConstant;
import com.clothify.constant.MessageConstant;
import com.clothify.domain.enumuration.Role;
import com.clothify.exception.ForbiddenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

@Slf4j
public final class CommonFunction {
  private static final String ENCODING = "UTF-8";
  private static final String ERROR_FILE = "errors.yml";
  private static final String VALIDATION_FILE = "validations.yml";
  private static final String URL_REGEX =
      "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";

  private CommonFunction() {}

  /**
   * Parse object to json string
   *
   * @param ob {@link Object} to parse
   * @return String
   */
  public static String convertToJSONString(Object ob) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(ob);
    } catch (Exception e) {
      log.error(e.getMessage());
      return null;
    }
  }

  /**
   * Generate a queue name
   *
   * @param userId User's id
   * @return String
   */
  public static String generateQueueName(UUID userId) {
    return generateCode(64) + "|" + userId;
  }

  /**
   * Generate a random email
   *
   * @param length Email length
   * @return String email
   */
  public static String generateRandomEmail(int length) {
    return generateCode(length) + "+clothify_store@gmail.com";
  }

  /**
   * Generate a random invoice Code
   *
   * @param length invoice code length
   * @return String invoice code
   */
  public static String generateInvoiceCode(int length) {
    return "HD" + generateCodeDigit(length);
  }

  /**
   * Generate a random code
   *
   * @param length Code length
   * @return String code
   */
  public static String generateCode(int length) {
    List<CharacterRule> rules =
        Arrays.asList(
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1));

    PasswordGenerator generator = new PasswordGenerator();
    return generator.generatePassword(length, rules);
  }

  /**
   * Generate a random code
   *
   * @param length Code length
   * @return String code
   */
  public static String generateCodeDigit(int length) {
    List<CharacterRule> rules = List.of(new CharacterRule(EnglishCharacterData.Digit, 1));

    PasswordGenerator generator = new PasswordGenerator();
    return generator.generatePassword(length, rules);
  }

  /**
   * Get current date time
   *
   * @return Timestamp
   */
  public static Timestamp getCurrentDateTime() {
    Date date = new Date();
    return new Timestamp(date.getTime());
  }

  public static Timestamp getExpiredDateTime(int years) {
    Timestamp currentTimestamp = getCurrentDateTime();

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currentTimestamp);
    calendar.add(Calendar.YEAR, years);

    Timestamp expiredTimestamp = new Timestamp(calendar.getTimeInMillis());
    return expiredTimestamp;
  }


  /**
   * Convert date into pattern "yyyy-MM-dd"
   *
   * @param inputDate Input date
   * @return Timestamp
   * @throws ParseException {@link ParseException} Error during parser
   */
  public static Timestamp yyyyMMddFormat(String inputDate) throws ParseException {
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);
    return new Timestamp(date.getTime());
  }

  /**
   * Convert date into pattern
   *
   * @param timeZoneOffset Time zone offset
   * @param timeString Input date
   * @return Timestamp
   * @throws ParseException {@link ParseException} Error during parser
   */
  public static Timestamp convertStringToUTC(
      String timeString, Integer timeZoneOffset, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

    TemporalAccessor temporalAccessor =
        formatter.parseBest(timeString, LocalDateTime::from, LocalDate::from);

    LocalDateTime localDateTime;
    if (temporalAccessor instanceof LocalDate) {
      localDateTime = ((LocalDate) temporalAccessor).atStartOfDay();
    } else {
      localDateTime = (LocalDateTime) temporalAccessor;
    }

    OffsetDateTime offsetDateTime =
        localDateTime.atOffset(
            ZoneOffset.ofHours(Objects.isNull(timeZoneOffset) ? 7 : timeZoneOffset));

    OffsetDateTime utcDateTime = offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC);

    LocalDateTime utcLocalDateTime = utcDateTime.toLocalDateTime();

    return Timestamp.valueOf(utcLocalDateTime);
  }

  public static Timestamp convertStringToTimeStampAtEndDate(String date, Integer timeZone) {
    return convertStringToUTC(date.concat(" 23:59:59"), timeZone, CommonConstant.DATE_TIME_FORMAT);
  }

  /**
   * @param timeZoneOffset Time zone offset
   * @param timestamp Input date
   * @return Timestamp
   */
  public static Timestamp convertTimestampToUTC(Timestamp timestamp, Integer timeZoneOffset) {
    LocalDateTime localDateTime = timestamp.toLocalDateTime();

    OffsetDateTime offsetDateTime =
        localDateTime.atOffset(
            ZoneOffset.ofHours(Objects.isNull(timeZoneOffset) ? 7 : timeZoneOffset));

    OffsetDateTime utcDateTime = offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC);

    LocalDateTime utcLocalDateTime = utcDateTime.toLocalDateTime();

    return Timestamp.valueOf(utcLocalDateTime);
  }

  public static Timestamp convertToStartOfDayUTC(Timestamp date, Integer timeZoneOffset) {
    LocalDate localDate = date.toLocalDateTime().toLocalDate();

    LocalDateTime startOfDay = localDate.atStartOfDay();

    OffsetDateTime offsetDateTime =
        startOfDay.atOffset(
            ZoneOffset.ofHours(Objects.isNull(timeZoneOffset) ? 7 : timeZoneOffset));

    OffsetDateTime utcDateTime = offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC);

    return Timestamp.valueOf(utcDateTime.toLocalDateTime());
  }

  public static Timestamp convertToEndOfDayUTC(Timestamp date, Integer timeZoneOffset) {
    LocalDate localDate = date.toLocalDateTime().toLocalDate();

    LocalDateTime endOfDay = localDate.atTime(LocalTime.of(23, 59, 59));

    OffsetDateTime offsetDateTime =
        endOfDay.atOffset(ZoneOffset.ofHours(Objects.isNull(timeZoneOffset) ? 7 : timeZoneOffset));

    OffsetDateTime utcDateTime = offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC);

    return Timestamp.valueOf(utcDateTime.toLocalDateTime());
  }

  /**
   * Convert date into pattern "yyyy-MM-dd"
   *
   * @param inputDate Input date
   * @return Timestamp
   * @throws ParseException {@link ParseException} Error during parser
   */
  public static Timestamp yyyyMMddHHmmSSFormat(String inputDate) throws ParseException {
    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inputDate);
    return new Timestamp(date.getTime());
  }

  /**
   * Convert camel case to snake case
   *
   * @param input string type camel case
   * @return String type snake case
   */
  public static String convertToSnakeCase(String input) {
    return input.replaceAll("([^_A-Z])([A-Z])", "$1_$2").toLowerCase();
  }

  /**
   * Kiểm tra tính hợp lệ của URL.
   *
   * @param urlString URL cần kiểm tra.
   * @return true nếu URL hợp lệ, ngược lại trả về false.
   */
  public static boolean isValidUrl(String urlString) {
    try {
      new URL(urlString);
      return true;
    } catch (MalformedURLException e) {
      return false;
    }
  }

  public static Timestamp getMinTime(int year, int month, int day) throws ParseException {
    if (day == 0) {
      String time = year + "-" + convertDayAndMonthToString(month) + "-01 00:00:00";
      return CommonFunction.yyyyMMddHHmmSSFormat(time);
    }
    String time =
        year
            + "-"
            + convertDayAndMonthToString(month)
            + "-"
            + convertDayAndMonthToString(day)
            + " 00:00:00";
    return CommonFunction.yyyyMMddHHmmSSFormat(time);
  }

  public static Timestamp getMaxTime(int year, int month, int day) throws ParseException {
    if (day == 0) {
      String time =
          year
              + "-"
              + convertDayAndMonthToString(month)
              + "-"
              + convertDayAndMonthToString(getDay(year, month))
              + " 23:59:59";
      return CommonFunction.yyyyMMddHHmmSSFormat(time);
    }

    String time =
        year
            + "-"
            + convertDayAndMonthToString(month)
            + "-"
            + convertDayAndMonthToString(day)
            + " 23:59:59";
    return CommonFunction.yyyyMMddHHmmSSFormat(time);
  }

  public static String convertDayAndMonthToString(int value) {
    if (value < 10) {
      return "0" + value;
    }
    return "" + value;
  }

  public static int getDay(int year, int month) {
    if (month == 4 || month == 6 || month == 9 || month == 11) {
      return 31;
    }

    if (month == 2) {
      if (checkIsLeap(year)) {
        return 29;
      }
      return 28;
    }
    return 31;
  }

  public static boolean checkIsLeap(int year) {
    boolean isLeap = false;
    if (year % 4 == 0) {
      if (year % 100 == 0) {
        if (year % 400 == 0) isLeap = true;
      } else isLeap = true;
    }
    return isLeap;
  }

  public static String handleFieldName(String fieldName) {
    String index = fieldName.substring(fieldName.indexOf("[") + 1, fieldName.indexOf("]"));
    return fieldName.replaceAll(index, "");
  }

  public static String generateCode() {
    return RandomStringUtils.randomAlphanumeric(6);
  }

  public static Timestamp convertMillisecondToTimeStamp(String date) {
    return new Timestamp(Long.parseLong(date));
  }

  public static boolean isLessThanTwoDays(Timestamp date) {
    Instant currentTime = Instant.now();
    Instant startAt = date.toInstant();
    Duration difference = Duration.between(currentTime, startAt);
    return difference.toDays() < 2;
  }

  public static String handleContentSearch(String content) {
    return content.replaceAll(
        "([\\[\\]\\-\\\\\\~\\`\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\=\\{\\}\\|\\:\\;\\'\\<\\>\\,\\.\\?\\/])",
        "\\\\$1");
  }

  public static String getRole(Role role) {
    return role.toString().replace("ROLE_", "");
  }

  public static String getPermission(Role role) {
    return role.toString().replace("ROLE_", "");
  }

  public static String getCurrentInstantSecond() {
    return String.valueOf(Instant.now().toEpochMilli());
  }

  public static Timestamp mergeTimestamps(Timestamp dateTimestamp, Timestamp timeTimestamp) {
    try {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(dateTimestamp.getTime());

      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH);
      int day = calendar.get(Calendar.DAY_OF_MONTH);

      Calendar timeCalendar = Calendar.getInstance();
      timeCalendar.setTimeInMillis(timeTimestamp.getTime());

      int hour = timeCalendar.get(Calendar.HOUR_OF_DAY);
      int minute = timeCalendar.get(Calendar.MINUTE);
      int second = timeCalendar.get(Calendar.SECOND);
      int millis = timeCalendar.get(Calendar.MILLISECOND);

      calendar.set(year, month, day, hour, minute, second);
      calendar.set(Calendar.MILLISECOND, millis);

      return new Timestamp(calendar.getTimeInMillis());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Double haversineDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
    if (Objects.isNull(lat1)
        || Objects.isNull(lon1)
        || Objects.isNull(lat2)
        || Objects.isNull(lon2)) {
      return null;
    }
    double earthRadius = 6371000;
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);
    double a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2)
            + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return earthRadius * c;
  }

  public static Timestamp parseStartDate(String startDate, Integer timeZone) throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATE_FORMAT);
    Calendar cal = Calendar.getInstance();

    if (Strings.isNullOrEmpty(startDate)) {
      cal.setTime(new Date());
    } else {
      Date parsedDate = dateFormat.parse(startDate);
      cal.setTime(parsedDate);
    }
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return convertTimestampToUTC(new Timestamp(cal.getTimeInMillis()), timeZone);
  }

  public static Timestamp parseEndDate(String endDate, String startDate, Integer timeZone)
      throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.DATE_FORMAT);
    Calendar cal = Calendar.getInstance();

    if (Strings.isNullOrEmpty(endDate)) {
      if (Strings.isNullOrEmpty(startDate)) {
        cal.setTime(new Date());
      } else {
        Date parsedDate = dateFormat.parse(startDate);
        cal.setTime(parsedDate);
      }
    } else {
      Date parsedDate = dateFormat.parse(endDate);
      cal.setTime(parsedDate);
    }

    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return convertTimestampToUTC(new Timestamp(cal.getTimeInMillis()), timeZone);
  }

  public static boolean compareTimeLargerThan(Timestamp a, Timestamp b) {
    return a.compareTo(b) >= 0;
  }

  public static Timestamp getTimeFromDateString(String date, String format) {
    if (Strings.isNullOrEmpty(date)) {
      return CommonFunction.getCurrentDateTime();
    }
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat(format);
      Date parsedDate = dateFormat.parse(date);
      return new Timestamp(parsedDate.getTime());
    } catch (ParseException e) {
      throw new ForbiddenException(MessageConstant.DATE_FORMAT_IS_INVALID);
    }
  }

  public static String generatePassword() {
    PasswordGenerator gen = new PasswordGenerator();
    List<CharacterRule> rules = new ArrayList<>();

    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
    lowerCaseRule.setNumberOfCharacters(2);

    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
    upperCaseRule.setNumberOfCharacters(2);

    CharacterData digitChars = EnglishCharacterData.Digit;
    CharacterRule digitRule = new CharacterRule(digitChars);
    digitRule.setNumberOfCharacters(2);

    rules.add(lowerCaseRule);
    rules.add(upperCaseRule);
    rules.add(digitRule);

    return gen.generatePassword(8, rules);
  }

  public static String removeExtraSpaces(String input) {
    if (input == null || input.isEmpty()) {
      return input;
    }

    input = input.trim();

    input = input.replaceAll("\\s+", " ");

    return input;
  }

  public static List<Timestamp> getDatesWithNoonTime(String start, String end) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate startDate = LocalDate.parse(start, formatter);
    LocalDate endDate = LocalDate.parse(end, formatter);

    List<Timestamp> datesWithNoonTime = new ArrayList<>();
    LocalTime noonTime = LocalTime.of(12, 0);

    for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
      LocalDateTime dateTime = LocalDateTime.of(date, noonTime);
      datesWithNoonTime.add(Timestamp.valueOf(dateTime));
    }

    return datesWithNoonTime;
  }

  public static List<Timestamp[]> getTimeIntervals(String dateStr, Integer timeZone) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.parse(dateStr, dateFormatter);

    List<Timestamp[]> intervals = new ArrayList<>();
    ZoneOffset zoneOffset = ZoneOffset.ofHours(Objects.isNull(timeZone) ? 7 : timeZone);

    for (int hour = 0; hour < 24; hour++) {
      LocalDateTime localStart = date.atTime(hour, 0);
      LocalDateTime localEnd = date.atTime(hour, 59, 59, 999000000);

      OffsetDateTime start = localStart.atOffset(zoneOffset).withOffsetSameInstant(ZoneOffset.UTC);
      OffsetDateTime end = localEnd.atOffset(zoneOffset).withOffsetSameInstant(ZoneOffset.UTC);

      intervals.add(
          new Timestamp[] {Timestamp.from(start.toInstant()), Timestamp.from(end.toInstant())});
    }

    return intervals;
  }

  public static String[] splitFullName(String fullName) {
    if (fullName == null || fullName.trim().isEmpty()) {
      return new String[] {null, null};
    }

    String[] parts = fullName.trim().split("\\s+");

    if (parts.length < 2) {
      return new String[] {parts[0], null};
    }

    String firstName = parts[0];
    String lastName = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));

    return new String[] {firstName, lastName};
  }

  public static boolean areArraysEqual(List<String> arr1, List<String> arr2) {
    if (arr1.size() != arr2.size()) {
      return false;
    }

    List<String> sortedArr1 = new ArrayList<>(arr1);
    List<String> sortedArr2 = new ArrayList<>(arr2);

    Collections.sort(sortedArr1);
    Collections.sort(sortedArr2);

    return sortedArr1.equals(sortedArr2);
  }

  public static String encodeUrl(String url) {
    try {
      String encodedUrl = URLEncoder.encode(url, ENCODING);

      encodedUrl =
          encodedUrl
              .replace("+", "%2B")
              .replace("-", "%2D")
              .replace("*", "%2A")
              .replace("_", "%5F")
              .replace("~", "%7E");
      return encodedUrl;
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("Encoding not supported", e);
    }
  }

  public static String splitByKeyword(String s, String keyword) {
    if (s == null || s.isEmpty()) {
      return s;
    }
    return s.contains(keyword) ? s.split(keyword)[1] : s;
  }
}
