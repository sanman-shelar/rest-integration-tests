package com.app.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import java.io.IOException;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;
import com.app.rest.model.User;
import static org.junit.Assert.assertThat;

public class IntegrationTest 
{
  @Test
  public void givenUserExists_whenUserInfoIsRetrieved_then200IsReceived() throws ClientProtocolException, IOException {
      // Given
      final int user = RandomUtils.nextInt(1, 10);
      final HttpUriRequest request = new HttpGet("https://reqres.in/api/users/" + user);

      // When
      final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

      // Then
      assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
  }
  
  @Test
  public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived() throws ClientProtocolException, IOException {
      // Given
      final int user = RandomUtils.nextInt(50, 100);
      final HttpUriRequest request = new HttpGet("https://reqres.in/api/users/" + user);

      // When
      final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

      // Then
      assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
  }
  
  @Test
  public void givenUserExists_whenUserInfoIsRetrieved_thenRetrievedNameIsValid() throws ClientProtocolException, IOException {
      // Given      
      final HttpUriRequest request = new HttpGet("https://reqres.in/api/users/2");

      // When
      final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

      // Then
      final User user = Mapper.retrieveResourceFromResponse(httpResponse, User.class); 
      assertThat("Janet", Matchers.is(user.getFirst_name()));
      assertThat("Weaver", Matchers.is(user.getLast_name()));
  }
}
