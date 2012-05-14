<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>MongoDB DEMO演示</title>
        <%@ include file="taglibs.jsp" %>
        <script type="text/javascript">
            
        </script>
    </head>
    <body class="easyui-layout">

		<div region="north" split="true" style="height:40px;padding:0px;overflow:hidden;background:#1E4176;">
	        	<table width="100%" >
		            <tr>
		                <td height="32"><h1><font color="white" style="font-size:16pt">框架技术演示</font></h1></td>
		            </tr>
			</table>
		</div>
		<!--
		<div region="east" split="true" style="width:2px;background:#efefef;overflow:hidden;">
		</div>
		-->
		<div region="south" style="height:2px;background:#efefef;overflow:hidden;">
		</div>

		<div region="west" split="true" title="系统功能列表" style="width:180px;padding:1px;overflow:hidden;">
			<div class="easyui-accordion" fit="true" border="false">
				<div title="业务操作" selected="true" style="overflow:auto;">
					<div class="nav-item">
						<a href="javascript:$.ewcms.openTab({title:'google',src:'http://www.google.com'})">
							<img src="source/image/print_class.png" style="border:0;"></img><br/>
							<span>google</span>
						</a>
					</div>
					<div class="nav-item">
						<a href="javascript:$.ewcms.openTab({title:'sina',src:'http://www.sina.com'})">
							<img src="source/image/kdmconfig.png" style="border:0;"></img><br/>
							<span>sina网站</span>
						</a>
					</div>
					<div class="nav-item">
						<a href="javascript:$.ewcms.openTab({title:'人员管理',src:'person/index.action'})">
							<img src="source/image/package_settings.png" style="border:0;"></img><br/>
							<span>人员管理</span>
						</a>
					</div>
				</div>
				<div title="数据设置" style="overflow:auto;">
					<div class="nav-item">
						<a href="javascript:addTab('区域设置')">
							<img src="source/image/package.png"></img><br/>
							<span>区域设置</span>
						</a>
					</div>
					<div class="nav-item">
						<a href="javascript:addTab('设备类别')">
							<img src="source/image/kontact.png"></img><br/>
							<span>设备类别</span>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div region="center" style="overflow:hidden;">
			<div class="easyui-tabs"  id="systemtab" fit="true" border="false">
				<div title="Tab1" style="padding:20px;overflow:hidden;"> 
					<div style="margin-top:20px;">
						<h3>jQuery EasyUI framework help you build your web page easily.</h3>
						<li>easyui is a collection of user-interface plugin based on jQuery.</li> 
						<li>using easyui you don't write many javascript code, instead you defines user-interface by writing some HTML markup.</li> 
						<li>easyui is very easy but powerful.</li> 
					</div>
				</div>
			</div>
		</div>
	</body>
</html>