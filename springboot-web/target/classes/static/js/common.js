function changeToPostMethod(toPage, ele) {
	var myForm = document.createElement('form');
	myForm.method = 'post';
	myForm.action = toPage;
	for (var e in ele) { //json循环
		var myInput = document.createElement('input');
		myInput.name = e;
		myInput.value = ele[e];
		myForm.appendChild(myInput);
	}
	document.body.appendChild(myForm);
	myForm.submit();
	document.body.removeChild(myForm);
}