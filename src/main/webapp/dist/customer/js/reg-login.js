var curWwwPath = window.document.location.href;
var pathName = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
var basePath = curWwwPath.substring(0, pos)+"/hotel";

$(document).ready(function() {

	$("#back-home").click(function() {
		window.location.href = basePath + "/home";
	});

	$("#reg").click(function() {
		window.location.href = basePath + "/reg";
	});

	$("#login").click(function() {
		window.location.href = basePath + "/login";
	});
});