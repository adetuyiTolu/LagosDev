# LagosDev
Implementation of Andela Android learning community task to fetch lagos developers using github api
<br/>
#ScreenShots
<p align="center">
  <img src="https://raw.githubusercontent.com/adetuyiTolu/LagosDev/master/screenshots/screen1.png" width="350"/>
  <img src="https://raw.githubusercontent.com/adetuyiTolu/LagosDev/master/screenshots/screen2.png" width="350"/>
  <img src="https://raw.githubusercontent.com/adetuyiTolu/LagosDev/master/screenshots/screen3.png" width="350"/>
  <img src="https://raw.githubusercontent.com/adetuyiTolu/LagosDev/master/screenshots/screen4.png" width="350"/>
  <img src="https://raw.githubusercontent.com/adetuyiTolu/LagosDev/master/screenshots/screen5.png" width="350"/>
</p>

#How it works
-On launch, the application checks if there is internet access
-if there is internet access, it retrieves the list of lagos developers from github and save details locally
-otherwise, it loads from the local storage
-then update the RecyclerView
-User can as well refresh/reload by swiping down via the swipeRefreshLayout attached to the recyclerView
-A click on each developer opens up developer details
-via the detailed page, user can:
<ul>
<li>View developer picture and username</li>
<li>share developers details</li>
<li>click on developers github link to get more information</li>
</ul>
