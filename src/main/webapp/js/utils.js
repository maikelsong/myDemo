
//在用这个js文件的时候，把不需要的函数去掉


var Browser = new Object();

Browser.isMozilla = (typeof document.implementation != 'undefined') && (typeof document.implementation.createDocument != 'undefined') && (typeof HTMLDocument != 'undefined');
Browser.isIE = window.ActiveXObject ? true : false;
Browser.isFirefox = (navigator.userAgent.toLowerCase().indexOf("firefox") != - 1);
Browser.isSafari = (navigator.userAgent.toLowerCase().indexOf("safari") != - 1);
Browser.isOpera = (navigator.userAgent.toLowerCase().indexOf("opera") != - 1);



//随机得到一个0和nMaxNumber之间的整数
function rand(nMaxNumber)
{
	return Math.round(Math.random()*nMaxNumber);
}

//将一个文本串编码成XML文本串
function encodeToXMLText(strText)
{
	var strOutput="";
	var nLength=strText.length;
	for(var i=0;i<nLength;i=i+1)
	{
		var ch=strText.charAt(i);
		switch(ch)
		{
			case '&':
				strOutput+="&amp;";
				break;
			case '/':
				strOutput+="&#47;";
				break;
			case '\'':
				strOutput+="&#039;";
				break;
			case '>':
				strOutput+="&gt;";
				break;
			case '<':
				strOutput+="&lt;";
				break;
			case '\"':
				strOutput+="&quot;";
				break;
			case '\r':
				strOutput+="&#xd;";
				break;
			case '\n':
				strOutput+="&#xa;";
				break;
			default:
				strOutput+=ch;
		}
	}
	return strOutput;
}

//将一个文本串编码成HTML文本串
function encodeToHTMLText(strText)
{
	var strOutput="";
	var nLength=strText.length;
	for(var i=0;i<nLength;i=i+1)
	{
		var ch=strText.charAt(i);
		switch(ch)
		{
			case '&':
				strOutput+="&amp;";
				break;
			case '/':
				strOutput+="&#47;";
				break;
			case ' ':
				strOutput+="&nbsp;";
				break;
			case '\'':
				strOutput+="&#039;";
				break;
			case '>':
				strOutput+="&gt;";
				break;
			case '<':
				strOutput+="&lt;";
				break;
			case '\"':
				strOutput+="&quot;";
				break;
			case '\r':
				strOutput+="&#xd;";
				break;
			case '\n':
				strOutput+="&#xa;";
				break;
			default:
				strOutput+=ch;
		}
	}
	return strOutput;
}


	//去两边空格  
	String.prototype.trim=function(){  
	    return this.ltrim().rtrim();  
	}  
	//去左边空格  
	String.prototype.ltrim=function(){  
	    return this.replace(/(^\s*)/g,"");  
	}  
	//去右边空格  
	String.prototype.rtrim=function(){  
	    return this.replace(/(\s*$)/g,"");  
	}  


function getBrowserType() 
{ 
	var OsObject = ""; 

	if(navigator.userAgent.indexOf("MSIE")>=0)
	{ 
		return "MSIE"; //IE浏览器 
	} 
	if(navigator.userAgent.indexOf("Firefox")>=0)
	{ 
		return "Firefox"; //Firefox浏览器 
	} 
	if(navigator.userAgent.indexOf("Safari")>=0)
	{ 
		return "Safari"; //Safan浏览器 
	} 
	if(navigator.userAgent.indexOf("Camino")>=0)
	{ 
		return "Camino"; //Camino浏览器 
	} 
	if(navigator.userAgent.indexOf("Gecko/")>=0)
	{ 
		return "Gecko"; //Gecko浏览器 
	} 
}
//在js中 将文本内容格式化以符合HTML显示要求显示
String.prototype.FormatTextToHTML=function()
{
	return this.replace(/&gt;/g,">" ).replace(/&lt;/g,"<" ).replace(/&amp;/g,"&").replace(/&nbsp;/g," ")
	.replace(/&#47;/g,"/").replace(/&apos;/g,"'").replace(/&quot;/g,"\"").replace(/""/g,"\r");
}

//自定义统计字符数，一个中文算2个字符
String.prototype.getLength = function() {
	var arr = this.match(/[\u00FF-\uFFFF]/gi);
	if(!arr || arr == null)
		return this.length;
	var len = this.length + arr.length;
	return len;
}

//格式化文本内容(去掉换行)
function richTextInputEncoder(source)
{
	var resultStr = source;
	if (source == null)
		return source;
	resultStr = resultStr.replace(/[\r\n\t]/g,"");//去掉回车换行水平制表符
	
	return resultStr;
}



//获取浏览器上URL参数
function getUrlParms()    
{
	var args=new Object();   
	var query=location.search.substring(1);//获取查询串   
	var pairs=query.split("&");//在逗号处断开   
	for(var i=0;i<pairs.length;i++)
	{   
	    var pos=pairs[i].indexOf('=');//查找name=value   
	    if(pos==-1)continue;//如果没有找到就跳过   
	    var argname=pairs[i].substring(0,pos);//提取name   
	    var value=pairs[i].substring(pos+1);//提取value   
	    args[argname]=unescape(value);//存为属性   
	}
	return args;
}


  //设置隐藏参数值。利用在：隐藏参数需要获取浏览器上的参数
  function setHiddenValues(){
    	var args = getUrlParms();
    	if(args["source"]!=undefined && args["source"]!=''){$('#source').val(args["source"])};
    	if(args["expand"]!=undefined && args["expand"]!=''){$('#expand').val(args["expand"])};
    	
    	if(document.referrer!=self.location.href)document.getElementById('refurl').value=document.referrer;
  	    document.getElementById('cururl').value=self.location.href;
    }
    

	//检测是否为中文字符
	function isChineseString(val)
	{
        var reg = /^[\u0391-\uFFE5]+$/;
        if (val.match(reg)) {
            return true;
        }
        else {
            return false;
        }
    }
	
	//检测是否为手机号码
	function isMobile(sMobile)
	{
		if(sMobile==''){
			return false;
		}
		if(! ((/^1[3|4|5|8][0-9]\d{4,8}$/.test(sMobile)) && sMobile.length==11 )){
			return false;
		}
		return true;
	}  
	
	
	//检测是否为邮箱
	function isEmail(val)
	{
		if(val.lastIndexOf('.')>0){
			var t = val.substring(val.lastIndexOf('.')+1,val.length);
			if( ! (t=='com' || t=='cn' || t=='org' || t=='net') )
				 return false;
		}
		
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,4}$/;
		if(!myreg.test(val)){
			return false;
		}
		return true;
	}


	//把分 转成钱格式
	function integerToMoney(nMoney)
	{
		var strYuan	=nMoney/100;
		var nCent	=nMoney%100;
		
		if(nCent<10)
		{
			return ""+strYuan+".0"+nCent
		}
		else
		{
			return ""+strYuan+"."+nCent
		}
	}




	//检测是否为数字
	Utils.isNumber = function(val)
	{
	  var reg = /^[\d|\.|,|-]+$/;
	  return reg.test(val);
	}
	
	
	//检测是否为时间
	Utils.isTime = function(val)
	{
	  var reg = /^\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}$/;
	
	  return reg.test(val);
	}
	
	
	//获取cookie
	Utils.getCookie = function(name) 
	{
	    var bikky = document.cookie;
	    name += "=";
	    var i = 0; 
	    while (i < bikky.length) 
	    {
	      var offset = i + name.length;
	      if (bikky.substring(i, offset) == name) 
	      { 
	        var endstr = bikky.indexOf(";", offset); 
	        if (endstr == -1) endstr = bikky.length;
	          return unescape(bikky.substring(offset, endstr)); 
	      }
	        i = bikky.indexOf(" ", i) + 1; 
	        if (i == 0) break; 
	    }
	    return null; 
	}
	
	
	//统计字符
	Utils.wordCount = function(_obj, _max){
		var _total = _obj.value.mylength();
		if(_total > _max) {
			_obj.value = _obj.value.mysubstring2(_max);
		}
	}
	
	
	
	//获取兼容性事件
	Utils.getEvent = function()
	{   
		if(document.all) {
			return window.event;//如果是ie
		}   
		func=getEvent.caller;   
		while(func!=null) {   
			var arg0=func.arguments[0];   
			if(arg0) {   
				if((arg0.constructor==Event || arg0.constructor ==MouseEvent) || (typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation)){   
					return arg0;   
				}               
			}   
			func=func.caller;   
		}   
		return null;   
	}
	 
	 
	 
Utils.fixEvent = function(e)
{
  var evt = (typeof e == "undefined") ? window.event : e;
  return evt;
}

Utils.srcElement = function(e)
{
  if (typeof e == "undefined") e = new Object;
  var src = document.all ? e.srcElement : e.target;
  return src;
}



/////////////////////////////////////////////////////////


//拖拽
//对“拖动点”定义：onMousedown="StartDragTip(this)" onMouseup="StopDragTip(this)" onMousemove="DragTip(this)"即可
var tipMove=false,start_X,start_Y;

function StartDragWin(obj, winId){  //定义准备拖拽的函数
	var event=getEvent();
	//同时兼容ie和ff的写法
	if (!window.captureEvents) {
		obj.setCapture(); //对当前对象的鼠标动作进行跟踪
	}else {
		window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
	}
	tipMove=true;
	//获取鼠标相对内容层坐标
	var pos=jQuery('#'+winId).position();
	jQuery('#'+winId).fadeTo(0,0.8);
	
	start_X=pos.left-event.clientX;
	start_Y=pos.top-event.clientY;
}

function DragWin(obj, winId){        //定义拖拽函数
	var event=getEvent();
	if(tipMove){
		var left_x;
		var top_y;
		if(event.clientX+start_X < 0) {
			left_x = 5;
		} else if (event.clientX+start_X > document.documentElement.clientWidth-jQuery('#'+winId).width()-15) {
			left_x = document.documentElement.clientWidth-jQuery('#'+winId).width()-15;
		} else {
			left_x = event.clientX+start_X;
		}
		if(event.clientY+start_Y < document.documentElement.scrollTop) {
			top_y = document.documentElement.scrollTop;
		} else if (event.clientY+start_Y > document.documentElement.scrollTop+document.documentElement.clientHeight-jQuery('#'+winId).height()-20) {
			top_y = document.documentElement.scrollTop+document.documentElement.clientHeight-jQuery('#'+winId).height()-30;
		} else {
			top_y = event.clientY+start_Y;
		}
		jQuery('#'+winId).css("left",left_x);
		jQuery('#'+winId).css("top",top_y);
	}
}

function StopDragWin(obj, winId){   //定义停止拖拽函数
	//同时兼容ie和ff的写法
	if (!window.captureEvents) {
		obj.releaseCapture(); //停止对当前对象的鼠标跟踪
	}else {
		window.releaseEvents(Event.MOUSEMOVE|Event.MOUSEUP);
	}
	jQuery('#'+winId).fadeTo(0,1);
	tipMove=false;
}



//////////////////////////////////////////////////////////////


function createCookie(name, value, days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }
    else var expires = "";
    document.cookie = name+"="+value+expires+"; path=/";
}

function getCookie(sVar){
	cookies = document.cookie.split('; ');
	for(var i = 1; i <= cookies.length; i++){
		if(cookies[i - 1].split('=')[0] == sVar){
		 return cookies[i - 1].split('=')[1];
		}
	}
	return '';
}

function removeCookie(name) {
    createCookie(name,"",-1);
}