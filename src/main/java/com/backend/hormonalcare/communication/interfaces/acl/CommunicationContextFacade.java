package com.backend.hormonalcare.communication.interfaces.acl;

import com.backend.hormonalcare.communication.domain.model.commands.CreateConversationCommand;
import com.backend.hormonalcare.communication.domain.model.commands.SendMessageCommand;
import com.backend.hormonalcare.communication.domain.services.CommunicationCommandService;

import java.util.List;

public class CommunicationContextFacade {

    private final CommunicationCommandService communicationCommandService;

    public CommunicationContextFacade(CommunicationCommandService communicationCommandService) {
        this.communicationCommandService = communicationCommandService;
    }

    public Long createConversationBetweenDoctorAndPatient(Long doctorProfileId, Long patientProfileId) {
        var command = new CreateConversationCommand(List.of(doctorProfileId, patientProfileId));
        var conversationOptional = communicationCommandService.handle(command);
        return conversationOptional.map(conversation -> conversation.getId()).orElse(null);
    }

    public Long sendMessage(Long conversationId, Long senderProfileId, Long receiverProfileId, String text) {
        var command = new SendMessageCommand(conversationId, senderProfileId, receiverProfileId, text, null);
        var messageIdOptional = communicationCommandService.handle(command);
        return messageIdOptional.orElse(null).getId();
    }
}
