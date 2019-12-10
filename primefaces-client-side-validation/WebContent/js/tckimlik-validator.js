PrimeFaces.validator['TcKimlikNo'] = {
	throwError : function(detail) {
		throw {
			summary : 'Validation Error',
			detail : detail
		}
	},
	isValid : function(value) {
		if (value.match("^\\d{11}$")==null) {
			return false;
		}
		digits = new Array(11);
		for (i=0;i<digits.length;++i) {
			digits[i] = value.charCodeAt(i) - 48;
			if (digits[i] < 0 || digits[i] > 9) {
				return false;
			}
		}
		x = digits[0];
		y = digits[1];
		for (i = 1; i < 5; i++) {
			x += Number(digits[2 * i]);
		}
		for (i = 2; i <= 4; i++) {
			y += Number(digits[2 * i - 1]);
		}
		c1 = 7 * x - y;
		if (c1 % 10 != digits[9]) {
			return false;
		}
	    c2 = 0;
		for (i = 0; i < 10; ++i) {
			c2 += digits[i];
		}
		if (c2 % 10 != digits[10]) {
			return false;
		}
		return true;
	},
	validate : function(element, value) {
		if (value == undefined || value.length != 11) {
			this.throwError("This is not a valid identity no!")
		}
		if (!this.isValid(value)) {
			messageId = element.data('message');
			message = PrimeFaces.util.ValidationContext.getMessage(messageId).detail;
			this.throwError(message);
		}
	}
};