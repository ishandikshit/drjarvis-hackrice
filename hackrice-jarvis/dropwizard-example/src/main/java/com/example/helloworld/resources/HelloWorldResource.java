package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.api.Saying;
import com.example.helloworld.core.Template;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.jersey.params.DateTimeParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.beans.AppletInitializer;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import ai.api.*;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import edu.asu.nlp.MainPostProcessor;
import edu.asu.nlp.SingletonClass;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    private final Template template;
    private final AtomicLong counter;
    String session; 
    SingletonClass sg=SingletonClass.getInstance();

    public HelloWorldResource(Template template) {
        this.template = template;
        this.counter = new AtomicLong();
        sg.setSession("");
    }

    @GET
    @Timed(name = "get-requests")
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        return new Saying(counter.incrementAndGet(), template.render(name));
    }

    @POST
    public Saying receiveHello(@Valid Saying saying) throws AIServiceException {
    	
    	
    	String text=saying.getContent();
    	sg.setSpeechInput(text);
    	
    	System.out.println(text);
    	AIConfiguration aic = new AIConfiguration("014cb2cd82bf4bbf9ed3aee464a2e7da");
    	AIDataService dataService = new AIDataService(aic);
    	AIRequest air = new AIRequest();
    	air.setQuery(text);
    	air.setSessionId(sg.getSession());
    	AIResponse response = dataService.request(air);
    	sg.setSpeechOutput(response.getResult().getFulfillment().getSpeech().toString());
    	sg.setAIResponse(response);
    	sg.setSession(response.getSessionId());
    	new MainPostProcessor();
    	return new Saying(counter.incrementAndGet(), sg.getSpeechOutput());
    }

    @GET
    @Path("/date")
    @Produces(MediaType.TEXT_PLAIN)
    public String receiveDate(@QueryParam("date") Optional<DateTimeParam> dateTimeParam) {
        if (dateTimeParam.isPresent()) {
            final DateTimeParam actualDateTimeParam = dateTimeParam.get();
            LOGGER.info("Received a date: {}", actualDateTimeParam);
            return actualDateTimeParam.get().toString();
        } else {
            LOGGER.warn("No received date");
            return null;
        }
    }
}
