package main;

import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Main {
	public static void main(String [] args) {
		System.out.println("====== INICIALIZANDO TESTE DO WSS ======");
		
		String topic = "MQTT Examples";
		String content = "Message from MqttPuslishSample";
		int qos = 2;
		String broker = "wss://ws-cadmobile.prf.gov.br/ws";
		String clientId = "JavaSample";
		MemoryPersistence persistence = new MemoryPersistence();
		
		Scanner scan_ = new Scanner(System.in);
		
		System.out.println("Insira um endereço de conexão:");
		broker = scan_.nextLine();
		
		System.out.println("Insira o usuario: ");
		String user = scan_.nextLine();
		
		System.out.println("Insira a senha: ");
		String password = scan_.nextLine();
		
		try {
			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setUserName(user);
			connOpts.setPassword(password.toCharArray());
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: " + broker);
			sampleClient.connect(connOpts);
			System.out.println("Connected");
//			System.out.println("Publishing message: " + content);
//			MqttMessage message = new MqttMessage(content.getBytes());
//			message.setQos(qos);
//			sampleClient.publish(topic, message);
//			System.out.println("Message Published");
			sampleClient.disconnect();
			System.out.println("Disconnected");
			System.exit(0);
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
		
		
	}
}