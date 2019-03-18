What have I learned from building the app?

When I got into building this app it was clear to me that I’m interested in learning as much as possible. I wanted to follow current best practices and create a proper MVVM architecture, using Dagger 2, Retrofit 2 and above all the Architecture Components : ViewModel, LiveData and Room. To this pile of wonders I wanted to add some Kotlin usage. I didn’t want to use Kotlin for the whole app, because I’ve never used it before, and it really increased complexity.
The main idea of MVVM is to maintain the separation of concerns and control the UI from the model. Although I didn’t create any tests for this app, It is completely testable, from unit tests to functionality tests because it’s broken down to it’s M-model, V-views and VM-viewmodels.
It makes work pretty clean and ordered because you, as a programmer, know where to put things, and it’s clear when things are in the wrong place. 

Due to the small scope of the app, I’ve chose to use only one Dagger2 component, the application one. It took care of my need for singleton-application scope objects such as the network, persistence and viewmodels Modules. In a more spread out app, I would probably prefer to create more subcomponents. 

When it comes to using Kotlin, every time I tried it, it was great discovering it’s new abilities and it’s way o simplify all. For example, it’s data class that I used for the FlickrPrePhoto object.
Using LiveData makes handling the lifeCycle events of the app really easy. Everything is updated quickly and is maintained through configuration changes and killing the main Activity. Before adding the Room persistence, I’ve directly observed the response from the Adapter and photo appeared rapidly and without any lag. I have never seen fewer ANR while developing an app.

I have first implemented the integration with the flickr api, and only after went for the Google Locations. I have much experience with the location different strategies, and decided to opt in immediately with the current Google recommended usage. After they changed their way of handling background services, it was crucial to get it right.
I’ve implemented a foreground Lifecycleservice that sprouts a constant Notification informing the user that the app is still alive and running in background. I’ve decided not go with the IntentService, having concerns with it being killed by the system in API level 26 and up. Next time I’ll try the JobIntentService.



If you have any question,
I’ll be happy to answer.

Thanks,
Nitzan
