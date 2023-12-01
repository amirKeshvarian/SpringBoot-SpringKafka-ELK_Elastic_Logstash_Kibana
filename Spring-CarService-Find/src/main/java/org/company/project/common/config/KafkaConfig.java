//package org.company.project.common.config;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.TopicBuilder;
//
//@Configuration
//public class KafkaConfig {
//    @Bean
//    public NewTopic personTopic() {
//        return TopicBuilder.name("car-event")
//                .partitions(3)
//                .build();
//    }
//}