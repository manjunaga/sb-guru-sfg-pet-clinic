package guru.springframework.sfgpetclinic.model;

public class Speciality extends BaseEntity {

	private static final long serialVersionUID = -1900857801577707939L;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
