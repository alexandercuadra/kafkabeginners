package com.github.alexandercuadra.kafka.tutorial1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoKeys {

        public static void main(String[] args) {
                final Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallbacks.class);
                String bootstrapServers = "127.0.0.1:9092";

                // Crear Producer Properties
                Properties properties = new Properties();

                properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); //kafka address
                properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
                properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

                // Crear el producer

                KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties); //string string significa que queremos el key como string y vlauer como un string


                for (int i=0; i<10;i++){
                        // Crear producer record

                        String topic = "first_topic";
                        String value = "Probando Kafka"+ Integer.toString(i);
                        String key = "id_" + Integer.toString(i);


                        ProducerRecord<String, String> record =
                                new ProducerRecord<String, String>(topic,key,value);
                        // send data - asychronous
                        producer.send(record, new Callback() {
                                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                                        //executes every time a record is seccessfully sent or an exception is thrown
                                        if (e == null) {
                                                // record es enviado
                                                logger.info("Received new medatada: \n" +
                                                        "Topic: " + recordMetadata.topic() + "\n" +
                                                        "partition:" + recordMetadata.partition() + "\n" +
                                                        "Offset: " + recordMetadata.offset() + "\n" +
                                                        "Timestamp: " + recordMetadata.timestamp());
                                        } else {
                                                logger.error("Error while producing, e");

                                        }
                                }
                        });
                }

                // flush data
                producer.flush();
                producer.close();
        }
}


