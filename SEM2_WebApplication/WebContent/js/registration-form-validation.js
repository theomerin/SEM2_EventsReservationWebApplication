function alphanumeric(uadd) {
	var letters = /^[0-9a-zA-Z-,]+(\s{0,1}[0-9a-zA-Z-, ])*$/;
	if (uadd.value.match(letters)) {
		return true;
	} else {
		alert('Address must have alphanumeric characters only');
		uadd.focus();
		return false;
	}
}

function allLetter(name) {
	var letters = /^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/;
	if (name.value.match(letters)) {
		return true;
	} else {
		alert('Name must have alphabet characters only');
		name.focus();
		return false;
	}
}

function formValidation() {

	var firstname = document.registration.firstname;
	var lastname = document.registration.lastname;
	var uadd = document.registration.address;
	var uzip = document.registration.zip;
	var uemail = document.registration.email;

	if (allLetter(firstname)) {

		if (allLetter(lastname)) {

			if (alphanumeric(uadd)) {

				if (allnumeric(uzip)) {
					if (ValidateEmail(uemail)) {
						if (validSubmit()) {

						}
					}
				}
			}
		}

	}
	return false;
}

function allnumeric(uzip) {
	var numbers = /^\d{4}$/;
	if (uzip.value.match(numbers)) {
		return true;
	} else {
		alert('Please enter a 4-digit ZIP code');
		uzip.focus();
		return false;
	}
}

function ValidateEmail(uemail) {
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if (uemail.value.match(mailformat)) {
		return true;
	} else {
		alert("You have entered an invalid email address!");
		uemail.focus();
		return false;
	}
}

function ValidateMobilenum(mobilenum) {
	var mailformat = /^\d{11}$/;
	if (mobilenum.value.match(mailformat)) {
		return true;
	} else {
		alert("Please input a 11-digit mobile number");
		mobilenum.focus();
		return false;
	}
}

function validSubmit() {
	alert('Succesfully Registered');
	window.location.reload()
	return true;
}

function exitAlert() {
	if (confirm('Are you sure you want to cancel your registration?')) {
		return true;
	} else {
		if (window.event) {
			window.event.returnValue = false;
		} else {
			e.preventDefault();
		}
		return false;
	}
}

function detectSeat(x) {
	document.getElementByID("seatNumber").innerHTML = x;
}
