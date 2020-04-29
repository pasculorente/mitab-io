package org.uichuimi.mitab.io.model;

public class Checksum extends Field {

	public Checksum(String method, String cheksum) {
		super(method, cheksum);
	}

	public Checksum(Field field) {
		super(field);
	}

	public String getMethod() {
		return super.getXref();
	}

	public String getChecksum() {
		return getValue();
	}
}
