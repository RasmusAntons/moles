package de.rasmusantons.moles;

public class MoleInfo {
	private Kit kit;

	public Kit getKit() {
		return kit;
	}

	public void setKit(Kit kit) {
		this.kit = kit;
	}

	public boolean hasKit() {
		return kit != null;
	}
}
