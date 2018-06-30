This was a test app for a firm I applied to.

I figured it was a decent enough assignment that I would add onto it. 

I used RX, Retro, GSON for the network layer
I just serilaized Objects with GSON and stored them as plain text in the internal file dir for persitance.

I used 1 activity with a view pager of fragments.  Each fragment observes the data observable class. 

When making network calls, you update the dataobservable class which then notifies the observers.  
When updating the data set, the data observable sets changed but waits for the mutator to call the notify.

An exmaple of a chain of operators in rxjava and retrofit is in the movie fragment. 

State persists through orientation changes. 

Favorites persist locally. 

Eventually I'll add either sqlite with my own DAO or use Room and LiveModel to replace the current persitance and if I use LiveModel it will replace my data observable.  

Someday I may even find time to add some test.
