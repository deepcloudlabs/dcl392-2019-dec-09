package com.example.web.validation;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.validation.metadata.ConstraintDescriptor;

import org.primefaces.validate.bean.ClientValidationConstraint;

public class TcKimlikNoClientValidator implements ClientValidationConstraint {

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getMetadata(ConstraintDescriptor constraintDescriptor) {
		Map<String, Object> metadata = new HashMap<String, Object>();
		for (Object cd : constraintDescriptor.getComposingConstraints()) {
			if (cd instanceof ConstraintDescriptor) {
				Annotation annotation = ((ConstraintDescriptor) cd).getAnnotation();
				if (annotation instanceof TcKimlikNo) {
					metadata.put("data-message", ((TcKimlikNo) annotation).message());
				}
			}
		}
		return metadata;
	}

	@Override
	public String getValidatorId() {
		return TcKimlikNo.class.getSimpleName();
	}

}
