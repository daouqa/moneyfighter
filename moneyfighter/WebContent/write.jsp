<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,org.json.simple.JSONObject"%>
<!DOCTYPE html>
<html>
<head>
<title>가계부 - 내역입력</title>
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

	});
</script>
<script type="text/javascript">
	function write_action() {
		alert("입력되었습니다~~~~~~~~~~~~");
		var mForm = document.write; // form의 값을 가져오기 위함
		var obj = new Object(); // JSON형식으로 변환 할 오브젝트
		obj['date'] = mForm.datepicker.value;
		if(mForm.type.value=="수입") obj['type'] = "income";
		else obj['type']="outcome";
		obj['category'] = mForm.category.value;
		obj['price'] = mForm.price.value;
		obj['note'] = mForm.note.value;

		console.log(obj);
		var json_data = JSON.stringify(obj); // form의 값을 넣은 오브젝트를 JSON형식으로 변환
		var request = $.ajax({
			url : "MoneyBook",
			type : "POST",
			data : obj,
			dataType : "json"
		});
	}

	//선택값에 따라 selectbox 다르게 출력되는 부분 구현 
	var selbox1 = new Array('수입','지출');

	var selbox2 = new Array();
	selbox2[0] = new Array('월급','용돈');
	selbox2[1] = new Array('교통비','주유비','식비','커피','보험비','기타');

	function init(f) {
		var sel1 = f.type;
		var sel2 = f.category;
	 	
		sel1.options[0] = new Option("선택","");
		sel2.options[0] = new Option("선택","");

		for(var i=0; i<selbox1.length; i++) {
			sel1.options[i+1] = new Option(selbox1[i],selbox1[i]);
		} 

	}
	function itemChange(f) {
		var sel1 = f.type;
		var sel2 = f.category;

		var sel = sel1.selectedIndex;
		for(var i=sel2.length; i>=0; i--) {
			sel2.options[i] = null;
		}

		sel2.options[0] = new Option(" 선택","");
		if(sel != 0) {
			for(var i=0; i<selbox2[sel-1].length; i++) {
				sel2.options[i+1] = new Option(selbox2[sel-1][i], selbox2[sel-1][i]);
			}
		}
	}
	

</script>
<%
	Date d1 = new Date();
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	String strdate = sdf1.format(d1);
%>
<style>
table#t01 {
	width: 40%;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	text-align: left;
}

table#t01 th {
	background-color:black;
	color: white;
}

</style>
</head>
<body onload="init(this.write);">
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
							<h3 align="left">내역 입력</h3>
							<hr  style="height: 5px; border-color:black; border-top-width: 1px;">
							<br>
							<form action="" method="post" name="write">
								<table id="t01">
									<tr>
										<th>날짜</th>
										<td><input type="text" size="13"
											style="text-align: center" value=<%= strdate %>
											id="datepicker"> &nbsp;<span
											class="glyphicon glyphicon-calendar"></span></td>
									</tr>
								  	<tr>
										<th>수입/지출</th>
										<td><select id="type" name="type" onchange="itemChange(this.form)";>
										</select></td>
									</tr>
									<tr>
										<th>항목</th>
										<td><select id="category" name="category">
										</select></td>
									</tr>

									<tr>
										<th>금액</th>
										<td><input type="text" name="price">
											원</td>
									</tr>
									<tr>
										<th>비고</th>
										<td><input type="textarea" size="30"name="note">
											<input type="submit" onclick="write_action()" value="입력하기"></td>
									</tr>
								</table>

							</form>

							
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->
	</div>

</body>
</html>