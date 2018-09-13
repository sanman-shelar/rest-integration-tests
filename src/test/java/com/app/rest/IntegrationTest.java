package com.app.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.io.IOException;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;
import com.app.rest.model.User;
import com.app.rest.model.UserPostResponse;

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
  public void givenUserDoesNotExist_whenUserInfoIsRetrieved_then404IsReceived() throws ClientProtocolException, IOException {
      // Given
      final int user = RandomUtils.nextInt(50, 100);
      final HttpUriRequest request = new HttpGet("https://reqres.in/api/users/" + user);

      // When
      final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

      // Then
      assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
  }
  
  @Test
  public void
  givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson() throws ClientProtocolException, IOException {
    
     // Given
     String jsonMimeType = "application/json";
     HttpUriRequest request = new HttpGet( "https://reqres.in/api/users/2" );
   
     // When
     HttpResponse response = HttpClientBuilder.create().build().execute(request);
   
     // Then
     String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
     assertEquals( jsonMimeType, mimeType );
  }
  
  @Test
  public void givenUserExists_whenUserInfoIsRetrieved_thenRetrievedNameIsValid() throws ClientProtocolException, IOException {
      // Given      
      final HttpUriRequest request = new HttpGet("https://reqres.in/api/users/2");

      // When
      final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

      // Then
      final User user = Mapper.retrieveResourceFromRootResponse(httpResponse, User.class); 
      assertThat("Janet", Matchers.is(user.getFirst_name()));
      assertThat("Weaver", Matchers.is(user.getLast_name()));
  }
  
  @Test
  public void givenUserDoesNotExist_whenUserInfoIsSubmitted_then201IsReceived() throws ClientProtocolException, IOException {
      // Given      
      final HttpPost request = new HttpPost("https://reqres.in/api/users");
      final String json = "{\"name\":\"John\",\"job\":\"Tester\"}";
      final StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
      request.setEntity(entity);
      request.setHeader("Accept", "application/json");
      request.setHeader("Content-type", "application/json");

      // When
      final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

      // Then
      assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_CREATED));
  }
  
  @Test
  public void givenUserDoesNotExist_whenUserInfoIsSubmitted_thenuserIsCreated() throws ClientProtocolException, IOException {
      // Given      
      final HttpPost request = new HttpPost("https://reqres.in/api/users");
      final String json = "{\"name\":\"John\",\"job\":\"Tester\"}";
      final StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
      request.setEntity(entity);
      request.setHeader("Accept", "application/json");
      request.setHeader("Content-type", "application/json");

      // When
      final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

      // Then
      final UserPostResponse user = Mapper.retrieveResourceFromResponse(httpResponse, UserPostResponse.class); 
      assertThat("John", Matchers.is(user.getName()));
      assertThat("Tester", Matchers.is(user.getJob()));
  }
  
  @Test
  public void givenUserExists_whenDeleteRequestIsSubmitted_then204IsReceived() throws ClientProtocolException, IOException {
      // Given      
      final HttpDelete request = new HttpDelete("https://reqres.in/api/users/2");

      // When
      final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

      // Then
      assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
  }  
}
