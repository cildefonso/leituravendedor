package bip.leituravendedor.com.br.kafka;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@AllArgsConstructor
@Log4j2
public class KafkaRequestSender {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicRequest, Object request) {
    	try {
    		final String mensageKey = UUID.randomUUID().toString();
//    		Message<String> message = MessageBuilder
//    			.withPayload(new Gson().toJson(request))
//                .setHeader(KafkaHeaders.TOPIC, topicRequest)
//                .build();
    		this.kafkaTemplate.send(topicRequest, mensageKey, new Gson().toJson(request));
    		
    	}
        catch (Exception e) {
        	log.error(e);
        }
    }
    
   
    public boolean deleteTopic(String topicName) {
    	AdminClient adminClient = null;
        final DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Collections.singletonList(topicName));
        try {
          deleteTopicsResult.all().get();
        } catch (InterruptedException | ExecutionException e) {
          log.error("Failed to delete Kafka topic: {}, Exception: {}", topicName, e);
          return false;
        }
        return true;
      }


}
