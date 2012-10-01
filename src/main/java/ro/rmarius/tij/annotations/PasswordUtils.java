package ro.rmarius.tij.annotations;

import java.lang.reflect.Method;
import java.util.List;

public class PasswordUtils {

	@UseCase(id = 34, description = "Password must contain at least one numeric")
	public boolean validatePassword(String password) {
		return password.matches("\\w*\\d\\w*");
	}

	@UseCase(id = 35, description = "Encrypt password")
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}

	@UseCase(id = 45)
	public boolean checkForNewPassword(List<String> previousPasswords, String newPassword) {
		return !previousPasswords.contains(newPassword);
	}

	public static void showUseCases(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();

		for (Method method : methods) {

			UseCase useCase = method.getAnnotation(UseCase.class);

			if (useCase != null) {
				System.out.println("Found use case " + useCase.id() + " " + useCase.description());
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		showUseCases(PasswordUtils.class);
	}
}
