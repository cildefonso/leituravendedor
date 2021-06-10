package bip.leituravendedor.com.br.gateway.listener;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import bip.leituravendedor.com.br.gateway.json.FileUUIDJson;
import bip.leituravendedor.com.br.service.ReaderFileService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ReadInputVendedorListener {

    @Autowired
    private ReaderFileService readerFileService;
    
    

    //@KafkaListener(topics = "kafka.topictopics1, kafka.topictopics2, kafka.topictopics3, kafka.topictopics4", groupId = "${kafka.consumergroup}")
    @KafkaListener(topics = {"${kafka.topictopics1}", "${kafka.topictopics2}", "${kafka.topictopics3}", "${kafka.topictopics4}"}, groupId = "${kafka.consumergroup}")
    public void execute(String message, ConsumerRecord consumerRecord) throws IOException {
    	try {
    	  
    		log.info("key: " + consumerRecord.key());
            log.info("Headers: " + consumerRecord.headers());
            log.info("Partion: " + consumerRecord.partition());
            log.info("Order: " + consumerRecord.value());
            log.info("Order: " + consumerRecord.topic());
    		if (!message.equals("")) {
    			FileUUIDJson fileUUIDJson = new Gson().fromJson(message, FileUUIDJson.class);
    			readerFileService.execute(fileUUIDJson);
    		}
    	}
    	catch (IOException ioe) {
    		log.error(ioe);
    	}
    }
    

    
    
//    public void deleteTopic(String name) {
//    	 if (topics().contains(name)) {
//    	  log.warn("Deleting topic. name: {}", name);
//    	  DeleteTopicsResult result = adminClient.deleteTopics(singleton(name));
//    	  try {
//    	   result.all().get();
//    	   log.warn("Deleted topic. name: {}, result: {}", name, result);
//    	   deletedTopics.inc(result.values().size());
//    	  } catch (InterruptedException | ExecutionException  e) {
//    	   log.error("Exception occured during topic deletion. name: {}", name, e);
//    	  }
//    	 }
//    }
    
//    public void deleteMessages(String topicName, int partitionIndex, int beforeIndex) {
//        TopicPartition topicPartition = new TopicPartition(topicName, partitionIndex);
//        Map<TopicPartition, RecordsToDelete> deleteMap = new HashMap<>();
//        deleteMap.put(topicPartition, RecordsToDelete.beforeOffset(beforeIndex));
//        kafkaAdminClient.deleteRecords(deleteMap);
// }
   
}
