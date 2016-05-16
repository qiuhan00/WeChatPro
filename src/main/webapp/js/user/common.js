/**
 * 格式化easyui的日期格式
 * 
 */
function myformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}
function myparser(s) {
	if (!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}
/**
 * 序列化form表单的元素
 */
function getJson4Form(formId){
	var array = $('#'+formId).serializeArray();
	var obj = {};
	$.each(array,function(i,n){
		var name = n.name;
		var key = n.value;
		var oldVal= obj[name];
		if(oldVal==undefined || oldVal=="")
		{
			obj[name] = key;	
		}
		else
		{
			obj[name] = obj[name]+","+key;
		}
		
	});
	return obj;
}

/**
 * 扩展jquery实现序列化form表单对象
 * 调用示例:sy.serializeObject($("#searchForm").form())
 */
var sy = $.extend({}, sy);/*定义一个全局变量*/
sy.serializeObject = function (form) { /*将form表单内的元素序列化为对象，扩展Jquery的一个方法*/
    var o = {};
    $.each(form.serializeArray(), function (index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
};
