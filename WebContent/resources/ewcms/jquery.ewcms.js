/**
 * jQuery ewcms 1.0
 * 
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 * 
 * author wangwei [hhywangwei@gmail.com]
 */

(function($){
	$.fn.ewcms = function(){
		
	};
	
	$.fn.ewcms.openWindow = function(options){
		var opts = $.extend({}, $.fn.ewcms.defaults.window, options);  
		if(!hasElementFor(opts.windowId)){
			return;
		}
		$(opts.windowId).removeAttr("style");
		$(opts.windowId).window({
		   title: opts.title,
		   width: opts.width,
		   height: opts.height,
		   left:(opts.left ? opts.left : ($(window).width() - opts.width)/2),
		   top:(opts.top ? opts.top : ($(window).height() - opts.height)/2),
		   modal: opts.modal,
		   maximizable:opts.maximizable,
		   minimizable:opts.minimizable,
		   onClose:opts.onClose(opts.windowId)
		});
		if(opts.url){
			opts.iframeId ? 
					$(opts.iframeId).attr('src',opts.url) 
					:$(opts.windowId).find('iframe').attr('src',opts.url);
		}
		$(opts.windowId).window('open');
	};
	
	$.fn.ewcms.closeWindow = function(windowId){
		if(!hasElementFor(windowId)){
			return;
		}
		$(windowId).window('close');
	};
	
	$.fn.ewcms.defaults = {};
	
	$.fn.ewcms.defaults.window = {
		title : "新窗口",
		width : 500,
		height : 300,
		modal:true,
		maximizable:false,
		minimizable:true,
		onClose:function(windowId){
			$(windowId).find("iframe").attr('src','about:blank');
	    }
	};
	
	$.fn.ewcms.operator = {};
	
	$.fn.ewcms.operator.save = function(options){
		var opts = $.extend({}, $.fn.ewcms.defaults.operator, options);
		if(!hasElementFor(opts.iframeId)){
			return;
		}
		window.frames[iframeId].document.forms[0].submit();
	};
	
	$.fn.ewcms.operator.add = function(options){
		var opts = $.extend({}, $.fn.ewcms.defaults.operator, options);
		if(!hasElementFor([opts.iframeId,opts.windowId])){
			return;
		}
		openWindow(opts);
	};
	
	$.fn.ewcms.operator.update = function(options){
		var opts = $.extend({}, $.fn.ewcms.defaults.operator, options);
		if(!hasElementFor([opts.datagridId,opts.iframeId,opts,windowId])){
			return;
		}
		
	    var rows = $(opts.datagridId).datagrid('getSelections');
	    if(rows.length == 0){
	        $.messager.alert('提示','请选择修改记录','info');
	        return;
	    }
	    
	    var url = (( opts.url.indexOf("?") == -1) ? opts.url + '?' : opts.url + '&');
	    $each(rows,function(index,row){
	    	url += 'selections=' + opts.getId(row) +'&';
	    });
	    openWindow(opts);
	};
	
	$.fn.ewcms.operator.remove = function(options){
		var opts = $.extend({}, $.fn.ewcms.defaults.operator, options);
		if(!hasElementFor(opts.datagridId)){
			return;
		}
	    var rows = $(opts.datagridId).datagrid('getSelections');
	    if(rows.length == 0){
	        $.messager.alert('提示','请选择删除记录','info');
	        return ;
	    }
	    
	    var data = '';
	    $.each(rows,function(index,row){
	    	data =data + 'selections=' + opts.getId(row) +'&';
	    });
	    $.messager.confirm("提示","确定要删除所选记录吗?",function(r){
	        if (r){
	            $.post(opts.urls,ids,function(data){          	
	            	$.messager.alert('成功','删除成功','info');
	            	$(opts.datagridId).datagrid('clearSelections');
	                $(opts.datagridId).datagrid('reload');              	
	            });
	        }
	    });
	};
	
	$.fn.ewcms.defaults.operator = {
		datagridId : '#tt',
		iframeId : '#editifr',
		windowId : '#edit-window',
		url:"#",
		getId : function(row){
			return id;
		}
	};
	
	$.fn.ewcms.query = function(opts){
		
	};
	
	$.fn.ewcms.defaults.query = {
			datagridId : '#tt',
			formId : '#queryform',
			windowId : '#query-window',
			url: "query.action"
	};
	
	function hasElementFor(id){
		var ids = $.isArray(id) ? id : [id];
		$.each(ids,function(index,i){
			if($(i).length == 0){
				alert(i + '编号的元素不存在');
				return false;
			}
		});
		return true;
	};
	
})(JQuery);