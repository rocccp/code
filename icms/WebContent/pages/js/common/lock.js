function setTime() {
	var day = "";
	var month = "";
	var ampm = "";
	var ampmhour = "";
	var myweekday = "";
	var year = "";
	var myHours = "";
	var myMinutes = "";
	var mySeconds = "";
	mydate = new Date();
	myweekday = mydate.getDay();
	mymonth = parseInt(mydate.getMonth() + 1) < 10 ? "0"
			+ (mydate.getMonth() + 1) : mydate.getMonth() + 1;
	myday =parseInt( mydate.getDate())<10?"0"+ mydate.getDate(): mydate.getDate();
	myyear = mydate.getYear();
	myHours = parseInt(mydate.getHours())<10?"0"+ mydate.getHours(): mydate.getHours();
	myMinutes =parseInt( mydate.getMinutes())<10?"0"+ mydate.getMinutes(): mydate.getMinutes();;
	mySeconds = parseInt(mydate.getSeconds()) < 10 ? "0" + mydate.getSeconds()
			: mydate.getSeconds();
	year = (myyear > 200) ? myyear : 1900 + myyear;
	if (myweekday == 0)
		weekday = " 星期日 ";
	else if (myweekday == 1)
		weekday = " 星期一 ";
	else if (myweekday == 2)
		weekday = " 星期二 ";
	else if (myweekday == 3)
		weekday = " 星期三 ";
	else if (myweekday == 4)
		weekday = " 星期四 ";
	else if (myweekday == 5)
		weekday = " 星期五 ";
	else if (myweekday == 6)
		weekday = " 星期六 ";
	datetime.innerText = year + "年" + mymonth + "月" + myday + "日 " + myHours
			+ ":" + myMinutes + ":" + mySeconds + " " + weekday;
	setTimeout("setTime()", 1000);
}