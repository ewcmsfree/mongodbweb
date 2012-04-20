<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>文章分类属性</title>	
		<%@ include file="../taglibs.jsp" %>
		<script type="text/javascript">
		$(function(){
			ewcmsBOBJ = new EwcmsBase();
			ewcmsBOBJ.setQueryURL('<c:url value="/document/category/query.do"/>');
			
			ewcmsBOBJ.delToolItem('缺省查询');

			ewcmsBOBJ.addToolItem('缺省查询','icon-back',defQueryOperate);
			
			ewcmsBOBJ.openDataGrid('#tt',{
                columns:[[
						{field:'id',title:'序号',width:50,sortable:true},
		                {field:'categoryName',title:'名称',width:200}
                  ]]
			});

			ewcmsOOBJ = new EwcmsOperate();
			ewcmsOOBJ.setQueryURL(ewcmsBOBJ.getQueryURL());
			ewcmsOOBJ.setInputURL('<c:url value="/document/category/edit.do"/>');
			ewcmsOOBJ.setDeleteURL('<c:url value="/document/category/delete.do"/>');
			
		});
		function queryOperate(){
			var params = $('#tt').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields =$('#queryform').serializeArray(); //自动序列化表单元素为JSON对象
			$.each(fields, function(i, field){
				params[field.name] = field.value; //设置查询参数
			}); 
			$('#tt').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}
		function defQueryOperate(){
			$('#queryform').form('clear');
			queryOperate();
		}
		</script>		
	</head>
	<body class="easyui-layout">
		<div region="center" style="padding:2px;" border="false">
	 		<table id="tt" fit="true"></table>	
	 	</div>
        <div id="edit-window" class="easyui-window" closed="true" icon="icon-winedit" title="&nbsp;文章分类属性" style="display:none;">
            <div class="easyui-layout" fit="true">
                <div region="center" border="false">
                   <iframe id="editifr"  name="editifr" class="editifr" frameborder="0" onload="iframeFitHeight(this);" scrolling="no"></iframe>
                </div>
                <div region="south" border="false" style="text-align:center;height:28px;line-height:28px;background-color:#f6f6f6">
                    <a class="easyui-linkbutton" icon="icon-save" href="javascript:void(0);" onclick="saveOperator();">保存</a>
                    <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);" onclick="javascript:$('#edit-window').window('close');">取消</a>
                </div>
            </div>
        </div>	
        <div id="query-window" class="easyui-window" closed="true" icon='icon-search' title="查询"  style="display:none;">
            <div class="easyui-layout" fit="true"  >
                <div region="center" border="false" >
                <form:form id="queryform" modelAttribute="category">
                	<table class="formtable">
                            <tr>
                                <td class="tdtitle">编号：</td>
                                <td class="tdinput">
                                    <input type="text" id="id" name="id" class="inputtext"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="tdtitle">名称：</td>
                                <td class="tdinput">
                                    <input type="text" id="categoryName" name="categoryName" class="inputtext"/>
                                </td>
                            </tr>
               		</table>
               	</form:form>
                </div>
                <div region="south" border="false" style="text-align:center;height:28px;line-height:28px;background-color:#f6f6f6">
                    <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="queryOperate();$('#query-window').window('close');">查询</a>
                    <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="javascript:$('#query-window').window('close');">取消</a>
                </div>
            </div>
        </div>      	
	</body>
</html>