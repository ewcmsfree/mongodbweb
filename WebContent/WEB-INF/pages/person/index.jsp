<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>医疗证据管理</title>	
		<%@ include file="../taglibs.jsp" %>
		<script type="text/javascript">
		$(function(){
			$('#tt').datagrid({
			    nowrap:true,
			    pagination:true,
			    rownumbers:true,
			    singleSelect:false,
			    pageSize:20,
				frozenColumns:[[
				    {field:'ck',checkbox:true},
				    {field:'id',title:'序号',width:50,hidden:true}
				]],
				columns:[[
			        {field:'name',title:'名称',width:200,sortable:true},
			        {field:'age',title:'年龄',width:100,sortable:true},
			        {field:'address',title:'地址',width:300},
			        {field:'email',title:'邮箱',width:200}
	            ]],
	            url:"<c:url value='/person/query.action'/>"
			});
            $("#tb-add").click(function(){
				$.ewcms.openWindow({
					src:"<c:url value='/person/edit.action'/>",
					windowId:"#edit-window"
				});
			});
            $("#tb-query").click(function(){
            	$.ewcms.query({
            		url:"<c:url value='/person/query.action'/>"
            	});
            });
		});
		</script>		
	</head>
	<body class="easyui-layout">
		<div region="center" style="padding:2px;" border="false">
	 		 <table id="tt" toolbar="#tb" fit="true"></table>
             <div id="tb" style="padding:5px;height:auto;">
               <div style="margin-bottom:5px">
					<a id='tb-add' href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
					<a id='tb-edit' href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
					<a id='tb-remove' href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
				</div>
                <div style="padding-left:5px;">
                   <form id="queryform"  style="padding: 0;margin: 0;">
                    证据编号：<input type="text" name="id" style="width:80px"/>&nbsp;
                    名称：<input type="text" name="name" style="width:120px"/>&nbsp;
                    <a href="#" id="tb-query" class="easyui-linkbutton" iconCls="icon-search">查询</a>
                   </form>
               </div>
            </div>	
	 	</div>
        <div id="edit-window" class="easyui-window" closed="true" icon="icon-winedit" title="&nbsp;文章分类属性" style="display:none;">
            <div class="easyui-layout" fit="true">
                <div region="center" border="false">
                   <iframe id="editifr"  name="editifr" class="editifr" frameborder="0" scrolling="no"></iframe>
                </div>
                <div region="south" border="false" style="text-align:center;height:28px;line-height:28px;background-color:#f6f6f6">
                    <a class="easyui-linkbutton" icon="icon-save" href="javascript:void(0);" onclick="saveOperator();">保存</a>
                    <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);" onclick="javascript:$('#edit-window').window('close');">取消</a>
                </div>
            </div>
        </div>	
	</body>
</html>