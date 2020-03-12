package com.daurada.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IconEntity extends BasicEntity {
	
	private static final long serialVersionUID = 2384692047684518230L;
	
	@Column(name="icon_path", length=255)
	private String iconPath;
	
	public String getIconPath() {
		return iconPath;
	}
	
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

}
