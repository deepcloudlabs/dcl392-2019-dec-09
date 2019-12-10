package com.example.web.validation;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Pattern;
import javax.validation.metadata.ConstraintDescriptor;

import org.primefaces.util.HTML;
import org.primefaces.validate.bean.ClientValidationConstraint;

public class EmailClientValidator implements ClientValidationConstraint {

    private static final String MESSAGE_METADATA = "data-p-pattern-msg";
    private static final String MESSAGE_ID = "{javax.validation.constraints.Pattern.message}";
    
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getMetadata(ConstraintDescriptor constraintDescriptor) {
		for (Object o : constraintDescriptor.getComposingConstraints()) {
			if (o instanceof ConstraintDescriptor) {
				ConstraintDescriptor cd = (ConstraintDescriptor) o;
				Map<String,Object> metadata = new HashMap<String, Object>();
		        Map attrs = cd.getAttributes();
		        Object message = attrs.get("message");
		        
		        metadata.put(HTML.VALIDATION_METADATA.PATTERN, attrs.get("regexp"));
		        
		        if(!message.equals(MESSAGE_ID)) {
		            metadata.put(MESSAGE_METADATA, "You must enter a valid e-mail!");
		        }
		        
		        return metadata;

			}
		}
		return null;
	}

	@Override
	public String getValidatorId() {
		return Pattern.class.getSimpleName();
	}

}
