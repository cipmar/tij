package ro.rmarius.tij.annotations.orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlString {

	public int lenght() default 0;

	public String name() default "";

	public Constraints constraints() default @Constraints;
}
