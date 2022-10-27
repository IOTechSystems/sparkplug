/********************************************************************************
 * Copyright (c) 2014-2022 Cirrus Link Solutions and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Cirrus Link Solutions - initial implementation
 ********************************************************************************/

package org.eclipse.sparkplug.impl.exception.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * A class representing a Sparkplug B payload
 */
@JsonInclude(Include.NON_NULL)
public class SparkplugBPayload {

	private Date timestamp;
	private List<Metric> metrics;
	private Long seq = null;
	private String uuid;
	private byte[] body;

	public SparkplugBPayload() {
	}

	public SparkplugBPayload(Date timestamp, List<Metric> metrics, Long seq, String uuid, byte[] body) {
		this(timestamp, metrics, seq);
		this.uuid = uuid;
		this.body = body;
	}

	public SparkplugBPayload(Date timestamp, List<Metric> metrics, Long seq) {
		this(timestamp, metrics);
		this.seq = seq;
	}

	public SparkplugBPayload(Date timestamp, List<Metric> metrics) {
		this.timestamp = timestamp;
		this.metrics = metrics;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void addMetric(Metric metric) {
		metrics.add(metric);
	}

	public void addMetric(int index, Metric metric) {
		metrics.add(index, metric);
	}

	public void addMetrics(List<Metric> metrics) {
		this.metrics.addAll(metrics);
	}

	public Metric removeMetric(int index) {
		return metrics.remove(index);
	}

	public boolean removeMetric(Metric metric) {
		return metrics.remove(metric);
	}

	public List<Metric> getMetrics() {
		return metrics;
	}

	@JsonIgnore
	public Integer getMetricCount() {
		return metrics.size();
	}

	public void setMetrics(List<Metric> metrics) {
		this.metrics = metrics;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SparkplugBPayload [timestamp=");
		builder.append(timestamp != null ? timestamp.getTime() : "null");
		builder.append(", metrics=");
		builder.append(metrics);
		builder.append(", seq=");
		builder.append(seq != null ? seq : "null");
		builder.append(", uuid=");
		builder.append(uuid);
		builder.append(", body=");
		builder.append(Arrays.toString(body));
		builder.append("]");
		return builder.toString();
	}

	/**
	 * A builder for creating a {@link SparkplugBPayload} instance.
	 */
	public static class SparkplugBPayloadBuilder {

		private Date timestamp;
		private List<Metric> metrics;
		private Long seq = null;
		private String uuid;
		private byte[] body;

		public SparkplugBPayloadBuilder(Long sequenceNumber) {
			this.seq = sequenceNumber;
			metrics = new ArrayList<Metric>();
		}

		public SparkplugBPayloadBuilder() {
			metrics = new ArrayList<Metric>();
		}

		public SparkplugBPayloadBuilder addMetric(Metric metric) {
			this.metrics.add(metric);
			return this;
		}

		public SparkplugBPayloadBuilder addMetrics(Collection<Metric> metrics) {
			this.metrics.addAll(metrics);
			return this;
		}

		public SparkplugBPayloadBuilder setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public SparkplugBPayloadBuilder setSeq(Long seq) {
			this.seq = seq;
			return this;
		}

		public SparkplugBPayloadBuilder setUuid(String uuid) {
			this.uuid = uuid;
			return this;
		}

		public SparkplugBPayloadBuilder setBody(byte[] body) {
			this.body = body;
			return this;
		}

		public SparkplugBPayload createPayload() {
			return new SparkplugBPayload(timestamp, metrics, seq, uuid, body);
		}
	}
}
