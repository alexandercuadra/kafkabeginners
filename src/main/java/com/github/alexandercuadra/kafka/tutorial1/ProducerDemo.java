package com.github.alexandercuadra.kafka.tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {

    public static void main(String[] args) {
            String bootstrapServers = "127.0.0.1:9092";

            // Crear Producer Properties
            Properties properties = new Properties();

            properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); //kafka address
            properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

            // Crear el producer

            KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties); //string string significa que queremos el key como string y vlauer como un string


            // Crear producer record
            ProducerRecord<String, String> record =
                    new ProducerRecord<String, String>("first_topic", "Probando Kafka");
            // send data - asychronous
            producer.send(record);

            // flush data
            producer.flush();
            producer.close();
    }
}


