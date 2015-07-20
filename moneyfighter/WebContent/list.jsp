<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*"%>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>가계부 - 입출금내역</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/simple-sidebar.css" rel="stylesheet">
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/datepicker.css">
<script src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript">
	// When the document is ready
	$(document).ready(function() {

		$('#datepicker').datepicker({
			format : "yyyy-mm-dd"
		});

		$('#searchBtn').on("click", function() {
			alert("헤헤 버튼클릭~~");
		});

	});

</script>
<!-- 입출금 내역 테이블 스타일 정의 -->
<style>
table#t01 {
	width: 100%;
}

table#t02 {
	width: 30%;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	text-align: left;
}

table#t01 tr:nth-child(even) {
	background-color: #eee;
}

table#t01 tr:nth-child(odd) {
	background-color: #fff;
}

table#t01 th {
	background-color: black;
	color: white;
}

table#t02 th {
	background-color: black;
	color: white;
	width: 30%;
	text-align: center;
}
</style>
</head>
<body>
	<div id="wrapper">

		<!-- Side 메뉴 -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li class="sidebar-brand"><a href="#"> Money Fighter </a></li>
				<li><a href="./list.jsp">입출금 내역</a></li>
				<li><a href="./write.jsp">내역 입력</a></li>
				<li><a href="./itemmgmt.jsp">항목 관리</a></li>
				<li><a href="./stat.jsp">통계</a></li>
				<li><a href="./about.jsp">About</a></li>
			</ul>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- 본문 내용 -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<h3 align="center">입출금 내역</h3>
							<hr  style="height: 5px; border-color:black; border-top-width: 1px;">
							<br>
							<% 	Date d1 = new Date();
	                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	                            String strdate = sdf1.format(d1);
	                            
	                         
	                         %>
							<p>
								<input type="text" style="text-align: center" size="15"
									value=<%= strdate %> id="datepicker"> <span
									class="glyphicon glyphicon-calendar"></span> <a id="searchBtn"
									data-bypass=""
									style="background: #666; color: #fff; padding: 3px 5px; text-decoration: none; border-radius: 3px; margin-left: 3px; margin-right: 10px">search</a>
							</p>

							<!-- 입출금 내역 테이블 -->
							<table id="t01">
								<tr>
									<th style="width: 25%">날짜</th>
									<th style="width: 15%">수입/지출</th>
									<th style="width: 15%">항목</th>
									<th style="width: 15%">금액</th>
									<th style="width: 30%">비고</th>
								</tr>
								
								<%
								
								ArrayList<JSONObject> list = new ArrayList<JSONObject>();
								
	                            
								//과장님이 json오브젝트 넘겨주시면 주석제거 
								/*JSONObject listjson = new JSONObject();
								  listjson = json.getJSONObject("");*/
	                          
	                        	//json오브젝트 받기전에 테스트할 데이터 임의로 입력
	                        	JSONObject t1 = new JSONObject();
	                        	t1.put("date","2015-07-16");
	                        	t1.put("type","outcome");
	                        	t1.put("category","커피");
	                        	t1.put("price","10000");
	                        	t1.put("note","커피빈");

	                        	//json오브젝트 받기전에 테스트할 데이터 임의로 입력
	                            JSONObject t2 = new JSONObject();
	                        	t2.put("date","2015-07-16");
	                        	t2.put("type","income");
	                        	t2.put("category","월급");
	                        	t2.put("price","1080000");
	                        	t2.put("note","7월 월급");

	                        	//json오브젝트 받기전에 테스트할 데이터 임의로 입력
	                            JSONObject t3 = new JSONObject();
	                        	t3.put("date","2015-07-16");
	                        	t3.put("type","outcome");
	                        	t3.put("category","식비");
	                        	t3.put("price","17000");
	                        	t3.put("note","바울아저씨");
	                        	
	                        	JSONObject t4 = new JSONObject();
	                        	t4.put("date","2015-07-16");
	                        	t4.put("type","outcome");
	                        	t4.put("category","보험");
	                        	t4.put("price","250000");
	                        	t4.put("note","7월 보험비");
	
	                            //json오브젝트를 list배열에 추가
	                            list.add(t1);
	                            list.add(t2);
	                            list.add(t3);
	                            list.add(t4);
	                            
	                            int incomesum=0;
                   		  		int outcomesum=0;
                   		  		int balance;
 
	                            //list배열의 데이터를 테이블에 출력 
	                   		  	for(int i=0; i < list.size(); i++) {
	                   		  		String str;
	                   		  		
	                   		  		if(list.get(i).get("type")=="income") {
	                   		  			str="수입";
	                   		  			incomesum+=Integer.parseInt(list.get(i).get("price").toString());
	                   		  			
	                   		  		}
	                   		  		else {
	                   		  			str="지출";
	                   		  		    outcomesum+=Integer.parseInt(list.get(i).get("price").toString());
	                   		  		}
	              	            %>
								 <tr>
									<td><%= list.get(i).get("date")%></td>
									<td><%= str%></td>
									<td><%= list.get(i).get("category")%></td>
									<td><%= list.get(i).get("price")%></td>
									<td><%= (String)list.get(i).get("note")%></td>
								</tr> 								
								<%
	                        		}
								%>
							</table>
							<%
								if(incomesum>=outcomesum) balance=incomesum-outcomesum;
               		  			else balance=0; 
							%>
							<br> <br>
							<table id="t02" align="right">
								<tr>
									<th>수입 합계</th>
									<td><%= incomesum %> 원</td>
								</tr>
								<tr>
									<th>지출 합계</th>
									<td><%= outcomesum %> 원</td>
								</tr>
								<tr>
									<th>잔액</th>
									<td><%= balance %> 원</td>
								</tr>
							</table>

						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->
	</div>

</body>
</html>