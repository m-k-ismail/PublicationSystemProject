<%@ include file="../HTML/navBar.html" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../CSS/fileUpload.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="content">
                <h1>Web Admin Tool</h1>
                <h3>Upload a File</h3>
                <hr>
            </div>
            <form action="/action/UploadFile" method="post" enctype="multipart/form-data">
                <div class="input-group mb-3">
                    <div class="custom-file">
                        <input type="file" name="file" class="custom-file-input" id="inputGroupFile02">
                        <label class="custom-file-label" for="inputGroupFile02">Choose file</label>
                    </div>
                    <div class="input-group-append">
                        <span><input type="submit" class="input-group-text"></span>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>