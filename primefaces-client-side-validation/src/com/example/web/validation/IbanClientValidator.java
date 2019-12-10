package com.example.web.validation;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.validation.metadata.ConstraintDescriptor;

import org.primefaces.validate.bean.ClientValidationConstraint;

@SuppressWarnings("rawtypes")
public class IbanClientValidator implements ClientValidationConstraint {

	@Override
	public Map<String, Object> getMetadata(ConstraintDescriptor constraintDescriptor) {
		Map<String,Object> metadata = new HashMap<String, Object>();
		for (Object cd : constraintDescriptor.getComposingConstraints()) {
			if (cd instanceof ConstraintDescriptor) {
				Annotation annotation = ((ConstraintDescriptor) cd).getAnnotation();
		        if (annotation instanceof Iban){
		        	metadata.put("data-message", ((Iban) annotation).message());
		        }
			}
		}
		return metadata;
	}

	@Override
	public String getValidatorId() {
		return Iban.class.getSimpleName();
	}

}
