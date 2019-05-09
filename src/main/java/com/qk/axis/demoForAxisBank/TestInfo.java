package com.qk.axis.demoForAxisBank;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestInfo {

  String[] TestCaseId();

  String Description();

  String[] Tags();
}
