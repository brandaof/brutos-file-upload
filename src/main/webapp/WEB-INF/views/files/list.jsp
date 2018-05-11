<%@page session="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>

	<head>
		<jsp:include page="../fragments/header.jsp"/>
	</head>

<body>

	<jsp:include page="../fragments/navigation.jsp"/>
	
    <div class="container">
    	<section>
    	
	    	<div class="col-lg-8 col-lg-offset-2">
	    		<div class="page-header">
					<h1>Files</h1>
                </div>
	    	</div>
	    	
			<form method="POST"
				action="${pageContext.servletContext.contextPath}/files" enctype="multipart/form-data">
				
				  <div class="form-group row">
					<label class="col-sm-2 control-label">File</label>
					<div class="col-sm-10">
						<input type="file" name="file" multiple>
					</div>
				  </div>
				
					<div class="form-group row">
					  <div class="col-sm-offset-2 col-sm-12">
					     <button type="submit" class="btn-lg btn-primary float-right">Submit</button>
					  </div>
					</div>
				
			</form>
	    	
			<table class="table table-striped">
				<thead>
					<tr>
						<th class="col-sm-10">File</th>
						<th>Action</th>
					</tr>
				</thead>
	
				<c:forEach items="${Controller.files}" var="item">
					<tr>
						<td><a href="${pageContext.request.contextPath}/files/${item}">${item}</a></td>
						<td>
							  <button class="btn btn-danger"
			                        onclick="post('/files/delete/${item}')">Delete</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			
    	</section>
    	
    </div>

	<jsp:include page="../fragments/footer.jsp"/>

</body>

</html>