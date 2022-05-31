<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="<%= request.getContextPath() %>/js/excanvas.min.js"></script> 
<script src="<%= request.getContextPath() %>/js/jquery.min.js"></script> 
<script src="<%= request.getContextPath() %>/js/jquery.ui.custom.js"></script> 
<script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script> 
<script src="<%= request.getContextPath() %>/js/jquery.flot.min.js"></script> 
<script src="<%= request.getContextPath() %>/js/jquery.flot.resize.min.js"></script> 
<script src="<%= request.getContextPath() %>/js/jquery.peity.min.js"></script> 
<script src="<%= request.getContextPath() %>/js/fullcalendar.min.js"></script> 
<script src="<%= request.getContextPath() %>/js/maruti.js"></script> 
<script src="<%= request.getContextPath() %>/js/maruti.dashboard.js"></script> 
<script src="<%= request.getContextPath() %>/js/maruti.chat.js"></script> 
 

<script type="text/javascript">
  // This function is called from the pop-up menus to transfer to
  // a different page. Ignore if the value returned is a null string:
  function goPage (newURL) {

      // if url is empty, skip the menu dividers and reset the menu selection to default
      if (newURL != "") {
      
          // if url is "-", it is this page -- reset the menu:
          if (newURL == "-" ) {
              resetMenu();            
          } 
          // else, send page to designated URL            
          else {  
            document.location.href = newURL;
          }
      }
  }

// resets the menu selection upon entry to this page:
function resetMenu() {
   document.gomenu.selector.selectedIndex = 2;
}
</script>