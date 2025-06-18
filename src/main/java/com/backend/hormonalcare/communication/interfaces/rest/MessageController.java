package com.backend.hormonalcare.communication.interfaces.rest;

import com.backend.hormonalcare.communication.domain.model.aggregates.Conversation;
import com.backend.hormonalcare.communication.domain.model.commands.MarkMessageAsReadCommand;
import com.backend.hormonalcare.communication.domain.model.queries.GetMessagesByConversationIdQuery;
import com.backend.hormonalcare.communication.domain.model.queries.GetUnreadMessagesByUserIdQuery;
import com.backend.hormonalcare.communication.domain.services.CommunicationCommandService;
import com.backend.hormonalcare.communication.domain.services.CommunicationQueryService;
import com.backend.hormonalcare.communication.interfaces.rest.resources.MessageResource;
import com.backend.hormonalcare.communication.interfaces.rest.resources.SendMessageResource;
import com.backend.hormonalcare.communication.interfaces.rest.transform.MessageResourceFromEntityAssembler;
import com.backend.hormonalcare.communication.interfaces.rest.transform.SendMessageCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/Messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final CommunicationCommandService communicationCommandService;
    private final CommunicationQueryService communicationQueryService;

    public MessageController(CommunicationCommandService communicationCommandService, CommunicationQueryService communicationQueryService) {
        this.communicationCommandService = communicationCommandService;
        this.communicationQueryService = communicationQueryService;
    }

    @PostMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<MessageResource> sendMessage(
            @PathVariable Long conversationId,
            @RequestBody SendMessageResource resource) {

        var command = SendMessageCommandFromResourceAssembler.toCommandFromResource(conversationId, resource);
        var messageOptional = communicationCommandService.handle(command);

        if (messageOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var message = messageOptional.get();
        var messageResource = MessageResourceFromEntityAssembler.toResourceFromEntity(message);
        return new ResponseEntity<>(messageResource, HttpStatus.CREATED);
    }

    @GetMapping("/conversations/{conversationId}/messages")
    public ResponseEntity<List<MessageResource>> getMessagesByConversationId(
            @PathVariable Long conversationId,
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        var query = new GetMessagesByConversationIdQuery(conversationId, userId, page, size);
        var messages = communicationQueryService.handle(query);

        var messageResources = messages.stream()
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(messageResources);
    }

    @GetMapping("/users/{userId}/messages/unread")
    public ResponseEntity<List<MessageResource>> getUnreadMessagesByUserId(@PathVariable Long userId) {
        var query = new GetUnreadMessagesByUserIdQuery(userId);
        var messages = communicationQueryService.handle(query);

        var messageResources = messages.stream()
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(messageResources);
    }

    @PatchMapping("/conversations/{conversationId}/messages/{messageId}/read")
    public ResponseEntity<Void> markMessageAsRead(
            @PathVariable Long conversationId,
            @PathVariable Long messageId,
            @RequestParam Long userId) {

        var command = new MarkMessageAsReadCommand(conversationId, messageId, userId);
        var conversationOptional = communicationCommandService.handle(command);

        if (conversationOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
