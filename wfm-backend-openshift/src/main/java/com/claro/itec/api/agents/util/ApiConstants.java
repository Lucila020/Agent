package com.claro.itec.api.agents.util;


public class ApiConstants {

    //public static final String REGEXP_PASSWORD = "^(?=[^\\s]*[a-z])(?=[^\\s]*[A-Z])(?=[^\\s]*\\d)(?=[^\\s]*[#$^+=!*()@%&])[^\\s]{8,30}$";

    public static final String REGEXP_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9][a-zA-Z0-9._-]+\\.)+[a-zA-Z]{2,6}$";

    public static final String REGEXP_NAME = "[A-Z][a-zA-Z][^#&<>\"~;$^%{}?]{1,20}$";

    public static final String REGEXP_BS = "^((?![\\^!@#$*~ <>?]).)((?![\\^!@#$*~<>?]).){0,73}((?![\\^!@#$*~ <>?]).)" ;

}
