PrimeFaces.locales['en'] = {
    messages : PrimeFaces.locales['en_US'].messages
};
 
$.extend(PrimeFaces.locales['en'].messages, {
	'{validation.email}':  'This is not a valid e-mail!',
	'{validation.iban}':  'This is not a valid IBAN!',
	'{validation.identityNo}':  'This is not a valid identity number!',
	'{validation.username1}':  'User name must start with letter!',
    '{validation.username2}':  'User name must be at least 5-char length!',
    '{validation.strongPassword1}': 'Password must be at least six chars!',
    '{validation.strongPassword2}': 'Must contain at least one digit!',
    '{validation.strongPassword3}': 'Must contain at least one character from the set -,_,$,.!'
});
