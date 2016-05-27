var setting = {
	view : {
		dblClickExpand : false,
		showLine : true,
		selectedMulti : false
	},
	data : {
		//使用简单数据类型，也就是数组
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "parentId",
			rootPId : 0
		}
	},
	callback:{
		//beforeClick: this.beforeClick,
		onClick:openNew  //绑定菜单单击事件
	}
};
var zNodes;
var treeObj;
var curMenu = null
$(function() {
	//后台获取菜单项json数据
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : path + "/login/loadMenu",// 请求的action路径
		error : function() {// 请求失败处理函数
			alert('请求失败');
		},
		success : function(data) { // 请求成功后处理函数。
			if(null == data || data==""){
				$("#tree").html("<h2>无权限资源</h2>");
				return;
			}
			$.fn.zTree.init($("#tree"), setting, data); // 把后台封装好的简单Json格式赋给
			treeObj = $.fn.zTree.getZTreeObj("tree");//获取id为tree的zTree的对象
			
			/*curMenu = treeObj.getNodes()[0].children[0];
			treeObj.selectNode(curMenu);
			var a = $("#" + treeObj.getNodes()[0].tId + "_a");
			a.addClass("cur");*/
		}
	});
	
	//绑定tabs的右键菜单
    $("#tabs").tabs({
        onContextMenu : function (e, title) {
            e.preventDefault();//去除默认的右键点击事件
            $('#tabsMenu').menu('show', {
                left : e.pageX,
                top : e.pageY
            }).data("tabTitle", title);
        }
    });
    
    //设置关闭事件
    $("#tabsMenu").menu({
        onClick : function (item) {
        	closeTabs(this, item.name);
        }
    });
})

//center新增tab
function openNew(event,treeId,treeNode){
	var tabName = treeNode.name;
	if(treeNode.click == false){
		return;
	}
	if($("#tabs").tabs("exists",tabName)){
		$("#tabs").tabs("select",tabName);
	}else{
		var url;
		if(treeNode.resourceURL.indexOf("www")!=-1){
			url = treeNode.resourceURL ;
		}else{
			url = path + treeNode.resourceURL ;
		}
		$("#tabs").tabs("add",{
			title : tabName,
			closable:true,
			content:'<div style="width:100%;height:100%;overflow:hidden;background:#f0f6e4;">'  
                +'<iframe src="'  
                + url 
                +'" scrolling="auto" style="width:100%;height:100%;border:0;"></iframe></div>'
		});
	}
}
//关闭事件的实现：关闭当前、关闭所有、关闭其他
function closeTabs(menu, type){
	var currTab = $(menu).data("tabTitle");
	var tabs = $("#tabs");
	if(currTab == "登录信息"){
		return;
	}
	if(type == 'close'){
		tabs.tabs("close",currTab);
	}
	var allTabs = tabs.tabs("tabs");
    var closeTabsTitle = [];
    
    $.each(allTabs, function () {
        var opt = $(this).panel("options");
        if (opt.closable && opt.title != currTab && type === "Other") {
            closeTabsTitle.push(opt.title);
        } else if (opt.closable && type === "All") {
            closeTabsTitle.push(opt.title);
        }
    });
    
    for (var i = 0; i < closeTabsTitle.length; i++) {
        tabs.tabs("close", closeTabsTitle[i]);
    }
}

function beforeClick(treeId, node) {
	if (node.isParent) {
		if (node.level === 0) {
			var pNode = curMenu;
			while (pNode && pNode.level !==0) {
				pNode = pNode.getParentNode();
			}
			if (pNode !== node) {
				var a = $("#" + pNode.tId + "_a");
				a.removeClass("cur");
				zTree_Menu.expandNode(pNode, false);
			}
			a = $("#" + node.tId + "_a");
			a.addClass("cur");

			var isOpen = false;
			for (var i=0,l=node.children.length; i<l; i++) {
				if(node.children[i].open) {
					isOpen = true;
					break;
				}
			}
			if (isOpen) {
				zTree_Menu.expandNode(node, true);
				curMenu = node;
			} else {
				zTree_Menu.expandNode(node.children[0].isParent?node.children[0]:node, true);
				curMenu = node.children[0];
			}
		} else {
			zTree_Menu.expandNode(node);
		}
	}
	return !node.isParent;
}
