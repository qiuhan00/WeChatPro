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
/** 格式化金额 s-待格式化字段 n-保留小数位 */
function fmoney(s, n) {
	n = n >= 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse();
	var r = n > 0 ? s.split(".")[1] : '';
	t = "";
	for (var i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	var value = t.split("").reverse().join("");
	if (n > 0) {
		value += "." + r;
	}
	return value;
}
function focusEditor(tableId,field,editIndex){
	var editor = $('#'+tableId).datagrid('getEditor', {index:editIndex,field:field});
	if (editor){
		editor.target.focus();
	} else {
		var editors = $('#'+tableId).datagrid('getEditors', editIndex);
		if (editors.length){
			editors[0].target.focus();
		}
	}
}

/*获取可编辑表格所有更改的数据,返回json对象*/
function ToSaveJson(gridId){
	var inserted = $('#'+gridId).datagrid('getChanges','inserted');
	var updated = $('#'+gridId).datagrid('getChanges','updated');
	var deleted = $('#'+gridId).datagrid('getChanges','deleted');
	var result ={'_inserted':inserted,'_updated':updated,'_deleted':deleted};
	return result;
}

/*获取可编辑表格所有更改的数据,返回格式为后台接收需要的格式*/
function ToSaveParam(gridId,parentId){
	var result = ToSaveJson(gridId);
	result = JSON.stringify(result);
	var param = {'_easy_grid':result};
	if(parentId){
		param = {'_easy_grid':result,'parentId':parentId};
	}
	return param;
}

function endEditing() {
	if (editIndex == undefined) {
		return true;
	}
	if ($('#tt').datagrid('validateRow', editIndex)) {
		$('#tt').datagrid('endEdit', editIndex);
		editIndex = undefined;
		var rows = $("#tt").datagrid("getRows");
		for ( var i = 0; i < rows.length; i++) {
			$('#tt').datagrid('endEdit', i);
		}
		return true;
	} else {
		return false;
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
