package com.example.web.validation;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.metadata.ConstraintDescriptor;

import org.primefaces.validate.bean.ClientValidationConstraint;

public class StrongPasswordClientValidator implements ClientValidationConstraint {
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getMetadata(ConstraintDescriptor constraintDescriptor) {
		int patternNo = 1, numberOfPatterns = 0;
		Map<String, Object> metadata = new HashMap<String, Object>();
		for (Object o : constraintDescriptor.getComposingConstraints()) {
			if (o instanceof ConstraintDescriptor) {
				ConstraintDescriptor cd = (ConstraintDescriptor) o;
				Annotation annotation = cd.getAnnotation();
				if (annotation instanceof Pattern) {
					Pattern pattern = (Pattern) annotation;
					String patternNoAsString = Integer.toString(patternNo);
					metadata.put("data-pattern".concat(patternNoAsString), pattern.regexp());
					metadata.put("data-message".concat(patternNoAsString), pattern.message());
					patternNo++;
					numberOfPatterns++;
				} else if (annotation instanceof Size) {
					Size size = (Size) annotation;
					metadata.put("data-size-min", size.min());
					metadata.put("data-size-max", size.max());
					metadata.put("data-size-message", size.message());
				}
			}
		}
		metadata.put("data-no-of-patterns", numberOfPatterns);
		return metadata;
	}

	@Override
	public String getValidatorId() {
		return StrongPassword.class.getSimpleName();
	}

}
