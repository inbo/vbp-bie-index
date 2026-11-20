package au.org.ala.bie

import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry

/**
 * Custom WebSocket configuration that adds CORS support for SockJS transports.
 */
@EnableWebSocketMessageBroker
class CustomWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /** Comma-separated origin patterns, from websocket.cors.allowedOrigins */
    String allowedOrigins = "*"

    @Override
    void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        String[] origins = (allowedOrigins ?: "*").tokenize(',')*.trim().findAll() as String[]
        stompEndpointRegistry.addEndpoint("/stomp")
                .setAllowedOriginPatterns(origins)
                .withSockJS()
    }

}
