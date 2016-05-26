NS4 = (document.layers) ? 1 : 0;
IE4 = (document.all) ? 1 : 0;
ver4 = (NS4 || IE4) ? 1 : 0;

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr;
  for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document;
  if(d.images){
    if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments;
    for(i=0; i<a.length; i++)
      if (a[i].indexOf("#")!=0) { d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}
  }
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;
  if(!d) d=document;
  if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document;
    n=n.substring(0,p);
  }
  if(!(x=d[n])&&d.all) x=d.all[n];
  for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++)
    x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById)
    x=d.getElementById(n);
  return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments;
  document.MM_sr=new Array;
  for(i=0;i<(a.length-2);i+=3)
  if ((x=MM_findObj(a[i]))!=null){
    document.MM_sr[j++]=x;
    if(!x.oSrc) x.oSrc=x.src;
    x.src=a[i+2];
  }
}

if (ver4) {
    with (document) {
        write("<STYLE TYPE='text/css'>");
        if (NS4) {
            write(".parent {position:absolute; visibility:visible}");
            write(".child {position:absolute; visibility:visible}");
            write(".regular {position:absolute; visibility:visible}")
        }
        else {
            write(".child {display:none}")
        }
        write("</STYLE>");
    }
}

function getIndex(el) {
    ind = null;
    for (i=0; i<document.layers.length; i++) {
        whichEl = document.layers[i];
        if (whichEl.id == el) {
            ind = i;
            break;
        }
    }
    return ind;
}

function arrange() {
    nextY = document.layers[firstInd].pageY + document.layers[firstInd].document.height;
    for (i=firstInd+1; i<document.layers.length; i++) {
        whichEl = document.layers[i];
        if (whichEl.visibility != "hide") {
            whichEl.pageY = nextY;
            nextY += whichEl.document.height;
        }
    }
}

function initIt(){
    if (!ver4) return;
    if (NS4) {
        for (i=0; i<document.layers.length; i++) {
            whichEl = document.layers[i];
            if (whichEl.id.indexOf("Child") != -1) whichEl.visibility = "hide";
       }
        arrange();
    }
    else {
        divColl = document.all.tags("DIV");
		var j=1;
        for (i=0; i<divColl.length; i++) {
          whichEl = divColl(i);
		  if (whichEl.className == "parent"){
		    var o = null;
			while(null == o) {
			  try{
				//alert("initIt begin:" + j);
				o=eval("KB"+j+"P");
    		    //alert("initIt middle:" + j);			      
				o.width="16";
				o.style.visibility="visible";
				o=eval("KB"+j+"O");
				o.width="0";
				o=eval("KB"+j+"Q");
				o.width="16";
				o.style.visibility="visible";
				o=eval("KB"+j+"L");
				o.width="0";
				//alert("initIt end:" + j);					      
			  } catch(e) {					  	
			  }
			  j=j+1;
			}
		  }
          if (whichEl.className == "child") whichEl.style.display = "none";
        }
    }
}

function expandIt(el) {
    if (!ver4) return;
    if (IE4) {
        whichEl = eval(el + "Child");
		whichP = eval(el+"P");
		whichO = eval(el+"O");
		whichQ = eval(el+"Q");
		whichL = eval(el+"L");
        if (whichEl.style.display == "none") {
			whichP.width="0";
			whichO.width="16";
			whichQ.width="0";
			whichL.width="16";
			whichEl.style.display = "block";
			whichO.style.visibility="visible";
			whichL.style.visibility="visible";
			var j=1;
			try {				
            	whichEll = eval(el + j + "Parent");
            	while (whichEll != null) {
            		try{
			          	whichPl = eval(el + j + "P");
			          	whichOl = eval(el + j + "O");
			          	whichQl = eval(el + j + "Q");
			          	whichLl = eval(el + j + "L");
			          	whichOl.width="0";
				        whichPl.width="16";
				        whichLl.width="0";
				        whichQl.width="16";			        
				        whichPl.style.visibility="visible";
				        whichQl.style.visibility="visible"; 
			        }catch(e) {			        	
			        }
			        whichEll.style.display = "block";
			        j = j+1;		        
			        try {
                		whichEll = eval(el + j + "Parent");
			        } catch (e) {
			          	break;
			        }
			    }
			} catch (e) {
			}
        } else {
			whichO.width="0";
			whichP.width="16";
			whichL.width="0";
			whichQ.width="16";
			whichP.style.visibility="visible";
			whichQ.style.visibility="visible";
          	whichEl.style.display = "none";
        }
    } else {
        whichEl = eval("document." + el + "Child");
        if (whichEl.visibility == "hide") {
            whichEl.visibility = "show";
        }else {
            whichEl.visibility = "hide";
        }
        arrange();
    }
}