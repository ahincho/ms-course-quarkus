package com.nova.generics.ms.course.quarkus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.edu.nova.java.libs.notifications.application.facade.NotificationFacade;
import pe.edu.nova.java.libs.notifications.domain.model.EmailNotification;
import pe.edu.nova.java.libs.notifications.domain.result.NotificationResult;
import pe.edu.nova.java.libs.notifications.domain.vo.EmailAddress;
import pe.edu.nova.java.libs.notifications.domain.vo.MessageBody;
import pe.edu.nova.java.libs.notifications.domain.vo.Subject;

import java.util.Map;

/**
 * Minimal REST surface for the {@code ms-course-quarkus} instance.
 *
 * <p>This single endpoint demonstrates the full flow:
 * Quarkus REST controller -> NotificationFacade (provided by the
 * {@code nova-notifications-quarkus-extension} adapter) -> simulated
 * email send -> NotificationResult JSON.
 *
 * <p>It is the Quarkus counterpart of the
 * {@code com.nova.generics.ms-course} Spring Boot instance
 * (see {@code instances/ms-course/pom.xml}).
 */
@ApplicationScoped
@Path("/api/notifications/email")
@Produces(MediaType.APPLICATION_JSON)
public class NotificationsResource {

    private final NotificationFacade facade;

    public NotificationsResource(NotificationFacade facade) {
        this.facade = facade;
    }

    @GET
    @Path("/welcome")
    public Map<String, Object> welcome() {
        NotificationResult result = facade.send(EmailNotification.builder()
                .from(new EmailAddress("no-reply@example.com"))
                .to(new EmailAddress("customer@example.com"))
                .subject(new Subject("Welcome"))
                .body(new MessageBody("Thanks for signing up to Nova."))
                .build());
        return Map.of(
                "sent", result.isSent(),
                "providerMessageId", result.providerMessageId().orElse(""),
                "channel", result.channel().name().toLowerCase());
    }
}