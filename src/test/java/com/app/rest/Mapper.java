package com.app.rest;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {

  public static <T> T retrieveResourceFromResponse(final HttpResponse response,
      final Class<T> clazz) throws IOException {
    final String jsonFromResponse = EntityUtils.toString(response.getEntity());
    final ObjectMapper mapper =
        new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
    return mapper.readValue(jsonFromResponse, clazz);
  }
}
