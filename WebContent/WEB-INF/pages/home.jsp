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
        <div region="north" split="true" class="head">
			<div id="top">
			    <div id="toppiz">
			   	<div class="huanying">
			       <span style="font-size:13px;font-weight: bold;">MongoDB 欢迎你</span>
			   	</div>
			   <div style="float:right;margin-top:5px;margin-right:10px;">
		           <a id="button-main" href="#" style="border:0;padding:0;"><img src="<s:url value='/ewcmssource/image/exit.png'/>" width="17" height="17" style="border:0;"/></a>
		       </div>
			   	<div class="anniu">
			   		<div class="bs">
						<a class="styleswitch a1" style="cursor: pointer" title="谈黄色" rel="sunny"></a>
						<a class="styleswitch a2" style="cursor: pointer" title="浅蓝色" rel="cupertino"></a>
						<a class="styleswitch a4" style="cursor: pointer" title="黑色" rel="dark-hive"></a>	
						<a class="styleswitch a5" style="cursor: pointer" title="灰色" rel="pepper-grinder"></a>		
					</div>
			   </div>
					<div style="float:right;padding-top:42px;margin-right:10px;">
                        <span id="tipMessage" style="color:red;font-size:13px;width:100px;"></span>
                    </div>
			 </div>
             <div id="mm" class="easyui-menu" style="width:120px;display:none;">
                <div  id="switch-menu" iconCls="icon-switch">站点切换</div>
                <div class="menu-sep"></div>
                <div id="user-menu" iconCls="icon-edit">修改用户信息</div>
                <div id="password-menu" iconCls="icon-password">修改密码</div>
                <div class="menu-sep"></div>
                <div id="progress-menu">发布进度</div>
                <div class="menu-sep"></div>
                <div id="exit-menu" iconCls="icon-exit">退出</div>
             </div>
             </div>
        </div>
        <div region="south" style="height:2px;background:#efefef;overflow:hidden;"></div>
        <div region="west" split="true" title="平台菜单" style="width:180px;padding:1px;overflow:hidden;">
            <div id="mainmenu" class="easyui-accordion" fit="true" border="false">
                <div title="例程" selected="true" style="overflow:auto;">
                    <div class="nav-item">
                        <a href="javascript:_home.addTab('人员管理','site/channel/index.do')">
                            <img src="ewcmssource/image/package.png" style="border:0;"/><br/>
                            <span>人员管理</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:_home.addTab('证件查询','site/template/index.do')">
                            <img src="ewcmssource/image/kontact.png" style="border:0;"/><br/>
                            <span>证件查询</span>
                        </a>
                    </div>
                </div>
                <div title="菜单二" style="overflow:auto;">
                    <div class="nav-item">
                        <a href="javascript:_home.addTab('google','http://www.google.com')">
                            <img src="ewcmssource/image/crawler_content.png" style="border:0"/><br/>
                            <span>内容采集</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>