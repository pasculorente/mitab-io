package org.reactome.server.tools.mitab.io.model;

public enum PsiMitabVersion {TAB_25(15), TAB_26(36), TAB_27(42);

	private final int columns;

	PsiMitabVersion(int columns) {
		this.columns = columns;
	}

	public int getColumns() {
		return columns;
	}
}
