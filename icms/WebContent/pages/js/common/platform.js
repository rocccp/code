function isValidEmailAddress(emailAddress) {
	var pattern = new RegExp(
			/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
	return pattern.test(emailAddress);
}

function checkNullObject(title) {
	this.title = title;
}

function checkNull(objs) {
	for ( var i = 0; i < objs.size(); i++) {

		if (objs[i].value == "" || objs[i].value == undefined) {
			return new checkNullObject(objs[i].attributes['title'].nodeValue);
		}
	}
	return null;
}

function appendUrl(objs, appendURLStr) {
	for ( var i = 0; i < objs.size(); i++) {

		if (objs[i].value != null && objs[i].value != undefined
				&& trim(objs[i].value) != "") {
			if (appendURLStr != "") {
				appendURLStr = appendURLStr + "&" + objs[i].name + "="
						+ objs[i].value;
			} else {
				appendURLStr = appendURLStr + objs[i].name + "="
						+ objs[i].value;
			}

		}

	}
	return appendURLStr;
}

function initAdd(objs) {
	for ( var i = 0; i < objs.size(); i++) {
		if (objs[i].value != "" && objs[i].value != null
				&& objs[i].value != undefined) {
			objs[i].value = "";
		}
	}
}

function trim(str) { // 删除左右两端的空格
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function ltrim(str) { // 删除左边的空格
	return str.replace(/(^\s*)/g, "");
}
function rtrim(str) { // 删除右边的空格
	return str.replace(/(\s*$)/g, "");
}

function initTalbe() {
	var cth = $('<td/>');
	var cthch = $('<input type="checkbox"/>');
	cthch.click(function() {
				if (this.checked) {
					$('tbody tr', g.bDiv)
							.each(
									function() {
										$(this).addClass('trSelected').find(
												'input')[0].checked = true;
									})
				} else {

					$('tbody tr', g.bDiv)
							.each(
									function() {
										$(this).removeClass('trSelected').find(
												'input')[0].checked = false;
									})
				}
			})

	cth.addClass("cth").attr({
		width : "22"
	}).append(cthch);
	$(this).prepend(cth);
}
