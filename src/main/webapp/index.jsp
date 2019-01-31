<html>
<head><title>Parse XML</title></head>
<body>
<form action="mainWindow" method="post" enctype="multipart/form-data">
    <h2>Upload XML file</h2>
    <input type="file" name="xml">
    <h2>Upload XSD schema</h2>
    <input type="file" name="xsd">
    <h2>Choose parser</h2>
    <label>
        <select name="parser-type">
            <option value="DOM">DOM</option>
            <option value="SAX">SAX</option>
            <option value="StAX">StAX</option>
        </select>
    </label>
    <input type="submit">
</form>
</body>
</html>
