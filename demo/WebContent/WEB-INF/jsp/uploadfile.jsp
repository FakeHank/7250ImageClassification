<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>The King of Image Classification</title>
<script
    src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script>
    $(document)
            .ready(
                    function() {
                        //add more file components if Add is clicked
                        $('#addFile')
                                .click(
                                        function() {
                                            var fileIndex = $('#fileTable tr')
                                                    .children().length - 1;
                                            $('#fileTable')
                                                    .append(
                                                            '<tr><td>'
                                                                    + '   <input type="file" name="files['+ fileIndex +']" accept="image/png,image/jpg,image/jpeg" />'
                                                                    + '</td></tr>');
                                        });
                        $('#try')
                        	.click(function(){
                            	if(document.getElementById("pw").value=="something"){
                            		document.getElementById("upload").removeAttribute("hidden");
                            	}
                        	});
 
                    });

</script>
<style type="text/css">
body {
	background-image: url("video/cat_homepage.jpg");
}
</style>
</head>
<body>
    <br>
    <br>
	<input id="pw" type="password"/>
    <button id="try" align="center" onclick="click()">try</button>
    <div id="upload" align="center" hidden="true">
        <h1>Upload Multiple Files</h1>
 
 
        <form:form method="post" action="savefiles.html"
            modelAttribute="uploadForm" enctype="multipart/form-data">
 
            <p>Select files to upload. Press Add button to add more file
                inputs.</p>
 
            <table id="fileTable">
                <tr>
                    <td><input name="files[0]" type="file" accept="image/png,image/jpg,image/jpeg" /></td>
                </tr>
            </table>
            <br />
            <input type="submit" value="Upload" />
            <input id="addFile" type="button" value="Add File" />
        </form:form>
 
        <br />
    </div>
</body>

</html>