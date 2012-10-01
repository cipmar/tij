package ro.rmarius.tij.annotations.orm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DBUtil {

	public void saveBean(Object bean) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException,
			SecurityException, InvocationTargetException {
		DBTable dbTable = bean.getClass().getAnnotation(DBTable.class);

		if (dbTable == null) {
			throw new RuntimeException("Annotation DBTable not found!");
		}

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(dbTable.name());
		sb.append(" (");

		// fields
		Field[] fields = bean.getClass().getDeclaredFields();

		for (Field field : fields) {

			String fieldName = field.getName();
			Method method = bean.getClass().getMethod(
					"get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length()));

			SqlInteger sqlInteger = field.getAnnotation(SqlInteger.class);

			if (sqlInteger != null) {
				sb.append(sqlInteger.name());
				sb.append(" ==> ");
				Integer result = (Integer) method.invoke(bean);
				sb.append(result);
			}

			SqlString sqlString = field.getAnnotation(SqlString.class);

			if (sqlString != null) {
				sb.append(sqlString.name());
				sb.append(" length ");
				sb.append(sqlString.lenght());
				sb.append(" ==> ");

				String result = (String) method.invoke(bean);
				sb.append(result);
			}

			sb.append(", ");
		}

		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");

		System.err.println(sb.toString());
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, SecurityException, InvocationTargetException {
		Member member = new Member();

		member.setFirstName("Mihai");
		member.setLastName("Popescu");
		member.setAge(23);

		DBUtil dbUtil = new DBUtil();
		dbUtil.saveBean(member);
	}
}
