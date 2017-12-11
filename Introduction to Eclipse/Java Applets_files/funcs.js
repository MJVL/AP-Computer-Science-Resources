function download(id)
{
 var elt = document.getElementById(id);
 elt.submit();
}

function openpopup(url) {
  var features =
    'width=500,height=500,status=0,location=0,resizable=1,scrollbars=1';
  open( url, 'fileview', features );
}

function openpopup(url, name, width, height) {
  var features =
    'width=' + width + ',height=' + height 
             + ',status=0,location=0,resizable=1,scrollbars=1';
  open( url, name, features );
}
