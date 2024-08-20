package mk.ukim.finki.busngobackend.service

import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class WebSocketService(
    private val messagingTemplate: SimpMessagingTemplate,
) {
    fun sendMessage(
        topicSuffix: String,
        message: String,
    ) {
        messagingTemplate.convertAndSend("/topic/$topicSuffix", message)
    }
}
