<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>
  <div class="table-responsive">

<table id="mytable" class="table table-bordred table-striped">
                   
                   <thead>
                   
                   <th><input type="checkbox" id="checkall" /></th>
                   <th>Sender</th>
                   <th>Receiver</th>
                    <th>Subject</th>
                    <th>Date</th>
                    <th>Reply</th>
                     
                       <th>Delete</th>
                   </thead>
    <tbody>
    <c:forEach items="${messages}" var="messages" varStatus="count">
    <tr>
    <td><input type="checkbox" class="checkthis" /></td>
    <td><strong>${messages.sender}</strong></td>
    <td><strong>${messages.receiver}</strong></td>
    <td><strong>${messages.subject }</strong> - ${fn:substring(messages.content,0,20)}</td>
    <td>${messages.date }</td>
    <td><a href="sendMessage?to=${messages.sender}"><i id="reply" class="glyphicon glyphicon-share-alt"></i></a></td>
    <%-- <td><c:out value='sendMessage?sender=${messages.sender}'/><button>Reply<span class="glyphicon glyphicon-share-alt"></span></button></td> --%>
  
    <td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>
    </tr>
    
 </c:forEach>
    
   
    
    </tbody>
        
</table>


            </div>
            



