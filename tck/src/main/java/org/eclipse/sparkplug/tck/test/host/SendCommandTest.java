/*******************************************************************************
 * Copyright (c) 2021, 2022 Ian Craggs
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Craggs - initial implementation and documentation
 *******************************************************************************/

package org.eclipse.sparkplug.tck.test.host;

import static org.eclipse.sparkplug.tck.test.common.Constants.TCK_CONSOLE_PROMPT_TOPIC;
import static org.eclipse.sparkplug.tck.test.common.Constants.TCK_LOG_TOPIC;
import static org.eclipse.sparkplug.tck.test.common.Constants.TCK_CONSOLE_REPLY_TOPIC;
import static org.eclipse.sparkplug.tck.test.common.Constants.TOPIC_PATH_DBIRTH;
import static org.eclipse.sparkplug.tck.test.common.Constants.TOPIC_PATH_DCMD;
import static org.eclipse.sparkplug.tck.test.common.Constants.TOPIC_PATH_NBIRTH;
import static org.eclipse.sparkplug.tck.test.common.Constants.TOPIC_PATH_NCMD;
import static org.eclipse.sparkplug.tck.test.common.Constants.TOPIC_PATH_NDEATH;
import static org.eclipse.sparkplug.tck.test.common.Constants.TOPIC_ROOT_SP_BV_1_0;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_MESSAGE_FLOW_EDGE_NODE_BIRTH_PUBLISH_WILL_MESSAGE_TOPIC;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_METRIC_NAME;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_METRIC_VALUE;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_VERB;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_METRIC_NAME;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_METRIC_VALUE;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_NAME;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VALUE;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VERB;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_VERB;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_PAYLOADS_DCMD_QOS;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_PAYLOADS_DCMD_RETAIN;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_PAYLOADS_DCMD_SEQ;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_PAYLOADS_DCMD_TIMESTAMP;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_PAYLOADS_NCMD_QOS;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_PAYLOADS_NCMD_RETAIN;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_PAYLOADS_NCMD_SEQ;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_PAYLOADS_NCMD_TIMESTAMP;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_TOPICS_DCMD_MQTT;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_TOPICS_DCMD_PAYLOAD;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_TOPICS_DCMD_TIMESTAMP;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_TOPICS_DCMD_TOPIC;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_TOPICS_NCMD_MQTT;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_TOPICS_NCMD_PAYLOAD;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_TOPICS_NCMD_TIMESTAMP;
import static org.eclipse.sparkplug.tck.test.common.Requirements.ID_TOPICS_NCMD_TOPIC;
import static org.eclipse.sparkplug.tck.test.common.Requirements.MESSAGE_FLOW_EDGE_NODE_BIRTH_PUBLISH_WILL_MESSAGE_TOPIC;
import static org.eclipse.sparkplug.tck.test.common.Requirements.OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_METRIC_NAME;
import static org.eclipse.sparkplug.tck.test.common.Requirements.OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_METRIC_VALUE;
import static org.eclipse.sparkplug.tck.test.common.Requirements.OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_VERB;
import static org.eclipse.sparkplug.tck.test.common.Requirements.OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_METRIC_NAME;
import static org.eclipse.sparkplug.tck.test.common.Requirements.OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_METRIC_VALUE;
import static org.eclipse.sparkplug.tck.test.common.Requirements.OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_NAME;
import static org.eclipse.sparkplug.tck.test.common.Requirements.OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VALUE;
import static org.eclipse.sparkplug.tck.test.common.Requirements.OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VERB;
import static org.eclipse.sparkplug.tck.test.common.Requirements.OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_VERB;
import static org.eclipse.sparkplug.tck.test.common.Requirements.PAYLOADS_DCMD_QOS;
import static org.eclipse.sparkplug.tck.test.common.Requirements.PAYLOADS_DCMD_RETAIN;
import static org.eclipse.sparkplug.tck.test.common.Requirements.PAYLOADS_DCMD_SEQ;
import static org.eclipse.sparkplug.tck.test.common.Requirements.PAYLOADS_DCMD_TIMESTAMP;
import static org.eclipse.sparkplug.tck.test.common.Requirements.PAYLOADS_NCMD_QOS;
import static org.eclipse.sparkplug.tck.test.common.Requirements.PAYLOADS_NCMD_RETAIN;
import static org.eclipse.sparkplug.tck.test.common.Requirements.PAYLOADS_NCMD_SEQ;
import static org.eclipse.sparkplug.tck.test.common.Requirements.PAYLOADS_NCMD_TIMESTAMP;
import static org.eclipse.sparkplug.tck.test.common.Requirements.TOPICS_DCMD_MQTT;
import static org.eclipse.sparkplug.tck.test.common.Requirements.TOPICS_DCMD_PAYLOAD;
import static org.eclipse.sparkplug.tck.test.common.Requirements.TOPICS_DCMD_TIMESTAMP;
import static org.eclipse.sparkplug.tck.test.common.Requirements.TOPICS_DCMD_TOPIC;
import static org.eclipse.sparkplug.tck.test.common.Requirements.TOPICS_NCMD_MQTT;
import static org.eclipse.sparkplug.tck.test.common.Requirements.TOPICS_NCMD_PAYLOAD;
import static org.eclipse.sparkplug.tck.test.common.Requirements.TOPICS_NCMD_TIMESTAMP;
import static org.eclipse.sparkplug.tck.test.common.Requirements.TOPICS_NCMD_TOPIC;
import static org.eclipse.sparkplug.tck.test.common.Utils.checkHostApplicationIsOnline;
import static org.eclipse.sparkplug.tck.test.common.Utils.setResult;
import static org.eclipse.sparkplug.tck.test.common.Utils.setShouldResult;
import static org.eclipse.sparkplug.tck.test.common.Utils.addQuotes;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.sparkplug.tck.sparkplug.Sections;
import org.eclipse.sparkplug.tck.test.TCK;
import org.eclipse.sparkplug.tck.test.TCKTest;
import org.eclipse.sparkplug.tck.test.common.Constants;
import org.eclipse.sparkplug.tck.test.common.Constants.TestStatus;
import org.eclipse.sparkplug.tck.test.common.SparkplugBProto.DataType;
import org.eclipse.sparkplug.tck.test.common.SparkplugBProto.Payload.Metric;
import org.eclipse.sparkplug.tck.test.common.SparkplugBProto.PayloadOrBuilder;
import org.eclipse.sparkplug.tck.test.common.Utils;
import org.jboss.test.audit.annotations.SpecAssertion;
import org.jboss.test.audit.annotations.SpecVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * This is the primary host Sparkplug send command test:
 *
 * to check that a command from a primary host under test is correct to both an
 * edge node (NCMD) and a device (DCMD).
 *
 * There will be a prompt to the person executing the test to send a command to
 *
 * The host application under test must be connected and online prior to starting this test.
 * The id of the host application must be passed as the first parameter to this test.
 * The second parameter is the id of the edge node to be used.
 * The third parameter is the id of the device to be used.
 *
 * @author Ian Craggs, Anja Helmbrecht-Schaar
 */

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.packets.connect.ConnectPacket;
import com.hivemq.extension.sdk.api.packets.connect.WillPublishPacket;
import com.hivemq.extension.sdk.api.packets.disconnect.DisconnectPacket;
import com.hivemq.extension.sdk.api.packets.general.Qos;
import com.hivemq.extension.sdk.api.packets.publish.PublishPacket;
import com.hivemq.extension.sdk.api.packets.subscribe.SubscribePacket;
import com.hivemq.extension.sdk.api.services.Services;
import com.hivemq.extension.sdk.api.services.builder.Builders;
import com.hivemq.extension.sdk.api.services.publish.Publish;
import com.hivemq.extension.sdk.api.services.publish.PublishService;

@SpecVersion(
		spec = "sparkplug",
		version = "3.0.0-SNAPSHOT")
public class SendCommandTest extends TCKTest {

	private static final String NODE_CONTROL_REBIRTH = "Node Control/Rebirth";
	private static final String EDGE_METRIC = "TCK_metric/Boolean";
	private static final String DEVICE_METRIC = "Inputs/0";

	private static final Logger logger = LoggerFactory.getLogger("Sparkplug");
	private final @NotNull Map<String, String> testResults = new HashMap<>();
	private final @NotNull List<String> testIds = List.of(ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_VERB,
			ID_TOPICS_NCMD_MQTT, ID_PAYLOADS_NCMD_QOS, ID_PAYLOADS_NCMD_RETAIN, ID_TOPICS_NCMD_TIMESTAMP,
			ID_PAYLOADS_NCMD_SEQ, ID_PAYLOADS_NCMD_TIMESTAMP, ID_TOPICS_NCMD_PAYLOAD,
			ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VERB,
			ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_NAME,
			ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VALUE, ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_VERB,
			ID_TOPICS_DCMD_MQTT, ID_PAYLOADS_DCMD_QOS, ID_PAYLOADS_DCMD_RETAIN, ID_TOPICS_DCMD_TIMESTAMP,
			ID_PAYLOADS_DCMD_TIMESTAMP, ID_PAYLOADS_DCMD_SEQ, ID_TOPICS_DCMD_PAYLOAD,
			ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_METRIC_NAME,
			ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_METRIC_VALUE,
			ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_METRIC_NAME,
			ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_METRIC_VALUE, ID_TOPICS_NCMD_TOPIC, ID_TOPICS_DCMD_TOPIC);
	private @NotNull String deviceId;
	private @NotNull String groupId;
	private @NotNull String edgeNodeId;
	private @NotNull String hostApplicationId;

	private String edgeNodeTestClientId = null;
	private List<Metric> edgeBirthMetrics = null;
	private List<Metric> deviceBirthMetrics = null;

	private TestStatus state = null;
	private TCK theTCK = null;

	private PublishService publishService = Services.publishService();

	public SendCommandTest(TCK aTCK, String[] params) {
		logger.info("Primary host {}: Parameters: {} ", getName(), Arrays.asList(params));
		theTCK = aTCK;

		if (params.length < 4) {
			log("Not enough parameters: " + Arrays.toString(params));
			log("Parameters to host send command test must be: hostApplicationId, groupId edgeNodeId deviceId");
			throw new IllegalArgumentException();
		}
		hostApplicationId = params[0];
		groupId = params[1];
		edgeNodeId = params[2];
		deviceId = params[3];
		logger.info("Parameters are HostApplicationId: {}, GroupId: {}, EdgeNodeId: {}, DeviceId: {}",
				hostApplicationId, groupId, edgeNodeId, deviceId);

		final AtomicBoolean hostOnline = checkHostApplicationIsOnline(hostApplicationId);

		if (!hostOnline.get()) {
			logger.info("HostApplication {} not online - test not started.", hostApplicationId);
			return;
		}

		// First we have to connect an edge node and device.
		// We do this by sending an MQTT control message to the TCK device utility.
		// ONLY DO THIS IF THE EDGE/DEVICE haven't already been created!!
		state = TestStatus.CONNECTING_DEVICE;

		String payload = "NEW DEVICE " + addQuotes(hostApplicationId) + " " + addQuotes(groupId) + " "
				+ addQuotes(edgeNodeId) + " " + addQuotes(deviceId);
		Publish message = Builders.publish().topic(Constants.TCK_DEVICE_CONTROL_TOPIC).qos(Qos.AT_LEAST_ONCE)
				.payload(ByteBuffer.wrap(payload.getBytes())).build();
		logger.info("Requesting new device creation. GroupId: {}, EdgeNodeId: {}, DeviceId: {}", groupId, edgeNodeId,
				deviceId);
		publishService.publish(message);
	}

	@Override
	public void endTest(Map<String, String> results) {
		testResults.putAll(results);
		Utils.setEndTest(getName(), testIds, testResults);
		reportResults(testResults);
	}

	@Override
	public String getName() {
		return "Host SendCommand";
	}

	@Override
	public String[] getTestIds() {
		return testIds.toArray(new String[0]);
	}

	@Override
	public Map<String, String> getResults() {
		return testResults;
	}

	@SpecAssertion(
			section = Sections.OPERATIONAL_BEHAVIOR_EDGE_NODE_SESSION_ESTABLISHMENT,
			id = ID_MESSAGE_FLOW_EDGE_NODE_BIRTH_PUBLISH_WILL_MESSAGE_TOPIC)
	@Override
	public void connect(String clientId, ConnectPacket packet) {
		/* Determine if this the connect packet for the Edge node under test.
		 * Set the clientid if so. */
		Optional<WillPublishPacket> willPublishPacketOptional = packet.getWillPublish();
		if (willPublishPacketOptional.isPresent()) {
			WillPublishPacket willPublishPacket = willPublishPacketOptional.get();
			String willTopic = willPublishPacket.getTopic();
			if (willTopic.equals(TOPIC_ROOT_SP_BV_1_0 + "/" + groupId + "/" + TOPIC_PATH_NDEATH + "/" + edgeNodeId)) {
				edgeNodeTestClientId = clientId;
				logger.info("Host Application send command test - connect - client id is " + clientId);
				testResults.put(ID_MESSAGE_FLOW_EDGE_NODE_BIRTH_PUBLISH_WILL_MESSAGE_TOPIC,
						setResult(true, MESSAGE_FLOW_EDGE_NODE_BIRTH_PUBLISH_WILL_MESSAGE_TOPIC));
			}
		}
	}

	@Override
	public void disconnect(String clientId, DisconnectPacket packet) {
		logger.info("Host - {} - DISCONNECT {}, {} ", getName(), clientId, state);
	}

	@Override
	public void subscribe(String clientId, SubscribePacket packet) {
		logger.info("Host - {} - SUBSCRIBE {}, {} ", getName(), clientId, state);
	}

	private void publishToTckConsolePrompt(String payload) {
		Publish message = Builders.publish().topic(TCK_CONSOLE_PROMPT_TOPIC).qos(Qos.AT_LEAST_ONCE)
				.payload(ByteBuffer.wrap(payload.getBytes())).build();
		logger.info("Requesting command to edge node id:{} ", edgeNodeId);
		publishService.publish(message);
	}

	@Override
	public void publish(String clientId, PublishPacket packet) {
		logger.info("Host - {} test - PUBLISH - topic: {}, state: {} ", getName(), packet.getTopic(), state);

		if (edgeNodeTestClientId != null && edgeNodeTestClientId.equals(clientId)) {
			logger.debug("Host send command test - publish -  to topic: {} ", packet.getTopic());
			String topic = packet.getTopic();
			String[] topicLevels = topic.split("/");

			if (!(topicLevels[0].equals(TOPIC_ROOT_SP_BV_1_0) && topicLevels[1].equals(groupId))) {
				logger.info("Skip - Edge session establishment test for this topic");
				return;
			}

			// Example: spBv1.0/Group0/DBIRTH/Edge0/Device0
			if (topicLevels[2].equals(TOPIC_PATH_NBIRTH)) {
				if (topicLevels[3].equals(edgeNodeId)) {
					PayloadOrBuilder sparkplugPayload = Utils.getSparkplugPayload(packet);
					if (sparkplugPayload != null) {
						edgeBirthMetrics = sparkplugPayload.getMetricsList();
					}
				}
			}
			if (topicLevels[2].equals(TOPIC_PATH_DBIRTH) && topicLevels[3].equals(edgeNodeId)) {
				String device = topicLevels[topicLevels.length - 1];
				logger.debug("Start check for Device {} ", device);
				if (device.equals(deviceId)) {
					PayloadOrBuilder sparkplugPayload = Utils.getSparkplugPayload(packet);
					if (sparkplugPayload != null) {
						deviceBirthMetrics = sparkplugPayload.getMetricsList();
					}
				}
			}
		}

		final String topic = packet.getTopic();
		if (topic.equals(TCK_CONSOLE_REPLY_TOPIC)) {
			ByteBuffer byteBuffer = packet.getPayload().orElseGet(null);
			if (byteBuffer != null) {
				final String payload = StandardCharsets.UTF_8.decode(byteBuffer).toString();
				if (payload.equals("Device " + deviceId + " successfully created")) {
					logger.info("SendCommandTest: Device was created");
					publishToTckConsolePrompt("Send an edge rebirth to edge node " + edgeNodeId);
					state = TestStatus.EXPECT_NODE_REBIRTH;
				}
			}
		} else if (topic
				.equals(Constants.TOPIC_ROOT_SP_BV_1_0 + "/" + groupId + "/" + TOPIC_PATH_NCMD + "/" + edgeNodeId)) {
			if (state == TestStatus.EXPECT_NODE_REBIRTH) {
				checkNodeCommand(clientId, packet);
				publishToTckConsolePrompt("Send an edge command to edge node " + edgeNodeId + " metric " + EDGE_METRIC);
				state = TestStatus.EXPECT_NODE_COMMAND;
			} else if (state == TestStatus.EXPECT_NODE_COMMAND) {
				checkNodeCommand(clientId, packet);
				publishToTckConsolePrompt(
						"Send a device rebirth command to device " + deviceId + " at edge node " + edgeNodeId);
				state = TestStatus.EXPECT_DEVICE_REBIRTH;
			}
		} else if (topic.equals(Constants.TOPIC_ROOT_SP_BV_1_0 + "/" + groupId + "/" + TOPIC_PATH_DCMD + "/"
				+ edgeNodeId + "/" + deviceId)) {
			if (state == TestStatus.EXPECT_DEVICE_REBIRTH) {
				checkDeviceCommand(clientId, packet);
				publishToTckConsolePrompt("Send a device command to device " + deviceId + " at edge node " + edgeNodeId
						+ " metric " + DEVICE_METRIC);
				state = Constants.TestStatus.EXPECT_DEVICE_COMMAND;
			} else if (state == TestStatus.EXPECT_DEVICE_COMMAND) {
				checkDeviceCommand(clientId, packet);
				theTCK.endTest();
			}
		}
	}

	@SpecAssertion(
			section = Sections.PAYLOADS_B_NCMD,
			id = ID_PAYLOADS_NCMD_TIMESTAMP)
	@SpecAssertion(
			section = Sections.PAYLOADS_B_NCMD,
			id = ID_PAYLOADS_NCMD_SEQ)
	@SpecAssertion(
			section = Sections.PAYLOADS_B_NCMD,
			id = ID_PAYLOADS_NCMD_QOS)
	@SpecAssertion(
			section = Sections.PAYLOADS_B_NCMD,
			id = ID_PAYLOADS_NCMD_RETAIN)
	@SpecAssertion(
			section = Sections.PAYLOADS_DESC_NCMD,
			id = ID_TOPICS_NCMD_MQTT)
	@SpecAssertion(
			section = Sections.PAYLOADS_DESC_NCMD,
			id = ID_TOPICS_NCMD_TIMESTAMP)
	@SpecAssertion(
			section = Sections.PAYLOADS_DESC_NCMD,
			id = ID_TOPICS_NCMD_PAYLOAD)
	@SpecAssertion(
			section = Sections.OPERATIONAL_BEHAVIOR_COMMANDS,
			id = ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_VERB)
	@SpecAssertion(
			section = Sections.TOPICS_COMMAND_NCMD,
			id = ID_TOPICS_NCMD_TOPIC)
	private void checkNodeCommand(final String clientId, final @NotNull PublishPacket packet) {
		logger.info("Host - {}  - PUBLISH - checkNodeCommand {}, {}", getName(), packet.getTopic(), state);

		logger.debug("Check Req: {}:{}.", ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_VERB,
				OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_VERB);
		testResults.put(ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_VERB,
				setResult(true, OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_VERB));

		// QoS and not retained - related tests
		logger.debug("Check Req: {}:{}.", ID_TOPICS_NCMD_MQTT, TOPICS_NCMD_MQTT);
		testResults.put(ID_TOPICS_NCMD_MQTT,
				setResult((packet.getQos() == Qos.AT_MOST_ONCE && !packet.getRetain()), TOPICS_NCMD_MQTT));

		logger.debug("Check Req: {}:{}.", ID_PAYLOADS_NCMD_QOS, PAYLOADS_NCMD_QOS);
		testResults.put(ID_PAYLOADS_NCMD_QOS, setResult((packet.getQos() == Qos.AT_MOST_ONCE), PAYLOADS_NCMD_QOS));

		logger.debug("Check Req: {}:{}.", ID_PAYLOADS_NCMD_RETAIN, PAYLOADS_NCMD_RETAIN);
		testResults.put(ID_PAYLOADS_NCMD_RETAIN, setResult(!packet.getRetain(), PAYLOADS_NCMD_RETAIN));

		// payload related tests
		PayloadOrBuilder inboundPayload = Utils.getSparkplugPayload(packet);
		Boolean[] bValid = checkValidCommandPayload(inboundPayload);

		logger.debug("Check Req: {}:{}.", ID_TOPICS_NCMD_TIMESTAMP, TOPICS_NCMD_TIMESTAMP);
		testResults.put(ID_TOPICS_NCMD_TIMESTAMP, setResult(bValid[0], TOPICS_NCMD_TIMESTAMP));

		logger.debug("Check Req: {}:{}.", ID_PAYLOADS_NCMD_SEQ, PAYLOADS_NCMD_SEQ);
		testResults.put(ID_PAYLOADS_NCMD_SEQ, setResult(bValid[1], PAYLOADS_NCMD_SEQ));

		logger.debug("Check Req: {}:{}.", ID_PAYLOADS_NCMD_TIMESTAMP, PAYLOADS_NCMD_TIMESTAMP);
		testResults.put(ID_PAYLOADS_NCMD_TIMESTAMP, setResult(bValid[0], PAYLOADS_NCMD_TIMESTAMP));

		logger.debug("Check Req: {}:{}.", ID_TOPICS_NCMD_PAYLOAD, TOPICS_NCMD_PAYLOAD);
		testResults.put(ID_TOPICS_NCMD_PAYLOAD, setResult(bValid[2], TOPICS_NCMD_PAYLOAD));

		// Topic check
		String topic = packet.getTopic();
		boolean goodTopic =
				topic.equals(TOPIC_ROOT_SP_BV_1_0 + "/" + groupId + "/" + TOPIC_PATH_NCMD + "/" + edgeNodeId);
		testResults.put(ID_TOPICS_NCMD_TOPIC, setResult(goodTopic, TOPICS_NCMD_TOPIC));
	}

	@SpecAssertion(
			section = Sections.PAYLOADS_B_DCMD,
			id = ID_PAYLOADS_DCMD_TIMESTAMP)
	@SpecAssertion(
			section = Sections.PAYLOADS_B_DCMD,
			id = ID_PAYLOADS_DCMD_SEQ)
	@SpecAssertion(
			section = Sections.PAYLOADS_B_DCMD,
			id = ID_PAYLOADS_DCMD_QOS)
	@SpecAssertion(
			section = Sections.PAYLOADS_B_DCMD,
			id = ID_PAYLOADS_DCMD_RETAIN)
	@SpecAssertion(
			section = Sections.PAYLOADS_DESC_DCMD,
			id = ID_TOPICS_DCMD_MQTT)
	@SpecAssertion(
			section = Sections.PAYLOADS_DESC_DCMD,
			id = ID_TOPICS_DCMD_TIMESTAMP)
	@SpecAssertion(
			section = Sections.PAYLOADS_DESC_DCMD,
			id = ID_TOPICS_DCMD_PAYLOAD)
	@SpecAssertion(
			section = Sections.OPERATIONAL_BEHAVIOR_COMMANDS,
			id = ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_VERB)
	@SpecAssertion(
			section = Sections.TOPICS_COMMAND_DCMD,
			id = ID_TOPICS_DCMD_TOPIC)
	private void checkDeviceCommand(String clientId, PublishPacket packet) {
		logger.info("Host - {}  - PUBLISH - checkDeviceCommand {}, {} ", getName(), packet.getTopic(), state);

		logger.debug("Check Req: {}:{}.", ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_VERB,
				OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_VERB);
		testResults.put(ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_VERB,
				setResult(true, OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_VERB));

		// QoS and not retained - related tests
		logger.debug("Check Req: {}:{}.", ID_TOPICS_DCMD_MQTT, TOPICS_DCMD_MQTT);
		testResults.put(ID_TOPICS_DCMD_MQTT,
				setResult(packet.getQos() == Qos.AT_MOST_ONCE && !packet.getRetain(), TOPICS_DCMD_MQTT));

		logger.debug("Check Req: {}:{}.", ID_PAYLOADS_DCMD_QOS, PAYLOADS_DCMD_QOS);
		testResults.put(ID_PAYLOADS_DCMD_QOS, setResult((packet.getQos() == Qos.AT_MOST_ONCE), PAYLOADS_DCMD_QOS));

		logger.debug("Check Req: {}:{}.", ID_PAYLOADS_DCMD_RETAIN, PAYLOADS_DCMD_RETAIN);
		testResults.put(ID_PAYLOADS_DCMD_RETAIN, setResult(!packet.getRetain(), PAYLOADS_DCMD_RETAIN));

		// payload related tests
		PayloadOrBuilder inboundPayload = Utils.getSparkplugPayload(packet);
		Boolean[] bValid = checkValidDeviceCommandPayload(inboundPayload);

		logger.debug("Check Req: {}:{}.", ID_TOPICS_DCMD_TIMESTAMP, TOPICS_DCMD_TIMESTAMP);
		testResults.put(ID_TOPICS_DCMD_TIMESTAMP, setResult(bValid[0], TOPICS_DCMD_TIMESTAMP));

		logger.debug("Check Req: {}:{}.", ID_PAYLOADS_DCMD_TIMESTAMP, PAYLOADS_DCMD_TIMESTAMP);
		testResults.put(ID_PAYLOADS_DCMD_TIMESTAMP, setResult(bValid[0], PAYLOADS_DCMD_TIMESTAMP));

		logger.debug("Check Req: {}:{}.", ID_PAYLOADS_DCMD_SEQ, PAYLOADS_DCMD_SEQ);
		testResults.put(ID_PAYLOADS_DCMD_SEQ, setResult(bValid[1], PAYLOADS_DCMD_SEQ));

		logger.debug("Check Req: {}:{}.", ID_TOPICS_DCMD_PAYLOAD, TOPICS_DCMD_PAYLOAD);
		testResults.put(ID_TOPICS_DCMD_PAYLOAD, setResult(bValid[2], TOPICS_DCMD_PAYLOAD));

		// Topic check
		String topic = packet.getTopic();
		boolean goodTopic = topic.equals(
				TOPIC_ROOT_SP_BV_1_0 + "/" + groupId + "/" + TOPIC_PATH_DCMD + "/" + edgeNodeId + "/" + deviceId);
		testResults.put(ID_TOPICS_DCMD_TOPIC, setResult(goodTopic, TOPICS_DCMD_TOPIC));
	}

	@SpecAssertion(
			section = Sections.OPERATIONAL_BEHAVIOR_COMMANDS,
			id = ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_METRIC_NAME)
	@SpecAssertion(
			section = Sections.OPERATIONAL_BEHAVIOR_COMMANDS,
			id = ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_METRIC_VALUE)
	private Boolean[] checkValidDeviceCommandPayload(PayloadOrBuilder payload) {
		logger.info("Host - {}  - PUBLISH - checkValidDeviceCommandPayload {}, {} ", getName(), payload, state);

		Boolean[] bValidPayload = new Boolean[] { false, false, false };

		if (payload != null) {
			bValidPayload[0] = (payload.hasTimestamp());
			bValidPayload[1] = !payload.hasSeq();
			List<Metric> metrics = payload.getMetricsList();

			ListIterator<Metric> metricIterator = metrics.listIterator();
			while (metricIterator.hasNext()) {
				Metric current = metricIterator.next();
				if (current.getName().equals(DEVICE_METRIC)) {
					bValidPayload[2] = true;
				}

				// look for the current metric name in the birth metrics
				for (Metric birth : deviceBirthMetrics) {
					if (birth.getName().equals(current.getName())) {
						testResults.put(ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_METRIC_NAME,
								setShouldResult(true, OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_METRIC_NAME));
						if (current.getDatatype() == birth.getDatatype() && Utils.hasValue(current)) {
							testResults.put(ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_METRIC_VALUE,
									setResult(true, OPERATIONAL_BEHAVIOR_DATA_COMMANDS_DCMD_METRIC_VALUE));
						}
						break;
					}
				}
			}
		}
		return bValidPayload;
	}

	@SpecAssertion(
			section = Sections.OPERATIONAL_BEHAVIOR_COMMANDS,
			id = ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VERB)
	@SpecAssertion(
			section = Sections.OPERATIONAL_BEHAVIOR_COMMANDS,
			id = ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_NAME)
	@SpecAssertion(
			section = Sections.OPERATIONAL_BEHAVIOR_COMMANDS,
			id = ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VALUE)
	@SpecAssertion(
			section = Sections.OPERATIONAL_BEHAVIOR_COMMANDS,
			id = ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_METRIC_NAME)
	@SpecAssertion(
			section = Sections.OPERATIONAL_BEHAVIOR_COMMANDS,
			id = ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_METRIC_VALUE)
	private Boolean[] checkValidCommandPayload(PayloadOrBuilder payload) {
		logger.info("Host - {}  - PUBLISH - checkValidCommandPayload {}, {} ", getName(), payload, state);

		Boolean[] bValidPayload = new Boolean[] { false, false, false };

		if (payload != null) {
			bValidPayload[0] = payload.hasTimestamp();
			bValidPayload[1] = !payload.hasSeq();
			List<Metric> metrics = payload.getMetricsList();

			ListIterator<Metric> metricIterator = metrics.listIterator();
			while (metricIterator.hasNext()) {
				Metric current = metricIterator.next();
				if (current.getName().equals(EDGE_METRIC)) {
					bValidPayload[2] = true;
				} else if (current.getName().equals(NODE_CONTROL_REBIRTH)) {
					bValidPayload[2] = true;

					logger.debug("Check Req: {}:{}.", ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VERB,
							OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VERB);
					testResults.put(ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VERB,
							setResult(true, OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VERB));

					logger.debug("Check Req: {}:{}.", ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_NAME,
							OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_NAME);
					testResults.put(ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_NAME,
							setResult(true, OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_NAME));

					if (current.getDatatype() == DataType.Boolean.getNumber()) {
						logger.debug("Check Req: {}:{}.", ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VALUE,
								OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VALUE);
						testResults.put(ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VALUE, setResult(
								current.getBooleanValue(), OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_REBIRTH_VALUE));
					}
				}

				// look for the current metric name in the birth metrics
				for (Metric birth : edgeBirthMetrics) {
					if (birth.getName().equals(current.getName())) {
						testResults.put(ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_METRIC_NAME,
								setShouldResult(true, OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_METRIC_NAME));
						testResults.put(ID_OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_METRIC_VALUE,
								setResult(current.getDatatype() == birth.getDatatype() && Utils.hasValue(current),
										OPERATIONAL_BEHAVIOR_DATA_COMMANDS_NCMD_METRIC_VALUE));
						break;
					}
				}
			}
		}
		return bValidPayload;
	}
}
