<html lang="en-US">
<head>
<meta charset="utf-8">
<title>Search</title>

<link href="http://fonts.googleapis.com/css?family=Open+Sans"
	rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<style>

body {
	color: #000;
	font: 14px/1.5em "Open Sans", sans-serif;
	margin: 0;
}

#logoTitle{    font-weight: bold;
    font-size: 50px;
    /* margin-top: 30px; */
    background-color: white;
    height: 60px;
    padding-top: 35px;
    border-bottom: 2px solid;
    /* margin-bottom: 1px; */}

fieldset {
	border: 0;
	margin: 0;
	padding: 0;
}

input {
	border: none;
	font-family: inherit;
	font-size: inherit;
	line-height: 1.5em;
	margin: 0;
	outline: none;
	padding: 0;
	-webkit-appearance: none;
}

input[type="search"] {
	-webkit-appearance: textfield;
	-moz-box-sizing: content-box;
	-webkit-box-sizing: content-box;
	box-sizing: content-box;
}

input[type="search"]::-webkit-search-cancel-button,input[type="search"]::-webkit-search-decoration
	{
	-webkit-appearance: none;
}

.clearfix {
	*zoom: 1;
}

.clearfix:before,.clearfix:after {
	content: "";
	display: table;
}

.clearfix:after {
	clear: both;
}

.container {
	height: 100px;
    margin-left: 12px !important;
}
.usercontainer{
    height: 100px;
    margin-left: 12px !important;
    float: right;
    margin-right: 50px;
    margin-top: -100px;
    color: white;
    font-weight: bold;}

/* ---------- SEARCH ---------- */
#search {
	background: #42454e;
	border-radius: 3px;
	display: inline-block;
	padding: 7px;
}

#search input {
	float: left;
}

#search input[type="search"],#search input[type="submit"] {
	border-radius: 3px;
	font-size: 12px;
}

#search input[type="search"] {
	background: #fff;
	color: #42454e;
	min-width: 400px;
	padding: 6px 8px;
	height: 40px;
	font-size: 25px;
}

#search input[type="submit"] {
	background: #1bba9a;
	color: #fff;
	font-weight: bold;
	margin-left: 7px;
	padding: 6px 10px;
	height: 52px;
	width: 120px;
	font-size: 18px;
}

#search input[type="submit"]:hover {
	background: #189e83;
}

#search input[type="search"]::-webkit-input-placeholder {
	color: #42454e;
}

#search input[type="search"]:-moz-placeholder {
	color: #42454e;
}

#search input[type="search"]:-ms-input-placeholder {
	color: #42454e;
}

#searchResult {margin-left: 12px !important;}

#result_div {
	border: 1px solid gray;
	width: 600px;
	padding: 15px;
	border-radius: 6px;
	margin-bottom: 5px;
}

#status_div {
	font-weight: bold;
	font-size: 22px;
	margin-bottom: 20px;
}

#details_div {
	font-size: 18px;
}

#details_span {
	font-weight: bold;
}

</style>

<script>
var dataObj = '{"studentdata":[{"name" : "Hemant Gautam", "maths":95, "physics":88, "chemistry":82, "english":79},' +
                               '{"name" : "Rakesh Verma", "maths":80, "physics":91, "chemistry":55, "english":72},' +
                               '{"name" : "Rahul Dewani", "maths":80, "physics":70, "chemistry":89, "english":72},' +
                               '{"name" : "Himanshu Suman", "maths":40, "physics":30, "chemistry":94, "english":72},' +
                               '{"name" : "Saurav Kumar", "maths":80, "physics":70, "chemistry":94, "english":93}]}'
function getSearchResult() {
	$.ajax({
		url: "/sg-usersearch/filter",
		type: "POST",
		data: {data: dataObj},
		success: function(res) {

			console.log("Looks count for the week is:");
			var data = JSON.parse(res);
			console.log(res);
		}
	});
}
</script>
</head>

<body>
	<div id="logoTitle">
		Drools Demo</div>
	<div class="container">

		<div id="search">

			<form action="javascript:void(0);"
				style="margin-bottom: 0px;">
					<input type="submit" value="Fire Rules" class="button"
						onClick="getSearchResult()">

			</form>

		</div>
		<!-- end search -->

	</div>
	<!-- end container -->

</body>
</html>