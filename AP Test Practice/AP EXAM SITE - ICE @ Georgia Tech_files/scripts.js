/**
 * @author Manas
 */
function echeck(str) {
        //alert("in echeck");
		var at="@";
		var dot=".";
		var lat=str.indexOf(at);
		var lstr=str.length;
		var ldot=str.indexOf(dot);
		if (str.indexOf(at)==-1){
			//alert("no at1");
			//alert("client is"+document.getElementById("clienttp"));
			//alert("server is"+document.getElementById("servertp"));
			document.getElementById("clienttp").innerHTML = "<p>Invalid E-mail ID</p>";
			//alert("no at2");
		   return false;
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		   document.getElementById("clienttp").innerHTML = "<p>Invalid E-mail ID</p>";
		   return false;
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    document.getElementById("clienttp").innerHTML = "<p>Invalid E-mail ID</p>";
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    document.getElementById("clienttp").innerHTML = "<p>Invalid E-mail ID</p>";
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    document.getElementById("clienttp").innerHTML = "<p>Invalid E-mail ID</p>";
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		    document.getElementById("clienttp").innerHTML = "<p>Invalid E-mail ID</p>";
		    return false
		 }
		
		 if (str.indexOf(" ")!=-1){
		    document.getElementById("clienttp").innerHTML = "<p>Invalid E-mail ID</p>";
		    return false
		 }

 		 return true					
	}

function validateForm(emailID)
{   var x = true;
	var validDiv;
	//alert("in validateForm");
	try{ validDiv = document.getElementById("clienttp");
	     //serverValidDiv = document.getElementById("servertp");
	     //serverValidDiv.innerHTML = "";
	    validDiv.innerHTML = "";   }
	catch(e)
	{//alert(e);return false;}
	//var emailID=document.getElementById("userID");
	//alert("in validateForm and emailID= "+emailID.value);
	if ((emailID.value==null)||(emailID.value==""))
	{   //alert("in if");
	    
		//alert("null/empty");
		document.getElementById("clienttp").innerHTML = "<p>Please Enter your username</p>";
		//alert("1");
		emailID.focus();
		x =  false;
	}
	
	else if (echeck(emailID.value) == false)
	{
		
		//document.getElementById("errorSpan").innerHTML = "value check";
		emailID.value="";
		emailID.focus();
		x = false;
	}
	
	
    
	return x;
}


function checkForAjax()
{
	var xmlHttp;
  	try
    {
    	// Firefox, Opera 8.0+, Safari
    	xmlHttp=new XMLHttpRequest();
    }
 	 catch (e)
    {
    	// Internet Explorer
    	try
      	{
      		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
      	}
    	catch (e)
      	{
      		try
        	{
        		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        	}
      		catch (e)
        	{
        		alert("Your browser does not support AJAX!");
        		return false;
        	}
      	}
    }
	
	return xmlHttp;
}
function showLoginLinks()
{
	
}
function showStudentLinks()
{   alert("in showLinks()");
	document.getElementById('studentDiv').innerHTML="<table width='800' border='0' align='center' cellpadding='0' cellspacing='0' bordercolor='#000000'><tr><td width='107' align='center' background='images/index_02.gif'><a href='begin_test.jsp'>Take Test</a></td><td width='110' align='center' background='images/index_02.gif'><a href='teacher_contact.jsp'>Contact A Teacher</a></td><td width='180' align='center' background='images/index_02.gif'><a href='continue_test.jsp'>Statistics</a></td><td width='141' align='center' background='images/index_02.gif'><a href='rates.php'>Rates</a></td><td width='118' align='center' background='images/index_02.gif'><a href='bookings.php'>Bookings</a></td><td width='130' height='31' align='center' background='images/index_02.gif'><a href='progress.jsp'>Check My Progress</a></td><td width='118' align='center' background='images/index_02.gif'><a href='student_logout.jsp'>Logout</a></td></tr></table>";
	
	/*var sPath = window.location.pathname;
	var sPage = sPath.substring(sPath.lastIndexOf('/') + 1);
	    
		if(sPage=='edit_corporate.php')
		{ 
		   	getCorporateUsers();
		}
		if(sPage=='rates.php')
		{ 
		   	getRates();
			getPickRates();
		}		
		if(sPage=='edit_Announce.php')
		{ getAnnouncements();
			
		}
		else
		{  
			showYearwise();
		}*/
	
		
}
 function updateAnn(ann_id)
  
  { 
  var editid='edit'+ann_id;
  var updateid='update'+ann_id;
  var d=new Date();
  	document.getElementById(editid).style.visibility='visible'; 
	document.getElementById(updateid).style.visibility='hidden';
	document.getElementById(ann_id).readOnly=true;
	document.getElementById(ann_id).style.backgroundColor="#ffffff";
	updateAnnounce(ann_id);
  }
  function updateAnnounce(ann_id)
  {
  	
  var xmlHttp=checkForAjax();
  xmlHttp.onreadystatechange=function()
      {
      if(xmlHttp.readyState==4)
        { 
        
        }
      }	
	  
	  var changeText=document.getElementById(ann_id).value;
	  
	  
	  	xmlHttp.open("GET",'updateAnnounce.php?ann_id='+ann_id+'&announcement='+changeText,true);
	  
	    xmlHttp.send(null);  
  }
  
  function deleteAnn(ann_id)
  { 
   if(confirm('Are you sure?')){
   var xmlHttp=checkForAjax();
  xmlHttp.onreadystatechange=function()
      {
      if(xmlHttp.readyState==4)
        { getAnnouncements();
        
        }
      }	
	 
	  //var changeText=document.getElementById(ann_id).value;
	  
	  
	  	xmlHttp.open("GET",'deleteAnnounce.php?ann_id='+ann_id,true);
	  
	    xmlHttp.send(null);  
		}
  }
  function checkUserName()
  {
	  
  }
  function showLinks()
  {
	  document.getElementId('navigationLinks').innerHTML = "<a href='logout.jsp'>Back To Main Menu</a><a href='logout.jsp'>Logout</a>";
  }