PrimeFaces.validator['Iban'] = {
	MAX : 999999999,
	MODULUS : 97,
	throwError : function(detail) {
		throw {
			summary : 'Validation Error',
			detail : detail
		}
	},
	calculateModulus : function(code) {
		var reformattedCode = code.substring(4) + code.substring(0, 4);
		reformattedCode = reformattedCode.replace(/[A-Z]/g, function(match) {
			return match.charCodeAt(0) - 55;
		});
		var total = 0;
		for (i = 0; i < reformattedCode.length; i++) {
			charValue = reformattedCode.charCodeAt(i) - 48;
			if (charValue < 0 || charValue > 35) {
				return 0;
			}
			total = (Number(charValue) > 9 ? total * 100 : total * 10) + charValue;
			if (total < this.MAX) {
				total = (total % this.MODULUS);
			}
		}
		return total % this.MODULUS;
	},
	validate : function(element, value) {
		if (value == undefined || value.length < 5) {
			this.throwError("This is not a valid IBAN!")
		}
		modulusResult = this.calculateModulus(value);
		if (modulusResult != 1) {
			messageId = element.data('message');
			message = PrimeFaces.util.ValidationContext.getMessage(messageId).detail;
			this.throwError(message);
		}
	}
};