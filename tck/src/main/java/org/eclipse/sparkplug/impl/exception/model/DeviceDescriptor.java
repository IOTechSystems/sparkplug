/********************************************************************************
 * Copyright (c) 2020-2022 Cirrus Link Solutions and others
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

public class DeviceDescriptor extends EdgeNodeDescriptor {

	private final String deviceId;
	private final String descriptorString;

	public DeviceDescriptor(String groupId, String edgeNodeId, String deviceId) {
		super(groupId, edgeNodeId);
		this.deviceId = deviceId;
		this.descriptorString = groupId + "/" + edgeNodeId + "/" + deviceId;
	}

	public DeviceDescriptor(String descriptorString) {
		super(descriptorString.substring(0, descriptorString.lastIndexOf("/")));
		this.deviceId = descriptorString.substring(descriptorString.lastIndexOf("/") + 1);
		this.descriptorString = descriptorString;
	}

	public DeviceDescriptor(EdgeNodeDescriptor edgeNodeDescriptor, String deviceId) {
		super(edgeNodeDescriptor.getGroupId(), edgeNodeDescriptor.getEdgeNodeId());
		this.deviceId = deviceId;
		this.descriptorString = edgeNodeDescriptor.getDescriptorString() + "/" + deviceId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * Returns a {@link String} representing the Device's Descriptor of the form:
	 * "<groupName>/<edgeNodeName>/<deviceId>".
	 * 
	 * @return a {@link String} representing the Device's Descriptor.
	 */
	@Override
	public String getDescriptorString() {
		return descriptorString;
	}

	public String getEdgeNodeDescriptorString() {
		return super.getDescriptorString();
	}

	@Override
	public int hashCode() {
		return this.getDescriptorString().hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof DeviceDescriptor) {
			return this.getDescriptorString().equals(((DeviceDescriptor) object).getDescriptorString());
		}
		return this.getDescriptorString().equals(object);
	}

	@Override
	public String toString() {
		return getDescriptorString();
	}
}
