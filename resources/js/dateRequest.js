function getDate()
{
	var currentTime = new Date();
	var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	var seconds = currentTime.getSeconds()
	
	if(hours < 10)
	{
		hours = "0" + hours;
	}
	if(minutes < 10)
	{
		minutes = "0" + minutes;
	}
	if(seconds < 10)
	{
		seconds = "0" + seconds;
	}
	
	return year + "-" + month + "-" + day +" " + hours + ":" + minutes + ":" + seconds;	
}