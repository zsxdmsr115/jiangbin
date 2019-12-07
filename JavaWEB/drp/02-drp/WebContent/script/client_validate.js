function isEmpty(s) {

}
// 删除字符串左边的空格
function ltrim(str) {
	if (str.length == 0)
		return (str);
	else {
		var idx = 0;
		while (str.charAt(idx).search(/\s/) == 0)
			idx++;
		return (str.substr(idx));
	}
}

// 删除字符串右边的空格
function rtrim(str) {
	if (str.length == 0) {
		return str;
	} else {
		var idx = str.length - 1;
		while (str.charAt(idx).search(/\s/)==0) {
			idx--;
		}
		return str.substring(0, idx + 1);
	}
}
// 删除字符串左右两边的空格
function trim(str) {
	return (rtrim(ltrim(str)));
}
//校验是否是Email
function isEmail(eml) {
	if(trim(eml)!='') {
	  var re=new RegExp("@[\\w]+(\\.[\\w]+)+$");
	  return(re.test(eml));
	}
	else
	  return(true);
}