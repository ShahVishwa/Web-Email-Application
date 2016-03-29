<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>
  <div class="table-responsive">
  
  
  
 
  
  
  
  
  

<table id="mytable" class="table table-bordred table-striped">
                   
                   <thead>
                   
                   <th><input type="checkbox" id="checkall" /></th>
                   <th>Sender</th>
                    <th>Subject</th>
                    <th>Date</th>
                    <th>Reply</th>
                      <th>Star it?</th>
                       <th>Delete</th>
                   </thead>
    <tbody>
    <c:forEach items="${messages}" var="messages" varStatus="count">
    <tr>
    <td><input type="checkbox" class="checkthis" /></td>
    <td><strong>${messages.sender}</strong></td>
    <td><a href="#" data-toggle="modal" data-target="#contentModal${messages.messageID}"><strong>${messages.subject }</strong> - ${fn:substring(messages.content,0,20)}</a></td>
    <td>${messages.date }</td>
    <td><a href="sendMessage?to=${messages.sender}" data-toggle="modal" data-target="#myModal" ><i id="reply" class="glyphicon glyphicon-share-alt"></i></a></td>
    <%-- <td><c:out value='sendMessage?sender=${messages.sender}'/><button>Reply<span class="glyphicon glyphicon-share-alt"></span></button></td> --%>
    <td><p data-placement="top" data-toggle="tooltip" title="Star"><button class="btn btn-primary btn-xs" data-title="Star" onclick=makeStarred(event,${messages.messageID}) ><span class="glyphicon glyphicon-star  ${messages.email_status}"></span></button></p></td>
    <td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" onclick=deleteMessage(event,${messages.messageID}) ><span class="glyphicon glyphicon-trash"></span></button></p></td>
    </tr>
    
    
    
    
    <div class="container">
		<div class="modal fade" id="contentModal${messages.messageID}" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Message</h4>
					</div>
					<div class="modal-body">
       
          <div class="form-group">
            <label class="col-sm-2" for="inputTo">from</label>
            <div class="col-sm-10"><input type="text" class="form-control" id="receiver" value="${messages.sender}" ></input></div>
          </div>
          <div class="form-group">
            <label class="col-sm-2" for="inputSubject">Subject</label>
            <div class="col-sm-10"><input type="text" class="form-control" id="subject" value="${messages.subject}"></input></div>
          </div>
         
          <div class="form-group">
            <label class="col-sm-3" for="inputBody">Message</label>
            <!--<div class="col-sm-9"><textarea class="form-control" id="content" rows="8" value="${messages.content}"></textarea></div>-->
            
           <div class="col-sm-9"><c:out value="${messages.content}" escapeXml="false"/></div>
           
         
          </div>
          <div class="row"></div>
           <div class="row"></div>
        
      </div>
					<div class="modal-footer">
                         <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancel</button> 
                         
                         
                    </div>
				</div>
			</div>
		</div>
	</div>
    
    
    
    
    
    
    
 </c:forEach>
    
   
    
    </tbody>
        
</table>


                
            </div>
            
      

    