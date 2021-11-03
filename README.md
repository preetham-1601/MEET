# MEETUP
Basically a Chat Application.
Meetup is a android application that allows you to find and join groups related to your own personal interests. However, instead of just talking about these interests online,
Meetup is used to organize offline and in-person meetings in your area. Common locations for Meetup groups include cafes, parks, and sometimes even people's homes.
Meetup is a great resource for meeting new people, getting involved to share there interests among different individuals.
This is where you can send and accept requests from different people and communicate with one another sharing views, ideas, perspectives, thoughts and lot. This is a best way to connect with various individuals over the globe and pursue one’s passion together.

## TECHNOLOGIES USED
   - ANDROID STUDIO
   - KOTLIN , XML
   - FIREBASE
   
##	MODULES
In this project we are having following modules:
1.	Login
2.	Bottom Navigation Drawer
3.	Main Activity (Chats)
4.  Messages
5.	Requests
6.	Settings
###	Modules Description

####	LOGIN :

Validation for the length of the email and password(Password should be hidden when typed) ,Navigation to registration page on corresponding click Upon clicking the login button, if all the validations are true, the code should hit the Firebase authentication and validate the credentials. If the credentials were correct, take the user to all restaurants page.
####	BOTTOM NAVIGATION DRAWER:

Click Home navigates to chats fragment . requests -> Requests fragment Settings -> Settings Fragment.Clicking on Logout, takes the user to the login page, After logout, all the preferences are cleared.
####	CHATS :

Opening this fragment, sends a request to the firebase real time database and fetches the list of chats. The fetched data is parsed and the data is inflated in the recycler view. When you click on the chats, you will be taken to a messaging area where you can communicate with each other. 
#### MESSAGES:
Messages to different chats will be seen in this activity.

####	REQUESTS :

Opening this screen, sends a request to the firebase database requesting the details of the friend requests sent and received. Clicking on add button, will send a request to other user , who may wants to connect, and requests sent to the current user will also be displayed.
###	SETTINGS :

Clicking  on this fragment,  shows the profile information and helps in editing the details and interests provided by the user and can make changes over all the fragments .  Logout option is seen on the action bar in order to logout the user.  
