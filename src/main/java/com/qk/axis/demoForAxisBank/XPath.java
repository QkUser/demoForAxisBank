/*
 * bcwti
 *
 * Copyright (c) 2010 Parametric Technology Corporation (PTC). All Rights Reserved.
 *
 * This software is the confidential and proprietary information of PTC and is subject to the terms
 * of a software license agreement. You shall not disclose such confidential information and shall
 * use it only in accordance with the terms of the license agreement.
 *
 * ecwti
 */

package com.qk.axis.demoForAxisBank;

public class XPath {

  /*
   * The type of surrounding character to be added to return string
   */
  public enum Quote {
    SINGLE, DOUBLE, NONE;
  }

  /**
   * This method is used to make the xpathText safe to build xpath locator
   *
   * @param xpathText
   * @param surroundingChar To provide flexibility and compatibility to handle special characters in
   *        addition to single quotes and double quotes.
   * @return - If xpathText of concat(...) sequence, return xpathText - If xpathText not of
   *         concat(...) sequence, add characters defined by surroundingCharType to the beginning
   *         and end of xpathText, return xpathText
   */
  public static String makeXpathSafe(String xpathText, Quote surroundingChar) {

    // Null pointer case
    if (null == xpathText) {
      return "";
    }

    // Do nothing if xpathText has already been converted to concat(...) sequence
    boolean alreadyConverted = xpathText.startsWith("concat(") && xpathText.endsWith(")");
    if (alreadyConverted) {
      return xpathText;
    }

    // Check if the xpathText safe or not, right now only check single and double quotes
    boolean isXpathSafe = !xpathText.contains("'") && !xpathText.contains("\"");

    if (isXpathSafe) {
      // Regular string, not of concat(...) sequence, add surrounding characters defined by
      // surroundingCharType
      switch (surroundingChar) {
        case SINGLE:
          return "'" + xpathText + "'";
        case DOUBLE:
          return "\"" + xpathText + "\"";
        case NONE:
          return xpathText;
        default:
          break;
      }
    }

    // Special strings never occur in Windchill UI
    final String singleQuote = "!SINGLEQUOTE!";
    final String doubleQuote = "!DOUBLEQUOTE!";
    final String comma = "!COMMA!";

    xpathText = xpathText.replaceAll("'", singleQuote);
    xpathText = xpathText.replaceAll("\"", doubleQuote);
    xpathText = xpathText.replaceAll(singleQuote, comma + "\"'\"" + comma);
    xpathText = xpathText.replaceAll(doubleQuote, comma + "'\"'" + comma);
    xpathText = xpathText.replaceAll(comma + comma, comma);

    // Clear starting & ending comma
    if (xpathText.startsWith(comma)) {
      xpathText = xpathText.replaceFirst(comma, "");
    }
    if (xpathText.endsWith(comma)) {
      xpathText = xpathText.substring(0, xpathText.length() - comma.length());
    }

    // Surrounding plain text by single quotes
    if (!xpathText.startsWith("'") && !xpathText.startsWith("\"")) {
      xpathText = "'" + xpathText;
    }
    if (!xpathText.endsWith("'") && !xpathText.endsWith("\"")) {
      xpathText = xpathText + "'";
    }

    int fromIndex = 1;
    int lastIndex = 0;
    while (fromIndex > 0) {
      fromIndex = xpathText.indexOf(comma, lastIndex);
      if (fromIndex > 0) {
        char curChar = xpathText.charAt(fromIndex - 1);
        if (curChar != '\'' && curChar != '"') {
          xpathText = xpathText.substring(0, fromIndex) + "'" + xpathText.substring(fromIndex);
        }
        fromIndex = xpathText.indexOf(comma, lastIndex);

        char charAfterComa = xpathText.charAt(fromIndex + comma.length());
        if (charAfterComa != '\'' && charAfterComa != '"') {
          xpathText = xpathText.substring(0, fromIndex + comma.length()) + "'"
              + xpathText.substring(fromIndex + comma.length());
        }
        lastIndex = fromIndex + comma.length() + 1;
      }
    }

    // Special comma to real comma
    xpathText = xpathText.replaceAll(comma, ",");

    // To concat(...) sequence
    xpathText = "concat(" + xpathText + ")";

    return xpathText;
  }

  /**
   * This method is used to make the xpathText safe to build xpathText locator As single quotes are
   * widely used in xpath locator to surround target text, this method adds single quotes to
   * xpathText by default
   *
   * @param xpathText
   * @return - If xpathText of concat(...) sequence, return xpathText - If xpathText not of
   *         concat(...) sequence, add single quotes to the beginning and end of xpathText, return
   *         xpathText
   */
  public static String makeXpathSafe(String xpathText) {
    return makeXpathSafe(xpathText, Quote.SINGLE);
  }

}
