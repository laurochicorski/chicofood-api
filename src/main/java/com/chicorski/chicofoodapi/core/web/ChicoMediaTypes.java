package com.chicorski.chicofoodapi.core.web;

import org.springframework.http.MediaType;

public class ChicoMediaTypes {

    public static final String V1_APPLICATION_JSON_VALUE = "application/vdn.chicofood.v1+json";

    public static final MediaType V1_APPLICATION_JSON = MediaType.valueOf(V1_APPLICATION_JSON_VALUE);
}
