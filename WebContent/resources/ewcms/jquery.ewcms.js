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
	
	$.extend({
		ewcms:{
			openTab : function (options){
				var defaults = {
					id : '#systemtab',
					src : '#',
					refresh : false
				};
				var opts = $.extend({}, defaults, options); 
			    if(!opts.content){
			    	opts.content = '<iframe src="' + opts.src + '" width=100% height=100% frameborder=0/>'; 
			    }
			    if(!hasElementFor(opts.id)){
			    	return ;
			    }
			    var t = $(opts.id);
			    var title = opts.title;
			    if (!t.tabs('exists', title)) {
			        t.tabs('add', {
			            title : title,
			            content : opts.content,
			            closable : true
			        });
			        return;
			    }
			    t.tabs('select', title);
		    	if(opts.refresh){
		  	        t.tabs('update', {
		  	            tab : t.tabs("getTab", title),
		  	            options : {
		  	                content : opts.content
		  	            }
		  	        });
		    	}
			},
			query : function(options) {
				var defaults = {
						datagridId : '#tt',
						formId : '#queryform',
						url: "query.action",
						selections : []
				};
				var opts = $.extend({}, defaults, options);
				if(!hasElementFor(opts.datagridId)){
					return ;
				}
				if(opts.selections.length > 0){
					$(opts.datagridId).datagrid('load',{
						selections:opts.selections
					});
				}else{
					if(!hasElementFor(opts.formId)){
						return ;
					}
					var wapper = {
							parameters:{
								a:'1',
								b:'2'
							}
					};
					alert($.param(wapper));
					$(opts.datagridId).datagrid({
						url:"query.action?wapper.parameters['a']=1&wapper.parameters['b']=2"
					});

				}
			},
			openWindow : function(options){
				var defaults = {
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
				var opts = $.extend({}, defaults, options);  
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
				if(opts.src){
					opts.iframeId ? 
							$(opts.iframeId).attr('src',opts.src) 
							:$(opts.windowId).find('iframe').attr('src',opts.src);
				}
				$(opts.windowId).window('open');
			}
		}
	});
})(jQuery);
