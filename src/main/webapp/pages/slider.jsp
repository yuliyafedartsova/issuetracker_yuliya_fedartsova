<%@include file="/libs/foundation/global.jsp"%>
<%@ page import="java.util.ArrayList, java.util.List"%>
<%
final String TRANS_TIME_DEFAULT = "500";
final String DELAY_TIME_DEFAULT = "5000";
final String SPEED = "playSpeed";
final String IN_TIME = "fadeIn";
final String OUT_TIME = "fadeOut";
String playDelay = properties.get(SPEED, DELAY_TIME_DEFAULT); 
String fadeOut = properties.get(OUT_TIME, TRANS_TIME_DEFAULT);
String fadeIn = properties.get(IN_TIME, TRANS_TIME_DEFAULT);
int SLIDES_NUMBER = 5;
final String BOOKMARK = "bookmark";
final String LINK = "link";
final String IMAGE_REFERENCE = "imageReference";
List<Slide> slides = new ArrayList<Slide>();
for(int i = 1; i <= SLIDES_NUMBER; i++){
    String imagePath = (String) properties.get(IMAGE_REFERENCE + i);
	String bookmark = (String) properties.get(BOOKMARK + i);
	String link = (String) properties.get(LINK + i);
	Slide slide = new Slide(imagePath, bookmark, link);
	slides.add(slide);
}

%>

<cq:include script="/libs/wcm/core/components/init/init.jsp"/>
<cq:includeClientLib categories="jquerysamples"/>
<div class="slider">
    <div class="options" speed="<%= playDelay %>" fadeIn="<%= fadeIn %>" fadeOut="<%= fadeOut %>">  </div>
    <div class="images">
    <ul class="panes">
		<c:forEach items="<%=slides%>" var="slide"> 
  		<li info="${slide.bookmark}" link="${slide.link}.html">
			<a href="${slide.link}.html">
            <img title="${slide.bookmark}" alt="${slide.bookmark}" src="${slide.imagePath}"/>
			</a>
		</li>
		</c:forEach>
    </ul>
    </div>
    <div class="descriptions">
    <table class="tabs">
        <tbody>
			<c:forEach items="<%=slides%>" var="slide">
				<tr>
                    <td class="inactiveTab" info="${slide.bookmark}" link="${slide.link}.html">
						<a href="${slide.link}.html"> <div class="text"> ${slide.bookmark} </div> </a> 
					</td>
                </tr>
			</c:forEach>
        </tbody>
    </table>
    </div>
</div>
<div class="bookmark">
    <div class="bookmarkText"> <%=slides.get(0).getBookmark()%> </div>
	<a class="reference" href="<%=slides.get(0).getLink()%>.html">Learn more</a>
</div>

<%!
/**
     * Container class for slides
     */
    public static final class Slide {
        private String imagePath = "";
        private String bookmark = "";
        private String link = "";

        private Slide(String imagePath, String bookmark, String link) {
			this.imagePath = imagePath;
            this.bookmark = bookmark;
            this.link = link;
        }
        public String getImagePath() {
            return imagePath;
        }

        public String getBookmark() {
            return bookmark;
        }

        public String getLink() {
            return link;
        }
    }
%>
