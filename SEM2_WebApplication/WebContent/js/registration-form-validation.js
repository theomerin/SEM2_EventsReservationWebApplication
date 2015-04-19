function formValidation()
	{
		var firstname = document.registration.firstname;
		var lastname = document.registration.lastname;
		var address = document.registration.address;
		var zip = document.registration.zip;
		var email = document.registration.email;
		var mobilenum = document.registration.mobilenum;



		if(allLetter(firstname))
		{

				if(allLetter(lastname))
				{
					if(alphanumeric(address))
					{

						if(allnumeric(zip))
						{
							if(ValidateEmail(email))
							{
								if(ValidateMobilenum(mobilenum))
								{
									if(validSubmit())
									{
									}
								}
							}
						}
					}
				}

		}
		return false;
	}

function allLetter(name)
{
	var letters = /^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/;
	if(name.value.match(letters))
	{
		return true;
	}
	else
	{
		alert('Name must have alphabet characters only');
		name.focus();
		return false;
	}
}

function alphanumeric(address)
{
	var letters = /^[0-9a-zA-Z-,]+(\s{0,1}[0-9a-zA-Z-, ])*$/;
	if(address.value.match(letters))
	{
		return true;
	}
	else
	{
		alert('Address must have alphanumeric characters only');
		address.focus();
		return false;
	}
}


function allnumeric(zip)
{
	var numbers = /^\d{4}$/;
	if(zip.value.match(numbers))
	{
		return true;
	}
	else
	{
		alert('Please enter a 4-digit ZIP code');
		zip.focus();
		return false;
	}
}

function ValidateEmail(email)
{
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(email.value.match(mailformat))
	{
		return true;
	}
	else
	{
		alert("You have entered an invalid email address!");
		email.focus();
		return false;
	}
}

function ValidateMobilenum(mobilenum)
{
	var mobileformat = /^\d{11}$/;
	if(mobilenum.value.match(mobileformat))
	{
		return true;
	}
	else
	{
		alert("Please enter a 11-digit mobile number");
		mobilenum.focus();
		return false;
	}
}

function validSubmit()
{
		alert('Succesfully Registered');
		window.location.reload()
		return true;
}

 function exitAlert()
  {
    if(confirm('Are you sure you want to cancel your registration?')) {
      return true;
    } else {
      if(window.event) {
        window.event.returnValue = false;
      } else {
        e.preventDefault();
      }
      return false;
    }
  }
