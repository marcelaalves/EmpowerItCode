package bean;

import java.io.Serializable;

public class Wob  implements  Serializable{

	private String name;
	private String urlImage;
	private String description;
	private String localization;

	public Wob(String name, String urlImage, String descritption,
			String localization) {
		this.setName(name);
		this.setUrlImage(urlImage);
		this.setDescription(descritption);
		this.setLocalization(localization);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

}
