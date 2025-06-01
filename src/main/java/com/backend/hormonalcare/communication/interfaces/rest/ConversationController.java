package com.backend.hormonalcare.communication.interfaces.rest;

import com.backend.hormonalcare.communication.domain.model.queries.GetConversationByIdQuery;
import com.backend.hormonalcare.communication.domain.model.queries.GetConversationsByUserIdQuery;
import com.backend.hormonalcare.communication.domain.services.CommunicationCommandService;
import com.backend.hormonalcare.communication.domain.services.CommunicationQueryService;
import com.backend.hormonalcare.communication.interfaces.rest.resources.ConversationResource;
import com.backend.hormonalcare.communication.interfaces.rest.resources.CreateConversationResource;
import com.backend.hormonalcare.communication.interfaces.rest.transform.ConversationResourceFromEntityAssembler;
import com.backend.hormonalcare.communication.interfaces.rest.transform.CreateConversationCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/conversations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConversationController {

    private final CommunicationCommandService communicationCommandService;
    private final CommunicationQueryService communicationQueryService;

    public ConversationController(CommunicationCommandService communicationCommandService, CommunicationQueryService communicationQueryService) {
        this.communicationCommandService = communicationCommandService;
        this.communicationQueryService = communicationQueryService;
    }

    @PostMapping
    public ResponseEntity<ConversationResource> createConversation(@RequestBody CreateConversationResource resource) {
        var command = CreateConversationCommandFromResourceAssembler.toCommandFromResource(resource);
        var conversationOptional = communicationCommandService.handle(command);

        if (conversationOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var conversation = conversationOptional.get();
        var conversationResource = ConversationResourceFromEntityAssembler.toResourceFromEntity(conversation);

        return new ResponseEntity<>(conversationResource,HttpStatus.CREATED);
    }

    @GetMapping("/{conversationId}")
    public ResponseEntity<ConversationResource> getConversationById(@PathVariable Long conversationId) {
        var query = new GetConversationByIdQuery(conversationId);
        var conversationOptional = communicationQueryService.handle(query);

        if (conversationOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var conversation = conversationOptional.get();
        var conversationResource = ConversationResourceFromEntityAssembler.toResourceFromEntity(conversation);

        return ResponseEntity.ok(conversationResource);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ConversationResource>> getConversationsByUserId(@PathVariable Long userId) {
        var query = new GetConversationsByUserIdQuery(userId);
        var conversations = communicationQueryService.handle(query);

        var conversationResources = conversations.stream()
                .map(ConversationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(conversationResources);
    }
}
