package ro.rmarius.tij.annotations.orm;

@DBTable(name = "MEMBER")
public class Member {

	@SqlString(lenght = 30, name = "FIRST_NAME", constraints = @Constraints(allowNull = false))
	private String firstName;

	@SqlString(lenght = 30, name = "LAST_NAME", constraints = @Constraints(allowNull = false))
	private String lastName;

	@SqlInteger(name = "AGE")
	private Integer age;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
