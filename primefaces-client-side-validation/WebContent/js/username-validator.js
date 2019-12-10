PrimeFaces.validator['Username'] = {
	throwError : function(detail) {
		throw {
			summary : 'Validation Error',
			detail : detail
		}
	},
	validate : function(element, value) {
		if (value == undefined) {
			this.throwError("This is not a valid username!")
		}
		var noOfPatterns = Number(element.data('no-of-patterns'));
		patterns = [];
		messages = [];
		for (i = 1; i <= noOfPatterns; ++i) {
			patterns.push(element.data('pattern' + i));
			messageId = element.data('message' + i);
			message = PrimeFaces.util.ValidationContext.getMessage(messageId);
			messages.push(message.detail);
		}
		for (i in patterns) {
			pattern = patterns[i];
			var regex = new RegExp(pattern);
			if (!regex.test(value)) {
				this.throwError(messages[i]);
			}
		}
	}
};