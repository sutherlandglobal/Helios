

$.tablesorter.addParser({ 
	        // set a unique id 
	        id: 'excelDateSorter', 
	        is: function(s) { 
	            // return false so this parser is not auto detected 
	            return false; 
	        }, 
	        format: function(data) { 
	            // format your data for normalization 
	        	
	        	var normalized = data;
	        	var fields = data.split(" ");
	        	
	        	//12 hour date MM/DD/YYYY hh:MM:SS AMPM
	        	
	        	//return the normalized text, oddly enough is an sql date
	        	//YYYY-MM-DD HH:MM:SS
	        	if(fields.length == 3)
	        	{
	        		var dateFields = fields[0].split("/");
	        		var timeFields = fields[1].split(":");
	        		var hour = parseInt(timeFields[0]);
	        		
	        		if(fields[2] == "PM" && hour != 12)
	        		{
	        			hour = hour + 12;
	        		}
	        		else
	        		{
	        			if(hour < 10)
	        			{
	        				hour = "0" + hour;
	        			}
	        		}
	        		
	        		normalized = dateFields[2] + dateFields[0] + dateFields[1] + hour + timeFields[1] + timeFields[2];
	        	}
	        	
	            return normalized; 
	        }, 
	        // set type, either numeric or text 
	        type: 'text' 
	    }); 