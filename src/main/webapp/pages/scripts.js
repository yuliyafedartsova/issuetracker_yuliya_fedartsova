
function showResolutions() {
	document.getElementById("res").removeAttribute('id');
}

function showVersions(id) {
	document.getElementById(id).removeAttribute('class');
}

function addVersion(id) {
	var f = document.getElementById("issue-form");
	f.versionId.value = id;
}










