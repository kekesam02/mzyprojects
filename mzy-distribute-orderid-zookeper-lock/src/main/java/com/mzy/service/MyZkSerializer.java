package com.mzy.service;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

public class MyZkSerializer implements ZkSerializer {

	@Override
	public byte[] serialize(Object data) throws ZkMarshallingError {
		try {
			return String.valueOf(data).getBytes("UTF-8");
		} catch (Exception e) {
			throw new ZkMarshallingError(e);
		}
	}

	@Override
	public Object deserialize(byte[] bytes) throws ZkMarshallingError {
		try {
			return new String(bytes,"UTF-8");
		} catch (Exception e) {
			throw new ZkMarshallingError(e);
		}
	}

}
