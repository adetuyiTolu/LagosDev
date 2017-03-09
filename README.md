# LagosDev
Implementation of Andela Android learning community task to fetch lagos developers using github api
<br/>
#ScreenShots
<p>
  <img src="https://raw.githubusercontent.com/adetuyiTolu/LagosDev/master/screenshots/screen1.png" />
  <img src="https://raw.githubusercontent.com/adetuyiTolu/LagosDev/master/screenshots/screen2.png" />
  <img src="https://raw.githubusercontent.com/adetuyiTolu/LagosDev/master/screenshots/screen3.png" />
  <img src="https://raw.githubusercontent.com/adetuyiTolu/LagosDev/master/screenshots/screen4.png" />
  <img src="https://raw.githubusercontent.com/adetuyiTolu/LagosDev/master/screenshots/screen5.png" />
</p>

#How it works
<ol>
  <li>On launch, the application checks if there is internet access</li>
  <li>if there is internet access, it retrieves the list of lagos developers from github and save details locally</li>
  <li>otherwise, it loads from the local storage</li>
  <li>then update the RecyclerView</li>
  <li>User can as well refresh/reload by swiping down via the swipeRefreshLayout attached to the recyclerView</li>
  <li>A click on each developer opens up developer details</li>
  <li>via the detailed page, user can:
    <ul>
      <li>View developer picture and username</li>
      <li>share developers details</li>
      <li>click on developers github link to get more information</li>
    </ul>
   </li>
</ol>
